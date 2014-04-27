package com.phicomm.application.subscriber.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.Device;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.SystemContext;

@Repository("deviceDao")
public class DeviceDao extends HibernateDaoSupport implements IDeviceDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(Device device) {
		this.getHibernateTemplate().save(device);
	}

	@Override
	public void update(Device device) {
		this.getHibernateTemplate().update(device);
	}

	@Override
	public void delete(long id) {
		Device device = this.load(id);
		this.getHibernateTemplate().delete(device);
	}

	@Override
	public Device load(long id) {
		return this.getHibernateTemplate().load(Device.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> list() {
		return this.getSession().createQuery("from Device").list();
	}
	
	@Override
	public long loadByBiggerThanWanIp(String wanIp,Date deviceTime){
		return (Long)this.getSession().createQuery("select count(*) from Device where deviceWanIp=? and deviceTime>?")
				.setParameter(0, wanIp).setParameter(1, deviceTime).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Device> find() {
		int size = SystemContext.getPageSize();
		int offset = SystemContext.getPageOffset();
		Query query = this.getSession().createQuery("from Device");
		query.setFirstResult(offset).setMaxResults(size);
		List<Device> datas = query.list();
		Pager<Device> us = new Pager<Device>();
		us.setDatas(datas);
		us.setOffset(offset);
		us.setSize(size);
		long total = (Long)this.getSession()
					.createQuery("select count(*) from Device")
					.uniqueResult();
		us.setTotal(total);
	
		return us;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Device> loadByuid(long uid) {
		return this.getSession().createQuery("from Device where uid=?").setParameter(0, uid).list();
	}
	
	@Override
	public Device loadByMac(String mac) {
		return (Device)this.getSession().createQuery("from Device where deviceMac=?")
				.setParameter(0, mac).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Device> search(String name,String value) {
		int size = SystemContext.getPageSize();
		int offset = SystemContext.getPageOffset();
		Query query = this.getSession().createQuery("from Device where "+name+" like '%"+value+"%'");
		
		query.setFirstResult(offset).setMaxResults(size);
		List<Device> datas = query.list();
		Pager<Device> us = new Pager<Device>();
		us.setDatas(datas);
		us.setOffset(offset);
		us.setSize(size);
		long total = (Long)this.getSession()
					.createQuery("select count(*) from Device where "+name+" like '%"+value+"%'")
					.uniqueResult();
		us.setTotal(total);
		return us;
	}

	/**
		 * 发布者IP
		 */
		private String ipAddr;
}
