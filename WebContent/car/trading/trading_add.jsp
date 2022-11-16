<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>新增文章 - 资讯管理 - H-ui.admin v3.1</title>

<script type="text/javascript">
		         var flag = false;// 表单是否已经提交标识，默认为false
		          function dosubmit()
		           {
		        	  if(!flag)
			      		{
			      			flag=true;//标识已经点击
			      			document.getElementById("btn").disabled= "disabled";
			      			return true;
			      		}
			      		return false;
		         }
		</script>

<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<br>
<br>
	<form action="<%=request.getContextPath() %>/trading.do?method=add"  method="post" onsubmit="return dosubmit()">
		<table border="2px" style="width: 70%; " align="center">
			<tr>
				<td colspan="6" align="center"><h2>合作商明细</h2></td>
				<td><input type="hidden" name="token" value="${sessionToken}" />
			</tr>
			<tr>
				<td><h5>商户编号:</h5></td><td><input type="text" class="input-text" name="CaGongYing2" ></td>
				<td><h5>合作品牌:</h5></td><td><input type="text"  class="input-text" name="GY_name" ></td>
				<td><h5>合作方式:</h5></td><td><input type="text" class="input-text" name="GY_fs" ></td>
			</tr>
			
			<tr>
				<td><h5>合作时间:</h5></td><td><input type="text"  onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="commentdatemin" name="GY_Time" class="input-text Wdate"></td>
				<td><h5>合作地址:</h5></td><td><input type="text" class="input-text"  name="GY_Address"></td>
				<td><h5>电话联系:</h5></td><td><input type="text"   class="input-text"  name="GY_Phone"></td>
			</tr>
		
			<tr>
				<td colspan="6" align="center">
					<input type="submit" class="btn btn-primary radius" value='&nbsp;&nbsp;提交&nbsp;&nbsp;'/>
					<a href="<%=request.getContextPath()%>/trading.do?method=query"><button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button></a>
				</td>
			</tr>
		</table>
	</form>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">




</script>
<%@ include file="/footer.jsp"%>
</body>
</html>