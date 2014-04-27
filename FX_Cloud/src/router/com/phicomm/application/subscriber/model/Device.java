package com.phicomm.application.subscriber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name="fx_device",uniqueConstraints={@UniqueConstraint(columnNames = { "deviceMac" },name="un_mac")})
public class Device {
	
	private long id;
	private String DeviceTyp;		//设备型号，如FWR-602
	private String DeviceMac;		//设备MAC
	private String NickName;		//设备别名
	private String AdminName;		//设备的Admin账号
	private String AdminPassword;	//设备的Admin账号的密码
	private String DeviceWanIp;		//设备的Wan口IP
	private String UserEmail;		//对应云账户的Email
	private long uid;				
	private String DeviceVer;		//设备当前的软件版本
	private String ActiveStatus;	//设备是否激活
	private Date deviceTime;
	private String hardVer;

	public Date getDeviceTime() {
		return deviceTime;
	}
	public void setDeviceTime(Date deviceTime) {
		this.deviceTime = deviceTime;
	}
	@GeneratedValue
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDeviceTyp() {
		return DeviceTyp;
	}
	public void setDeviceTyp(String deviceTyp) {
		DeviceTyp = deviceTyp;
	}
	
	@Column(name = "deviceMac", unique = true, nullable = true)
	public String getDeviceMac() {
		return DeviceMac;
	}
	public void setDeviceMac(String deviceMac) {
		DeviceMac = deviceMac;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getAdminName() {
		return AdminName;
	}
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
	public String getAdminPassword() {
		return AdminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		AdminPassword = adminPassword;
	}
	public String getDeviceWanIp() {
		return DeviceWanIp;
	}
	public void setDeviceWanIp(String deviceWanIp) {
		DeviceWanIp = deviceWanIp;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getDeviceVer() {
		return DeviceVer;
	}
	public void setDeviceVer(String deviceVer) {
		DeviceVer = deviceVer;
	}
	public String getActiveStatus() {
		return ActiveStatus;
	}
	public void setActiveStatus(String activeStatus) {
		ActiveStatus = activeStatus;
	}
	@Column(name="hard_ver")
	public String getHardVer() {
		return hardVer;
	}
	public void setHardVer(String hardVer) {
		this.hardVer = hardVer;
	}

	
}
