package com.s4game.server.bus.stagecontroll.position;

public class RoleLocation {
    
    private String mapId;
    private String stageId;
    private int x;
    private int y;

    public RoleLocation(String mapId, String stageId, int x, int y) {
        this.mapId = mapId;
        this.stageId = stageId;
        this.x = x;
        this.y = y;
    }

    public String getMapId() {
        return this.mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getStageId() {
        return this.stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Object[] enterPositionFormat() {
        return new Object[]{getMapId(), getX(), getY()};
    }
}