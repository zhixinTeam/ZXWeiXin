<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
<base href="<%=basePath%>">
  <meta charset="UTF-8">
  <title>河南志信科技公众平台</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="static/shop_pc/i/favicon.png">
  <link rel="stylesheet" href="static/shop_pc/css/amazeui.min.css"/>
  <link rel="stylesheet" href="static/shop_pc/css/admin.css">
  <link rel="stylesheet" type="text/css" href="static/shop_pc/css/app.css"/>
  <link rel="stylesheet" href="static/shop_pc/js/loading/jquery.loading.css" type="text/css">
  <script src="static/shop_pc/myjs/login.js"></script>
  <script src="static/weixin/js/sweetalert/dist/sweetalert-dev.js"></script>
  <link rel="stylesheet" href="static/weixin/js/sweetalert/dist/sweetalert.css">
</head>
<body style="background:url(images/login-bg.jpg) center repeat-y;">
	<header class="am-topbar am-topbar-inverse admin-header">
  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
  	<div class="am-topbar-brand">
    <strong>易购水泥公众平台</strong>
  </div>
    <ul class="am-nav am-nav-pills am-topbar-nav">
        <li ><a href="shop/toindex">首页</a></li>
        <li class="am-active"><a href="shop/totjsja">个人中心</a></li>
      </ul>
      
     

      <div class="am-topbar-right">
      	<a href="shop/toregister">
        <button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm">
        	<span class="am-icon-pencil"></span> 注册</button>
        </a>
      </div>

     
  </div>
</header>

	<div class="login-box">
    <div class="logo-img">
      <img src="static/shop_pc/images/logo2_03.png" alt="" class="am-img-responsive"/> 
      <p class="am-text-center am-login-pt">易购水泥公众平台</p>
    </div>
    <form action="" class="am-form">
      <div class="am-form-group am-form-login" >
        <label for="doc-vld-name-2"><i class="am-icon-user am-icon-sm am-icon-wz"></i></label>
        <input type="text" class="am-form-field" id="loginname" minlength="3" placeholder="输入用户名（至少 3 个字符）" required/>
      </div>

      <div class="am-form-group am-form-login">
        <label for="doc-vld-pwd-1"><i class="am-icon-key am-icon-sm am-icon-wz"></i></label>
        <input type="password" class="am-form-field" id="password" placeholder="输入密码" required/>
     </div>
      
      
      <button class="am-btn am-btn-secondary am-btn-lg am-round am-size" onclick="severCheck();"  type="button">登录</button>
    </form>
  </div>
<script src="static/shop_pc/js/jquery.min.js"></script>
<script src="static/shop_pc/js/amazeui.min.js"></script>
 <script src="static/shop_pc/js/loading/jquery.loading.js" type="text/javascript"></script>  
<script  src="static/shop_pc/js/jquery.cookie.js"></script> 
</body>
</html>
