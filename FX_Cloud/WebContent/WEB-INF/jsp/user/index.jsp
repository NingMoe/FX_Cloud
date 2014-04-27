<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>

<frameset rows="48,*" cols="*" frameborder="NO" border="0" framespacing="0">
  <frame src="<%=request.getContextPath()%>/user/top" name="topFrame" scrolling="NO" noresize >
  <frameset cols="205,*" frameborder="NO" border="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/user/left" name="leftFrame" scrolling="NO" noresize>
    <frame src="<%=request.getContextPath()%>/notice/notice_list" name="mainFrame">
  </frameset>
</frameset>
<noframes><body>
</body></noframes>
</html>
