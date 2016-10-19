package com.s4game.core.data;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月20日 下午6:19:15
 *
 */

public abstract class AbsVersion implements IVersion {

	private long version = System.currentTimeMillis();
	
	@Override
	public String getVersion() {
		return String.valueOf(version);
	}

	@Override
	public void updateVersion() {
		this.version = System.currentTimeMillis();
	}

}
