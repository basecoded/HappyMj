package com.s4game.server.login.service;

import com.s4game.server.bus.role.entity.UserRole;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月7日 下午3:14:49
 *
 */

public interface ILoginService {
	
	/**
	 * 获取角色列表
	 * 
	 * 返回系统时间
	 * 
	 * @param userId
	 * @param serverId
	 * @param timestamp
	 * @param sign
	 * @param fangChenmi
	 */
	public UserRole in(String userId, String serverId, String name, String face, String timestamp, String sign);


}
