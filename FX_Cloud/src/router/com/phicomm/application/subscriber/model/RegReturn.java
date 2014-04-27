package com.phicomm.application.subscriber.model;

public class RegReturn {
	public int retState;
	public int ifSuport;
	public int regStat;
	public int retReason;
	
	
	
	public String Return() {
		return "{\"retLogin\":{\"retState\":\""+this.retState+"\",\"ifSuport\":\""+this.ifSuport+"\",\"regStat\":\""+this.regStat+"\",\"retReason\":\""+this.retReason+"\"}}";
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
	public int getRegStat() {
		return regStat;
	}
	public void setRegStat(int regStat) {
		this.regStat = regStat;
	}
	public int getRetReason() {
		return retReason;
	}
	public void setRetReason(int retReason) {
		this.retReason = retReason;
	}
	
	
	
}
