<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/system_right.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/inc.js"></script>
<script type="text/javascript">
$(function(){
	$("#search").click(function(event){
		var con = $("#con").val(); 
		var href = window.location.href;
		var len = href.lastIndexOf("?");
		if(len>0) {
			href = href.substr(0,len);
		}
		window.location.href=href+"?con="+con;
	});
});
</script>
</head>
<body>
<div id="content">
<p class="right_position"><img src="<%=request.getContextPath()%>/resources/image/position.png" /><strong>当前位置:</strong>&nbsp;首页&nbsp;》&nbsp;消息&nbsp;》&nbsp;显示</p>
	<table width="1000" cellspacing="0" cellPadding="0" id="listTable">
		<thead>
		<tr><td colspan="9">
		搜索消息:<input type="text" name="con" size="30" id="con" value="${con}">
		<input type="button" id="search" value="搜索文章"/>
		</td></tr> 
		<tr>
			<td width="250">消息标题</td>
			<td>消息作者</td>
			<td>创建时间</td>
			<td>发布开始时间</td>
			<td>发布终止时间</td>
			<td>速率</td>
			<td>状态</td>
			<td>操作</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${datas.datas }" var="t">
		<c:if test="${t.status ne 3 }">
			<tr>
				<td><a href="javascript:openWin('<%=request.getContextPath() %>/news/${t.id }','showTopic')" class="list_link">${t.title }</a></td>
				<td>${t.author}</td>
				<td>
					<fmt:formatDate value="${t.createDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					<fmt:formatDate value="${t.publishStartDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					<fmt:formatDate value="${t.publishEndDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>${t.publishRate }</td>
				<td>
					<c:if test="${t.status eq 0 }">未审核&nbsp;<a href="firstAudit/${t.id }" class="list_op">一审</a></c:if>
					<c:if test="${t.status eq 1 }">一审通过&nbsp;<a href="secondAudit/${t.id }" class="list_op">二审</a></c:if>
					<c:if test="${t.status eq 2 }">一审失败&nbsp;</c:if>
					<c:if test="${t.status eq 4 }">二审通过&nbsp;</c:if>
					<c:if test="${t.status eq 5 }">二审失败&nbsp;</c:if>
				</td>
				<td>
				<c:if test="${t.status eq 0 or t.status eq 2 or t.status eq 5}">
					<a href="delete/${t.id }" class="list_op delete">删除</a>
					<a href="javascript:openWin('<%=request.getContextPath() %>/news/update/${t.id}','updateTopic')" class="list_op">更新</a>
				</c:if>
				<c:if test="${t.status eq 4}"><a href="#" class="list_op delete">发布</a></c:if>
				</td>
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="9" style="text-align:right;margin-right:10px;">
			<jsp:include page="/inc/pager.jsp">
				<jsp:param value="${datas.total }" name="totalRecord"/>
				<jsp:param value="audits" name="url"/>
			</jsp:include>
			</td>
		</tr>
		</tfoot>
	</table>
</div>
</body>
</html>