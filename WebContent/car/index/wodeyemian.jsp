<%@page import="java.util.List"%>
<%@page import="com.bd.rj.dao.DaoImpl"%>
<%@page import="com.bd.rj.dao.Dao"%>
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
<title>汽车后台管理系统</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
<style type="text/css">
	dt{
		font-size: 17px;
	}
	.tishi{
		width: 179px; height:38px;position: static;border:1px solid ; background: #FFFFFF;border-radius: 15px; top: 38px;
		visibility:hidden;  text-align:center;
	}
	#qczlglxt
		{
			font-size: 35px;
			color: white;
			font-family: "楷体";
		}
</style>
</head>
<body >

<header class="navbar-wrapper">  
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="#" id="qczlglxt"> 🚗 汽车租赁后台管理系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml"></a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs"></span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
	
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>当前管理员</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A"><%=((Map)session.getAttribute("user")).get("manager_name")%><i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						
						<li><a href="<%=request.getContextPath()%>/login.do?method=loginOut">切换账户</a></li>
						<li>
						  <a href="<%=request.getContextPath()%>/login.do?method=loginOut" >退出</a>
						</li>
				</ul>
			</li>
				
				<% 
					Dao dao = new DaoImpl();
					List list= (List)dao.executeQueryForList("Select * from myfavorite");
					
				
				%>
			
				<li class="dropDown dropDown_hover">
					<a href="<%=request.getContextPath()%>/user.do?method=favoritePage" class="dropDown_A"><span>我的收藏</span><i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						
						<%
						for(int i = 0 ; i < list.size() ; i++) {
							Map map2 = (Map)list.get(i);
						    
						%>
						
						
						<li><a href="<%=request.getContextPath()%>/user.do?method=favoritePage&Favorite_id=<%=map2.get("Favorite_id")%>"><%=map2.get("Favorite_name")%></a></li>
						
						<%
						}
						%>
						
						
				</ul>
			</li>
				
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>

</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">

		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i><div class="tishi" > 👆 这里可以查看用户</div></dt>
			<dd>
				<ul>
<!-- 					<li><a data-href="picture-list.html" data-title="图片管理" href="javascript:void(0)">图片管理</a></li>  -->
						<li><a data-href="<%=request.getContextPath()%>/seller.do?method=query" data-title="用户管理" href="javascript:void(0)">用户管理</a></li>
						
				</ul>
			</dd>
		</dl>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe620;</i> 订单信息<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i><div class="tishi" > 👆 这里查看订单信息</div></dt>
			<dd>
				<ul>
					<!-- <li><a data-href="product-brand.html" data-title="品牌管理" href="javascript:void(0)">品牌管理</a></li>
					<li><a data-href="product-category.html" data-title="分类管理" href="javascript:void(0)">分类管理</a></li>
					<li><a data-href="product-list.html" data-title="产品管理" href="javascript:void(0)">产品管理</a></li> -->
					<li><a data-href="<%=request.getContextPath()%>/commodity.do?method=query" data-title="商品管理" href="javascript:void(0)">商品管理</a></li>
			</ul>
		</dd>
	</dl>
		<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe622;</i> 供应商合作<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i><div class="tishi" > 👆 供应商请点这里</div></dt>
			<dd>
				<ul>
					<!-- <li><a data-href="http://h-ui.duoshuo.com/admin/" data-title="评论列表" href="javascript:;">评论列表</a></li>
					<li><a data-href="feedback-list.html" data-title="意见反馈" href="javascript:void(0)">意见反馈</a></li> -->
					<li><a data-href="<%=request.getContextPath()%>/trading.do?method=query" data-title="信息管理" href="javascript:void(0)">信息管理</a></li>
			</ul>
		</dd>
	</dl>
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 供应商管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i><div class="tishi" > 👆 在这管理供应商</div></dt>
			<dd>
				<ul>
					<li><a data-href="<%=request.getContextPath()%>/vip.do?method=query" data-title="供应商列表" href="javascript:;"> 供应商列表</a></li>
					
			</ul>
		</dd>
	</dl>
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i><div class="tishi" > 👆 管理员请点这</div></dt>
			<dd>
				<ul>
					<!-- <li><a data-href="admin-role.html" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>
					<li><a data-href="admin-permission.html" data-title="权限管理" href="javascript:void(0)">权限管理</a></li> -->
					<li><a data-href="<%=request.getContextPath()%>/manager.do?method=query" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>
			</ul>
		</dd>
	</dl>
		<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i> 租车金额统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i><div class="tishi" > 👆 租车折线图</div></dt>
			<dd>
				<ul>
					<li><a data-href="<%=request.getContextPath()%>/car/interest/interest_list.jsp" data-title="租车金额折线图" href="javascript:void(0)">租车金额折线图</a></li>
			</ul>
		</dd>
	</dl>
	<dl id="menu-system">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 车辆基本信息<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i><div class="tishi" > 👆 查看车辆信息</div></dt>
			<dd>
				<ul>
					<li><a data-href="<%=request.getContextPath()%>/user.do?method=query" data-title="车辆管理" href="javascript:void(0)">车辆管理</a></li>
			</ul>
		</dd>
	</dl>
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section  class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="主页面" data-href="<%=request.getContextPath()%>/car/index/wedezuomian.jsp">主页面</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="<%=request.getContextPath()%>/car/index/wedezuomian.jsp"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">

var tishi=document.getElementsByClassName('tishi');

tishi[0].style.visibility="visible";

tishi[0].addEventListener('click',function(){

	tishi[0].style.display='none';
	tishi[1].style.visibility="visible";
})
tishi[1].addEventListener('click',function(){
	
	tishi[1].style.display='none';
	tishi[2].style.visibility="visible";
})
tishi[2].addEventListener('click',function(){

	tishi[2].style.display='none';
	tishi[3].style.visibility="visible";
})

tishi[3].addEventListener('click',function(){
	
	tishi[3].style.display='none';
	tishi[4].style.visibility="visible";
})
tishi[4].addEventListener('click',function(){
	
	tishi[4].style.display='none';
	tishi[5].style.visibility="visible";
})
tishi[5].addEventListener('click',function(){

	tishi[5].style.display='none';
	tishi[6].style.visibility="visible";
})
tishi[6].addEventListener('click',function(){

	tishi[6].style.display='none';
})






function fangda() {
	
}

$(function(){
	
});
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看详细信息',
		content: '<p>管理员信息</p><p>管理员账号：<%=((Map)session.getAttribute("user")).get("manager_name")%></p><p>邮箱：<%=((Map)session.getAttribute("user")).get("manager_email")%></p><p>手机号：<%=((Map)session.getAttribute("user")).get("manager_phone")%></p><p>身份证：<%=((Map)session.getAttribute("user")).get("manager_idertity")%></p>'
	});
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 

</body>
</html>