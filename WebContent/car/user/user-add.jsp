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
<style type="text/css">
   div{
      margin-top: 10px;
   }
</style>

<script type="text/javascript">
		function imgPreview(fileDom)
		{
			//alert("触发预览");
			//1、判断当前设备是否支持预览,然后初始化
			if(window.FileReader)
			{
				var fileReader = new FileReader();
			}
			else
			{
				alert("你的设备太垃圾了，请升级一下子.....");
				return false;
			}
			//通过js的组合选择器获取到文件域对象，就是第一个
			var file = fileDom.files[0];
			//alert("file-->"+file);
			//设置一个校验的正则表达式，为了避免用户选择的不是图片
			var imageType = /^image\//;
			//正则表达式，当前文件域里面的类型
			//alert("imageType:-->"+imageType);
			//当前文件域里面的数据类型
			//alert("file.type:-->"+file.type);
			//取非的意思是不匹配
			//alert("imageType.test(file.type)-->"+!imageaType.test(file.type));
			
			if(!imageType.test(file.type))
			{
				alert("请选择图片");
				document.getElementById("spid").innerHTML="<input type=\"file\" name=\"image\" onchange=\"imgPreview(this)\" id=\"imgFile\" accept=\"image/gif,image/jpeg,image/jpg,image/png,image/svg\" />";
				//alert("文件选择清空了......");
				return false;
			}
			//当文件域中读取完成之后才触发图片标签
			fileReader.onload = function()
			{
				//获取图片dom
				var img = document.getElementById("preview");
				//图片路径设置为读取的图片
				//alert("this:-->"+this);//从弹框可以看出是FileReader对象
				//this.result可以看出是一个data开头的字符串，这段字符串的实质就是 Data URL
				//alert("this.resulr:-->"+this.result);
				//相当于是为图片标签设置了src的url值
				img.src = this.result;
			};
			//将文件域标签中的内容显示在图片标签的位置
			fileReader.readAsDataURL(file);
			
			var flag = false;//表单是否已经提交标识，默认为false
			if(!flag)
			{
				flag=true;//标识已经点击
				document.getElementById("btn").disabled = "disabled";
				return true; 
			}
			return false;
			
			
		}
		
		function checkForm()
		{
			//alert("1.文件域的非空校验");
			 var imgFile=document.getElementById("imgFile").value;
			 if(imgFile=="")
			 {
				 alert("请先选择本次上传的图片");
				 return false;
			 }
			 
		    //alert("2.图片大小的校验");
		    var	imgFileElement=document.getElementById("imgFile");
		    var fileData=imgFileElement.files[0];
		    //alert(fileData.size);
		    var size=fileData.size; 
		    if(size>10240*10240)
			{
				alert("您本次上传的图片超过10MB了,请选择一个小点的再上传.....");
				return false;
			}
		}
		
		</script>

</head>
<body>
<form  action="<%=request.getContextPath() %>/user.do?method=add" method="post" name="form1" onsubmit="return checkForm(form1)" enctype="multipart/form-data" >
<article class="page-container">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>车辆编号：<span id="ajax_name" ></span></label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="" placeholder="添加编号" id="user-name" name="user_id" datatype="*2-16" nullmsg="车牌不能为空">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>车辆车牌：<span id="ajax_name" ></span></label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="" placeholder="添加车牌" id="user-name" name="user_Plate" datatype="*2-16" nullmsg="车牌不能为空">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>车辆型号：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" placeholder="添加车辆型号" id="user-tel" name="user_Model"">
		</div>
	</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>车辆颜色：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text"placeholder="添加颜色" id="user-email" name="user_Color">
			
		</div>
	</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>车辆图片：</label>
		 
		<div class="formControls col-xs-8 col-sm-9">
	     	 <tr>
		         <td>添加车辆图片：</td>
		          <td> <span id="spid"><input   type="file"   name="user_Imgpath"  onchange="imgPreview(this)"   id="imgFile"/></span></td>
		         <td rowspan="3"><img id="preview"   style="width:130px;height:130px"  src="<%=request.getContextPath()%>/car/img/565656.png"  /></td>
	         </tr>
		</div>
		
 		<!-- 
		<tr>
          
			<td> <span id="spid"><input   type="file"   name="image"  onchange="imgPreview(this)"   id="imgFile"/></span></td>
			</tr>
			-->
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>车辆状态：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<select name=user_State>
			<option value="已租">已租</option>
			<option value="未租">未租</option>
			
		 </select>
		</div>
	</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" placeholder="添加供应商" id="user-tel" name="user_Gongying"">
		</div>
	</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>租车用户：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" placeholder="添加租车用户" id="user-tel" name="user_Yonghu"">
		</div>
	</div>
	
	
	<div class="row cl" style="display: none;">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>租车用户：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" placeholder="添加租车用户" id="user-tel" value="${sessionToken}" name="token"">
		</div>
	</div>


	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<a href="<%=request.getContextPath()%>/user.do?method=query"><button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button></a>
		</div>
	</div>
</article>
</form>

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
	})
</script> 



<!--/请在上方写此页面业务相关的脚本-->
<%@ include file="/footer.jsp"%>
</body>
</html>