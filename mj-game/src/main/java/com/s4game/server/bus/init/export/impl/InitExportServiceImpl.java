package com.s4game.server.bus.init.export.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s4game.server.bus.init.command.InitCommands;
import com.s4game.server.bus.init.export.InitExportService;
import com.s4game.server.bus.swap.BusMsgSender;

@Service
public class InitExportServiceImpl implements InitExportService {
	
	@Autowired
	private BusMsgSender busMsgSender;
	
	public void roleIn(String roleId, String ip) {
		busMsgSender.send2BusInit(InitCommands.ROLE_IN, roleId, ip);
	}

	public void roleOut(String roleId) {
		busMsgSender.send2BusInit(InitCommands.ROLE_OUT, roleId, null);
	}
}
