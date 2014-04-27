
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>服务器后台管理系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login.css" type="text/css" />
	<script type="text/javascript">    
	function check(){		
		document.forms[0].submit();
	}
	function cleanContext(){		
		document.forms[0].username.value="";
		document.forms[0].password.value="";
	    return false;
	}	
	</script>
</head>

<body>
<p class="phicomm_logo"><img src="<%=request.getContextPath()%>/resources/image/phicomm.png" /></p>
<div class="login">
    <div class="login_cont">
    <p class="error"></p>
	<sf:form method="post" modelAttribute="manager">
		<table cellpadding="0" width="482px" align="left">
			<tr class="label" >
	    		<td width="160" style="text-align:right"><label style="text-align:right" ><strong>用户名:</strong></label></td>
	    		<td width="322"><sf:input type="text" class="input_login author_bg" path="username" name="username"/>
	    		          &nbsp;${message}<sf:errors path="username"/>
	    		</td>
	        </tr>
            <tr><td colspan="2" height="5px">&nbsp;</td></tr> 
	        <tr class="label" >
	       		<td width="160" style="text-align:right"><label style="text-align:right"><strong>密码:</strong></label></td>
	            <td width="322"><sf:input type="password" class="input_login password_bg" path="password" name="password"/>
	                      &nbsp;<sf:errors path="password"/></td>
	        </tr>
	        <tr><td colspan="2" height="5px">&nbsp;</td></tr>
            <tr class="submit"><td colspan="2" align="center">
	    	<input type="image" src="<%=request.getContextPath()%>/resources/image/login_but.png"/ onClick="check();">&nbsp;&nbsp;
	        <input type="image" src="<%=request.getContextPath()%>/resources/image/login_reset.png"/ onClick="return cleanContext();">
            </td></tr>   
	   </table>
	</sf:form>
    </div>
</div>


</body>
</html>