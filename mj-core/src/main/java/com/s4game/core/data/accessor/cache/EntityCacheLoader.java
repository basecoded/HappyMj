package com.s4game.core.data.accessor.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月21日 上午11:29:32
 * 
 */

public class EntityCacheLoader implements IEntityCacheLoader {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private List<IEntityCacheModelLoader> entityCacheModelLoaders;

	public EntityCacheLoader() {
	}

	public void setEntityCacheModelLoaders(List<IEntityCacheModelLoader> paramList) {
		this.entityCacheModelLoaders = paramList;
	}

	@Override
	public IEntityCache loadEntityCache(String key) {
		EntityCache entityCache = new EntityCache(key);
		if (null != this.entityCacheModelLoaders) {
			long start = System.currentTimeMillis();

			for (IEntityCacheModelLoader modelLoader : entityCacheModelLoaders) {
				try {
					modelLoader.load(key, entityCache);
				} catch (Exception e) {
					this.LOG.error("", e);
				}
			}
			
			long end = System.currentTimeMillis();
			LOG.info("Cache[{}], active all cache usetime: {} ms", key, (end - start));

		}
		return entityCache;
	}

	@Override
	public void addCacheModelLoader(IEntityCacheModelLoader paramIEntityCacheModelLoader) {
		if (null == this.entityCacheModelLoaders) {
			this.entityCacheModelLoaders = new ArrayList<IEntityCacheModelLoader>();
		}
		this.entityCacheModelLoaders.add(paramIEntityCacheModelLoader);
	}

}
