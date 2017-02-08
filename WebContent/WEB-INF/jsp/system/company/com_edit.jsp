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
		if( $("#USER_ID").val() !=""){
			$("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray");
			$("#susernumber").attr("readonly","readonly");
			$("#susernumber").css("color","gray");
			$("#email").attr("readonly","readonly");
			$("#email").css("color","gray");
		}
	});
	
	//保存
	function save(){
		
		if($("#companyname").val()==""){
			
			$("#companyname").tips({
				side:3,
	            msg:'输入集团名称',
	            bg:'#AE81FF',
	            time:3
	        });	
			$("#companyname").focus();
			return false;
		}
		
		if($("#wechatsub").val()==""){
					
					$("#wechatsub").tips({
						side:3,
			            msg:'输入公众号',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#wechatsub").focus();
					return false;
				}
		
		if($("#Appid").val()==""){
			
			$("#Appid").tips({
				side:3,
	            msg:'输入公众号',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#Appid").focus();
			return false;
		}
		if($("#Appid").val()==""){
					
					$("#Appid").tips({
						side:3,
			            msg:'输入公众号',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#Appid").focus();
					return false;
				}
		
		
		$("#companyForm").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
		
	}
	
</script> 
	</head>
<body>
	<form action="company/${msg}" name="companyForm" id="companyForm" method="post">
		<input type="hidden" name="companyID" id="companyID" value="${pd.companyID }"/>
		<input type="hidden" name="USERNAME" id="USERNAME" value="${pd.USERNAME }"/>
		<input type="hidden" name="currentPage" id="currentPage" value="${pd.currentPage }"/>
		<div id="zhongxin">
		<table>
			
			
			<tr>
				<td><input type="text" name="companyname" id="companyname" value="${pd.companyname }"   maxlength="32"  placeholder="这里输入集团名" title="集团名" onblur="hasU()"/></td>
			</tr>
			<tr>
				<td>
					<select name="status" title="状态">
					<option value="1" <c:if test="${pd.status == '1' }">selected</c:if> >服务正常</option>
					<option value="0" <c:if test="${pd.status == '0' }">selected</c:if> >服务关闭</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><input type="text" name="wechatsub" id="wechatsub" value="${pd.wechatsub }" maxlength="32" placeholder="这里输入公共账号" title="公共账号" onblur="hasN('${pd.USERNAME }')"/></td>
			</tr>
			 <tr>
				<td><input type="text" name="Appid" id="Appid" value="${pd.Appid }"  maxlength="32" placeholder="输入AppID" title="AppID"/></td>
			</tr> 
			<tr>
				<td><input type="text" name="Secrectid" id="Secrectid" value="${pd.Secrectid }"  maxlength="32" placeholder="输入SecrectID" title="SecrectID" /></td>
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