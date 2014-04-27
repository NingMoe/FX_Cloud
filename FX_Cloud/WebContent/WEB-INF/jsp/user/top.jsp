<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无文档标题</title>
<link href="<%=request.getContextPath()%>/resources/css/page.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function logout(str){
	if(confirm("确认退出系统么？")){
		top.location.href = str;
	}
}
</script>
</head>

<body topmargin="0" leftmargin="0">
<div class="system_top">
<div class="system_top">
  <span><a >${version}</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick='logout("<%=request.getContextPath()%>/admin/logout")'>退出系统</a>&nbsp;&nbsp;</span>
  <p class="system_logo"><img src="<%=request.getContextPath()%>/resources/image/system_logo.png" /><label>欢迎您！管理员</label></p>
</div>
</body>
</html>
