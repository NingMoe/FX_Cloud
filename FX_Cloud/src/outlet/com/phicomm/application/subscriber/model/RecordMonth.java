package com.phicomm.application.subscriber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 对每天的功耗进行计算
 * @author boke
 *
 */
@Entity
@Table(name="cloud_record_month")
public class RecordMonth {
	/**
	 * 自增主键
	 */
	private long id;
	/**
	 * 统计的时间
	 */
	private Date recordTime;
	/**
	 * 上一次的功耗
	 */
	private long preKwh;
	/**
	 * 这次的功耗
	 */
	private long nowKwh;
	/**
	 * 上一次的功率
	 */
	private long preWatt;
	/**
	 * 这次的功率
	 */
	private long nowWatt;
	/**
	 * 外键
	 */
	//private Outlet outlet;
	private long otlId;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="red_time",columnDefinition="timestamp default CURRENT_TIMESTAMP")
	@Index(name="ix_month_time")
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
	@Column(name="pre_kwh")
	public long getPreKwh() {
		return preKwh;
	}
	
	public void setPreKwh(long preKwh) {
		this.preKwh = preKwh;
	}
	
	@Column(name="now_kwh")
	public long getNowKwh() {
		return nowKwh;
	}
	public void setNowKwh(long nowKwh) {
		this.nowKwh = nowKwh;
	}
	
	@Column(name="pre_watt")
	public long getPreWatt() {
		return preWatt;
	}
	public void setPreWatt(long preWatt) {
		this.preWatt = preWatt;
	}
	
	@Column(name="now_watt")
	public long getNowWatt() {
		return nowWatt;
	}
	public void setNowWatt(long nowWatt) {
		this.nowWatt = nowWatt;
	}
	@Column(name="otl_id")
	@Index(name="ix_month_otl")
	public long getOtlId() {
		return otlId;
	}
	public void setOtlId(long otlId) {
		this.otlId = otlId;
	}
	
	//@ManyToOne
	//@JoinColumn(name="otl_id")
	//@Column(name="otl_id")
	/*public Outlet getOutlet() {
		return outlet;
	}
	public void setOutlet(Outlet outlet) {
		this.outlet = outlet;
	}*/
	
}
