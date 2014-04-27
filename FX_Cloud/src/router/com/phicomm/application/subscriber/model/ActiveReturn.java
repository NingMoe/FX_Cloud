package com.phicomm.application.subscriber.model;

public class ActiveReturn {

	 
	public int retState;
	public int ifSuport;
	public int activeSta;
	public String activeCode;
	
	
	
	public String Return() {
		return "{\"retLogin\":{\"retState\":\""+this.retState+"\",\"ifSuport\":\""+this.ifSuport+"\",\"activeSta\":\""+this.activeSta+"\",\"activeCode\":\""+this.activeCode+"\"}}";
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



	public int getActiveSta() {
		return activeSta;
	}



	public void setActiveSta(int activeSta) {
		this.activeSta = activeSta;
	}



	public String getActiveCode() {
		return activeCode;
	}



	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	
}
