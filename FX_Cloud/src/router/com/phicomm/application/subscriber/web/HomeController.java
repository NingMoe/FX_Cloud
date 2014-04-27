package com.phicomm.application.subscriber.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.phicomm.application.subscriber.model.MD5UTIL;
import com.phicomm.application.subscriber.model.SendmailResetThreadPool;
import com.phicomm.application.subscriber.model.SendmailWebThreadPool;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.UserDto;
import com.phicomm.application.subscriber.service.IUserService;
import com.phicomm.application.subscriber.util.XMLUtil;

@Controller
@SessionAttributes("loginHomeUser")
@RequestMapping("/home")
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	public HomeController() {
		super();
	}

	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = {"/login","/",""},method = RequestMethod.GET)
	public String login(Model model){
		model.addAttribute(new User());
		return "home/login/login";
	}
	
	@RequestMapping(value = {"/login","/",""},method = RequestMethod.POST)
	public String login(@Validated User user,BindingResult br,Model model){
		try{
			if(br.hasErrors()) {
				logger.warn(br.toString());
				return "home/login/login";
			}
			if(user.getMailAddress().trim().equals("") || user.getPassword().trim().equals("")){
				model.addAttribute("errorMessage", "*用户名或密码不能为空");
				return "home/login/login";
			}
			
			logger.info("mailAddress:" + user.getMailAddress());
			logger.info("mailPassword:" + user.getPassword());
			
			java.util.List<User> u = userService.loadByUsernamePsd(user.getMailAddress(), user.getPassword());// 根据post过来的值查询
			if (u.size() == 0) {
				logger.warn("用户名或密码错误");
				model.addAttribute("errorMessage", "*用户名或密码错误");
				return "home/login/login";
			} else if(u.size() > 1){	//邮箱重复
				logger.warn("用户名冲突");
				model.addAttribute("errorMessage", "*用户名冲突");
				return "home/login/login";
			} else if(u.size() == 1 && u.get(0).getIsActive() != 1){
				logger.warn("未激活");
				model.addAttribute("errorMessage", "*未激活，请激活后登录");
				return "home/login/login";
			}
			else {
				logger.info("成功登陆");
				model.addAttribute("loginHomeUser", u.get(0));	
			}		
		}catch(Exception ex){
			logger.error(ex.toString(),ex);
			model.addAttribute("errorMessage", "*" + ex.toString());
			return "home/login/login";
		}
		return "redirect:/home/index";
	}
	
	@RequestMapping(value = "/registration",method = RequestMethod.GET)
	public String registration(Model model){
		model.addAttribute(new User());
		return "home/login/registration";
	}
	
	@RequestMapping(value = "/registration",method = RequestMethod.POST)
	public String registration(@Validated User user,BindingResult br,Model model,String confirm,String area){
		try{
			model.addAttribute("area", area);
			if(br.hasErrors()) {
				logger.warn(br.toString());
				return "home/login/registration";
			}
			if(!confirm.equals(user.getPassword())){
				model.addAttribute("errorPwd", "*两次输入的密码不相同");
				return "home/login/registration";
			}
			if(user.getUsername().trim().equals("")){
				model.addAttribute("errorUsername", "*用户名/姓名不能为空");
				return "home/login/registration";
			}
			
			List<User> u = userService.loadByMail(user.getMailAddress());
			if(u.size() > 0){
				model.addAttribute("errorMail", "*该邮箱已注册");
				return "home/login/registration";
			}
			
			String password = MD5UTIL.MD5(user.getPassword());
			user.setPassword(password);
			user.setProvince(area);
			user.setCreateTime(new Date());
			user.setIsActive(0);
			
			logger.info("MailAddress:" + user.getMailAddress());
			logger.info("Password:" + password);
			logger.info("Username:" + user.getUsername());
			logger.info("Phone:" + user.getPhone());
			logger.info("Province:" + area);
			logger.info("ZipCode:" + user.getZipCode());
			userService.add(user);
			
			String host = XMLUtil.getVersionDocument().getRootElement().elementText("host");
			String linkAddress = 
				"<a href=\"http://" + host + "/FX_Cloud/home/"+ user.getId() + "?" + "active=" + MD5UTIL.MD5(String.valueOf(user.getId()))+"\">" + 
				"http://" + host + "/FX_Cloud/home/"+ user.getId() + "?" + "active=" + MD5UTIL.MD5(String.valueOf(user.getId())) + "</a>";
			SendmailWebThreadPool sendmail= SendmailWebThreadPool.getInstance(user.getUsername(),user.getMailAddress(),linkAddress);
			Thread t1=new Thread(sendmail);
			t1.start();
		}catch(Exception ex) {	
			logger.error("用户注册失败：" + ex.toString());
			model.addAttribute("message", "*用户注册失败" + ex.toString());
			return "home/login/registration";
		}
		return "/home/login/prompt";			
	}
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest req){
		logger.info("访问主页");
		HttpServletRequest hsq = (HttpServletRequest)req;
		User u = (User)hsq.getSession().getAttribute("loginHomeUser");
		if(u != null){
			UserDto ut = userService.loadById(u.getId());
			model.addAttribute("userDto", ut);
		}
		return "home/index/inform";
	}
	
	@RequestMapping(value = "/index/{id}",method = RequestMethod.GET)
	public String index(@PathVariable int id,Model model,User user){
		return "redirect:/home/index/";	
	}
	
	/**
	 * 激活邮箱注册链接接口
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public String index(@PathVariable int id,Model model){
		User user = userService.load(id);
		if(user.getIsActive() == 0){
			user.setIsActive(1);
			userService.update(user);
			model.addAttribute("loginHomeUser", user);
			UserDto u = userService.loadById(id);
			model.addAttribute("userDto", u);
			return "home/login/active";
		}
		return "home/login/activeValidate";	
	}
	
	@RequestMapping(value = "/index/{id}/update",method = RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		User u = userService.load(id);
		model.addAttribute("user", u);
		return "home/index/update";
	}
	
	@RequestMapping(value = "/index/{id}/update",method = RequestMethod.POST)
	public String update(@PathVariable int id,User user,Model model,String area){
		user.setProvince(area);
		if(user.getUsername().trim().equals("")){		
			model.addAttribute("message", "*用户名/姓名不能为空");
			return "home/index/update";
		}
		User u = userService.load(id);
		u.setUsername(user.getUsername());
		u.setProvince(user.getProvince());
		u.setPhone(user.getPhone());
		u.setZipCode(user.getZipCode());
		userService.update(u);
		return "redirect:/home/index/"+ u.getId();
	}
	
	@RequestMapping(value = "/help",method = RequestMethod.GET)
	public String help(){
		return "home/login/help";
	}
//}

	@RequestMapping(value = "/mail",method = RequestMethod.GET)
	public String mail(Model model){
		model.addAttribute(new User());
		return "home/login/mail";
	}
	
	@RequestMapping(value = "/mail",method = RequestMethod.POST)
	public String mail(@Validated User user,BindingResult br,Model model,String confirm,String area){
		try{
			
			
			List<User> u = userService.loadByMail(user.getMailAddress());
			if(u.size() != 1){
				model.addAttribute("errorMail", "*该邮箱不存在");
				return "home/login/mail";
			}
			/*if(u.size() > 1 || (u.size() == 1 && u.get(0).getIsActive() != 1)){
				model.addAttribute("errorMail", "*该邮箱未激活");
				return "home/login/mail";
			}*/
			
			logger.info("MailAddress:" + user.getMailAddress());
			
			String activeCode = new RegisterController().randActiveCode();
			u.get(0).setActiveCode(activeCode);
			userService.update(u.get(0));
			String host = XMLUtil.getVersionDocument().getRootElement().elementText("host");
			String linkAddress = 
				"<a href=\"http://" + host + "/FX_Cloud/home/passwd/"+ u.get(0).getId() + "?" + "updatepwd=" + MD5UTIL.MD5(activeCode)+"\">" + 
				"http://" + host + "/FX_Cloud/home/passwd/"+ u.get(0).getId() + "?" + "updatepwd=" + MD5UTIL.MD5(activeCode) + "</a>";
			SendmailResetThreadPool sendmail= SendmailResetThreadPool.getInstance(u.get(0).getUsername(),u.get(0).getMailAddress(),linkAddress);
			Thread t1=new Thread(sendmail);
			t1.start();
			
		}catch(Exception ex) {	
			logger.error("邮箱验证失败：" + ex.toString());
			model.addAttribute("message", "*邮箱验证失败" + ex.toString());
			return "home/login/mail";
		}
		return "/home/login/promptPasswd";			
	}
	
	/**
	 * 修改密码
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/passwd/{id}",method = RequestMethod.GET)
	public String passwd(@PathVariable int id,Model model,String updatepwd){
		User u = userService.load(id);
		
		if(u.getId() == 0) {
			logger.info("链接错误");
			return "home/login";	
		}
		if(!MD5UTIL.MD5(u.getActiveCode()).equalsIgnoreCase(updatepwd))
			return "home/login/passwdValidate";
		/* 检查 识别号*/
		logger.info("passwd"+id);
		u.setPassword(null);
		model.addAttribute("user", u);
		logger.info("passwd");
		
		return "home/login/passwd";	
	}

	@RequestMapping(value = "/passwd/{id}",method = RequestMethod.POST)
	//public String passwd(@Validated User user,BindingResult br,Model model,String confirm,String area){
	public String passwd(@PathVariable int id,User user,Model model,String confirm){
		//user.setProvince(area);
		User u = userService.load(id);
		
		
		if(!confirm.equals(user.getPassword())){
			model.addAttribute("errorPwd", "*两次输入的密码不相同");
			return "home/login/passwd";
		}

		String password = MD5UTIL.MD5(user.getPassword());
		u.setPassword(password);
		u.setActiveCode("000");
		logger.info("passwd 123");
		
		userService.update(u);
	
		logger.info("passwd 456");
		
		return "home/login/passwdResult";
	}
	

	
}