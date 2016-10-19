package com.s4game.server.bus.role.dao.cache;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCache;
import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.server.bus.role.dao.IUserRoleDao;
import com.s4game.server.bus.role.entity.UserRole;

@Component
public class RoleCacheModelLoader implements IEntityCacheModelLoader {
	
	@Resource
	private IUserRoleDao userRoleDao;

	public void load(String id, IEntityCache entityCache) {
		entityCache.addData(this.userRoleDao.initRole(id), UserRole.class);
	}
}
