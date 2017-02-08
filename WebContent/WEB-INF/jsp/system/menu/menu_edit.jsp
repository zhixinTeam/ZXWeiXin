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
		<title>菜单</title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>	
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
		<script type="text/javascript">
			$(top.hangge());	
			$(document).ready(function(){
					if($("#id").val()!=""){
						var parentId = $("#pId").val();
						if(parentId=="0"){
							$("#parentId").attr("disabled",true);
						}else{
							$("#parentId").val(parentId);
							$("#form-field-radio1").attr("disabled",true);
							$("#form-field-radio2").attr("disabled",true);
							$("#form-field-radio1").attr("checked",false);
							$("#form-field-radio2").attr("checked",false);
						}
					}
				});
				
				var authpath = "";
				function setMUR(){
					authpathy = $("#authpath").val();
					if(authpathy != ''){authpath = authpathy;}
					if($("#parentId").val()=="0"){
						$("#authpath").attr("readonly",true);
						$("#authpath").val("");
						$("#form-field-radio1").attr("disabled",false);
						$("#form-field-radio2").attr("disabled",false);
					}else{
						$("#authpath").attr("readonly",false);
						$("#authpath").val(authpath);
						$("#form-field-radio1").attr("disabled",true);
						$("#form-field-radio2").attr("disabled",true);
						$("#form-field-radio1").attr("checked",false);
						$("#form-field-radio2").attr("checked",false);
					}
				}
				
				//保存
				function save(){
					if($("#authname").val()==""){
						
						$("#authname").tips({
							side:3,
				            msg:'请输入菜单名称',
				            bg:'#AE81FF',
				            time:2
				        });
						
						$("#authname").focus();
						return false;
					}
					if($("#authpath").val()==""){
						$("#authpath").val('#');
					}
					if($("#authorder").val()==""){
						
						$("#authorder").tips({
							side:1,
				            msg:'请输入菜单序号',
				            bg:'#AE81FF',
				            time:2
				        });
						
						$("#authorder").focus();
						return false;
					}
					
					if(isNaN(Number($("#authorder").val()))){
						
						$("#authorder").tips({
							side:1,
				            msg:'请输入菜单序号',
				            bg:'#AE81FF',
				            time:2
				        });
						
						$("#authorder").focus();
						$("#authorder").val(1);
						return false;
					}
					
					$("#menuForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				}
				
				function setType(value){
					$("#authtype").val(value);
				}
		</script>
		
	</head>
	
	<body>
		<form action="auth/edit" name="menuForm" id="menuForm" method="post">
			<input type="hidden" name="id" id="id" value="${pd.id}"/>
			<input type="hidden" name="pId" id="pId" value="${pd.parentid}"/>
			<input type="hidden" name="authtype" id="authtype" value="${pd.authtype }"/>
			<div id="zhongxin">
			<table>
				<tr>
					<td>
						<select name="PARENT_ID" id="parentId" onchange="setMUR()" title="菜单">
							<option value="0">顶级菜单</option>
							<c:forEach items="${menuList}" var="auth">
								<option value="${auth.id }">${auth.authname }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td><input type="text" name="authname" id="authname" placeholder="这里输入菜单名称" value="${pd.authname }" title="名称"/></td>
				</tr>
				<tr>
					<td><input type="text" name="authpath" id="authpath" placeholder="这里输入链接地址" value="${pd.authpath }" title="地址"/></td>
				</tr>
				<tr>
					<td><input type="number" name="authorder" id="authorder" placeholder="这里输入序号" value="${pd.authorder}" title="序号"/></td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<label style="float:left;padding-left: 32px;"><input name="form-field-radio" id="form-field-radio1" onclick="setType('1');" <c:if test="${pd.authtype == '1' }">checked="checked"</c:if> type="radio" value="icon-edit"><span class="lbl">系统菜单</span></label>
						<label style="float:left;padding-left: 5px;"><input name="form-field-radio" id="form-field-radio2" onclick="setType('2');" <c:if test="${pd.authtype != '1' }">checked="checked"</c:if> type="radio" value="icon-edit"><span class="lbl">业务菜单</span></label>
					</td>
				</tr>
				<tr>
				<td style="text-align: center; padding-top: 10px;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
			</table>
			</div>
			<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
		</form>
	</body>
</html>