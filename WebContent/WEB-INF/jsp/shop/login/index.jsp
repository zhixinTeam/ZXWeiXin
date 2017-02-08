<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 <html xmlns=http://www.w3.org/1999/xhtml>
  <head>
  <base href="<%=basePath%>">  
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
   <meta name="keywords" content=" 河南志信商城  志信科技公司 ，科技公司" />
    <meta name="description" content=" 一卡通服务  水泥 " />
    <title>公司网站</title>  
    <link href="static/shop/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="static/shop/css/style.css" />
   <script src="static/shop/js/jquery.min.js" type="text/javascript"></script>
   <script src="static/shop/js/bootstrap.min.js" type="text/javascript" ></script>
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head> 
  </script>
  <body>
  <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">     
      <a class="navbar-brand" href="#"><img id="logo" src="static/shop/img/logo.png" alt="" /></a>
    </div>
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right  ">
        <li><a href="javascript:void(0);" onclick="toshop()" id="sy"  >首页 <span class="sr-only">(current)</span></a></li>
        <li><a href="javascript:void(0);" onclick="process()" >公司简介</a></li>
        <li><a href="javascript:void(0);" onclick="toorder()" id="ws" >申请提货单</a></li>
        <li><a href="javascript:void(0);" onclick="toorderlist()" id="wd" >我的提货订单</a></li>
        <c:if test="${not empty shopuser }">
         <li><a href="javascript:void(0);" onclick="logout()"  id="dl">退出</a></li>
          </c:if>
          <c:if test="${empty shopuser}">
                  <li><a href="javascript:void(0);" onclick="tologin()"  id="dl">登录</a></li>
          	</c:if>
      </ul>
     
    </div>
  </div>
  
</nav>
<div id="myCarousel" class="carousel slide" data-ride="carousel">

   <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
   </ol>   
  
   <div class="carousel-inner " >
      <div class="item active">
         <img src="static/shop/img/ban11.jpg" alt="First slide" >
         <div class="carousel-caption"></div>
      </div>
      <div class="item">
         <img src="static/shop/img/banbg002.jpg" alt="Second slide" >
         <div class="carousel-caption"></div>
      </div>
      <div class="item">
         <img src="static/shop/img/banbg05.jpg" alt="Third slide" >
         <div class="carousel-caption"></div>
      </div>
    
   </div> 
  
   
   <a class="carousel-control left" href="#myCarousel" 
      data-slide="prev">&lsaquo;</a>
   <a class="carousel-control right" href="#myCarousel" 
      data-slide="next">&rsaquo;</a>
</div> 

 
 
 
<div class="jumbotron1">
  
 <ul class="container clearfix ">
			<li class="wow bounceInDown">
				<a href="#">
					<i class="glyphicon glyphicon-home"></i>
					<h2>关于我们</h2>
					<p>
						2008年8月正式成立河南志信科技有限公司
						是一家专业从事软硬件设计、开发、销售实施
						提供解决方案和专业技术服务的高科技公司。
					</p>
				</a>				
			</li>
			<li class="wow bounceInDown" >
				<a href="#">
				<i class="glyphicon glyphicon-glass"></i>
				<h2>公司荣誉</h2>
				<p>
					河南志信科技有限公司拥有计算机软件著作权登记证书，
					质量管理体系证书，被国家认定为AAA级信用企业。
				</p>	
				</a>	
			</li>
			<li class="wow bounceInDown" >
				<a href="#">
				<i class=" glyphicon glyphicon-bookmark"></i>
				<h2>设计团队</h2>
				<p>
					拥有专业的技术团队
					注重系统的安全性和使用性服务宗旨：
					质量第一、科技领先、用户至上、信誉第一
					
				</p>
				</a>	
			</li>
			<li>
				<a href="#">
				<i class="glyphicon glyphicon-th-large"></i>
				<h2>案例</h2>
				<p>
					江西永丰南方水泥有限公司/
					江西安福南方水泥有限公司/
					
					中国长城铝业公司水泥厂/
					天瑞集团郑州水泥有限公司/
				</p>
				</a>	
			</li>
	</ul> 
</div>
<div class="clearfix"></div>

<div id="jycp" >
<section>
	<h3>产品中心</h3>
	<p>经营理念：
聚焦客户、优质服务、质量为本、行业领先
<br />
服务宗旨：
质量第一、科技领先、用户至上、信誉第一</p>
</section>
	
<div class="row ">
  <div class="col-sm-6 col-md-3">
    <div class="thumbnail">
     <a href=""><img src="static/shop/img/31.jpg" alt="..."></a> 
      <div class="caption">
        <h3 class="className">厂家名称：华新水泥</h3>
        <p>42.5普通硅酸盐水泥袋装型50kg</p>
       <p class="pic"><strong><i>微商城价：推荐型</i></strong></p>
      </div>
    </div>
  </div>
  <div class="col-sm-6 col-md-3">
    <div class="thumbnail">
      <a href=""><img src="static/shop/img/29.jpg" alt="..."></a>
      <div class="caption">
        <h3 class="className">厂家名称：葛洲坝水泥</h3>

         <p>42.5普通硅酸盐水泥袋装型50kg</p>
       <p class="pic"><strong><i>微商城：常用型</i></strong></p>
     
      </div>
    </div>
  </div>
  <div class="col-sm-6 col-md-3">
    <div class="thumbnail">
      <a href=""><img src="static/shop/img/30.jpg" alt="..."></a>
      <div class="caption">
        <h3 class="className">厂家名称：松滋水泥</h3>
      
         <p>42.5普通硅酸盐水泥袋装型50kg</p>
         <p class="pic"><strong><i>微商城：热销型</i></strong></p>
       
      </div>
    </div>
  </div>
  <div class="col-sm-6 col-md-3">
    <div class="thumbnail">
      <a href=""><img src="static/shop/img/32.jpg" alt="..."></a>
      <div class="caption">
        <h3 class="className">厂家名称：三峡牌水泥</h3>
       
        <p>42.5普通硅酸盐水泥袋装型50kg</p>
       <p class=" pic"><strong><i>微商城：精品型</i></strong></p>
       
      </div>
    </div>
  </div>
</div>
	
</div>
  
 
<div class="clearfix"></div>
<div id="jycp" style="height: 480px;">
<section>
	<h3>案例展示</h3>
	<p>经营理念：
聚焦客户、优质服务、质量为本、行业领先
<br />
服务宗旨：
质量第一、科技领先、用户至上、信誉第一</p>
</section>
	

<div class="frame">
<div class="wrap">
	<img src='static/shop/img/a1.jpg' alt='' />
	<div class="wrap_cent">
		<span></span>
		<section>
			<h1>江西安福南方水泥有限公司</h1>
		<a href="">2011年3月安装本公司系统不仅提高了工作效率
			还带来了很大的收益，
		</a>
		</section>			
	</div>
</div> 
</div>

<div class="frame">
<div class="wrap">
	<img src='static/shop/img/a2.jpg' alt='' />
	<div class="wrap_cent">
		<span></span>
		<section>
			<h1>江西安福南方水泥有限公司</h1>
		<a href="">2011年6月安装本公司系统不仅提高了工作效率
			还带来了很大的收益，</a>
		</section>			
	</div>
</div> 
</div>

<div class="frame">
<div class="wrap">
	<img src='static/shop/img/a3.jpg' alt='' />
	<div class="wrap_cent">
		<span></span>
		<section>
			<h1>江山南方水泥有限公司</h1>
		<a href="">2011年9月安装本公司系统不仅提高了工作效率
			还带来了很大的收益，</a>
		</section>			
	</div>
</div>
</div>

<div class="frame">
<div class="wrap">
	<img src='static/shop/img/a4.jpg' alt='' />
	<div class="wrap_cent">
		<span></span>
		<section>
			<h1>新安中联万基水泥有限公司</h1>
		<a href="">2012年3月安装本公司系统不仅提高了工作效率
			还带来了很大的收益，</a>
		</section>			
	</div>
</div>
</div>
<div class="frame">
<div class="wrap">
	<img src='static/shop/img/a5.jpg' alt='' />
	<div class="wrap_cent">
		<span></span>
		<section>
			<h1>中国长城铝业公司水泥厂</h1>
		<a href="">2012年10月安装本公司系统不仅提高了工作效率
			还带来了很大的收益，</a>
		</section>			
	</div>
</div>
</div>
<div class="frame">
<div class="wrap">
	<img src='static/shop/img/a6.jpg' alt='' />
	<div class="wrap_cent">
		<span></span>
		<section>
			<h1>天瑞集团郑州水泥有限公司</h1>
		<a href="">2012年12月安装本公司系统不仅提高了工作效率
			还带来了很大的收益，</a>
		</section>			
	</div>
</div>
</div>
<div class="frame">
<div class="wrap">
	<img src='static/shop/img/a9.jpg' alt='' />
	<div class="wrap_cent">
		<span></span>
		<section>
			<h1>天瑞集团汝州水泥有限公司</h1>
		<a href="">2013年3月安装本公司系统不仅提高了工作效率
			还带来了很大的收益，</a>
		</section>			
	</div>
</div>
</div>
<div class="frame">
<div class="wrap">
	<img src='static/shop/img/a8.jpg' alt='' />
	<div class="wrap_cent">
		<span></span>
		<section>
			<h1>郑煤集团龙力水泥有限责任公司</h1>
		<a href="">2013年3月安装本公司系统不仅提高了工作效率
			还带来了很大的收益，</a>
		</section>			
	</div>
</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){

$(".wrap div").hover(function() {
	$(this).animate({"top": "-354px"}, 400, "swing");
},function() {
	$(this).stop(true,false).animate({"top": "0px"}, 400, "swing");
});

});
</script>	

<script type="text/javascript">
	
		
		
 
 	
    function toshop(){
	 window.location.href="<%=basePath%>shop";
 	}
 	//下单页面
 	function toorder(){
 		window.location.href="<%=basePath%>shop/order";
 	}
 	//历史订单
    function toorderlist(){
    	window.location.href="<%=basePath%>shop/listOrders";
    }
 	//去登录页面
 	function tologin(){
	 	window.location.href="<%=basePath%>shop/login_toLogin";
 	}
 	//退出
 	function logout(){
 		window.location.href="<%=basePath%>shop/logout";
 	}
 	//运营流程
 	function process(){
 		window.location.href="<%=basePath%>shop/process";
 	}
 	
	</script>
</div>

  
<div class="clearfix"></div>

<div id="footer">
	<p>电话：0371-63551234/63702237</p>
	<p>传真：0371-63702239   邮箱：hnzxtech@163.com</p>
	<p>地址：郑州市高新技术开发区长椿路电子商务产业园9号楼1504室
		<br />
		版权所有：河南志信科技有限公司 豫ICP备13019378号
	</p>
</div>
 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/bootstrap.min.js" type="text/javascript" ></script>
     <!-- Include all compiled plugins (below), or include individual files as needed -->
  </body>
</html>