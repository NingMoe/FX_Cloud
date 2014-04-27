package com.phicomm.application.subscriber.web;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phicomm.application.subscriber.model.MD5UTIL;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.service.IUserService;

@Controller
@RequestMapping("/register")
public class ActiveController {
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String register(@PathVariable int id,String activecode) {
		User user = userService.load(id);
		if(user != null){
			//当用户点击失效链接后，将被定向到页面提示‘该链接已失效或用户已激活’
			//1.该激活链接对应的用户被成功激活
			if(user.getIsActive() == 1 || user.getIsActive() == 2){
				return "register/activeValidate";
			}
			//2.该用户再次获得新的激活码和邮件
			if(user.getActiveCode()!=null && !"".equals(user.getActiveCode())
					&& !activecode.equals(MD5UTIL.MD5(user.getActiveCode().trim()))){
				return "register/activeValidate";
			}
		}
		return "register/activation";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public String register(@PathVariable int id,Model model,String activate) {
		if(activate.trim().equals("")){
			model.addAttribute("error", "请输入激活码");
			return "register/activation";
		}
		User u = userService.load(id);
		if(u == null) {
			model.addAttribute("error", "该帐号已过期");
			return "register/activation";
		}
		if(u.getActiveCode() == null){
			model.addAttribute("error", "您选择的帐号不正确");
			return "register/activation";
		}
		if(!u.getActiveCode().equals(activate)){
			model.addAttribute("error", "您输入的激活码不正确");
			return "register/activation";
		}
		List<User> ul = userService.loadByMail(u.getMailAddress());
		for(User user :ul){
			if(user.getId() != u.getId()){				
				user.setMailAddress(new Date().getTime() + "." + user.getMailAddress());
				user.setIsActive(2);
				userService.update(user);
			}
		}
		u.setIsActive(1);;
		userService.update(u);
		return "redirect:/register/activeSuccess";
	}
	
	@RequestMapping(value="/activeSuccess",method=RequestMethod.GET)
	public String registed() {
		return "register/activeSuccess";
	}
	
}
