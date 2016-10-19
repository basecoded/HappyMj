package com.s4game.server.stage.model.element.role;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.stage.model.core.element.impl.state.StateManager;
import com.s4game.server.stage.model.core.stage.IStage;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月21日 下午5:25:53
 *
 */
@Component
public class RoleFactory {

    @Resource
    private IUserRoleService userRoleService;
    
    public IRole create(String roleId, IStage stage) {
        RoleWrapper roleWrapper = userRoleService.getRole(roleId);
        
        RoleBusinessData businessData = new RoleBusinessData(roleWrapper);
        
        Role role = new Role(roleWrapper.getId(), roleWrapper.getName(), "1");
        role.setBusinessData(businessData);
        role.setStateManager(new StateManager());
        role.setEventManager(new RoleEventManager(role));
        
        
        return role;
    }
    
}
