package com.bdma.proxy.model;

import java.io.Serializable;

public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7662229870688030393L;
	private String deviceTypeGuid;
	private String deviceId;
	private String deviceMessageTypeGuid = "";
	private String statusCode = "success";
	private String onboardingOrigin = "";
	private Object data;
	public String getDeviceTypeGuid() {
		return deviceTypeGuid;
	}
	public void setDeviceTypeGuid(String deviceTypeGuid) {
		this.deviceTypeGuid = deviceTypeGuid;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceMessageTypeGuid() {
		return deviceMessageTypeGuid;
	}
	public void setDeviceMessageTypeGuid(String deviceMessageTypeGuid) {
		this.deviceMessageTypeGuid = deviceMessageTypeGuid;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getOnboardingOrigin() {
		return onboardingOrigin;
	}
	public void setOnboardingOrigin(String onboardingOrigin) {
		this.onboardingOrigin = onboardingOrigin;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	




}
