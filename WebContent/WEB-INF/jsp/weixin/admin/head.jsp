<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<base href="<%=basePath%>">
 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
   content="width=device-width, initial-scale=1">
  <title>Hello Amaze UI</title>
  <meta name="renderer" content="webkit"> 
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="icon" type="image/png" href="static/weixin/i/favicon.png">
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="static/weixin/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <link rel="apple-touch-icon-precomposed" href="static/weixin/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileImage" content="static/weixin/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">
  <link rel="stylesheet" href="static/weixin/css/amazeui.min.css">
 <link rel="stylesheet" type="text/css" href="static/weixin/css/dy.css"/>
  <script src="static/weixin/js/jquery.min.js"></script> 
</head>
<script type="text/javascript">

	function toindex(){
		window.location.href="<%=basePath %>/wxuser/tophoneindex?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName } ";
	}
	function toprocess(){
		window.location.href="<%=basePath %>/wxuser/toprocess?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName } ";
	}
	function toprocess_details(){
		window.location.href="<%=basePath %>/wxuser/process_details?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName } ";
	}
	
</script>
<body>
	<!--1-->
  <header data-am-widget="header" class="am-header am-header-default am-topbar-inverse
		 am-header-fixed">
  <div class="am-header-left am-header-nav">
    <a href="#left-link" class="">
      <i class="am-header-icon am-icon-home home am-icon-sm"></i>
    </a>    
  </div>
  <h1 class="am-header-title">当阳水泥公司</h1>  
   <nav data-am-widget="menu" class="am-menu  am-menu-offcanvas1"        
   data-am-menu-offcanvas> 
    <a href="javascript: void(0)" class="am-menu-toggle">
          <i class="am-menu-toggle-icon am-icon-bars am-icon-sm"></i>
    </a>
    <div class="am-offcanvas" >
      <div class="am-offcanvas-bar am-offcanvas-bar-flip">
      <ul class="am-menu-nav am-avg-sm-1">
          <li class="">
            <a href="javascript:void(0);" onclick="toindex()"  class="" >首页</a>
          </li>
          <li class="##">
            <a href="javascript:void(0);" onclick="toprocess()" class="" >新闻动态</a>
          </li>          
      </ul>
      </div>
    </div>
  </nav>
</header>
<!-- 导航头部结束 -->