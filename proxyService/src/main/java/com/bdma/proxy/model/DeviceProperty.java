package com.bdma.proxy.model;

public class DeviceProperty {

	private String propertyName;
	private String dataType;
	private boolean mandatory;
	
	public DeviceProperty(String propertyName, String dataType, boolean mandatory) {
		super();
		this.propertyName = propertyName;
		this.dataType = dataType;
		this.mandatory = mandatory;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	

}
