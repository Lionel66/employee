<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
*{
margin:0;
padding:0
}
#left {
	width: 200px;
	height: 600px;
	float: left;
	padding-left:20px
}
a{
color:white;
text-decoration: none;
}
#right {
	width: 800px;
	height: 600px;
	
}

#top, #bottom {
	height: 80px;
	background: #337ab7;
}

#top b {
    float:left;
	position: relative;
	color: white;
	font-size: 25px;
	left: 600px;
	top: 20px
}
#top span{
color: white;
font-size: 15px;
position: relative;
float:right;
top: 60px

}
.yi{
width:160px;
height:40px;
background:#337ab7;
color:white;
margin-top:10px;
border-radius:3px;
text-align: center;
line-height:40px;
cursor:pointer;
}

.er{
list-style:none;
width:160px;
border-radius:3px;

}
.er li{
background:#f85959;
margin-top:5px;
text-align: center;
height:30px;
line-height:30px;
font-size:14px
}
.er a {
color:white;
}

</style>
<script src="js/jquery.js"></script>
<script type="text/javascript">
$().ready(function(){
	$(".yi").click(function(){
		$(this).next().slideToggle();
	})
	
})
    var websocket = null;

	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://192.168.0.149:8080/employee6/websocket");
	} else {
		alert('没有建立websocket连接')
	}

	//连接发生错误的回调方法
	websocket.onerror = function() {
		//setMessage("错误");
	};

	//连接成功建立的回调方法
	websocket.onopen = function(event) {
		//setMessage("建立连接");
	}

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		$("#count").html("当前有"event.data"人在访问");
	}

	//连接关闭的回调方法
	websocket.onclose = function() {
		//setMessage("close");
	}

	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口。
	window.onbeforeunload = function() {
		websocket.close();
	} 
	

</script>
</head>
<body>
	<div id="container">
		<div id="top">
			<b>员工管理系统</b>
			<span id="count"> 当前有<%=application.getAttribute("num")%>人在访问 </span>
		</div>
		<div id="main">
			<div id="left">
				<div class="yi">员工管理</div>
				<ul class="er">
				<li><a href="emp" target="right">员工管理</a></li>
				<li><a href="emp?type=showAdd" target="right">添加员工</a></li>
				</ul>
				<div class="yi">部门管理</div>
				<ul class="er">
				<li><a href="dep" target="right">部门管理</a></li>
				<li><a href="dep?type=showAdd" target="right">添加部门</a></li>
				</ul>
				<div class="yi">项目管理</div>
				<ul class="er">
				<li><a href="pro" target="right">项目管理</a></li>
				<li><a href="pro?type=showAdd" target="right">添加项目</a></li>
				</ul>
				<div class="yi">绩效管理</div>
				<ul class="er">
				<li><a href="sc" target="right">绩效查看</a></li>
				<li><a href="sc?type=manage" target="right">绩效管理</a></li>
				</ul>
			</div>
			<iframe id="right" name="right" scrolling="no" frameborder="0" src="emp"></iframe>
		</div>
		<div id="bottom"></div>
	</div>
</body>
</html>