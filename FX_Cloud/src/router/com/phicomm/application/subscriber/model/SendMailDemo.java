package com.phicomm.application.subscriber.model;

import com.phicomm.application.subscriber.util.XMLUtil;



public class SendMailDemo {
	public static void send(String mailto,String theme,String context) {
		String mailServer = XMLUtil.getMailDocument().getRootElement().elementText("Mailserver");
		String mailManager = XMLUtil.getMailDocument().getRootElement().elementText("MailManager");
		String mailPsd = XMLUtil.getMailDocument().getRootElement().elementText("MailManagerpsd");
		String mailUser = XMLUtil.getMailDocument().getRootElement().elementText("MailUser");
		
		SendMail sm=new SendMail(mailServer);
		sm.setNamePass(mailManager,mailPsd);
		sm.setSubject(theme);
		sm.setFrom(mailUser);
		sm.setTo(mailto);

		sm.setBody(context);
		sm.setNeedAuth(true);
		boolean b=sm.setOut();
		if(b){
			System.out.println("\n邮件发送成功!!!!!");
		}
		else{
			System.out.println("邮件发送失败!!!!");
		}
	}
}
