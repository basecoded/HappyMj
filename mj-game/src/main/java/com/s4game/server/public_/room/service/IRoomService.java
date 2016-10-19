package com.s4game.server.public_.room.service;

import java.util.List;

import com.s4game.server.public_.room.entity.Room;

public interface IRoomService {

    List<Room> loadAll();

    Room loadById(long roomId);
    
    Room loadByRoleId(String roleId);
    
    /**
     * 创建房间
     * 
     * @param roleId
     * @param round
     * @param serial
     * @param win
     * @return
     */
    Room createRoom(String roleId, int round, boolean serial, boolean win);
    
    /**
     * 创建房间副本
     * 
     * @param roleId
     * @param stageId
     * @param mapId
     */
    void createRoomCopy(String roleId, String stageId, String mapId, Object[] additionalData);

    /**
     * 进入房间
     * 
     * @param roleId
     * @param roomId
     * @param msgQueue
     */
    void enterRoom(String roleId, String roomId);
    
    /**
     * 进入场景处理数据
     * 
     * @param stageId
     * @param roleId
     */
    void roleEnterStageHandle(String stageId, String roleId);
}
