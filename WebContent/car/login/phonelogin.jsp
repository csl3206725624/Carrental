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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link href="static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<
 type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>后台登录 </title>
<style type="text/css">
  .pass{
     position: relative;
     left:450px;
     top:40px;
  }

	#Yzm
	{
		height:40px;
		width:280px;
		font-size:14px;
	}
	.btn-list-btn
	{
		height:40px;
		width:77px;
		background-color: white;
		font-size:14px;
		
	}
	
	#fhym
	{
		color:white;	
	}
	
</style>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="1111111111111" />
<div class="loginWraper">
  <div id="loginform" class="loginBox">

   
    <form class="form form-horizontal" name="form1" id="form1" action="<%=request.getContextPath()%>/login.do?method=register" method="post">
     
     	
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="" name="manager_name" type="text" placeholder="&nbsp;请输入你想要注册的账号"  required class="input-text size-L">
        </div>
 
     	<br><br>
      
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="" name="manager_sex" type="text" placeholder="&nbsp;请输入你的性别"  required class="input-text size-L">
        </div>
    
      	<br><br>
     
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="" name="manager_phone" type="text" placeholder="&nbsp;请输入手机号码"  required class="input-text size-L">
        </div>
    
      	<br><br>
     
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="" name="manager_carid" type="text" placeholder="&nbsp;请输入身份证号"  required class="input-text size-L">
        </div>
      
      	<br><br>
      
       <label class="form-label col-xs-3"><i class="Hui-iconfont"> &#xe605;</i></label>
        <div class="formControls col-xs-8">
      	<input  type="text" placeholder="&nbsp;请输入邮箱"  id="email" type="text" name="email" class="input-text size-L">
     	</div>
     	
     	<br><br>
      
        <label class="form-label col-xs-3"><i class="Hui-iconfont"> &#xe605;</i></label>
        <div class="formControls col-xs-8">
        <input  type="text" placeholder=" &nbsp;请输入验证码" class=" web-form-input " required="required" id="Yzm" >
         <input type = "submit" id="code"  value="点击验证" class="btn-list-btn"  οnclick="createCode()"/>
         </div>
       	<br><br>
           
          <div> &nbsp; &nbsp;</div>
        <div class="pass"></div>
        <div class="formControls col-xs-8" style="width: 100%;" >
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath() %>/login.do?method=page" id="fhym" >返回账号登陆</a>
       
         </div>
     <br><br><br>
     
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
        </div>
     
      
    </form>
  </div>
</div>
<div class="footer">汽车租赁后台管理系统</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script>
//邮箱
//如果输入的是邮箱,那么边框颜色为绿色,否则为红色

// 获取输入框
var email = document.getElementById("email");
// 获取键盘抬起事件
email.onkeyup = function(){
  // 判断这个文本框输入的是不是邮箱
  var reg = /^[0-9a-zA-Z_.-]+[@][0-9a-zA-Z_.-]+([.][a-zA-Z]+){1,2}$/;
  if(reg.test(this.value) ){
    this.style.border = "1px solid green";
  }else{
    this.style.border = "1px solid red";
  }
}




//验证码
var code ; //在全局定义验证码
//产生验证码
function createCode(){
    code = "";
    var codeLength = 4;//验证码的长度
    var checkCode = document.getElementById("code");
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R', 'S','T','U','V','W','X','Y','Z');//随机数
    for(var i = 0; i < codeLength; i++) {//循环操作
        var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）
        code += random[index];//根据索引取得随机数加到code上
    }
    checkCode.value = code;//把code值赋给验证码
}
//校验验证码
document.getElementById("Yzm").addEventListener("change",validate);

function validate(){
    var inputCode = document.getElementById("Yzm").value.toUpperCase(); //取得输入的验证码并转化为大写
    if(inputCode.length <= 0) { //若输入的验证码长度为0
        alert("请输入验证码！"); //则弹出请输入验证码
        $("#Yzm").focus();
        YZM = false;
    }
    else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时
        alert("验证码输入错误！@_@"); //则弹出验证码输入错误
        createCode();//刷新验证码
        $("#Yzm").val("");//清空文本框
        $("#Yzm").focus();//重新聚焦验证码框
        YZM = false;
    }
    else { //输入正确时
        $("#Yzm").blur();//绑定验证码输入正确时要做的事
        YZM = true;

    }
};
</script>
</body>
</html>