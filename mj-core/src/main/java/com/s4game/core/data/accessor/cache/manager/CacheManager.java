package com.s4game.core.data.accessor.cache.manager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.core.data.accessor.cache.IEntityCache;
import com.s4game.core.data.accessor.cache.IEntityCacheLoader;
import com.s4game.core.data.accessor.exception.CacheException;
import com.s4game.core.data.accessor.write.AsyncWriteManager;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月21日 上午11:38:51
 * 
 */

public class CacheManager {
    
    private Logger LOG = LoggerFactory.getLogger(getClass());

	private ConcurrentMap<String, IEntityCache> caches = new ConcurrentHashMap<String, IEntityCache>();

	private String name;

	private boolean needClean = false;

	private long cleanPeriod = 300000; //5 min

	private IEntityCacheLoader entityCacheLoader;

	private AsyncWriteManager asyncWriteManager;

	public CacheManager() {

	}

	public void setCaches(ConcurrentMap<String, IEntityCache> caches) {
		this.caches = caches;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNeedClean(boolean needClean) {
		this.needClean = needClean;
	}

	public void setCleanPeriod(Long cleanPeriod) {
		this.cleanPeriod = cleanPeriod;
	}

	public long getCleanPeriod() {
        return cleanPeriod;
    }

    public void setCleanPeriod(long cleanPeriod) {
        this.cleanPeriod = cleanPeriod;
    }

    public String getName() {
        return name;
    }

    public boolean isNeedClean() {
        return needClean;
    }

    public void setAsyncWriteManager(AsyncWriteManager asyncWriteManager) {
		this.asyncWriteManager = asyncWriteManager;
	}

	public void setEntityCacheLoader(IEntityCacheLoader entityCacheLoader) {
		this.entityCacheLoader = entityCacheLoader;
	}

	public void activateRoleCache(String identity) {
		if (null != this.asyncWriteManager) {
			this.asyncWriteManager.syncAllDataChange(identity);
		}

		IEntityCache entityCache = null;
		if (null == entityCache) {
			entityCache = this.entityCacheLoader.loadEntityCache(identity);
			this.caches.put(identity, entityCache);
		}
		entityCache.activate();
	}

	public void freezeRoleCache(String identity) {
		this.caches.remove(identity);
		if (null != this.asyncWriteManager) {
			this.asyncWriteManager.flushDataContainer(identity);
		}
	}

	public IEntityCache getRoleCache(String identity) {
		IEntityCache entityCache = (IEntityCache) this.caches.get(identity);
		if (null == entityCache) {
		    LOG.error("cache name: {}, can't matche data to identity: {}", name, identity);
		    
			throw new CacheException("can't  matched cache to identity: " + identity);
		}
		return entityCache;
	}

}
