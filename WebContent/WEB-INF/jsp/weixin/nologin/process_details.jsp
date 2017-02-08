<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
 <head>
 <base href="<%=basePath%>">
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
   content="width=device-width, initial-scale=1">
  <title>新闻详情</title>
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
<body>
<script type="text/javascript">

	function toindex(){
		window.location.href="<%=basePath %>/wxuser/tophoneindex?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName } ";
	}
	function toprocess(){
		window.location.href="<%=basePath %>/wxuser/toprocess?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName } ";
	}
	function toprocess_details(){
		window.location.href="<%=basePath %>/wxuser/process_details?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName }&apppackid=${pd.apppackid } ";
	}
</script>
	<!--1-->
  <header data-am-widget="header" class="am-header am-header-default am-topbar-inverse
		 am-header-fixed">
  <div class="am-header-left am-header-nav">
    <a href="javascript:void(0);" onclick="toprocess_details()"  >
      <i class="am-header-icon am-icon-home home am-icon-sm"></i>
    </a>    
  </div>
  <h1 class="am-header-title">新闻详情</h1>  
   <nav data-am-widget="menu" class="am-menu  am-menu-offcanvas1"        
   data-am-menu-offcanvas> 
    <a href="javascript: void(0);" class="am-menu-toggle">
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
<!--1-->  
<!--2-->  
   <div data-am-widget="slider" class="am-slider am-slider-b2" >
  <ul class="am-slides">
       <c:forEach items="${list_app_pictures}" var="apppicture" > 
      <li>
         <img src="<%=basePath %>uploadFiles/uploadImgs/${apppicture.path } " >
        
      </li>
      </c:forEach>
  </ul>
</div>
<script type="text/javascript">
	$(function() {
  $('.am-slider').flexslider({
   animation: "fade",             
  reverse: false,              
  animationLoop: true,  
   animationSpeed: 400      
  });
});
</script>
 <!--2-->
   <div data-am-widget="list_news" class="am-list-news am-list-news-default" > 
    <div class="am-list-news-hd am-cf">    
        <a href="javascript:void(0);" onclick="toprocess_details()" class="">
          <h2>新闻动态</h2>
            <span class="am-list-news-more am-fr">更多 &raquo;</span>
        </a>
         </div>
  <div class="am-list-news-bd">
  <ul class="am-list">    
      
      
      
      
      
		      <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-top">
        <div class="am-list-thumb am-u-sm-12">
          <a href="" class="">
            <img src="<%=basePath %>uploadFiles/uploadImgs/${app_pack.path }" alt=""/>
          </a>
        </div>
        <div class=" am-list-main">
            <h3 class="am-list-item-hd">
            	<a href="javascript:void(0);" class="">${app_pack.miaosu }</a></h3>
            <div class="am-gs-text">${app_pack.context }    </div>
      </li>
     
     
    </ul>
  </div>

    </div>


 <!--5--> 
   <footer data-am-widget="footer"
          class="am-footer am-footer-default"
           data-am-footer="{  }">
      <div class="am-footer-miscs ">

          <p>由 <a href="www.hnzxtech.com/" title="河南志信"
                                                target="_blank" class="">河南志信科技有限公司</a>
            提供技术支持</p>
        <p>CopyRight©2016  big bug.</p>
    </div>
  </footer>

 
 <!--5-->
 
 
 
  <div data-am-widget="gotop" class="am-gotop am-gotop-fixed">
  <a href="#top" title="">
    <img class="am-gotop-icon-custom" src="<%=basePath %>static/weixin/img/s.png"
    />
  </a>
</div>
<script src="<%=basePath %>static/weixin/js/jquery.min.js"></script>
<script src="<%=basePath %>static/weixin/js/amazeui.min.js"></script>
</body>
</html>