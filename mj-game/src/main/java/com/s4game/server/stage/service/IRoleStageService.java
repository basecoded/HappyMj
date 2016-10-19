package com.s4game.server.stage.service;

import com.s4game.server.stage.entity.RoleStage;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月17日 下午3:52:19
 * 
 */

public interface IRoleStageService {

    public void createRoleStage(String roleId);

    public RoleStage loadRoleStage(String roleId);

    public void syncRoleStageData(String roleId, String stageId);
}
