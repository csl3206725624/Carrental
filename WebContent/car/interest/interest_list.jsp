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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>租车折线图</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 租车金额 <span class="c-gray en">&gt;</span> 折线图 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div id="container" style="min-width:700px;height:400px"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
<script type="text/javascript">
$(function () {
    Highcharts.chart('container', {
        title: {
            text: '2010年至今的租车金额变化',
            x: -20 //center
        },
       /*  subtitle: {
            text: 'http://www.soudang.cn/',
            x: -20
        }, */
        xAxis: {
            categories: ['2010年', '2011年', '2012年', '2013年', '2014年', '2015年','2016年', '2017年', '2018年', '2019年','2020年', '2021年']
        },
        yAxis: {
            title: {
                text: '金额 (元)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '元'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: 
        [
        {
            name: '新能源',
            data: [0,0,0,530, 600, 605, 620, 630, 660, 700,690,720]
        },{
            name: '小型',
            data: [480, 490, 502, 510, 524, 530, 528, 559, 606, 634,590,603]
        },{
            name: '紧凑型',
            data: [503, 488, 490, 529, 532, 540, 566, 539, 686, 625,653,631]
        },{
            name: '中型',
            data: [485, 550, 530, 629, 652, 728, 808, 625, 699, 825,811,833]
        },{
            name: '中大性',
            data: [655, 630, 520, 469, 362, 238, 338, 355, 429, 565,566,596]
        },{
            name: '大型',
            data: [625, 330, 520, 209, 632, 438, 618, 725, 819, 935,965,920]
        },{
            name: 'SUV',
            data: [562, 550, 540, 569, 652, 688, 698, 695, 759, 825,863,842]
        },{
            name: 'MPV',
            data: [235, 560, 430, 629, 442, 668, 788, 825, 929, 1025,966,999]
        },{
            name: '跑车',
            data: [986, 1020, 1060, 1159, 1152, 1168, 1258, 1365, 1369, 1425,1305,1333]
        }
        
        ]
    });
});
</script>
<%@ include file="/footer.jsp"%>
</body>
</html>