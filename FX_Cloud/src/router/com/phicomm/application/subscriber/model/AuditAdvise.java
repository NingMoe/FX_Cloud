package com.phicomm.application.subscriber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="fx_aduit_advise")
public class AuditAdvise {
	private long id;
	/**
	 * 审核意见
	 */
	private String advise;
	private Topic topic;
	private Manager user;
	private Date auditTime;
	/**
	 * 0：未审核
	 * 1:一审通过
	 * 2:一审禁止
	 * 3：已删除
	 * 4：二审通过
	 * 5：二审禁止
	 * 6：已发布
	 */
	private byte status;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="advise",columnDefinition="text")
	public String getAdvise() {
		return advise;
	}
	public void setAdvise(String advise) {
		this.advise = advise;
	}
	@ManyToOne
	@JoinColumn(name="t_id")
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	@ManyToOne
	@JoinColumn(name="u_id")
	public Manager getUser() {
		return user;
	}
	public void setUser(Manager user) {
		this.user = user;
	}
	@Column(name="audit_time")
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	@Column(name="status",nullable=false,columnDefinition="tinyint default 0")
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	
	
}
