package com.phicomm.application.subscriber.service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.IManagerDao;
import com.phicomm.application.subscriber.dao.IRoleDao;
import com.phicomm.application.subscriber.model.CloudException;
import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.Role;
import com.phicomm.application.subscriber.util.SecurityUtil;

@Service("managerService")
public class ManagerService implements IManagerService {
	private IManagerDao managerDao;	
	private IRoleDao roleDao;
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}
	@Inject
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IManagerDao getManagerDao() {
		return managerDao;
	}

	@Resource
	public void setManagerDao(IManagerDao managerDao) {
		this.managerDao = managerDao; 
	}
	
	@Override
	public void add(Manager manager) {
		Manager u = managerDao.loadByUsername(manager.getUsername(),manager.getPassword());
		System.out.println(u);
		//if(u!=null) throw new UserException("用户名和密码不能为空！");
		managerDao.add(manager);
	}
	
	@Override
	public Manager login(String username, String password) {
		Manager m = managerDao.loadByUsername(username);
		if(m==null) throw new CloudException("用户名或者密码不正确");
		try {
			if(!SecurityUtil.md5(username,password).equals(m.getPassword())) {
				throw new CloudException("用户名或者密码不正确");
			}
		} catch (NoSuchAlgorithmException e) {
			throw new CloudException("密码加密失败:"+e.getMessage());
		}
		return m;
	}

	@Override
	public List<Role> listManagerRoles(long id) {
		return managerDao.listManagerRoles(id);
	}
	
	private void addManagerRole(Manager m,long rid) {
		//1、检查角色对象是否存在，如果不存在，就抛出异常
		Role role = roleDao.load(rid);
		if(role==null) throw new CloudException("要添加的用户角色不存在");
		//2、检查用户角色对象是否已经存在，如果存在，就不添加
		managerDao.addManagerRole(m, role);
	}
	

	@Override
	public void add(Manager m, Long[] rids) {
		Manager tu = managerDao.loadByUsername(m.getUsername());
		if(tu!=null)throw new CloudException("添加的用户对象已经存在，不能添加");
		m.setCreateDate(new Date());
		try {
			m.setPassword(SecurityUtil.md5(m.getUsername(),m.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			throw new CloudException("密码加密失败:"+e.getMessage());
		}
		managerDao.add(m);
		//添加角色对象
		for(Long rid:rids) {
			this.addManagerRole(m, rid);
		}
	}
	
	@Override
	public Pager<Manager> findManager() {
		return managerDao.findManager();
	}
	@Override
	public void delete(long id) {
		managerDao.delete(id);
	}
	@Override
	public Manager load(long id) {
		return managerDao.load(id);
	}
	
	@Override
	public List<Long> listManagerRoleIds(long id) {
		return managerDao.listManagerRoleIds(id);
	}
	
	
	@Override
	public void update(Manager m, Long[] rids) {
		//1、获取用户已经存在的组id和角色id
		List<Long> erids = managerDao.listManagerRoleIds(m.getId());
		//2、判断，如果erids中不存在rids就要进行添加
		for(Long rid:rids) {
			if(!erids.contains(rid)) {
				addManagerRole(m, rid);
			}
		}
		//3、进行删除
		for(Long erid:erids) {
			if(!ArrayUtils.contains(rids, erid)) {
				managerDao.deleteManagerRole(m.getId(), erid);
			}
		}
		
	}
}
