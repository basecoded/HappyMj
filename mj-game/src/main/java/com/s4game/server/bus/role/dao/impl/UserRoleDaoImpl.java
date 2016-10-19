package com.s4game.server.bus.role.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.AccessType;
import com.s4game.core.data.accessor.GlobalIdentity;
import com.s4game.server.bus.role.dao.IUserRoleDao;
import com.s4game.server.bus.role.entity.UserRole;
import com.s4game.server.bus.share.dao.BusAbsCacheDao;

@Component
public class UserRoleDaoImpl extends BusAbsCacheDao<UserRole> implements IUserRoleDao {
	
	@Override
	public List<UserRole> getRolesFromDb(String userId, String serverId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("serverId", serverId);
		
		return getRecords(param, GlobalIdentity.get(), AccessType.getDirectDbType());
	}

	@Override
	public List<UserRole> getRole(String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		
		return getRecords(param, GlobalIdentity.get(), AccessType.getDirectDbType());
	}

	@Override
	public void insertRole(UserRole paramUserRole) {
		insert(paramUserRole, paramUserRole.getId(), AccessType.getDirectDbType());
	}

	@Override
	public UserRole getRoleByRoleId(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		
		List<UserRole> list = getRecords(param, GlobalIdentity.get(), AccessType.getDirectDbType());
		if (null == list || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<UserRole> getRolesLikeName(String name) {
		name = "%" + name + "%";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		
		return query("selectUserRoleLikeName", param);
	}

	@Override
	public UserRole initRole(String id) {
		return load(id, id, AccessType.getDirectDbType());
	}

	@Override
	public void updateRole(UserRole paramUserRole) {
		update(paramUserRole, paramUserRole.getId(), AccessType.getDirectDbType());
	}

	@Override
	public List<String> getRolesOrderLevel() {
		return query("selectUserRoleOrderByLevel", new HashMap<String, Object>());
	}

	@Override
	public List<String> selectRoleIdsByUserId(String userId, String serverId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("serverId", serverId);
		
		return query("selectRoleIdsByUserId", param);
	}

	@Override
	public List<String> selectRoleIdsByRoleNames(String roleNames, String serverId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleNames", roleNames);
		param.put("serverId", serverId);
		
		return query("selectUserRolesByRoleNames", param);
	}

	@Override
	public List<UserRole> getRolesLikeName(String name, String job) {
		name = "%" + name + "%";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		if (!job.equals("0")) {
			param.put("job", job);
		}
		return query("selectUserRoleLikeNameOrJob", param);
	}

	@Override
	public List<UserRole> selectRoleInfosByUserId(String userId, String serverId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("serverId", serverId);
		
		return getRecords(param);
	}
}