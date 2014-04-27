package com.phicomm.application.subscriber.dao;

import java.util.List;

import com.phicomm.application.subscriber.model.Role;


public interface IRoleDao extends IBaseDao<Role> {
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
}
