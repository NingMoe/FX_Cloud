package com.phicomm.application.subscriber.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="fx_user")
public class User {
	
	private long id;
	private String MailAddress;		//用户邮箱
	private String Password;		//用户密码
	private String Username;		//用户名
	private String Phone;			//手机号
	private String Province;		//省
	private String ZipCode;			//邮编
	private String OnlineMac;		//当前APP登录的设备MAC
	private Timestamp Time;				
	private Date createTime;		//用户创建时间
	private int isActive;			//是否激活,默认是0未激活，1是已激活,2是失效
	private String activeCode;
	@GeneratedValue
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	@NotEmpty(message="用户邮箱不能为空")
	@Email(message="邮箱格式不正确")
	@Index(name="id_mail")
	public String getMailAddress() {
		return MailAddress;
	}
	public void setMailAddress(String mailAddress) {
		MailAddress = mailAddress;
	}
	@NotEmpty(message="用户密码不能为空")
	@Index(name="id_psd")
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	@Column(name = "onlineMac", unique = true, nullable = true)
	public String getOnlineMac() {
		return OnlineMac;
	}
	public void setOnlineMac(String onlineMac) {
		OnlineMac = onlineMac;
	}
	public Timestamp getTime() {
		return Time;
	}
	public void setTime(Timestamp time) {
		Time = time;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="isActive",columnDefinition="int(10) not null default 0")
	@Index(name="ix_active")
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	
	
}
