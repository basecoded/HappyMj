package com.s4game.server.message;

import com.s4game.core.message.Message;
import com.s4game.server.executor.Route;

/**
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月10日 下午8:30:15 
 *
 */
public interface IRouteHelper {

	public Route getRoute(Message message);
	
}
