<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="../wxadmin/top.jsp"%>
</head>
<body> 

<%@ include file="../wxadmin/head.jsp"%>
       
<div class="all-elements">
    <%@ include file="../wxadmin/left.jsp"%>
    
    
    
    <!-- Page Content-->
    <div id="content" class="snap-content">        
        <div class="header-clear"></div>
        <div class="content">
            
          
            
        </div>
        <!-- Page Footer-->
         <%@ include file="../wxadmin/floor.jsp"%>
        
    </div>
    
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
<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery-1.9.1.min.js" > </script>
		
<!--引入属于此页面的js -->
		<script type="text/javascript" src="static/js/myphone/phone_head.js"></script>
</body>

