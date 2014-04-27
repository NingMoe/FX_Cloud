<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/base/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/article.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/core/jquery.cms.keywordinput.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/main.js"></script>
</head>
<body>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;background:#eee;z-index: 999;border:1px solid #999">
	<ul id="mytree" class="ztree" style="margin-top:0;"></ul>
</div>
<div id="container">
<div id="contents">
<input type="hidden" id="ctx" value="<%=request.getContextPath() %>"/>
	<h3 class="admin_link_bar" style="text-align:center">
	<span>${topic.title }</span>
	</h3>
	<table width="980" cellspacing="0" cellPadding="0" id="addTable">
		<tr>
			<td class="rightTd" width="120px">消息标题:</td>
			<td class="leftTd">${topic.title}[${topic.user.employeeID }]</td>
		</tr>
		<tr>
			<td class="rightTd">创建时间:</td>
			<td class="leftTd">
				<fmt:formatDate value="${topic.createDate}" pattern="yyyy-MM-dd HH:mm"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">发布开始时间:</td>
			<td class="leftTd">
				<fmt:formatDate value="${topic.publishStartDate}" pattern="yyyy-MM-dd HH:mm"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">发布结束时间:</td>
			<td class="leftTd">
				<fmt:formatDate value="${topic.publishStartDate}" pattern="yyyy-MM-dd HH:mm"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">发布频率</td>
			<td class="leftTd">
				${topic.publishRate}
			</td>
		</tr>
		<tr>
			<td class="rightTd">发布范围</td>
			<td class="leftTd">
				${publishRange}
			</td>
		</tr>
		<tr>
			<td class="rightTd">文章附件</td>
			<td class="leftTd">
			<c:forEach items="${atts }" var="att">
			<a href="<%=request.getContextPath() %>/resources/upload/${att.newName}" class="list_op">${att.oldName }</a>&nbsp;
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="2">文章内容</td>
		</tr>
		<tr>
			<td colspan="2">
			${topic.content }
			</td>
		</tr>
	</table>
</div>
</div>
</body>
</html>