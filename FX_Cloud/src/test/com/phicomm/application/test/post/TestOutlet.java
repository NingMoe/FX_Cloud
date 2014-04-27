package com.phicomm.application.test.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.phicomm.application.test.util.TestPropertiesUtil;

public class TestOutlet {
	public static void main(String[] args) {
		String serverIp = TestPropertiesUtil.getConfigProp().getProperty("serviceIpAddress");
		Map<String,String> routerApis=  new HashMap<String, String>();
		routerApis.put("outlet_0_BOOT_STRAP", "outlet0BOOTSTRAP.xml");
		//routerApis.put("outlet_reportPower_STRAP", "outletReportPower.xml");
		//routerApis.put("outlet_reportRule", "outletReportRule.xml");
		//routerApis.put("outlet_4valueChange", "outlet4valueChange.xml");
		Set<String> routerKeys = routerApis.keySet();
		for(String routerKey:routerKeys){
			getRouterResponse(routerApis.get(routerKey),serverIp);
		}
	}
	public static void getRouterResponse(String xmlFile,String serverIp){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;
		
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		try {
			if(builder != null)
				doc = builder.parse("src/test/source/"+xmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		String request = GetxmlFile.xmlConvertString(doc);
        
        String response = GetPostUtil
        		.sendPost("http://"+serverIp+"/FX_Cloud/outlet/report/"
                , request);
		//String response = http("http://"+serverIp+"/FX_Cloud/outlet/report/",request);
        System.out.println(response);

	}
	
		 
		public static String http(String url, String params) {
			URL u = null;
			HttpURLConnection con = null;
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(params);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
			con.disconnect();
			}
		}
			 
		//读取返回内容
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = null;
		try {
			if(con != null)
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			if(br != null){
				while ((temp = br.readLine()) != null) {
					buffer.append(temp);
					buffer.append("\n");
				}
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		 
		return buffer.toString();
	}
	 

}
