<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<base href="<%=basePath%>">
	
	 <head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
   <meta name="keywords" content=" 河南志信商城  志信科技公司 ，科技公司" />
    <meta name="description" content=" 一卡通服务  水泥 " />
    <title>公司网站</title>  
    <link href="static/shop/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="static/shop/css/style.css" />
   <script src="static/shop/js/jquery.min.js" type="text/javascript"></script>
   <script src="static/shop/js/bootstrap.min.js" type="text/javascript" ></script>
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head> 
  </script>
  <body>
  <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">     
      <a class="navbar-brand" href="#"><img id="logo" src="static/shop/img/logo.png" alt="" /></a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right  ">
        <li><a href="javascript:void(0);" onclick="toshop()" id="sy" class="animated slideInDown" >首页 <span class="sr-only">(current)</span></a></li>
        <li><a href="javascript:void(0);" onclick="process()" id="gs" class="animated slideInDown">公司简介</a></li>
        <li><a href="javascript:void(0);" onclick="toorder()" id="ws" class="animated slideInDown">申请提货单</a></li>
        <li><a href="javascript:void(0);" onclick="toorderlist()" id="wd" class="animated slideInDown">我的提货订单</a></li>
        <c:if test="${not empty shopuser }">
         <li><a href="javascript:void(0);" onclick="logout()" class="animated slideInDown" id="dl">退出</a></li>
          </c:if>
          <c:if test="${empty shopuser}">
                  <li><a href="javascript:void(0);" onclick="tologin()" class="animated slideInDown" id="dl">登录</a></li>
          	</c:if>
      </ul>
     
    </div>
  </div>
</nav>