package com.s4game.core.data.accessor.impl;

import java.util.List;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.IQueryFilter;
import com.s4game.core.data.accessor.IDbAccessor;
import com.s4game.core.data.accessor.cache.IEntityCache;
import com.s4game.core.data.accessor.cache.manager.CacheManager;
import com.s4game.core.data.accessor.cache.model.EntityCacheContainer;
import com.s4game.core.data.accessor.write.AsyncWriteManager;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月21日 下午4:27:56
 * 
 */

public class EntityCacheAccessor implements IDbAccessor {

	private CacheManager cacheManager;

	private AsyncWriteManager asyncWriteManager;

	@Override
	public Object insert(String key, IEntity entity, Class<? extends IEntity> clazz) {
		IEntityCache entityCache = cacheManager.getRoleCache(key);

		EntityCacheContainer container = entityCache.getContainer(clazz);
		if (null == container) {
			entityCache.addData(entity, clazz);
		} else {
			container.insert(entity);
		}

		asyncWriteManager.getDataContainer(key).insert(entity);

		return entity;
	}

	@Override
	public IEntity query(String key, Object param, Class<? extends IEntity> clazz) {
		IEntityCache entityCache = cacheManager.getRoleCache(key);
		EntityCacheContainer container = entityCache.getContainer(clazz);
		
		if(null != container) {
			return container.load(key);
		}
		return null;
	}

	@Override
	public List<IEntity> queryList(String key, Object param, Class<? extends IEntity> clazz) {
		IEntityCache entityCache = cacheManager.getRoleCache(key);
		EntityCacheContainer container = entityCache.getContainer(clazz);
		
		if(null != container) {
			return container.loadAll();
		}
		return null;
	}

	@Override
	public List<IEntity> queryList(String key, Object param, IQueryFilter<IEntity> queryFilter, Class<? extends IEntity> clazz) {
		IEntityCache entityCache = cacheManager.getRoleCache((String)param);
		EntityCacheContainer container = entityCache.getContainer(clazz);
		
		if(null != container) {
			return container.loadAll(queryFilter);
		}
		return null;
	}

	@Override
	public int delete(String key, Object param, Class<? extends IEntity> clazz) {
		boolean success = false;
		IEntityCache entityCache = cacheManager.getRoleCache(key);

		EntityCacheContainer container = entityCache.getContainer(clazz);
		IEntity entity = null;
		if (null != container) {
			entity = container.delete(key);
			success = (null != entity);
		}

		asyncWriteManager.getDataContainer(key).delete(entity);
		return success ? 1 : 0;
	}

	@Override
	public int update(String key, IEntity entity) {
		boolean success = false;
		IEntityCache entityCache = cacheManager.getRoleCache(key);

		EntityCacheContainer container = entityCache.getContainer(entity.getClass());
		if (null != container) {
			success = container.update(entity);
		}

		asyncWriteManager.getDataContainer(key).update(entity);
		return success ? 1 : 0;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setAsyncWriteManager(AsyncWriteManager asyncWriteManager) {
		this.asyncWriteManager = asyncWriteManager;
	}

}
