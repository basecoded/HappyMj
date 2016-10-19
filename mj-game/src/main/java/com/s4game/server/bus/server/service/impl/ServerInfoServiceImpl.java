package com.s4game.server.bus.server.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.s4game.server.bus.server.dao.IServerInfoDao;
import com.s4game.server.bus.server.entity.ServerInfo;
import com.s4game.server.bus.server.service.IServerInfoService;
import com.s4game.server.utils.GameConfigureUtil;

/**
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月23日 下午10:17:21 
 *
 */
@Component
public class ServerInfoServiceImpl implements IServerInfoService {

	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IServerInfoDao serverInfoDao;
	
	private ServerInfo serverInfo;
	
	@Override
	public void init() {
		List<ServerInfo> list = serverInfoDao.loadAll();
		if( list.isEmpty() ) {
			ServerInfo server = new ServerInfo();
			server.setId( GameConfigureUtil.getServerId() );
			server.setStartTime(new Timestamp(System.currentTimeMillis()));
			
			serverInfoDao.insert(server);
			
			this.serverInfo = server;
		} else {
			this.serverInfo = list.get(0);
		}
		
		String prefixId = GameConfigureUtil.getServerPrefixRolename();
		if( null != prefixId && !prefixId.equals(serverInfo.getPrefixId())) {
			serverInfo.setPrefixId(prefixId);
			
			serverInfoDao.update(serverInfo);
		}
		
		LOG.info("server size: " + list.size());
	}

	@Override
	public Timestamp getServerStartTime() {
		return serverInfo.getStartTime();
	}

	@Override
	public Timestamp getServerHefuTime() {
		return serverInfo.getHefuTime();
	}

}
