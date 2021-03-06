<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/base/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/article.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/zTree/zTreeStyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/core/jquery.cms.keywordinput.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.keywordinput.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery-ui-1.10.0.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/xheditor/xheditor-1.1.14-zh-cn.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/uploadify/uploadify.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/easyui/themes/icon.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/css/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/css/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/topicAdd.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/city.js"></script>
<script>
	$(function(){
		setup();
	});
</script>
</head>
<body>
<input type="hidden" id="sid" value="<%=session.getId()%>"/>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;background:#eee;z-index: 999;border:1px solid #999">
	<ul id="mytree" class="ztree" style="margin-top:0;"></ul>
</div>
<div id="container">
<div id="contents">
<input type="hidden" id="ctx" value="<%=request.getContextPath() %>"/>
	<h3 class="admin_link_bar" style="text-align:center">
	<span>添加文章功能</span>
	</h3>
	<sf:form method="post" modelAttribute="topic" id="addForm">
	<sf:hidden path="publishRange" id="publishRange" />
	<table width="980" cellspacing="0" cellPadding="0" id="addTable">
		<tr>
			<td class="rightTd" width="120px">消息标题:</td>
			<td class="leftTd">
			<sf:input path="title" size="80"/><sf:errors cssClass="errorContainer" path="title"/></td>
		</tr>
		<tr>
			<td class="rightTd">发布开始时间:</td>
			<td class="leftTd">
				<sf:input path="publishStartDate" id="publishStartDate"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">发布结束时间:</td>
			<td class="leftTd">
				<sf:input path="publishEndDate" id="publishEndDate"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">速率:</td>
			<td class="leftTd">
				<sf:input path="publishRate" id="publishRate"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">发布范围:</td>
			<td class="leftTd">
                <select id="s1"><option>省份</option></select>
				<select id="s2"><option>地级市</option></select>
				<select id="s3"><option>市、县级市、县</option></select>   
				<span style="color:red;">当前范围：${range }</span>            
			</td>
		</tr>
		<tr>
			<td class="rightTd">文章附件</td>
			<td class="leftTd">
				<div id="attachs"></div>
				<input type="file" id="attach" name="attach"/>
				<input type="button" id="uploadFile" value="上传文件"/>
			</td>
		</tr>
		<tr>
		<td colspan="2">已传附件</td>
		</tr>
		<tr>
		<td colspan="2">
		<table id="ok_attach" width="890px" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
				<Td>文件名缩略图</Td>
				<td width="180">文件名</td>
				<td>文件大小</td>
				<td>主页图片</td>
				<td>栏目图片</td>
				<td>附件信息</td>
				<td width="160">操作</td>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${atts }" var="att">
				<tr>
					<td>
						<c:if test="${att.isImg eq 1 }">
							<img src="<%=request.getContextPath() %>/resources/upload/thumbnail/${att.newName}"/>
						</c:if>
						<c:if test="${att.isImg ne 1 }">
							普通类型附件
						</c:if>
					</td>
					<td>
						${att.oldName }
					</td>
					<td>
						${att.size/1024}K
					</td>
					<c:if test="${att.isImg eq 1 }">
						<td><input type='checkbox' value="${att.id }" name='indexPic' class='indexPic' <c:if test="${att.isIndexPic eq 1 }">checked="checked"</c:if>></td>
						<td><input type='radio' value="${att.id }" name='channelPicId' <c:if test="${att.id eq topicDto.channelPicId }"> checked="checked"</c:if>></td>
					</c:if>
					<c:if test="${att.isImg ne 1 }">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</c:if>
					<td><input type='checkbox' value="${att.id }" name='isAttach' class='isAttach' <c:if test="${att.isAttach eq 1 }">checked="checked"</c:if>></td>
					<td><a href='#' class='list_op insertAttach' title='${att.id}' isImg="${att.isImg }" 
						name="${att.newName }" oldName="${att.oldName }">插入附件</a>&nbsp;<a href='#' title="${att.id }" class='list_op deleteAttach delete'>删除附件</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</td>
		</tr>
		<tr>
			<td colspan="2">文章内容</td>
		</tr>
		<tr>
			<td colspan="2">
			<sf:textarea path="content" rows="25" cols="110"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="centerTd"><input type="button" id="addBtn" value="更新文章"/><input type="reset"/></td>
		</tr>
	</table>
	</sf:form>
</div>
</div>
</body>
</html>