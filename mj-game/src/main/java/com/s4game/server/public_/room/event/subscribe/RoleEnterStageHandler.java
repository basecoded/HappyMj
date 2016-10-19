package com.s4game.server.public_.room.event.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.public_.room.service.IRoomService;
import com.s4game.server.share.event.EventConstants;

@Component
public class RoleEnterStageHandler implements IEventHandler {

    @Autowired
    private IRoomService roomService;
    
    @Override
    public void handle(Object source, Object data) {
        Object[] array = (Object[]) data;
        String stageId = (String)array[0];
        String roleId = (String)array[2];
        
        roomService.roleEnterStageHandle(stageId, roleId);
    }

    @Override
    public String getEventType() {
        return EventConstants.ROLE_ENTER_STAGE;
    }

}
