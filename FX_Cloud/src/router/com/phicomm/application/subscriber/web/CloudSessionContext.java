package com.phicomm.application.subscriber.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class CloudSessionContext {
	private static final Map<String,HttpSession> ctx = new HashMap<String,HttpSession>();
	private CloudSessionContext(){}
	
	public static void addSessoin(HttpSession session) {
		ctx.put(session.getId(), session);
	}
	
	public static void removeSession(HttpSession session) {
		ctx.remove(session.getId());
	}
	
	public static HttpSession getSession(String sessionId) {
		return ctx.get(sessionId);
	}
}
