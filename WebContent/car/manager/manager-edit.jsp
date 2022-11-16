<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加管理员 - 管理员管理 </title>
<style type="text/css">
   div{
      margin-top: 10px;
   }
</style>

</head>
<body>
<article class="page-container">
	<form  action="<%=request.getContextPath()%>/manager.do?method=update" method="post">
	<%Map map = (Map)request.getAttribute("editMap"); %>>
	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理员名字：<span id="ajax_name" ></span></label>
		<div class="formControls col-xs-8 col-sm-9">
		<input type="hidden" class="input-text" value="<%=map.get("manager_id") %>"name="manager_id">
			<input type="text" class="input-text" value="<%=map.get("manager_name") %>" placeholder="" id="adminName" name="name" onblur="doAjax()"   onfocus="retianxie()">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>身份证号：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" value="<%=map.get("manager_carid") %>" placeholder="身份证号" id="carid" name="carid">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>初始密码：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="password" class="input-text" autocomplete="off" value="<%=map.get("manager_password") %>" placeholder="密码" id="password" name="password">
		</div>
	</div>

	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>
		<div class="formControls col-xs-8 col-sm-9 skin-minimal">
			<div class="radio-box">
				<input name="sex" type="radio" id="sex-1" value="男" <%=(map.get("manager_sex").equals("男"))? "checked":"" %>  /> 
				<label for="sex-1">男</label>
			</div>
			<div class="radio-box">
				<input type="radio" name="sex"  id="sex-2" value="女" <%=(map.get("manager_sex").equals("女"))? "checked":"" %> > 
				<label for="sex-2">女</label>
			</div>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="<%=map.get("manager_phone") %>" placeholder="" id="phone" name="phone">
		</div>
	</div>
<!-- 	<div class="row cl"> -->
<!-- 		<label class="form-label col-xs-4 col-sm-3">角色：</label> -->
<!-- 		<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;"> -->
<!-- 			<select class="select" name="adminRole" size="1"> -->
<!-- 				<option value="0">超级管理员</option> -->
<!-- 				<option value="1">总编</option> -->
<!-- 				<option value="2">栏目主辑</option> -->
<!-- 				<option value="3">栏目编辑</option> -->
<!-- 			</select> -->
<!-- 			</span> </div> -->
<!-- 	</div> -->
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			<a href="<%=request.getContextPath()%>/manager.do?method=query"><button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button></a>
		</div>
	</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-add").validate({
		rules:{
			adminName:{
				required:true,
				minlength:4,
				maxlength:16
			},
			password:{
				required:true,
			},
			password2:{
				required:true,
				equalTo: "#password"
			},
			sex:{
				required:true,
			},
			phone:{
				required:true,
				isPhone:true,
			},
			email:{
				required:true,
				email:true,
			},
			adminRole:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
	
	});
});
//1使用ajax
function doAjax(){
	  //alert("开始使用ajax")
	  var url="<%=request.getContextPath()%>/manager.do?method=testAjax";
	  //alert("url:"+url);
	  var manager_name=$("#adminName").val();
	  //alert("gname:"+gname);
	  $.post(url,{manager_name:manager_name},function(data){
		  //alert("data:"+data);
		  jiezhi(data);
	  });
}
//显示调用
  function jiezhi(data){
  	if(data=="yes"){
  		$("#ajax_name").html("<font color='green'>代号可以使用</font>")
  	}
  	else if(data=="no"){
  		$("#ajax_name").html("<font color='red'>代号已经存在不可以使用</font>")
  	}
  }
  //获得热点
  function retianxie(){
	  $("#adminName").val("");
	  $("#ajax_name").html("");
  }
  
  
  
</script> 
<!--/请在上方写此页面业务相关的脚本-->
<%@ include file="/footer.jsp"%>
</body>
</html>