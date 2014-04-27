package com.phicomm.application.subscriber.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.phicomm.application.subscriber.model.Topic;


public class TopicAuditDto {
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	private long id;
	private String advise;
	private Date auditTime;
	private byte status;
	private String employeeID;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getAdvise() {
		return advise;
	}
	public void setAdvise(String advise) {
		this.advise = advise;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public TopicAuditDto() {
	}
	public TopicAuditDto(String advise, Date auditTime,
			byte status, String employeeID) {
		this.advise = advise;
		this.auditTime = auditTime;
		this.status = status;
		this.employeeID = employeeID;
	}
	
	
	
	
}
