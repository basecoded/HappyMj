package com.s4game.server.stage.event.publish;

import com.s4game.core.event.IEvent;
import com.s4game.server.share.event.EventConstants;

public class RoleEnterStageEvent implements IEvent {
    
    private Object[] data;
    
    private String roleId;

    public RoleEnterStageEvent(String stageId, String mapId, String roleId) {
        this.data = new Object[] { stageId, mapId, roleId };
        this.roleId = roleId;
    }

    public String getType() {
        return EventConstants.ROLE_ENTER_STAGE;
    }

    public Object getData() {
        return this.data;
    }

    public Object getSource() {
        return this.roleId;
    }
}