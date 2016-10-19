package com.s4game.core.data.accessor.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.accessor.cache.model.EntityCacheContainer;
import com.s4game.core.data.accessor.cache.model.EntityCacheContainerFactory;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月21日 上午11:11:18
 *
 */

public class EntityCache implements IEntityCache {

	private String identity;
	
	private boolean activate = true;

    private long cleanInterval = 1;

    private long freezeTime = System.currentTimeMillis();
	
    private Map<Class<? extends IEntity>, EntityCacheContainer> containers = new HashMap<Class<? extends IEntity>, EntityCacheContainer>();
    
	public EntityCache(String identity) {
		this.identity = identity;
	}
	
	@Override
	public String getIdentity() {
		return identity;
	}

	@Override
	public void activate() {
		this.activate = true;
	}

	@Override
	public void freeze() {
		this.activate = false;
		this.freezeTime = System.currentTimeMillis();
	}

	@Override
	public void setCleanInterval(long interval) {
		this.cleanInterval = interval;
	}

	@Override
	public boolean canClean() {
		return (!this.activate) && (System.currentTimeMillis() - this.freezeTime > this.cleanInterval);
	}

	@Override
	public void addData(List<IEntity> list, Class<? extends IEntity> clazz) {
		EntityCacheContainer container = EntityCacheContainerFactory.create(list);
		containers.put(clazz, container);
	}

	@Override
	public void addData(IEntity entity, Class<? extends IEntity> clazz) {
		List<IEntity> list = new ArrayList<IEntity>();
		if (null != entity) {
		    list.add(entity);
		}
		
		addData(list, clazz);
	}

	@Override
	public EntityCacheContainer getContainer(Class<? extends IEntity> clazz) {
		return containers.get(clazz);
	}

}
