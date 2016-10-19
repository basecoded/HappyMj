package com.s4game.server.bus.stagecontroll.position;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月20日 上午10:42:41
 *
 */

public abstract class AbsRolePosition {

    private String mapId;
    
    private int x;
    
    private int y;
    
    private int mapType;
    
    private String roleId;
    
    public AbsRolePosition(String roleId, String mapId, int mapType, int x, int y) {
        this.roleId = roleId;
        this.mapId = mapId;
        this.mapType = mapType;
        this.x = x;
        this.y = y;
    }

    public String getMapId() {
        return mapId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getRoleId() {
        return roleId;
    }
   
    public int getMapType() {
        return mapType;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 是否是副本地图
     * 
     * @return
     */
    public abstract boolean isCopyMap();
    
    public abstract String getStageId();
   
    public abstract Object[] enterPositionFormat();
    
}
