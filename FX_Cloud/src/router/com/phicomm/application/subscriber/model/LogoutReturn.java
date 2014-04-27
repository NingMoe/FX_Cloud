package com.phicomm.application.subscriber.model;



public class LogoutReturn {
	 
	public int retState;
	public int ifSuport;
	public int retLogout; 
	

	public String Return() {
		return "{\"retLogout\":{\"retState\":\""+this.retState+"\",\"ifSuport\":\""+this.ifSuport+"\",\"retLogout\":\""+this.retLogout+"\"}}";
			
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



	public int getRetLogout() {
		return retLogout;
	}



	public void setRetLogout(int retLogout) {
		this.retLogout = retLogout;
	}





	
	
	
}
