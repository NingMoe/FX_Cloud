package com.phicomm.application.subscriber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 插座即时上报的电压电流存储在此表
 * @author boke
 *
 */
@Entity
@Table(name="cloud_outlet_report")
public class OutletReport {
	/**
	 * 自增主键
	 */
	private long id;
	/**
	 * 电流
	 */
	private double amps;
	/**
	 * 电压
	 */
	private double volt;
	/**
	 * 功率
	 */
	private double kwh;
	/**
	 * 上报时间，服务器时间
	 */
	private Date reportTime;
	/**
	 * 总的功耗
	 */
	private double totalKwh;
	/**
	 * 断电清空时间
	 */
	private Date clearTime;
	/**
	 * 插座上报，计算当前上报信息的时间
	 */
	private Date calTime;
	/**
	 * 插座id
	 */
	private Outlet outlet;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="rep_amps")
	public double getAmps() {
		return amps;
	}
	public void setAmps(double amps) {
		this.amps = amps;
	}
	
	@Column(name="rep_volt")
	public double getVolt() {
		return volt;
	}
	public void setVolt(double volt) {
		this.volt = volt;
	}
	
	@Column(name="report_time")
	@Index(name="ix_rpt")
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	@Column(name="clear_time")
	public Date getClearTime() {
		return clearTime;
	}
	public void setClearTime(Date clearTime) {
		this.clearTime = clearTime;
	}
	@ManyToOne
	@JoinColumn(name="otl_id")
	public Outlet getOutlet() {
		return outlet;
	}
	public void setOutlet(Outlet outlet) {
		this.outlet = outlet;
	}
	public OutletReport() {
	}
	
	@Column(name="cal_time")
	public Date getCalTime() {
		return calTime;
	}
	public void setCalTime(Date calTime) {
		this.calTime = calTime;
	}
	@Column(name="rep_kwh")
	public double getKwh() {
		return kwh;
	}
	public void setKwh(double kwh) {
		this.kwh = kwh;
	}
	@Column(name="rep_total_kwh")
	public double getTotalKwh() {
		return totalKwh;
	}
	public void setTotalKwh(double totalKwh) {
		this.totalKwh = totalKwh;
	}
	
	
}
