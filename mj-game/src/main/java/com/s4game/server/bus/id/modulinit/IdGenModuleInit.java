package com.s4game.server.bus.id.modulinit;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.share.moduleinit.BusModuleInit;

/**
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月25日 下午9:46:16 
 *
 */
@Component
public class IdGenModuleInit extends BusModuleInit {

	@Override
	protected InCmd getInCmd() {
		return null;
	}

}
