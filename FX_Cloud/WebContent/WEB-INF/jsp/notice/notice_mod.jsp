<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息修改</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/system_right.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	$(".newVer").show();
	$(".newNews").hide();
	if($("#verDesc").val()==""){
		
		$(".newVer").toggle();
		$(".newNews").toggle();
	};
});

</script>
</head>

<body style="background:#F4F4F4">
<sf:form method="post" modelAttribute="news">
<div class="right">
 <p class="right_position"><img src="<%=request.getContextPath()%>/resources/image/position.png" /><strong>当前位置:</strong>&nbsp;首页&nbsp;》&nbsp;消息管理&nbsp;》&nbsp;消息修改</p>
 <div class="search">
   <p class="system_page_title"><span class="mouse_over"><strong>修改</strong></span></p>
   <ul class="add_content">
     <table class="add_table">
       <tr class="newVer">
         <td width="10%" class="add_title">新版本型号:</td>
         <td><sf:input path="routerVer" class="text_class_more" name="routerVer" />${message}<sf:errors path="routerTyp"/> </td>
       </tr>
       <tr class="newVer">
         <td class="add_title">新版本硬件型号:</td>
         <td><sf:input path="hardVer" class="text_class_more" name="hardVer" style="width:200px" /><sf:errors path="hardVer"/></td>
       </tr>
       <tr class="newVer">
         <td class="add_title">版本说明:</td>
         <td><sf:input path="verDesc" class="text_class_more" name="verDesc" style="width:200px" id="verDesc" /><sf:errors path="verDesc"/></td>
       </tr>
        <tr class="newVer">
         <td class="add_title">此版本适用的设备型号:</td>
         <td><sf:input path="routerTyp" class="text_class_more" name="routerTyp" style="width:200px" /><sf:errors path="routerVer"/></td>
       </tr>
        <tr class="newNews">
         <td class="add_title">主题:</td>
         <td><sf:input path="newsTheme" class="text_class_more" name="newsTheme" style="width:200px" /><sf:errors path="newsTheme"/></td>
       </tr>
       <tr class="newNews">
         <td class="add_title">内容:</td>
         <td><sf:textarea path="newsContext" class="text_class_more" name="newsContext" style="width:200px" /><sf:errors path="newsContext"/></td>
       </tr>
<!--        <tr id="startTime">
         <td class="add_title">消息推送开始时间:</td>
         <td><input class="easyui-datetimebox" name="startTime1" required="true" style="width:200px" /></td>
       </tr>
       <tr id="endTime">
         <td class="add_title">消息推送结束时间:</td>
         <td><input class="easyui-datetimebox" name="endTime1" required="true" style="width:200px" /></td>
       </tr> -->
        <tr class="newVer">
         <td class="add_title">升级地址:</td>
         <td><sf:input path="url" class="text_class_more" name="url" style="width:200px" /><sf:errors path="url"/></td>
       </tr>
              
     
       <tr>
		 <td></td>
         <td style="height:80px"><a href="javascript:document.forms[0].submit()" class="system_btn"><span>确 定</span></a><a href="<%=request.getContextPath()%>/notice/notice_list" class="system_btn" style="margin-left:20px"><span>取 消</span></a></td>
       </tr>
     </table>
   </ul>
 </div>
</div>

</sf:form>

</body>
</html>
