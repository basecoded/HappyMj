package com.s4game.server.stage.model.element.role;

import java.io.Serializable;

import com.s4game.server.bus.role.export.RoleWrapper;

public class RoleBusinessData implements Serializable {

    private static final long serialVersionUID = 1L;
    private RoleWrapper loginRole;

    private int roomId;
    private String roomLeaderId;
    private String[] roomMembers;

    public RoleBusinessData(RoleWrapper roleWrapper) {
        this.loginRole = roleWrapper;
    }

    public String getRoleId() {
        return this.loginRole.getId();
    }

    public String getName() {
        return this.loginRole.getName();
    }

    public String getJob() {
        return this.loginRole.getJob();
    }

    public Integer getSex() {
        return this.loginRole.getSex();
    }

    public String getFace() {
        return this.loginRole.getFace();
    }

    public Long getOfflineTime() {
        return this.loginRole.getOfflineTime();
    }

    public void setRoom(int roomId, String roomLeaderId, String[] roomMembers) {
        this.roomId = roomId;
        this.roomLeaderId = roomLeaderId;
        this.roomMembers = roomMembers;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomLeaderId() {
        return roomLeaderId;
    }

    public void setRoomLeaderId(String roomLeaderId) {
        this.roomLeaderId = roomLeaderId;
    }

    public String[] getRoomMembers() {
        return roomMembers;
    }

    public void setRoomMembers(String[] roomMembers) {
        this.roomMembers = roomMembers;
    }

}