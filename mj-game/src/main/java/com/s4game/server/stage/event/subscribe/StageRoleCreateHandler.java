package com.s4game.server.stage.event.subscribe;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.share.event.EventConstants;
import com.s4game.server.stage.service.IRoleStageService;

@Component
public class StageRoleCreateHandler implements IEventHandler {
    
    @Resource
    private IRoleStageService roleService;

    public void handle(Object source, Object data) {
        Object[] array = (Object[]) data;
        
        String roleId = (String) array[1];

        this.roleService.createRoleStage(roleId);
    }

    public String getEventType() {
        return EventConstants.ROLE_CREATE;
    }
}