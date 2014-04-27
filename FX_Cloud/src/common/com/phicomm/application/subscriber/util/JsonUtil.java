package com.phicomm.application.subscriber.util;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static JsonUtil ju;
	private static JsonFactory jf;
	private static ObjectMapper mapper;
	private JsonUtil(){}
	
	public synchronized static JsonUtil getInstance() {
		if(ju==null) ju = new JsonUtil();
		return ju;
	}
	
	public static synchronized ObjectMapper getMapper() {
		if(mapper==null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	public static synchronized JsonFactory getFactory() {
		if(jf==null) jf = new JsonFactory();
		return jf;
	}
	
	public String obj2json(Object obj) {
		JsonGenerator jg = null;
		try {
			synchronized(this){
				jf = getFactory();
				mapper = getMapper();
			}
			StringWriter out = new StringWriter();
			jg = jf.createJsonGenerator(out);
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(jg!=null) jg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Object json2obj(String json,Class<?> clz) {
		try {
			synchronized(this){
				mapper = getMapper();
			}
			return mapper.readValue(json,clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
