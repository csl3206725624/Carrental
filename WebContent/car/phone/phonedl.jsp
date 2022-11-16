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

<title>手机验证码登录 </title>
<style type="text/css">
  .pass{
     position: relative;
     left:450px;
     top:40px;
  }
	
	#zc,#fx,#fhym
	{
		color: white;
		font-size: 15px;
	}

</style>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="loginWraper">
  <div id="loginform" class="loginBox">
  
  <div style="display: none;" id="">
  	<form id="diyi" action="<%=request.getContextPath()%>/login.do?method=hq" method="post">
  	<input type="text" name = "hao" id="phone1">
 	 </form>
  </div>
  <% String  phoner=(String)request.getAttribute("phoner"); %>
<input style="display: none;"  id="thedai" type="text" value="<%=request.getAttribute("thedai") %>">
   
    <form class="form form-horizontal" action="<%=request.getContextPath()%>/login.do?method=sjdl" method="post">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="phone2" name="phone2" type="text" placeholder="用户手机号码 " value="<%=phoner%>" class="input-text size-L">
          <input type="button" value="获取验证码" class="btn btn-success" id="dier" onclick="checkForm()">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="" name="yzm" type="text" placeholder="验证码" class="input-text size-L">
          <div> &nbsp; &nbsp;</div>
        </div>
        <div class="pass"></div>
         </div>
         
        <div class="formControls col-xs-8" style="width: 100%;">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath() %>/login.do?method=phone" id="zc" >前往注册</a>
        &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           
           
        <a href="<%=request.getContextPath() %>/login.do?method=page" id="fhym" >返回账号登陆</a>
           
         </div>
     <br>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
      	
     
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
        </div>
      </div>
      
      
    </form>
  </div>
</div>
<div class="footer">汽车租赁后台管理系统</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>

</body>

<script type="text/javascript">

    $(document).ready(function(){  
      var btn = document.getElementById('dier');
      var dai = document.getElementById('thedai').value;
      if(dai == 1){
       var time = 59;
      }
      else {
      var time = 0;
  }
     
    
        btn.disabled = true;
           // 开启定时器
          
           
             var timer = setInterval(function () {
                    // 判断剩余秒数
                    if (time == 0) {
                        // 清除定时器和复原按钮
                        clearInterval(timer);
                        btn.disabled = false;
                        btn.value = '获取验证码';
                    } else {
                        btn.value = '还剩下' + time + '秒';
                        time--;
                    }
                }, 1000);
      
           

    });  
</script>

<script type="text/javascript"> 

function checkForm(){  
	
	var phone2= document.getElementById('phone2').value;
	
	   document.getElementById('phone1').value = phone2;
	   var user= document.getElementById('phone1').value;
	   if(user == null){  
	       //验证不通过 
	     return false;  
	   }  
	   document.getElementById("diyi").submit();  
	} 


  var bt01 = document.getElementById("bt01"); 
  bt01.onclick = function() { 
    bt01.disabled = true;  //当点击后倒计时时候不能点击此按钮 
    var time = 5;  //倒计时5秒 
    var timer = setInterval(fun1, 1000);  //设置定时器 
    function fun1() { 
      time--; 
      if(time>=0) { 
        bt01.innerHTML = time + "s后重新发送"; 
      }else{ 
        bt01.innerHTML = "重新发送验证码"; 
        bt01.disabled = false;  //倒计时结束能够重新点击发送的按钮 
        clearTimeout(timer);  //清除定时器 
        time = 5;  //设置循环重新开始条件 
      } 
    } 
  } 
</script>
</html>