package com.s4game.server.public_.nodecontrol.event.publish;

import com.s4game.core.event.IEvent;

public class RoleLogoutEvent implements IEvent {
    private Object[] data = null;

    public RoleLogoutEvent(String roleId, String paramString2, Long onlineTime) {
        this.data = new Object[] { roleId, paramString2, onlineTime,
                Long.valueOf(System.currentTimeMillis()) };
    }

    public String getType() {
        return "role_logout";
    }

    public Object getData() {
        return this.data;
    }

    @Override
    public Object getSource() {
        return this.data[0];
    }
}
