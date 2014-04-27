package com.phicomm.application.subscriber.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.Cellphone;
import com.phicomm.application.subscriber.model.Outlet;
import com.phicomm.application.subscriber.model.OutletPhone;

@SuppressWarnings("unchecked")
@Repository("outletDao")
public class OutletDao extends HibernateDaoSupport implements IOutletDao {
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(Outlet outlet) {
		this.getSession().save(outlet);
	}

	@Override
	public void delete(long id) {
		Outlet outlet = this.load(id);
		this.getSession().delete(outlet);
	}

	@Override
	public void update(Outlet outlet) {
		this.getSession().update(outlet);
	}

	@Override
	public Outlet load(long id) {
		return (Outlet)this.getSession().load(Outlet.class, id);
	}

	@Override
	public List<Outlet> list() {
		return this.getSession().createQuery("from Outlet").list();
	}

	@Override
	public List<Outlet> listByUsrId(long usrId) {
		return this.getSession().createQuery("from Outlet o left join fetch o.user u where u.id=?")
				.setParameter(0, usrId).list();
	}

	@Override
	public Outlet loadByMac(String mac) {
		return (Outlet)this.getSession().createQuery("from Outlet o where o.mac=?").setParameter(0, mac).uniqueResult();
	}

	@Override
	public void addOutletCellphone(Outlet outlet, Cellphone cellphone) {
		OutletPhone op = new OutletPhone();
		op.setOutlet(outlet);
		op.setCellphone(cellphone);
		this.getSession().save(op);
	}

	@Override
	public void addBatch(List<Outlet> os) {
		for(int i=0;i<os.size();i++) {
		    this.getSession().save(os.get(i));
		    if(i%10==0){
		    	this.getSession().flush();
		    	this.getSession().clear();
		    }
		}
	}

}
