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
        <script type="text/javascript">
            $(document).ready(function() {
                window.prettyPrint() && prettyPrint();
            });
        </script>
        <link rel="stylesheet" href="static/weixin/css/amazeui.min.css">
	  <link rel="stylesheet" type="text/css" href="static/weixin/css/app.css"/>
	  <link rel="stylesheet" href="static/weixin/js/loading/jquery.loading.css" type="text/css">
	   <script src="static/weixin/js/jquery.min.js"></script> 
	   <script src="static/weixinjs/amazeui.min.js"></script>
	     <!-- sweetalert start-->
		 <script src="static/weixin/js/sweetalert/dist/sweetalert-dev.js"></script>
		  <link rel="stylesheet" href="static/weixin/js/sweetalert/dist/sweetalert.css">
		 <!-- sweetalert end -->
   		 
		<title>志信科技</title>
<script type="text/javascript">
	
	$(document).ready(function(){
	 if($("#id").val()!="")
		 $("#username").attr("disabled",true); 
		 
	});
	
	function reset1(){ 
		//$("#factory_ID").val("");
		WeixinJSBridge.call('closeWindow');
		
		
	}
	
	//表单是否已经提交标识，默认为false
	
	//判断用户名是否存在
	function hasU(){
		
		var newusername = $("#username").val();
		if(newusername !="" && $("#id").val() ==""){
			var url = "wxuser/hasU?newusername="+newusername+"&tm="+new Date().getTime();
			$.ajax({
				   type: "get", cache: false,  
				    url: url,  
				    data: "",  
				    dataType: "text",  
				    success: function (data) {  
				        if (data == "error" || data == "") {  
				        	swal("账号已存在!");
				        	$("#username").val("");
							$("#username").focus();
				        } 
				    }  
			});
		}
		
		
	}
	 
	function save(){
		
		 
		if($("#factory_ID").val()==null){
			swal("请选择工厂!");
			$("#factory_ID").focus();
			return false;
		}
		$("#factory_IDs").val(($("#factory_ID").val()).join(","));
		
		
		if($("#username").val()==""){
			swal("账号不能为空!");
			$("#username").focus();
			return false;
		}
	
		if($("#username").val().length<3){
			swal("账号太短!");
			return false;
		}
		
		var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
		
		if($("#phone").val()==""){
					
					
					swal("手机号不能为空!");
					$("#phone").focus();
					return false;
				}else if($("#phone").val().length != 11 && !myreg.test($("#phone").val())){
					
					swal("手机号格式不正确!");
					$("#phone").focus();
					return false;
				}
				
		var phone =$("#phone").val();
		//swal("注册成功,初始化密码为'"+phone.substr(phone.length-6,1)+"'!");	
		
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
   				originalID:$("#originalID").val(),
   				phone:$("#phone").val(),
   				openid:$("#openid").val(),
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
				var msg= $("#msg").val();
				if(msg=="success"){
					swal("注册成功,初始化密码为'"+phone.substring(phone.length-6,phone.length)+"'!");
					 window.location.reload(); 
				}else if(msg=="oldsuccess"){
					swal("修改信息成功,初始化密码为'"+phone.substring(phone.length-6,phone.length)+"'!");
				}
				
			},
	    	error:function(){
	    		var time = setTimeout(function(){
     				//关闭
     				$(window).loading({action:"close",id:id});
     			},1000);
  				swal("注册失败，请返回微信主页重新进入!");
  			}
		});  
		
		
		
	}
	
	
	
	
</script>

	</head>
	
	<body>
<!--1-->
	    <header data-am-widget="header"
          class="am-header am-header-default">
          <a href="login.html">
          	<span class="am-icon-angle-left am-color am-icon-lg am-fl"></span>
         </a> 
      <h1 class="am-header-title am-color">
     注册页面
      </h1>
  </header>
	<!--1-->
	<div class="am-g am-register">
		
 <form action="wxuser/phone_index" class="am-form" id="doc-vld-msg" data-am-validator>
 
		<input type="hidden" name="wx_token" id="wx_token" value="${pd.wx_token}" />
		<input type="hidden" name="id" id="id" value="${pd.id }"/>
		<input type="hidden" name="openid" id="openid" value="${pd.fromUserName }"/>
		<input type="hidden" name="originalID" id="originalID" value="${pd.toUserName }"/>
		<input type="hidden" name="factory_IDs" id="factory_IDs" />
  <fieldset>
    <!--<legend>注册页面</legend>-->
    
      <div class="am-form-group">
      <label for="doc-select-2-1">关注工厂</label>
      <select class="am-round"  id="factory_ID"  name="factory_ID"   minchecked="1" maxchecked="10" multiple required>
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
    
    
    <div class="am-form-group">
      <label for="doc-vld-name-2">商城帐号</label><span style='color:sandybrown'>(英文字母、数字和下划线)</span>
      <input type="hidden" id="msg" value="<%=request.getAttribute("message") %>">
      <input type="text" class="am-round" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"   name="username" id="username" value="${pd.username }" onblur="hasU()" 
    	  minlength="3" placeholder="商城账号(至少 3 个字符)"
      required/>
    </div>

    <div class="am-form-group" data-am-validator>
      <label for="doc-vld-528">手机号</label>
      <input type="text" name="phone" id="phone"  value="${pd.phone }" class="js-pattern-mobile am-round"
             placeholder="输入手机号" required/>
    </div>
	<script type="text/javascript">
		if ($.AMUI && $.AMUI.validator) {
      $.AMUI.validator.patterns.mobile = /^\s*1\d{10}\s*$/;
}
	</script>
   <div class="am-g">
   	<div class="am-u-sm-6">
   		 <button class="am-btn am-btn-secondary am-btn-block am-round" type="button" onclick="save();">提交</button>
   	</div>
   	<div class="am-u-sm-6">
   		 <button class="am-btn am-btn-warning am-btn-block am-round" type="button" onclick="reset1();">返回</button>
    	
   	</div>
   </div> 
  </fieldset>
</form>
<script type="text/javascript">	
  $('#doc-vld-msg').validator({
    onValid: function(validity) {
      $(validity.field).closest('.am-form-group').find('.am-alert').hide();
    },
    onInValid: function(validity) {
      var $field = $(validity.field);
      var $group = $field.closest('.am-form-group');
      var $alert = $group.find('.am-alert');
      var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

      if (!$alert.length) {
        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
          appendTo($group);
      }
      $alert.html(msg).show();
    }
    
});

  
 

</script>


		
	</div>
	
<script src="static/weixin/js/jquery-1.7.2.js"></script>	
	
<script src="static/weixin/js/amazeui.min.js"></script>
<script src="static/weixin/js/jquery.min.js"></script> 
  <script src="static/weixin/js/loading/jquery.loading.js" type="text/javascript"></script> 
</body>
	
</html>