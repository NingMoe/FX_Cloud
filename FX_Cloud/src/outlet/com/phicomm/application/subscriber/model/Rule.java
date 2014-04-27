package com.phicomm.application.subscriber.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 插座的规则
 * @author boke
 *
 */
@Entity
@Table(name="cloud_rule")
public class Rule {
	/**
	 * 自增主键
	 */
	private long id;
	/**
	 * 规则的名字
	 */
	private String name;
	/**
	 * 规则的创建者
	 */
	private String creater;
	/**
	 * 规则的间隔
	 */
	private String interval;
	/**
	 * 规则生效时间
	 */
	private Time starts;
	/**
	 * 这条规则属于哪个设备
	 */
	private Outlet outlet;
	/**
	 * 顺序
	 */
	private int orders;
	/**
	 * 规则状态
	 * 0:未知
	 * 1:关闭
	 * 2：开启
	 */
	private byte status;
	/**
	 * 规则是否每周重读
	 * 0:未知
	 * 1：不重复
	 * 2：重复
	 */
	private byte isRepeat;
	/**
	 * 是否生效
	 */
	private byte isActive;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="rul_name",length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="rul_creater",length=100)
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name="rul_interval",length=20,nullable=false)
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	
	@Column(name="rul_starts",nullable=false)
	public Time getStarts() {
		return starts;
	}
	public void setStarts(Time starts) {
		this.starts = starts;
	}
	
	@Column(name="rul_status",nullable=false,columnDefinition="tinyint default 0")
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	@Column(name="rul_orders")
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	
	@Column(name="is_repeat",nullable=false,columnDefinition="tinyint default 0")
	public byte getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(byte isRepeat) {
		this.isRepeat = isRepeat;
	}
	
	@ManyToOne
	@JoinColumn(name="otl_id")
	public Outlet getOutlet() {
		return outlet;
	}
	public void setOutlet(Outlet outlet) {
		this.outlet = outlet;
	}
	
	@Column(name="is_active")
	public byte getIsActive() {
		return isActive;
	}
	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	
	
}
