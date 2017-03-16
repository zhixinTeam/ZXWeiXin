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
  <title>易购水泥公众平台</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="static/shop_pc/i/favicon.png">
  <link rel="stylesheet" href="static/shop_pc/css/amazeui.min.css"/>
  <link rel="stylesheet" type="text/css" href="static/shop_pc/css/app.css"/>
  <script src="static/weixin/js/sweetalert/dist/sweetalert-dev.js"></script>
  <link rel="stylesheet" href="static/weixin/js/sweetalert/dist/sweetalert.css">
 
</head>
<body>
	
<header class="am-topbar am-topbar-fixed-top am-topbar-inverse">
  <div class="">
    <h1 class="am-topbar-brand">
     <strong>易购水泥公众平台</strong>
    </h1>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-secondary am-show-sm-only"
            data-am-collapse="{target: '#collapse-head'}"><span class="am-sr-only">导航切换</span> <span
        class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="collapse-head">
      <ul class="am-nav am-nav-pills am-topbar-nav">
        <li class="am-active"><a href="#">首页</a></li>      
        <li><a href="shop/totjsja">个人中心</a></li>
      </ul>

     <!--   <div class="am-topbar-right">
      <div class="am-dropdown" data-am-dropdown="{boundary: '.am-topbar'}">
        <button class="am-btn am-btn-default am-topbar-btn am-btn-sm am-dropdown-toggle" data-am-dropdown-toggle>
        <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
        </button>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </div>
    </div>
 -->

      <div class="am-topbar-right">
      	<a href="shop/toregister">
        <button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm">
        	<span class="am-icon-pencil"></span> 注册</button>
        </a>
      </div>

      <div class="am-topbar-right">
      	<a href="shop/login_toLogin">
        <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">
        	<span class="am-icon-user"></span> 登录</button>
        	</a>
      </div>  
      
    </div>
  </div>
</header>

<div class="get">
  <div class="am-g">
    <div class="am-u-lg-12">
      <h1 class="get-title">易购水泥公众平台为你提供优质的服务</h1>

      <p>
        期待你的参与，期待与你合作，让我们共同与时俱进！
      </p>

      <p>
        <a href="http://www.hnzxtech.com/" class="am-btn am-btn-sm get-btn">欢迎您的合作√</a>
      </p>
    </div>
  </div>
</div>

<div class="detail">
  <div class="am-g am-container">
    <div class="am-u-lg-12">
      <h2 class="detail-h2">易购水泥，期待和你一起去实现!</h2>

      <div class="am-g">
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">

          <h3 class="detail-h3">
            <i class="am-icon-mobile am-icon-sm"></i>
            手机端下提货单
          </h3>

          <p class="detail-p">
            随着科学技术的发展，智能化的手机已经普及，手机的作用越来越突出，河南志信科技有限公司
            为了提高客户的提货效率，节约提货时间，开发了手机网上下提货单，欢迎广大客户参与！
          </p>
        </div>
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
          <h3 class="detail-h3">
            <i class="am-icon-cogs am-icon-sm"></i>
            技术优势，团队创作
          </h3>

          <p class="detail-p">
            河南志信科技有限公司拥有较高水平的软件开发团队，并获得了软件开发著作荣誉证书，我们公司
            对每款软件的开发都进行了现实的需要，大量的测试等一系列的步骤开发完成，以最好的
            功能帮助客户。
          </p>
        </div>
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
          <h3 class="detail-h3">
            <i class="am-icon-check-square-o am-icon-sm"></i>
            公司服务宗旨
          </h3>

          <p class="detail-p">
          司的宗旨：经营理念：聚焦客户、优质服务、质量为本、行业领先<br />
          服务宗旨：质量第一、科技领先、用户至上、信誉第一
          </p>
        </div>
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
          <h3 class="detail-h3">
            <i class="am-icon-send-o am-icon-sm"></i>
           公司经营业务
          </h3>
          <p class="detail-p">
           易购水泥公众平台是一家专业从事软硬件设计、开发、销售、实施、提供解决方案和专业技术服务的高科技公司。
           公司开发的水泥企业一卡通系统、袋装水泥计数系统、等产品，
           已经在多家大、中型水泥生产企业得到广泛应用。
          </p>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="hope">
  <div class="am-g am-container">
    <div class="am-u-lg-4 am-u-md-6 am-u-sm-12 hope-img">
      <img src="static/shop_pc/i/examples/landing.png" alt="" data-am-scrollspy="{animation:'slide-left', repeat: false}">
      <hr class="am-article-divider am-show-sm-only hope-hr">
    </div>
    <div class="am-u-lg-8 am-u-md-6 am-u-sm-12">
      <h2 class="hope-title">网上下提单的优势</h2>

      <p>
        在信息科技迅速发展的年代，网络的应用已经深入人心，为了顺应时代的潮流我们也要与时俱进。
        通过网上下单可以提高客户提货效率，节约提货时间，节约工厂人员等
      </p>
    </div>
  </div>
</div>

<div class="about">
  <div class="am-g am-container">
    <div class="am-u-lg-12">
      <h2 class="about-title about-color">易购水泥公众平台，非常欢迎大家的参与</h2>

      <div class="am-g">
        <div class="am-u-lg-6 am-u-md-4 am-u-sm-12">
          <form action="" class="am-form" id="doc-vld-msg" data-am-validator>
  <fieldset>
    <div class="am-form-group">
      <label for="doc-vld-name-2-1">用户名：</label>
      <input type="text" id="doc-vld-name-2-1" minlength="3" placeholder="输入用户名（至少 3 个字符）" required/>
    </div>

    <div class="am-form-group">
      <label for="doc-vld-email-2-1">邮箱：</label>
      <input type="email" id="doc-vld-email-2-1" data-validation-message="自定义提示信息：输入地球上的电子邮箱撒" placeholder="输入邮箱" required/>
    </div>
    
    <div class="am-form-group">
      <label for="doc-vld-ta-2-1">评论：</label>
      <textarea id="doc-vld-ta-2-1" ></textarea>
    </div>

    <button  type="button" onclick="severCheck();" class="am-btn am-btn-primary am-btn-sm"><i class="am-icon-check"></i> 提 交</button>
  </fieldset>          
    </form>
          <hr class="am-article-divider am-show-sm-only">
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
});
          	
          	
          	function severCheck(){
          	   var loginname = $("#doc-vld-name-2-1").val();
	      		var password = $("#doc-vld-email-2-1").val();
	      		var companyid =$("#doc-vld-ta-2-1").val();
	      		if(loginname==""||password==""||companyid==""){
	      					$("#doc-vld-msg").submit();
	      					return false;
	      		 }	
	      		
	      			swal("提交成功");
	      			return false;
	      		
          	}
          	
          </script>
        </div>

        <div class="am-u-lg-6 am-u-md-8 am-u-sm-12">
          <h4 class="about-color">关于我们</h4>
          <p>
          	2002年志信(原郑州金世纪）创建于郑州，2008年8月正式成立河南志信科技有限公司。
          	是一家专业从事软硬件设计、开发、销售、实施、提供解决方案和专业技术服务的高科技公司。
          	公司开发的水泥企业一卡通系统、袋装水泥计数系统、散装水泥发货系统、可追溯物联网发货系统等产品，已经在多家大、
          	中型水泥生产企业得到应用，在行业内享有很好的声誉。
          </p>
         
          <h4 class="about-color">联系我们</h4>
          <p>
          	      电话：0371-63551234/63702237		
				QQ：3044105458
				邮箱：hnzxtech@163.com
				地址：郑州市高新技术开发区长椿路电子商务产业园9号楼1504室
          </p>
        </div>
      </div>
    </div>
  </div>
</div>



<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="static/shop_pc/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="static/shop_pc/js/amazeui.min.js"></script>
</body>
</html>
