package com.phicomm.application.subscriber.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.IManagerDao;
import com.phicomm.application.subscriber.dao.IRoleDao;
import com.phicomm.application.subscriber.model.CloudException;
import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.Role;

@Service("roleService")
public class RoleService implements IRoleService {
	private IRoleDao roleDao;
	private IManagerDao managerDao;
	
	
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
	@Inject
	public void setManagerDao(IManagerDao managerDao) {
		this.managerDao = managerDao;
	}
	@Override
	public void add(Role role) {
		roleDao.add(role);
	}

	@Override
	public void delete(int id) {
		List<Manager> us = managerDao.listRoleManagers(id);
		if(us!=null&&us.size()>0) throw new CloudException("删除的角色对象中还有用户，不能删除");
		roleDao.delete(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public Role load(int id) {
		return roleDao.load(id);
	}

	@Override
	public List<Role> listRole() {
		return roleDao.listRole();
	}

	@Override
	public void deleteRoleUsers(int rid) {
		roleDao.deleteRoleUsers(rid);
	}

}
