package com.phicomm.application.subscriber.model;


public class AppOutletReturn {
	private String retState;
	private String retValid;
	private String retSup;

	
	public String getRetState() {
		return retState;
	}
	public void setRetState(String retState) {
		this.retState = retState;
	}
	public String getRetValid() {
		return retValid;
	}
	public void setRetValid(String retValid) {
		this.retValid = retValid;
	}
	public String getRetSup() {
		return retSup;
	}
	public void setRetSup(String retSup) {
		this.retSup = retSup;
	}
	public void setRsEc(String retState,String retValid,String retSup){
		this.setRetState(retState);
		this.setRetValid(retValid);
		this.setRetSup(retSup);
	}
	public String appOutletReturn(){
		return "{\"retSwitch\":{\"retState\":\""+this.retState+",\"retValid\":\""+this.retSup+"\"}}";
	}
}
