<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>
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
<title>品牌管理</title>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 商品管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">
<div>
<button class="btn btn-success" id="btn">打印</button> 
</div>

	<form action="<%=request.getContextPath() %>/commodity.do?method=deleteAll" method="post">
		
		<div class="mt-20" id="wrap" class="wrap">
		
				<table class="table table-border table-bordered ">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="70">订单编号</th>
							<th width="120">客户编号</th>
							<th width="200">商品名称</th>
							<th width="100">车牌号</th>
							<th width="120">交易价格</th>
							<th width="80">订单时间</th>
							<th width="80">所属类型</th>
							<th width="80">状态</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="map">
							<tr class="text-c">
								<td><input name="delIdArray" type="checkbox" value="${map.RId}"></td>
								<td>${map.RId}</td>
								<td>${map.ClNo}</td>
								<td>${map.CaName}</td>
								<td>${map.CaPlate}</td>
								<td class="text-l">${map.FLease}元</td>
								<td>${map.StartTime}</td>
								<td>${map.CaModel}</td>
								<td>${map.CaState}</td>
								<td class="f-14 product-brand-manage"><a
									style="text-decoration: none" class="ml-5"
									onClick="active_del(this,'10001')"
									href="<%=request.getContextPath() %>/commodity.do?method=delete&id=${map.RId }"
									title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	<br>
	 
<div style="width: 100%;padding-left: 90%">  &nbsp; &nbsp;
<span class="l"> &nbsp;  &nbsp;&nbsp;<input type="submit"  value="批量删除" class="btn btn-danger radius">
</span>  
</div>
</form>
</div>


 <script>
 	
    document.getElementById('btn').onclick = function () 
    {

   
      Print('#wrap', {
        onStart: function () {
          console.log('onStart', new Date())
        },
        onEnd: function () {
          console.log('onEnd', new Date())
        }
      });

    }
  </script>  

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script src="<%=request.getContextPath()%>/js/Print.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "asc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,6]}// 制定列不参与排序
	]
});

</script>

<script type="text/javascript">

/* document.onmousemove = function getMousePos() {
	 var e = window.event;
		//console.log('x:' + e.screenX + "     y:" + e.screenY)
		$("#absdiv").css("left",e.screenX-300);
		$("#absdiv").css("top",e.screenY-300);
} */
function out(id)
{
	 var float_div = document.getElementById("float_div");
	 float_div.style.display = "none";
}
function retianxie(i,id)
{
	 var url="<%=request.getContextPath()%>/commodity.do?method=Ajax";	
	 $.post(url,{'id':id},function(data)
	 {
	     var data = $.parseJSON(data);
	     //alert(data.commodity_name);
	     //alert(data.commodity_img)
	     var float_div = document.getElementById("float_div");
	     
	     var image = document.getElementById("image");
	     var image_name = document.getElementById("image_name");
	     var image_price = document.getElementById("image_price");
	     //alert(data.commodity_img);
	     image.src="<%=request.getContextPath()%>/seller.do?method=imgpage&imgss="+data.commodity_img+" ";
	     image.style.width="100px";
	     image.style.height="100px";
	     image_name.innerHTML=data.commodity_name;
	     //alert(data.commodity_name);
	     image_price.innerHTML=data.commodity_give_price;
	     
	     var is = i*20+120+i*18;
	     float_div.style.display = "inline";
	     //alert("111");
	     float_div.style.top = is+"px";
	     float_div.style.left = "300px";
	     <%-- var divs = "<div id='absdiv' style='position: absolute;background:silver;color:#fff;width:350px;height:150px;overflow: hidden;'><img style='background: red; width: 130px;height: 130px; margin-top: 10px;margin-left: 10px;float: left;' src='<%=request.getContextPath()%>/seller.do?method=imgpage&imgss="+data.commodity_img+"''/><div id='' style='margin-top: 35px;margin-left: 170px;'>名称："+data.commodity_name+"</div><div id='' style='margin-top: 20px;margin-left: 170px;'>价格："+ data.commodity_give_price+"</div></div>"
	     $("body").append(divs) --%>
	 });
}

</script>

<%@ include file="/footer.jsp"%>
  
</body>
</html>