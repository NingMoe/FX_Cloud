<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更改密码</title>
<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">

</script>
</head>
<body class="body" onLoad="init()">
<sf:form  method="post" modelAttribute="user">
<table id="registerTable" width="946px" border="0" height="527px" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="70" width="14">&nbsp;</td>
    <td ></td>
    <td colspan="2" align="right"><img src="<%=request.getContextPath()%>/resources/image/logo.png" /></td>
    <td width="297">&nbsp;</td>
    <td width="57">&nbsp;</td>
    <td width="281"><span class="phone1">全国客服热线：</span><span class="phone2">400-720-5677</span></td>
    <td width="13">&nbsp;</td>
  </tr>
  <tr>
    <td height="12" class="re_edgeleft1"></td>
    <td class="re_edgeTop" width="34"></td>
    <td width="223" class="re_edgeTop"></td>
    <td colspan="2" class="re_edgeTop"></td>
    <td colspan="2" class="re_edgeTop"></td>
    <td width="13" class="re_edgeright1"></td>
  </tr>
  <tr class="re_bg">
    <td class="re_edgeleft2" height="42" >&nbsp;</td>
    <td>&nbsp;</td>
    <td class="re_title">更改用户密码</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td rowspan="2" class="re_edgeright3">&nbsp;</td>
  </tr>
  <tr>
    <td height="65" class="re_edgeleft2">&nbsp;</td>
    <td class="re_bg">&nbsp;</td>
    <td colspan="3" rowspan="2"  class="re_noticeTd1">
        <table width="540" height="360" border="0" cellpadding="0" cellspacing="0" class="re_word">
          <tr>
            <td height="10" colspan="5"></td>
          </tr>
          <tr>
            <td width="33">&nbsp;</td>
            <td width="137">请输入注册邮箱：</td>
            <td width="19">&nbsp;</td>
            <td colspan="2"><sf:input type="text" path="mailAddress"/>&nbsp;<sf:errors path="mailAddress"/>${errorMail}</td>
          </tr>
        
          <tr>
            <td>&nbsp;</td>
            <td colspan="2" align="right" valign="top"><input type="submit" name="button" id="button" value="提交"  class="re_button" onClick="return submitData();"/></td>
            <td width="55">&nbsp;</td>
            <td width="296" valign="top"><input type="button" name="button2" id="button2" value="取消" class="re_button"/></td>
          </tr>
        </table>
    </td>
    <td colspan="2" class="re_bgRight2">&nbsp;</td>
  </tr>
  <tr>
    <td height="302" class="re_edgeleft2">&nbsp;</td>
    <td class="re_bg">&nbsp;</td>
    <td colspan="2" rowspan="2" class="re_bgRight">&nbsp;</td>
    <td rowspan="2" class="re_edgeright4">&nbsp;</td>
  </tr>
  <tr>
    <td height="24" class="re_edgeleft2">&nbsp;</td>
    <td class="re_bg">&nbsp;</td>
    <td class="re_bg">&nbsp;</td>
    <td colspan="2" class="re_edgeDown4">&nbsp;</td>
  </tr>
  <tr>
    <td height="13" class="re_edgeleft3"></td>
    <td class="re_edgeDown3"></td>
    <td class="re_edgeDown3"></td>
    <td colspan="2" class="re_edgeDown1"></td>
    <td colspan="2" class="re_edgeDown2"></td>
    <td class="re_edgeright2"></td>
  </tr>
</table>
<input type="hidden" id="area" name="area" value="${area}">
</sf:form>
</body>
</html>