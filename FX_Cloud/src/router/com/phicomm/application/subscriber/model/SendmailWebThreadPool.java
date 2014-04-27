package com.phicomm.application.subscriber.model;

public class SendmailWebThreadPool implements Runnable {
	
	private static String usrname=null;
	private static String Mailaddr=null;
	private static String linkAddress = null;
	
	
	private static SendmailWebThreadPool sendmailthreadpool=null;
	
	private SendmailWebThreadPool(String usrname1,String Mailaddr1,String linkAddress1){
		usrname=usrname1;
		Mailaddr=Mailaddr1;
		linkAddress=linkAddress1;
	}
    public synchronized static SendmailWebThreadPool getInstance(String usrname1,String Mailaddr1,String linkAddress1){
    	usrname=usrname1;
		Mailaddr=Mailaddr1;
		linkAddress=linkAddress1;
    	if(sendmailthreadpool==null)
    	{
    		
    		sendmailthreadpool=new SendmailWebThreadPool(usrname, Mailaddr,linkAddress);
    		System.out.println("usrname="+usrname+"\t"+"Mailaddr="+Mailaddr+"\t"+"linkAddress="+linkAddress);
    		
    	}
    	
		return sendmailthreadpool;
    	
    }
	
	@Override
	public void run() {
		String mailer = usrname+"，您好!</br>非常感谢您选择斐讯产品，并注册成为斐讯（<a href=\"http://www.phicomm.com\">http://www.phicomm.com/cn/</a>）会员</br>【产品信息描述】</br>再次欢迎您加入斐讯家园！希望斐讯产品和服务能够为您的工作和生活带来更多精彩丰富的IT资讯！</br>请点击下面链接进行激活</br>"+ linkAddress + "</br></br>________________________________________</br>此信是由斐讯网站系统发出，系统不接收回信，因此请勿直接回复。如果有其它疑问，请发邮件至<a href=\"mailto:apps@phicomm.com.cn\">apps@phicomm.com.cn</a>，我们会及时回复您的来信。</br>";
	      try{
	    	  SendMailDemo.send(Mailaddr, "注册成功", mailer);
	         }
	      catch(Exception ex){
	          System.out.println(ex.toString());
	       }
	}
}
