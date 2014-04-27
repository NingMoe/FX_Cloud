<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>left</title>
<link href="<%=request.getContextPath()%>/resources/css/page.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var i = 0;
		$(".title02").each(function(){
			$(this).attr("num",i);
			$(this).click(function(){
				var num = $(this).attr("num");
				$(".out").slideUp(400);
				var f_li = $("#out_i_"+num).find("li").length;
				$(".title02").css({color:"#000000"});
				$(this).css({color:"#fd7a18"});
				$(".title02 .fx_menu_title_in").attr("src","<%=request.getContextPath()%>/resources/image/arrow_1.png");
				$(".title02 .fx_menu_title_file").attr("src","<%=request.getContextPath()%>/resources/image/left_title_icon.png");
				$(this).find(".fx_menu_title_file").attr("src","<%=request.getContextPath()%>/resources/image/left_title_icon2.png");
				if(f_li){
					$("#out_i_"+num).slideDown(400);
					$(this).find(".fx_menu_title_in").attr("src","<%=request.getContextPath()%>/resources/image/arrow_2.png");
				}
			});
			i++;
		});
		i = 0;
		$(".out").each(function(){
			$(this).attr("num",i);
			$(this).attr("id","out_i_"+i);
			$(this).slideUp(1);
			i++;
		});
		$(".out li a").each(function(){
			$(this).click(function(){
				$(".out li a").removeAttr("class");
				$(this).attr("class","current");
			});
			
		});
	});
</script>
</head>

<body topmargin="0" leftmargin="0">
<div class="system_cont">
  <div class="system_left">
     <ul> 
        <li class="title01">>>&nbsp;&nbsp;功能菜单</li>
     </ul> 
    <!-- <ul> 
        <a href="#"><li class="title02"><label><img class="fx_menu_title_in" src="<%=request.getContextPath()%>/resources/image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="<%=request.getContextPath()%>/resources/image/left_title_icon2.png" />用户管理</li></a>
     </ul>
     <ul class="out">
          <li><a href="<%=request.getContextPath()%>/user/video_list" target="mainFrame"><img src="<%=request.getContextPath()%>/resources/image/left_icon.png" />注册用户信息</a></li>
    </ul>    --> 


     <ul> 
        <a href="#"><li class="title02"><label><img class="fx_menu_title_in"  src="<%=request.getContextPath()%>/resources/image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="<%=request.getContextPath()%>/resources/image/left_title_icon.png" />消息发布</li></a>
     </ul>
	 	<ul class="out">
          <li><a href="<%=request.getContextPath()%>/notice/notice_add" target="mainFrame"><img src="<%=request.getContextPath()%>/resources/image/left_icon.png" />信息添加</a></li>
          <li><a href="<%=request.getContextPath()%>/notice/notice_list" target="mainFrame"><img src="<%=request.getContextPath()%>/resources/image/left_icon.png" />信息显示</a></li>
         <!-- <li><a href="sys_dictionary_list.htm" target="mainFrame"><img src="<%=request.getContextPath()%>/resources/image/left_icon.png" />系统管理三</a></li>  --> 
        </ul>   
        
        
        <ul> 
        <a href="#"><li class="title02"><label><img class="fx_menu_title_in"  src="<%=request.getContextPath()%>/resources/image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="<%=request.getContextPath()%>/resources/image/left_title_icon.png" />新版消息发布</li></a>
     </ul>
	 	<ul class="out">
          <li><a href="<%=request.getContextPath()%>/news/add" target="mainFrame"><img src="<%=request.getContextPath()%>/resources/image/left_icon.png" />信息添加</a></li>
          <li><a href="<%=request.getContextPath()%>/news/list" target="mainFrame"><img src="<%=request.getContextPath()%>/resources/image/left_icon.png" />信息显示</a></li>
        </ul>   
        <ul> 
        <a href="#"><li class="title02"><label><img class="fx_menu_title_in"  src="<%=request.getContextPath()%>/resources/image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="<%=request.getContextPath()%>/resources/image/left_title_icon.png" />后台用户管理</li></a>
     </ul>
	 	<ul class="out">
          <li><a href="<%=request.getContextPath()%>/manager/add" target="mainFrame"><img src="<%=request.getContextPath()%>/resources/image/left_icon.png" />后台用户添加</a></li>
          <li><a href="<%=request.getContextPath()%>/manager/list" target="mainFrame"><img src="<%=request.getContextPath()%>/resources/image/left_icon.png" />后台用户显示</a></li>
        </ul>   
  </div>
  <p class="left_icon"><img src="<%=request.getContextPath()%>/resources/image/mini-left.gif" /></p>
</div>

</body>
</html>
