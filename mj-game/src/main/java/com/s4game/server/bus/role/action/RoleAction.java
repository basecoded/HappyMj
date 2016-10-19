package com.s4game.server.bus.role.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.server.bus.swap.BusMsgSender;

/**
*
* @author zeusgoogogle@gmail.com
* @sine 2016年9月15日 下午9:04:12
*
*/
@ActionWorker
public class RoleAction {

	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BusMsgSender msgSender;

}
