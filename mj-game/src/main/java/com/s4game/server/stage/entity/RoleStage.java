package com.s4game.server.stage.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;

public class RoleStage extends AbsVersion implements Serializable, IEntity {
    
    private static final long serialVersionUID = 1L;
    
    private String userRoleId;
    private String mapId;
    private Integer mapX;
    private Integer mapY;
    private Integer hp;
    private Integer mp;
    private Integer maxHp;
    private Integer maxMp;
    private String buff;
    private String props;
    private Integer state;
    private String mapNode;
    private Integer tiLi;
    private Integer lineNo;
    private String pkInfo;
    private Integer shanbiVal;
    private String meirenInfo;
    private String zuoqiInfo;
    private Integer freeFlyCount;
    private Long flyCountRefreshTime;
    private String copyInfo;
    private Timestamp logUpdateTime;

    public String getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(String paramString) {
        this.userRoleId = paramString;
    }

    public String getMapId() {
        return this.mapId;
    }

    public void setMapId(String paramString) {
        this.mapId = paramString;
    }

    public Integer getMapX() {
        return this.mapX;
    }

    public void setMapX(Integer paramInteger) {
        this.mapX = paramInteger;
    }

    public Integer getMapY() {
        return this.mapY;
    }

    public void setMapY(Integer paramInteger) {
        this.mapY = paramInteger;
    }

    public Integer getHp() {
        return this.hp;
    }

    public void setHp(Integer paramInteger) {
        this.hp = paramInteger;
    }

    public Integer getMp() {
        return this.mp;
    }

    public void setMp(Integer paramInteger) {
        this.mp = paramInteger;
    }

    public Integer getMaxHp() {
        return this.maxHp;
    }

    public void setMaxHp(Integer paramInteger) {
        this.maxHp = paramInteger;
    }

    public Integer getMaxMp() {
        return this.maxMp;
    }

    public void setMaxMp(Integer paramInteger) {
        this.maxMp = paramInteger;
    }

    public String getBuff() {
        return this.buff;
    }

    public void setBuff(String paramString) {
        this.buff = paramString;
    }

    public String getProps() {
        return this.props;
    }

    public void setProps(String paramString) {
        this.props = paramString;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer paramInteger) {
        this.state = paramInteger;
    }

    public String getMapNode() {
        return this.mapNode;
    }

    public void setMapNode(String paramString) {
        this.mapNode = paramString;
    }

    public Integer getTiLi() {
        return this.tiLi;
    }

    public void setTiLi(Integer paramInteger) {
        this.tiLi = paramInteger;
    }

    public Integer getLineNo() {
        return this.lineNo;
    }

    public void setLineNo(Integer paramInteger) {
        this.lineNo = paramInteger;
    }

    public String getPkInfo() {
        return this.pkInfo;
    }

    public void setPkInfo(String paramString) {
        this.pkInfo = paramString;
    }

    public Integer getShanbiVal() {
        return this.shanbiVal;
    }

    public void setShanbiVal(Integer paramInteger) {
        this.shanbiVal = paramInteger;
    }

    public String getMeirenInfo() {
        return this.meirenInfo;
    }

    public void setMeirenInfo(String paramString) {
        this.meirenInfo = paramString;
    }

    public Integer getFreeFlyCount() {
        return this.freeFlyCount;
    }

    public void setFreeFlyCount(Integer paramInteger) {
        this.freeFlyCount = paramInteger;
    }

    public Long getFlyCountRefreshTime() {
        return this.flyCountRefreshTime;
    }

    public void setFlyCountRefreshTime(Long paramLong) {
        this.flyCountRefreshTime = paramLong;
    }

    public String getZuoqiInfo() {
        return this.zuoqiInfo;
    }

    public void setZuoqiInfo(String paramString) {
        this.zuoqiInfo = paramString;
    }

    public String getCopyInfo() {
        return this.copyInfo;
    }

    public void setCopyInfo(String copyInfo) {
        this.copyInfo = copyInfo;
    }

    public Timestamp getLogUpdateTime() {
        return this.logUpdateTime;
    }

    public void setLogUpdateTime(Timestamp logUpdateTime) {
        this.logUpdateTime = logUpdateTime;
    }

    public String getPirmaryKeyName() {
        return "userRoleId";
    }

    public String getPrimaryKeyValue() {
        return getUserRoleId();
    }

    public RoleStage copy() {
        RoleStage localRoleStage = new RoleStage();
        localRoleStage.setUserRoleId(getUserRoleId());
        localRoleStage.setMapId(getMapId());
        localRoleStage.setMapX(getMapX());
        localRoleStage.setMapY(getMapY());
        localRoleStage.setHp(getHp());
        localRoleStage.setMp(getMp());
        localRoleStage.setMaxHp(getMaxHp());
        localRoleStage.setMaxMp(getMaxMp());
        localRoleStage.setBuff(getBuff());
        localRoleStage.setProps(getProps());
        localRoleStage.setState(getState());
        localRoleStage.setMapNode(getMapNode());
        localRoleStage.setTiLi(getTiLi());
        localRoleStage.setLineNo(getLineNo());
        localRoleStage.setPkInfo(getPkInfo());
        localRoleStage.setShanbiVal(getShanbiVal());
        localRoleStage.setMeirenInfo(getMeirenInfo());
        localRoleStage.setFreeFlyCount(getFreeFlyCount());
        localRoleStage.setFlyCountRefreshTime(getFlyCountRefreshTime());
        localRoleStage.setZuoqiInfo(getZuoqiInfo());
        localRoleStage.setCopyInfo(getCopyInfo());
        localRoleStage.setLogUpdateTime(getLogUpdateTime());
        return localRoleStage;
    }
}