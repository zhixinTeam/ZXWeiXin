<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet" type="text/css" href="static/shop_pc/css/app.css" />
<link rel="stylesheet" type="text/css"
	href="static/shop_pc/css/admin.css" />
<script src="static/shop_pc/js/jquery.min.js"></script>
<script src="static/shop_pc/myjs/tjsja.js"></script>
<script src="static/shop_pc/myjs/common.js"></script>
<script src="static/shop_pc/js/amazeui.min.js"></script>
<link rel="stylesheet"
	href="static/shop_pc/js/loading/jquery.loading.css" type="text/css">
<script src="static/weixin/js/sweetalert/dist/sweetalert-dev.js"></script>
<link rel="stylesheet"
	href="static/weixin/js/sweetalert/dist/sweetalert.css">
<script src="static/shop_pc/js/loading/jquery.loading.js"
	type="text/javascript"></script>
<script src="static/shop_pc/js/amazeui.datatables.min.js"
	type="text/javascript"></script>
<script src="static/shop_pc/js/jquery.cookie.js"></script>
</head>
<body>
	<header class="am-topbar am-topbar-inverse admin-header">
		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
			<div class="am-topbar-brand">
				<strong>易购水泥公众平台</strong>
			</div>
			<ul class="am-nav am-nav-pills am-topbar-nav">
				<li><a href="shop/toindex">首页</a></li>
				<li class="am-active"><a href="shop/totjsja">个人中心</a></li>
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

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
			<div class="am-offcanvas-bar admin-offcanvas-bar">
				<ul class="am-list admin-sidebar-list">
					<li><a href="shop/totjsja"><span
							class="am-icon-align-justify"></span> 车辆管理</a></li>
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav'}"><span
							class="am-icon-file"></span> 工厂订单管理 <span
							class="am-icon-plus am-fr am-margin-right"></span> </a>
						<ul class="am-list am-collapse admin-sidebar-sub"
							id="collapse-nav">

							<%-- 	<li><a href="cwddlb.html" class="am-cf"><span
									class="am-icon-check"></span>${factory.factoryname}</a></li> --%>


						</ul></li>
					<li><a href="shop/tolsdd"><span class="am-icon-table"></span>
							查看提货单</a></li>
					<li><a href="shop/toxgmm"><span class="am-icon-key"></span>
							修改密码</a></li>
					<li><a href="shop/toadd"><span class="am-icon-file"></span>
							新增订单</a></li>

				</ul>

				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p>
							<span class="am-icon-bookmark"></span> 公告
						</p>
						<p>时光静好，与君语；细水流年，与君同。—— 易购水泥</p>
					</div>
				</div>

			</div>
		</div>
		<!-- sidebar end -->
<div>
				<iframe name="mainFrame" id="mainFrame" frameborder="0" src="tab.do" style="margin:0 auto;width:100%;height:100%;"></iframe>
			</div>
		<!-- content start -->
		<div class="admin-content">

			<div class="admin-content-body">

				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">新增车辆</strong> / <small>tjthr</small>
					</div>
				</div>
				<hr>
				<form action="" class="am-form" id="doc-vld-msg" data-am-validator>
					<fieldset>
						
						<div class="am-form-group am-u-lg-4" data-am-validator>
							<label>车牌号：</label> <input type="text" id="tracknumber"
								class="js-pattern-tracknumber am-radius" placeholder="车牌号"
								required />
						</div>
						
						<div class="am-form-group am-u-lg-4">
							<label for="">姓名：</label> <input class="am-radius" type="text"
								id="drivername" placeholder="姓名" required />
						</div>

						<div class="am-form-group am-u-lg-4" data-am-validator>
							<label for="doc-vld-528">电话：</label> <input type="text"
								id="driverphone" class="js-pattern-mobile am-radius"
								placeholder="电话" required />
						</div>

						<div class="am-form-group am-u-lg-12">
							<button class="am-btn am-btn-sm am-btn-secondary am-radius "
								type="button" onclick="save();">保存</button>
							<button class="am-btn am-btn-sm am-btn-warning    am-radius "
								type="reset">重置</button>

						</div>
					</fieldset>
				</form>
				<script type="text/javascript">
					
				</script>


				<div class="am-cf am-padding am-padding-bottom-0 am-wstop">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">提货人管理</strong> / <small>thrgl</small>
					</div>
				</div>
				<hr>

				<table
					class="am-table am-table-bordered am-table-compact am-table-striped am-table-radius am-table-hover" id="example">
					<thead>
						<tr>
							<th class="am-text-center">姓名</th>
							<th class="am-text-center">电话</th>
							<th class="am-text-center">车牌号</th>
							<th class="am-text-center">操作</th>
						</tr>
					</thead>
					<tbody id="table_driver">

						
					</tbody>
				</table>

				<footer class="admin-content-footer">
					<hr>
					<p class="am-padding-left">© 2017 YiGoushuini, Inc. Licensed
						under MIT license.</p>
				</footer>

			</div>
			<!-- content end -->
		</div>
	</div>
	<script src="static/shop_pc/js/amazeui.min.js"></script>
</body>
</html>