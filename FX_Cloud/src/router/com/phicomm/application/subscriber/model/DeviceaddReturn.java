package com.phicomm.application.subscriber.model;

import javax.annotation.Resource;

import com.phicomm.application.subscriber.service.IDeviceService;


public class DeviceaddReturn {
	private IDeviceService deviceService;
	
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}


	@Resource
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	 
	public int retState;
	public int ifSuport;
	public int retAdd; 
    public int retReason;
	

	public String Return() {
		return "{\"retAdd\":{\"retState\":\""+this.retState+"\",\"ifSuport\":\""+this.ifSuport+"\",\"retAdd\":\""+this.retAdd+"\",\"retReason\":\""+this.retReason+"\"}}";
			
	}



	public int getRetState() {
		return retState;
	}



	public void setRetState(int retState) {
		this.retState = retState;
	}



	public int getIfSuport() {
		return ifSuport;
	}



	public void setIfSuport(int ifSuport) {
		this.ifSuport = ifSuport;
	}


	public int getRetAdd() {
		return retAdd;
	}


	public void setRetAdd(int retAdd) {
		this.retAdd = retAdd;
	}


	public int getRetReason() {
		return retReason;
	}


	public void setRetReason(int retReason) {
		this.retReason = retReason;
	}




	
	
	
}
