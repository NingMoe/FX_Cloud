package com.phicomm.application.subscriber.dto;


import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.phicomm.application.subscriber.model.Manager;

public class ManagerDto {
	private long id;
	/**
	 * 用户登录名称
	 */
	private String username;
	/**
	 * 用户登录密码
	 */
	private String password;
	/**
	 * 真是姓名
	 */
	private String realname;
	/**
	 * 工号
	 */
	private String employeeID;
	/**
	 * 角色id
	 */
	private Long[] roleIds;
	
	private String description;
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	@NotEmpty(message="用户名不能为空")
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty(message="用户密码不能为空")
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}





	public Long[] getRoleIds() {
		return roleIds;
	}


	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}




	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getEmployeeID() {
		return employeeID;
	}


	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}


	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	public Manager getManager() {
		Manager m = new Manager();
		m.setId(this.id);
		m.setRealname(realname);
		m.setPassword(password);
		m.setUsername(username);
		m.setDescription(description);
		m.setEmployeeID(employeeID);
		return m;
	}
	

	public ManagerDto(Manager m) {
		this.setId(m.getId());
		this.setDescription(m.getDescription());
		this.setEmployeeID(m.getEmployeeID());
		this.setPassword(m.getPassword());
		this.setRealname(m.getRealname());
		this.setUsername(m.getRealname());
	}
	public ManagerDto(Manager m,Long[] roleIds) {
		this.setId(m.getId());
		this.setRealname(m.getRealname());
		this.setPassword(m.getPassword());
		this.setUsername(m.getUsername());
		this.setDescription(m.getDescription());
		this.setEmployeeID(m.getEmployeeID());
		this.setRoleIds(roleIds);
	}
	public ManagerDto(Manager m,List<Long> roleIds) {
		this.setId(m.getId());
		this.setRealname(m.getRealname());
		this.setPassword(m.getPassword());
		this.setUsername(m.getUsername());
		this.setDescription(m.getDescription());
		this.setEmployeeID(m.getEmployeeID());
		this.setRoleIds(list2Array(roleIds));
	}
	private Long[] list2Array(List<Long> datas) {
		Long[] nums = new Long[datas.size()];
		for(int i=0;i<datas.size();i++) {
			nums[i] = datas.get((int)i);
		}
		return nums;
	}
	public ManagerDto() {
	}
}
