package com.s4game.server.bus.stagecontroll;

import com.s4game.server.bus.stagecontroll.position.AbsRolePosition;
import com.s4game.server.bus.stagecontroll.position.RoleNormalPosition;

public class RoleState {

    private String roleId;

    private AbsRolePosition curPosition;
    
    private AbsRolePosition readyForPosition;
    
    private RoleNormalPosition roleNormalPosition;

    public RoleState(String roleId) {
        this.roleId = roleId;
    }

    public AbsRolePosition getCurPosition() {
        return this.curPosition;
    }

    public void setCurPosition(AbsRolePosition curPosition) {
        this.curPosition = curPosition;
    }

    public AbsRolePosition getReadyToPosition() {
        return this.readyForPosition;
    }

    public void setReadyForPosition(AbsRolePosition readyForPosition) {
        this.readyForPosition = readyForPosition;
    }

    public void setOfflineSavePosition(RoleNormalPosition roleNormalPosition) {
        this.roleNormalPosition = roleNormalPosition;
    }

    public RoleNormalPosition getOfflineSavePosition() {
        return this.roleNormalPosition;
    }

    public String getRoleId() {
        return roleId;
    }

}