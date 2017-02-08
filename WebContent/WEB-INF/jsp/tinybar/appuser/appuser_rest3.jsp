<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<!--  -->
		<script type="text/javascript" src="static/js/bootstrap-multiselect.js"></script>
		<link rel="stylesheet" href="static/css/bootstrap-multiselect.css" type="text/css"/>
		<title>志信科技</title>
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
		
		var isCommitted = false;
	});
	function reset(){ 
		$("#factory_ID").val("");
		$("#susernumber").val("");
		$("#phone").val("");
		$("#wxusername").val("");
		$("#username").val("");
		$("#email").val("");
		  
		
		
	}
	
	//表单是否已经提交标识，默认为false
	 
	function save(){
		
		if($("#factory_ID").val()==null){
			
			$("#factory_ID").tips({
				side:3,
	            msg:'选择部门工厂',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#factory_ID").focus();
			return false;
		}
		$("#factory_IDs").val(($("#factory_ID").val()).join(","));
		//var factory_ID=($("#factory_ID").val()).join(",")
		 if($("#wxusername").val()==""){
					
					$("#wxusername").tips({
						side:3,
			            msg:'输入微信号',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#wxusername").focus();
					return false;
				}
		if($("#username").val()==""){
			
			$("#username").tips({
				side:3,
	            msg:'输入用户名',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#username").focus();
			return false;
		}
		
		
		
		
		
		
		var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
		
		if($("#phone").val()==""){
					
					$("#phone").tips({
						side:3,
			            msg:'输入手机号',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#phone").focus();
					return false;
				}else if($("#phone").val().length != 11 && !myreg.test($("#phone").val())){
					$("#phone").tips({
						side:3,
			            msg:'手机号格式不正确',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#phone").focus();
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
			 
		 
		
		$("#userBindForm").submit();
		
	}
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
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
<body >
	<div align="center">
	<h4>vip用户注册</h2>
	<hr>
	<form action="wxuser/phone_index" name="userBindForm" id="userBindForm" method="post" >
		<input type="hidden" name="wx_token" id="wx_token" value="${pd.wx_token}" />
		<input type="hidden" name="id" id="id" value="${pd.id }"/>
		<input type="hidden" name="openid" id="openid" value="${pd.fromUserName }"/>
		<input type="hidden" name="originalID" id="originalID" value="${pd.toUserName }"/>
		<input type="hidden" name="factory_IDs" id="factory_IDs" />
		<div id="zhongxin">
		<table>
			
			
			<tr class="info">
				<td>
				<select id="factory_ID" name="factory_ID"  multiple="multiple">
				<option value=""
				<c:if   test="${ fn:length(oldfactoryList) == 0}">
				 selected
				 </c:if> 
				 >请选择部门工厂</option>
				<c:forEach items="${factoryList}" var="factory">
				<option value="${factory.id }" 
				<c:forEach items="${oldfactoryList}" var="oldfac">
				<c:if test="${oldfac.id==factory.id}">
				selected 
				</c:if> 
				</c:forEach>
				 >${factory.factoryname }</option>
				 
				</c:forEach>
				    
				</select>
				</td>
			</tr>
			
			<%-- <tr class="info">
				<td>
				<select   name="factory_ID" id="factory_ID"  style="vertical-align:top;">
				<option value="">请选择部门工厂</option>
				<c:forEach items="${factoryList}" var="factory">
				<option value="${factory.id }">${factory.factoryname }</option>
				</c:forEach>
					</select>
				</td>
			</tr> --%>
			<tr>
				<td><input type="text" name="wxusername" id="wxusername" value="${pd.wxusername }"   maxlength="32"  placeholder="这里输入微信号" title="微信号" /></td>
			</tr>
			<tr>
				<td><input type="text" name="username" id="username" value="${pd.username }"   maxlength="32"  placeholder="这里输入用户名" title="用户名" "/></td>
			</tr>
			
			
			<tr>
				<td><input type="number" name="phone" id="phone"  value="${pd.phone }"  maxlength="32" placeholder="这里输入手机号" title="手机号"/></td>
			</tr>
			<tr>
				<td><input type="email" name="email" id="email"  value="${pd.email }" maxlength="32" placeholder="这里输入邮箱" title="邮箱" /></td>
			</tr>
			
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="reset();">重置</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
		
	</form>
	</div>
	
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