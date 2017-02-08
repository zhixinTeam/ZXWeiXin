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
		
		$("#username").attr("readonly","readonly");
		$("#username").css("color","gray");
	});
	
	//保存
	function save(){
		
		if($("#factoryname").val()==""){
			
			$("#factoryname").tips({
				side:3,
	            msg:'输入工厂名称',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#factoryname").focus();
			return false;
		}
		
		if($("#serviceurl").val()==""){
					
					$("#serviceurl").tips({
						side:3,
			            msg:'输入服务地址',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#serviceurl").focus();
					return false;
				}
		
		if($("#serviceparam").val()==""){
			
			$("#serviceparam").tips({
				side:3,
	            msg:'输入参数格式',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#serviceparam").focus();
			return false;
		}
	
		$("#factoryForm").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
		
	} 
	
</script> 
	</head>
<body>
	<form action="company/${msg}" name="factoryForm" id="factoryForm" method="post">
		<input type="hidden" name="factoryID" id="factoryID" value="${pd.factoryID }"/>
		<input type="hidden" name="USERNAME" id="USERNAME" value="${pd.USERNAME }"/>
		<input type="hidden" name="sys_userid" id="sys_userid" value="${pd.sys_userid }"/>
		<input type="hidden" name="currentPage" id="currentPage" value="${pd.currentPage }"/>
		<div id="zhongxin">
		<table>
			
			<tr>
				<td>
					<select name="STATUS" title="状态">
					<option value="1" <c:if test="${pd.STATUS == '1' }">selected</c:if> >服务正常</option>
					<option value="0" <c:if test="${pd.STATUS == '0' }">selected</c:if> >服务关闭</option>
					</select>
				</td>
			</tr>
			<tr><td><input class="span10 date-picker" name="startdate" id="startdate" value="${pd.startdate}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly"  placeholder="开始日期"/></td></tr>
			<tr><td><input class="span10 date-picker"  name="enddate" id="enddate" value="${pd.enddate}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly"  placeholder="到期日期"/></td></tr>
					
			<tr>
				<td><input type="text" name="editlog" id="editlog" value="${pd.editlog }"   maxlength="32"  placeholder="这里输入服务开闭原因" title="服务操作日志" /></td>
			</tr>
			<tr>
				<td><input type="text" name="factoryname" id="factoryname" value="${pd.factoryname }"   maxlength="32"  placeholder="这里输入工厂名" title="工厂名" onblur="hasU()"/></td>
			</tr>
			<tr>
				<td><input type="text" name="serviceurl" id="serviceurl" value="${pd.serviceurl }" maxlength="32" placeholder="这里输入工厂服务地址" title="服务地址" onblur="hasN('${pd.USERNAME }')"/></td>
			</tr>
			 <tr>
				<td><input type="text" name="serviceparam" id="serviceparam" value="${pd.serviceparam }"  maxlength="32" placeholder="输入服务参数" title="参数格式"/></td>
			</tr> 
			
			<tr>
				<td><input type="text" name="username" id="username" value="${pd.username }"   maxlength="32"  placeholder="这里输入用户名" title="用户名" onblur="hasU()"/></td>
			</tr> 
			
			<tr>
				<td><input type="password" name="password" id="password" value="${pd.password }"  maxlength="32" placeholder="不修改密码此次不填" title="密码"/></td>
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
		 $(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker({
				
				 showMeridian: true,
			        autoclose: true,
			        todayBtn: true
			});
			
		});

		</script>
	 
</body>
</html>