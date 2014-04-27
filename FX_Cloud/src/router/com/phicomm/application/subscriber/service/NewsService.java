package com.phicomm.application.subscriber.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.INewsDao;
import com.phicomm.application.subscriber.model.News;
import com.phicomm.application.subscriber.model.Pager;


@Service("newsService")
public class NewsService implements INewsService {

	private INewsDao newsDao;
	
	
	
	public INewsDao getNewsDao() {
		return newsDao;
	}

	@Resource
	public void setNewsDao(INewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public void add(News news) {
		//News u = newsDao.loadByUsername(news.getNewsTyp());
	//	if(u!=null) throw new UserException("此邮箱已被注册！");
		newsDao.add(news);
	}

	@Override
	public void update(News news) {
		newsDao.update(news);
	}

	@Override
	public void delete(long id) {
		newsDao.delete(id);
	}

	@Override
	public News load(long id) {
		return newsDao.load(id);
	}

	@Override
	public List<News> list(Timestamp time) {
		return newsDao.list(time);
	}

	@Override
	public List<News> list() {
		return newsDao.list();
	}
	
	@Override
	public List<News> listSalesByTime(Date lastTime){
		return newsDao.listSalesByTime(lastTime);
	}
	@Override
	public List<News> listSales(){
		return newsDao.listSales();
	}
	@Override
	public Pager<News> find() {
		return newsDao.find();
	}
	
	@Override
	public Pager<News> search(String name,String value) {
		return newsDao.search( name, value);
	}

	@Override
	public News loadbytime(Timestamp time,String DevcTyp) {
		News n = newsDao.loadbytime(time,DevcTyp);
		return n;
	}

	@Override
	public News loadbytime(String DevcTyp,String hardVer) {
		News n = newsDao.loadbytime(DevcTyp,hardVer);
		return n;
	}
	
	@Override
	public News loadbytime(String DevcTyp) {
		News n = newsDao.loadbytime(DevcTyp);
		return n;
	}
	
	@Override
	public News loadbyDevcVer(String DevcVer,String DevcTyp) {
		News n = newsDao.loadbyDevcVer(DevcVer,DevcTyp);
		return n;
	}
	@Override
	public News loadByVerAndTyp(String DevcVer,String DevcTyp){
		News n = newsDao.loadByVerAndTyp(DevcVer,DevcTyp);
		return n;
	}
	
	@Override
	public News loadByVerAndTyp(String DevcVer,String DevcTyp,long id){
		News n = newsDao.loadByVerAndTyp(DevcVer,DevcTyp,id);
		return n;
	}

	@Override
	public News loadbytime(String DevcTyp, String hardVer, Date lastTime) {
		//if(lastTime == null || "".equals(lastTime))
			//return newsDao.loadbytime(DevcTyp,hardVer);
		return newsDao.loadbytime(DevcTyp,hardVer,lastTime);
	}
	
}
