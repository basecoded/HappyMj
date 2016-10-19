package com.s4game.core.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月3日 下午10:03:06
 * 
 */
public class Message {

    /**
     * Message Body
     * 
     * 0: command Not Null 1: data Not Null 2: dest type 消息目标通道 3：from type
     * 消息来源通道 4: group 消息路由 5: sessionId 6: roleId 7: userId 8: stageId 9: token
     * 10: ip
     * 
     */

    public Message() {

    }

    public Message(Message message) {
        this.command = message.getCommand();
        this.data = message.getData();
        this.from = message.getFrom();
        this.dest = message.getDest();
        this.roleId = message.getRoleId();
        this.stageId = message.getStageId();
        this.token = message.getToken();
        this.ip = message.getIp();

        this.roleIds =  message.getRoleIds();
        this.route = message.getRoute();
        this.userId = message.getUserId();
        this.sessionId = message.getSessionId();
    }

    public Message(String command, Object data, FromType from, DestType dest, String roleId) {
        this(command, data, from, dest, roleId, null, null, null);
    }
    
    public Message(String command, Object data, FromType from, DestType dest, String userId, String sessionId) {
        this(command, data, from, dest, null, userId, sessionId, null);
    }
    
    public Message(String command, Object data, FromType from, DestType dest, String roleId, String userId, String sessionId, String ip) {
        this.command = command;
        this.data = data;
        this.from = from;
        this.dest = dest;
        this.roleId = roleId;
        this.userId = userId;
        this.sessionId = sessionId;
        this.ip = ip;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public FromType getFrom() {
        return from;
    }

    public void setFrom(FromType from) {
        this.from = from;
    }

    public DestType getDest() {
        return dest;
    }

    public void setDest(DestType dest) {
        this.dest = dest;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Object[] getToken() {
        return token;
    }

    public void setToken(Object[] token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String toData() {
        return JSONArray.toJSONString(getData());
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("command", command);
        json.put("from", from);
        json.put("dest", dest);
        json.put("roleId", roleId);
        json.put("data", data);
        json.put("route", route);
        json.put("userId", userId);
        json.put("roleIds", roleIds);
        json.put("sessionId", sessionId);
        json.put("token", token);
        json.put("ip", ip);
        
        return json.toJSONString();
    }
    
    /**
     * 协议号
     */
    private String command;

    /**
     * 消息路由
     */
    private int route;

    /**
     * 消息发出地
     */
    private FromType from;

    /**
     * 消息目的地
     */
    private DestType dest;

    /**
     * 消息内容
     */
    private Object data;

    /**
     * Channel Id
     */
    private String sessionId;

    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 账号Id
     */
    private String userId;

    /**
     * 广播角色ID
     */
    private String[] roleIds;
    
    /**
     * 场景Id
     */
    private String stageId;

    /**
     * 
     * 由 TokenManager 产生，用于调度验证
     */
    private Object[] token;

    /**
     * 客户端 IP
     */
    private String ip;

    public enum FromType {

        CLIENT(1),

        BUS(2),

        STAGE(3),

        STAGE_CONTROL(4),

        ;

        private final int value;

        private FromType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static FromType findType(int type) {
            switch (type) {
            case 1:
                return CLIENT;
            case 2:
                return BUS;
            case 3:
                return STAGE;
            case 4:
                return STAGE_CONTROL;
            default:
                throw new IllegalArgumentException("invalid frome type.");
            }

        }
    }

    public enum DestType {

        CLIENT(0),

        BUS(1),

        STAGE_CONTROL(2),

        STAGE(3),

        INOUT(4),

        BUS_INIT(5),

        PUBLIC(6),

        INNER_SYSTEM(7),

        ;

        private final int value;

        private DestType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static DestType findType(int type) {
            switch (type) {
            case 0:
                return CLIENT;
            case 1:
                return BUS;
            case 2:
                return STAGE_CONTROL;
            case 3:
                return STAGE;
            case 4:
                return INOUT;
            case 5:
                return BUS_INIT;
            case 6:
                return PUBLIC;
            case 7:
                return INNER_SYSTEM;
            default:
                throw new IllegalArgumentException("invalid frome type.");
            }

        }
    }

}
