package com.phicomm.application.subscriber.dao;

import java.sql.SQLException;
import java.util.List;

import com.phicomm.application.subscriber.model.Outlet;
import com.phicomm.application.subscriber.model.Rule;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.UserOutletRule;

public interface IRuleDao {
	public void add(Rule rule);
	public void addBatch(List<Rule> rules) throws SQLException;
	public void delete(long id);
	public void deleteByOtl(long otlId);
	public void update(Rule rule);
	public Rule load(long id);
	/**
	 * 根据2个id找到规则关系的数量
	 * @param uid
	 * @param oid
	 * @param rid
	 * @return
	 */
	public Long CountUsrOtlRuls(long uid,long oid);
	/**
	 * 根据三个id找到唯一的规则关系
	 * @param uid
	 * @param oid
	 * @param rid
	 * @return
	 */
	public UserOutletRule loadUsrOtlRul(long uid,long oid,long rid);
	public List<Rule> list();
	/**
	 * 根据用户和插座找到所有对应规则
	 * @param uid
	 * @param oid
	 * @return
	 */
	public List<Rule> listUsrOtlRules(long uid,long oid); 
	/**
	 * 添加规则的关系表
	 * @param user
	 * @param outlet
	 * @param rule
	 */
	public void addUserOutletRule(User user,Outlet outlet,Rule rule);
	
	/**
	 * 删除规则关系
	 * @param uid
	 * @param oid
	 * @param rid
	 */
	public void deleteUsrOtlRul(long uid,long oid,long rid);
}
