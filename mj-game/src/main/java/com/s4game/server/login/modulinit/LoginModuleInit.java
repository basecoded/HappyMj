package com.s4game.server.login.modulinit;

import org.springframework.stereotype.Component;

import com.s4game.server.login.LoginModuleInfo;
import com.s4game.server.login.commond.LoginCommands;
import com.s4game.server.public_.share.PublicModuleInit;
import com.s4game.server.share.event.EventHandleCommands;
import com.s4game.server.share.moduleinit.Group;


/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月7日 下午2:58:03
 *
 */
@Component
public class LoginModuleInit extends PublicModuleInit {

	@Override
	protected InCmd getInCmd() {
		return new InCmd(LoginModuleInfo.MODULE_NAME, Group.LOGIN.name, new String[] {LoginCommands.IN, LoginCommands.LOGIN_IN});
	}

	@Override
	public EventHandleCommands getEventHandleCommands() {
		return null;
	}

}
