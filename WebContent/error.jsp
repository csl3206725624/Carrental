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


<title>后台登录 </title>
<style type="text/css">
  .pass{
     position: relative;
     left:450px;
     top:40px;
  }

</style>

<title>验证码 </title>

<style type="text/css">
   div{
      margin-top: 10px;
   }
</style>

</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="loginWraper">
  <div id="loginform" class="loginBox">
	
	
	<div class="row cl" style="margin: 0 130px;">

		
		<div class="formControls col-xs-8 col-sm-9" style="width: 480px; position: relative;top: 40px; left:-60px;">
             	&nbsp;&nbsp;<span style=" font-size: 20px;">错误提示信息：</span>
             	<br><hr><br>
							<span style="color:black; font-size: 19px; padding: 10px;"><%=request.getAttribute("error") %></span> <br> <br> 
		</div>
	 </div>
   <div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3" style="position: relative;left:100px; top:80px;">
			<input name="Submit" type="submit" class="btn btn-success radius size-L" value="&nbsp;返&nbsp;&nbsp;&nbsp;&nbsp;回&nbsp;" onClick="history.back(-1)">
			
		</div>
	</div>

  </div>
</div>
<div class="footer">汽车租赁后台管理系统</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>

</body>
</html>