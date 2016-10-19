package com.s4game.server.bus.share.service;

import java.util.Collection;

public interface IRoleStateService {

	public void change2online(String roleId);

	public void change2offline(String roleId);

	public boolean isOnline(String roleId);

	public Collection<String> getAllOnlineRoleids();
}