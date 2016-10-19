package com.s4game.core.data.accessor.write;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.core.SpringApplicationContext;
import com.s4game.core.sync.annotation.Sync;

/**
 * @author zeusgooogle
 * @date 2014-10-2 下午07:57:23
 */
public class AsyncWriteManager {

    private Logger LOG = LoggerFactory.getLogger(getClass());

	private static final String COMPONENT_NAME = "__async_write";

	private ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

	private long writePeriod = 300000L;

	private String name = "default async_write_manager";

	private int writerSize = 1;

	private SqlSessionTemplate template;

	private Writer[] writers;

	private ConcurrentMap<String, AsyncWriteDataContainer> dataContainers = new ConcurrentHashMap<String, AsyncWriteDataContainer>();

	public AsyncWriteManager() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWritePeriod(long writePeriod) {
		this.writePeriod = (writePeriod * 60L);
	}

	public void setWriterSize(int writerSize) {
		this.writerSize = writerSize;
	}

	public void setTemplate(SqlSessionTemplate template) {
		this.template = template;
	}

	public SqlSessionTemplate getTemplate() {
		return template;
	}

	private AsyncWriteManager getSpringAsyncWriteManager() {
		return (AsyncWriteManager) SpringApplicationContext.getApplicationContext().getBean(this.name);
	}

	@PostConstruct
	public void init() {
		if (this.writePeriod < 60L) {
			throw new RuntimeException("clean period must more than 1 minute.");
		}
		
		if (this.writerSize <= 0) {
			throw new RuntimeException("size of writer must > 0, [writerSize > 0].");
		}
		
		if (null == this.template) {
			throw new RuntimeException("SqlSessionTemplate can't be null.");
		}
		
		this.writers = new Writer[this.writerSize];
		for (int i = 0; i < this.writerSize; i++) {
			this.writers[i] = new Writer("AsyncWriteManager[" + this.name + "]-Writer-" + i);
		}
		
		Thread localThread = new Thread(new Runnable() {
			public void run() {
				try {
					for (;;) {
						Thread.sleep(AsyncWriteManager.this.getCleanPeriod());
						int i = 0;
						
						AsyncWriteManager asyncWriteManager = AsyncWriteManager.this.getSpringAsyncWriteManager();
						
						for (AsyncWriteDataContainer container : dataContainers.values()) {
							if (asyncWriteManager.cleanDataContainer(container.getIdentity(), container)) {
								i++;
							}
						}
						
						LOG.error("AsyncWriteManager[{}]:cleaned {},remain {}", name, i, dataContainers.size());
					}
				} catch (Exception localException) {
					LOG.error("", localException);
				}
			}
		}, "AsyncWriteManager[" + this.name + "]-Cleaner");
		localThread.setDaemon(true);
		localThread.start();
	}

	public void close() {
		LOG.error("server stop info:data container close...");
		
		Iterator<AsyncWriteDataContainer> iterator = this.dataContainers.values().iterator();
		while (iterator.hasNext()) {
			AsyncWriteDataContainer asyncWriteDataContainer = iterator.next();
			try {
				asyncWriteDataContainer.close();
			} catch (Exception e) {
				LOG.error("error on close worker[" + asyncWriteDataContainer.getIdentity() + "]", e);
			}
		}
		
		LOG.error("server stop info:start to data sync...");
		for (;;) {
			try {
				Thread.sleep(5000L);
			} catch (Exception e) {
			}
			
			long l = getSyncSize();
			if (l <= 0L) {
				int i = 0;
				for (Writer writer : this.writers) {
					if (writer.isWriting()) {
						i = 1;
						break;
					}
				}
				if (i == 0) {
					break;
				}
			}
			LOG.error("server stop info:data sync remain {}", l);
		}
		LOG.error("server stop info:data sync finished.");
	}

	private long getSyncSize() {
		long l = 0L;
		for (Writer writer : this.writers) {
			l += writer.getBalance();
		}
		return l;
	}

	@Sync(component = COMPONENT_NAME, indexes = { 0 })
	public AsyncWriteDataContainer getDataContainer(String identity) {
		AsyncWriteDataContainer asyncWriteDataContainer = this.dataContainers.get(identity);
		if (null == asyncWriteDataContainer) {
			asyncWriteDataContainer = new AsyncWriteDataContainer(identity, this);
			this.dataContainers.put(identity, asyncWriteDataContainer);
		}
		return asyncWriteDataContainer;
	}

	@Sync(component = COMPONENT_NAME, indexes = { 0 })
	public void flushDataContainer(String identity) {
		AsyncWriteDataContainer asyncWriteDataContainer = this.dataContainers.get(identity);
		if (null != asyncWriteDataContainer) {
			asyncWriteDataContainer.flush();
		}
	}

	@Sync(component = COMPONENT_NAME, indexes = { 0 })
	public boolean cleanDataContainer(String identity, AsyncWriteDataContainer asyncWriteDataContainer) {
		boolean bool = false;
		if ((null != asyncWriteDataContainer) && (asyncWriteDataContainer.canClean())) {
			asyncWriteDataContainer.flush();
			asyncWriteDataContainer.sync();
			this.dataContainers.remove(identity);
			bool = true;
		}
		return bool;
	}

	@Sync(component = COMPONENT_NAME, indexes = { 0 })
	public void syncAllDataChange(String identity) {
		AsyncWriteDataContainer asyncWriteDataContainer = this.dataContainers.get(identity);
		if (null != asyncWriteDataContainer) {
			asyncWriteDataContainer.flush();
			asyncWriteDataContainer.sync();
		}
	}

	private Writer getWriter() {
		Writer temp = null;
		long l1 = 0L;
		for (Writer writer : this.writers) {
			if (null == temp) {
				temp = writer;
				l1 = writer.getBalance();
			} else {
				long l2 = writer.getBalance();
				if (l2 < l1) {
					temp = writer;
					l1 = l2;
				}
			}
		}
		return temp;
	}

	public void accept2write(AsyncWriteDataContainer asyncWriteDataContainer) {
		getWriter().accept(asyncWriteDataContainer);
	}

	public ScheduledExecutorService getScheduledExecutor() {
		return this.scheduledExecutor;
	}

	public long getWritePeriod() {
		return this.writePeriod;
	}

	public long getCleanPeriod() {
		return this.writePeriod * 1000L * 3L;
	}

	private class Writer implements Runnable {

		private LinkedBlockingQueue<AsyncWriteDataContainer> writeQueue = new LinkedBlockingQueue<AsyncWriteDataContainer>();

		private volatile boolean writing = false;

		public Writer(String name) {
			new Thread(this, name).start();
		}

		public long getBalance() {
			return this.writeQueue.size();
		}

		public boolean isWriting() {
			return this.writing;
		}

		public void accept(AsyncWriteDataContainer asyncWriteDataContainer) {
			this.writeQueue.add(asyncWriteDataContainer);
		}

		public void run() {
			for (;;) {
				try {
					AsyncWriteDataContainer asyncWriteDataContainer = this.writeQueue.take();
					this.writing = true;

					sync(asyncWriteDataContainer);
				} catch (Exception e) {
					LOG.error("", e);
				} finally {
					this.writing = false;
				}
			}
		}

		protected void sync(AsyncWriteDataContainer asyncWriteDataContainer) {
			asyncWriteDataContainer.sync();
		}
	}

}
