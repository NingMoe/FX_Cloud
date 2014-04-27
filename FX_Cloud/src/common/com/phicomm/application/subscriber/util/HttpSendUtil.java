package com.phicomm.application.subscriber.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;


public class HttpSendUtil {
	 public static String sendGet(String url, String params)
	    {
	        String result = "";
	        BufferedReader in = null;
	        try
	        {
	            String urlName = url + "?" + params;
	            URL realUrl = new URL(urlName);
	            URLConnection conn = realUrl.openConnection();
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
	            conn.connect();
	           /* Map<String, List<String>> map = conn.getHeaderFields();
	            for (String key : map.keySet())
	            {
	                System.out.println(key + "--->" + map.get(key));
	            }*/
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
	            String line;
	            while ((line = in.readLine()) != null)
	            {
	                result += "\n" + line;
	            }
	        }
	        catch (Exception e)
	        {
	            System.out.println("" + e);
	            e.printStackTrace();
	        }
	        finally
	        {
	            try
	            {
	                if (in != null)
	                {
	                    in.close();
	                }
	            }
	            catch (IOException ex)
	            {
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }

	    /**
	     * 向指定URL发送POST方法的请求
	     * 
	     * @param url
	     *            发送请求的URL
	     * @param params
	     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	     * @return URL所代表远程资源的响应
	     */
	    public static String sendPost(String url, String params)
	    {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try
	        {
	            URL realUrl = new URL(url);
	            URLConnection conn = realUrl.openConnection();
	            
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            //conn.setRequestProperty("SOAPAction", "");
	            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));
	            out.print(params);
	           // System.out.println(params);
	            out.flush();
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
	            String line;
	            while ((line = in.readLine()) != null)
	            {
	                result += "\n" + line;
	            }
	        }
	        catch (Exception e)
	        {
	            System.out.println("发送POST请求出现异常！" + e);
	            e.printStackTrace();
	        }
	        finally
	        {
	            try
	            {
	                if (out != null)
	                {
	                    out.close();
	                }
	                if (in != null)
	                {
	                    in.close();
	                }
	            }
	            catch (IOException ex)
	            {
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }
	    
	    /**
	     * 向指定URL发送POST方法的请求
	     * 
	     * @param url
	     *            发送请求的URL
	     * @param params
	     *            请求参数，Map<String, Object>形式。
	     * @return URL所代表远程资源的响应
	     */
	public static String sendPost(String url, Map<String, Object> params) {
		String result = "";

		String encodedParams = URLBuilder.httpBuildQuery(params, null);
		System.out.println("**cdf**:"+encodedParams);
		result = sendPost(url, encodedParams);
		return result;
	}
}
