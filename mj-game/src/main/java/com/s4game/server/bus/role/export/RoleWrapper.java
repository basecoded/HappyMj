package com.s4game.server.bus.role.export;

import java.io.Serializable;
import java.sql.Timestamp;

import com.s4game.server.bus.role.entity.UserRole;

public class RoleWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UserRole userRole;

	public RoleWrapper(UserRole paramUserRole) {
		this.userRole = paramUserRole;
	}

	public Long getOnlineTime() {
		return this.userRole.getOnlineTime();
	}

	public Long getOfflineTime() {
		return this.userRole.getOfflineTime();
	}

	public String getId() {
		return this.userRole.getId();
	}

	public String getUserId() {
		return this.userRole.getUserId();
	}

	public String getName() {
		return this.userRole.getName();
	}

	public String getJob() {
		return this.userRole.getJob();
	}

	public Integer getSex() {
		return this.userRole.getSex();
	}

	public Long getExp() {
		return 0L;
	}


	public String getFace() {
		return this.userRole.getFace();
	}

	public Integer getCard() {
		return this.userRole.getCard();
	}

	public Timestamp getCreateTime() {
		return this.userRole.getCreateTime();
	}

	public String getServerId() {
		return this.userRole.getServerId();
	}

	public Long getUpgradeTime() {
		return this.userRole.getUpgradeTime();
	}
}