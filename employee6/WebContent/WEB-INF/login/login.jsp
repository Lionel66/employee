<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<style>
*{
			padding: 0;
			margin: 0 ;
		}
#top, #bottom {
	height: 110px;
	background: #337ab7;
}

#top b {
	position: relative;
	color: white;
	font-size: 25px;
	left: 600px;
	top: 40px
}
		#b{
			position: absolute;
			width: 1370px;
			height: 540px;
			top: 109px;
			background-color: #337ab7;
		}
		#login{
	        position: relative;
	        width: 990px;
            height: 475px;
            margin: 0 auto;
            z-index: 5;
            
            
		}
		#login-form{
			float: right;
			top: 10px;
		    position: relative;
            z-index: 4;
            background: #fff;
            overflow: visible;
            width: 346px;
            height: 400px;
            margin-right:325px;
		}
	
		
		
		#zhanghu{
			width: 170px;
			left: 80px;
			height: 54px;
            font-size: 18px;
            font-family: "microsoft yahei";
            text-align: center;
            border-bottom: 1px solid #f4f4f4;
            position: absolute;
            background: #fff;
            display: block;
            color: #666;
		}
		#zhanghu a{
			width: 173px;
			height: 18px;
			position: absolute;
			left: 0;
			top: 18px;
			text-decoration: none;
			cursor: pointer;
		}
		#zhanghu a:hover{
			color: #337ab7;
		}
		#login-name{
			left: 15px;
			margin-top: 100px;
			position: relative;
			width: 304px;
			height: 38px;
			border: 1px solid #bdbdbd;
			margin-bottom: 20px
		}
		#login-name label{
		    position: absolute;
            z-index: 3;
            top: 0;
            left: 0;
            width: 38px;
            height: 38px;
            border-right: 1px solid #bdbdbd;
            background: url(//misc.360buyimg.com/user/passport/1.0.0/widget/login-form-2016-1124/i/pwd-icons-new.png) no-repeat;
		}
		#login-name input{
			line-height: 18px;
            height: 18px;
            border: 0;
            padding: 10px 0 10px 50px;
            width: 254px;
            float: none;
            overflow: hidden;
            font-size: 14px;
            font-family: '\5b8b\4f53';
		}
		#login-password{
			left: 15px;
			position: relative;
			width: 304px;
			height: 38px;
			border: 1px solid #bdbdbd;
			margin-bottom: 20px
		}
		#login-password label{
			position: absolute;
            z-index: 3;
            top: 0;
            left: 0;
            width: 38px;
            height: 38px;
            border-right: 1px solid #bdbdbd;
            background: url(//misc.360buyimg.com/user/passport/1.0.0/widget/login-form-2016-1124/i/pwd-icons-new.png) no-repeat;
		}
		#login-password input{
			line-height: 18px;
            height: 18px;
            border: 0;
            padding: 10px 0 10px 50px;
            width: 254px;
            float: none;
            overflow: hidden;
            font-size: 14px;
            font-family: '\5b8b\4f53';
		}
		#login-yanzheng{
		    
			left: 15px;
			position: relative;
			width: 304px;
			height: 38px;
			border: 1px solid #bdbdbd;
			margin-bottom: 20px
		}
		#login-yanzheng input{
			line-height: 18px;
            height: 18px;
            border: 0;
            padding: 10px 0 10px 10px;
            width: 294px;
            float: left;
            overflow: hidden;
            font-size: 14px;
            font-family: '\5b8b\4f53';
		}
		
		#forget-password{
			position: relative;
            margin-bottom: 20px;
            width: 306px;
            height: 18px;
		}
		#forget-password a{
			color: #666;
            text-decoration: none;
            position: absolute;
            right: 0;
            top: 0;
            margin: 0;
            font-size: 13px;
            cursor: pointer;
		}
		#forget-password a:hover{
			color: #337ab7;
		}
		#login-btn{
			left: 15px;
			width: 303px;
			height:32px;
			position: absolute;
			border: 1px solid #337ab7;
		}
		#login-btn a{
		    border: 1px solid #337ab7;
		    display: block;
            width: 302px;
            background: #337ab7;
            height: 31px;
            line-height: 31px;
            color: #fff;
            font-size: 20px;
            font-family: 'Microsoft YaHei';
            text-align: center;
            cursor: pointer;
            text-decoration: none;
		}
		#background {
			position: absolute;
			background: url(//img12.360buyimg.com/da/jfs/t23848/348/2063178168/154703/c357c61c/5b727e9bN54b0d14b.jpg) 0px 0px no-repeat
		}
</style>
<script src="js/jquery.js"></script>
<script type="text/javascript">
$().ready(function(){
	if(self!=top){
		top.location="user";
	}
	$("#login-btn").click(function(){
		var username=$("#username").val();
		var password=$("#password").val();
		var random=$("#yanzheng").val();
		$.ajax({
			url:"user",
			type:"post",
			data:{
				type:"doLogin",
				username:username,
				password:password,
				random:random,
			},
			dataType:"text",
			success:function(data){
				if(data=="true"){
					location.href="index"
				}else{
					alert("账号或密码错误")
				}
			}
		})
	})
	$("#image").click(function(){
		$(this).attr("src","user?type=randomImage&"+Math.random())
	})
})
</script>
</head>
<body>
        <div id="top">
			<b>员工管理系统</b>
		</div>
		<form action="user"  role="form"  method="post">
		<div id="b">
		<div id="login">
			<div id="login-form">
				
				<div id="zhanghu">
					<a>账户登录</a>
				</div>
			
			<div id="login-name">
				<label></label>
				<input type="text" autocomplete="off"  placeholder="邮箱/用户名/已验证手机" id="username" value="${name }" />
			</div>
			<div id="login-password">
				<label></label>
				<input type="password" autocomplete="off" placeholder="密码" id="password"/>
				
			</div>
			<div id="login-yanzheng">
				<input type="text" autocomplete="off" placeholder="验证码" id="yanzheng"/>
				<img id="image" src="user?type=randomImage" style="margin-top:10px" />
			</div>
			<div id="mes" style="height:40px">${mes }</div>
			<div id="forget-password">
				<a>忘记密码</a>
			</div>
			</form>
			<div id="login-btn">
				<a>登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
				</div>
			
			<div id="background">
				
			</div>
		

</body>
</html>