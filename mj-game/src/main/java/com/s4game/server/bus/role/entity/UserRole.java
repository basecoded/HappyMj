package com.s4game.server.bus.role.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;

public class UserRole extends AbsVersion implements Serializable, IEntity {

    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String name;
    private String job;
    private Integer sex;
    private Integer card;
    private String face;

    private Timestamp createTime;
    private Long onlineTime;
    private Long offlineTime;
    private String serverId;
    private Long upgradeTime;
    private String platform;
    private Integer loginCount;
    private Timestamp logUpdateTime;

    public Timestamp getLogUpdateTime() {
        return this.logUpdateTime;
    }

    public void setLogUpdateTime(Timestamp logUpdateTime) {
        this.logUpdateTime = logUpdateTime;
    }

    public Integer getLoginCount() {
        return this.loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Long getOnlineTime() {
        return this.onlineTime;
    }

    public void setOnlineTime(Long onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Long getOfflineTime() {
        return this.offlineTime;
    }

    public void setOfflineTime(Long offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getFace() {
        return this.face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public Long getUpgradeTime() {
        return this.upgradeTime;
    }

    public void setUpgradeTime(Long setUpgradeTime) {
        this.upgradeTime = setUpgradeTime;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPirmaryKeyName() {
        return "id";
    }

    public String getPrimaryKeyValue() {
        return getId();
    }

    public UserRole copy() {
        UserRole userRole = new UserRole();

        userRole.setId(getId());
        userRole.setUserId(getUserId());
        userRole.setName(getName());
        userRole.setJob(getJob());
        userRole.setSex(getSex());
        userRole.setFace(getFace());
        userRole.setCard(getCard());
        
        userRole.setCreateTime(getCreateTime());
        userRole.setOnlineTime(getOnlineTime());
        userRole.setOfflineTime(getOfflineTime());
        userRole.setServerId(getServerId());
        userRole.setPlatform(getPlatform());
        userRole.setUpgradeTime(getUpgradeTime());
        userRole.setLoginCount(getLoginCount());
        userRole.setLogUpdateTime(getLogUpdateTime());
        return userRole;
    }
}