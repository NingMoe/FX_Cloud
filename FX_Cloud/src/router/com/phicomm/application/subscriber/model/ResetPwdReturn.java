package com.phicomm.application.subscriber.model;

public class ResetPwdReturn {

	 
	public int retState;
	public int ifSuport;
	public int resetSta;
	public int retReason;
	
	
	
	public String Return() {
		return "{\"retLogin\":{\"retState\":\""+this.retState+"\",\"ifSuport\":\""+this.ifSuport+"\",\"activeSta\":\""+this.resetSta+"\",\"activeCode\":\""+this.retReason+"\"}}";
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



	public int getResetSta() {
		return resetSta;
	}



	public void setResetSta(int resetSta) {
		this.resetSta = resetSta;
	}



	public int getRetReason() {
		return retReason;
	}



	public void setRetReason(int retReason) {
		this.retReason = retReason;
	}



	
}
