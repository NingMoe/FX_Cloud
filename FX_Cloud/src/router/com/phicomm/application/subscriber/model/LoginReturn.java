package com.phicomm.application.subscriber.model;

public class LoginReturn {

	 
	public int retState;
	public int ifSuport;
	public int loginSta;
	public int retReason;
	public String retReasonText;
	
	
	public String Return() {
		return "{\"retLogin\":{\"retState\":\""+this.retState+"\",\"ifSuport\":\""+this.ifSuport+"\",\"loginSta\":\""+this.loginSta+"\",\"retReason\":\""+this.retReason+"\"}}";
	}
	
	public String ReturnAllReasonText() {
		return "{\"retLogin\":{\"retState\":\""+this.retState+"\",\"ifSuport\":\""+this.ifSuport+"\",\"loginSta\":\""+this.loginSta+"\",\"retReason\":\""+this.retReason+"\",\"retReasonText\":\""+this.retReasonText+"\"}}";
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



	public int getLoginSta() {
		return loginSta;
	}



	public void setLoginSta(int loginSta) {
		this.loginSta = loginSta;
	}



	public int getRetReason() {
		return retReason;
	}



	public void setRetReason(int retReason) {
		this.retReason = retReason;
	}



	public String getRetReasonText() {
		return retReasonText;
	}



	public void setRetReasonText(String retReasonText) {
		this.retReasonText = retReasonText;
	}

	
	
	
}
