package com.phicomm.application.subscriber.dao;


import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.Release;

@Repository("releaseDao")
public class ReleaseDao extends HibernateDaoSupport implements IReleaseDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(Release release) {
		this.getHibernateTemplate().save(release);
	}	

	/*@Override
	public void update(Release release) {
		this.getHibernateTemplate().update(release);
	}

	@Override
	public void delete(int id) {
		Release release = this.load(id);
		this.getHibernateTemplate().delete(release);
	}

	@Override
	public Release load(int id) {
		return this.getHibernateTemplate().load(Release.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Release> list() {
		return this.getSession().createQuery("from Release").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Release> find() {
		int size = SystemContext.getSize();
		int offset = SystemContext.getOffset();
		Query query = this.getSession().createQuery("from Release");
		query.setFirstResult(offset).setMaxResults(size);
		List<Release> datas = query.list();
		Pager<Release> us = new Pager<Release>();
		us.setDatas(datas);
		us.setOffset(offset);
		us.setSize(size);
		long total = (Long)this.getSession()
					.createQuery("select count(*) from Release")
					.uniqueResult();
		us.setTotal(total);
		return us;
	}
	
	@Override
	public Release loadByUsername(String username) {
		return (Release)this.getSession().createQuery("from Release where reger2Mail=?")
					.setParameter(0, username).uniqueResult();
	}*/	
}
