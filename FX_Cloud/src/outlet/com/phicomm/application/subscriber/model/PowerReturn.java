package com.phicomm.application.subscriber.model;

import java.util.List;

public class PowerReturn {
	private String retState;
	private String errorCode;
	private String devMac;
	private String devName;
	private String devStat;
	private String todayPow;
	private String monthPow;
	private String lastMonPow;
	private long day1Pow;
	private long day2Pow;
	private long day3Pow;
	private long day4Pow;
	private long day5Pow;
	private long day6Pow;
	
	
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
	public String getTodayPow() {
		return todayPow;
	}
	public void setTodayPow(String todayPow) {
		this.todayPow = todayPow;
	}
	public String getMonthPow() {
		return monthPow;
	}
	public void setMonthPow(String monthPow) {
		this.monthPow = monthPow;
	}
	public String getLastMonPow() {
		return lastMonPow;
	}
	public void setLastMonPow(String lastMonPow) {
		this.lastMonPow = lastMonPow;
	}
	public long getDay1Pow() {
		return day1Pow;
	}
	public void setDay1Pow(long day1Pow) {
		this.day1Pow = day1Pow;
	}
	public long getDay2Pow() {
		return day2Pow;
	}
	public void setDay2Pow(long day2Pow) {
		this.day2Pow = day2Pow;
	}
	public long getDay3Pow() {
		return day3Pow;
	}
	public void setDay3Pow(long day3Pow) {
		this.day3Pow = day3Pow;
	}
	public long getDay4Pow() {
		return day4Pow;
	}
	public void setDay4Pow(long day4Pow) {
		this.day4Pow = day4Pow;
	}
	public long getDay5Pow() {
		return day5Pow;
	}
	public void setDay5Pow(long day5Pow) {
		this.day5Pow = day5Pow;
	}
	public long getDay6Pow() {
		return day6Pow;
	}
	public void setDay6Pow(long day6Pow) {
		this.day6Pow = day6Pow;
	}
	public void setRsEc(String retState,String errorCode){
		this.setRetState(retState);
		this.setErrorCode(errorCode);
	}
	
	public String getPowerReturn(Outlet outlet,double tdKwh,double tmKwh,List<RecordMonth> rms,List<RecordDay> rds){
		StringBuilder sb = new StringBuilder();
		int j;
		sb.append("{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"device\":{"
				+ "\"devMac\":\""+outlet.getMac()+"\",\"devName\":\""+outlet.getAlias()+"\",\"devStat\":\""+outlet.getStatus()
				+"\",\"todayPow\":\""+tdKwh+"\",\"monthPow\":\""+tmKwh+"\",\"lastMonPow\":\"");
		if(rms == null) sb.append(0);
		else sb.append(rms.get(0).getNowKwh());
		sb.append("\"");
		
		for(j=0;j<rds.size();j++){
			sb.append(",day"+j+"Pow\":\""+rds.get(j).getNowKwh()+"\"");
		}
		for(j=rds.size();j<7;j++){
			sb.append(",day"+j+"Pow\":\""+0+"\"");
		}
		sb.append("}}");
		return sb.toString();
	}
	public String getPowerReturn(){
		return "{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"device\":{}}";
	}

}
