package com.s4game.server.share.export.impl;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCacheLoader;
import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.server.share.export.IEntityCacheLoaderExportService;

/**
 * @author zeusgooogle@gmail.com
 * @date 2014年10月21日 下午10:02:04
 */

@Component
public class EntityCacheLoaderExportServiceImpl implements IEntityCacheLoaderExportService {
	
	private IEntityCacheLoader entityCacheLoader;

	public EntityCacheLoaderExportServiceImpl() {
	}

	public void setEntityCacheLoader(IEntityCacheLoader entityCacheLoader) {
		this.entityCacheLoader = entityCacheLoader;
	}

	public void injectEntityCacheModelLoader(IEntityCacheModelLoader entityCacheModelLoader) {
		this.entityCacheLoader.addCacheModelLoader(entityCacheModelLoader);
	}
}
