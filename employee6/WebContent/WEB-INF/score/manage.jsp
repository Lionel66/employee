<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绩效管理</title>

<style>

#main{
width:600px;
margin:20px auto
}

#sc .select{
background:#337ab7
}
#sc td{
width:200px;
}
#emp input{
width:100px;
}
#sc select{
width:100px;
height:28px;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	$("#sc input").blur(function(){
		var value=$(this).val();
		var empId=$(this).data("empid");
		var proId=$(this).data("proid");
		var id=$(this).data("id");
		var input=$(this);
		$.ajax({
			url:"sc",
			type:"post",
			data:{
				type:"input",
				value:value,
				id:id,
				empId:empId,
				proId:proId
			},
		dataType:"json",
		success:function(data){
			
			input.parent().next().html(data.grade.value);
			input.data("id",data.id);
		}
		})
	   
	})
		
	})
	if(${p.ye}<=1){
		$("#pre").addClass("disabled");
	$("#pre").find("a").attr("onclick","return false");
	}
	if(${p.ye}>=${p.maxYe}){
		$("#next").addClass("disabled");
	$("#next").find("a").attr("onclick","return false");
	}
 


</script>
</head>
<body>

	<div id="main">
	<form action="sc" class="form-horizontal" role="form"  method="post">
<div class="form-group">
<div class="col-sm-2">
<input type="text"  class="form-control" name="name" placeholder="姓名" value="${c.emp.name}" >

</div>
<div class="col-sm-2 ">
<select name="depId"  class="form-control">
<option value="">部门</option>
<c:forEach items="${depList }" var="dep">
<option value="${dep.id }" <c:if test="${dep.id==c.emp.dep.id }">selected</c:if>>${dep.name }</option>
</c:forEach>
</select>
</div>
<div class="col-sm-3 ">
<select name="proId"  class="form-control">
<option value="">请选择项目</option>
<c:forEach items="${proList }" var="pro">
<option value="${pro.id }" <c:if test="${pro.id==c.pro.id }">selected</c:if>>${pro.name }</option>
</c:forEach>
</select>
</div>
<div class="col-sm-2">
<input type="text"  class="form-control" name="value" placeholder="成绩" value="${c.value!=-1?c.value:''}" >
</div>
<div class="col-sm-2 ">
<select name="grade"  class="form-control">
<option value="">等级</option>
<c:forEach items="${grades }" var="grade">
<option value="${grade.value}" <c:if test="${grade==c.grade}">selected</c:if>>${grade.value }</option>
</c:forEach>
</select>
</div>
<div class="form-group">
<div class=" col-sm-1">
<button  type="submit" class="btn btn-primary">搜索</button>
</div>
</div>
</div>
</form>
	<table id="sc" class="table table-bordered table-striped table-hover" style="width:600px" >
	<thead>
    <tr >
		<th>姓名</th>
		<th>部门</th>
		<th>项目</th>
		<th>成绩</th>
		<th>等级</th>
		</tr> 
	</thead>
	<tbody>
		<c:forEach items="${list}" var="sc">
		<tr data-id="${sc.id }">
			<td>${sc.emp.name }</td>
		    <td>${sc.emp.dep.name }</td> 
			<td style="width:260px">${sc.pro.name }</td>
			<td><input data-id="${sc.id }" data-empid="${sc.emp.id }" data-proid="${sc.pro.id }" type="text" value="${sc.value }"/></td>
			<td>${sc.grade.value }</td>
		</tr>
		</c:forEach>
		</tbody>	 
		
	</table>
		<ul class="pagination">
		<li id="first" ><a href="sc?type=manage&ye=1&empName=${c.emp.name}&depId=${c.emp.dep.id }&porId=${c.pro.id }&value=${c.value!=-1?c.value:""}&grade=${c.grade }" >首页</a></li>
    <li id="pre" ><a href="sc?type=manage&ye=${p.ye-1}&empName=${c.emp.name}&depId=${c.emp.dep.id }&porId=${c.pro.id }&value=${c.value!=-1?c.value:""}&grade=${c.grade }" >上一页</a></li>
   <c:forEach begin="${p.beginYe }" end="${p.endYe }" varStatus="status">
    <li <c:if test="${p.ye==status.index}"> class="active"</c:if>><a href="sc?type=manage&ye=${p.ye-1}&empId=${c.emp.name}&depId=${c.emp.dep.id }&porId=${c.pro.id }&value=${c.value!=-1?c.value:""}&grade=${c.grade }">${status.index}</a></li>
    </c:forEach>
    <li id="next"><a href="sc?type=manage&ye=${p.ye+1}&empName=${c.emp.name}&depId=${c.emp.dep.id }&porId=${c.pro.id }&value=${c.value!=-1?c.value:""}&grade=${c.grade }">下一页</a></li>
    <li id="last" ><a href="sc?type=manage&ye=${p.maxYe }&empName=${c.emp.name}&depId=${c.emp.dep.id }&porId=${c.pro.id }&value=${c.value!=-1?c.value:""}&grade=${c.grade }" >尾页</a></li>
</ul> 

	</div>
	
</body>
</html>