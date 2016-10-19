package com.s4game.core.data.accessor.cache.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.IQueryFilter;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月21日 上午10:49:43
 * 
 */

public class EntityCacheContainer {

	private ConcurrentMap<Object, IEntity> entities = new ConcurrentHashMap<Object, IEntity>();

	public EntityCacheContainer() {

	}

	public EntityCacheContainer(List<IEntity> list) {
		for (IEntity entity : list) {
			entities.put(entity.getPrimaryKeyValue(), entity);
		}
	}

	public boolean insert(IEntity entity) {
		if (entities.containsKey(entity.getPrimaryKeyValue())) {
			return false;
		}

		entities.put(entity.getPrimaryKeyValue(), entity);
		return true;
	}

	public boolean update(IEntity entity) {
		if (!entities.containsKey(entity.getPrimaryKeyValue())) {
			return false;
		}

		entities.put(entity.getPrimaryKeyValue(), entity);
		return true;
	}

	public IEntity delete(Object key) {
		return entities.remove(key);
	}

	public IEntity load(Object key) {
		return entities.get(key);
	}

	public List<IEntity> loadAll() {
		return new ArrayList<IEntity>(entities.values());
	}

	public List<IEntity> loadAll(IQueryFilter<IEntity> filter) {
		if (entities.isEmpty()) {
			return null;
		}

		List<IEntity> temp = new ArrayList<IEntity>();
		for (IEntity entity : entities.values()) {
			if ((!filter.stopped()) && (filter.check(entity))) {
				temp.add(entity);
			}
		}

		return temp;
	}
}
