<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<style>
*{
			padding: 0;
			margin: 0 ;
		}
#main{
width:400px;
margin:20px auto
}
#top{
font-size:26px;
margin-left:150px;

}

		#message{
			color: red;
		}
</style>
<script src="js/jquery.js"></script>
<script type="text/javascript">
$().ready(function(){
 	function showMessage(mes){
 		$("#message").html(mes);
 		setTimeout(function(){
 			$("#message").html("")
 		},1000)
	}
	$("form").submit(function(){
		var str=$("#password").val();
		var reStr=$("#rpassword").val();
		if(str==""){
			showMessage("密码不能为空")
			return false;
		}
		if(str!=reStr){
			showMessage("两次输入密码不一致")
			return false;
		}
		 /*var username=$("#username").val();
		var str=$("#password").val();
		var reStr=$("#reply").val();
		$.ajax({
			url:"user",
			type:"post",
			data:{
				type:"doRegister",
				username:username,
				
			},
			dataType:"text",
			success:function(data){
				if(data=="true"){
					showMessage("账号已存在")
					return false;
				}
			}
		})
		if(str==""){
			showMessage("密码不能为空")
			return false;
		}
		if(str!=reStr){
			showMessage("两次输入密码不一致")
			return false;
		}*/
	})
	
}) 
</script>
</head>
<body>
<div id="main">
<div id="top"><b>员工注册</b></div>
<form action="user?type=doRegister" class="form-horizontal" role="form"  method="post" ">
<div class="form-group">
<label for="username" class="col-sm-3 control-label">用户名：</label>
<div class="col-sm-7">
<input type="text"  class="form-control" name="username" placeholder="请输入账号" autocompany="off">
</div>
</div>
<div class="form-group">
<label for="password" class="col-sm-3 control-label">密码:</label>
<div class="col-sm-7">
<input type="password"  class="form-control" name="password" placeholder="请输入密码" autocompany="off">
</div>
</div>

<div class="form-group">
<label for="password" class="col-sm-3 control-label">确认密码:</label>
<div class="col-sm-7">
<input type="password"  class="form-control" name="rpassword" placeholder="请重新输入密码" autocompany="off">
</div>
</div>
<div id="message">${mes}</div>
<div class="form-group">
<div class="col-sm-offset-2 col-sm-10">
<button  id="btn" type="submit" class="btn btn-primary">注册</button>
</div>
</div>
</form>
</div>
</body>
</html>