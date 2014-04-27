package com.phicomm.application.subscriber.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.phicomm.application.subscriber.service.IUserService;

@Controller
@SessionAttributes("loginUser")
public class IndexController {
	private IUserService userService;
	
	
	public IUserService getUserService() {
		return userService;
	}

	//ͨ��@Resource���ע��IUserService
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	

	//�˳�����
	@RequestMapping("/logout")
	public String logout(Model model,HttpSession session) {
		model.asMap().remove("loginUser");//modelû��remove��ô��ת��asMap.
		session.invalidate();
		return "redirect:/login";
	}
}
