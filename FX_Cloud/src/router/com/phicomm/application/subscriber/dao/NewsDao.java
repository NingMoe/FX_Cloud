package com.phicomm.application.subscriber.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.News;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.SystemContext;

@Repository("newsDao")
public class NewsDao extends HibernateDaoSupport implements INewsDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(News news) {
		this.getHibernateTemplate().save(news);
	}

	@Override
	public void update(News news) {
		this.getHibernateTemplate().update(news);
	}

	@Override
	public void delete(long id) {
		News news = this.load(id);
		this.getHibernateTemplate().delete(news);
	}

	@Override
	public News load(long id) {
		return this.getHibernateTemplate().load(News.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> list(Timestamp time) {
		return this.getSession().createQuery("from News where routerTyp=? and time>?").setParameter(0, "").setParameter(1, time).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<News> listSalesByTime(Date lastTime){
		return this.getSession().createQuery("from News where time>? and routerTyp=?").setParameter(0, lastTime).setParameter(1, "").list();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<News> listSales(){
		return this.getSession().createQuery("from News where routerTyp=?").setParameter(0, "").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<News> list() {
		return this.getSession().createQuery("from News where routerTyp=?").setParameter(0, "").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<News> find() {
		int size = SystemContext.getPageSize();
		int offset = SystemContext.getPageOffset();
		Query query = this.getSession().createQuery("from News");
		query.setFirstResult(offset).setMaxResults(size);
		List<News> datas = query.list();
		Pager<News> us = new Pager<News>();
		us.setDatas(datas);
		us.setOffset(offset);
		us.setSize(size);
		long total = (Long)this.getSession()
					.createQuery("select count(*) from News")
					.uniqueResult();
		us.setTotal(total);
	
		return us;
	}
	
	@Override
	public News loadbytime(Timestamp time,String DevcTyp) {
		return (News)this.getSession().createQuery("from News where time>? and routerTyp=? order by time desc")
					.setParameter(0, time).setParameter(1, DevcTyp).setFirstResult(0).setMaxResults(1).uniqueResult();
	}
	
	@Override
	public News loadbytime(String DevcTyp,String hardVer) {
		return (News)this.getSession().createQuery("from News where routerTyp=? and hardVer=? order by time desc")
					.setParameter(0, DevcTyp).setParameter(1, hardVer).setFirstResult(0).setMaxResults(1).uniqueResult();
	}
	
	@Override
	public News loadbytime(String DevcTyp) {
		return (News)this.getSession().createQuery("from News where routerTyp=? order by time desc")
					.setParameter(0, DevcTyp).setFirstResult(0).setMaxResults(1).uniqueResult();
	}
	
	@Override
	public News loadbyDevcVer(String DevcVer,String DevcTyp) {
		return (News)this.getSession().createQuery("from News where routerVer=? and routerTyp=?")
					.setParameter(0, DevcVer).setParameter(1, DevcTyp).uniqueResult();
	}
	
	@Override
	public News loadByVerAndTyp(String DevcVer,String DevcTyp) {
		return (News)this.getSession().createQuery("from News where routerVer=? and routerTyp=?")
					.setParameter(0, DevcVer).setParameter(1, DevcTyp).uniqueResult();
	}
	
	@Override
	public News loadByVerAndTyp(String DevcVer,String DevcTyp,long id) {
		return (News)this.getSession().createQuery("from News where routerVer=? and routerTyp=? and id!=?")
					.setParameter(0, DevcVer).setParameter(1, DevcTyp).setParameter(2, id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<News> search(String name,String value) {
		int size = SystemContext.getPageSize();
		int offset = SystemContext.getPageOffset();
		Query query = this.getSession().createQuery("from News where "+name+" like '%"+value+"%'");
		
		query.setFirstResult(offset).setMaxResults(size);
		List<News> datas = query.list();
		Pager<News> us = new Pager<News>();
		us.setDatas(datas);
		us.setOffset(offset);
		us.setSize(size);
		long total = (Long)this.getSession()
					.createQuery("select count(*) from News where "+name+" like '%"+value+"%'")
					.uniqueResult();
		us.setTotal(total);
		return us;
	}

	@Override
	public News loadbytime(String DevcTyp, String hardVer,Date lastTime) {
		return (News)this.getSession().createQuery("from News where routerTyp=? and hardVer=? and time>? order by time desc")
				.setParameter(0, DevcTyp).setParameter(1, hardVer).setParameter(2,lastTime).setFirstResult(0).setMaxResults(1).uniqueResult();
	}
}
