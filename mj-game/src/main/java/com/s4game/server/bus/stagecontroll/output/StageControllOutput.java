package com.s4game.server.bus.stagecontroll.output;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class StageControllOutput {
    
	public static JSONObject login(String roleId) {
	    JSONObject json = new JSONObject();
	    json.put("roleId", roleId);
	    
	    return json;
	}

	public static Object[] applyChangeMap(String mapId, int x, int y) {
		return new Object[] { 1, mapId, x, y };
	}

}