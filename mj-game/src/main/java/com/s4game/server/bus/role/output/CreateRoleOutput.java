package com.s4game.server.bus.role.output;

import com.s4game.server.bus.role.entity.UserRole;

public class CreateRoleOutput {
	public static Object nameIllegal() {
		return new Object[] { Integer.valueOf(0), RoleErrorCode.ROLE_NAME_NO_ALLOW };
	}

	public static Object nameRepeated() {
		return new Object[] { Integer.valueOf(0), RoleErrorCode.NAME_REPEATED };
	}

	public static Object roleExisted() {
		return new Object[] { Integer.valueOf(0), RoleErrorCode.ROLE_EXIST_SAME_USERID };
	}

	public static Object createRoleSuccess(UserRole userRole) {
		return new Object[] { Integer.valueOf(1),
				new Object[] { 
					userRole.getId(), 
					userRole.getName(), 
					userRole.getJob(), 
					userRole.getSex(), 
					userRole.getFace() 
				} 
		};
	}
}
