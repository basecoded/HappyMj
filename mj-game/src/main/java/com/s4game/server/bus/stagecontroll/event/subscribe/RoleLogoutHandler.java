package com.s4game.server.bus.stagecontroll.event.subscribe;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.bus.stagecontroll.service.IStageControllService;
import com.s4game.server.share.event.EventConstants;

@Component
public class RoleLogoutHandler implements IEventHandler {
    
    @Resource
    private IStageControllService stageControllService;

    public void handle(Object source, Object data) {
        Object[] array = (Object[]) data;
        String roleId = (String) array[0];
        
        this.stageControllService.logout(roleId);
    }

    public String getEventType() {
        return EventConstants.ROLE_LOGOUT;
    }
}
