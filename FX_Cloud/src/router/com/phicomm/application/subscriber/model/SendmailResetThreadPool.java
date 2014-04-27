package com.phicomm.application.subscriber.model;

public class SendmailResetThreadPool implements Runnable {
	
	private static String usrname=null;
	private static String Mailaddr=null;
	private static String linkAddress = null;
	
	private static SendmailResetThreadPool sendmailthreadpool=null;
	
	private SendmailResetThreadPool(String usrname1,String Mailaddr1,String linkAddress1){
		usrname=usrname1;
		Mailaddr=Mailaddr1;
		linkAddress=linkAddress1;
	}
    public synchronized static SendmailResetThreadPool getInstance(String usrname1,String Mailaddr1,String linkAddress1){
    	usrname=usrname1;
		Mailaddr=Mailaddr1;
		linkAddress=linkAddress1;
    	if(sendmailthreadpool==null)
    	{
    		
    		sendmailthreadpool=new SendmailResetThreadPool(usrname, Mailaddr,linkAddress);
    		System.out.println("usrname="+usrname+"\t"+"Mailaddr="+Mailaddr+"\t"+"linkAddress="+linkAddress);
    		
    	}
    	
		return sendmailthreadpool;
    	
    }
	
	
	@Override
	public void run() {
		String mailer = "您好!</br>请您链接重置密码，如果超链接不可用，请手动复制链接地址至地址栏谢谢！</br>"+ linkAddress + "</br></br>________________________________________</br>此信是由斐讯网站系统发出，系统不接收回信，因此请勿直接回复。如果有其它疑问，请发邮件至<a href=\"mailto:apps@phicomm.com.cn\">apps@phicomm.com.cn</a>，我们会及时回复您的来信。</br>";
	      try{
	    	  SendMailDemo.send(Mailaddr, "帐号激活", mailer);
	         }
	      catch(Exception ex){
	          System.out.println(ex.toString());
	       }
		
	}

	
	
	
}
