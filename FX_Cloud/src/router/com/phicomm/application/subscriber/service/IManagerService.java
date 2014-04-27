package com.phicomm.application.subscriber.service;

import java.util.List;

import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.Role;


public interface IManagerService {
	public void add(Manager manager);
	public Manager login(String username,String password);
	public List<Role> listManagerRoles(long id);
	public void add(Manager m, Long[] rids);
	public Pager<Manager> findManager() ;
	public void delete(long id);
	public Manager load(long id);
	public List<Long> listManagerRoleIds(long id);
	public void update(Manager m, Long[] rids);
}
