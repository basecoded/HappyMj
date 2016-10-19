package com.s4game.server.stage.service;

public interface IRoleBehaviourService {

    /**
     * 获取场景内玩家ID
     * 
     * @param stageId
     * @return
     */
    String[] getStageRoleId(String stageId);
    
}
