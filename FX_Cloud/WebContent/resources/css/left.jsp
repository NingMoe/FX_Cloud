<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>left</title>
<link href="<%=request.getContextPath()%>/resources/css/page.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
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
				$(".title02 .fx_menu_title_in").attr("src","image/arrow_1.png");
				$(".title02 .fx_menu_title_file").attr("src","image/left_title_icon.png");
				$(this).find(".fx_menu_title_file").attr("src","image/left_title_icon2.png");
				if(f_li){
					$("#out_i_"+num).slideDown(400);
					$(this).find(".fx_menu_title_in").attr("src","image/arrow_2.png");
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
				$(this).attr("class","current")
			});
			
		});
	});
</script>
</head>

<body topmargin="0" leftmargin="0">
<div class="system_cont">
  <div class="system_left">
     <ul> 
        <li class="title01">>>&nbsp;&nbsp;���ܲ˵�</li>
     </ul> 
     <ul> 
        <a href="#"><li class="title02"><label><img class="fx_menu_title_in" src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon2.png" />���ݹ���</li></a>
     </ul>
     <ul class="out">
          <li><a href="news_list.htm" target="mainFrame"><img src="image/left_icon.png" />��������</a></li>
          <li><a href="video_list.htm" target="mainFrame"><img src="image/left_icon.png" />��Ƶ����</a></li>
          <li><a href="magazine_list.htm" target="mainFrame"><img src="image/left_icon.png" />�Ѷ�ڿ�</a></li>
          <li><a href="problem_list.htm" target="mainFrame"><img src="image/left_icon.png" />��������</a></li>
          <li><a href="service_list.htm" target="mainFrame"><img src="image/left_icon.png" />���߷���</a></li>
    </ul>   
     <ul> 
     	<a href="category_list.htm" target="mainFrame">
       	<li class="title02"><label><img class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />��Ŀ����</li>
     	</a>
     </ul>
      <ul class="out">
      </ul>
     <ul> 
        <a href="#"><li class="title02"><label><img  class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />��Ʒ����</li></a>
     </ul>
	 	<ul class="out">
          <li><a href="product_config_list.htm" target="mainFrame"><img src="image/left_icon.png" />��Ʒ���</a></li>
          <li><a href="product_list.htm" target="mainFrame"><img src="image/left_icon.png" />��Ʒ��Ϣ</a></li>
     </ul>
     <ul> 
        <a href="solution_list.htm" target="mainFrame">
       <li class="title02"><label><img class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />���񼰽����������</li></a>
     </ul>
	 	<ul class="out">
          <li><a href="solution_config_list.htm" target="mainFrame"><img src="image/left_icon.png" />���񼰽���������</a></li>
          <li><a href="solution_list.htm" target="mainFrame"><img src="image/left_icon.png" />���񼰽������</a></li>
          <li><a href="solution_case_list.htm" target="mainFrame"><img src="image/left_icon.png" />�ɹ�����</a></li>
     </ul>
	 <ul> 
        <a href="product_period_list.htm" target="mainFrame">
       <li class="title02"><label><img class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />��Ʒ�����ڹ���</li></a>
     </ul>
     <ul class="out">
    </ul>
     <ul> 
        <a href="#"><li class="title02"><label><img class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />��Ƹ����</li></a>
     </ul>
	 	<ul class="out">
          <li><a href="job_list.htm" target="mainFrame"><img src="image/left_icon.png" />��Ƹ��Ϣ</a></li>
          <li><a href="applicant_list.htm" target="mainFrame"><img src="image/left_icon.png" />�˲ſ�</a></li>
          <li><a href="job_news_list.htm" target="mainFrame"><img src="image/left_icon.png" />��Ƹ��̬</a></li>
        </ul>   
     <ul> 
        <a href="download_list.htm" target="mainFrame"><li class="title02"><label><img class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />��������</li></a>
     </ul>
     <ul class="out">
    </ul>
     <ul> 
        <a href="repair_list.htm" target="mainFrame"><li class="title02"><label><img class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />ά������</li></a>
     </ul>
     <ul class="out">
    </ul>
     <ul> 
        <a href="ad_list.htm" target="mainFrame"><li class="title02"><label><img class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />������</li></a>
     </ul>
     <ul class="out">
    </ul>
     <ul> 
        <a href="#"><li class="title02"><label><img class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />�û�����</li></a>
     </ul>
	 	<ul class="out">
          <li><a href="user_list.htm" target="mainFrame"><img src="image/left_icon.png" />�û�����</a></li>
          <li><a href="#"><img src="image/left_icon.png" />��ɫ\Ȩ�޹���</a></li>
        </ul>   
     <ul> 
        <a href="#"><li class="title02"><label><img class="fx_menu_title_in"  src="image/arrow_1.png"></label><span></span><img class="fx_menu_title_file" src="image/left_title_icon.png" />ϵͳ����</li></a>
     </ul>
	 	<ul class="out">
          <li><a href="#"><img src="image/left_icon.png" />��־����</a></li>
          <li><a href="#"><img src="image/left_icon.png" />���ù���</a></li>
          <li><a href="sys_dictionary_list.htm" target="mainFrame"><img src="image/left_icon.png" />�����ֵ�</a></li>
        </ul>   
  </div>
  <p class="left_icon"><a href="#"><img src="image/mini-left.gif" /></a></p>
</div>
</body>
</html>
