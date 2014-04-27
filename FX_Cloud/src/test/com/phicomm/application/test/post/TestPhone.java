package com.phicomm.application.test.post;

import static org.junit.Assert.*;

import org.junit.Test;

import com.phicomm.application.test.post.GetPostUtil;

public class TestPhone {
   private final static String LOCALIP = "localhost:8080";
  // private final static String TESTIP = "172.17.60.213";
   private final static String TESTIP = "localhost:8080";
   
	@Test
	public void testLogin() {
		//fail("Not yet implemented");
		String params = "phoneMac=6C:F0:49:80:F1:59&userMail=lei.ji@feixun.com.cn&password=MTIzNDU2&phoneTyp=201616&phoneVer=V1.02&phoneIMEI=08301155418&appVer=V1.0.3";
		String url = "http://"+ TESTIP +"/FX_Cloud/app/login";
		
		String response = GetPostUtil.sendGet(url, params);
		System.out.println(response);
	}
	
	@Test
	public void testRegist() {
		//fail("Not yet implemented");
		String params = "phoneMac=6C:F0:49:80:F1:59&userMail=boke.xu@feixun.com.cn&password=MTIzNDU2&username=jilei&province=0_1_1_9&phoneNo=12345678901&zipcode=201616&phoneTyp=FWR321&phoneVer=V1.0.1&phoneIMEI=08301155418&appVer=V1.0.2";
		String url = "http://"+ TESTIP +"/FX_Cloud/app/register";
		
		String response = GetPostUtil.sendGet(url, params);
		System.out.println(response);
	}
	
	
	@Test
	public void testBindDev() {
		String params = "userMail=lei.ji@feixun.com.cn&phoneMac=6C:F0:49:80:F1:59&phoneIMEI=08301155418&bindNum=1&devPID=PID1&devMac=11:22:33:44:55:71";
		String url = "http://"+ TESTIP +"/FX_Cloud/app/bindDev";
		
		String response = GetPostUtil.sendGet(url, params);
		System.out.println(response);
	}
	
	
	@Test
	public void testAllDevice() {
		String params = "phoneMac=6C:F0:49:80:F1:59&phoneIMEI=08301155418&userMail=lei.ji@feixun.com.cn";
		String url = "http://"+ TESTIP +"/FX_Cloud/app/getAllDevice";
		
		String response = GetPostUtil.sendGet(url, params);
		System.out.println(response);
	}
	
	@Test
	public void testActive() {
		String params = "loginMail=lei.ji@feixun.com.cn&loginPsw=MTIzNDU2";
		String url = "http://"+ TESTIP +"/FX_Cloud/login/active";
		
		String response = GetPostUtil.sendGet(url, params);
		System.out.println(response);
	}
	
	@Test
	public void testDeviceList() {
		String params = "ownMac=00:22:33:CB:57:D6";
		String url = "http://"+ TESTIP +"/FX_Cloud/device/devcList";
		
		String response = GetPostUtil.sendGet(url, params);
		System.out.println(response);
	}
	
	@Test
	public void testLogout() {
		String params = "ownMac=00:22:33:CB:57:D6";
		String url = "http://"+ TESTIP +"/FX_Cloud/login/logout";
		
		String response = GetPostUtil.sendGet(url, params);
		System.out.println(response);
	}
	
	@Test
	public void testNews() {
		String params = "ownMac=00:22:33:CB:57:D6&phoneTyp=i600w&userLong=&userLat=&appVer=v1.0&routerTyp=FIR151M&routerVer=V1.0.2&loginSta=0";
		String url = "http://"+ TESTIP +"/FX_Cloud/message/news";
		
		String response = GetPostUtil.sendGet(url, params);
		System.out.println(response);
	}

	
}
