package com.s4game.server.share.export;

import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;

/**
 * @author zeusgooogle@gmail.com
 * @date 2014年10月21日 下午10:00:09
 */
public interface IEntityCacheLoaderExportService {

	public void injectEntityCacheModelLoader(IEntityCacheModelLoader entityCacheModelLoader);

}
