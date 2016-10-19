package com.s4game.server.bus.stagecontroll.command;

public class StageControllCommands {
    
    /**
     * 玩家登录
     */
    public static final String LOGIN = "10003";
    
    /**
     * 请求切换场景（地图） 
     */
    public static final String APPLY_CHANGE_STAGE = "20000";
    
    /**
     * 切换场景（地图）
     */
    public static final String CHANGE_STAGE = "21000";
    
    public static final String INNER_LEAVE_STAGE = "S:LEAVE";
    public static final String INNER_ENTER_STAGE = "S:ENTER";
    
    /**
     * 服务器内部请求切换场景(地图)
     */
    public static final String INNER_APPLY_CHANGE_MAP = "SC:APPLY_CHANG_MAP";
    
    public static final String s50500 = "50500";
    public static final String s62001 = "62001";
    public static final String APPLY_CHANGE_LINE = "20004";
    public static final String GET_MAP_LINES = "20005";
    public static final String LOGOUT = "SC:LOGINOUT";
    
    public static final String INNER_APPLY_CHANGE_GUOZHAN = "SC:APPLY_CHANG_GUOZHAN";
    public static final String _APPLY_ENTER_SHENJIANG = "SC:APPLY_ZJZD";
    public static final String KUAFU_OFFLINE1 = "SC:KUAFU_OFFLINE_1";
    public static final String KUAFU_OFFLINE2 = "SC:KUAFU_OFFLINE_2";
    public static final String INNER_APPLY_CHANGE_CHALLENGE = "SC:APPLY_CHANG_CHALLENGE";
    
    /**
     * 请求进入副本
     */
    public static final String INNER_APPLY_CHANGE_COPY = "SC:APPLY_COPY";
    
    public static final String INNER_APPLY_LEAVE_COPY = "SC:APPLY_LEAVE_COPY";
    public static final String INNER_ENTER_KUAFU_COPY = "SC:ENTER_KUAFU_COPY";
}
