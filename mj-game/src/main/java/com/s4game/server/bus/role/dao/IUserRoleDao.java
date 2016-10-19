package com.s4game.server.bus.role.dao;

import java.util.List;

import com.s4game.core.data.accessor.dao.ICacheInitDaoOperation;
import com.s4game.server.bus.role.entity.UserRole;

public interface IUserRoleDao extends ICacheInitDaoOperation<UserRole> {
	
	public List<UserRole> getRolesFromDb(String userId, String serverId);

	public List<UserRole> getRole(String name);

	public void insertRole(UserRole userRole);

	public UserRole getRoleByRoleId(String id);

	public List<UserRole> getRolesLikeName(String name);

	public UserRole initRole(String id);

	public void updateRole(UserRole userRole);

	public List<String> getRolesOrderLevel();

	public List<String> selectRoleIdsByUserId(String userId, String serverId);

	public List<String> selectRoleIdsByRoleNames(String roleNames, String serverId);

	public List<UserRole> getRolesLikeName(String name, String job);

	public List<UserRole> selectRoleInfosByUserId(String userId, String serverId);
}