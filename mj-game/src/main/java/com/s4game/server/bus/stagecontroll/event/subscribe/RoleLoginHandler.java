package com.s4game.server.bus.stagecontroll.event.subscribe;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.bus.stagecontroll.command.StageControllCommands;
import com.s4game.server.bus.swap.BusMsgSender;
import com.s4game.server.share.event.EventConstants;

@Component
public class RoleLoginHandler implements IEventHandler {
    
    @Resource
    private BusMsgSender busMsgSender;
    
    public void handle(Object paramObject1, Object paramObject2) {
        Object[] arrayOfObject = (Object[]) paramObject2;
        busMsgSender.send2BusInner(StageControllCommands.LOGIN, (String) arrayOfObject[0], arrayOfObject);
    }

    public String getEventType() {
        return EventConstants.ROLE_LOGIN;
    }
}