package com.phicomm.application.subscriber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 消息表
 * @author boke.xu
 *
 */
@Entity
@Table(name="fx_topic")
public class Topic {
	private long id;
	private String title;
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
	/**
	 * 文章的内容
	 */
	private String content;
	/**
	 * 文章的发布时间，用来进行排序的
	 */
	private Date publishStartDate;
	private Date publishEndDate;
	/**
	 * 文章的创建时间
	 */
	private Date createDate;
	/**
	 * 文章的作者名称，用来显示用户的昵称，冗余字段
	 */
	private String author;
	/**
	 * 发布者IP
	 */
	private String ipAddr;
	/**
	 * 文章的发布者
	 */
	private Manager user;
	private String publishRange;
	private byte publishRate;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@Column(name="content",columnDefinition="text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="status",nullable=false,columnDefinition="tinyint default 0")
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	
	
	
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	@ManyToOne
	@JoinColumn(name="uid")
	public Manager getUser() {
		return user;
	}
	public void setUser(Manager u) {
		this.user = u;
	}
	
	@Column(name="ip_address")
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	@Column(name="start_date")
	public Date getPublishStartDate() {
		return publishStartDate;
	}
	public void setPublishStartDate(Date publishStartDate) {
		this.publishStartDate = publishStartDate;
	}
	@Column(name="end_date")
	public Date getPublishEndDate() {
		return publishEndDate;
	}
	public void setPublishEndDate(Date publishEndDate) {
		this.publishEndDate = publishEndDate;
	}
	@Column(name="publish_range")
	public String getPublishRange() {
		return publishRange;
	}
	public void setPublishRange(String publishRange) {
		this.publishRange = publishRange;
	}
	@Column(name="publish_rate",columnDefinition="tinyint default 0")
	public byte getPublishRate() {
		return publishRate;
	}
	public void setPublishRate(byte publishRate) {
		this.publishRate = publishRate;
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", title=" + title + "]";
	}
	
	
	public Topic(long id, String title, byte status, 
			Date publishStartDate, Date publishEndDate, Date createDate,
			String author, String publishRange, byte publishRate) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.publishStartDate = publishStartDate;
		this.publishEndDate = publishEndDate;
		this.createDate = createDate;
		this.author = author;
		this.publishRange = publishRange;
		this.publishRate = publishRate;
	}
	
	public Topic() {
	}
	
}
