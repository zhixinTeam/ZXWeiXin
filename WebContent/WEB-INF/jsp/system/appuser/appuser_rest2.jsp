<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
 <script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
		
	});
	
	//保存
	function save(){
		  if($("#role_id").val()==""){
			
			$("#role_id").tips({
				side:3,
	            msg:'选择角色',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#role_id").focus();
			return false;
		}
		if($("#loginname").val()=="" || $("#loginname").val()=="此用户名已存在!"){
			
			$("#loginname").tips({
				side:3,
	            msg:'输入用户名',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#loginname").focus();
			$("#loginname").val('');
			$("#loginname").css("background-color","white");
			return false;
		}else{
			$("#loginname").val(jQuery.trim($('#loginname').val()));
		}
		
		if($("#susernumber").val()==""){
			
			$("#susernumber").tips({
				side:3,
	            msg:'输入编号',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#susernumber").focus();
			return false;
		}
		
		if($("#USER_ID").val() !="" && $("#password").val()==""){
			
			$("#password").tips({
				side:3,
	            msg:'输入密码',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#password").focus();
			return false;
		}
		if($("#password").val()!=$("#chkpwd").val()){
			
			$("#chkpwd").tips({
				side:3,
	            msg:'两次密码不相同',
	            bg:'#AE81FF',
	            time:3
	        });
			
			$("#chkpwd").focus();
			return false;
		}
		if($("#name").val()==""){
			
			$("#name").tips({
				side:3,
	            msg:'输入姓名',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		}
		
		var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
		if($("#mobile").val()==""){
			
			$("#mobile").tips({
				side:3,
	            msg:'输入手机号',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#mobile").focus();
			return false;
		}else if($("#mobile").val().length != 11 && !myreg.test($("#mobile").val())){
			$("#mobile").tips({
				side:3,
	            msg:'手机号格式不正确',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#mobile").focus();
			return false;
		}
		
		if($("#email").val()==""){
			
			$("#email").tips({
				side:3,
	            msg:'输入邮箱',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#email").focus();
			return false;
		}else if(!ismail($("#email").val())){
			$("#email").tips({
				side:3,
	            msg:'邮箱格式不正确',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#email").focus();
			return false;
		}  
		
		$("#userForm").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
		
	}
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
		}
	
	//判断用户名是否存在
	function hasU(){
		
		var newusername = $("#loginname").val();
		if(newusername !="" && $("#USER_ID").val() ==""){
			var url = "user/hasU?newusername="+newusername+"&tm="+new Date().getTime();
			$.ajax({
				   type: "get", cache: false,  
				    url: url,  
				    data: "",  
				    dataType: "text",  
				    success: function (data) {  
				        if (data == "error" || data == "") {  
				        	$("#loginname").tips({
								side:3,
					            msg:'用户名已存在',
					            bg:'#AE81FF',
					            time:3
					        });
							setTimeout("$('#loginname').val('')",1000);
				        } 
				    }  
			});
		}
		
		
	}
	
	//判断邮箱是否存在
	function hasE(USERNAME){
		var email = $("#email").val();
		if(email !="" &&$("#USER_ID").val() ==""){
		var url = "<%=basePath%>user/hasE?email="+email+"&USERNAME="+USERNAME+"&tm="+new Date().getTime();
		
		$.ajax({
			   type: "get", cache: false,  
			    url: url,  
			    data: "",  
			    dataType: "text",  
			    success: function (data) {  
			        if (data == "error" || data == "") {  
			        	$("#email").tips({
							side:3,
				            msg:'邮箱已存在',
				            bg:'#AE81FF',
				            time:3
				        });
						setTimeout("$('#email').val('')",1000);
			        }
			    }  
		});
		
		
		}
		
	}
	
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
	</head>
<body>
	<form action="user/${msg}" name="userForm" id="userForm" method="post">
		<div id="zhongxin">
		<table>
			<tr class="info">
				<td>
				请选择工厂：<select class="chzn-select"  data-placeholder="请选择工厂" style="vertical-align:top;">
					<option value=""></option>
					<option value="1" >工厂1</option>
					<option value="2" >工厂2</option>
					<option value="3" >测试工厂</option>
				</select>
				</td>
			</tr>
			
			<tr>
				<td><input type="text" name="username" id="loginname" value="${pd.username }"   maxlength="32"  placeholder="这里输入用户名" title="用户名" onblur="hasU()"/></td>
			</tr>
			<tr>
				<td><input type="text" name="susernumber" id="susernumber" value="${pd.susernumber }" maxlength="32" placeholder="这里输入编号" title="编号" onblur="hasN('${pd.USERNAME }')"/></td>
			</tr>
			 <tr>
				<td><input type="password" name="password" id="password" value="${pd.password }"  maxlength="32" placeholder="输入密码" title="密码"/></td>
			</tr> 
			<tr>
				<td><input type="password" name="chkpwd" id="chkpwd" value="${pd.chkpwd }"  maxlength="32" placeholder="确认密码" title="确认密码" /></td>
			</tr>
			
			<tr>
				<td><input type="text" name="truename" id="name"  value="${pd.truename }"  maxlength="32" placeholder="这里输入姓名" title="姓名"/></td>
			</tr>
			<tr>
				<td><input type="number" name="mobile" id="mobile"  value="${pd.mobile }"  maxlength="32" placeholder="这里输入手机号" title="手机号"/></td>
			</tr>
			<tr>
				<td><input type="email" name="email" id="email"  value="${pd.email }" maxlength="32" placeholder="这里输入邮箱" title="邮箱" onblur="hasE('${pd.USERNAME }')"/></td>
			</tr>
			<tr>
				<td><input type="text" name="BZ" id="BZ"value="${pd.BZ }" placeholder="这里输入备注" maxlength="64" title="备注"/></td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
		
	</form>
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		
		 <script type="text/javascript">
		
		$(function() {
			
			//单选框
		$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script> 
	
</body>
</html>