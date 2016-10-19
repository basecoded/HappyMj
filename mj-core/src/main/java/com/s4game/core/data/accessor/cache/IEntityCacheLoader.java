package com.s4game.core.data.accessor.cache;


/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月21日 上午11:23:22
 * 
 */

public interface IEntityCacheLoader {

	public IEntityCache loadEntityCache(String key);

	public void addCacheModelLoader(IEntityCacheModelLoader entityCacheModelLoader);

}
