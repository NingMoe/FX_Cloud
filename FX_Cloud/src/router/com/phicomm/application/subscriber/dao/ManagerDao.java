package com.phicomm.application.subscriber.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.ManagerRole;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.Role;
import com.phicomm.application.subscriber.model.SystemContext;
@SuppressWarnings("unchecked")
@Repository("managerDao")
public class ManagerDao extends HibernateDaoSupport implements IManagerDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public void add(Manager manager) {
		this.getHibernateTemplate().save(manager);
	}
	
	@Override
	public Manager loadByUsername(String username,String password) {
		return (Manager)this.getSession().createQuery("from Manager where username=? and password=MD5(?)")
					.setParameter(0, username).setParameter(1,password).uniqueResult();
	}

	@Override
	public Manager load(long id) {
		return this.getHibernateTemplate().load(Manager.class, id);
	}

	
	@Override
	public List<Role> listManagerRoles(long managerId) {
		String hql = "select mr.role from ManagerRole mr where mr.manager.id=?";
		return this.getSession().createQuery(hql).setParameter(0,managerId).list();
	}
	
	@Override
	public List<Manager> listRoleManagers(long roleId) {
		String hql = "select ur.user from UserRole ur where ur.role.id=?";
		return this.getSession().createQuery(hql).setParameter(0,roleId).list();
	}
	
	@Override
	public void addManagerRole(Manager m, Role role) {
		ManagerRole mr = this.loadManagerRole(m.getId(), role.getId());
		if(mr!=null) return;
		mr = new ManagerRole();
		mr.setRole(role);
		mr.setManager(m);
		this.getSession().save(mr);
	}
	
	@Override
	public ManagerRole loadManagerRole(long managerId, long roleId) {
		String hql = "select ur from ManagerRole ur left join fetch ur.manager u left join fetch ur.role r where u.id=? and r.id=?";
		return (ManagerRole)this.getSession().createQuery(hql)
					.setParameter(0, managerId).setParameter(1, roleId).uniqueResult();
	}
	
	@Override
	public Manager loadByUsername(String username) {
		String hql = "from Manager where username=?";
		return (Manager)this.getSession().createQuery(hql)
				.setParameter(0, username).uniqueResult();
	}
	
	@Override
	public Pager<Manager> findManager() {
		int size = SystemContext.getPageSize();
		int offset = SystemContext.getPageOffset();
		Query query = this.getSession().createQuery("from Manager");
		query.setFirstResult(offset).setMaxResults(size);
		List<Manager> datas = query.list();
		Pager<Manager> us = new Pager<Manager>();
		us.setDatas(datas);
		us.setOffset(offset);
		us.setSize(size);
		long total = (Long)this.getSession()
					.createQuery("select count(*) from Manager")
					.uniqueResult();
		us.setTotal(total);
	
		return us;
	}

	@Override
	public void delete(long id) {
		Manager m = (Manager)this.getSession().load(Manager.class, id);
		m.setStatus((byte)2);
		this.getSession().update(m);
	}
	
	@Override
	public List<Long> listManagerRoleIds(long managerId) {
		String hql = "select ur.role.id from ManagerRole ur where ur.manager.id=?";
		return this.getSession().createQuery(hql).setParameter(0,managerId).list();
	}
	
	@Override
	public void deleteManagerRoles(long uid) {
		String hql = "delete ManagerRole ur where ur.manager.id=?";
		this.getSession().createQuery(hql).setParameter(0,uid).executeUpdate();
	}
	
	@Override
	public void deleteManagerRole(long uid, long rid) {
		String hql = "delete ManagerRole ur where ur.manager.id=? and ur.role.id=?";
		this.getSession().createQuery(hql).setParameter(0,uid).setParameter(1, rid).executeUpdate();
	}
}
