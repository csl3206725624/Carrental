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

<title>跳转页面</title>
<style type="text/css">
  .pass{
     position: relative;
     left:450px;
     top:40px;
  }
</style>

<meta http-equiv="refresh" content='3;url=<%=request.getContextPath()%>/login.do?method=page' content="text/html; charset=UTF-8">
<script type='text/javascript'>
 var i=3;
 function getTime(){
 document.getElementById('num').innerHTML="<font color='red'>"+i+"</font>";
 i-=1;
 var x=setTimeout('getTime()',1000)//1000毫秒=1秒
 if(i<=0){
 clearTimeout(x);
 }
 }
 window.onload=getTime;//开始执行倒计时
</script>

<style type="text/css">

#tzym
{
 font-size: 25px;
 font-family: 楷体;
 color: white;
 font-weight:bold; /*加粗*/
}

</style>

</head>
<body onload="getTime()" id="tzym">
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="loginWraper">
  <div id="loginform" class="loginBox">
	<div>
	   <center >
	   <br>
		   	<p>你好，恭喜你注册成功 !!</p>
		   	<p>你的登录密码已经快马加鞭发送到你的邮箱</p>
		   	<p>请你耐心等待，注意查看邮箱 !!! </p ><br>
		   	注意 ! 此页面将在<span id='num' style='display=inline;'>3</span>秒后跳转至登录页面
 		</center>
	</div>
  </div>
</div>
<div class="footer">汽车租赁后台管理系统</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>

</body>
</html>