package com.s4game.server.stage.utils;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.s4game.server.stage.model.element.role.IRole;

public class RoleStageUtil {

    public static final String MAP_ID = "map_id";
    public static final String STAGE_ID = "stage_id";
    public static final String X = "x";
    public static final String Y = "y";

    public static String encodeOfflineCopy(IRole role) {
        int x = role.getPosition().getX();
        int y = role.getPosition().getY();
        String mapId = role.getStage().getMapId();
        String stageId = role.getStage().getId();
        
        JSONObject json = new JSONObject();
        json.put(MAP_ID, mapId);
        json.put(STAGE_ID, stageId);
        json.put(X, x);
        json.put(Y, y);
        
        return json.toString();
    }

    public static Object[] decodeOfflineCopy(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        JSONObject json = JSON.parseObject(data);

        Object[] array = new Object[4];
        array[0] = json.get(MAP_ID);
        array[1] = json.get(STAGE_ID);
        array[2] = json.get(X);
        array[3] = json.get(Y);
        
        return array;
    }
}