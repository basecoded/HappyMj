package com.s4game.server.bus.room.output;

import com.alibaba.fastjson.JSONObject;
import com.s4game.server.bus.room.entity.RoleRoom;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午1:34:42 
*
*/
public class RoomOutput {

    public static JSONObject room(RoleRoom room) {
        JSONObject json = new JSONObject();
        json.put("roomId", room.getNumber());
        json.put("round", room.getRound());
        json.put("serial", room.isSerial());
        json.put("win", room.isWin());
        
        return json;
    }
    
}
