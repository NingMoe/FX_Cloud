package com.phicomm.application.subscriber.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.service.IUserService;
import com.phicomm.application.subscriber.util.XMLUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private IUserService userService;//注入分页功能

	public IUserService getUserService() {
		return userService;
	}
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	//value={"/users","/"}使其默认访问user根目录的时候就是users
	@RequestMapping(value={"/users","/"},method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("pagers", userService.find());	//所有的用户信息
		return "user/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new User());
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated User user,BindingResult br) {
		if(br.hasErrors()) {	//验证的user类，验证结果br
			return "user/add";
		}
		userService.add(user);
		return "redirect:/user/users";
	}
	
	//show显示详细信息根据ID，查询出来的值存入Model
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model) {//传了id参数就要@PathVariable int id
		model.addAttribute(userService.load(id));
		return "user/show";
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute(userService.load(id));
		return "user/update";
	}
	
/*
	@RequestMapping(value="/{id}/update",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Validated User user,BindingResult br,Model model) {
		if(br.hasErrors()) {
		//	model.addAttribute(userService.load(id));
			return "user/update";
		}
		User tu = userService.load(id);
		tu.setAdm2Pass(user.getAdm2Pass());
		tu.setAdmUser2Nm(user.getAdmUser2Nm());
		tu.setMailNum(user.getMailNum());
		tu.setReger2Mail(user.getReger2Mail());
		tu.setReger2Psw(user.getReger2Psw());
		tu.setRegerEn(user.getRegerEn());
		tu.setRegerUserFst(user.getRegerUserFst());
		tu.setRegerUserScd(user.getRegerUserScd());
	
		userService.update(tu);
		return "redirect:/user/users";
	}
	*/
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/user/users";
	}
	

	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(Model model) {
		model.addAttribute("pagers", userService.find());	//所有的用户信息
		return "user/index";
	}
		
	
	@RequestMapping(value="/left",method=RequestMethod.GET)
	public String letf() {
		
		return "user/left";
	}
	
	
	@RequestMapping(value="/top",method=RequestMethod.GET)
	public String top(Model model) {
		String version = XMLUtil.getVersionDocument().getRootElement().elementText("version");
		if(version==null || "".equals(version))
			model.addAttribute("version", "无法获取版本信息");
		else
			model.addAttribute("version", version);
		return "user/top";
	}
	
	@RequestMapping(value="/video_list",method=RequestMethod.GET)
	public String video_list(Model model) {
		model.addAttribute("pagers", userService.find());	//所有的用户信息
		return "user/video_list";
	}
	
	@RequestMapping(value="/video_add",method=RequestMethod.GET)
	public String video_add(Model model) {
		model.addAttribute(new User());
		return "user/video_add";
	}
	
	@RequestMapping(value="/video_add",method=RequestMethod.POST)
	public String video_add(@Validated User user,BindingResult br,Model model) {
		if(br.hasErrors()) {	//验证的user类，验证结果br
			return "user/video_add";
		}
		
		userService.add(user);
		model.addAttribute("pagers", userService.find());	//所有的用户信息
		return "user/video_list";
	}
	
	@RequestMapping(value="/{id}/video_delete",method=RequestMethod.GET)
	public String video_delete(@PathVariable int id,Model model) {
		userService.delete(id);
		model.addAttribute("pagers", userService.find());
		return "user/video_list";
	}
	
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(HttpServletRequest request,Model model) {
	
		model.addAttribute("pagers", userService.search("reger2Mail",request.getParameter("contextfor")));
		return "user/video_list";
	}
	
	@RequestMapping(value="/{id}/video_modify",method=RequestMethod.GET)
	public String viedo_modify(@PathVariable int id,Model model) {
		model.addAttribute(userService.load(id));
		return "user/video_update";
	}
}
