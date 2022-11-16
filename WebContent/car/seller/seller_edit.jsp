<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
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
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户修改</title>
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<p class="breadcrumb"> 用户表编辑 </p>
<div class="page-container">
	<form class="form form-horizontal" method="post" action="<%=request.getContextPath() %>/seller.do?method=edit" id="form-article-add">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">用户编号</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${map.seller_id }"  id="" name="seller_id">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">姓名</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${map.seller_name }"  id="" name="seller_name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">性别</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input name="seller_sex" type="radio" id="sex-1" value="男" ${ "男" eq map.seller_sex ? "checked" : "" }>男
				<input name="seller_sex" type="radio" id="sex-1" value="女" ${ "女" eq map.seller_sex ? "checked" : "" }>女
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">电话</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${map.seller_phone}"  id="" name="seller_phone">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">地址</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${map.seller_dizhi}"  id="" name="seller_dizhi">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">租车价格</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${map.seller_zc_money}"  id="" name="seller_zc_money">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">身份证号</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${map.seller_idertity}"  id="" name="seller_idertity">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">租车状态</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${map.seller_zc_zhuangtai}"  id="" name="seller_zc_zhuangtai">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">租车时间</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${map.seller_zc_time}"  id="" name="seller_zc_time">
			</div>
		</div>
		
		
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<input type="submit" value="保存并提交" class="btn btn-primary radius">
				<a href="<%=request.getContextPath()%>/seller.do?method=query"><button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button></a>
			</div>
		</div>
		
	</form>
	
</div>


<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>

<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script>
</body>
</html>