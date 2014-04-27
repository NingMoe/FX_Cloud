package com.phicomm.application.subscriber.model;

import java.util.List;

public class AppReturn {
	public String retState;
	public String errorCode;
	public String devMac;
	public String devName;
	public String devStat;
	public String online;
	public String phoneMac;
	public String phoneTyp;
	
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

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getPhoneMac() {
		return phoneMac;
	}

	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}

	public String getPhoneTyp() {
		return phoneTyp;
	}

	public void setPhoneTyp(String phoneTyp) {
		this.phoneTyp = phoneTyp;
	}

	public void setRsEc(String retState,String errorCode){
		this.setRetState(retState);
		this.setErrorCode(errorCode);
	}
	public String loginRegReturn() {
		return "{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\"}";
	}
	public String logoutRegReturn() {
		return "{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\"}";
	}
	public String getAllDevReturn(List<Outlet> ol) {
		//TODO 检查设备是否在线
		StringBuilder sb = new StringBuilder();
		sb.append("{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"devices\":[");
		
		if(ol.size()!=0){
			for(int i=0;i<ol.size();i++){
				if(i<ol.size()-1){
					sb.append("{\"devMac\":\""+ol.get(i).getMac()+"\",\"devName\":\""+ol.get(i).getAlias()+
						"\",\"online\":\""+"1"+"\",devStat\":\""+ol.get(i).getStatus()+"\"},");
				}else {
					sb.append("{\"devMac\":\""+ol.get(i).getMac()+"\",\"devName\":\""+ol.get(i).getAlias()+
							"\",\"online\":\""+"1"+"\",devStat\":\""+ol.get(i).getStatus()+"\"}");
				}
			}
		}
		sb.append("]}");
		return sb.toString();
	}
	public String getAllDevReturn() {
		return "{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"devices\":[]}";
	}
	public String getBindPhoneReturn(List<Cellphone> lcp){
		StringBuilder sb = new StringBuilder();
		sb.append("{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"phones\":[");
		
		if(lcp.size()!=0){
			for(int i=0;i<lcp.size();i++){
				if(i<lcp.size()-1){
					sb.append("{\"phoneMac\":\""+lcp.get(i).getMac()+"\",\"phoneTyp\":\""+lcp.get(i).getType()+"\"},");
				}else {
					sb.append("{\"phoneMac\":\""+lcp.get(i).getMac()+"\",\"phoneTyp\":\""+lcp.get(i).getType()+"\"}");
				}
			}
		}
		sb.append("]}");
		return sb.toString();
	}
	public String getBindPhoneReturn(){
		return "{\"retState\":\""+this.retState+"\",\"errorCode\":\""+this.errorCode+"\",\"phones\":[]}";
	}
}
