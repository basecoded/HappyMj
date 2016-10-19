package com.s4game.server.public_.swap;

import com.s4game.core.message.Message;
import com.s4game.server.executor.Route;
import com.s4game.server.message.IRouteHelper;
import com.s4game.server.public_.nodecontrol.NodeControlModuleInfo;
import com.s4game.server.share.moduleinit.CommandRegister;
import com.s4game.server.share.moduleinit.Group;

/**
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月9日 下午5:46:05 
 *
 */
public class PublicRouteHelper implements IRouteHelper {
	
	@Override
	public Route getRoute(Message message) {
		String command = message.getCommand();
		
		Group group = Group.find(message.getRoute());
		
		Route route = null;
		switch(group) {
		case LOGIN:
			route = new Route(Group.LOGIN.name);
			route.setData(message.getUserId());
			break;
		case STAGE_CONTROL:
		case STAGE:
		case PUBLIC:
		    if( CommandRegister.isModule(command, NodeControlModuleInfo.MODULE_NAME) ) {
		        route = new Route(NodeControlModuleInfo.MODULE_NAME);
		        route.setData(message.getRoleId());
		    } else {
		        route = new Route(Group.PUBLIC.name);
	            route.setData(CommandRegister.getCmdModule(command));
		    }
		    
			break;
			
		case SYSTEM:
			route = new Route(Group.SYSTEM.name);
			route.setData(message.getRoleId());
			break;
        default:
            
            break;
		}
		return route;
	}

}
