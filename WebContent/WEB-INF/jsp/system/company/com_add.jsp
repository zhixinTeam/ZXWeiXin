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
		<link rel="stylesheet" href="static/loading/jquery.loading.css" type="text/css">
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript"> 
	 $(top.hangge());
	$(document).ready(function(){
		
		$('#facshow').hide();
		$('#category').change(function(){
			if("facid" ==$(this).children('option:selected').val()){
				$('#facshow').show();
				$('#comdiv').hide();
				$('#comcls').hide();
				
			}else{
				$('#facshow').hide();
				$('#comdiv').show();
				$('#comcls').show();
			}
			//window.location.href="xx.php?param1="+p1+"¶m2="+p2+"";//页面跳转并传参
			});;
	});
	
	//保存
	function save(){
		//打开
		
	 	  if($("#category").val()==""){
			
			$("#category").tips({
				side:3,
	            msg:'选择类别',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#category").focus();
			return false;
		}
		
		  if($('#category').val() != "facid"){
				  if($("#companyname").val()=="" ){
					
					$("#companyname").tips({
						side:3,
			            msg:'输入集团名称',
			            bg:'#AE81FF',
			            time:2
			        });
					
					$("#companyname").focus();
					return false;
				}else{
					$("#companyname").val(jQuery.trim($('#companyname').val()));
				}
			
				if($("#wechatsub").val()==""){
					
					$("#wechatsub").tips({
						side:3,
			            msg:'输入微信号',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#wechatsub").focus();
					return false;
				}
				
				if($("#originalID").val()==""){
									
									$("#originalID").tips({
										side:3,
							            msg:'输入微信号',
							            bg:'#AE81FF',
							            time:3
							        });
									$("#originalID").focus();
									return false;
								}
				if($("#appid").val() ==""){
					
					$("#appid").tips({
						side:3,
			            msg:'输入appid',
			            bg:'#AE81FF',
			            time:2
			        });
					
					$("#appid").focus();
					return false;
				}
			if($("#secrectid").val() ==""){
						
						$("#secrectid").tips({
							side:3,
				            msg:'输入secrectid',
				            bg:'#AE81FF',
				            time:2
				        });
						
						$("#secrectid").focus();
						return false;
					}
		  }else{
			 
			  if($("#categorycom").val()==""){
					
					$("#categorycom").tips({
						side:3,
			            msg:'选择集团',
			            bg:'#AE81FF',
			            time:2
			        });
					
					$("#categorycom").focus();
					return false;
				}
		  }
		 
		  if($("#factoryname").val() ==""){
				
				$("#factoryname").tips({
					side:3,
		            msg:'输入工厂名称',
		            bg:'#AE81FF',
		            time:2
		        });
				
				$("#factoryname").focus();
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
		  
		if($("#adminname").val() ==""){
			
			$("#adminname").tips({
				side:3,
	            msg:'输入管理员',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#adminname").focus();
			return false;
		}
		if($("#adminpassword").val() ==""){
			
			$("#adminpassword").tips({
				side:3,
	            msg:'输入管理员密码',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#adminpassword").focus();
			return false;
		}
		
		if($("#truename").val() ==""){
					
					$("#truename").tips({
						side:3,
			            msg:'输入法人姓名',
			            bg:'#AE81FF',
			            time:2
			        });
					
					$("#truename").focus();
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
		
		if($("#orgcode").val() ==""){
					
					$("#orgcode").tips({
						side:3,
			            msg:'输入组织机构唯一码',
			            bg:'#AE81FF',
			            time:2
			        });
					
					$("#orgcode").focus();
					return false;
				}
		 
		
		
		if($("#serviceurl").val() ==" "){
			$("#serviceurl").tips({
				side:3,
	            msg:'输入服务地址',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#serviceurl").focus();
			return false;
		}
		
		if($("#serviceparam").val()==" "){
			
			$("#serviceparam").tips({
				side:3,
	            msg:'输入参数',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#serviceparam").focus();
			return false;
		}
		
		//打开
		 var id = "body-loading";
		$(window).loading({action:"open",id:id,font:40});
		$("#comForm").submit();
		$("#zhongxin").hide();
		//$("#zhongxin2").show();
		
	}
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
		}
	
	//判断用户名是否存在
	function hasU(){
		
		var newusername = $("#adminname").val();
		if(newusername !="" ){
			var url = "user/hasU?newusername="+newusername+"&tm="+new Date().getTime();
			$.ajax({
				   type: "get", cache: false,  
				    url: url,  
				    data: "",  
				    dataType: "text",  
				    success: function (data) {  
				        if (data == "error" || data == "") {  
				        	$("#adminname").tips({
								side:3,
					            msg:'管理员名已存在',
					            bg:'#AE81FF',
					            time:3
					        });
							setTimeout("$('#adminname').val('')",1000);
				        } 
				    }  
			});
		}
		
		
	}
	
	
	//判断邮箱是否存在
	function hasE(USERNAME){
		var email = $("#email").val();
		if(email !="" ){
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
	
	 </script> 
	</head>
<body>

<form class="form-horizontal" action="company/${msg}" name="comForm" id="comForm" method="post">
	
		<input type="hidden" name="USERNAME" id="USERNAME" value="${pd.USERNAME }"/>
		<input type="hidden" name="currentPage" id="currentPage" value="${pd.currentPage }"/>
	<div id="zhongxin">
    <fieldset>
        			<div class="form-group">
                           <label for="disabledSelect"  class="col-sm-2 control-label">集团or工厂</label>
                           <div class="col-sm-3">
                              <select name="category" id="category" class="form-control">
                                 <option selected="selected" value="">请选择</option>
							      <option value="comid">公司/集团</option>
							      <option value="facid">部门/工厂</option></select>
                           </div>
        				<div id="facshow">
                           <label for="disabledSelect"  class="col-sm-2 control-label">选择集团</label>
                          
                           <div class="col-sm-3">
                              <select name="categorycom" id="categorycom" class="form-control">
                                <option selected="selected" value="">请选择</option>
							     <c:forEach items="${comlist}" var="com">
										<option value="${com.id }" >${com.companyname }</option>
								 </c:forEach>
                              </select>
                           </div>
                           
        				</div>
        				 <div id="comdiv">
        
        					<label class="col-sm-2 control-label" for="ds_name">集团名称</label>
                          <div class="col-sm-3">
                             <input class="form-control" placeholder=""   name="companyname" id="companyname" value="${pd.companyname }"  type="text" />
                          </div>
	       
        					</div>
        				
        				</div>
	       	  <!-- ====================================start=========================================================== -->
       		<div id="comcls">
       				<fieldset>
                        <legend></legend>
                        <div class="form-group">
                           <label for="disabledSelect"  class="col-sm-2 control-label">微信帐号</label>
                           <div class="col-sm-3">
                            	<input placeholder="" name="wechatsub" id="wechatsub" value="${pd.wechatsub }"  class="form-control" type="text">
                           </div>

						   <label for="disabledSelect"  class="col-sm-2 control-label">微信原始ID</label>
                           <div class="col-sm-3">
                           		<input placeholder="" name="originalID" id="originalID" value="${pd.originalID }"  class="form-control" type="text">
                           </div>
                        </div>
                    </fieldset>
                    
                    
                     <fieldset>
					    <legend></legend>
                         <div class="form-group">
                          <label class="col-sm-2 control-label" for="ds_username">AppID</label>
                          <div class="col-sm-3">
                             <input class="form-control" name="appid" id="appid" value="${pd.appid }" type="text" placeholder=""/>
                          </div>
                          <label class="col-sm-2 control-label" for="ds_password">SecrectID</label>
                          <div class="col-sm-3">
                             <input class="form-control" name="secrectid" id="secrectid" value="${pd.secrectid }" type="text" placeholder=""/>
                          </div>
                       </div>
                    </fieldset>
       		
       		</div>
       		 <!-- ====================================end=========================================================== -->
        	<fieldset>
                        <legend></legend>
                        <div class="form-group">
                           <label for="disabledSelect"  class="col-sm-2 control-label">工厂名称</label>
                           <div class="col-sm-3">
                             <input class="form-control"  name="factoryname" id="factoryname" value="${pd.factoryname }" type="text" placeholder=""/>
                          </div>

						  <label for="disabledSelect"  class="col-sm-2 control-label">邮箱</label>
                           <div class="col-sm-3">
                             <input class="form-control" name="email" id="email" maxlength="32" value="${pd.email }"  onblur="hasE('${pd.USERNAME }')"  type="email" placeholder="" />
                          </div>
                        </div>
                    </fieldset>
			
				<fieldset>
                        <legend></legend>
                        
                        <div class="form-group">
                        <label for="disabledSelect"  class="col-sm-2 control-label">管理员</label>
                           <div class="col-sm-3">
                             <input class="form-control" name="adminname" id="adminname" value="${pd.adminname }" type="text" placeholder="" onblur="hasU()"/>
                          </div>
                           <label for="disabledSelect"  class="col-sm-2 control-label">管理员密码</label>
                           <div class="col-sm-3">
                             <input class="form-control" name="adminpassword" id="adminpassword" value="${pd.adminpassword }"  type="password" placeholder=""/>
                          </div>

						   
                        </div>
                    </fieldset>
			
		
                       <fieldset>
					    <legend></legend>
                         <div class="form-group">
                         <label for="disabledSelect"  class="col-sm-1 control-label">法人姓名</label>
                           <div class="col-sm-3">
                             <input class="form-control" name="truename" id="truename" value="${pd.truename }" type="text" placeholder="" />
                          </div>
                           <label for="disabledSelect"  class="col-sm-1 control-label">电话号码</label>
                           <div class="col-sm-3">
                             <input class="form-control" name="mobile" id="mobile" value="${pd.mobile }"  type="number" placeholder=""/>
                          </div>
                          <label class="col-sm-1 control-label" for="ds_username">组织机构唯一码</label>
                          <div class="col-sm-3">
                             <input class="form-control" name="orgcode" id="orgcode" value="${pd.orgcode }" type="text" placeholder=""/>
                          </div>
                         
                       </div>
                    </fieldset>
                    <fieldset>
                        <legend></legend>
                       <div class="form-group">
                          <label class="col-sm-2 control-label" for="ds_host">服务地址</label>
                          <div class="col-sm-3">
                               <textarea type="text" name="serviceurl" id="serviceurl" value="${pd.serviceurl }" class="form-control"> </textarea>
                           </div>
                          <label class="col-sm-2 control-label" for="ds_name">服务参数</label>
                          <div class="col-sm-3">
                             <textarea type="text" name="serviceparam" id="serviceparam" value="${pd.serviceparam }" class="form-control"> </textarea>
                          </div>
                       </div>
                      
                    </fieldset>     
				 <legend></legend>
        <!-- ====================================end=========================================================== -->
        
         <center> 
          <!-- Button -->
          <div class="controls">
            <a class="btn btn-mini btn-primary" onclick="save();">保存</a>
			<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
          </div>
       </center>
        
       <%--  <center> 
			<button type="button" id="button1" onclick="save();" align="center" class="btn btn-primary">提交</button>    
			<button type="button" id="button2"  onclick="rest();"  class="btn btn-warning">重置</button>
			<center> --%>

    </fieldset>
  </div>
  <!-- <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
	 -->	
  </form>
	


		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		
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