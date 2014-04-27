package com.phicomm.application.subscriber.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phicomm.application.subscriber.model.News;
import com.phicomm.application.subscriber.service.INewsService;


@Controller
@RequestMapping("/notice")
//@SessionAttributes("notice")
public class NoticeControler {
	private INewsService newsService;	
	private SimpleDateFormat df;
	public INewsService getNewsService() {
		return newsService;
	}

	@Resource
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	@RequestMapping(value={"/notice","/"},method=RequestMethod.GET)
	public String list(Model model) {
		return "notice/notice_list";
	}

	@RequestMapping(value="/notice_list",method=RequestMethod.GET)
	public String notice_list(Model model) {
		model.addAttribute("pagers", newsService.find());	
		return "notice/notice_list";
	}
	


	@RequestMapping(value="/notice_add",method=RequestMethod.GET)
	public String notice_add(Model model) {
		model.addAttribute(new News());
		return "notice/notice_add";
	}
	
	@RequestMapping(value="/notice_add",method=RequestMethod.POST)
	public String notice_add(@Validated News news,BindingResult br,HttpServletRequest req ,String startTime1
			,String endTime1,Model model,int choose) {
		System.out.println("choose::"+choose);
		String temp="";
		if(br.hasErrors()) {	
			return "notice/notice_add";
		}
		
		if(choose==0){
			//版本升级
			if(news.getRouterTyp().trim().equals("") || news.getRouterVer().trim().equals("")){
				model.addAttribute("messageVerTyp","设备型号或版本不能为空");
				return "notice/notice_add";
			}
			
			News n = new News();
			n = newsService.loadByVerAndTyp(news.getRouterVer(),news.getRouterTyp());
			if(n != null){
				model.addAttribute("messageWarn","此设备的版本已存在!");
				return "notice/notice_add";
			}
			
			temp = "你的"+news.getRouterTyp()+"路由器，已经发布"+news.getRouterVer()+"版本，如有需要请前往"+news.getUrl();
			news.setNewsTheme("设备版本升级");
			news.setNewsContext(temp);
		}else{
			if(news.getNewsTheme().trim().equals("") || news.getNewsContext().trim().equals("")){
				model.addAttribute("messageTheme","消息主题和内容不能为空");
				return "notice/notice_add";
			}
				news.setRouterVer("");
				news.setRouterTyp("");
		}
		news.setIpAddr(req.getRemoteAddr());
		newsService.add(news);
		model.addAttribute("pagers", newsService.find());	
		return "notice/notice_list";
	}
	
	
	@RequestMapping(value="/{id}/notice_mod",method=RequestMethod.GET)
	public String notice_mod(@PathVariable int id,Model model) {
		model.addAttribute(newsService.load(id));
		return "notice/notice_mod";
	}
	
	@RequestMapping(value="/{id}/notice_mod",method=RequestMethod.POST)
	public String notice_mod(@Validated News news,BindingResult br,Model model,HttpServletRequest req) {
		String temp="";
		if(news.getRouterVer() == null || "".equals(news.getRouterVer())){
			//消息推送
			news.setRouterVer("");
			news.setRouterTyp("");
			
		}else{
			News n = new News();
			n = newsService.loadByVerAndTyp(news.getRouterVer(),news.getRouterTyp(),news.getId());
			if(n != null){
				model.addAttribute("message", "版本号和设备型号不能重复");
				return "notice/notice_mod";
			}
			
			temp = "你的"+news.getRouterTyp()+"路由器，已经发布"+news.getRouterVer()+"版本，如有需要请前往"+news.getUrl();
			news.setNewsTheme("设备版本升级");
			news.setNewsContext(temp);
		}
		try{
			this.df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = this.df.format(new Date());
			news.setTime(Timestamp.valueOf(time));
			news.setIpAddr(req.getRemoteAddr());
			newsService.update(news);
		}catch(Exception ex){
			model.addAttribute("message", "更新失败");
			return "notice/notice_mod";
		}
		model.addAttribute("pagers", newsService.find());	
		return "notice/notice_list";
	}
	
	
	
	@RequestMapping(value="/{id}/notice_delete",method=RequestMethod.GET)
	public String notice_delete(@PathVariable int id,Model model) {
		newsService.delete(id);
		model.addAttribute("pagers", newsService.find());
		return "redirect:/notice/notice_list";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String notice_search(Model model) {

		model.addAttribute("pagers", newsService.find());	
		return "notice/notice_list";
	}
	
	

	
	
}
