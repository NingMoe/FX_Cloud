<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
<script>
	function check(id){
	$("#auditstatus").val(id);
	$("#topic").submit();
		}
</script>
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
	<sf:form method="post" modelAttribute="topic" id="topic">
	<input type="hidden" name="auditstatus" id="auditstatus" value="0"/>
	<input type="hidden" name="id" id="id" value="${topic.id}"/>
	<table width="980" cellspacing="0" cellPadding="0" id="addTable">
		<tr>
			<td class="rightTd" width="120px">文章标题:</td>
			<td class="leftTd">${topic.title}[${topic.user.employeeID }]</td>
		</tr>
		<tr>
			<td class="rightTd">发布时间:</td>
			<td class="leftTd">
				<fmt:formatDate value="${topic.publishDate}" pattern="yyyy-MM-dd HH:mm"/>(<fmt:formatDate value="${topic.createDate}" pattern="yyyy-MM-dd HH:mm"/>)
			</td>
		</tr>
		<tr>
			<td class="rightTd">文章状态</td>
			<td class="leftTd">
			<c:if test="${topic.status eq 0 }">未审核&nbsp;</c:if>
			<c:if test="${topic.status eq 1 }">一审通过&nbsp;</c:if>
			<c:if test="${topic.status eq 2 }">一审失败&nbsp;</c:if>
			<c:if test="${topic.status eq 4 }">二审通过&nbsp;</c:if>
			<c:if test="${topic.status eq 5 }">二审失败&nbsp;</c:if>	
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
		<tr>
		<td colspan="2">
		<span style="background:#921;color:#fff;padding:4px;">审核记录</span>
		</td>
		</tr>
		<c:forEach items="${audit }" var="t">
		<tr>
			<td>
			${t.employeeID} </td>
			<td>
			<fmt:formatDate value="${t.auditTime}" pattern="yyyy-MM-dd HH:mm"/>&nbsp;&nbsp;
			<c:if test="${t.status eq 0 }">未审核&nbsp;</c:if>
			<c:if test="${t.status eq 1 }">一审通过&nbsp;</c:if>
			<c:if test="${t.status eq 2 }">一审失败&nbsp;</c:if>
			<c:if test="${t.status eq 4 }">二审通过&nbsp;</c:if>
			<c:if test="${t.status eq 5 }">二审失败&nbsp;</c:if>	
			</td>
		</tr>
		<tr>
			<td colspan="2">
				${t.advise }
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="2">审核意见</td>
		</tr>
		<tr>
			<td colspan="2">
			<textarea name="auditAdvise" id="auditAdvise" rows="25" cols="110"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="centerTd">
			<input type="button" onclick="check(1)" value="一审通过"/>
			<input type="button" onclick="check(2)" value="一审失败"/></td>
		</tr>
	</table>
	</sf:form>
</div>
</div>
</body>
</html>