package com.phicomm.application.subscriber.dao;



import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.Msg;

@Repository("msgDao")
public class MsgDao extends HibernateDaoSupport implements IMsgDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(Msg msg) {
		this.getHibernateTemplate().save(msg);
	}

	
}
