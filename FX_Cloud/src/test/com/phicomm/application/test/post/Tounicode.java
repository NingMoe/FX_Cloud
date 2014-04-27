package com.phicomm.application.test.post;

public class Tounicode {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		System.out.println(new Tounicode().chinaToUnicode("桂林")); 
	}
	
	public static String chinaToUnicode(String str){ 
		String result=""; 
		for (int i = 0; i < str.length(); i++){ 
		            int chr1 = (char) str.charAt(i); 
		            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文) 
		                result+="&#x" + Integer.toHexString(chr1)+";"; 
		            }else{ 
		            result+=str.charAt(i); 
		            } 
		        } 
		return result; 
		} 
}
