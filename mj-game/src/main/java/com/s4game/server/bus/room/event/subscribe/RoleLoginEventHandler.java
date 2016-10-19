package com.s4game.server.bus.room.event.subscribe;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.share.event.EventConstants;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午1:05:57 
*
*/
@Component
public class RoleLoginEventHandler implements IEventHandler{
    
    @Override
    public void handle(Object source, Object data) {
        //Object[] dd = (Object[])data;
        //String roleId = (String)dd[0];

    }

    @Override
    public String getEventType() {
        return EventConstants.ROLE_LOGIN;
    }

}
