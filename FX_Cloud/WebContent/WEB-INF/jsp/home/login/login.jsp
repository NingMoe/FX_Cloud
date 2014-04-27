<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">    
	function register(){		
		top.location.href="<%=request.getContextPath()%>/home/registration";
	}	
	</script>
</head>
<body class="body">
<sf:form name="loginPost" method="POST" modelAttribute="user">
<table id="loginTable" width="947px" border="0" height="447px" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="17" height="58px" ></td>
    <td width="248" colspan="2" align="right" class="logo" ><img src="<%=request.getContextPath()%>/resources/image/logo.png" /></td>
    <td width="285" colspan="2">&nbsp;</td>
    <td></td>
    <td>&nbsp;</td>
    <td align="right"><span class="phone1">全国客服热线：</span><span class="phone2">400-720-5677</span></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td width="17">&nbsp;</td>
  </tr>
  <tr>
    <td class="role1"  height="11px" width="17" ></td>
    <td colspan="2" class="edge1"></td>
    <td colspan="2" class="edge1"></td>
    <td colspan="5" class="edge1"></td>
    <td width="17" class="role2"></td>
  </tr>
  <tr>
    <td height="166px" rowspan="6" class="edge_left3">&nbsp;</td>
    <td colspan="2" class="background">&nbsp;</td>
    <td colspan="2" class="background">&nbsp;</td>
    <td width="32" class="background">&nbsp;</td>
    <td width="27" class="background">&nbsp;</td>
    <td width="249" class="bg_right1">&nbsp;</td>
    <td width="24" class="background">&nbsp;</td>
    <td width="47" class="background"></td>
    <td height="32px" class="edge_right1">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" class="background">&nbsp;</td>
    <td colspan="2" class="background">&nbsp;</td>
    <td class="loginbg_right4">&nbsp;</td>
    <td class="loginbg_right3">&nbsp;</td>
    <td class="loginbg_right2">&nbsp;</td>
    <td class="loginbg_right1">&nbsp;</td>
    <td class="background">&nbsp;</td>
    <td height="33px" class="edge_right1">&nbsp;</td>
  </tr>
  <tr>
    <td width="91px" class="background">&nbsp;</td>
    <td width="162px" class="bg_left1">&nbsp;</td>
    <td width="105px" class="bg_left2">&nbsp;</td>
    <td width="179px" class="background">&nbsp;</td>
    <td class="loginbg_left1">&nbsp;</td>
    <td class="loginbg_mid1">&nbsp;</td>
    <td class="loginbg_mid2">&nbsp;</td>
    <td class="loginbg_mid3">&nbsp;</td>
    <td class="loginbg_right5">&nbsp;</td>
    <td height="29px" class="edge_right1">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" class="background">&nbsp;</td>
    <td colspan="2" class="background">&nbsp;</td>
    <td class="loginbg_left1">&nbsp;</td>
    <td class="login_orange">&nbsp;</td>
    <td class="login_orange">&nbsp;</td>
    <td class="loginbg_mid4">&nbsp;</td>
    <td class="loginbg_right6">&nbsp;</td>
    <td height="19px" class="edge_right1">&nbsp;</td>
  </tr>
  <tr>
    <td class="background">&nbsp;</td>
    <td class="bg_left3">&nbsp;</td>
    <td class="bg_left4">&nbsp;</td>
    <td class="bg_left5">&nbsp;</td>
    <td class="loginbg_left1">&nbsp;</td>
    <td class="login_edge_top2">&nbsp;</td>
    <td class="login_edge_top1">&nbsp;</td>
    <td class="login_edge_right1">&nbsp;</td>
    <td class="loginbg_right7">&nbsp;</td>
    <td height="35px" class="edge_right2">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" class="background">&nbsp;</td>
    <td class="bg_left6">&nbsp;</td>
    <td class="bg_left7">&nbsp;</td>
    <td class="loginbg_left1">&nbsp;</td>
    <td class="login_edge_left1">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td class="loginbg_right8">&nbsp;</td>
    <td class="edge_right3" height="19px">&nbsp;</td>
  </tr>
  <tr>
    <td class="edge_left3">&nbsp;</td>
    <td  height="196px" colspan="2" rowspan="4" class="bg_bottom1">&nbsp;</td>
    <td colspan="2" rowspan="4" class="bg_bottom2">&nbsp;</td>
    <td class="loginbg_left1">&nbsp;</td>
    <td class="login_edge_left1">&nbsp;</td>
    <td><table width="243" height="73" border="0">
      <tr>
        <td width="79" class="word" align="right">电子邮箱：</td>
        <td width="154"><sf:input type="text" path="mailAddress" class="input_style"/></td>
        </tr>
      <tr>
        <td height="28" align="right" class="word">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
        <td> <sf:input type="password" path="password" class="input_style"/></td>
      </tr>
      <tr>
        <td height="15" align="right" class="word"></td>
        <td><font class="word">${errorMessage}<sf:errors path="mailAddress"/></font></td>
      </tr>
      <tr>
      	<td height="15" align="right" class="word"></td>
        <td><font class="word"><sf:errors path="password"/></font></td>
      </tr>
      <tr>
      	<td height="15" align="right" class="word"></td>
        <td><font class="word"><a href="<%=request.getContextPath()%>/home/mail">忘记密码</a></font></td>
      </tr>
    </table></td>
    <td>&nbsp;</td>
    <td class="background">&nbsp;</td>
    <td rowspan="4" class="edge_right1" >&nbsp;</td>
  </tr>
  <tr>
    <td height="36px" class="edge_left3"></td>
    <td class="loginbg_left1">&nbsp;</td>
    <td class="login_edge_left1">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td class="background">&nbsp;</td>
  </tr>
  <tr>
    <td height="41px" class="edge_left2"></td>
    <td class="loginbg_left1">&nbsp;</td>
    <td class="login_edge_left1">&nbsp;</td>
    <td align="center">
    	<input type="submit" name="user_login" value="登&nbsp;&nbsp;录"  class="button"/>&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="button" name="user_registration" value="立即注册"   class="button" onclick="return register()"/></td>
    <td>&nbsp;</td>
    <td class="background">&nbsp;</td>
  </tr>
  <tr>
    <td width="17" height="34px" class="edge_left1"></td>
    <td class="loginbg_left2">&nbsp;</td>
    <td class="login_edge_left2">&nbsp;</td>
    <td>&nbsp;</td>
    <td class="login_edge_right2">&nbsp;</td>
    <td class="background">&nbsp;</td>
  </tr>
  <tr>
    <td class="role4" width="17"></td>
    <td height="17px" colspan="2" class="edge_down"></td>
    <td colspan="2" class="edge_down"></td>
    <td colspan="5" class="edge_down"></td>
    <td height="17px" width="17" class="role3"></td>
  </tr>
</table>

</sf:form>
</body>
</html>