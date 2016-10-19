package com.s4game.server.public_.share.service.impl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

import com.s4game.core.sync.annotation.Sync;
import com.s4game.server.public_.share.constants.PublicNodeConstants;
import com.s4game.server.public_.share.service.IPublicRoleStateService;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年6月11日 下午4:13:57
 * 
 */
@Component
public class PublicRoleStateServiceImpl implements IPublicRoleStateService {
	
	private ConcurrentMap<String, String> publicRoleStates = new ConcurrentHashMap<String, String>();

	@Sync(component = PublicNodeConstants.COMPONENT_NAME, indexes = { 0 })
	@Override
	public void change2PublicOnline(String roleId) {
		publicRoleStates.put(roleId, roleId);
	}

	@Sync(component = PublicNodeConstants.COMPONENT_NAME, indexes = { 0 })
	@Override
	public void change2PublicOffline(String roleId) {
		publicRoleStates.remove(roleId);
	}

	@Override
	public boolean isPublicOnline(String roleId) {
		return publicRoleStates.containsKey(roleId);
	}

	@Override
	public Collection<String> getAllOnlineRoleIds() {
		return publicRoleStates.values();
	}

}
