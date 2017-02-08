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
		
		$('#facshow').hide();
		$('#category').change(function(){
			if("facid" ==$(this).children('option:selected').val()){
				$('#facshow').show();
				$('#comdiv').hide();
			}else{
				$('#facshow').hide();
				$('#comdiv').show();
			}
			//window.location.href="xx.php?param1="+p1+"¶m2="+p2+"";//页面跳转并传参
			});;
	});
	
	//保存
	function save(){
		
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
		  }
		  
		if($("#adminname").val() ==""){
			
			$("#adminname").tips({
				side:3,
	            msg:'输入集团管理员',
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
		
		if($("#orgcode").val() ==""){
			
			$("#orgcode").tips({
				side:3,
	            msg:'输入组织机构唯一码',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#adminname").focus();
			return false;
		}
		
		
		$("#comForm").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
		
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
      <div id="legend" class="">
        <legend class="">新增公司集团1</legend>
      </div>
    <div class="control-group">
          <!-- Select Basic -->
          <label class="control-label">集团or工厂</label>
          <div class="controls">
            <select class="input-xlarge" name="category" id="category">
      <option selected="selected" value="">请选择</option>
      <option value="comid">公司/集团</option>
      <option value="facid">部门/工厂</option></select>
          </div>

        </div>
        
        
         <div class="control-group" id="facshow">
	          <!-- Select Basic -->
	          <label class="control-label">选择集团</label>
	          <div class="controls">
	            <select class="input-xlarge" name="categorycom" id="categorycom">
		      	<option selected="selected" value="">请选择</option>
			     <c:forEach items="${comlist}" var="com">
						<option value="${com.id }" >${com.companyname }</option>
					</c:forEach>
					</select>
		          </div>
	
	       	 </div>
	       	 
	       	 
        
        <div id="comdiv">
	       	 
        <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">集团名称</label>
          <div class="controls">
            <input placeholder=""   name="companyname" id="companyname" value="${pd.companyname }"  class="input-xlarge" type="text">
            <p class="help-block"></p>
          </div>
        </div>
        
        <div class="control-group">

          <!-- Text input-->
          <label class="control-label"  for="input01">微信帐号</label>
          <div class="controls">
            <input placeholder="" name="wechatsub" id="wechatsub" value="${pd.wechatsub }"  class="input-xlarge" type="text">
            <p class="help-block"></p>
          </div>
        </div>
        
		<div class="control-group">

          <!-- Text input-->
          <label class="control-label"  for="input01">微信原始ID</label>
          <div class="controls">
            <input placeholder="" name="originalID" id="originalID" value="${pd.originalID }"  class="input-xlarge" type="text">
            <p class="help-block"></p>
          </div>
        </div>
    <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">AppID</label>
          <div class="controls">
            <input placeholder="" name="appid" id="appid" value="${pd.appid }"   class="input-xlarge" type="text">
            <p class="help-block"></p>
          </div>
        </div>

    <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">SecrectID</label>
          <div class="controls">
            <input placeholder="" name="secrectid" id="secrectid" value="${pd.secrectid }" class="input-xlarge" type="text">
            <p class="help-block"></p>
          </div>
        </div>
        </div>
        <!-- ====================================end=========================================================== -->
        
        <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">工厂名称</label>
          <div class="controls">
            <input placeholder=""   name="factoryname" id="factoryname" value="${pd.factoryname }"  class="input-xlarge" type="text">
            <p class="help-block"></p>
          </div>
        </div>

    

    	<div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">管理员</label>
          <div class="controls">
            <input placeholder=""  name="adminname" id="adminname" value="${pd.adminname }" class="input-xlarge" type="text" onblur="hasU()">
            <p class="help-block"></p>
          </div>
        </div>

    <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">管理员密码</label>
          <div class="controls">
            <input placeholder="" name="adminpassword" id="adminpassword" value="${pd.adminpassword }" class="input-xlarge" type="password">
            <p class="help-block"></p>
          </div>
        </div>

    
        
         <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">法人姓名</label>
          <div class="controls">
            <input placeholder="" name="truename" id="truename" value="${pd.truename }" class="input-xlarge" type="text">
            <p class="help-block"></p>
          </div>
        </div>
        
        <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">电话号码</label>
          <div class="controls">
            <input placeholder="" name="mobile" id="mobile" value="${pd.mobile }" class="input-xlarge" type="number">
            <p class="help-block"></p>
          </div>
        </div>
        <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">邮箱</label>
          <div class="controls">
            <input placeholder="" name="email" id="email" maxlength="32" value="${pd.email }"  onblur="hasE('${pd.USERNAME }')"  class="input-xlarge" type="email">
            <p class="help-block"></p>
          </div>
        </div>

    <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">服务地址</label>
          <div class="controls">
            <div class="textarea">
                  <textarea type="text" name="serviceurl" id="serviceurl" value="${pd.serviceurl }" class=""> </textarea>
            </div>
          </div>
        </div>

    <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">服务参数</label>
          <div class="controls">
            <div class="textarea">
                  <textarea type="text" name="serviceparam" id="serviceparam" value="${pd.serviceparam }" class=""> </textarea>
            </div>
          </div>
        </div>
        <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">组织机构唯一码</label>
          <div class="controls">
            <input placeholder=""  name="orgcode" id="orgcode" value="${pd.orgcode }" class="input-xlarge" type="text">
            <p class="help-block"></p>
          </div>
        </div>
	
    <div class="control-group">
         
          <!-- Button -->
          <div class="controls">
            <a class="btn btn-mini btn-primary" onclick="save();">保存</a>
			<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
          </div>
          
        </div>
        

    </fieldset>
  </div>
  </form>
	
	
		<!-- 引入 -->	
	<script type="text/javascript"> 
		
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		}
		
		</script> 
	
</body>
</html>