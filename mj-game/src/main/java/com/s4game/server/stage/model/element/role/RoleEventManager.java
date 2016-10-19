package com.s4game.server.stage.model.element.role;

import org.springframework.stereotype.Component;

import com.s4game.server.stage.model.core.element.IElementEventManager;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月20日 下午7:07:19
 * 
 */
@Component
public class RoleEventManager implements IElementEventManager {

    private IRole role;
    
    public RoleEventManager() {
        
    }
    
    public RoleEventManager(IRole role) {
        this.role = role;
    }

    @Override
    public void fireLoginEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireLogoutEvent() {
        role.getId();
    }

    @Override
    public void fireMoveEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireStateChange() {
        // TODO Auto-generated method stub

    }

    @Override
    public void leaveFightStateEvent() {
        // TODO Auto-generated method stub
        
    }

}
