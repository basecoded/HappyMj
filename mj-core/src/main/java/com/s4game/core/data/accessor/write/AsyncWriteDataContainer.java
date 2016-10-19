package com.s4game.core.data.accessor.write;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;
import com.s4game.core.data.accessor.database.StatementUtils;

/**
 * @author zeusgooogle
 * @date 2014-10-2 下午07:59:45
 */
public class AsyncWriteDataContainer {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncWriteDataContainer.class);

	private String identity;

	private AsyncWriteManager asyncWriteManager;

	private Runnable triggerTask = new Runnable() {

		public void run() {
			AsyncWriteDataContainer.this.flush();
			AsyncWriteDataContainer.this.reschedule();
		}
	};

	private LinkedHashMap<String, EntityOperate> entityOperates = new LinkedHashMap<String, EntityOperate>();

	private Object dataUpdateLock = new Object();

	private Object dataSyncLock = new Object();

	private long lastOperateTime = System.currentTimeMillis();

	private boolean closed = false;

	private LinkedHashMap<String, EntityOperate> saveOperates = null;

	public AsyncWriteDataContainer(String identity, AsyncWriteManager asyncWriteManager) {
		this.identity = identity;
		this.asyncWriteManager = asyncWriteManager;
		
		reschedule();
	}

	public String getIdentity() {
		return this.identity;
	}

	public void updateLastOperateTime() {
		this.lastOperateTime = System.currentTimeMillis();
	}

	public void insert(IEntity entity) {
		synchronized (this.dataUpdateLock) {
			if (this.closed) {
				return;
			}
			((AbsVersion) entity).updateVersion();

			EntityOperate entityOperate = getEntityOperate(getKey(entity), this.entityOperates);
			entityOperate.insert(entity, true);
			updateLastOperateTime();
		}
	}

	public void update(IEntity entity) {
		synchronized (this.dataUpdateLock) {
			if (this.closed) {
				return;
			}
			EntityOperate entityOperate = getEntityOperate(getKey(entity), this.entityOperates);
			entityOperate.update(entity, true);
			updateLastOperateTime();
		}
	}

	public void delete(IEntity entity) {
		synchronized (this.dataUpdateLock) {
			if (this.closed) {
				return;
			}
			EntityOperate entityOperate = getEntityOperate(getKey(entity), this.entityOperates);
			boolean bool = entityOperate.delete(entity, true);
			if (bool) {
				this.entityOperates.remove(entityOperate.getId());
			}
			updateLastOperateTime();
		}
	}

	public void flush() {
		LinkedHashMap<String, EntityOperate> linkedHashMap = null;
		synchronized (this.dataUpdateLock) {
			if (this.entityOperates.size() > 0) {
				linkedHashMap = this.entityOperates;
				this.entityOperates = new LinkedHashMap<String, EntityOperate>();
			}
		}
		if (null != linkedHashMap) {
			synchronized (this) {
				if (null == this.saveOperates) {
					this.saveOperates = linkedHashMap;
				} else {
					for (EntityOperate operate : linkedHashMap.values()) {

						try {
							EntityOperate saveOp = null;
							IEntity entity = operate.getInsert();
							if (null != entity) {
								saveOp = getEntityOperate(operate.getId(), this.saveOperates);
								saveOp.insert(entity, false);
							}

							entity = operate.getUpdate();
							if (null != entity) {
								saveOp = getEntityOperate(operate.getId(), this.saveOperates);
								saveOp.update(entity, false);
							}

							entity = operate.getDelete();
							if (null != entity) {
								saveOp = getEntityOperate(operate.getId(), this.saveOperates);
								boolean success = saveOp.delete(entity, false);
								if (success) {
									this.saveOperates.remove(operate.getId());
								}
							}
						} catch (Exception e) {
							LOG.error("flush error,entity info: {}", operate.getEntityInfo(), e);
						}
					}
				}
				this.asyncWriteManager.accept2write(this);
			}
		}
	}

	private void reschedule() {
		synchronized (this.dataUpdateLock) {
			if (!this.closed) {
				this.asyncWriteManager.getScheduledExecutor().schedule(this.triggerTask, this.asyncWriteManager.getWritePeriod(), TimeUnit.SECONDS);
			}
		}
	}

	public boolean canClean() {
		synchronized (this) {
			synchronized (this.dataUpdateLock) {
				if (!this.closed) {
					this.closed = ((null == this.saveOperates) && (System.currentTimeMillis() - this.lastOperateTime > this.asyncWriteManager.getCleanPeriod()));
				}
			}
		}
		return this.closed;
	}

	public void close() {
		synchronized (this.dataUpdateLock) {
			this.closed = true;
		}
		flush();
	}

	public void sync() {
		LinkedHashMap<String, EntityOperate> linkedHashMap = null;
		synchronized (this) {
			if (null != this.saveOperates) {
				linkedHashMap = this.saveOperates;
				this.saveOperates = null;
			}
		}
		synchronized (this.dataSyncLock) {
			if (null != linkedHashMap) {
				Map<String, List<EntityOperate>> map = new HashMap<String, List<EntityOperate>>();
				for (EntityOperate operate : linkedHashMap.values()) {
					String name = operate.getEntityName();

					List<EntityOperate> list = map.get(name);
					if (null == list) {
						list = new ArrayList<EntityOperate>();
						map.put(name, list);
					}
					list.add(operate);
				}

				// handler commit & batch
				SqlSession session = this.asyncWriteManager.getTemplate().getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
				try {
					for (List<EntityOperate> opts : map.values()) {
						try {
							for (EntityOperate op : opts) {
								execute(op, session);
							}
							
							session.flushStatements();
						} catch (Exception e) {
							LOG.error("batch save to db falied.", e);
							
							/**
							 * signle save to db
							 */
							for (EntityOperate op : opts) {
								execute(op, session);
								session.flushStatements();
							}
						}
					}

					session.commit();
				} catch (Exception e) {
					LOG.error("sql session commit failed. ", e);
				} finally {
					session.close();
				}
			}
		}
	}

	private void execute(EntityOperate entityOperate, SqlSession session) throws SQLException {
		IEntity entity = entityOperate.getInsert();
		if (null != entity) {
			session.insert(StatementUtils.getStatement(StatementUtils.INSERT_OP, entity), entity);
		}

		entity = entityOperate.getUpdate();
		if (null != entity) {
			session.update(StatementUtils.getStatement(StatementUtils.UPDATE_OP, entity), entity);
		}

		entity = entityOperate.getDelete();
		if (null != entity) {
			session.delete(StatementUtils.getStatement(StatementUtils.DELETE_OP, entity), entity);
		}
	}

	private EntityOperate getEntityOperate(String id, LinkedHashMap<String, EntityOperate> linkedHashMap) {
		EntityOperate entityOperate = linkedHashMap.get(id);
		if (null == entityOperate) {
			entityOperate = new EntityOperate(id);
			linkedHashMap.put(id, entityOperate);
		}
		return entityOperate;
	}

	private String getKey(IEntity entity) {
		return ((AbsVersion) entity).getVersion() + entity.getClass().getSimpleName() + entity.getPrimaryKeyValue();
	}

}
