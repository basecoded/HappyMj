package com.s4game.server.public_.room.entity;

import java.sql.Timestamp;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月17日 下午12:12:02
 *
 */
public class Room extends AbsVersion implements IEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 5764691377281646396L;

    private long id;

    private String userRoleId;

    /**
     * 房间状态
     */
    private int status;

    /**
     * 总回合数
     */
    private int maxRound;

    /**
     * 当前回合 <= round
     */
    private int curRound;
    
    /**
     * 是否连中
     */
    private boolean serial;

    /**
     * 是否强制胡牌
     */
    private boolean win;

    private Timestamp createTime;
    
    private Timestamp logUpdateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public void setMaxRound(int maxRound) {
        this.maxRound = maxRound;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLogUpdateTime() {
        return logUpdateTime;
    }

    public void setLogUpdateTime(Timestamp logUpdateTime) {
        this.logUpdateTime = logUpdateTime;
    }

    @Override
    public String getPirmaryKeyName() {
        return "id";
    }

    @Override
    public Object getPrimaryKeyValue() {
        return getId();
    }

    @Override
    public IEntity copy() {
        Room room = new Room();
        room.setId(getId());
        room.setUserRoleId(getUserRoleId());
        room.setStatus(getStatus());
        room.setMaxRound(getMaxRound());
        room.setCurRound(getCurRound());
        room.setSerial(isSerial());
        room.setWin(isWin());
        room.setStatus(getStatus());
        
        room.setCreateTime(getCreateTime());
        room.setLogUpdateTime(getLogUpdateTime());
        
        return room;
    }

}
