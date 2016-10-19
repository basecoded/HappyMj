package com.s4game.server.io.message;

import com.s4game.core.message.Message;
import com.s4game.server.io.IoModuleInfo;
import com.s4game.server.share.moduleinit.CommandRegister;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月7日 下午6:04:42
 *
 */

public class IoMessage extends Message {

	public static final String IO_MSG_OUT_CMD = "io_msg_out";

	public IoMessage(Message message) {
		super(message);
	}

	@Override
	public String getCommand() {
		String command = super.getCommand();
		if (CommandRegister.isModule(command, IoModuleInfo.MODULE_NAME)) {
			return command;
		}
		
		return IO_MSG_OUT_CMD;
	}
	
	public String getRealCommand() {
		return super.getCommand();
	}
}
