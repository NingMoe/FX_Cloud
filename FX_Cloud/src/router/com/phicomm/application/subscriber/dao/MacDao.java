package com.phicomm.application.subscriber.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.Mac;

@Repository("MacDao")
public class MacDao extends HibernateDaoSupport implements IMacDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	

	@Override
	public void add(Mac mac) {
		this.getHibernateTemplate().save(mac);
	}

	@Override
	public void update(Mac mac) {
		this.getHibernateTemplate().update(mac);
	}
	
	
	@Override
	public void delete(long id) {
		Mac mac = this.load(id);
		this.getHibernateTemplate().delete(mac);
	}

	@Override
	public Mac load(long id) {
		return this.getHibernateTemplate().load(Mac.class, id);
	}
	
	@Override
	public Mac loadByMac(String mac){
		return (Mac) this.getSession().createQuery("from Mac where mac=?").setParameter(0, mac).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mac> list() {
		return this.getSession().createQuery("from Mac").list();
	}


	
}
