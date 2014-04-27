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
<div style="width:100%;">
  <p class="right_position"><img src="<%=request.getContextPath()%>/resources/image/position.png" /><strong>当前位置:</strong>&nbsp;首页&nbsp;》&nbsp;内容管理&nbsp;》&nbsp;视频中心</p>
  <div class="search">
    <p class="system_page_title"><span class="mouse_over"><strong>查询</strong></span></p>
    <ul class="search_content">
      <table class="search_table">
        <tr>
          <td width="30%"><label>视频类别：</label><select class="text_class_select"><option>全部</option><option>产品视频</option><option>活动视频</option></select></td>
          <td width="30%"><label>审核状态：</label><select class="text_class_select"><option>已审核</option><<option>未审核</option><option>已取消</option></select></td>
          <td></td>
        </tr>
        <tr>
          <td><label>更新日期：</label>
            <input type="text" class="text_class" />&nbsp;&nbsp;至&nbsp;&nbsp;<input type="text" class="text_class" /></td>
          <td><label>全文搜索：</label><input type="text" class="text_class_more" /></td>
          <td></td>

        </tr>
        <tr>
          <td style="padding-left:30px;"><a href="#" class="system_search_btn"><span>查 询</span></a></td>
          <td></td>
          <td></td>
        </tr>
      </table>
    </ul>
  </div>
 <div class="system_search_list">
   <table class="search_list_table">
     <thead>
     
  <th colspan="9" align="center" valign="middle"><span class="search_list_add"><a href="video_add.htm"><img src="<%=request.getContextPath()%>/resources/image/add.png" style="float:left; margin-top:4px; margin-right:3px"/>添加</a></span>&nbsp;&nbsp;列表</th>
  		<tr>
          <td width="3%" class="list_title" >序号</td>
          <td width="32%" class="list_title">标题</td>
          <td width="15%" class="list_title">相关栏目</td>
          <td width="10%" align="left" class="list_title">文件大小</td>
      	  <td width="7%" align="center" class="list_title">排序</td>
          <td width="7%" align="center" class="list_title">审核状态</td>
          <td width="7%" align="center" class="list_title">更新日期</td>
          <td width="7%" align="center" class="list_title">更新人</td>
          <td width="12%" align="center" class="list_title">操作</td>
        </tr>
        </thead>
     <tbody>
       <tr>
         <td align="center">1</td>
         <td><a href="#">K390v</a></td>
         <td>新闻中心</td>
         <td align="left">12MB</td>
      	 <td align="center"><input type="text" style="width:60px; text-align:center;" /></td>
         <td align="center">待审核</td>
         <td align="center">2013-07-20</td>
         <td align="center">marketing</td>
         <td align="center" class="caoz"><a href="#">审核</a>&nbsp;&nbsp;&nbsp;<a href="video_add.htm">修改</a>&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>
       </tr>
       <tr style="background:#faf9f9">
         <td align="center">2</td>
         <td><a href="#">I800v</a></td>
         <td>常见问题</td>
         <td align="left">6MB</td>
      	 <td align="center"><input type="text" style="width:60px; text-align:center;" /></td>
         <td align="center">已审核</td>
         <td align="center">2013-07-21</td>
         <td align="center">system</td>
         <td align="center" class="caoz"><a href="#">取消</a>&nbsp;&nbsp;&nbsp;<a href="video_add.htm">修改</a>&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>
       </tr>
     </tbody>
   </table>
  <p class="table_list_page">&nbsp;&nbsp;共<label style="color:#F68418"> 1/100 </label>
    页&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">首页</a>&nbsp;|&nbsp;<a href="#">上页</a>&nbsp;|&nbsp;<a href="#">下页</a>&nbsp;|&nbsp;<a href="#">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;跳转到:
    <input type="text" style="width:30px;" />&nbsp;&nbsp;<input type="button" value="GO" />&nbsp;&nbsp;&nbsp;&nbsp;</p>
</div>
</div>
</body>
</html>