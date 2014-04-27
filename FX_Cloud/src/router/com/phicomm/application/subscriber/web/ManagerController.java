package com.phicomm.application.subscriber.web;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.phicomm.application.subscriber.auth.AuthClass;
import com.phicomm.application.subscriber.auth.AuthMethod;
import com.phicomm.application.subscriber.dto.ManagerDto;
import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.Role;
import com.phicomm.application.subscriber.model.RoleType;
import com.phicomm.application.subscriber.service.IManagerService;
import com.phicomm.application.subscriber.service.IRoleService;

@Controller
@RequestMapping("/manager")
@AuthClass(value="login")
public class ManagerController {
	
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
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_ADMIN")
	public String add(Model model) {
		model.addAttribute("managerDto",new ManagerDto());
		model.addAttribute("roles",roleService.listRole());
		return "manager/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@AuthMethod(role="ROLE_ADMIN")
	public String add(@Valid ManagerDto managerDto,BindingResult br,Model model) {
		if(br.hasErrors()) {
			model.addAttribute("roles",roleService.listRole());
			return "manager/add";
		}
		managerService.add(managerDto.getManager(), managerDto.getRoleIds());
		return "redirect:/manager/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_ADMIN")
	public String list(Model model) {
		model.addAttribute("datas",managerService.findManager());
		return "manager/list";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_ADMIN")
	public String delete(@PathVariable long id){
		managerService.delete(id);
		return "redirect:/manager/list";
	}
	
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_ADMIN")
	public String update(@PathVariable long id,Model model) {
		Manager m = managerService.load(id);
		model.addAttribute("managerDto",new ManagerDto(m,
				managerService.listManagerRoleIds(id)));
		model.addAttribute("roles",roleService.listRole());
		return "manager/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	@AuthMethod(role="ROLE_ADMIN")
	public String update(@PathVariable long id,@Valid ManagerDto managerDto,BindingResult br,Model model) {
		if(br.hasErrors()) {
			System.out.println(br.hasErrors());
			model.addAttribute("roles",roleService.listRole());
			return "manager/update";
		}
		Manager m = managerService.load(id);
		m.setRealname(managerDto.getRealname());
		m.setDescription(managerDto.getDescription());
		m.setEmployeeID(managerDto.getEmployeeID());
		managerService.update(m, managerDto.getRoleIds());
		return "redirect:/manager/list";
	}
}
