package com.s4game.server.stage.event.publish;

import com.s4game.core.event.IEvent;

public class RoleLeaveStageEvent implements IEvent {
    
    Object[] data;

    public RoleLeaveStageEvent(String roleId, String stageId) {
        this.data = new Object[] { roleId, stageId };
    }

    public String getType() {
        return "role_leave_stage";
    }

    public Object getData() {
        return this.data;
    }

    public Object getSource() {
        return this.data[0];
    }
}