<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate icon" type="image/png"
	href="static/shop_pc/i/favicon.png">
<link rel="stylesheet" href="static/shop_pc/css/amazeui.min.css" />
<script src="static/shop_pc/js/jquery.min.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="static/shop_pc/css/app.css" />
<link rel="stylesheet" href="static/shop_pc/css/admin.css">
<script src="static/weixin/js/sweetalert/dist/sweetalert-dev.js"></script>
<link rel="stylesheet"
	href="static/weixin/js/sweetalert/dist/sweetalert.css">
<link rel="stylesheet"
	href="static/shop_pc/js/loading/jquery.loading.css" type="text/css">
<script src="static/shop_pc/myjs/register.js"></script>

<script src="static/shop_pc/js/jquery.cookie.js"></script>
</head>
<body
	style="background: url(static/shop_pc/images/login-bg.jpg) center repeat-y;">
	<header class="am-topbar am-topbar-inverse admin-header">
		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
			<div class="am-topbar-brand">
				<strong>易购水泥公众平台</strong>
			</div>
			<ul class="am-nav am-nav-pills am-topbar-nav">
				<li><a href="shop/toindex">首页</a></li>
				<li  class="am-active"><a href="shop/totjsja">个人中心</a></li>
			</ul>
			<div class="am-topbar-right">
				<a href="shop/login_toLogin">
					<button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">
						<span class="am-icon-user"></span> 登录
					</button>
				</a>
			</div>
		</div>
	</header>

	<div class="register-box am-btn-kx">
		<form action="" id="doc-vld-msg" class="am-form" data-am-validator>
			<br /><br/>
			<fieldset>
				<legend class="am-legend">
					注册用户
				</legend>

				<div class="am-form-group am-form-register">
					<label for="doc-vld-name-2" class="register-name">账号</label> <input
						type="text" id="doc-vld-name-2" minlength="3"
						placeholder="输入用户名（至少 3 个字符）" required />
				</div>

				<div class="am-form-group am-form-register">
					<label for="doc-vld-email-2-1">邮箱：</label> <input type="email"
						id="doc-vld-email-2-1" data-validation-message="提示信息：输入地球上的电子邮箱撒"
						placeholder="输入邮箱" required />
				</div>

				<div class="am-form-group am-form-register">
					<label for="doc-vld-pwd-1" class="register-pwd">密码</label> <input
						type="password" id="doc-vld-pwd-1" placeholder="请输入密码"
						pattern="^\d{6}$" required />
				</div>

				<div class="am-form-group am-form-register">
					<label for="doc-vld-pwd-2">确认密码</label> <input type="password"
						id="doc-vld-pwd-2" placeholder="请与上面输入的值一致"
						data-equal-to="#doc-vld-pwd-1" required />
				</div>

				<div class="am-form-group am-form-register">
					<label for="doc-vld-phone-1">请输入手机号</label> <input type="number"
						id="doc-vld-phone-1"  placeholder="请输入手机号" pattern="^\d{11}$" required />
				</div>

				<div class="am-g">
					<div class="am-u-md-12">
						<button class="am-btn am-btn-secondary am-btn-lg am-round am-size"
							type="button" onclick="save();">注册</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>

	</div>

	<script src="static/shop_pc/js/loading/jquery.loading.js"
	type="text/javascript"></script>
	<script src="static/shop_pc/js/jquery.min.js" charset="utf-8"></script>
	<script src="static/shop_pc/js/amazeui.min.js" charset="utf-8"></script>
</body>

</html>
