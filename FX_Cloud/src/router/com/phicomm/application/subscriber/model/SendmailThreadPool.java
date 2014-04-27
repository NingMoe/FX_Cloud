package com.phicomm.application.subscriber.model;

import com.phicomm.application.subscriber.util.XMLUtil;

public class SendmailThreadPool implements Runnable {
	
	private static String usrname=null;
	private static String Mailaddr=null;
	
	
	private static SendmailThreadPool sendmailthreadpool=null;
	
	private SendmailThreadPool(String usrname1,String Mailaddr1){
		usrname=usrname1;
		Mailaddr=Mailaddr1;
	}
    public synchronized static SendmailThreadPool getInstance(String usrname1,String Mailaddr1){
    	
    	usrname=usrname1;
    	Mailaddr=Mailaddr1;
    	System.out.println("usrname="+usrname+"\t"+"Mailaddr="+Mailaddr);
    	
    	if(sendmailthreadpool==null)
    	{
    		
    		sendmailthreadpool=new SendmailThreadPool(usrname, Mailaddr);
    		System.out.println("usrname="+usrname+"\t"+"Mailaddr="+Mailaddr);
    		
    	}
    	
		return sendmailthreadpool;
    	
    }
	
	
	@Override
	public void run() {
		String host = XMLUtil.getVersionDocument().getRootElement().elementText("host");
		String mailer = usrname+"，您好!</br>非常感谢您选择斐讯产品，并注册成为"
				+ "斐讯（<a href=\"http://www.phicomm.com\">http://www.phicomm.com/cn/</a>）会员，"
				+ "请访问链接（<a href=\"http://" + host + "/FX_Cloud/home/help/\">http://" + host + "/FX_Cloud/home/help/</a>）完成后续激活操作"
				+ "</br>【产品信息描述】</br>再次欢迎您加入斐讯家园！希望斐讯产品和服务能够为您的工作和"
				+ "生活带来更多精彩丰富的IT资讯！</br></br>________________________________________</br>"
				+ "此信是由斐讯网站系统发出，系统不接收回信，因此请勿直接回复。如果有其它疑问，"
				+ "请发邮件至<a href=\"mailto:apps@phicomm.com.cn\">apps@phicomm.com.cn</a>，"
				+ "我们会及时回复您的来信。</br>";
	      try{
	    	  SendMailDemo.send(Mailaddr, "注册成功", mailer);
	         }
	      catch(Exception ex){
	          System.out.println(ex.toString());
	       }
		
	}
}
