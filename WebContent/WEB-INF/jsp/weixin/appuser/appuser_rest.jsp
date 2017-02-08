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
		
		<link rel="stylesheet" href="static/loading/jquery.loading.css" type="text/css">
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
		WeixinJSBridge.call('closeWindow');
		
		
	}
	
	//表单是否已经提交标识，默认为false
	 
	function save(){
		if($("#flag").val()=="1"){
			bootbox.alert("绑定完成无法修改，请先到工厂完成注册解绑" ,function(){
				
			});
			return false;
		}
			
		
		
		if($("#factory_ID").val()==null){
			
			bootbox.alert("请选择工厂" ,function(){
			});
			
			$("#factory_ID").focus();
			return false;
		}
		$("#factory_IDs").val(($("#factory_ID").val()).join(","));
		
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
				
		
				
		
				
		//打开
		var id = "body-loading";
		$(window).loading({action:"open",id:id,font:40});		
						
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
				var time = setTimeout(function(){
     				//关闭
     				$(window).loading({action:"close",id:id});
     			},1000);
				bootbox.alert("注册成功，请到相应工厂绑定!" ,function(){
				});
			},
	    	error:function(){
	    		var time = setTimeout(function(){
     				//关闭
     				$(window).loading({action:"close",id:id});
     			},1000);
  				bootbox.alert("注册失败，请返回微信主页重新进入!");
  			}
		}); 
		
		
		
	}
	
	
	
	
</script>
<link rel="stylesheet" type="text/css" href="static/bootstrap-3.3.5-dist/css/bootstrap.min.css"> 

	</head>
<body >
<button type="button" class="btn btn-primary btn-block btn-lg">用户注册</button>
  	<div class="boot_top">
	<h5>已绑定的工厂：</h5>
	<c:forEach items="${oldfactoryList}" var="factory">
			<c:if test="${factory.is_bind=='1'}">
				 <input type="hidden" id="flag" value="1">
			<input type="checkbox" value='1'  checked="checked"  disabled="disabled" /><label>${factory.factoryname } </label><br>
			</c:if>
	</c:forEach>
	</div>
	<hr>
	<form action="wxuser/phone_index" name="userBindForm" id="userBindForm" method="post" >
		<input type="hidden" name="old_facids_str" id="old_facids_str" value="${pd.old_facids_str}" />
		<input type="hidden" name="wx_token" id="wx_token" value="${pd.wx_token}" />
		<input type="hidden" name="id" id="id" value="${pd.id }"/>
		<input type="hidden" name="openid" id="openid" value="${pd.fromUserName }"/>
		<input type="hidden" name="originalID" id="originalID" value="${pd.toUserName }"/>
		<input type="hidden" name="factory_IDs" id="factory_IDs" />
		<div id="zhongxin">
		
<script type="text/javascript">
    $(document).ready(function() {
        $('#factory_ID').multiselect({
        	nonSelectedText: '请选择部门工厂',
        	allSelectedText: '已全选',
        	buttonWidth: '100%'
        });
    });
</script>
	
 <div  class="form-group"  >
    <label for="name">关注工厂:</label><br>
    		
		<select id="factory_ID"  name="factory_ID" style="width:100%;"  multiple="multiple"  required > 
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
				
				
			
			
		</div>
		
  </div>
  <div class="form-group">
    <label for="name">用户名：</label>
    <input type="text" class="form-control" name="username" id="username" value="${pd.username }"    required >
  </div>
  <div class="form-group">
    <label for="name">手机号：</label>
    <input type="text" class="form-control" name="phone" id="phone"  value="${pd.phone }"   required >
  </div>
  <button type="button" class="btn btn-danger pull-left" style="width: 140px; margin-left: 15px;" onclick="save();">保存</button>
  <button type="button" class="btn btn-primary  pull-right" style="width: 140px; margin-right: 15px;" onclick="reset1();">返回</button>
	
	</form>
	
	
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
	
		  <script>


/**
 * Author:ZengWeilong Version 1.4
 * V1.4
 */
(function() {
	/**
	 * options = {action:"open",id:loadingId}
	 */
	$.fn.loading = function(options) {
		/*默认值不可随意更改*/
		var dfault = {
			id : null, /*loading Id*/
			rgba : 0.6, /*背景透明度*/
			action : "close", /*执行方式true打开 其他值关闭*/
			before : null, /* 动作执行前函数*/
			after : null, /*动作执行后函数*/
			font:10,
			position : "absolute" /*fixed 固定绝对位置 absolute相对位置*/
		};
		var json = $.extend({},dfault, options);

		if (json.id == null)
			return;
		
		var winobj = $(this);
		if (typeof (json.before) == "function")
			json.before();
		
		/*core*/
		var core = {
			restyle : function(winobj) {
				var height = $(winobj).innerHeight();
				var width = $(winobj).innerWidth();
				var top = $(winobj).offset() == undefined ? 0 : $(winobj).offset().top;
				var left = $(winobj).offset() == undefined ? 0 : $(winobj).offset().left;
				json.position = $(winobj).offset() == undefined ? "fixed":"absolute";
				var style = "z-index:10000;position:" + json.position + ";left:" + left + "px;top:"
						+ top + "px;width:" + width + "px;"
						+ "text-align:center;background:rgba(168,168,168," + json.rgba
						+ ");height:" + height + "px;line-height:" + height + "px;";
				return style;
			},
			open:function(){
				if (!$("#" + json.id).attr("style")) {
					var style = core.restyle(winobj);
					var divhtml = "<div id='" + json.id + "' style='display:none;" + style
							+ "'><span class='spinner-loader' style='font-size:"+json.font+"px!important;'>Loading&#8230;</span></div>";
					$("body").append(divhtml);
				}
				$("#" + json.id).fadeIn(200, function() {
					if (typeof (json.after) == "function")
						json.after();
				});
			},
			close:function(){
				$("#" + json.id).fadeOut(500, function() {
					if (typeof (json.after) == "function")
						json.after();
				}).remove();
			},
			resize:function(){
				var style = core.restyle(winobj);
				$("#" + json.id).removeAttr("style").attr("style", style);
			}
		}
		
		/*open*/
		if ("open" == json.action) {
			core.open();
		}
		/*close*/
		if ("close" == json.action) {
			core.close();
		}
		/*window change Listen*/
		$(window).resize(function() {
			core.resize();
		});
	}

})();





	
</script>
		
</body>
</html>