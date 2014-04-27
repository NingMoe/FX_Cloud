package com.phicomm.application.subscriber.model;

import java.util.StringTokenizer;

public class CheckIp {
	
	public static boolean CheckWanIp(String s){
	
		char a[] = s.toCharArray();
		if (s.length() > 15) {
			System.out.println("地址总长超过15");
			return false;
		}
		for (int i = 0; i < s.length(); i++)// 判断是否存在非法字符，分隔符只能为3个，
		{
			if ((a[i] < '0' || a[i] > '9') && a[i] != '.') {
				System.out.println("地址中包含非法字符!");
				return false;
			}
		}
		int dian = 0;
		for (int i = 0; i < s.length(); i++)// 判断是否存在非法字符，分隔符只能为3个，
		{

			if (a[i] == '.')
				dian++;
		}
		if (dian != 3) {
			System.out.println("地址中分隔符只能为3个!");
			return false;
		}
		for (int i = 0; i < s.length() - 1; i++)// 判断是否出现连续的分隔符
		{
			if (a[i] == '.' && a[i + 1] == '.') {
				System.out.println("地址中不能出现连续的分隔符!");
				return false;
			}
		}
		if (a[s.length() - 1] == '.')// 判断是否最后位为分隔符
		{
			System.out.println("地址最后位不能为分隔符!");
		}
		if (a[0] == '.')// 判断是否首位为分隔符
		{
			System.out.println("地址首位不能为分隔符!");
			return false;
		}
		StringTokenizer dian_s = new StringTokenizer(s, ".");// 判断地址是否在0至255之间
		while (dian_s.hasMoreTokens()) {
			String str = dian_s.nextToken();
			char b[] = str.toCharArray();
			int i = 0;
			int flag2 = 0;
			for (i = 0; i < str.length(); i++) {
				if (b[i] > '0' && b[i] < '9')
					flag2 = 1;
				if (b[i] < '0' || b[i] > '9') {
					flag2 = 0;
					break;
				}
			}
			if (flag2 == 1) {
				int x = Integer.parseInt(str);
				if (x < 0 || x > 255) {
					System.out.println("地址不在0至255之间!");
					return false;
				}
			}
		}
		return true;
	
	}
}
