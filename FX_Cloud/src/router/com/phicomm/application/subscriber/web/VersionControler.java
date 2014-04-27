package com.phicomm.application.subscriber.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phicomm.application.subscriber.util.PropertiesUtil;
import com.phicomm.application.subscriber.util.XMLUtil;

@Controller
@RequestMapping("/version")
public class VersionControler {

	private String outbox;
	private PrintWriter out;

	public VersionControler() {
	}

	@RequestMapping(value="/reloadConfig",method=RequestMethod.GET)
	public void reloadConfig(){
		PropertiesUtil.reload();
	}

	@RequestMapping(value="/output",method=RequestMethod.GET)
	public void output(){
		System.out.println(PropertiesUtil.getIpProp().getProperty("port"));
	}
	
	@RequestMapping(value = {"/verxml","/"})
	public void verxml(HttpServletResponse res) throws IOException {
		 res.setContentType("text/html");
	      res.setCharacterEncoding("utf-8");
	      this.out = res.getWriter();

	      try {
	         this.outbox = XMLUtil.getVersionDocument().getRootElement().elementText("version");
	         System.out.println(this.outbox);     
	         this.out.write(this.outbox);
	      } catch (Exception ex) {
	         this.out.write("获取版本信息失败");
	         ex.getStackTrace();
	      } finally {
	         this.out.flush();
	         this.out.close();
	      }
	      return;
	   }

}
