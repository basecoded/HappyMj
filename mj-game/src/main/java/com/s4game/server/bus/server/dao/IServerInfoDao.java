package com.s4game.server.bus.server.dao;

import java.util.List;

import com.s4game.server.bus.server.entity.ServerInfo;

/**
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月23日 下午10:11:58 
 *
 */
public interface IServerInfoDao {

	public List<ServerInfo> loadAll();
	
	public void insert(ServerInfo server);
	
	public void update(ServerInfo server);
	
}
