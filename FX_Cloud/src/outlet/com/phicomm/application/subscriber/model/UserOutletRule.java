package com.phicomm.application.subscriber.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.phicomm.application.subscriber.model.User;

/**
 * 规则的关系表
 * @author boke
 *
 */
//@Table(name="cloud_usr_otl_rul",uniqueConstraints={@UniqueConstraint(columnNames={"usr_id", "otl_id"})})
//@Entity
//@Table(name="cloud_usr_otl_rul")
public class UserOutletRule {
	private long id;
	private User user;
	private Rule rule;
	private String otlIds;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	@ManyToOne
	@JoinColumn(name="usr_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name="rul_id")
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public UserOutletRule() {
	}
	
	@Column(name="otl_ids")
	public String getOtlIds() {
		return otlIds;
	}
	public void setOtlIds(String otlIds) {
		this.otlIds = otlIds;
	}
	
	
}
