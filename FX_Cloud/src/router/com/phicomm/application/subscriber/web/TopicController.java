package com.phicomm.application.subscriber.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.phicomm.application.subscriber.auth.AuthClass;
import com.phicomm.application.subscriber.auth.AuthMethod;
import com.phicomm.application.subscriber.dto.AjaxObj;
import com.phicomm.application.subscriber.dto.TopicDto;
import com.phicomm.application.subscriber.model.Attachment;
import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.SystemContext;
import com.phicomm.application.subscriber.model.Topic;
import com.phicomm.application.subscriber.service.IAttachmentService;
import com.phicomm.application.subscriber.service.ITopicService;
import com.phicomm.application.subscriber.util.JsonUtil;

@Controller
@RequestMapping("/news")
@AuthClass(value="login")
public class TopicController {
	private ITopicService topicService;
	private IAttachmentService attachmentService;
	private final static List<String> imgTypes = Arrays.asList("jpg","jpeg","gif","png");
	
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}
	@Inject
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}


	public ITopicService getTopicService() {
		return topicService;
	}
	@Inject
	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}
	
	private void initList(String con,Model model,HttpSession session) {
		SystemContext.setSort("t.createDate");
		SystemContext.setOrder("desc");
		//boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		//if(isAdmin) {
			model.addAttribute("datas",topicService.searchTopic( con));
		//} else {
		//	User loginUser = (User)session.getAttribute("loginUser");
			//model.addAttribute("datas", topicService.find(loginUser.getId(),cid, con, status));
		//}
		if(con==null) con="";
		SystemContext.removeOrder();
		SystemContext.removeSort();
		model.addAttribute("con",con);
	}

	@RequestMapping("/list")
	@AuthMethod(role="base")
	public String auditList(@RequestParam(required=false) String con,Model model,HttpSession session) {
		initList(con, model, session);
		return "news/list";
	}
	
	
	@RequestMapping(value="/firstAudit/{id}",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_AUDITOR1")
	public String auditFirstStatus(@PathVariable int id,Model model) {
		model.addAttribute(topicService.load(id));
		model.addAttribute("audit",topicService.listAudit((long)id));
		return "news/audit1";
	}
	
	@RequestMapping(value="/firstAudit/{id}",method=RequestMethod.POST)
	@AuthMethod(role="ROLE_AUDITOR1")
	public String auditFirstStatus(int id,String auditAdvise,int auditstatus,HttpSession session) {
		Manager user = (Manager)session.getAttribute("loginUser");
		if(user ==null) return "manager/login";
		topicService.updateStatus(id,auditAdvise,user,auditstatus);
		return "redirect:/news/list";
	}
	
	@RequestMapping(value="/secondAudit/{id}",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_AUDITOR2")
	public String auditSecondStatus(@PathVariable int id,Model model) {
		model.addAttribute(topicService.load(id));
		model.addAttribute("audit",topicService.listAudit((long)id));
		return "news/audit2";
	}
	
	@RequestMapping(value="/secondAudit/{id}",method=RequestMethod.POST)
	@AuthMethod(role="ROLE_AUDITOR2")
	public String auditSecondStatus(int id,String auditAdvise,int auditstatus,HttpSession session) {
		Manager user = (Manager)session.getAttribute("loginUser");
		if(user ==null) return "manager/login";
		topicService.updateStatus(id,auditAdvise,user,auditstatus);
		return "redirect:/news/list";
	}
	
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		topicService.updateDelete(id);
		return "redirect:/news/list";
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_PUBLISH")
	public String add(Model model) {
		Topic t = new Topic();
		TopicDto td = new TopicDto(t);
		model.addAttribute("topicDto",td);
		return "news/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@AuthMethod(role="ROLE_PUBLISH")
	public String add(@Validated TopicDto topicDto,BindingResult br,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "news/add";
		}
		Topic t = topicDto.getTopic();
		Manager loginUser = (Manager)session.getAttribute("loginUser");
		topicService.add(t, loginUser.getId(),aids);
		return "redirect:/news/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_PUBLISH")
	public String update(@PathVariable int id,Model model) {
		Topic topic = topicService.load(id);
		model.addAttribute(topic);
		model.addAttribute("range",topicService.loadCity(topic.getPublishRange()));
		model.addAttribute("atts",attachmentService.listByTopic(id));
		return "news/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	@AuthMethod(role="ROLE_PUBLISH")
	public String update(@PathVariable int id,@Validated TopicDto topicDto,BindingResult br,String[]aks,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "topic/update";
		}
		Topic tt = topicService.load(id);
		Topic t = topicDto.getTopic();
		tt.setContent(t.getContent());
		tt.setPublishStartDate(t.getPublishStartDate());
		tt.setPublishEndDate(t.getPublishEndDate());
		tt.setPublishRange(t.getPublishRange());
		tt.setPublishRate(t.getPublishRate());
		tt.setStatus(t.getStatus());
		tt.setTitle(t.getTitle());
		topicService.update(tt,aids);
		return "common/addSuc";
	}
	
	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		Topic topic = topicService.load(id);
		model.addAttribute(topic);
		model.addAttribute("publishRange",topicService.loadCity(topic.getPublishRange()));
		model.addAttribute("atts",attachmentService.listByTopic(id));
		return "news/show";
	}
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)//返回的是json类型的值，而uploadify只能接受字符串
	public void upload(MultipartFile attach,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain;charset=utf-8");
		AjaxObj ao = null;
		try {
			Attachment att = new Attachment();
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());
			System.out.println(ext);
			att.setIsAttach(0);
			if(imgTypes.contains(ext)) att.setIsImg(1);
			else att.setIsImg(0);
			att.setIsIndexPic(0);
			att.setNewName(String.valueOf(new Date().getTime())+"."+ext);
			att.setOldName(FilenameUtils.getBaseName(attach.getOriginalFilename()));
			att.setSuffix(ext);
			att.setType(attach.getContentType());
			att.setTopic(null);
			att.setSize(attach.getSize());
			attachmentService.add(att, attach.getInputStream());
			ao = new AjaxObj(1,null,att);
		} catch (IOException e) {
			ao = new AjaxObj(0,e.getMessage());
		}
		resp.getWriter().write(JsonUtil.getInstance().obj2json(ao));
	}
	
}
