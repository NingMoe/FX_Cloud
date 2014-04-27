package com.phicomm.application.subscriber.model;

import java.util.List;


public class RouterLoginStatusReturn {

	public String PubMAC;
	
	public String AdminUsername;	//路由器本地admin账户
	public String AdminPassword;
	public String WAN_IP;
	public String MAIL_ADDRESS;
	public String PASSWORD;		//云账号密码
	public String USERNAME;
	public String MP_NUMBER;
	public String PROVINCE;
	public String ZIP_CODE;
	public String ACT_STATUS;
	public String DevcTyp;	//类型
	public String DevcVer; //软件版本
	public String hardVer;//硬件版本
	public List<String> EventCode;//路由器接口区分
	
	
	public String getHardVer() {
		return hardVer;
	}
	public void setHardVer(String hardVer) {
		this.hardVer = hardVer;
	}
	public String getPubMAC() {
		return PubMAC;
	}
	public void setPubMAC(String pubMAC) {
		PubMAC = pubMAC;
	}

	
	public String getAdminUsername() {
		return AdminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		AdminUsername = adminUsername;
	}

	public String getAdminPassword() {
		return AdminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		AdminPassword = adminPassword;
	}
	public String getWAN_IP() {
		return WAN_IP;
	}
	public void setWAN_IP(String wAN_IP) {
		WAN_IP = wAN_IP;
	}
	public String getMAIL_ADDRESS() {
		return MAIL_ADDRESS;
	}
	public void setMAIL_ADDRESS(String mAIL_ADDRESS) {
		MAIL_ADDRESS = mAIL_ADDRESS;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getMP_NUMBER() {
		return MP_NUMBER;
	}
	public void setMP_NUMBER(String mP_NUMBER) {
		MP_NUMBER = mP_NUMBER;
	}
	public String getPROVINCE() {
		return PROVINCE;
	}
	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}
	public String getZIP_CODE() {
		return ZIP_CODE;
	}
	public void setZIP_CODE(String zIP_CODE) {
		ZIP_CODE = zIP_CODE;
	}
	public String getACT_STATUS() {
		return ACT_STATUS;
	}
	public void setACT_STATUS(String aCT_STATUS) {
		ACT_STATUS = aCT_STATUS;
	}
	public String getDevcTyp() {
		return DevcTyp;
	}
	public void setDevcTyp(String aDevcTyp) {
		DevcTyp = aDevcTyp;
	}
	public String getDevcVer() {
		return DevcVer;
	}
	public void setDevcVer(String devcVer) {
		DevcVer = devcVer;
	}
	public List<String> getEventCode() {
		return EventCode;
	}
	public void setEventCode(List<String> eventCode) {
		EventCode = eventCode;
	}
	@Override
	public String toString() {
		return "RouterLoginStatusReturn [PubMAC=" + PubMAC + ", AdminUsername="
				+ AdminUsername + ", AdminPassword=" + AdminPassword
				+ ", WAN_IP=" + WAN_IP + ", MAIL_ADDRESS=" + MAIL_ADDRESS
				+ ", PASSWORD=" + PASSWORD + ", USERNAME=" + USERNAME
				+ ", MP_NUMBER=" + MP_NUMBER + ", PROVINCE=" + PROVINCE
				+ ", ZIP_CODE=" + ZIP_CODE + ", ACT_STATUS=" + ACT_STATUS
				+ ", DevcTyp=" + DevcTyp + ", DevcVer=" + DevcVer
				+ ", hardVer=" + hardVer + ", EventCode=" + EventCode + "]";
	}
	public RouterLoginStatusReturn() {
	}		
}
