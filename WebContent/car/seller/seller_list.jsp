<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户管理</title>
		<style type="text/css">
			
			.float_div
			{
			border:2px solid;
			border-radius:200px;
			-moz-border-radius:25px; /* Old Firefox */
			}img{border-radius:50px} 
		</style>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span class="c-gray en">&gt;</span> 用户列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

<form action="<%=request.getContextPath() %>/seller.do?method=query" method="post">

	<tr>
			<td align="left" valign="middle">&nbsp;
				身份证号： 
				<select id="sect" name="seller_id">
					<option value="">查询所有</option>
					<%
					 List list=(List)request.getAttribute("list_classs");
					 String seller_id=(String)request.getAttribute("seller_id");
					 for(int i =0;i<list.size();i++)
					 {
					   Map map_classs=(Map)list.get(i);
					   String  clasid=(String)map_classs.get("seller_id");
					   String  clasname=(String)map_classs.get("seller_idertity");
					%>
					
					<option value="<%=clasid%>"  <%=clasid.equals(seller_id)?"selected":"" %> ><%=clasname%></option>
					
					<%
					 }
					%>
				</select>
				&nbsp;用户姓名：
				<%
                     String key=(String)request.getAttribute("key");											
				%> 
				<input type="text" id="ky"  name="key"   value="<%=(key==null?"":key) %>"    />&nbsp;&nbsp; 
				<input type="submit"  value=" 查询 "  class="btn btn-primary radius"/>
			</td>
			<td>
			  <input type="button" value="导出用户信息" onclick="exportExcel()" class="btn btn-success" />
			</td>
		</tr>
		</tr>
		<tr>
		<td>选择用户信息：<input id="fed" type="file"  name="fe"/></td>
		<td><input type="button" value="导入用户信息" onclick="importExcel()" class="btn btn-success"/></td>
		</tr>


</form>

	<form action="<%=request.getContextPath() %>/seller.do?method=deleteAll" method="post">
	
		
		<span class="l">
		<a href="<%=request.getContextPath() %>/seller.do?method=addPage"  class="btn btn-primary radius"> 添加用户</a>
		
		</span> 
		
		
		
		
		
		</div>
		
		<div class="mt-20">
			<table class="table table-border table-bordered ">
				<thead>
					<tr class="text-c">
						<th width="20"><input name="" type="checkbox" value=""></th>
						<th width="20">序号</th>
						<th width="40">姓名</th>
						<th width="20">性别</th>
						<th width="100">身份证</th>
						<th width="100">电话</th>
						<th width="100">地址</th>
						<th width="100">租车时间</th>
						<th width="60">租车价格</th>
						<th width="80">租车状态</th>
						<th width="80">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list_examinee}" var="map">
						<tr class="text-c">
							<td><input name="ids" type="checkbox" value="${map.seller_idertity }"></td>
							<td>${list_classs.indexOf(map)+1}</td>
							<td  id="name"><p onmouseover="aa(${list.indexOf(map)+1},${map.seller_id})"  onmouseout="bb(${map.seller_id })">${map.seller_name }</p></td>
							<td>${map.seller_sex }</td>
							<td>${map.seller_idertity }</td>
							<td>${map.seller_phone }</td>
							<td>${map.seller_dizhi }</td>
							<td>${map.seller_zc_time }</td>
							<td>${map.seller_zc_money }</td>
							<td>${map.seller_zc_zhuangtai }</td>
							<td class="td-manage">
								<a style="text-decoration:none" class="ml-5" onClick="picture_edit('图库编辑','picture-add.html','10001')" href="<%=request.getContextPath() %>/seller.do?method=editpage&seller_idertity=${map.seller_idertity}" title="修改">
								修改
								</a> 
								
								<a style="text-decoration:none" class="ml-5"  href="<%=request.getContextPath() %>/seller.do?method=delete&seller_idertity=${map.seller_idertity}" title="删除">
								删除
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br>
		<div style="width: 100%;padding-left: 90%">
		<span class="l"> &nbsp; &nbsp;
		<input type="submit" onclick="datadel()" value="批量删除" class="btn btn-danger radius" >
		</span> 
		</div>
	</form>
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

function exportExcel()
{
var key= document.getElementById("ky").value;
	//alert(key);
var select=document.getElementById("sect");
var index=select.selectedIndex ;//拿到选中项的索引
//alert(index);
var seller_id;
if(index==0)
{
	seller_id="";
}
else
{
	var selectedValue=select.options[index].value;
	//alert("选中的下拉框的值"+selectedValue);
	seller_id=selectedValue;
}
	
  alert("开始导出用户信息");	
  var url= "<%=request.getContextPath()%>/seller.do?method=export&clasId="+seller_id+"&ky="+key+"";	
  //alert("url:"+encodeURI(url));	
  window.location.href=encodeURI(url);	
	
}

function importExcel()
{
	alert("开始导入用户信息");
	var fed=document.getElementById("fed").value;
	//alert("fed:"+fed);
	if(fed.length<=0)
	{
	 alert("请选择需要导入的excel的文件");
     return false;					
	}
	var url= "<%=request.getContextPath()%>/seller.do?method=importExcel&fed="+fed+"";				   
	 //alert("url:"+encodeURI(url));			
	 window.location.href=encodeURI(url); 
}
</script>
<%@ include file="/footer.jsp"%>
</body>
</html>