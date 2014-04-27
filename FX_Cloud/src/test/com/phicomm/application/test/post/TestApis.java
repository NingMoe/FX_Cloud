package com.phicomm.application.test.post;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.phicomm.application.test.util.TestPropertiesUtil;



public class TestApis {
	//private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args){
		new TestApis().run();
	}
	
	public void run(){
		String serverIp = TestPropertiesUtil.getConfigProp().getProperty("serviceIpAddress");
		long consumeTime = 0;
		Map<String,String> routerApis=  new HashMap<String, String>();
		Map<String,String> apkApis=  new HashMap<String, String>();
		List<CompApis> outPutTime = new ArrayList<CompApis>();
		
		routerApis.put("forget", "forget.xml");
		//routerApis.put("0_BOOT_STRAP", "0BOOTSTRAP.xml");
	//	routerApis.put("1_BOOT","1BOOT.xml");
	//	routerApis.put("4_VALUE_CHANGE", "4VALUECHANGE.xml");
	//	routerApis.put("X_GET_ACCOUNT_INFO","X_GET_ACCOUNT_INFO.xml");
		//routerApis.put("X_LOGIN", "X_LOGIN.xml");
		//routerApis.put("X_PHICOMM_swUpdateCheck", "X_PHICOMM_swUpdateCheck.xml");
		//routerApis.put("X_ACTIVE", "XACTIVE.xml");
		
		/*apkApis.put("apkLogin", PropertiesUtil.getApkApisProp().getProperty("apkLogin"));
		apkApis.put("apkAddDevice", PropertiesUtil.getApkApisProp().getProperty("apkAddDevice"));
		apkApis.put("apkGetDevice", PropertiesUtil.getApkApisProp().getProperty("apkGetDevice"));
		apkApis.put("apkMessage", PropertiesUtil.getApkApisProp().getProperty("apkMessage"));*/
		
		Set<String> routerKeys = routerApis.keySet();
		for(String routerKey:routerKeys){
			consumeTime = getRouterResponseTime(routerApis.get(routerKey),serverIp);
			outPutTime.add(new CompApis(routerKey,consumeTime));
		}
		
		Set<String> apkKeys = apkApis.keySet();
		for(String apkKey:apkKeys){
			try {
				consumeTime = getApkConsumeTime("http://"+serverIp+"/"+apkApis.get(apkKey));
				outPutTime.add(new CompApis(apkKey,consumeTime));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(outPutTime,new Comparator<CompApis>(){
			@Override
			public int compare(CompApis o1, CompApis o2) {
				return o1.getTime()>o2.getTime()?1:(o1.getTime()<o2.getTime()?-1:0);
			}
		});
		for(CompApis ap:outPutTime){
			outputAnswer(ap.getName(),ap.getTime());
		}
	}
	
	/**
	 * 获取路由器API的执行时间
	 * @param xmlFile 接口xml文件
	 * @param serverIp 服务器IP
	 * @return
	 */
	private long getRouterResponseTime(String xmlFile,String serverIp){
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
        
        long startTime = new Date().getTime();
        String response = GetPostUtil
                //.sendPost("http://"+serverIp+"/FX_Cloud/test/getxml"
        		.sendPost("http://"+serverIp+"/FX_Cloud/login/status/"
                , request);
        System.out.println(response);
        return new Date().getTime()-startTime;
	}
	
	/**
	 * 将API的执行时间写入文件
	 * @param apiName 
	 * @param consumeTime
	 */
	private void outputAnswer(String apiName,long consumeTime){
		String file =  TestPropertiesUtil.getConfigProp().getProperty("outputPath")+"apis"+".txt";
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriterWithEncoding(file,"utf-8",true)));
			out.println(apiName+"api consume "+consumeTime+"毫秒</br>");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
	}
	
	private long getApkConsumeTime(String destUrl) throws IOException{
		FileOutputStream fos = null;
	 	InputStream is = null;
	 	long startTime = new Date().getTime();
        try {
			URL url = new URL(destUrl);  
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 打开连接   
			conn.setConnectTimeout(5 * 1000);  // 设置访问超时时间  
			conn.setRequestMethod("GET");  // 设置请求参数  
			is = conn.getInputStream();  // 得到输入流  
			byte[] buffer = new byte[1024];  
			int len = 0;
			while ((len = is.read(buffer)) != -1) {  
				System.out.write(buffer,0,len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(is!=null)is.close();
			if(fos!=null)fos.close();
		} 
        return new Date().getTime()-startTime;
	}

}
