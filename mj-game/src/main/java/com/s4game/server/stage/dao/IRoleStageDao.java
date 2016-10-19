package com.s4game.server.stage.dao;

import com.s4game.core.data.accessor.dao.ICacheInitDaoOperation;
import com.s4game.server.stage.entity.RoleStage;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月17日 下午3:27:34
 *
 */

public interface IRoleStageDao extends ICacheInitDaoOperation<RoleStage> {

    public void createRoleStage(RoleStage roleStage, String roleId);
    
}
