package com.phicomm.application.subscriber.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.SystemContext;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.UserDto;

@Repository("userDao")
public class UserDao extends HibernateDaoSupport implements IUserDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
   public UserDto loadById(long id) {
      String id_sql = "select a.id as id,a.mailAddress as email,a.onlineMac as onlinePhoneMac,"
            +"a.phone as mobelPhone,a.username as userName,a.zipCode as postCode,"
            +"a.time as lastAccess,c.cityName as city from fx_user a left join fx_city c on(a.province=c.cid)"
            +" where a.id=?";
      SQLQuery id_query = this.getSession().createSQLQuery(id_sql);
      return (UserDto)id_query.setResultTransformer(Transformers.aliasToBean(UserDto.class)).setParameter(0, id).uniqueResult();
   }

	@Override
	public void add(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}
	
	//注册的时候查询邮箱是否已经激活
	@Override
	public User searchByMailPsdAct(String mail){
		return (User)this.getSession().createQuery("from User where mailAddress=? and isActive=1").setParameter(0, mail).uniqueResult();

	}

	
	@Override
	public User loadByMailPsdAct(String mail,String psd){
		return (User)this.getSession().createQuery("from User where mailAddress=? and password=MD5(?) and isActive=1").setParameter(0, mail).
				setParameter(1, psd).uniqueResult();

	}
	
	@Override
	public void delete(long id) {
		User user = this.load(id);
		this.getHibernateTemplate().delete(user);
	}

	@Override
	public User load(long id) {
		return this.getHibernateTemplate().load(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		return this.getSession().createQuery("from User").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadbyonlinemac(String onlinemac) {
		return this.getSession().createQuery("from User where onlineMac=?").setParameter(0, onlinemac).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<User> find() {
		int size = SystemContext.getPageSize();
		int offset = SystemContext.getPageOffset();
		Query query = this.getSession().createQuery("from User");
		query.setFirstResult(offset).setMaxResults(size);
		List<User> datas = query.list();
		Pager<User> us = new Pager<User>();
		us.setDatas(datas);
		us.setOffset(offset);
		us.setSize(size);
		long total = (Long)this.getSession()
					.createQuery("select count(*) from User")
					.uniqueResult();
		us.setTotal(total);
	
		return us;
	}
	
	@Override
	public User loadByUsername(String username,String psd) {
		return (User)this.getSession().createQuery("from User where mailAddress=? and password=MD5(?)")
					.setParameter(0, username).setParameter(1, psd).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadByMail(String mail) {
		return this.getSession().createQuery("from User where mailAddress=?")
					.setParameter(0, mail).list();
	}
	
	public User loadByIdMail(long id,String mail) {
		return (User)this.getSession().createQuery("from User where id=? and mailAddress=?")
					.setParameter(0, id).setParameter(1, mail).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadByUsernamePsd(String username,String psd) {
		return this.getSession().createQuery("from User where mailAddress=? and password=MD5(?)").setParameter(0, username).setParameter(1, psd).list();
	}
	
	@Override
	public User loadByMac(String mac) {
		return (User)this.getSession().createQuery("from User where onlineMac=?")
					.setParameter(0, mac).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<User> search(String name,String value) {
		int size = SystemContext.getPageSize();
		int offset = SystemContext.getPageOffset();
		Query query = this.getSession().createQuery("from User where "+name+" like '%"+value+"%'");
		
		query.setFirstResult(offset).setMaxResults(size);
		List<User> datas = query.list();
		Pager<User> us = new Pager<User>();
		us.setDatas(datas);
		us.setOffset(offset);
		us.setSize(size);
		long total = (Long)this.getSession()
					.createQuery("select count(*) from User where "+name+" like '%"+value+"%'")
					.uniqueResult();
		us.setTotal(total);
		return us;
	}
	
	public void transOn() {
			
	}
}
