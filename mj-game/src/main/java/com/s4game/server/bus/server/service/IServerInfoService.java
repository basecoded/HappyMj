package com.s4game.server.bus.server.service;

import java.sql.Timestamp;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月23日 下午10:16:19
 * 
 */
public interface IServerInfoService {

	public void init();

	public Timestamp getServerStartTime();

	public Timestamp getServerHefuTime();

}
