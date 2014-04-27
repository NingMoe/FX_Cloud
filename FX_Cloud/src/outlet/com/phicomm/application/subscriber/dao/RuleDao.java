package com.phicomm.application.subscriber.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.Outlet;
import com.phicomm.application.subscriber.model.Rule;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.CloudException;
import com.phicomm.application.subscriber.model.UserOutletRule;

@SuppressWarnings("unchecked")
@Repository("ruleDao")
public class RuleDao extends HibernateDaoSupport implements IRuleDao {
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	@Override
	public void add(Rule rule) {
		this.getSession().save(rule);
	}

	@Override
	public void delete(long id) {
		Rule rule = this.load(id);
		if(rule == null) throw new CloudException("要删除的规则不存在");
		this.getSession().delete(rule);
	}

	@Override
	public void update(Rule rule) {
		this.getSession().update(rule);
	}

	@Override
	public Rule load(long id) {
		return (Rule)this.getSession().load(Rule.class, id);
	}

	@Override
	public List<Rule> list() {
		return this.getSession().createQuery("from Rule").list();
	}

	@Override
	public List<Rule> listUsrOtlRules(long uid, long oid) {
		return this.getSession().createQuery("select uor.rule from UserOutletRule uor where uor.outlet.id=? and uor.user.id=?")
				.setParameter(0, oid).setParameter(1, uid).list();
	}
	@Override
	public void addUserOutletRule(User user, Outlet outlet, Rule rule) {
		Long count = this.CountUsrOtlRuls(user.getId(),outlet.getId());
		if(count != null && count >=2) throw new CloudException("每个用户每个插座最多两条规则");
		UserOutletRule uor = new UserOutletRule();
		uor.setUser(user);
		uor.setRule(rule);
		this.getSession().save(uor);
	}
	@Override
	public Long CountUsrOtlRuls(long uid, long oid) {
		return (Long)this.getSession().createQuery("select count(*) from UserOutletRule uor where uor.user.id=? and uor.outlet.id=?")
				.setParameter(0, uid).setParameter(1, oid).uniqueResult();
	}
	@Override
	public UserOutletRule loadUsrOtlRul(long uid, long oid, long rid) {
		return (UserOutletRule)this.getSession().createQuery("select count(*) from UserOutletRule uor where uor.user.id=? and uor.outlet.id=? and uor.rule.id=?")
				.setParameter(0, uid).setParameter(1, oid).setParameter(2, rid).uniqueResult();
	}
	@Override
	public void deleteUsrOtlRul(long uid, long oid, long rid) {
		UserOutletRule uor = this.loadUsrOtlRul(uid,oid,rid);
		if(uor == null) throw new CloudException("要删除的规则关系不存在");
		this.getSession().delete(uor);
	}
	
	@Override
	public void addBatch(final List<Rule> rules) throws SQLException {
		this.getSession().doWork(new Work() {
			@Override
			public void execute(Connection con) throws SQLException {
				String userSql = "insert into cloud_rule(rul_creater, rul_interval,is_active,is_repeat, rul_name, rul_orders, rul_starts, rul_status,otl_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = null;
					ps = con.prepareStatement(userSql);
					con.setAutoCommit(false);
					for(int i=0;i<rules.size();i++) {
						ps.setString(1, rules.get(i).getCreater());
						ps.setString(2, rules.get(i).getInterval());
						ps.setLong(3, rules.get(i).getIsActive());
						ps.setByte(4, rules.get(i).getIsRepeat());
						ps.setString(5, rules.get(i).getName());
						ps.setInt(6, rules.get(i).getOrders());
						ps.setTime(7, rules.get(i).getStarts());
						ps.setByte(8, rules.get(i).getStatus());
						ps.setLong(9, rules.get(i).getOutlet().getId());
						ps.addBatch();
					    if(i%50==0){
					    	ps.executeBatch();
					    	ps.clearBatch();
					    	con.commit();
					    }
					}
					ps.executeBatch();
					ps.clearBatch();
					con.commit();
			}
		});
		
		
		/*for(int i=0;i<rules.size();i++) {
		    this.getSession().save(rules.get(i));
		    if(i%50==0){
		    	this.getSession().flush();
		    	this.getSession().clear();
		    }
		}*/
	}
	@Override
	public void deleteByOtl(long otlId) {
		this.getSession().createQuery("delete Rule r where r.outlet.id=?").setParameter(0, otlId)
						.executeUpdate();
	}

}
