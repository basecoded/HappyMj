package com.s4game.server.stage.model.element.role;

import com.s4game.server.stage.model.core.element.IElementEventManager;
import com.s4game.server.stage.model.core.element.IStateManager;
import com.s4game.server.stage.model.core.element.impl.AbsElement;
import com.s4game.server.stage.model.core.stage.ElementType;
import com.s4game.server.stage.model.core.stage.IStage;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月21日 下午5:39:25
 * 
 */

public class Role extends AbsElement implements IRole {

    private IStateManager stateManager;
    
    private IElementEventManager eventManager;
    
    private RoleBusinessData roleBusinessData;

    public Role(String id, String name, String camp) {
        super(id, name, null, camp);
    }

    @Override
    public ElementType getElementType() {
        return ElementType.ROLE;
    }

    @Override
    public Object getMsgData() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void leaveStageHandle(IStage stage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enterStageHandle(IStage stage) {
        // TODO Auto-generated method stub

    }

    @Override
    public RoleBusinessData getRoleBusinessData() {
        return this.roleBusinessData;
    }

    public void setBusinessData(RoleBusinessData roleBusinessData) {
        this.roleBusinessData = roleBusinessData;
    }

    @Override
    public IStateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(IStateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public IElementEventManager getEventManager() {
        return eventManager;
    }

    public void setEventManager(IElementEventManager eventManager) {
        this.eventManager = eventManager;
    }
    
}
