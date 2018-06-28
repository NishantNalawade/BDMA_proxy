package com.bdma.proxy.model;

public class DeviceType {

	private String guid;
	private String extId;
	private String description;
	
	public DeviceType(String guid, String id, String description) {
		super();
		this.guid = guid;
		this.extId = id;
		this.description = description;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public String getExtId() {
		return extId;
	}
	public void setExtId(String extId) {
		this.extId = extId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
