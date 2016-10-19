package com.s4game.server.login.output;

import com.alibaba.fastjson.JSONObject;
import com.s4game.server.bus.role.entity.UserRole;

public class InOutput {
	public static Object accountForbitten() {
		return new Object[] { Integer.valueOf(0), LoginErrorCode.CHECK_FAILED };
	}

	public static Object signError() {
		return new Object[] { Integer.valueOf(0), LoginErrorCode.IS_FENGHAO };
	}

	public static JSONObject success(UserRole userRole) {
		JSONObject json = new JSONObject();
		
		json.put("userId", userRole.getUserId());
		json.put("roleId", userRole.getId());
		json.put("name", userRole.getName());
		json.put("face", userRole.getFace());
		json.put("card", userRole.getCard());
		
		return json;
	}
}
