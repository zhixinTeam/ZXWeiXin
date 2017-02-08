<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
 <html>
  <head>
  <base href="<%=basePath%>">  
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content=" 河南志信商城  志信科技公司 ，科技公司" />
    <meta name="description" content=" 一卡通服务  水泥 " />
        <title>公司网站</title>
        
   
   <script src="static/shop/js/jquery.min.js" type="text/javascript"></script>
   <link rel="stylesheet" type="text/css" href="static/shop/css/gsyy.css"/>
    <link href="static/shop/css/bootstrap.min.css" rel="stylesheet">  
    <script src="static/shop/js/bootstrap.min.js" type="text/javascript" ></script> 
     <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
       <script type="text/javascript">
		//服务器校验
		function severCheck(){
			if(check()){
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				var code = "qqbigbug"+loginname+",zx,"+password+"qqbigbug"+",zx,"+$("#code").val();
				$.ajax({
					type: "POST",
					url: 'shop/shop_login',
			    	data: {KEYDATA:code,tm:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(data){
						if("success" == data.result){
							saveCookie();
							window.location.href="<%=basePath%>shop/order";
						}else if("usererror" == data.result){
							bootbox.alert("用户名或密码有误！");
							$("#loginname").focus();
						}else if("codeerror" == data.result){
							bootbox.alert("验证码错误！");
							$("#code").focus();
						}else{
							bootbox.alert("缺少参数！");
							$("#loginname").focus();
						}
					}
				});
			}
		}
	
		

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		

		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {
				
				bootbox.alert("账号不能为空！");
				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {
				bootbox.alert("密码不能为空！");
				$("#password").focus();
				return false;
			}
			
			if ($("#code").val() == "") {
				bootbox.alert("验证码不得为空!");
				$("#code").focus();
				return false;
			}
			
			
			return true;
		}

		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginname', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginname").val('');
				$("#password").val('');
			}
		}

		
		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		
		
		
		jQuery(function() {
			var loginname = $.cookie('loginname');
			var password = $.cookie('password');
			if (typeof(loginname) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginname").val(loginname);
				$("#password").val(password);
				$("#saveid").attr("checked", true);
				$("#code").focus();
			}
			changeCode();
			$("#codeImg").bind("click", changeCode);
		});
		
		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				//$("#to-recover").trigger("click");
				severCheck();
			}
		});

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		function changeCode() {
			$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
		}
		
		function edit_pwd(){
			window.location.href="<%=basePath%>shop/edit_pwd";
		}
	</script>
	
  </head>
  <body>
  
  <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">     
      <a class="navbar-brand" href="#"><img id="logo" src="static/shop/img/logo.png" alt="" /></a>
    </div>
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right  ">
        <li><a href="javascript:void(0);" onclick="toshop()" id="sy"  >首页 <span class="sr-only">(current)</span></a></li>
        <li><a href="javascript:void(0);" onclick="process()" >公司简介</a></li>
        <li><a href="javascript:void(0);" onclick="toorder()" id="ws" >申请提货单</a></li>
        <li><a href="javascript:void(0);" onclick="toorderlist()" id="wd" >我的提货订单</a></li>
        <c:if test="${not empty shopuser }">
         <li><a href="javascript:void(0);" onclick="logout()"  id="dl">退出</a></li>
          </c:if>
          <c:if test="${empty shopuser}">
                  <li><a href="javascript:void(0);" onclick="tologin()"  id="dl">登录</a></li>
          	</c:if>
      </ul>
     
    </div>
  </div>
  
</nav>
<link rel="stylesheet" type="text/css" href="static/shop/css/login.style.css"/>
	  <script type="text/javascript" src="static/js/bootbox.js"></script>  
<div id="box">
   	
   	<div class="login-box">
   		<div class="login-title text-center">
   			<h1><small>欢迎登录</small></h1>
   		</div>
   		<div class="login-content">
   			<div class="form">
   				<form action="#" method="post">
   					<div class="form-group">
   					<div class="col-xs-12">
   						<div class="input-group">
   							<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
   							<input type="text" name="username" id="loginname"  class="form-control" placeholder="用户名："/>
   						</div>
   					</div>	
   				</div>
   				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
							<input type="password" id="password" name="password" class="form-control" placeholder="密码">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					
						
				    <div class="col-lg-8" >  
				    	
                      
				   <img src="static/login/yan.png" />
                      <input type="text" name="code" id="code" class="login_code" />
					<img id="codeImg" alt="点击更换"title="点击更换" src="" />
					<span color="black" class="spw" >记住密码</span>
                     <input name="form-field-checkbox" class="spw" id="saveid" type="checkbox" onclick="savePaw();" />
                   <button type="button" onclick="severCheck();"   class="btn btn-sm btn-info"><span class="glyphicon glyphicon-off" id="to-recover"></span> 登录</button>
					</div>
        	        
					
				</div>
				
			
				</div>
				<div class="form-group">
					<div class="col-xs-6 link">
						<p class="text-center remove-margin"><small>忘记密码？</small> <a href="javascript:void(0)" onclick="edit_pwd()"><small>密码找回</small></a>
						</p>
					</div>
					
				</div>
   				</form>
   			</div>
   		</div>
   	</div>


<div class="clearfix"></div>
<script type="text/javascript">
	
		
		
 
 	
    function toshop(){
	 window.location.href="<%=basePath%>shop";
 	}
 	//下单页面
 	function toorder(){
 		window.location.href="<%=basePath%>shop/order";
 	}
 	//历史订单
    function toorderlist(){
    	window.location.href="<%=basePath%>shop/listOrders";
    }
 	//去登录页面
 	function tologin(){
	 	window.location.href="<%=basePath%>shop/login_toLogin";
 	}
 	//退出
 	function logout(){
 		window.location.href="<%=basePath%>shop/logout";
 	}
 	//运营流程
 	function process(){
 		window.location.href="<%=basePath%>shop/process";
 	}
 	
	</script>
<div id="footer">
	<p>电话：0371-63551234/63702237</p>
	<p>传真：0371-63702239   邮箱：hnzxtech@163.com</p>
	<p>地址：郑州市高新技术开发区长椿路电子商务产业园9号楼1504室
		<br />
		版权所有：河南志信科技有限公司 豫ICP备13019378号
	</p>
</div>
      <!--   <script src="static/1.9.1/jquery.min.js" type="text/javascript"></script>  -->
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="static/shop/js/bootstrap.min.js" type="text/javascript" ></script>
     <script src="static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="static/js/jquery.cookie.js"></script> 
     <!-- Include all compiled plugins (below), or include individual files as needed -->
  </body>
</html>


