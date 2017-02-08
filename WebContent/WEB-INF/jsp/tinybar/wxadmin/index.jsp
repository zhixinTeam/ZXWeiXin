<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${picturelog.title } </title>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>

		

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


<div id="preloader">
	<div id="status">
    	<p class="center-text">
			加载中...
        </p>
    </div>
</div>
<div class="header">
    
      <img src="<%=basePath %>uploadFiles/uploadImgs/${picturelog.path } " class="main1-logo" > 
        <a href="#" class="open-menu"><i class="fa fa-navicon"></i></a><!-- 
        <a href="contact.html" class="open-mail"><i class="fa fa-envelope-o"></i></a> -->
       <!--  <a href="tel:+1331121279" class="open-call"><i class="fa fa-phone"></i></a> -->
        
    </div> 
</head>
<body> 
<div class="all-elements">

    <%@ include file="left.jsp"%>
   <!--  ../images/logo-dark.png -->
    
    
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
         <%@ include file="floor.jsp"%>
        
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

		<script type="text/javascript" src="static/js/jquery-1.9.1.min.js" > </script>
<script type="text/javascript" src="static/js/myphone/phone_head.js"></script>
</body>
</html>

