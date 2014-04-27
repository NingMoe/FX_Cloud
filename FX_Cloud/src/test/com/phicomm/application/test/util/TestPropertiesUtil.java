package com.phicomm.application.test.util;

import java.io.IOException;
import java.util.Properties;

public class TestPropertiesUtil {
	private static Properties configProp;
	private static Properties apkApidProp;
	
	public static Properties getConfigProp() {
		try {
			synchronized(TestPropertiesUtil.class){
				//System.out.println(TestPropertiesUtil.class.getClassLoader().);
				if(configProp==null) {
					configProp = new Properties();
					configProp.load(TestPropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configProp;
	}
	
	public static Properties getApkApisProp() {
		try {
			synchronized(TestPropertiesUtil.class){
				if(apkApidProp==null) {
					apkApidProp = new Properties();
					apkApidProp.load(TestPropertiesUtil.class.getClassLoader().getResourceAsStream("apkApis.properties"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return apkApidProp;
	}
	
}
