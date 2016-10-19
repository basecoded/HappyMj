package com.s4game.core.data.accessor.cache.model;

import java.util.List;

import com.s4game.core.data.IEntity;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月21日 上午11:06:47
 * 
 */

public class EntityCacheContainerFactory {

	public static EntityCacheContainer create(List<IEntity> list) {
		return new EntityCacheContainer(list);
	}
}
