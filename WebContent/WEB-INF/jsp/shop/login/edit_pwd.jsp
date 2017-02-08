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
				$("#to-recover").trigger("click");
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
<style type="text/css">
  	#bgcolor{
  		width: 100%;
  		height: 600px;
  		
  		
  	}
  	
 #box_a{
 	width: 600px;
 	height: 550px;
 /*	background-color:#09304f;*/
 	/*border: 2px solid #537ea1;*/
 	margin:0 auto;
 	position: relative;
 	top: -10px;
 	border-radius:10px;
 	
 } 	
  #aside{
 background-color:rgb(102,102,102);
 border-radius:10px;
 height: 480px;
 width: 560px;
 margin: 0 auto;
position: relative;
  	top:25px;

 }	
  #aside h1{
  	
  	padding: 5px 0 0 0;
  	color: white;
  	font-weight: normal;
  	font-size: 35px;
  	text-align: center;
  	
  
  	
  }	

  #aside_cen{
  	width: 560px;
  	height: 350px;
  	background-color:#F5F5F5;
  	margin-top: 8px;
  }	
 .input-group{
 	position: relative;
 	top: 20px;
 	padding: 15px 0;
 	width: 450px;
 	margin-left: 25px;
 	
 } 	
 .input-group input{
 	height: 45px;
 	
 } 
 #code{
 	width: 230px;
 }	
  .btn-default{
  	float: right;
  	height: 45px;
  	margin-top: 0px;
  	margin-right: 2px;
  	cursor: pointer;
  }	
  .btn-info{
  	margin-left: 90px;
  	margin-top: 16px;
  	width: 120px;
  	font-size: 20px;
  }
  .btn-warning{
  	margin-left: 150px;
  	margin-top: 16px;
  	width: 120px;
  	font-size: 20px;
  
  	border: none;
  	
  }	
  	  </style>
  	 <!-- 确认窗口 -->
   		 <script type="text/javascript" src="static/bootstrap3.0.3/bootstrap.min.js"></script> 
   		 <script type="text/javascript" src="static/js/bootbox.js"></script>
         <!-- 确认窗口 -->
<div id="bgcolor">
   <div id="box_a"> 	
   		<div id="aside">
   			<h1>找回密码</h1>
   			
   			<div id="aside_cen">
   			<div class="input-group">
           <span class="input-group-addon" id="basic-addon1">请输入账号</span>
           <input type="text" class="form-control" name="username" id ="username" placeholder="请输入账号" aria-describedby="basic-addon1">
           </div>	
   			<div class="input-group">
           <span class="input-group-addon" id="basic-addon1">输入验证码</span>
           <input type="text"  name="code"  id ="code" class="form-control" placeholder="输入验证码" aria-describedby="basic-addon1">
            <button type="button" class="btn btn-default" onclick="get_code(this)" >获取验证码</button> 
        
           </div>
          
           
           <div class="input-group">
           <span class="input-group-addon" id="basic-addon1">请输入密码</span>
           <input type="password" name="password"  id ="password" class="form-control" placeholder="请输入密码" aria-describedby="basic-addon1">
           </div>	
           
           <div class="input-group">
           <span class="input-group-addon" id="basic-addon1">请确认密码  </span>
           <input type="password" name="checkpassword"  id ="checkpassword"  class="form-control" placeholder="请输入密码" aria-describedby="basic-addon1">
           </div>	
             
   			</div>
   			
   			<button type="button" class="btn btn-info btn-lg" onclick="save_pwd()"><span class="glyphicon glyphicon-off"></span>确定</button>
   			<a href="javascript:void(0)" onclick="go_back()"><button type="button" class="btn btn-warning btn-lg"><span class="glyphicon glyphicon-repeat"></span>返回</button></a>
   		</div>
  
   </div>	
</div>

<div class="clearfix"></div>
<script type="text/javascript">
    function go_back(){
    	window.history.go(-1)
    }
    
    var clock = '';
    var nums = 30;
    var btn;
    function get_code(thisBtn){
    	
    	var username =$("#username").val();
    	 var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
		 if($("#username").val()==""){
				bootbox.alert("手机号不能为空!");
				$("#username").focus();
				return false;
			}else if($("#username").val().length != 11 && !myreg.test($("#username").val())){
				bootbox.alert("手机号格式不正确!");
				$("#username").focus();
				return false;
			}
		 btn = thisBtn;
    	 btn.disabled = true; //将按钮置为不可点击
    	 btn.innerText = nums+'秒后获取';
    	clock = setInterval(doLoop, 1000); //一秒执行一次
    	var url = "<%=basePath%>shop/find_code";
    	 $.ajax({
			   type: "post", 
			   cache: false,  
			    url: url,  
			    timeout:30000,
			    data:{
			    	username:username,
					datetime:new Date().getTime()
				},
			    dataType: "json",  
			    success: function (data) { 
			    	//var str = data.toJSONString();
			    	
			    	/* for(var i=0;i<data["listgoods"].length;i++){
			    		document.getElementById("snxh").options.add(new Option(data["listgoods"][i]["typename"]+data["listgoods"][i]["goodsname"],data["listgoods"][i]["g_id"]));
			    	}
			    	for(var i=0;i<data["listclient"].length;i++){
			    		document.getElementById("snkh").options.add(new Option(data["listclient"][i]["clientnumber"],data["listclient"][i]["c_id"]));
			    		
			    		
			    	} */
			    }  
		}); 
    }
    
    function save_pwd(){
    	var username =$("#username").val();
    	var code =$("#code").val();
    	var password =$("#password").val();
    	var checkpassword =$("#checkpassword").val();
    	var url = "<%=basePath%>shop/save_pwd";
    	 if($("#username").val()==""){
				bootbox.alert("手机号不能为空!");
				$("#username").focus();
				return false;
			}else if($("#username").val().length != 11 && !myreg.test($("#username").val())){
				bootbox.alert("手机号格式不正确!");
				$("#username").focus();
				return false;
			}
    	 if($("#code").val()==""){
				bootbox.alert("验证码不能为空!");
				$("#code").focus();
				return false;
			}
    	 if($("#password").val()==""){
				bootbox.alert("密码不能为空!");
				$("#password").focus();
				return false;
			}
    	 if($("#checkpassword").val()==""){
				bootbox.alert("确认密码不能为空!");
				$("#checkpassword").focus();
				return false;
			}
    	 if($("#password").val()!=$("#checkpassword").val()){
    		 bootbox.alert("两次密码不相同!");
				$("#checkpassword").focus();
				return false;
    	 }
    	 $.ajax({
			   type: "post", 
			   cache: false,  
			    url: url,  
			    timeout:10000,
			    data:{
			    	username:username,
			    	code:code,
			    	password:password,
					datetime:new Date().getTime()
				},
			    dataType: "json",  
			    success: function (data) { 
			    	 bootbox.alert(data["err_str"]);
			    }  
		}); 
    }
    
    
    function doLoop()
    {
    nums--;
    if(nums > 0){
     btn.innerText = nums+'秒后获取';
    }else{
     clearInterval(clock); //清除js定时器
     btn.disabled = false;
     btn.innerText = '获取验证码';
     nums = 10; //重置时间
    }
    }
 </script>

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


