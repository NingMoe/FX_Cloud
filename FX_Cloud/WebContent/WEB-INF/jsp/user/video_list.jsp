<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/system_right.css" type="text/css" />
<title>注册用户信息</title>
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
  <p class="right_position"><img src="<%=request.getContextPath()%>/resources/image/position.png" /><strong>当前位置:</strong>&nbsp;首页&nbsp;》&nbsp;用户管理&nbsp;》&nbsp;注册用户</p>
  <div class="search">
    <p class="system_page_title"><span class="mouse_over"><strong>查询</strong></span></p>
    <ul class="search_content">
    <form method="post" action="<%=request.getContextPath()%>/user/search">
      <table class="search_table">
        <tr>
          <td width="30%"><label>搜索选项：</label><select class="text_class_select" name="searchfor"><option>邮箱</option></select></td>
          <td width="30%"></td>
          <td></td>
        </tr>
        <tr>
          <td><label>搜索内容：</label>
            <input type="text" class="text_class" name="contextfor" /></td>
          <td></td>
          <td></td>

        </tr>
        <tr>
          <td style="padding-left:30px;"><a href="javascript:document.forms[0].submit()" class="system_search_btn"><span>查 询</span></a></td>
          <td></td>
          <td></td>
        </tr>
      </table>
      </form>
    </ul>
  </div>
 <div class="system_search_list">
   <table class="search_list_table">
     <thead>
     
  <th colspan="9" align="center" valign="middle"><span class="search_list_add"><a href="<%=request.getContextPath()%>/user/video_add"><img src="<%=request.getContextPath()%>/resources/image/add.png" style="float:left; margin-top:4px; margin-right:3px"/>添加</a></span>&nbsp;&nbsp;列表</th>
  		<tr>
          <td width="3%" class="list_title" >序号</td>
          <td width="15%" class="list_title">用户姓名</td>
          <td width="15%" class="list_title">邮箱</td>
          <td width="10%" align="left" class="list_title">当地邮编</td>
      	  <td width="14%" align="center" class="list_title">路由器用户名</td>
          <td width="12%" align="center" class="list_title">路由器密码</td>
          <td width="12%" align="center" class="list_title">注册日期</td>
          <td width="7%" align="center" class="list_title">状态</td>
          <td width="12%" align="center" class="list_title">操作</td>
        </tr>
        </thead>
     <tbody>
     <c:forEach items="${pagers.datas }" var="u">
       <tr>
         <td align="center">${u.id }</td>
         <td align="center"><a href="#">${u.regerUserFst }${u.regerUserScd }</a></td>
         <td align="center">${u.reger2Mail }</td>
         <td align="center">${u.mailNum }</td>
      	 <td align="center">${u.admUser2Nm }</td>
         <td align="center">${u.adm2Pass }</td>
         <td align="center">2013-08-20</td>
         <td align="center">已审核</td>
         <td align="center" class="caoz"><a href="#">审核</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/user/${u.id }/video_modify">修改</a>&nbsp;&nbsp;&nbsp;<a href="#" onclick='confirmdel("<%=request.getContextPath()%>/user/${u.id }/video_delete")'>删除</a></td>
       </tr>
		</c:forEach>
     </tbody>
   </table>
  <p class="table_list_page">&nbsp;&nbsp;<jsp:include page="/inc/pager.jsp">
				<jsp:param value="video_list" name="url"/>
				<jsp:param value="${pagers.total}" name="items"/>
			</jsp:include></p>
</div>
</div>
</body>
</html>
