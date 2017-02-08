<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
 <html>
  <head>
  <base href="<%=basePath%>">  
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content=" 河南志信商城  志信科技公司 ，科技公司" />
    <meta name="description" content=" 一卡通服务  水泥 " />
        <title>公司网站</title>
        
   
   <script src="static/shop/js/jquery.min.js" type="text/javascript"></script>
   <link rel="stylesheet" type="text/css" href="static/shop/css/gsyy.css"/>
    <link href="static/shop/css/bootstrap.min.css" rel="stylesheet">  
    <script src="static/shop/js/bootstrap.min.js" type="text/javascript" ></script> 
     <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
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
<div id="gsjj">
	<img src="static/shop/img/gsjj.jpg"/>	
</div>

<div id="gsjj_cen" >
	<div class="gsjj_a">
		<figure>
			<div class="png">
			
				<img src="static/shop/img/e1.jpg" alt="" />
				<img class="png1" src="static/shop/img/s1.png"/>
			
			<script type="text/javascript">
				$(function(){
				 $(".png").hover(function(){
					$(".png1").animate({top:'0px'},200,"swing");
				   },function(){
				   	$(".png1").stop().animate({top:'-452px'},200,"swing" );
				   });
				   				   
				});
			</script>
			</div>			
			<figcaption>
				<article>
					<h2>公司简介</h2>				
					<p>2002年志信(原郑州金世纪）创建于郑州，2008年8月正式成立河南志信科技有限公司。
					是一家专业从事软硬件设计、开发、销售、实施、提供解决方案和专业技术服务的高科技公司。
					公司开发的水泥企业一卡通系统已经在多家大、中型水泥生产企业得到应用，在行业内享有很好的声誉。					
					</p>					
					<h3 >经营理念：<br />
					<span>聚焦客户、优质服务、质量为本、行业领先!</span></h3>
					<h3 >服务宗旨：<br />
					<span>质量第一、科技领先、用户至上、信誉第一!</span></h3>							
				</article>													
			</figcaption>
		</figure>		
	</div>
	
	
<div id="gsjj_cen" class="cen" >		
			<h4>合作伙伴</h4>
			<div class="hzhb_a">
			<ul>
				<li>
					
					<p>华新水泥厂</p>
					<img src="static/shop/img/72722.jpg" alt="" />
					<img class="png2" src="static/shop/img/72722.png" alt="" />
					
				</li>
				
				<li>
					
					<p>葛洲坝水泥</p>
					<img src="static/shop/img/71733.jpg" alt="" />
					<img class="png2" src="static/shop/img/72722.png" alt="" />
					
				</li>
				
				<li>
					
					<p>华新水泥厂</p>
					<img src="static/shop/img/71734.jpg" alt="" />
					<img class="png2" src="static/shop/img/72722.png" alt="" />
					
				</li>
				
				<li>
					
					<p>华新水泥厂</p>
					<img src="static/shop/img/71735.jpg" alt="" />
					<img class="png2" src="static/shop/img/72722.png" alt="" />
					
				</li>
			</ul>									
		</div>
	<script type="text/javascript">
		$(function(){
			$(".hzhb_a ul li").hover(function(){
			$(this).find(".png2").animate({top:'0px'},200,"swing");
			},function(){
				$(this).find(".png2").stop().animate({top:'-405px'},200,"swing");
			});
		});
	</script>		
</div>


<div id="gsjj_cen" class="cen" >
	<div class="information">
		<h5>最新动态</h5>
		<hgroup id="scrollDiv">
			<ul>
				<li>
				葛洲坝当阳水泥工厂物流一卡通系统成功上线/2016-08-22	
				</li>
				<li>
				贺河南志信荣获《软件产品登记证书》/2016-08-20
				</li>
				<li>
				贺葛洲坝松滋水泥有限公司物流一卡通系统正式上线/2016-08-2
				</li>
				<li>
				柬埔寨卓雷丁水泥工厂物流发货标准化系统正式上线/2016-07-20
				</li>
				<li>
				葛洲坝荆门水泥工厂物流一卡通系统于日前正式上线/2016-05-20
				</li>
				<li>
				江西上高南方水泥财务业务一体化工厂物流扩展系统正式上线/2016-05-2
				</li>
				<li>
				营口金地球水泥有限公司物流发货一卡通系统正式上线/2016-02-2
				</li>
				<li>
				江西安福南方水泥工厂物流一卡通发货系统正式上线/2016-02-2
				</li>
			<!-- 	<script src="static/shop/js/jquery.js" type="text/javascript" charset="utf-8"></script>  -->
		<script type="text/javascript">
			function AutoScroll(obj){
             $(obj).find("ul:first").animate({
                marginTop: "-25px"
		            }, 500, function() {
                $(this).css({ marginTop: "0px" }).find("li:first").appendTo(this);
            });
        }
           $(function() {
            var myar = setInterval('AutoScroll("#scrollDiv")', 1500)
              $("#scrollDiv").hover(function() { 
            	   clearInterval(myar); 
                  },function() { 
                  	myar = setInterval('AutoScroll("#scrollDiv")', 1500)
                 });
        });
				</script>
			</ul>
		</hgroup>
	<div class="xwtp">
		
	<div id="myCarousel" class="carousel slide" data-ride="carousel">  
   <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
   </ol>    
   <div class="carousel-inner " >
      <div class="item active">
         <img src="static/shop/img/20150915163709136.jpg" alt="First slide" >
         <div class="carousel-caption"></div>
      </div>
      <div class="item">
         <img src="static/shop/img/20150915163720140.jpg" alt="Second slide" >
         <div class="carousel-caption"></div>
      </div>
      <div class="item">
         <img src="static/shop/img/201509151637336505.jpg" alt="Third slide" >
         <div class="carousel-caption"></div>
      </div>
      <div class="item">
         <img src="static/shop/img/20150915163720140.jpg" alt="Third slide" >
         <div class="carousel-caption"></div>
      </div>
   </div> 
   <a class="carousel-control left" href="#myCarousel" 
      data-slide="prev">&lsaquo;</a>
   <a class="carousel-control right" href="#myCarousel" 
      data-slide="next">&rsaquo;</a>
</div> 						
</div>

	</div>
	<!--*-->
	
	<div class="cpzx">
		<h5>产品中心</h5>
		<div class="cpzx_b">			
	<ul id="myTab" class="nav nav-tabs"> 
    <li class="active"> 
        <a href="#home" data-toggle="tab"> 
                  无人值守磅房系统 
        </a> 
    </li> 
    <li>
    	<a href="#ios" data-toggle="tab">一卡通发货系统</a>
    </li> 
    <li>
    	<a href="#iob" data-toggle="tab">袋装水泥计数系统</a>
    </li>
    <li class="dropdown"> 
        <a href="#" id="myTabDrop1" class="dropdown-toggle" 
         data-toggle="dropdown">更多
            <b class="caret"></b> 
        </a> 
        <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1"> 
            <li><a href="#jmeter" tabindex="-1" data-toggle="tab">可追溯物联网发货系统</a></li> 
            <li><a href="#ejb" tabindex="-1" data-toggle="tab">商砼企业管理信息系统</a></li> 
            <li><a href="#ejb" tabindex="-1" data-toggle="tab">散装水泥发货系统 </a></li>
        </ul> 
    </li> 
</ul> 
<div id="myTabContent" class="tab-content"> 
    <div class="tab-pane fade in active" id="home">
    	<h6>无人值守磅房同步视频管控系统概述</h6>
        <p>本系统可以自动采集毛重、皮重信息、车辆称重图片、可自动统计净重、自动进行打印、
        	可以通过局域网、因特网连接实现数据和图片的实时监控，磅单的查询可以关联图像信息。
        	公司领导可以在任何一台机器查询到称重数据，加强公司企业内部管理水平。</p>
        	<div class="home_a">
        		<img src="static/shop/img/w1.jpg"/>
        		<img src="static/shop/img/w1.jpg"/>       		
        	</div>
    </div> 
    <div class="tab-pane fade" id="ios"> 
    	<h6>水泥企业一卡通发货系统概述</h6>
        <p>水泥企业一卡通发货系统，就是在同一张卡上实现合同管理、财务结算、发货开票、门卫放行、
        	磅房称重管理、刷卡存款、刷卡取货等一系列的智能管理。其核心内容是利用卡片这种特定的物理媒介，
        	实现从业务数据的生成、采集、传输到汇总分析的信息资源管理的规范化和自动化。</p> 
        	<div class="home_a">
        		<img src="static/shop/img/w2.jpg"/>
        		<img src="static/shop/img/w2.jpg"/>       		
        	</div>
    </div> 
    <div class="tab-pane fade" id="iob"> 
    	<h6>袋装水泥计数系统概述</h6>
        <p>可自动吸收水泥袋上和皮带上的干扰脉冲达到准确计数。设定单包的正常参数，
        	连包时可根据单包的正常参数智能识别出连包数。
可设定并显示单包数，达到设定值时，计数器输出闪烁报警信号“AL”并输出一组开关控制信号（5A）。
设定值到达时计数器自动清零或手动清零：可按需要输入密码设定。
</p> 
        	<div class="home_a">
        		<img src="static/shop/img/w3.jpg"/>
        		<img src="static/shop/img/w3.jpg"/>       		
        	</div>
    </div> 
    <div class="tab-pane fade" id="jmeter"> 
        <p>更新中</p> 
    </div> 
    <div class="tab-pane fade" id="ejb"> 
        <p>更新中</p> 
    </div> 
    <div class="tab-pane fade" id="ejb"> 
         <p>更新中</p>  
    </div> 
</div>	
						
</div>
		
</div>
	
</div>


<div class="clearfix"></div>
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
<div id="footer">
	<p>电话：0371-63551234/63702237</p>
	<p>传真：0371-63702239   邮箱：hnzxtech@163.com</p>
	<p>地址：郑州市高新技术开发区长椿路电子商务产业园9号楼1504室
		<br />
		版权所有：河南志信科技有限公司 豫ICP备13019378号
	</p>
</div>
      <!--   <script src="static/1.9.1/jquery.min.js" type="text/javascript"></script>  -->
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="static/shop/js/bootstrap.min.js" type="text/javascript" ></script>
     <!-- Include all compiled plugins (below), or include individual files as needed -->
  </body>
</html>


