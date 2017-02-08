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
	
	
	function reset1(){ 
		//$("#factory_ID").val("");
		$("#susernumber").val("");
		$("#phone").val("");
		//$("#wxusername").val("");
		$("#username").val("");
		//$("#email").val("");
		  
		
		
	}
	
	//表单是否已经提交标识，默认为false
	 
	function save(){
		
		
		if($("#factory_ID").val()==null){
			
			$("#factory_ID").tips({
				side:3,
	            msg:'请选择工厂',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#factory_ID").focus();
			return false;
		}
		$("#factory_IDs").val(($("#factory_ID").val()).join(","));
		//var factory_ID=($("#factory_ID").val()).join(",")
		/*  if($("#wxusername").val()==""){
					
					$("#wxusername").tips({
						side:3,
			            msg:'输入微信号',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#wxusername").focus();
					return false;
				} */
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
				
		/* if($("#email").val()==""){
					
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
				}    */
			 
		 
		
		//$("#userBindForm").submit();
				
		
				
				
						
		var url = "<%=basePath%>/wxuser/register?tm="+new Date().getTime();
		$.ajax({
			type: "POST",
			url: url,
	    	data:{
 				wx_token:$("#wx_token").val(),
   				id:$("#id").val(),
   				factory_IDs:$("#factory_IDs").val(),
   				//wxusername:$("#wxusername").val(),
   				originalID:$("#originalID").val(),
   				phone:$("#phone").val(),
   				openid:$("#openid").val(),
   				//email:$("#email").val(),
   				username:$("#username").val(),
   				old_facids_str:$("#old_facids_str").val() 
   				},
			dataType:'json',
			cache: false,
			success: function(data){
				bootbox.alert("注册成功，请到相应工厂绑定!" ,function(){
				});
				
			},
	    	error:function(){
  				bootbox.alert("注册失败，请返回微信主页重新进入!");
  			}
		}); 
		
		
		
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
<link rel="stylesheet" type="text/css" href="static/bootstrap-3.3.5-dist/css/bootstrap.min.css"> 

	</head>
<body >
	<div align="center">
	<h4>vip用户注册</h4>
	<hr>已经绑定的工厂：<br>
	<c:forEach items="${oldfactoryList}" var="factory">
			<c:if test="${factory.is_bind=='1'}">
			<input type="checkbox" value='1'  checked="checked"  disabled="disabled" /><label>${factory.factoryname } </label><br>
			</c:if>
	</c:forEach>
	<hr>
	<form action="wxuser/phone_index" name="userBindForm" id="userBindForm" method="post" >
		<input type="hidden" name="old_facids_str" id="old_facids_str" value="${pd.old_facids_str}" />
		<input type="hidden" name="wx_token" id="wx_token" value="${pd.wx_token}" />
		<input type="hidden" name="id" id="id" value="${pd.id }"/>
		<input type="hidden" name="openid" id="openid" value="${pd.fromUserName }"/>
		<input type="hidden" name="originalID" id="originalID" value="${pd.toUserName }"/>
		<input type="hidden" name="factory_IDs" id="factory_IDs" />
		
			<%-- <input type="hidden" name="fromUserName" id="fromUserName" value="${pd.fromUserName }" /> --%>
			<%-- <input type="hidden" name="toUserName" id="toUserName" value="${pd.toUserName }" /> --%>
		<div id="zhongxin">
		
<script type="text/javascript">
    $(document).ready(function() {
        $('#factory_ID').multiselect({
        	nonSelectedText: '请选择工厂',
        	allSelectedText: '已全选',
        	buttonWidth: '170px'
        });
    });
</script>
		<table>
			
			
			<tr class="info">
			<td>关注工厂</td>
				<td >
				<select  id="factory_ID" name="factory_ID" style="width:141px;"   multiple="multiple">
			  
				<c:forEach items="${factoryList}" var="factory">
				<option value="${factory.id }" 
				<c:forEach items="${oldfactoryList}" var="oldfac">
				<c:if test="${oldfac.id==factory.id}">
				 selected="selected"
				</c:if> 
				</c:forEach>
				 >${factory.factoryname }</option>
				 
				</c:forEach>
			</select>
				
				</td>
			</tr>
			
			<%-- <tr>
				<td>微信号</td>
				<td><input type="text" name="wxusername" id="wxusername" value="${pd.wxusername }"   maxlength="32"  placeholder="这里输入微信号" title="微信号" /></td>
			</tr> --%>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" id="username" value="${pd.username }"   maxlength="32"  placeholder="这里输入用户名" title="用户名"/></td>
			</tr>
			
			
			<tr>
				<td>手机号</td>
				<td><input type="number" name="phone" id="phone"  value="${pd.phone }"  maxlength="32" placeholder="这里输入手机号" title="手机号"/></td>
			</tr>
			<%-- <tr>
				<td>邮箱&nbsp</td>
				<td><input type="email" name="email" id="email"  value="${pd.email }" maxlength="32" placeholder="这里输入邮箱" title="邮箱" /></td>
			</tr> --%>
			
			<tr>
			<td></td>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="reset1();">重置</a>
				</td>
			</tr>
			
		</table>
		</div>
		
		
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
		
	</form>
	</div>
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
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