package com.s4game.server.public_.share.service;

import java.util.Collection;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年6月11日 下午4:12:14
 * 
 */
public interface IPublicRoleStateService {

	public void change2PublicOnline(String roleId);

	public void change2PublicOffline(String roleId);

	public boolean isPublicOnline(String roleId);

	public Collection<String> getAllOnlineRoleIds();

}
