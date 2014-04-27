package com.phicomm.application.subscriber.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.phicomm.application.subscriber.model.Topic;


public class TopicDto {
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	private long id;
	private String title;
	private String content;
	/**
	 * 文章的发布时间，用来进行排序的
	 */
	private String publishStartDate;
	private String publishEndDate;
	private String publishRange;
	private int publishRate;
	private String createDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@NotEmpty(message="文章标题不能为空")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getPublishStartDate() {
		return publishStartDate;
	}
	public void setPublishStartDate(String publishStartDate) {
		this.publishStartDate = publishStartDate;
	}
	public String getPublishEndDate() {
		return publishEndDate;
	}
	public void setPublishEndDate(String publishEndDate) {
		this.publishEndDate = publishEndDate;
	}
	public String getPublishRange() {
		return publishRange;
	}
	public void setPublishRange(String publishRange) {
		this.publishRange = publishRange;
	}
	public int getPublishRate() {
		return publishRate;
	}
	public void setPublishRate(int publishRate) {
		this.publishRate = publishRate;
	}
	public TopicDto() {
	}
	
	public Topic getTopic() {
		Topic t = new Topic();
		t.setContent(this.getContent());
		t.setId(this.getId());
		t.setTitle(this.getTitle());
		t.setPublishRange(this.getPublishRange());
		t.setPublishRate((byte)this.getPublishRate());
		try {
			t.setPublishStartDate(sdf.parse(this.getPublishStartDate()));
			t.setPublishEndDate(sdf.parse(this.getPublishEndDate()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		/*try {
			Date d = sdf.parse(this.getPublishStartDate());
			Calendar cd = Calendar.getInstance();
			cd.setTime(d);
			Calendar ca = Calendar.getInstance();
			ca.setTime(new Date());
			ca.set(cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DATE));
			t.setPublishStartDate(ca.getTime());
		} catch (ParseException e) {
			t.setPublishStartDate(new Date());
		}*/
		return t;
	}
	public TopicDto(Topic topic) {
		this.setContent(topic.getContent());
		this.setId(topic.getId());
		this.setPublishRange(topic.getPublishRange());
		this.setPublishRate(topic.getPublishRate());
		this.setTitle(topic.getTitle());
	}
	
	
}
