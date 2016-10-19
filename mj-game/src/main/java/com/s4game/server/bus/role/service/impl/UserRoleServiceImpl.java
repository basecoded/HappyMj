package com.s4game.server.bus.role.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.s4game.core.data.accessor.cache.manager.CacheManager;
import com.s4game.core.event.IEventService;
import com.s4game.server.bus.id.export.IdGenerator;
import com.s4game.server.bus.id.service.IIdGenService;
import com.s4game.server.bus.role.RoleModuleInfo;
import com.s4game.server.bus.role.dao.IUserRoleDao;
import com.s4game.server.bus.role.entity.UserRole;
import com.s4game.server.bus.role.event.publish.RoleCreateEvent;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.bus.share.service.IRoleStateService;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Resource
	private IUserRoleDao userRoleDao;

	@Resource
	private IIdGenService idGenService;

	@Resource
	private IdGenerator idGenerator;

	@Resource
	private IRoleStateService roleStateService;

	@Resource
	private IEventService eventService;

	@Autowired
	@Qualifier("roleCacheManager")
	private CacheManager roleCacheManager;

	@Override
	public String getRoleId(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserId(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole getRole(String userId, String serverId) {
		List<UserRole> list = userRoleDao.getRolesFromDb(userId, serverId);
		if (null != list && !list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public UserRole createRole(String userId, String serverId, String name, String face, String platform) {
		// 名称合法性，过滤词
		// nameCheck

//		String realName = GameConfigureUtil.getServerId() + "." + name;

		// 重名验证
//		List<UserRole> list = userRoleDao.getRole(realName);
//		if (null != list && !list.isEmpty()) {
//			return CreateRoleOutput.nameRepeated();
//		}

		UserRole userRole = roleInit(userId, serverId, name, face, platform);
		eventService.publish(new RoleCreateEvent(userRole));

		return userRole;
	}

	private UserRole roleInit(String userId, String serverId, String name, String face, String platform) {
		UserRole userRole = new UserRole();

		userRole.setId(idGenerator.getId4Module(RoleModuleInfo.MODULE_NAME));
		userRole.setUserId(userId);
		userRole.setServerId(serverId);
		userRole.setName(name);
		userRole.setJob("A");

		userRole.setSex(1);
		userRole.setFace(face);

		userRole.setCard(10);
		long l = System.currentTimeMillis();
		userRole.setCreateTime(new Timestamp(l));
		userRole.setOnlineTime(Long.valueOf(l));
		userRole.setUpgradeTime(Long.valueOf(l));
		userRole.setPlatform(platform);

		this.userRoleDao.insertRole(userRole);

		return userRole;
	}

	@Override
	public Object[] selectRoleIdsByUserId(String userId, String serverId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleWrapper getRole(String roleId) {
		UserRole userRole = null;

		if (this.roleStateService.isOnline(roleId)) {
			userRole = (UserRole) this.userRoleDao.cacheLoad(roleId, roleId);
		} else {
			userRole = this.userRoleDao.getRoleByRoleId(roleId);
		}
		if (null != userRole) {
			return new RoleWrapper(userRole);
		}

		return null;
	}

	@Override
	public void online(String roleId) {

	}

	@Override
	public void offline(String roleId) {

	}

}
