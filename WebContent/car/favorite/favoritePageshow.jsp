<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script typet="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
  <script>
        $(function () {            
            $(".like").click(function () {
                $(this).toggleClass('cs');                
            })
        })
    </script>
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
<title>收藏夹</title>
		<style type="text/css">
			.like{ font-size:30px;  color:#ccc; cursor:pointer;}
			.cs{color:	#DC143C;}
			
			
			
			.float_div
{
border:2px solid;
border-radius:200px;
-moz-border-radius:25px; /* Old Firefox */
}img{border-radius:50px} 
		</style>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>  我的收藏  <span class="c-gray en">&gt;</span>  <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">


	<form action="<%=request.getContextPath() %>/commodity.do?method=deleteAll" method="post">
		
		<div class="mt-20">
				<table class="table table-border table-bordered ">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="90">热度</th>		
				<th width="40">赞</th>	
				<th width="90">车牌号</th>
				<th width="90" >型号</th>
				<th width="90">颜色</th>
				<th width="90">是否租出</th>
				<th width="100">供应商</th>、
				<th width="100">租户</th>
				<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="map">
							<tr class="text-c">
								<td><input name="delIdArray" type="checkbox" value="${map.Favorite_id}"></td>
								<td>${map.Favorite_rd} </td>
    							<td>  <a href="<%=request.getContextPath()%>/user.do?method=favoritezan&Favorite_id=${map.Favorite_id}" ><p class="like">&#10084;</p></a> </td>
								<td>${map.user_Plate}</td>
								<td>${map.user_Model}</td>
								<td>${map.user_Color}</td>
								<td>${map.user_State}</td>
								<td>${map.user_Gongying}</td>
								<td>${map.user_Yonghu}</td>
								<td class="f-14 product-brand-manage"><a
									style="text-decoration: none" class="ml-5"
									onClick="active_del(this,'10001')"
									href="<%=request.getContextPath() %>/user.do?method=deletefavorite&Favorite_id=${map.Favorite_id}"
									title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	<br>
<div style="width: 100%;padding-left: 90%">  &nbsp; &nbsp;
<a href="<%=request.getContextPath()%>/user.do?method=fanshouye"  class="btn btn-danger radius">返回首页</a>
</span>  </div>
</form>
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<script type="text/javascript" src="<%=request.getContextPath()%>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">

$(function () {            
    $(".like").click(function () {
        $(this).toggleClass('cs');                
    })
})


$("#btn").click(function () {
    var count = $("#lbl").parent().text();

    count++;

    $("#lbl").parent().text(count);
})


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
<br><br><br><br>
<%@ include file="/footer.jsp"%>
</body>
</html>