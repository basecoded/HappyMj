package com.s4game.server.bus.role.service;

import com.s4game.server.bus.role.entity.UserRole;
import com.s4game.server.bus.role.export.RoleWrapper;

public interface IUserRoleService {

	public String getRoleId(String name);

	public String getUserId(String name);

	public UserRole getRole(String userId, String serverId);

	public UserRole createRole(String userId, String serverId, String name, String face, String platform);

	public Object[] selectRoleIdsByUserId(String userId, String serverId);

	public RoleWrapper getRole(String roleId);

	public void online(String roleId);

	public void offline(String roleId);

}