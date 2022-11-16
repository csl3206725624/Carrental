<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/jquery-3.4.1.js">
		
	</script>
	<script type="text/javascript">
    window.onload=function(){
        setInterval(function(){
            var date=new Date();
            var year=date.getFullYear(); //获取当前年份
            var mon=date.getMonth()+1; //获取当前月份
            var da=date.getDate(); //获取当前日
            var day=date.getDay(); //获取当前星期几
            var h=date.getHours(); //获取小时
            var m=date.getMinutes(); //获取分钟
            var s=date.getSeconds(); //获取秒
            var d=document.getElementById('Date');
            d.innerHTML='当前时间 : '+year+' 年 '+mon+' 月 '+da+' 日 '+' 星期 '+day+' '+h +':'+ m +':'+ s ;  },1000)  }
</script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<style type="text/css">
		#panel,#flip{
		border-radius:20px;
			padding: 5px;
			font-size:30px;
			text-align: center;
			background-color: #F2F2F2;
			border: solid 1px #C7C7C7;
		}
		#panel{
		
			padding: 50px;
			display: none;
		}
		
		#p1,#p2,#p3,#p4,#p5,#p6
		{
			font-size:30px;
			color: #555555;
			font-family:华文新魏;
		
		}
		#panel
		{
			background-color: #EBF7F4;
		}
		
		#Date
		{
			font-size:18px;
			color: #555555;
			font-family:宋体;
		}
	
	</style>
	<script type="text/javascript">
		$(document).ready(
			function(){
				$("#flip").mousedown(
					function(){
						$("#panel").slideToggle("slow",function(){
							
						 var  tex =	$('#flip').text();
						 if (tex=="点我滑下面板") {
						 	$('#flip').text("点我上拉面板");
						 	
						 }
						 else{
						
						 
						 }
						})
					
					}
				)
			}
		)
	</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/script.js"></script>

</head>
<body style="background-color: #F0F0F0;">


<div id="Date" > </div>
	<div id="flip"><p style="background-color: ;text-align:left;" id="p1">&nbsp;使用导航</p></div>
		<div id="panel">
	 <p align="left" id="p1">❀  左方任务栏双击打开每个模块</p>
	 <p align="left" id="p2">❀  用户管理支持用户导入导出用户信息execl格式</p>
	 <p align="left" id="p3">❀  订单信息支持用户查询注册用户的基本信息</p>
	 <p align="left" id="p4">❀  供应商对应的供应系统可以查看合作单位</p>
	 <p align="left" id="p5">❀  车辆基本信息可以上传下载车辆图片</p>
	</div>
		<br>
		<div id="flip">
 		 <img src="<%=request.getContextPath() %>/images/123444.png"> 
 		</div>
 		
		
		

</body>
</html>