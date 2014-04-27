<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
<table width="1024" align="center" border="1">
	<tr>
	<td>用户标识:${pagers.total }</td><td>用户邮箱</td><td>用户姓名</td><td>用户邮编</td><td>设备账号</td><td>设备密码</td>
	<td>操作</td>
	</tr>
	<c:if test="${pagers.total le 0 }">	<!-- <=0 -->
		<tr>
		<td colspan="7">目前还没有用户数据</td>
		</tr>
	</c:if>
	<c:if test="${pagers.total gt 0}">
		<c:forEach items="${pagers.datas }" var="u">
		<tr>
		<td>${u.id }</td><td>${u.reger2Mail }</td>
		<td><a href="${u.id }">${u.regerUserFst }${u.regerUserScd }</a></td>
		<td>${u.mailNum }</td><td>${u.admUser2Nm }</td><td>${u.adm2Pass }</td>
		<td><a href="${u.id }/update">更新</a>&nbsp;<a href="${u.id }/delete">删除</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="7">
			<jsp:include page="/inc/pager.jsp">
				<jsp:param value="users" name="url"/>
				<jsp:param value="${pagers.total}" name="items"/>
			</jsp:include>
		</td>
		</tr>	
	</c:if>
</table>
</body>
</html>