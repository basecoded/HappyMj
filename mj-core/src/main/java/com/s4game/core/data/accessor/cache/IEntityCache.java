package com.s4game.core.data.accessor.cache;

import java.util.List;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.accessor.cache.model.EntityCacheContainer;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月21日 上午10:47:48
 * 
 */

public interface IEntityCache {

	/**
	 * entity cache pirmary key
	 * 
	 * @return
	 */
	public String getIdentity();

	public void activate();

	public void freeze();

	public void setCleanInterval(long interval);

	public boolean canClean();

	public void addData(List<IEntity> list, Class<? extends IEntity> clazz);

	public void addData(IEntity entity, Class<? extends IEntity> clazz);

	public EntityCacheContainer getContainer(Class<? extends IEntity> clazz);
}
