package com.s4game.server.public_.room.output;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.s4game.server.public_.room.entity.Room;
import com.s4game.server.public_.room.model.RoomMemberData;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午1:34:42 
*
*/
public class RoomOutput {

    public static JSONObject room(Room room) {
        JSONObject json = new JSONObject();
        json.put("roomId", room.getId());
        json.put("creator", room.getUserRoleId());
        json.put("status", room.getStatus());
        json.put("maxRound", room.getMaxRound());
        json.put("curRound", room.getCurRound());
        json.put("serial", room.isSerial());
        json.put("win", room.isWin());
        
        return json;
    }
    
    public static JSONObject join(RoomMemberData member) {
        JSONObject json = new JSONObject();
        json.put("roleId", member.getRoleId());
        json.put("name", member.getName());
        json.put("face", member.getFace());
        json.put("ready", member.isReady());
        
        return json;
    }
    
    public static JSONObject memberData(RoomMemberData member) {
        return (JSONObject) JSON.toJSON(member);
    }
}
