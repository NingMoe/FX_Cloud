package com.phicomm.application.subscriber.model;


public class PowerHDMReturn {
	private String retState;
	private String errorCode;
	private String devMac;
	private String devName;
	private String devStat;
	private String hourPow;
	private String dayPow;
	private String monPow;
	
	public String getRetState() {
		return retState;
	}

	public void setRetState(String retState) {
		this.retState = retState;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDevMac() {
		return devMac;
	}

	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevStat() {
		return devStat;
	}

	public void setDevStat(String devStat) {
		this.devStat = devStat;
	}

	public String getHourPow() {
		return hourPow;
	}

	public void setHourPow(String hourPow) {
		this.hourPow = hourPow;
	}

	public String getDayPow() {
		return dayPow;
	}

	public void setDayPow(String dayPow) {
		this.dayPow = dayPow;
	}

	public String getMonPow() {
		return monPow;
	}

	public void setMonPow(String monPow) {
		this.monPow = monPow;
	}

	public void setRsEc(String retState,String errorCode){
		this.setRetState(retState);
		this.setErrorCode(errorCode);
	}
	
	public String getPowerHoursReturn(Outlet outlet){
		StringBuilder sb = new StringBuilder();
		sb.append("{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"device\":{"
				+ "\"devMac\":\""+outlet.getMac()+"\",\"devName\":\""+outlet.getAlias()+"\",\"devStat\":\""+outlet.getStatus()
				+"\",\"hourPow\":\""+this.hourPow+"\"}}");
		return sb.toString();
	}
	public String getPowerDaysReturn(Outlet outlet){
		StringBuilder sb = new StringBuilder();
		sb.append("{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"device\":{"
				+ "\"devMac\":\""+outlet.getMac()+"\",\"devName\":\""+outlet.getAlias()+"\",\"devStat\":\""+outlet.getStatus()
				+"\",\"dayPow\":\""+this.dayPow+"\"}}");
		return sb.toString();
	}
	public String getPowerMonsReturn(Outlet outlet){
		StringBuilder sb = new StringBuilder();
		sb.append("{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"device\":{"
				+ "\"devMac\":\""+outlet.getMac()+"\",\"devName\":\""+outlet.getAlias()+"\",\"devStat\":\""+outlet.getStatus()
				+"\",\"monPow\":\""+this.monPow+"\"}}");
		return sb.toString();
	}
	public String getPowerReturn(){
		return "{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"device\":{}}";
	}

}
