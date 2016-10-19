package com.s4game.server.bus.id.entity;

import java.io.Serializable;

import com.s4game.core.data.IEntity;

public class IdGen implements Serializable, IEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id = "";
	private String moduleName = "";
	private Long value;
	private String prefix;
	private Integer version;

	public String getId() {
		return this.id;
	}

	public void setId(String paramString) {
		this.id = paramString;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String paramString) {
		this.moduleName = paramString;
	}

	public Long getValue() {
		return this.value;
	}

	public void setValue(Long paramLong) {
		this.value = paramLong;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String paramString) {
		this.prefix = paramString;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer paramInteger) {
		this.version = paramInteger;
	}

	public String getPirmaryKeyName() {
		return "id";
	}

	public String getPrimaryKeyValue() {
		return getId();
	}

	public IdGen copy() {
		IdGen idGen = new IdGen();
		idGen.setId(getId());
		idGen.setModuleName(getModuleName());
		idGen.setValue(getValue());
		idGen.setPrefix(getPrefix());
		idGen.setVersion(getVersion());
		
		return idGen;
	}
}