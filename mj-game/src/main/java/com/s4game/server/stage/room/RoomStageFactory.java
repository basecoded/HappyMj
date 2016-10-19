package com.s4game.server.stage.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.server.public_.room.entity.Room;
import com.s4game.server.public_.room.model.RoomBusinessData;
import com.s4game.server.public_.room.service.IRoomService;

@Component
public class RoomStageFactory {
    
    @Autowired
    private IRoomService roomService;

    public RoomStage create(String stageId, String mapId) {
        RoomStage stage = new RoomStage(stageId, mapId);
        
        Room room = roomService.loadById(Long.parseLong(stageId));
        
        // create business data
        RoomBusinessData data = new RoomBusinessData();
        data.setRoomId(room.getId());
        data.setStatus(room.getStatus());
        
        stage.setRoomBusinessData(data);
        
        return stage;
    }
    
}
