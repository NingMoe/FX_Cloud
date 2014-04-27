package com.phicomm.application.subscriber.dao;


import java.util.List;








import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.ManagerRole;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.Role;

public interface IManagerDao {
	public void add(Manager manager);
	public Manager loadByUsername(String username,String password);
	public Manager load(long id);
	/**
	 * 根据角色id获取用户列表
	 * @param roleId
	 * @return
	 */
	public List<Role> listManagerRoles(long managerId);
	public List<Manager> listRoleManagers(long roleId);
	public void addManagerRole(Manager m, Role role) ;
	public Manager loadByUsername(String username);
	public ManagerRole loadManagerRole(long managerId, long roleId);
	public Pager<Manager> findManager();
	public void delete(long id);
	public List<Long> listManagerRoleIds(long managerId);
	public void deleteManagerRoles(long uid);
	public void deleteManagerRole(long uid, long rid);
}
