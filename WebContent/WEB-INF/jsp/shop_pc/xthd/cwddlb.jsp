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
<link rel="stylesheet" type="text/css" href="static/shop_pc/css/app.css" />
<link rel="stylesheet" type="text/css"
	href="static/shop_pc/css/admin.css" />
<script src="static/shop_pc/js/jquery.min.js"></script>
<script src="static/shop_pc/myjs/common.js"></script>
 <script src="static/weixin/js/sweetalert/dist/sweetalert-dev.js"></script>
  <link rel="stylesheet" href="static/weixin/js/sweetalert/dist/sweetalert.css">
<script src="static/shop_pc/myjs/cwddlb.js"></script>
<script src="static/shop_pc/js/loading/jquery.loading.js" type="text/javascript"></script>  
<script  src="static/shop_pc/js/jquery.cookie.js"></script> 
<link rel="stylesheet" href="static/shop_pc/js/loading/jquery.loading.css" type="text/css">
</head>
<body>
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
					<a href="javascript:void(0);" onclick="logout()" >
						<button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">
							<span class="am-icon-user"></span> 退出
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
							class="am-icon-align-justify"></span>  车辆管理</a></li>
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav'}"><span
							class="am-icon-file"></span> 工厂订单管理 <span
							class="am-icon-plus am-fr am-margin-right"></span> </a>
						<ul class="am-list am-collapse admin-sidebar-sub"
							id="collapse-nav">
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

		<!-- content start -->
		<div class="admin-content">

			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">${factroy.factoryname }</strong> / <small>财务订单管理</small>
						<input type="hidden" id="factoryName" value="<%=request.getAttribute("factoryName") %>">
					</div>
				</div>

				<hr>
				<input type="hidden" id="flag"   value="0"/>
				<section data-am-widget="accordion"
					class="am-accordion am-accordion-gapped" data-am-accordion='{  }'>
					<c:if
						test="${(list_shopclient)== null || fn:length(list_shopclient) == 0}">
						<img alt="" src="static/weixin/img/ts.png">
					</c:if>
					<c:if test="${ fn:length(list_shopclient) > 0}">
						<c:forEach items="${list_shopclient}" var="shopclient">
							<dl class="am-accordion-item am-active">
								<dt
									onclick="findorderlist('${shopclient.c_id}','${shopclient.clientnumber}','${shopclient.clientname}','${pd.factoryid}')"
									class="am-panel-title am-accordion-title"
									data-am-collapse="{parent: '#accordion', target: '#div_${shopclient.clientnumber}'}">
									${shopclient.clientname} <span class="am-fr">查看订单<i
										class="am-icon-sm am-icon-left"></i></span>

								</dt>
								<dd class="am-accordion-bd am-collapse ">
									<div class="am-accordion-content">
										<table
											class="am-table am-table-bordered am-table-striped am-table-radius am-table-hover">
											<thead>
												<tr>
													<th class="am-text-center">订单编号</th>
													<th class="am-text-center">开票时间</th>
													<th class="am-text-center">水泥名称</th>
													<th class="am-text-center">剩余吨数</th>
													<th class="am-text-center">提货</th>
												</tr>
											</thead>
											<tbody id="div_${shopclient.clientnumber}">
												<!-- <tr>
													<td class="am-text-center">987695</td>
													<td class="am-text-center">2016/11/23</td>
													<td class="am-text-center">袋装水泥pc425</td>
													<td class="am-text-center">1300</td>
													<td class="am-text-center"><a href="xthd.html"
														target="_blank">
															<button type="button"
																class="am-btn am-btn-primary am-btn-xs am-radius ">我要提货</button>
													</a></td>
												</tr>
												<tr>
													<td class="am-text-center">987695</td>
													<td class="am-text-center">2016/11/23</td>
													<td class="am-text-center">袋装水泥pc425</td>
													<td class="am-text-center">1300</td>
													<td class="am-text-center"><a href="xthd.html">
															<button type="button"
																class="am-btn am-btn-primary am-btn-xs am-radius ">我要提货</button>
													</a></td>
												</tr> -->
											</tbody>
										</table>
									</div>
								</dd>
							</dl>
						</c:forEach>
					</c:if>


				</section>

				<footer class="admin-content-footer">
					<hr>
					
						<p class="am-padding-left">由 <a href="http://www.hnzxtech.com/" title="河南志信"
                                                target="_blank" class="">河南志信</a>
            提供技术支持</p>
       
				</footer>

			</div>
			<!-- content end -->

		</div>

	</div>
	<script src="static/shop_pc/js/amazeui.min.js"></script>
</body>
</html>