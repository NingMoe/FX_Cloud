package com.phicomm.application.subscriber.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.phicomm.application.subscriber.model.News;
import com.phicomm.application.subscriber.model.Pager;
public interface INewsDao {
	public void add(News news);
	public void update(News news);
	public void delete(long id);
	public News load(long id);
	public List<News> list(Timestamp time);
	public List<News> list();
	public List<News> listSalesByTime(Date lastTime);
	public List<News> listSales();
	public Pager<News> find();
	public Pager<News> search(String name,String value);
	public News loadbyDevcVer(String DevcVer,String DevcTyp);
	public News loadbytime(Timestamp time,String DevcTyp);
	public News loadbytime(String DevcTyp);
	public News loadByVerAndTyp(String DevcVer,String DevcTyp);
	public News loadByVerAndTyp(String DevcVer,String DevcTyp,long id);
	public News loadbytime(String DevcTyp, String hardVer);
	public News loadbytime(String DevcTyp, String hardVer,Date lastTime);
}
