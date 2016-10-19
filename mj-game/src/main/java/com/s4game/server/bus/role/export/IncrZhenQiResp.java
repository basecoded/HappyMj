package com.s4game.server.bus.role.export;

public class IncrZhenQiResp {
	
	private Object[] result;
	private Integer realIncr;

	public IncrZhenQiResp(Object[] paramArrayOfObject, Integer paramInteger) {
		this.result = paramArrayOfObject;
		this.realIncr = paramInteger;
	}

	public Object[] getResult() {
		return this.result;
	}

	public Integer getRealIncr() {
		return this.realIncr;
	}

	public Integer getCurZhenQi() {
		return (Integer) this.result[0];
	}
}