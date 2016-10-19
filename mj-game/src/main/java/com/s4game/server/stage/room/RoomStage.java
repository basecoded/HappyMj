package com.s4game.server.stage.room;

import com.s4game.server.public_.room.model.RoomBusinessData;
import com.s4game.server.stage.model.core.stage.AbsStage;
import com.s4game.server.stage.model.core.stage.IStageElement;

public class RoomStage extends AbsStage {

    private String stageId;
    
    private String mapId;
    
    private RoomBusinessData roomBusinessData;
    
    public RoomStage(String stageId, String mapId) {
        this.stageId = stageId;
        this.mapId = mapId;
    }
    
    @Override
    public String getId() {
        return stageId;
    }
    
    @Override
    public String getMapId() {
        return mapId;
    }

    @Override
    public boolean isCopy() {
        return true;
    }

    public RoomBusinessData getRoomBusinessData() {
        return roomBusinessData;
    }

    public void setRoomBusinessData(RoomBusinessData roomBusinessData) {
        this.roomBusinessData = roomBusinessData;
    }

    @Override
    protected void addHandler(IStageElement stageElement) {
        stageElement.enterStageHandle(this);
    }

    @Override
    protected void deleteHandler(IStageElement stageElement) {
        stageElement.leaveStageHandle(this);
    }

}
