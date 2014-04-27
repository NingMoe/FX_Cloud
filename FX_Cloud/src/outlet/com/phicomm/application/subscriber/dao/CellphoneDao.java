package com.phicomm.application.subscriber.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.Cellphone;

@SuppressWarnings("unchecked")
@Repository("cellphoneDao")
public class CellphoneDao extends HibernateDaoSupport implements ICellphoneDao {
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(Cellphone cellphone) {
		this.getSession().save(cellphone);
	}

	@Override
	public void delete(long id) {
		Cellphone cellphone = this.load(id);
		this.getSession().delete(cellphone);
	}

	@Override
	public void update(Cellphone cellphone) {
		this.getSession().update(cellphone);
	}

	@Override
	public Cellphone load(long id) {
		return (Cellphone)this.getSession().load(Cellphone.class, id);
	}

	@Override
	public List<Cellphone> list() {
		return this.getSession().createQuery("from Cellphone").list();
	}

	@Override
	public Cellphone loadByImei(String imei) {
		return (Cellphone)this.getSession().createQuery("from Cellphone c where c.imei=?").setParameter(0, imei).uniqueResult();
	}

	@Override
	public List<Cellphone> loadByOutlet(long outlet_id){
		return this.getSession().createQuery("from op.cellphone left join fetch OutletPhone op left join fetch op.outlet o where o.id=?")
				.setParameter(0, outlet_id).list();
	}
}
