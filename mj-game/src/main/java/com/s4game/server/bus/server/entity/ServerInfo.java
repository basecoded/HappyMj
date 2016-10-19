package com.s4game.server.bus.server.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月23日 下午9:09:58
 * 
 */
public class ServerInfo extends AbsVersion implements Serializable, IEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Timestamp startTime;
	private String prefixId;
	private Timestamp hefuTime;
	private Timestamp logUpdateTime;

	public String getId() {
		return this.id;
	}

	public void setId(String paramString) {
		this.id = paramString;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp paramTimestamp) {
		this.startTime = paramTimestamp;
	}

	public String getPrefixId() {
		return this.prefixId;
	}

	public void setPrefixId(String paramString) {
		this.prefixId = paramString;
	}

	public Timestamp getHefuTime() {
		return this.hefuTime;
	}

	public void setHefuTime(Timestamp paramTimestamp) {
		this.hefuTime = paramTimestamp;
	}

	public Timestamp getLogUpdateTime() {
		return this.logUpdateTime;
	}

	public void setLogUpdateTime(Timestamp paramTimestamp) {
		this.logUpdateTime = paramTimestamp;
	}

	public String getPirmaryKeyName() {
		return "id";
	}

	public String getPrimaryKeyValue() {
		return getId();
	}

	public ServerInfo copy() {
		ServerInfo serverInfo = new ServerInfo();
		serverInfo.setId(getId());
		serverInfo.setStartTime(getStartTime());
		serverInfo.setPrefixId(getPrefixId());
		serverInfo.setHefuTime(getHefuTime());
		serverInfo.setLogUpdateTime(getLogUpdateTime());
		
		return serverInfo;
	}
}
