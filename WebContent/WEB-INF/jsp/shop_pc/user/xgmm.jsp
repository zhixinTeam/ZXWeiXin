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
  <!--<meta name="viewport"
        content="width=device-width, initial-scale=1">-->
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="static/shop_pc/i/favicon.png">
  <link rel="stylesheet" href="static/shop_pc/css/amazeui.min.css"/>
  <link rel="stylesheet" type="text/css" href="static/shop_pc/css/app.css"/>
  <link rel="stylesheet" type="text/css" href="static/shop_pc/css/admin.css"/>
  <script src="static/shop_pc/js/jquery.min.js"></script>
  <script src="static/shop_pc/myjs/common.js"></script>
  <script src="static/shop_pc/myjs/xgmm.js"></script>
  <script src="static/shop_pc/js/amazeui.min.js"></script>
  <script src="static/weixin/js/sweetalert/dist/sweetalert-dev.js"></script>
  <link rel="stylesheet" href="static/weixin/js/sweetalert/dist/sweetalert.css">
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
          <strong class="am-text-primary am-text-lg">修改密码</strong>
        </div>
      </div>

      <hr>

      <form action="" class="am-form" id="doc-vld-msg" data-am-validator>
  <fieldset>    
    <!-- <div class="am-form-group" data-am-validator>
      <label for="doc-vld-528">用户名：</label>
      <input type="number" id="loginname" class="js-pattern-mobile am-radius"
             placeholder="输入手机号" required/>         
    </div> -->
  <div class="am-form-group">
      <label for="doc-vld-pwd-1">请输入新密码：</label>
      <input type="password" class="am-radius" id="doc-vld-pwd-1" placeholder="请输入密码" pattern="^\d{6}$" required/>
    </div>

    <div class="am-form-group">
      <label for="doc-vld-pwd-2">请确认密码：</label>
      <input type="password" class="am-radius" id="doc-vld-pwd-2" placeholder="确认密码" data-equal-to="#doc-vld-pwd-1" required/>
    </div>  
    
    <button class="am-btn am-btn-secondary am-radius am-btn-block am-btn-kx" type="button" onclick="save();">保存</button>
    
</form>
<script type="text/javascript">
	$(function() {
  $('#doc-vld-msg').validator({
    onValid: function(validity) {
      $(validity.field).closest('.am-form-group').find('.am-alert').hide();
    },

    onInValid: function(validity) {
      var $field = $(validity.field);
      var $group = $field.closest('.am-form-group');
      var $alert = $group.find('.am-alert');
      var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

      if (!$alert.length) {
        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
          appendTo($group);
      }

      $alert.html(msg).show();
    }
  });
 
if ($.AMUI && $.AMUI.validator) {
      $.AMUI.validator.patterns.mobile = /^\s*1\d{10}\s*$/;
}
});					
</script>

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


 <!--6-->
  <div data-am-widget="gotop" class="am-gotop am-gotop-fixed">
  <a href="#top" title="">
    <img class="am-gotop-icon-custom" src="static/shop_pc/images/s.png"
    />
  </a>
</div>
<script src="static/shop_pc/js/amazeui.min.js"></script> 
</body>
</html>