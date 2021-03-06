<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
});
</script>
</head>
<body>
<div id="content">
	<sf:form method="post" modelAttribute="managerDto" id="addForm">
	<table width="800" cellspacing="0" cellPadding="0">
		<thead><tr><td colspan="2">添加用户功能</td></tr></thead>
		<tr>
			<td class="rightTd" width="200px">用户名(必须是英文):</td>
			<td class="leftTd"><sf:input path="username" size="30"/><sf:errors cssClass="errorContainer" path="username"/></td>
		</tr>
		<tr>
			<td class="rightTd">真实姓名:</td><td class="leftTd"><sf:input path="realname" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">用户密码:</td><td><sf:password path="password" size="30"/><sf:errors cssClass="errorContainer" path="password"/></td>
		</tr>
		<tr>
			<td class="rightTd">确认密码:</td><td><input type="password" id="confirmPwd" name="confirmPwd" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">工号:</td><td><sf:input path="employeeID" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">描述:</td><td><sf:input path="description" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">角色:</td>
			<td>
				<sf:checkboxes  items="${roles}" itemLabel="name" itemValue="id" path="roleIds"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="centerTd"><input type="submit" value="添加用户"/><input type="reset"/></td>
		</tr>
	</table>
	</sf:form>
</div>
</body>
</html>