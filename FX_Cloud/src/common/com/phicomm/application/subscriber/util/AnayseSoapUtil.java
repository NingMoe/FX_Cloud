package com.phicomm.application.subscriber.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.phicomm.application.subscriber.model.OutletSoapAccept;

/**
 * 通过DOM4J解析SOAP包，并返回OutletSoapAccept对象
 * @author boke
 *
 */
public class AnayseSoapUtil {
	@SuppressWarnings("unchecked")
	public static OutletSoapAccept run(HttpServletRequest req) throws DocumentException, IOException{
		OutletSoapAccept osa= new OutletSoapAccept();
		List<String> eventCode = new ArrayList<String>();
		List<String> rules = new ArrayList<String>();
		SAXReader reader = new SAXReader();
		Document d = reader.read(req.getInputStream());
		Element root = d.getRootElement();
		List<Element> eles = root.selectNodes("//*");
		for(Element e:eles){
			if(e.getName().equals("DeviceId")){
				String mac = e.elementText("SerialNumber");
         	    if(CheckUtil.CheckMac(mac)){
         	    	osa.setOtlMac(mac);	
         	    }else if(CheckUtil.Check12Mac(mac)){
	         		StringBuilder mac_s = new StringBuilder(); 	    
	           	    for(int i = 0; i< mac.length(); i=i+2){
	           	    	mac_s.append(mac.substring(i, i+2) + ":"); 	    	
	           	    }
	           	    mac_s.deleteCharAt( mac_s.length()-1 );
	           	    osa.setOtlMac(mac_s.toString());	
         	    }else{
         	    	osa.setOtlMac(null);	
         	    }
         	   osa.setOtlVer(e.elementText("ProductClass").trim());
         	   osa.setOtlPid(e.elementText("PID").trim());
			}
			if(e.getName().equals("EventStruct")){
				List<Element> ecs = e.selectNodes("EventCode");
				for(Element ec:ecs){
					eventCode.add(ec.getText().trim());
				}
				osa.setEventCode(eventCode);
			}
			if(e.getName().equals("ParameterValueStruct")){
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.DeviceInfo.SoftwareVersion"))
					osa.setSwVer(e.elementText("Value").trim());
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.DeviceInfo.HardwareVersion"))
					osa.setHwVer(e.elementText("Value").trim());
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.Outletname"))
					osa.setOtlAlias(e.elementText("Value").trim());
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.Note"))
					osa.setOtlRemark(e.elementText("Value").trim());
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.DeviceStatue")){
					if(!"".equals(e.elementText("Value").trim()))
						osa.setOtlStatus(Byte.valueOf(e.elementText("Value").trim()));
				}
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.LockFlag")){
					if(!"".equals(e.elementText("Value").trim()))
						osa.setIsLock(Byte.valueOf(e.elementText("Value").trim()));
				}
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.Password") && e.elementText("Value").trim() != null){
					String temp2Base64 = Base64.decode64(e.elementText("Value").trim());
					if(!Base64.encode64(temp2Base64).equals(e.elementText("Value").trim())){
						osa.setOtlPsd(null);	    //解密后再加密的与接受到的不同，说明未加密            					
					}else{
						osa.setOtlPsd(temp2Base64);
					}
				}
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.PhoneIMEI"))
					osa.setPhoImei(e.elementText("Value").trim());
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.Current")){
					if(!"".equals(e.elementText("Value").trim()))
						osa.setOtlAmps(Double.valueOf(e.elementText("Value").trim()));
				}
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.Voltage")){
					if(!"".equals(e.elementText("Value").trim()))
						osa.setOtlVolt(Double.valueOf(e.elementText("Value").trim()));
				}
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.Power")){
					if(!"".equals(e.elementText("Value").trim()))
						osa.setOtlTotalKwh(Double.valueOf(e.elementText("Value").trim()));
				}
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.SegPower")){
					if(!"".equals(e.elementText("Value").trim()))
						osa.setOtlKwh(Double.valueOf(e.elementText("Value").trim()));
				}
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.ReportDate"))
					osa.setReportDate(e.elementText("Value").trim());
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.ClearTime"))
					osa.setClearTime(e.elementText("Value").trim());
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.MaxUser")){
					if(!"".equals(e.elementText("Value").trim()))
						osa.setMaxUsers(Integer.parseInt(e.elementText("Value").trim()));
				}
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.TimerNum")){
					if(e.elementText("Name").trim() != null && !"".equals(e.elementText("Name").trim()))
						osa.setRuleNo(Integer.parseInt(e.elementText("Value").trim()));
				}
				if(e.elementText("Name").trim().equals("InternetGatewayDevice.X_PHICOMM.Outlet.Rule")){
					if(e.elementText("Name").trim() != null && !"".equals(e.elementText("Name").trim()))
						rules.add(e.elementText("Value").trim());
				}
			}
		}
		if(rules.size()>0)
			osa.setRules(rules);
		return osa;
	}
	
}
