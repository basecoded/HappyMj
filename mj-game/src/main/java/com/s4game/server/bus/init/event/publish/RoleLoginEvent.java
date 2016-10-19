package com.s4game.server.bus.init.event.publish;

import com.s4game.core.event.IEvent;
import com.s4game.server.share.event.EventConstants;

public class RoleLoginEvent implements IEvent {
    
    private Object[] data = null;

    public RoleLoginEvent(String roleId, String ip) {
        this.data = new Object[] { roleId, ip };
    }

    public String getType() {
        return EventConstants.ROLE_LOGIN;
    }

    public Object getData() {
        return this.data;
    }

    public Object getSource() {
        return this.data[0];
    }
}
