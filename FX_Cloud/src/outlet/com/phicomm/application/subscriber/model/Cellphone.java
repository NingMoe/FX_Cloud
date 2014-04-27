package com.phicomm.application.subscriber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 手机表
 * @author boke
 *
 */
@Entity
@Table(name="cloud_cellphone")
public class Cellphone {
	/**
	 * 自增主键
	 */
	private long id;
	/**
	 * 手机串号
	 */
	private String imei;
	/**
	 * 手机型号
	 */
	private String type;
	/**
	 * 手机版本
	 */
	private String ver;
	/**
	 * 手机号
	 */
	private String no;
	/**
	 * 手机mac地址
	 */
	private String mac;
	/**
	 * app的版本
	 */
	private String appVer;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="pho_imei",length=100,nullable=false)
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	@Column(name="pho_type",length=100)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="pho_ver",length=100)
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	@Column(name="pho_no",length=20)
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	@Column(name="pho_mac",length=20)
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	@Column(name="app_ver",length=50)
	public String getAppVer() {
		return appVer;
	}
	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}
	
	
}
