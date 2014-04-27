package com.phicomm.application.subscriber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 插在插座上的设备
 * @author boke
 *
 */
public class OutletDevice {
	/**
	 * 自增主键
	 */
	private long id;
	/**
	 * 设备类别,比如冰箱，空调之类
	 */
	private String type;
	/**
	 * 设备的型号
	 */
	private String ver;
	/**
	 * 设备品牌
	 */
	private String brand;
	
	/**
	 * 注册时间
	 */
	private Date bindTime;
	private String devAlias;
	private Outlet outlet;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="dev_type",length=100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="dev_ver",length=100)
	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	@Column(name="dev_brand",length=100)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name="bind_time")
	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
	@Column(name="dev_alias",length=100)
	public String getDevAlias() {
		return devAlias;
	}

	public void setDevAlias(String devAlias) {
		this.devAlias = devAlias;
	}

	@ManyToOne
	@JoinColumn(name="otl_id")
	public Outlet getOutlet() {
		return outlet;
	}

	public void setOutlet(Outlet outlet) {
		this.outlet = outlet;
	}
	
	
}
