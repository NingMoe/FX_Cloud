package com.phicomm.application.subscriber.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.PhoneUser;

@Repository("phoneuserDao")
public class PhoneUserDao extends HibernateDaoSupport implements IPhoneUserDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(PhoneUser phoneuser) {
		this.getHibernateTemplate().save(phoneuser);
	}

	@Override
	public void update(PhoneUser phoneuser) {
		this.getHibernateTemplate().update(phoneuser);
	}

	@Override
	public void delete(long id) {
		PhoneUser phoneuser = this.load(id);
		this.getHibernateTemplate().delete(phoneuser);
	}

	@Override
	public PhoneUser load(long id) {
		return this.getHibernateTemplate().load(PhoneUser.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhoneUser> list() {
		return this.getSession().createQuery("from PhoneUser").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhoneUser> listUid(long uid,String phoneType,String phoneVer) {
		return this.getSession().createQuery("from PhoneUser where uid=? and phoneType=? and phoneVer=?").
				setParameter(0, uid).setParameter(1, phoneType).setParameter(2, phoneVer).list();
	}
	
	
	
	@Override
	public long countUid(long uid){
		long total = (Long)this.getSession()
				.createQuery("select count(*) from PhoneUser where uid=?").setParameter(0, uid)
				.uniqueResult();
		return total;
	}

}
