package com.phicomm.application.subscriber.web;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.phicomm.application.subscriber.auth.AuthClass;
import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.Role;
import com.phicomm.application.subscriber.model.RoleType;
import com.phicomm.application.subscriber.service.IManagerService;
import com.phicomm.application.subscriber.service.IRoleService;

@Controller
@SessionAttributes("loginUser")
@RequestMapping("/admin")
@AuthClass(value="login")
public class LoginAdminController {
	
	private IManagerService managerService;
	private IRoleService roleService;
	
	public IRoleService getRoleService() {
		return roleService;
	}
	@Inject
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public IManagerService getManagerService() {
		return managerService;
	}	
	@Resource
	public void setManagerService(IManagerService managerService) {
		this.managerService = managerService;
	}
	//用户登录
	@RequestMapping(value={"/login","/"},method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute(new Manager());
		return "manager/login";
	}

	@RequestMapping(value={"/login","/"},method=RequestMethod.POST)
	public String login(String username,String password,Model model,@Validated Manager manager,BindingResult br,HttpSession session) {
		if(br.hasErrors()) {
			return "manager/login";
		}
		Manager m = managerService.login(username, password);
			
		if(m == null){
			model.addAttribute("message", "用户名或密码错误！");
			return "admin/login";
		}else{
			model.addAttribute("loginUser", m);	
			List<Role> rs = managerService.listManagerRoles(m.getId()); //获取登入账号的所有角色
			boolean isAdmin = isRole(rs,RoleType.ROLE_ADMIN);	//是不是管理员
			session.setAttribute("isAdmin", isAdmin);
			if(!isAdmin) {
				session.setAttribute("allActions", getAllActions(rs, session));	//如果不是管理员，那么将其所有的角色写入SET
				session.setAttribute("isAudit1", isRole(rs,RoleType.ROLE_AUDITOR1));
				session.setAttribute("isAudit2", isRole(rs,RoleType.ROLE_AUDITOR2));
				session.setAttribute("isPublish", isRole(rs,RoleType.ROLE_PUBLISH));
			}
			CloudSessionContext.addSessoin(session);
			return "redirect:/user/test";
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private Set<String> getAllActions(List<Role> rs,HttpSession session) {
		Set<String> actions = new HashSet<String>();
		Map<String,Set<String>> allAuths = (Map<String,Set<String>>)session.getServletContext().getAttribute("allAuths");
		actions.addAll(allAuths.get("base"));
		for(Role r:rs) {
			if(r.getRoleType()==RoleType.ROLE_ADMIN) continue;
			actions.addAll(allAuths.get(r.getRoleType().name()));
		}
		return actions;
	}
	
	private boolean isRole(List<Role> rs,RoleType rt) {
		for(Role r:rs) {
			if(r.getRoleType()==rt) return true;
		}
		return false;
	}
	
	@RequestMapping("/logout")
	public String logout(Model model,HttpSession session) {
		model.asMap().remove("loginUser");
		session.invalidate();
		return "redirect:/admin/login";
	}	
	
}
