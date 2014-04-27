<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示</title>
<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="body" >
<sf:form modelAttribute="userDto">
<table  width="947px" border="0" height="70px" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="27"></td>
    <td width="248" align="right"><img src="<%=request.getContextPath()%>/resources/image/logo.png" /></td>
    <td width="398">&nbsp;</td>
    <td width="274"><span class="phone1">全国客服热线：</span><span class="phone2">400-720-5677</span></td>
  </tr>
</table>

<table width="947px"  height="33px" align="center" cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td width="13" class="in_menu1">&nbsp;</td>
    <td width="34" class="in_menu3">&nbsp;</td>
    <td width="106" class="in_menu3">提示</td>
    <td width="782" class="in_menu3">&nbsp;</td>
    <td width="12" class="in_menu2">&nbsp;</td>
  </tr>
</table>
<table width="947px"  height="400px" align="center" cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td class="re_bg">
        <table  width="900px" border="0" height="300" align="center" cellpadding="0" cellspacing="0" class="re_word" bgcolor="#FFFFFF">
          <tr>
            <td width="31" rowspan="8">&nbsp;</td>
            <td width="869" height="37" align="center">密码修改成功&nbsp;(<a href="<%=request.getContextPath()%>/home/index">点此返回</a>)</td>
          </tr>
        </table>
    </td>
  </tr>
</table>
<table  width="947px"  height="33px" align="center" cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td class="in_bottom" align="center">版权所有 © 上海斐讯数据通信技术有限公司 2008-2013</td>
  </tr>
  <tr>
    <td><b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b></td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
</sf:form>
</body>
</html>