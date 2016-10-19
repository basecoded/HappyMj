package com.s4game.server.bus.room.entity;

import java.sql.Timestamp;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月17日 下午12:12:02
 *
 */
public class RoleRoom extends AbsVersion implements IEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 5764691377281646396L;

    private String userRoleId;

    /**
     * 房间号
     */
    private int number;

    /**
     * 总回合数
     */
    private int round;

    /**
     * 是否连中
     */
    private boolean serial;

    /**
     * 是否强制胡牌
     */
    private boolean win;

    /**
     * 房间状态
     */
    private int status;
    
    /**
     * 当前回合 <= round
     */
    private int curRound;
    
    private Timestamp logUpdateTime;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public boolean isSerial() {
        return serial;
    }

    public void setSerial(boolean serial) {
        this.serial = serial;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCurRound() {
        return curRound;
    }

    public void setCurRound(int curRound) {
        this.curRound = curRound;
    }

    public Timestamp getLogUpdateTime() {
        return logUpdateTime;
    }

    public void setLogUpdateTime(Timestamp logUpdateTime) {
        this.logUpdateTime = logUpdateTime;
    }

    @Override
    public String getPirmaryKeyName() {
        return "userRoleId";
    }

    @Override
    public Object getPrimaryKeyValue() {
        return getUserRoleId();
    }

    @Override
    public IEntity copy() {
        RoleRoom room = new RoleRoom();
        room.setUserRoleId(getUserRoleId());
        room.setNumber(getNumber());
        room.setRound(getRound());
        room.setSerial(isSerial());
        room.setWin(isWin());
        room.setStatus(getStatus());
        room.setCurRound(getCurRound());

        return room;
    }

}
