package com.phicomm.application.subscriber.model;

import java.io.BufferedReader;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


//import org.dom4j.Document;
//import org.dom4j.io.SAXReader;
//import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.phicomm.application.subscriber.util.Base64;



/**
 * Servlet implementation class ACSServlet
 */
@WebServlet("/ACSServlet")
public class ACSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ACSServlet() {
        super();
    }

	public RouterLoginStatusReturn processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		
		   System.out.println("Received request.");
	 

	    	RouterLoginStatusReturn rlsr= new RouterLoginStatusReturn();//声明路由器-用户状态类
	       
	 
	        if(request.getContentLength() > 0)
	        {
	            try
	            {
	                BufferedReader reader = request.getReader();
	            	
	                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	                // 获取DocumentBuilder
	                DocumentBuilder xdb = dbf.newDocumentBuilder();
	                   
	                // 接下来我们将文件解析为一个DOM树，得到一个Document对象。
	                Document doc = xdb.parse (new InputSource(reader));
	                
	               // SAXReader saxreader = new SAXReader();
	               // Document doc = saxreader.read(new InputSource(reader));
	                
	                
	                
	                if (doc == null)
	                {
	                    // Error occured
	                    System.out.println("Doc is null!");
	                }
	                else
	                {           	
	                	/*
	                	 * DOM4J解析
	                	 *          ***/
         	
	                	/*
	                	 * 
	                	 * DOM解析
	                	 * 
	                	 */
	                	Element element = (Element) doc.getDocumentElement(); 
	            
	                	//解析各个节点参数
	                	System.out.println("解析系统的参数：--------------------");
	                	
	                	Node Manufacturer =doc.getElementsByTagName("Manufacturer").item(0);
	                	if(Manufacturer.hasChildNodes())
	                	System.out.println("Manufacturer:"+Manufacturer.getFirstChild().getNodeValue().trim());
	                	
	                	Node OUI =doc.getElementsByTagName("OUI").item(0);
	                	if(OUI.hasChildNodes())
	                	System.out.println("OUI:"+OUI.getFirstChild().getNodeValue().trim());
	                	
	                	Node ProductClass=doc.getElementsByTagName("ProductClass").item(0);
	                	if(ProductClass.hasChildNodes())
	                	{
	                		//设备类型
	                		//System.out.println("ProductClass:"+ProductClass.getFirstChild().getNodeValue().trim());
	                		rlsr.setDevcTyp(ProductClass.getFirstChild().getNodeValue().trim());
	                	}
	                	
	                	
	                	Node SerialNumber =doc.getElementsByTagName("SerialNumber").item(0);
	                	if(SerialNumber.hasChildNodes())
	                	{
	                		System.out.println("SerialNumber:"+SerialNumber.getFirstChild().getNodeValue());
	                	    String mac = SerialNumber.getFirstChild().getNodeValue();
	                	    if(this.CheckMac(mac)){
	                	    	//System.out.println("MAC是合法的");
		                	 
	                	    	StringBuilder mac_s = new StringBuilder(""); 	    
		                 	    for(int i = 0; i< mac.length(); i=i+2){
		                 	    	mac_s.append(mac.substring(i, i+2) + ":"); 	    	
		                 	    }
		                 	    mac_s.deleteCharAt( mac_s.length()-1 );
		                 	   System.out.println("mac:"+mac_s.toString());
		                 	   rlsr.setPubMAC(mac_s.toString());	//公用MAC
		                	    
	                	  }else{
	                		//  System.out.println("MAC不是合法的");
	                		  rlsr.setPubMAC(null);	
	                	  }
	                	    
	                	}
	                	
	                	rlsr.EventCode = new ArrayList<String>();
	                	NodeList eventstruct = doc.getElementsByTagName("EventStruct");
	                	for(int i =0;i<eventstruct.getLength();i++){
	                		Node event =eventstruct.item(i).getFirstChild().getNextSibling();
	                		Node CommandKey =event.getNextSibling().getNextSibling();
	                		//eventcode
	                		if(event.hasChildNodes())
	                		{
	                		//	System.out.println(event.getNodeName()+"="+event.getFirstChild().getNodeValue().trim());
	                			rlsr.EventCode.add(event.getFirstChild().getNodeValue().trim());
	                		}
	                		
	                		if(CommandKey.hasChildNodes())
	                			System.out.println(CommandKey.getNodeName()+"="+CommandKey.getFirstChild().getNodeValue().trim());
	                	}
	                	
	                	Node MaxEnvelopes=doc.getElementsByTagName("MaxEnvelopes").item(0);
	                	if(MaxEnvelopes.hasChildNodes())
	                	System.out.println("MaxEnvelopes:"+MaxEnvelopes.getFirstChild().getNodeValue());
	                	
	                	Node CurrentTime =doc.getElementsByTagName("CurrentTime").item(0);
	                	if(CurrentTime.hasChildNodes())
	                	System.out.println("CurrentTime:"+CurrentTime.getFirstChild().getNodeValue());
	                	
	                	Node RetryCount =doc.getElementsByTagName("RetryCount").item(0);
	                	if(RetryCount.hasChildNodes())
	                	System.out.println("RetryCount:"+RetryCount.getFirstChild().getNodeValue());
	                	
	                	System.out.println("系统参数结束——————————————————————————");
	                	
	                	
	                	NodeList nl =  element.getElementsByTagName("ParameterValueStruct"); 
	                	for (int i=0;i < nl.getLength();i++){ 
	                		//System.out.println("length="+nl.getLength());
	                		Node nl2 = nl.item(i).getFirstChild().getNextSibling();
	                		Node nl3 = nl2.getNextSibling().getNextSibling();
	                		String nodename=null;
	                		String nodevalue=null;
	                		
	                		
	                		
	                		if(nl2.hasChildNodes()){
	                			  nodename=nl2.getFirstChild().getNodeValue().trim();
	                		}
	                		
	                		if(nl3.hasChildNodes()){
	                		
	                			nodevalue=nl3.getFirstChild().getNodeValue().trim();
	                			

	                			
	                			//System.out.println("获取设备公共信息——————————————————————————");
	                            /****获取设备公共信息****/
	                            if(nodename.equals("InternetGatewayDevice.DeviceSummary")){
	                              // System.out.println("Devicesummary="+nodevalue);
	                            }
	                            if(nodename.equals("InternetGatewayDevice.DeviceInfo.SpecVersion")){
	                              // System.out.println("SpecVersion="+nodevalue);
	                            }
	                            if(nodename.equals("InternetGatewayDevice.DeviceInfo.HardwareVersion")){
	                               //System.out.println("HardwareVersion="+nodevalue);
	                            	rlsr.setHardVer(nodevalue);
	                            }
	                            if(nodename.equals("InternetGatewayDevice.DeviceInfo.SoftwareVersion")){
	                               //System.out.println("SoftwareVersion="+nodevalue);
	                               rlsr.setDevcVer(nodevalue);
	                            }
	                            if(nodename.equals("InternetGatewayDevice.DeviceInfo.ProvisioningCode")){
	                               //System.out.println("ProvisioningCode="+nodevalue);
	                            }
	                         if(nodename.equals("InternetGatewayDevice.ManagementServer.ConnectionRequestURL")){
	                               //System.out.println("ConnectionRequestURL="+nodevalue);
	                            }
	                            if(nodename.equals("InternetGatewayDevice.ManagementServer.ParameterKey")){
	                              // System.out.println("ParameterKey="+nodevalue);
	                            }
	                			/**获取用户登录状态**/
	                			
	                			if(nodename.equals("InternetGatewayDevice.X_PHICOMM.USERNAME")){
	                				//System.out.println("username="+nodevalue);
	                				rlsr.setAdminUsername(nodevalue);
	                			}
	                			if(nodename.equals("InternetGatewayDevice.X_PHICOMM.PASSWORD")){
	                				System.out.println("password="+nodevalue);
	                				if(nodevalue != null){
	                					rlsr.setAdminPassword(nodevalue);
	                					//String temp1Base64 = Base64.decode64(nodevalue);
	                					//if(!Base64.encode64(temp1Base64).equals(nodevalue)){
	                					//	System.out.println("加密后不相同："+ Base64.encode64(temp1Base64));
	                						//rlsr.setAdminPassword(null);	    //解密后再加密的与接受到的不同，说明未加密            					
	                					//}else{
	                						//System.out.println("加密后相同："+ Base64.encode64(temp1Base64));
	                						//rlsr.setAdminPassword(temp1Base64);
	                					//}
	                				}
	                			}
//	                			if(nodename.equals("InternetGatewayDevice.ManagementServer.ConnectionRequestURL"))
//	                			{
//	                				System.out.println("反向连接URL="+nodevalue);
//	                				rlsr.setWAN_IP(nodevalue);
//	                			}
	                			if(nodename.equals("InternetGatewayDevice.WANDevice.1.WANConnectionDevice.1.WANPPPConnection.1.ExternalIPAddress")){
	                				//wanip
	                				System.out.println("pppoe下wanip="+nodevalue);
	                				if(CheckIp.CheckWanIp(nodevalue)){
	                					//System.out.println("这是合法的ip");
	                					rlsr.setWAN_IP(nodevalue);
	                				}
	                				else{
	                					//System.out.println("这是不合法的ip");
	                					rlsr.setWAN_IP(null);
	                				}
	                			}
	                			if(nodename.equals("InternetGatewayDevice.WANDevice.1.WANConnectionDevice.1.WANIPConnection.1.ExternalIPAddress")){
	                				System.out.println("非PPPOE下wanip="+nodevalue);
	                				if(CheckIp.CheckWanIp(nodevalue)){
	                					//System.out.println("这是合法的ip");
	                					rlsr.setWAN_IP(nodevalue);
	                				}
	                				else{
	                					//System.out.println("这是不合法的ip");
	                					rlsr.setWAN_IP(null);
	                				}
	                			}
	                			//**----------获取用户状态结束-------------------------**/
	                		    //**注册云帐户**/
	                			if(nodename.equals("InternetGatewayDevice.X_PHICOMM.CLOUD.MAILADDRESS")){
	                				System.out.println("cloudmailaddress="+nodevalue);
	                				rlsr.setMAIL_ADDRESS(nodevalue);
	                			}
	                			if(nodename.equals("InternetGatewayDevice.X_PHICOMM.CLOUD.PASSWORD")){
	                				System.out.println("cloudpassworld="+nodevalue);
	                				if(nodevalue != null){
	                					//System.out.println("正在测试：接受到的密码为：" + nodevalue);
	               				
	                					String temp2Base64 = Base64.decode64(nodevalue);
	                					//System.out.println("正在测试：解密后的密码为：" + temp2Base64);
	                					if(!Base64.encode64(temp2Base64).equals(nodevalue)){
	                						//System.out.println("加密后不相同："+ Base64.encode64(temp2Base64));
	                						rlsr.setPASSWORD(null);	    //解密后再加密的与接受到的不同，说明未加密            					
	                					}else{
	                						rlsr.setPASSWORD(temp2Base64);
	                					}
	                					
	                				}
	                			}
	                			if(nodename.equals("InternetGatewayDevice.X_PHICOMM.CLOUD.USERNAME")){
	                				System.out.println("cloudusername="+nodevalue);
	                				rlsr.setUSERNAME(nodevalue);
	                			}
	                			if(nodename.equals("InternetGatewayDevice.X_PHICOMM.CLOUD.MOBILE")){
	                				System.out.println("cloudmoblie="+nodevalue);
	                				if(this.CheckNumber(nodevalue)){	//手机号码必须数字
	                					//System.out.println("这是合法的号码");
	                					rlsr.setMP_NUMBER(nodevalue);
	                				}
	                				else{
	                					//System.out.println("这不是合法的号码");
	                					rlsr.setMP_NUMBER(null);
	                				}
	                			}
	                			if(nodename.equals("InternetGatewayDevice.X_PHICOMM.CLOUD.PROVINCE")){
	                				System.out.println("cloudprovince="+nodevalue);
	                				rlsr.setPROVINCE(nodevalue);
	                			}
	                			if(nodename.equals("InternetGatewayDevice.X_PHICOMM.CLOUD.ZIPCODE")){
	                				System.out.println("cloudzipcode="+nodevalue);
	                				rlsr.setZIP_CODE(nodevalue);
	                			}
	                			/**-----------------注册云帐号结束----------------------------**/
	                			
	                			/***------------------云帐号的登录及登出***/
	                			if(nodename.equals("InternetGatewayDevice.X_PHICOMM.ACTSTATUS")){
	                				System.out.println("loginstatus="+nodevalue);
	                				if(nodevalue.equals("0") || nodevalue.equals("1")){
	                					//System.out.println("激活状态合法");
	                					////logger.error("激活状态合法");
	                					rlsr.setACT_STATUS(nodevalue);
	                				}else{
	                					//System.out.println("激活状态不合法");
	                					rlsr.setACT_STATUS(null);
	                				}
	                			}
	                			
	                			/**-------------本地管理密码变更同上-------------***/
	                			
	                			
	                			
	                			
	                		}
	                		
	    	                	
	                	} 
	                	
	                }
	               
	            }
	            catch(Exception e)
	            {
	                System.out.println(e);
	            }
	        }
	 
	        System.out.println("____________________________");
	 
	        response.setContentType("text/xml"); // Need this to prevent Apache SOAP from gacking
	        return rlsr;
	}


	
	private boolean CheckNumber(String num){
		
		if(!num.matches("[0-9]*")) 
		{ 
			return false; 
		} 
		return true;
	}
	
	
	private boolean CheckMac(String mac){
		
		if(!mac.matches("[0-9|A-Z|a-z]{12}")) 
		{ 
			return false; 
		} 
		return true;
	}
	
	
	
	

}
