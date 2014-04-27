package com.phicomm.application.subscriber.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;



@Entity
@Table(name="fx_news")

public class News {
	
	private long id;
	private String RouterVer;	//路由器版本
	private String NewsTheme;	//升级信息主体
	private String NewsContext;	//升级信息内容
	private String url;			//
	private String RouterTyp;	//路由器型号
	private Timestamp Time;		//发布的时间
	private Date startTime;		//消息的触发时间
	private Date endTime;		//消息的结束时间
	private String ipAddr;
	private String verDesc;
	private String hardVer;
	
	@GeneratedValue
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "VerDesc",columnDefinition="varchar(500) not null default ''")
	public String getVerDesc() {
		return verDesc;
	}
	public void setVerDesc(String verDesc) {
		this.verDesc = verDesc;
	}
	@Column(name = "RouterVer",columnDefinition="varchar(50) not null default ''")
	public String getRouterVer() {
		return RouterVer;
	}
	public void setRouterVer(String routerVer) {
		RouterVer = routerVer;
	}
	
	
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	@Column(name = "NewsTheme",columnDefinition="varchar(255) not null default ''")
	public String getNewsTheme() {
		return NewsTheme;
	}
	public void setNewsTheme(String newsTheme) {
		NewsTheme = newsTheme;
	}
	
	
	@Column(name = "newsContext",columnDefinition="varchar(255) not null default ''")
	public String getNewsContext() {
		return NewsContext;
	}
	public void setNewsContext(String newsContext) {
		NewsContext = newsContext;
	}
	
	
	
	@Column(name = "url",columnDefinition="varchar(255) not null default ''")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "routerTyp",columnDefinition="varchar(100) not null default ''")
	public String getRouterTyp() {
		return RouterTyp;
	}
	public void setRouterTyp(String routerTyp) {
		RouterTyp = routerTyp;
	}

	
	@Column(name = "time",columnDefinition="TIMESTAMP not null default now()")
	public Timestamp getTime() {
		return Time;
	}
	public void setTime(Timestamp time) {
		Time = time;
	}
	@Index(name="ix_start")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Index(name="end")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="hard_ver")
	public String getHardVer() {
		return hardVer;
	}
	public void setHardVer(String hardVer) {
		this.hardVer = hardVer;
	}
	

	
	
	
}
