<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改员工</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<style>
#main{
width:450px;
margin:20px auto
}

</style>
</head>
<body>

<div id="main">
<form action="dep" class="form-horizontal" role="form"  method="post">
<input type="hidden" name="type" value="update" />
<input type="hidden" name="id" value="${dep.id }" />
<div class="form-group">
<label for="firstname" class="col-sm-2 control-label">部门名</label>
<div class="col-sm-10">
<input type="text"  class="form-control" name="name" value="${dep.name}" autocompany="off">
</div>
</div>

<div class="form-group">
<div class="col-sm-offset-2 col-sm-10">
<button  type="submit" class="btn btn-primary">保存</button>
</div>
</div>
</form>
</div>
</body>
</html>