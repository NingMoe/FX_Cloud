<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>激活帮助</title>
<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
</head>

<body class="body" onload="init()">
<form name="registrationPost" method="post">
<table id="registerTable" width="946px" border="0" height="3988px" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="70" width="14">&nbsp;</td>
    <td ></td>
    <td align="right"><img src="<%=request.getContextPath()%>/resources/image/logo.png" /></td>
    <td width="297">&nbsp;</td>
    <td width="57">&nbsp;</td>
    <td width="281"><span class="phone1">全国客服热线：</span><span class="phone2">400-720-5677</span></td>
    <td  width="13">&nbsp;</td>
  </tr>
  <tr>
    <td height="12" class="re_edgeleft1"></td>
    <td class="re_edgeTop" width="34"></td>
    <td class="re_edgeTop"></td>
    <td class="re_edgeTop"></td>
    <td colspan="2" class="re_edgeTop"></td>
    <td width="13" class="re_edgeright1"></td>
  </tr>
  <tr class="re_bg">
    <td class="re_edgeleft2" height="42" >&nbsp;</td>
    <td>&nbsp;</td>
    <td class="re_title">一、正常激活步骤</td>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td class="re_edgeright3">&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="6" class="re_edgeleft2">&nbsp; </td>
    <td rowspan="6" class="re_bg">&nbsp;</td>
    <td class="re_bg"  height="20" valign="top">&nbsp;</td>
    <td class="re_bg" >&nbsp;</td>
    <td colspan="2" class="re_bg" >&nbsp;</td>
    <td rowspan="26" class="re_edgeright3">&nbsp;</td>
  </tr>
  <tr>
    <td  height="160" rowspan="2" valign="top" class="re_bg"><table width="241" height="88" border="0">
      <tr>
        <td width="31" height="84" align="center" valign="top" class="help_circle1">&nbsp;</td>
        <td width="140" class="help_center" align="center">注册（快速向导）</td>
        <td width="56" ><img src="<%=request.getContextPath()%>/resources/image/arrow.jpg" width="54" height="38" /></td>
      </tr>
    </table></td>
    <td rowspan="2" align="center" valign="top" class="re_bg"><table width="263" height="88" border="0">
      <tr>
        <td width="32" height="84" align="center" valign="top" ><img src="<%=request.getContextPath()%>/resources/image/circle2.jpg" />&nbsp;</td>
        <td width="163" class="help_center" align="center">确认云状态（运行状态）</td>
        <td width="54" ><img src="<%=request.getContextPath()%>/resources/image/arrow.jpg" width="54" height="38" /></td>
      </tr>
    </table></td>
    <td height="100" colspan="2" align="center" valign="top" class="re_bg"><table width="290" height="88" border="0">
      <tr>
        <td width="32" height="84" align="center" valign="top" ><img src="<%=request.getContextPath()%>/resources/image/circle3.jpg" />&nbsp;</td>
        <td width="151" class="help_center" align="center">获取激活码（云功能）</td>
        <td width="93" >&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="75" colspan="2" align="center" valign="top" class="re_bg"><table width="290" height="71" border="0">
      <tr>
        <td width="32" height="67"  align="center" valign="top" >&nbsp;</td>
        <td width="151"  align="center"><img src="<%=request.getContextPath()%>/resources/image/arrow2.jpg" width="38" height="51" /></td>
        <td width="93" >&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="4" class="re_bg" ></td>
  </tr>
  <tr>
    <td class="re_bg" ><table width="241" height="88" border="0">
      <tr>
        <td width="31" height="84" align="center" valign="top" ><img src="<%=request.getContextPath()%>/resources/image/circle6.jpg" /></td>
        <td width="140" class="help_center" align="center">激活成功</td>
        <td width="56" ><img src="<%=request.getContextPath()%>/resources/image/arrow3.jpg" width="54" height="38" /></td>
      </tr>
    </table></td>
    <td class="re_bg" valign="top" align="center"><table width="263" height="88" border="0">
      <tr>
        <td width="32" height="84" align="center" valign="top" ><img src="<%=request.getContextPath()%>/resources/image/circle5.jpg" />&nbsp;</td>
        <td width="163" class="help_center" align="center"> 填写激活码（填写激活验证框）</td>
        <td width="54" ><img src="<%=request.getContextPath()%>/resources/image/arrow3.jpg" width="54" height="38" /></td>
      </tr>
    </table></td>
    <td colspan="2" class="re_bg" align="center" valign="top"><table width="290" height="88" border="0">
      <tr>
        <td width="32" height="84" align="center" valign="top" ><img src="<%=request.getContextPath()%>/resources/image/circle4.jpg" />&nbsp;</td>
        <td width="151" class="help_center" align="center">选择激活链接（激活邮件）</td>
        <td width="93" >&nbsp;</td>
      </tr>
    </table></td>
    </tr>
  <tr>
    <td colspan="4" class="re_bg" >&nbsp;</td>
  </tr>
  
  <tr class="re_bg">
    <td height="24" rowspan="20" class="re_edgeleft2">&nbsp;</td>
    <td rowspan="20" class="re_bg">&nbsp;</td>
    <td  height="62" class="re_title">二、详细激活过程</td>
    <td class="re_bg">&nbsp;</td>
    <td colspan="2" class="re_bg">&nbsp;</td>
  </tr>
  <tr class="re_bg">
    <td class="re_word" height="50">第一步：填写注册信息并保存；</td>
    <td class="re_bg">&nbsp;</td>
    <td colspan="2" class="re_bg">&nbsp;</td>
  </tr>
  <tr class="re_bg">
    <td height="400" colspan="4"  align="center"><img src="<%=request.getContextPath()%>/resources/image/1.png" width="644" height="380" /></td>
    </tr>
  <tr class="re_bg">
    <td height="64" colspan="4"  class="re_word" >第二步：注册成功后，系统自动跳转至运行状态（
	最下面一行为云状态），提示到云功能页面激活邮箱；</td>
  </tr>
  <tr class="re_bg">
    <td height="50" colspan="4"  class="re_word" align="center" ><img src="<%=request.getContextPath()%>/resources/image/2.jpg" width="818" height="208" /></td>
  </tr>
  <tr class="re_bg">
    <td height="67" colspan="4"  class="re_word" >第三步：在“系统工具”子页面中的“云功能”页面点击“获取激活码”按钮；</td>
  </tr>
  <tr class="re_bg">
    <td height="55" colspan="4" class="re_word" align="center"><img src="<%=request.getContextPath()%>/resources/image/3.jpg" width="850" height="361" /></td>
  </tr>
  <tr class="re_bg">
    <td height="43" colspan="4" class="re_word">&nbsp;</td>
  </tr>
  <tr class="re_bg">
    <td height="71" colspan="4" class="re_word" align="center"><img src="<%=request.getContextPath()%>/resources/image/4.jpg" width="855" height="365" /></td>
  </tr>
  <tr class="re_bg">
    <td height="71" colspan="4" class="re_word">第四步：打开注册邮箱，可以收到激活邮件，选择要激活的账号，点击链接，会跳出输入激活码框；</td>
  </tr>
  <tr class="re_bg">
    <td height="40" colspan="4"  class="re_word" align="center"><img src="<%=request.getContextPath()%>/resources/image/5.jpg" width="788" height="145" /></td>
  </tr>
  <tr class="re_bg">
    <td height="44" colspan="4"  class="re_word">&nbsp;</td>
  </tr>
  <tr class="re_bg">
    <td height="73" colspan="4"  class="re_word" align="center"><img src="<%=request.getContextPath()%>/resources/image/6.jpg" width="769" height="460" /></td>
  </tr>
  <tr class="re_bg">
    <td height="73" colspan="4"  class="re_word">第五步：输入激活码，激活成功</td>
  </tr>
  <tr class="re_bg">
    <td height="48" colspan="4"  class="re_word" align="center"><img src="<%=request.getContextPath()%>/resources/image/7.jpg" width="765" height="456" /></td>
  </tr>
  <tr class="re_bg">
    <td height="65" colspan="4"  class="re_word">第六步：打开路由器页面，重新登录已激活的邮箱</td>
  </tr>
  <tr class="re_bg">
    <td height="46" colspan="4"  class="re_word" align="center"><img src="<%=request.getContextPath()%>/resources/image/8.png" width="688" height="332" /></td>
  </tr>
  <tr class="re_bg">
    <td height="66" colspan="4"  class="re_word"><p>第七步：运行状态查看登录成功 </p></td>
  </tr>
  <tr class="re_bg">
    <td height="58" colspan="4"  class="re_word" align="center"><img src="<%=request.getContextPath()%>/resources/image/9.png" width="668" height="159" /></td>
  </tr>
  <tr class="re_bg">
    <td height="58" colspan="4"  class="re_word" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td height="13" class="re_edgeleft3"></td>
    <td class="re_bg"></td>
    <td class="re_edgeDown3"></td>
    <td class="re_bg"></td>
    <td colspan="2" class="re_bg"></td>
    <td class="re_edgeright2"></td>
  </tr>
</table>
</form>
</body>
</html>
