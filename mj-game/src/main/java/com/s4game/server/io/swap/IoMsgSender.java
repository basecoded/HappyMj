package com.s4game.server.io.swap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.message.Message;
import com.s4game.server.io.IoModuleInfo;
import com.s4game.server.message.manager.SwapManager;
import com.s4game.server.share.moduleinit.CommandRegister;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月7日 下午6:11:17
 *
 */
@Component
public class IoMsgSender {

	@Resource
	private IoMsgDispatcher ioDispatcher;
	
	@Resource
	private SwapManager swapManager;
	
	public void swap(Message message) {
		String command = message.getCommand();
		if( CommandRegister.isModule(command, IoModuleInfo.MODULE_NAME) ) {
			ioDispatcher.in(message);
		}else {
			swapManager.swap(message);
		}
	}
	
}
