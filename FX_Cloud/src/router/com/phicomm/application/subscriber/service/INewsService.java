package com.phicomm.application.subscriber.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.phicomm.application.subscriber.model.News;
import com.phicomm.application.subscriber.model.Pager;

public interface INewsService {
	public void add(News news);
	public void update(News news);
	public void delete(long id);
	public News load(long id);
	public List<News> list(Timestamp time);//获取时间不为null的促销信息
	public List<News> list();//获取时间null的促销信息
	public List<News> listSalesByTime(Date lastTime);
	public List<News> listSales();
	public Pager<News> find();
	public Pager<News> search(String name,String value);
	public News loadbyDevcVer(String DevcVer,String DevcTyp);
	public News loadbytime(Timestamp time,String DevcTyp);
	public News loadbytime(String DevcTyp,String hardVer);
	public News loadbytime(String DevcTyp);
	public News loadbytime(String DevcTyp,String hardVer,Date lastTime);
	public News loadByVerAndTyp(String DevcVer,String DevcTyp);
	public News loadByVerAndTyp(String DevcVer,String DevcTyp,long id);
}
