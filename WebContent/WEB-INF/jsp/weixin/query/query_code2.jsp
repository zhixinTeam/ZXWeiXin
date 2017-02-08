<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
		
		<link rel="stylesheet" href="static/muselect/docs/css/bootstrap-3.3.2.min.css" type="text/css">
        <link rel="stylesheet" href="static/muselect/docs/css/bootstrap-example.css" type="text/css">
        <link rel="stylesheet" href="static/muselect/docs/css/prettify.css" type="text/css">

        <script type="text/javascript" src="static/muselect/docs/js/jquery-2.1.3.min.js"></script><!-- 
        <script type="text/javascript" src="static/muselect/docs/js/bootstrap-3.3.2.min.js"></script> -->
        <script type="text/javascript" src="static/muselect/docs/js/prettify.js"></script>

        <link rel="stylesheet" href="static/muselect/dist/css/bootstrap-multiselect.css" type="text/css">
        <script type="text/javascript" src="static/muselect/dist/js/bootstrap-multiselect.js"></script>
			<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<!--  
   		 <script type="text/javascript" src="static/js/bootbox.js"></script>   -->
   		 <script type="text/javascript" src="static/bootstrap3.0.3/bootstrap.min.js"></script> 
   		 <script type="text/javascript" src="static/js/bootbox.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                window.prettyPrint() && prettyPrint();
            });
        </script>
		<title>志信科技</title>
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
		
		var isCommitted = false;
	});
	
	
	
	
	//表单是否已经提交标识，默认为false
	
	
	
	//判断编码是否存在
	function hasN(USERNAME){
		var susernumber = $("#susernumber").val();
		var currentPage =$("#currentPage").val();
		if(susernumber !="" &&$("#USER_ID").val() ==""){
		var url = "<%=basePath%>user/hasN?susernumber="+susernumber+"&USERNAME="+USERNAME+"&currentPage="+currentPage+"&tm="+new Date().getTime();
		$.ajax({
			   type: "get", cache: false,  
			    url: url,  
			    data: "",  
			    dataType: "text",  
			    success: function (data) {  
			        if (data == "error" || data == "") {  
			        	$("#susernumber").tips({
							side:3,
				            msg:'编号已存在',
				            bg:'#AE81FF',
				            time:3
				        });
						setTimeout("$('#susernumber').val('')",1000);
			        }
			    }  
		});
		}
	}
	
</script>
<link rel="stylesheet" type="text/css" href="static/bootstrap-3.3.5-dist/css/bootstrap.min.css"> 

	</head>
<body >
	<div align="center">
	<h4>防伪码查询</h4>
	
	<hr>
	
	</div>
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		
		<script type="text/javascript">
		
		
		$(function() {
			
			
			
		});
		
		</script>
	
</body>
</html>