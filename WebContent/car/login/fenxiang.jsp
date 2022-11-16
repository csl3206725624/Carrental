<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" /><script type="text/javascript" src="。/lib/jquery/1.9.1/jquery.js"></script>

<link href="static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

<title>分享页面</title>
<style type="text/css">
  .pass
  {
     position: relative;
     left:450px;
     top:40px;
  }
	
  #p1s
  {
  	font-size: 25px;
  	font-family: 黑体;
  	color: white;
  	text-align: center;
  }
  #p2s,#p3s
  {
	font-size: 18px;
  	font-family: 黑体;
  	color: white;
  
  }
  #dwqdl
  {
  	text-align: center;
  }

</style>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="loginWraper">
  <div id="loginform" class="loginBox">
	<div>
    	<div class="bshare-custom">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击分享:&nbsp;<a title="分享到QQ空间" class="bshare-qzone"></a>
			<a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
			<a title="分享到人人网" class="bshare-renren"></a>
			<a title="分享到腾讯微博" class="bshare-qqmb"></a>
			<a title="分享到网易微博" class="bshare-neteasemb"></a>
			<a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a>
			<br/><br/>
			<hr/>
		</div>
		<script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/button.js#style=-1&uuid=&pophcol=2&lang=zh">
			
		</script>
		<script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js">
		</script>	
	</div>
	
	<div>
	<br>
		<p id="p1s">汽车租赁公司简介</p>
		<p id="p2s"> &nbsp;&nbsp;&nbsp;汽车租赁管理系统，根据行业背景而生产出来的，符合当下对社会的需求，让客户一触就懂。</p>
		<p id="p3s"> &nbsp;&nbsp;&nbsp;租赁汽车可以节省大笔投资，不必支付例如养路费、保险费等之类的费用。美好你我生活!</p>
	</div>
	
	<br/><br/>
	<form action="<%=request.getContextPath() %>/login.do?method=page" method="post">
		<div class="formControls col-xs-8" style="width: 100%;" id="dwqdl">
			<input type="submit"  value=" 点我去登录 "  class="btn btn-primary radius"/>
    	</div>
	</form>
  </div>
</div>
<div class="footer">汽车租赁后台管理系统</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>

</body>
</html>