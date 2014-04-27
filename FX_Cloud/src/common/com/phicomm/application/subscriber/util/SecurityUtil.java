package com.phicomm.application.subscriber.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.text.StrBuilder;

public class SecurityUtil {
	public static String md5(String psd) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(psd.getBytes(Charset.forName("utf-8")));
		return new BigInteger(1,md.digest()).toString();
	}
	
	public static StringBuilder md5HX(String psd) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(psd.getBytes(Charset.forName("utf-8")));
		byte[] di = md.digest();
		return byte2hex(di);
	}
	
	public static StringBuilder byte2hex(byte[] b){
		StringBuilder hs= new StringBuilder();
		for (int n=0;n<b.length;n++){
			hs.append(java.lang.Integer.toHexString(b[n] & 0XFF));
		}
		return hs;
	}
	
	public static String md5(String username,String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(username.getBytes());
		md.update(password.getBytes());
		return new BigInteger(1,md.digest()).toString(16);
	}
}
