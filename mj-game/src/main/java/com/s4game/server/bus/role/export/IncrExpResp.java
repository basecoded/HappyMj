package com.s4game.server.bus.role.export;

public class IncrExpResp {

	private int upgradeCount;
	private Long realIncr;

	public IncrExpResp() {
	}

	public IncrExpResp(int paramInt, Long paramLong) {
		this.upgradeCount = paramInt;
		this.realIncr = paramLong;
	}

	public int getUpgradeCount() {
		return this.upgradeCount;
	}

	public Long getRealIncr() {
		return this.realIncr;
	}
}