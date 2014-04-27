package com.phicomm.application.subscriber.util;

import com.phicomm.application.subscriber.model.MD5UTIL;
import com.phicomm.application.subscriber.model.SendmailWebThreadPool;
import com.phicomm.application.subscriber.model.User;

public class SendMailUtil {
	public static void sendNoActive(User user){
		String host = XMLUtil.getVersionDocument().getRootElement().elementText("host");
		String linkAddress = 
			"<a href=\"http://" + host + "/FX_Cloud/home/"+ user.getId() + "?" + "active=" + MD5UTIL.MD5(String.valueOf(user.getId()))+"\">" + 
			"http://" + host + "/FX_Cloud/home/"+ user.getId() + "?" + "active=" + MD5UTIL.MD5(String.valueOf(user.getId())) + "</a>";
		SendmailWebThreadPool sendmail= SendmailWebThreadPool.getInstance(user.getUsername(),user.getMailAddress(),linkAddress);
		Thread t1=new Thread(sendmail);
		t1.start();
	}

}
