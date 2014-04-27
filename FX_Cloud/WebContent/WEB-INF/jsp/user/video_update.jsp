<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户修改</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/system_right.css" type="text/css" />
</head>

<body style="background:#F4F4F4">
<sf:form method="post" modelAttribute="user">
<div class="right">
 <p class="right_position"><img src="<%=request.getContextPath()%>/resources/image/position.png" /><strong>当前位置:</strong>&nbsp;首页&nbsp;》&nbsp;用户管理&nbsp;》&nbsp;添加</p>
 <div class="search">
   <p class="system_page_title"><span class="mouse_over"><strong>添加</strong></span></p>
   <ul class="add_content">
     <table class="add_table">
       <tr>
         <td width="10%" class="add_title"><label class="importent">*</label>邮箱</td>
         <td><sf:input path="reger2Mail" class="text_class_more" /><sf:errors path="reger2Mail"/></td>
       </tr>
       
       <tr>
         <td class="add_title"><label class="importent">*</label>密码</td>
         <td><sf:password path="reger2Psw" class="text_class_more" /><sf:errors path="reger2Psw"/></td>
        </tr>
        <tr>
         <td class="add_title">邮编</td>
         <td><sf:input path="mailNum" class="text_class_more" /><sf:errors path="mailNum"/></td>
       </tr>
               <tr>
         <td class="add_title"><label class="importent">*</label>姓</td>
         <td><sf:input path="regerUserFst" class="text_class_more" /><sf:errors path="regerUserFst"/></td>
       </tr>
               <tr>
         <td class="add_title"><label class="importent">*</label>名</td>
         <td><sf:input path="regerUserScd" class="text_class_more" /><sf:errors path="regerUserScd"/></td>
       </tr>
               <tr>
         <td class="add_title">路由器用户名</td>
         <td><sf:input path="admUser2Nm" class="text_class_more" /><sf:errors path="admUser2Nm"/></td>
       </tr>
               <tr>
         <td class="add_title">路由器密码</td>
         <td><sf:input path="adm2Pass" class="text_class_more" /><sf:errors path="adm2Pass"/></td>
       </tr>
     
       <tr>
		 <td></td>
         <td style="height:80px"><a href="javascript:document.forms[0].submit()" class="system_btn"><span>确 定</span></a><a href="<%=request.getContextPath()%>/user/video_list" class="system_btn" style="margin-left:20px"><span>取 消</span></a></td>
       </tr>
     </table>
   </ul>
 </div>
</div>
</sf:form>
</body>
</html>
