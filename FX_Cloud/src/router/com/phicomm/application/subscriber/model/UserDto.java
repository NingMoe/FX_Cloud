package com.phicomm.application.subscriber.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class UserDto {
	private java.math.BigInteger newid;
	private String userName;
	private String email;
	private String mobelPhone;
	private String city;
	private String postCode;
	private String onlinePhoneMac;
	private Timestamp lastAccess;
	


	public BigInteger getId() {
		return newid;
	}


	public void setId(BigInteger newid) {
		this.newid =newid;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getMobelPhone() {
		return mobelPhone;
	}


	public void setMobelPhone(String mobelPhone) {
		this.mobelPhone = mobelPhone;
	}


	public String getPostCode() {
		return postCode;
	}


	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}


	public String getOnlinePhoneMac() {
		return onlinePhoneMac;
	}


	public void setOnlinePhoneMac(String onlinePhoneMac) {
		this.onlinePhoneMac = onlinePhoneMac;
	}


	public Timestamp getLastAccess() {
		return lastAccess;
	}


	public void setLastAccess(Timestamp lastAccess) {
		this.lastAccess = lastAccess;
	}


	public UserDto(String email, String city) {
		super();
		this.email = email;
		this.city = city;
	}


	public UserDto(){
		
	}
	
}
