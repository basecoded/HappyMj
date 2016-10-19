package com.s4game.server.bus.role.event.publish;

import com.s4game.core.event.IEvent;
import com.s4game.server.bus.role.entity.UserRole;

public class RoleCreateEvent implements IEvent {
	
	private Object[] data;

	public RoleCreateEvent(UserRole userRole) {
		this.data = new Object[] { userRole.getUserId(), userRole.getId(), userRole.getName() };
	}

	public Object getData() {
		return this.data;
	}

	public String getType() {
		return "role_create";
	}

	@Override
	public Object getSource() {
		return this.data[1];
	}
}
