<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>车辆基本信息</title>

		 <SCRIPT>
			    var intTimeStep=20;
			    var isIe=(window.ActiveXObject)?true:false;
			    var intAlphaStep=(isIe)?5:0.05;
			    var curSObj=null;
			    var curOpacity=null;
			    var stopTime=0;
			    function startObjMessage(objId)
			    {
			    curSObj=document.getElementById(objId);
			    if(isIe){curSObj.style.cssText='DISPLAY: none; Z-INDEX: 1; FILTER: alpha(opacity=0); POSITION: absolute;';}
			    setMessage();
			    }
			    function setMessage()
			    {
			    if(isIe){curSObj.filters.alpha.opacity=0;}
			    else{curOpacity=0;curSObj.style.opacity=0}
			    curSObj.style.display='';
			    setMessageShow();
			    }
			    function setMessageShow()
			    {
			    if(isIe)
			    {
			    curSObj.filters.alpha.opacity+=intAlphaStep;
			    if (curSObj.filters.alpha.opacity<100) {setTimeout('setMessageShow()',intTimeStep);}
			    else{stopTime+=10;if(stopTime<500){setTimeout('setMessageShow()',intTimeStep);}else{stopTime=0;setMessageClose();}}
			    }else
			    {
			    curOpacity+=intAlphaStep;
			    curSObj.style.opacity =curOpacity;
			    if (curOpacity<1) {setTimeout('setMessageShow()',intTimeStep);}
			    else{stopTime+=10;if(stopTime<200){setTimeout('setMessageShow()',intTimeStep);}else{stopTime=0;setMessageClose();}}
			    }
			    }
			    function setMessageClose()
			    {
			    if(isIe)
			    {
			    curSObj.filters.alpha.opacity-=intAlphaStep;
			    if (curSObj.filters.alpha.opacity>0) {
			    setTimeout('setMessageClose()',intTimeStep);
			    }
			    else {
			    curSObj.style.display='none';
			    }
			    }
			    else
			    {
			    curOpacity-=intAlphaStep;
			    if (curOpacity>0) {
			    curSObj.style.opacity =curOpacity;
			    setTimeout('setMessageClose()',intTimeStep);
			    }
			    else {
			    curSObj.style.display='none';
			    }
			    }
			    }
    </SCRIPT>
	
</head>
<body>


<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 车辆管理 <span class="c-gray en">&gt;</span> 车辆列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form action="<%=request.getContextPath() %>/user.do?method=query" method="post">
	<div class="text-c"> 
	    <input type="text" class="input-text" style="width:250px" placeholder="输入车辆车牌" id="" name="user_Plate" value="${user_Plate}">
		<input type="text" class="input-text" style="width:250px" placeholder="输入车辆型号" id="" name="user_Model" value="${user_Model}">
		<input type="text" class="input-text" style="width:250px" placeholder="输入车辆颜色" id="" name="user_Color" value="${user_Color}">
		<button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		<a href="<%=request.getContextPath() %>/user.do?method=addPage"  class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加车辆信息</a>
	</div>
</form>
<br>
	<form action="<%=request.getContextPath() %>/user.do?method=deleteArr" method="post" >
	<table class="table table-border table-bordered table-bg">
	
		<thead>
			<tr>
				<th scope="col" colspan="9">车辆列表</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="90">车牌 </th>
				<th width="90">车辆名称型号</th>
				<th width="90">车辆颜色</th>
				<th width="90" >车辆图片</th>
				<th width="90">供应商</th>
				<th width="90">租车用户</th>
				<th width="100">是否已租用</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		 <%
		   
		 List list = (List)request.getAttribute("list");
		 
        	for(int i = 0;i<list.size();i++)
        	{
        		Map map = (Map)list.get(i);
		 
		 %>
			<tr class="text-c">
				<td><input type="checkbox" value="<%=map.get("user_id") %>" name="userArray"></td>
				<td><%=map.get("user_Plate") %></td>
				<td><%=map.get("user_Model") %></td>
				<td><%=map.get("user_Color") %></td>
				
				<td style="padding:5px;"><img style="width:50px;height:50px" id="img" src="<%=request.getContextPath()%>/user.do?method=download&user_Imgpath=<%=map.get("user_Imgpath")%>"/></td>
				
				<td><%=map.get("user_Gongying") %></td>
						<td><%=map.get("user_Yonghu") %></td>
				<td class="td-status"><span class="label label-success radius"><%=map.get("user_State") %></span></td>
				<td class="td-manage">
<!-- 				<a style="text-decoration:none" onClick="admin_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>  -->
				<a title="收藏" href="<%=request.getContextPath()%>/user.do?method=tofavorite&user_id=<%=map.get("user_id") %>"  class="ml-5" onclick="startObjMessage('objDiv')"  ID="Button1" NAME="Button1" style="text-decoration:none"><i class="Hui-iconfont">收藏</i></a>
				<a title="编辑" href="<%=request.getContextPath()%>/user.do?method=editPage&user_id=<%=map.get("user_id") %>"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">修改</i></a>
				 <a title="删除" href="<%=request.getContextPath()%>/user.do?method=deleteOne&user_id=<%=map.get("user_id") %>"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">删除</i></a></td>
				 <DIV  id="objDiv" style="DISPLAY: none; Z-INDEX: 3; POSITION: absolute; ">
    			<img src="//images/8bcLQqF.png"><span style="margin-left:400px; color:blue; font-size: 50px">请勿重复收藏</span><img src="//images/8bcLQqF.png"></DIV>
			
			</tr>
			<% } %>
		</tbody>
	</table>
	</form>
	<br>
	<div >
	<a class="l" href="<%=request.getContextPath()%>/pano.autohome.com.cn/27887__c8e162b6fafe467a885428b9c0856f54.html" ><i class="btn btn-success">汽车全景</i></a>
	</div>
	<div style="width: 100%;padding-left: 94%">
	<span class="l"><input type="submit" value="批量删除" class="btn btn-danger radius"></span>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
// /*管理员-增加*/
// function admin_add(title,url,w,h){
// 	layer_show(title,url,w,h);
// }
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}

// }
</script>
<%@ include file="/footer.jsp"%>
</body>
</html>