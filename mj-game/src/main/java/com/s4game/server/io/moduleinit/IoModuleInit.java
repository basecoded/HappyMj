package com.s4game.server.io.moduleinit;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.share.moduleinit.BusModuleInit;
import com.s4game.server.io.IoModuleInfo;
import com.s4game.server.io.commond.IoCommands;
import com.s4game.server.share.event.EventHandleCommands;
import com.s4game.server.share.moduleinit.Group;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月7日 下午6:44:55
 *
 */
@Component
public class IoModuleInit extends BusModuleInit {

	@Override
	protected InCmd getInCmd() {
		return new InCmd(IoModuleInfo.MODULE_NAME, Group.IO.name, new String[] {IoCommands.KICK_OUT, IoCommands.PING});
	}
	
	@Override
	public EventHandleCommands getEventHandleCommands() {
		// TODO Auto-generated method stub
		return null;
	}

}
