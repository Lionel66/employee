<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>

<style>
#model {
	width: 600px;
	margin: 20px auto
}

#pro, #noPro {
	width: 580px;
	height: 200px;
	border: 1px solid #337ab7;
	border-radius: 3px;
}

#btn {
	width: 300px;
	margin: 20px auto;
}

#add {
	margin-right: 20px;
}

#addBatch {
	margin-right: 20px;
}

#deleteBatch {
	margin-right: 20px;
}

.pro {
	background: #337ab7;
	height: 40px;
	line-height: 40px;
	float: left;
	margin-left: 5px;
	color: #fff;
	padding: 0 20px;
	margin-top: 10px;
	border-radius: 3px;
	cursor: pointer
}

.select {
	background: #d9534f;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
	$(".pro").click(function(){
		$(this).toggleClass("select");
	})
	$("#add").click(function(){
		if($("#noPro").find(".select").length>0){
			var proId=$("#noPro").find(".select").data("id");
		
			$.ajax({
				url:"d2p",
				type:"post",
				data:{
					type:"add2",
					depId:"${dep.id}",
					proId:proId
				},
			    dataType:"text",
			    success:function(data){
			    	if(data=="true"){
			    		var pro=$("#noPro").find(".select");
			    		pro.removeClass("select");
			    		$("#pro").append(pro);
			    	}
			    }
			} )   
			
		}else{
			alert("请选择数据")
			
		}
		
	})
	$("#delete-model").click(function(){
			if($("#pro").find(".select").length>0){
				var proId=$("#pro").find(".select").data("id");
			
				$.ajax({
					url:"d2p",
					type:"post",
					data:{
						type:"delete2",
						depId:"${dep.id}",
						proId:proId
					},
				    dataType:"text",
				    success:function(data){
				    	if(data=="true"){
				    		var pro=$("#pro").find(".select");
				    		pro.removeClass("select");
				    		$("#noPro").append(pro);
				    	}
				    }
				} )   
				
			}else{
				alert("请选择数据");
				
			}
			})
		$("#addBatch").click(function(){
		if($("#noPro").find(".select").length>0){
			var proId="";
				
		    $("#noPro").find(".select").each(function(){
			proId+=	$(this).data("id")+",";
				
			})
            proId=proId.substring(0,proId.length-1);	
			$.ajax({
				url:"d2p",
				type:"post",
				data:{
					type:"addBatch",
					depId:"${dep.id}",
					proId:proId
				},
			    dataType:"text",
			    success:function(data){
			    	if(data=="true"){
			    		var pro=$("#noPro").find(".select");
			    		pro.removeClass("select");
			    		$("#pro").append(pro);
			    	}
			    }
			} )   
			
		}else{
			alert("请选择数据")
			
		}
		
	})
	$("#deleteBatch").click(function(){
			if($("#pro").find(".select").length>0){
				var proId="";
				
			    $("#pro").find(".select").each(function(){
				proId+=	$(this).data("id")+",";
					
			    })
			                proId=proId.substring(0,proId.length-1);	

				$.ajax({
					url:"d2p",
					type:"post",
					data:{
						type:"deleteBatch",
						depId:"${dep.id}",
						proId:proId
					},
				    dataType:"text",
				    success:function(data){
				    	if(data=="true"){
				    		var pro=$("#pro").find(".select");
				    		pro.removeClass("select");
				    		$("#noPro").append(pro);
				    	}
				    }
				} )   
				
			}else{
				alert("请选择数据");
				
			}
			})
			var proLeft=$("#model #pro").offset().left;
	        var proTop=$("#model #pro").offset().top;
	        var noProTop=$("#model #noPro").offset().top;
	        var proWidth=parseFloat($("#model #pro").css("width"));
	        var proHeight=parseFloat($("#model #pro").css("height"));
	        var startLeft;
	        var startTop;
				    $( "#model .pro" ).draggable({
				        start: function() {
				        startLeft=$(this).offset().left;
				        startTop=$(this).offset().top;
				          },
				         
				          stop: function() {
				        	  var stopLeft=$(this).offset().left;
				        	  var stopTop=$(this).offset().top;
				        	  
				        	  
				        	  if(stopLeft>=proLeft&&stopLeft<=proLeft+proWidth&&
				        			  stopTop>=proTop&&stopTop<=proTop+proHeight){
				        		  var pro=$(this)
				        		  var proId=$(this).data("id");
				        			
				      			$.ajax({
				      				url:"d2p",
				      				type:"post",
				      				data:{
				      					type:"add2",
				      					depId:"${dep.id}",
				      					proId:proId
				      				},
				      			    dataType:"text",
				      			    success:function(data){
				      			    	if(data=="true"){
				      			    		pro.css("position","static");
				      			    		$("#pro").append(pro);
				      			    		 pro.css("position","relative");
				      			    		pro.css("left","0");
				      			    		pro.css("top","0");
				      			    		
				      			    	}
				      			    }
				      			} ) 
				        	  }else if(stopLeft>=proLeft&&stopLeft<=proLeft+proWidth&&
					        			  stopTop>=noProTop&&stopTop<=noProTop+proHeight){
					        		  var pro=$(this)
					        		  var proId=$(this).data("id");
					        		  $.ajax({
					  					url:"d2p",
					  					type:"post",
					  					data:{
					  						type:"delete2",
					  						depId:"${dep.id}",
					  						proId:proId
					  					},
					  				    dataType:"text",
					  				    success:function(data){
					  				    	if(data=="true"){
					  				    		pro.css("position","static");
					      			    		$("#noPro").append(pro);
					      			    		 pro.css("position","relative");
					      			    		pro.css("left","0");
					      			    		pro.css("top","0");;
					  				    	}
					  				    }
					  				} )   
				        	  }else{
				        		  $(this).offset({left:startLeft,top:startTop})
				        	  }
				          
				          
				          }
				        });
				   
				    
				  

})  


</script>

	<div id="model">
		<h2 style="text-align: center">${dep.name }</h2>
		<div id="pro">
			<c:forEach items="${list }" var="pro">
				<div class="pro" data-id=${pro.id }>${pro.name }</div>
			</c:forEach>
		</div>
		<div id="btn">
			<button id="add" type="button" class="btn btn-primary">↑</button>
			<button id="addBatch" type="button" class="btn btn-primary">↑↑</button>
			<button id="deleteBatch" type="button" class="btn btn-primary">↓↓</button>
			<button id="delete-model" type="button" class="btn btn-primary">↓</button>
		</div>
		<div id="noPro">
			<c:forEach items="${noList }" var="pro">
				<div class="pro" data-id="${pro.id }">${pro.name }</div>
			</c:forEach>
		</div>
	</div>
