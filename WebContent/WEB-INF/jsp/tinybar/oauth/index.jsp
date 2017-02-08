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

<title>${picturelog.title }</title>

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
<%-- <%=basePath %>uploadFiles/uploadImgs/${picture.path } --%>

<style>
 .main1-logo{
   
    width:110px;
    height:18px;
    background-size:110px 18px;
    background-repeat:no-repeat;
    margin-top:37px;
    float:left;
    margin-left:30px;
}
</style>
</head>
<body> 

<div id="preloader">
	<div id="status">
    	<p class="center-text">
			加载中...
        </p>
    </div>
</div>

<div class="all-elements">
    <div class="snap-drawers">
        <!-- Left Sidebar -->
        <div class="snap-drawer snap-drawer-left">  
            <a href="<%=basePath %>/wxuser/tophoneindex?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName } " class="selected-item"><i class="fa fa-home"></i>主页</a>
             <a href="<%=basePath %>/wxorder/listorders?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName } "><i class="fa fa-cog"></i>我的订单</a>
         <!--   <a href="page-media.html"><i class="fa fa-picture-o"></i>Media</a>
            <a href="page-blog.html"><i class="fa fa-pencil"></i>Blog</a>
            <a href="contact.html"><i class="fa fa-envelope-o"></i>Contact</a>
            <a href="#"><i class="fa fa-facebook"></i>Like Us</a>
            <a href="#"><i class="fa fa-twitter"></i>Follow Us</a>
            <a href="#"><i class="fa fa-google-plus"></i>Follow us</a>
            <a href="#" class="show-share-bottom"><i class="fa fa-retweet"></i>Share</a> -->
            <a href="#" class="sidebar-close"><i class="fa fa-times"></i>关闭</a>
        </div>
    </div>
   <!--  ../images/logo-dark.png -->
    <div class="header">
    
      <img src="<%=basePath %>uploadFiles/uploadImgs/${picturelog.path } " class="main1-logo" > 
        <a href="#" class="open-menu"><i class="fa fa-navicon"></i></a><!-- 
        <a href="contact.html" class="open-mail"><i class="fa fa-envelope-o"></i></a> -->
       <!--  <a href="tel:+1331121279" class="open-call"><i class="fa fa-phone"></i></a> -->
        
    </div> 
    
    <a href="#" class="footer-ball"><i class="fa fa-navicon"></i></a>
    
    <!-- Page Content--> 
    <div id="content" class="snap-content">        
        <div class="slider-container full-bottom">
            <div class="homepage-slider" data-snap-ignore="true"> 
                  <c:forEach items="${listpictures}" var="picture" >         
                <div>
                    <div class="overlay"></div>
                    <div class="homepage-slider-caption homepage-left-caption">
                        <h3>${picture.title } </h3>
                        <p>${picture.bz } </p>
                    </div>
                    <img src="<%=basePath %>uploadFiles/uploadImgs/${picture.path } " class="responsive-image" alt="img">
                </div>
                </c:forEach>
                
            </div>
        </div>
        
        <div class="content">            
            <div class="container no-bottom">
                <h3>欢迎!</h3>
                <p>
                   		欢迎使用本系统！
                </p>

            </div>
            <div class="decoration"></div>
            
            <div class="content-heading full-bottom">
                <h2>服务项</h2>
                <em>公司目标：更优的服务</em>
                <i class="fa fa-cog"></i>
            </div>
            
            <div class="decoration"></div>
            
           
            <div class="decoration"></div>
            
            
           
           
            <div class="decoration"></div>
            
        </div>
        <!-- Page Footer-->
        <div class="footer">
            <p class="center-text">志信科技 © big bug 2015</p>
             <div class="footer-socials half-bottom" >
                <!-- <a href="#" class="footer-facebook"><i class="fa fa-facebook"></i></a>
                <a href="#" class="footer-twitter"><i class="fa fa-twitter"></i></a>
                <a href="#" class="footer-transparent"></a>
                <a href="#" class="footer-share show-share-bottom"><i class="fa fa-share-alt"></i></a>
                <a href="#" class="footer-up"><i class="fa fa-angle-up"></i></a> -->
            </div> 
        </div>     
        
    </div>
    
   
   <!--  end page content -->
   
   
    <div class="share-bottom">
        <h3>Share Page</h3>
        <div class="share-socials-bottom">
            <a href="">
                <i class="fa fa-facebook facebook-color"></i>
                Facebook
            </a>
            <a href="">
                <i class="fa fa-twitter twitter-color"></i>
                Twitter
            </a>
            <a href="">
                <i class="fa fa-google-plus google-color"></i>
                Google
            </a>

            <a href="">
                <i class="fa fa-pinterest-p pinterest-color"></i>
                Pinterest
            </a>
            <a href="sms:">
                <i class="fa fa-comment-o sms-color"></i>
                Text
            </a>
            <a href="">
                <i class="fa fa-envelope-o mail-color"></i>
                Email
            </a>
        </div>
        <a href="#" class="close-share-bottom">Close</a>
    </div>
    
</div>

</body>

