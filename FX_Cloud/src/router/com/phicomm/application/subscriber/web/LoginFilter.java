package com.phicomm.application.subscriber.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phicomm.application.subscriber.model.Manager;



public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
		
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsq = (HttpServletRequest)req;
		Manager u = (Manager)hsq.getSession().getAttribute("loginUser");
		if(u==null) {
			((HttpServletResponse)resp).sendRedirect(hsq.getContextPath()+"/manager/login");
		}
		chain.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
			
	}

}
