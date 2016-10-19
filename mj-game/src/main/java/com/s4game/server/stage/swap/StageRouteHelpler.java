package com.s4game.server.stage.swap;

import com.s4game.core.message.Message;
import com.s4game.server.executor.Route;
import com.s4game.server.message.IRouteHelper;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月21日 下午3:10:31
 *
 */

public class StageRouteHelpler implements IRouteHelper {

    @Override
    public Route getRoute(Message message) {
        Route route = new Route("stage");
        route.setData(message.getStageId());
        
        
//        if( message.getRoute() == Group.SYSTEM ) {
//            route = new Route("system");
//            route.setData(message.getStageId());
//        } else {
//            route = new Route("stage");
//            route.setData(message.getStageId());
//        }
        return route;
    }

}
