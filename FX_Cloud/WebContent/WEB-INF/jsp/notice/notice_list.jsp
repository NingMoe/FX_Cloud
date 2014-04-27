<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/system_right.css" type="text/css" />
<title>消息发布</title>
<script type="text/javascript">
function confirmdel(str){
	if(confirm("确认删除？")){
		location.href = str;
	}
}
</script>
</head>

<body style="background:#F4F4F4">
<div class="right">
  <p class="right_position"><img src="<%=request.getContextPath()%>/resources/image/position.png" /><strong>当前位置:</strong>&nbsp;首页&nbsp;》&nbsp;消息管理&nbsp;》&nbsp;消息显示</p>
  <div class="search">
    <p class="system_page_title"><span class="mouse_over"><strong>显示</strong></span></p>
    <ul class="search_content">

    </ul>
  </div>
 <div class="system_search_list">
   <table class="search_list_table">
     <thead>
     
  <th colspan="11" align="center" valign="middle"><span class="search_list_add"><a href="<%=request.getContextPath()%>/notice/notice_add"><img src="<%=request.getContextPath()%>/resources/image/add.png" style="float:left; margin-top:4px; margin-right:3px"/>添加</a></span>&nbsp;&nbsp;列表</th>
  		<tr>
          <td width="3%" align="center" class="list_title" >序号</td>
          <td width="6%" align="center" class="list_title">类型</td>
          <td width="6%" align="center" class="list_title">版本型号</td>
          <td width="10%" align="center" class="list_title">版本说明</td>
          <td width="10%" align="center" class="list_title">适用的设备型号</td>
          <td width="10%" align="center" class="list_title">主题</td>
      	  <td width="27%" align="center" class="list_title">内容</td>
          <td width="11%" align="center" class="list_title">地址</td>
     <!--      <td width="10%" align="center" class="list_title">开始时间</td> -->
          <td width="10%" align="center" class="list_title">发布时间</td> 
          <td width="9%" align="center" class="list_title">IP</td> 
          <td width="10%" align="center" class="list_title">操作</td>
        </tr>
        </thead>
     <tbody>
     <c:forEach varStatus="status" items="${pagers.datas }" var="u">
       <tr>
         <td align="center">${status.count+pagers.getOffset() }</td>
         <td align="center">
		<c:choose>
		
		   <c:when test="${u.routerVer==''}">    
		   	消息推送
		   </c:when>
		   
		   <c:otherwise>版本发布
		   </c:otherwise>
		  
		</c:choose>
         	</td>
        <td align="center">${u.routerVer}</td>
        <td align="cneter">${u.verDesc}</td>
         <td align="center">${u.routerTyp }</td>
         <td align="center">${u.newsTheme }</td>
      	 <td align="center">${u.newsContext }</td>
         <td align="center">${u.url }</td>
       <%--   <td align="center">${u.startTime }</td> --%>
         <td align="center">${u.time }</td>
         <td align="center">${u.ipAddr }</td>
         <td align="center" class="caoz"><!-- <a href="<%=request.getContextPath()%>/notice/${u.id }/notice_check">审核</a> --><a href="<%=request.getContextPath()%>/notice/${u.id }/notice_mod">修改</a>&nbsp;&nbsp;&nbsp;<a href="#" onclick='confirmdel("<%=request.getContextPath()%>/notice/${u.id }/notice_delete")'>删除</a></td>
       </tr>
		</c:forEach>
     </tbody>
   </table>
  <p class="table_list_page">&nbsp;&nbsp;<jsp:include page="/inc/pager.jsp">
				<jsp:param value="notice_list" name="url"/>
				<jsp:param value="${pagers.total}" name="items"/>
			</jsp:include></p>
</div>
</div>
</body>
</html>
