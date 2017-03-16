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
  <META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="static/shop_pc/i/favicon.png">
  <link rel="stylesheet" href="static/shop_pc/css/amazeui.min.css"/>
  <link rel="stylesheet" type="text/css" href="static/shop_pc/css/app.css"/>
  <link rel="stylesheet" type="text/css" href="static/shop_pc/css/admin.css"/>
  <script src="static/shop_pc/js/jquery.min.js"></script>
   <script src="static/shop_pc/myjs/lsdd.js"></script>
   <script src="static/shop_pc/myjs/common.js"></script>
  <link rel="stylesheet" href="static/shop_pc/js/loading/jquery.loading.css" type="text/css">
   <script src="static/weixin/js/sweetalert/dist/sweetalert-dev.js"></script>
  <link rel="stylesheet" href="static/weixin/js/sweetalert/dist/sweetalert.css">
   <script src="static/shop_pc/js/loading/jquery.loading.js" type="text/javascript"></script>  
<script  src="static/shop_pc/js/jquery.cookie.js"></script> 
<script src="static/shop_pc/js/amazeui.datatables.min.js" type="text/javascript" ></script>
		 <!-- <script type="text/javascript"> 
		 
		 </script> -->
</head>
<body>
		
	<header class="am-topbar am-topbar-inverse admin-header">
  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
  	<div class="am-topbar-brand">
    <strong>易购水泥公众平台</strong>
  </div>
    <ul class="am-nav am-nav-pills am-topbar-nav">
        <li class="am-active"><a href="shop/toindex">首页</a></li>
        <li><a href="shop/totjsja">个人中心</a></li>
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
        <li><a href="shop/totjsja"><span class="am-icon-align-justify"></span>  车辆管理</a></li>
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 工厂订单管理 
          	<span class="am-icon-plus am-fr am-margin-right"></span>
          </a>
          <ul class="am-list am-collapse admin-sidebar-sub" id="collapse-nav">
          </ul>
        </li>
        <li><a href="shop/tolsdd"><span class="am-icon-table"></span> 查看提货单</a></li>
        <li><a href="shop/toxgmm"><span class="am-icon-key"></span> 修改密码</a></li>
        <li><a href="shop/toadd"><span class="am-icon-file"></span> 新增订单</a></li>
        
      </ul>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-bookmark"></span> 公告</p>
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
          <strong class="am-text-primary am-text-lg">查看提货单</strong> 
        </div>
      </div>
       <hr/>
      
        <table class="am-table am-table-striped am-table-hover am-table-bordered am-text-nowrap
	am-table-compact" id="example">
				    <thead>
				        <tr>
				           <th class="am-text-center">订单编号</th>
				           <th class="am-text-center">工厂名称</th>
				           <th class="am-text-center">产品名称</th>
				           <th class="am-text-center">司机名称</th>
				           <th class="am-text-center">提货日期</th>
				           <th class="am-text-center">吨数</th>
				           <th class="am-text-center">车牌号</th>
				            <th class="am-text-center">查看二维码</th>
				           <th class="am-text-center">操作</th>
				        </tr>
				    </thead>
			    <tbody id="orderlist">
			     
			        
			    </tbody>  
     </table>    
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
<div data-am-widget="gotop" class="am-gotop am-gotop-fixed">
  <a href="#top" title="">
    <img class="am-gotop-icon-custom" src="static/shop_pc/images/s.png"
    />
  </a>
</div>
<script src="static/shop_pc/js/amazeui.min.js"></script>
</body>
</html>
