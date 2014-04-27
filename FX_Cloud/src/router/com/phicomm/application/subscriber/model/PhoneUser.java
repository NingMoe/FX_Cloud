package com.phicomm.application.subscriber.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="fx_phone_user")
public class PhoneUser {
	private long id;
	public String phoneType;
	public String phoneVer;
	public long uid;

	
	@GeneratedValue
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "phoneType",columnDefinition="varchar(50) not null default ''")
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	
	@Column(name = "phoneVer",columnDefinition="varchar(50) not null default ''")
	public String getPhoneVer() {
		return phoneVer;
	}
	public void setPhoneVer(String phoneVer) {
		this.phoneVer = phoneVer;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
	
	
	
	
}
