package com.s4game.server.bus.init.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.manager.CacheManager;
import com.s4game.core.event.IEventService;
import com.s4game.server.bus.init.event.publish.RoleLoginEvent;
import com.s4game.server.bus.init.service.InitService;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.bus.share.service.IRoleStateService;

@Component
public class InitServiceImpl implements InitService {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private boolean usecache = true;

	@Resource
	private CacheManager roleCacheManager;

	@Resource
	private IRoleStateService roleStateService;

	@Resource
	private IEventService eventService;
	
	@Resource
	private IUserRoleService userRoleService;

	@Override
	public void roleIn(String roleId, String ip) {
		if (this.usecache) {
			this.roleCacheManager.activateRoleCache(roleId);
		}

		this.roleStateService.change2online(roleId);

		try {
			this.eventService.publish(new RoleLoginEvent(roleId, ip));
		} catch (Exception e) {
			LOG.error("roleIn exception roleId: {}", roleId, e);
		}
	}

	@Override
	public void roleOut(String roleId) {
		if (this.usecache) {
			this.roleCacheManager.freezeRoleCache(roleId);
		}
		this.roleStateService.change2offline(roleId);
	}

	@Override
	public void syncRoleIn(String roleId, String ip) {

	}

	@Override
	public void roleOutOnServerClose(String roleId) {
		roleOut(roleId);
	}

	@Override
	public void syncRoleOut(String roleId) {
		roleOut(roleId);
	}

}