<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0 minimal-ui"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=basePath %>staticphone/images/splash/splash-icon.png">
<link rel="apple-touch-icon-precomposed" sizes="180x180" href="<%=basePath %>staticphone/images/splash/splash-icon-big.png">
<link rel="apple-touch-startup-image" href="<%=basePath %>staticphone/images/splash/splash-screen.png" 	media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="<%=basePath %>staticphone/images/splash/splash-screen@2x.png" media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" href="<%=basePath %>staticphone/images/splash/splash-screen-six.png" media="(device-width: 375px)">
<link rel="apple-touch-startup-image" href="<%=basePath %>staticphone/images/splash/splash-screen-six-plus.png" media="(device-width: 414px)">
<link rel="apple-touch-startup-image" sizes="640x1096" href="<%=basePath %>staticphone/images/splash/splash-screen@3x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="<%=basePath %>staticphone/images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="<%=basePath %>staticphone/images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="<%=basePath %>staticphone/images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="<%=basePath %>staticphone/images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>
<title>Epsilon Mobile Framework - Version 2.0</title>

<link href="<%=basePath %>staticphone/styles/style.css"     		 rel="stylesheet" type="text/css">
<link href="<%=basePath %>staticphone/styles/framework.css" 		 rel="stylesheet" type="text/css">
<link href="<%=basePath %>staticphone/styles/owl.theme.css" 		 rel="stylesheet" type="text/css">
<link href="<%=basePath %>staticphone/styles/swipebox.css"		 rel="stylesheet" type="text/css">
<link href="<%=basePath %>staticphone/styles/font-awesome.css"	 rel="stylesheet" type="text/css">
<link href="<%=basePath %>staticphone/styles/animate.css"			 rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=basePath %>staticphone/scripts/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>staticphone/scripts/jqueryui.js"></script>
<script type="text/javascript" src="<%=basePath %>staticphone/scripts/framework.plugins.js"></script>
<script type="text/javascript" src="<%=basePath %>staticphone/scripts/custom.js"></script>

</head>
<body> 

<div id="preloader">
	<div id="status">
    	<p class="center-text">
			加载中...
        </p>
    </div>
</div>












