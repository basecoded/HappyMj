package com.s4game.server.stage.event.publish;

import com.s4game.core.event.IEvent;

public class StageCreateEvent implements IEvent {
    
    private String stageId;
    
    private Object data;

    public StageCreateEvent(String mapId, String stageId) {
        this.stageId = stageId;
        this.data = new Object[] { mapId, stageId };
    }

    public String getType() {
        return "stage_create";
    }

    public Object getData() {
        return this.data;
    }

    public Object getSource() {
        return this.stageId;
    }
}