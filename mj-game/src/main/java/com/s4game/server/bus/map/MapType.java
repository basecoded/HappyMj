package com.s4game.server.bus.map;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月17日 上午10:15:50
 * 
 */

public class MapType {

    /**
     * 空闲状态
     */
    public static final int IDLE = 1;

    /**
     * 在房间中
     */
    public static final int ROOM = 2;

    public static boolean usedForOfflineSave(int type) {
        return type == ROOM;
    }
    
    public static boolean isOfflineSaveCopy(int type) {
        return type == ROOM;
    }

}
