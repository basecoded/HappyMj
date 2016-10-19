package com.s4game.server.bus.stagecontroll.position;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月20日 上午10:59:20
 *
 */

public class StageCopyPosition extends AbsRolePosition {

    private String stageId;
    
    private boolean stageExist;
    
    private Object[] additionalData;
    
    public StageCopyPosition(String roleId, String mapId, int mapType, int x, int y, Object[] additionalData) {
        this(roleId, mapId, mapType, x, y, mapId, additionalData);
    }
    
    public StageCopyPosition(String roleId, String mapId, int mapType, int x, int y, String stageId, Object[] additionalData) {
        super(roleId, mapId, mapType, x, y);
        this.stageId = stageId;
        this.additionalData = additionalData;
    }

    @Override
    public String getStageId() {
        return stageId;
    }

    public boolean isStageExist() {
        return stageExist;
    }

    public void setStageExist(boolean stageExist) {
        this.stageExist = stageExist;
    }

    public Object[] getAdditionalData() {
        return additionalData;
    }

    @Override
    public Object[] enterPositionFormat() {
        return new Object[]{getStageId(), getMapId(), getX(), getY()};
    }

    @Override
    public boolean isCopyMap() {
        return true;
    }


}
