<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="<%=basePath%>">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--<meta name="viewport" content="width=device-width, initial-scale=1">-->
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>公司网站</title>
    <!-- Bootstrap -->
    <script src="static/shop/js/jquery.min.js" type="text/javascript"></script> 
   <link rel="stylesheet" type="text/css" href="static/shop/css/order.css"/>
    <link href="static/shop/css/bootstrap.min.css" rel="stylesheet">
       
  <!--  <script src="static/shop/js/bootstrap.min.js" type="text/javascript"></script> -->
   
   <style type="text/css">
   li { float:left;list-style:none;}
   
    #modal-body {
		height: 400px;
	}
   #modal-body img{
		margin: 30px auto;
		display :block;
		/* max-width:100%; */
	}
	#myModalLabel{
		text-align: center;
	}
   </style>
   
   
 <!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/myjs/shop_head.js"></script>
		 <script type="text/javascript" src="static/js/jquery.tips.js"></script> 
		<!--提示框-->
		<script type="text/javascript" src="static/bootstrap3.0.3/bootstrap.min.js"></script> 
   		 <script type="text/javascript" src="static/js/bootbox.js"></script>
   		
   		 
		<script type="text/javascript" >
		var locat = (window.location+'').split('/'); 
		$(function(){if('tool'== locat[3]){locat =  locat[0]+'//'+locat[2];}else{locat =  locat[0]+'//'+locat[2]+'/'+locat[3];};});

		function cancel(ordernumber,o_id){
			//bootbox.alert("注册失败，请返回微信主页重新进入!");
			 bootbox.confirm("确定要取消订单["+ordernumber+"]吗?", function(result) {
				if(result) {
					top.jzts(); 
					var url = "<%=basePath%>shop/cancle?orderid="+o_id+"&tm="+new Date().getTime()+"&currentPage="+$("#nowpage").val();
				    $.ajax({
						   type: "get", cache: false,  
						    url: url,  
						    data: "",  
						    dataType: "text",  
						    success: function (data) { 
						    	var totalCount = Number($('#totalCount').val()) || 1, showCount = 10,
							    limit = Number($('#limit').val()) || 10,nowpage = Number($('#nowpage').val()) ;
							
						    	createTable(nowpage, limit, totalCount)
						    }  
					});
					
					
				}
			}); 
		}
		
		function checkOrderNO(orderNO){
			var locat="http://101.200.195.224/wxplatform";
			var url = "<%=basePath%>tool/createTwoDimensionCode";
		    $.ajax({
				   type: "post", cache: false,  
				    url: url,  
				    data:{
				    	orderNO:orderNO
				    },
				    dataType: "json",  
				    success: function (data) { 
				    	/* $('#myModal').modal({
					        keyboard: true
					    }) */
					    $('#myModal').modal('show');
				    	 if("success" == data.result){
				    		 document.getElementById('myModalLabel').innerText="提货单订单号："+orderNO;
							 $("#encoderImg").attr({"src":locat+'/uploadFiles/twoDimensionCode/' + data.encoderImgId,"style":"width:320px;"}); 
							 // $("#encoderImg").innerHTML= "<img    src="+locat+"/uploadFiles/twoDimensionCode/"+ data.encoderImgId+"/>";	
							// $("#encoderImg").attr("src",locat+'/uploadFiles/twoDimensionCode/' + data.encoderImgId);       
						 }else{
							 document.getElementById('myModalLabel').innerText="提货单订单号："+orderNO;
							 $("#encoderImg").attr({"src":locat+"/uploadFiles/twoDimensionCode/404.jpg","style":"width:320px;"});
							 return;
						 }
				    }  
			});
		}
		
		function preview() {
			 $("#encoderImg").attr({"style":"width:150px;"}); 
				
			//document.getElementById('encoderImg').style="width:10px;max-width:10px;";
			//$("#encoderImg").attr({"sytle":"width:200px;"}); 
			bdhtml=window.document.body.innerHTML;//获取当前页的html代码
			sprnstr="<!--startprint-->";//设置打印开始区域
			eprnstr="<!--endprint-->";//设置打印结束区域
			prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html

			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
			window.document.body.innerHTML=prnhtml;
			window.print();
			window.location.reload();

			}
		
		 </script>
		 
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
 
  <body>
   <!--导航头部 -->
    <jsp:include page="../admin/head.jsp" flush="true" />
   
  <!-- 导航头部结束 -->
  <form action="shop/listOrders" method="post" name="orderForm" id="orderForm">
  <input type="hidden"  name="nowpage" id="nowpage"  value="${pd.pageorders.currentPage }" >
  <input type="hidden"  name="totalCount" id="totalCount"  value="${pd.pageorders.recordCount}" >
  
  </form>
  
  <!--startprint-->
  <div   class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="wb" name="wb" width="0">
    </OBJECT>
							<div class="modal-dialog" id="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true" >
												&times;
											</button>
											<h4 class="modal-title" id="myModalLabel">
											订单号：服务异常请稍后再试
											</h4>
										</div>
										<div class="modal-body" id="modal-body" >
											<img alt="" id ="encoderImg" src="" style="width:10%;max-width:10%;">
														
										</div>
										<div class="modal-footer">
										<button type="button" class="btn btn-success" id="biuuu_button" data-dismiss="modal"  onclick="preview()"  >打印二维码
											</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn btn-default" data-dismiss="modal" >关闭
											</button>
										</div>
									</div>
								</div>
							</div> 
 <!--endprint-->
<div class="clearfix" style="width:850px; margin: 0 auto; " >			
<table border="1" style="margin: 0 auto;">  
  <caption class="caption  animated zoomIn">提货申请单</caption>  
  <thead>   
    <tr style="background-color:whitesmoke; color: #444; border: 2px solid #E5E5E5;" >
     <th style="width: 120px; text-align: center; height: 40px;" >订单编号</th>   
      <th style="width: 80px; text-align: center; height: 40px;" >司机</th>  
      <th style="width: 100px; text-align: center; height: 40px;" >司机手机号</th>  
     			 
      
      <th style="width: 100px; text-align: center; " >提货日期</th>
      <th style="width: 100px; text-align: center;" >商品名称</th>
      <th style="width: 100px; text-align: center;" >工厂名称</th>
      <th style="width: 50px; text-align: center;" >吨数</th>
      <th style="width: 200px; text-align: center;" >操作</th>  
      
    </tr>  
  </thead>  
  <tbody>  
   <c:choose>
					<c:when test="${not empty pagebeanlist}">
						<c:forEach items="${pagebeanlist}" var="var" varStatus="vs">
							
							<tr style="background-color:white; color: #444; border: 2px solid #E5E5E5;">
							
							 <td style="width: 120px; text-align: center;height: 40px;" >${var.fac_order_no}
							 </td> 
							 
							  
						     
						      <td style="width: 80px; text-align: center;height: 40px; ">${var.shopdriver.name}</td>  
						      <td style="width: 80px; text-align: center;height: 40px; ">${var.shopdriver.phone}</td>  
						      <td style="width: 100px; text-align: center;">${var.thdate}</td>  
						        <td style="width: 100px; text-align: center;">${var.shopgoods.goodstype}${var.shopgoods.goodsname}</td>  
						      <td style="width: 150px; text-align: center;">${var.doc_factory.factoryname}</td> 
						       <td style="width: 50px; text-align: center;">${var.data}</td> 
						     
						      <td style="width: 200px; text-align: left;">
						      	
						      	
						       <c:if test="${var.status  == 0 }">
						       	<button type="button"  onclick="checkOrderNO('${var.ordernumber}');" style="width: 100px;" class="btn btn-success  btn-s-xs">查看扫描码</button>

						     
											 	<button type="button"  onclick="cancel('${var.ordernumber}','${var.o_id }');" style="width: 70px;" class="btn btn-error btn-s-xs">取消</button>
			
											 </c:if>
						      <c:if test="${var.status  == 1 }">
											<span class="btn1">已完成</span> 	
											 </c:if>
											 <c:if test="${var.status  == 2 }">
											 	已取消
											 </c:if>
								
						     				</td>  
						      <!--<td style="width: 150px; text-align: center;">fou</td>
						        <td style="width: 150px; text-align: center;">男</td>  
						      <td style="width: 150px; text-align: center;">3.6</td> -->
						    </tr>  
							
								
						</c:forEach>
						
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
				
  

  </tbody>  
 
</table> 
  
<div id="callBackPager" style="float:right;"></div>
  

</div>
    

<!-- 分页插件 start-->
   		<script src="static/shop/bootStrapPager/js/jquery-1.11.1.min.js"></script>

	    <script src="static/shop/bootStrapPager/js/bootstrap.js"></script>
	
	    <script src="static/shop/bootStrapPager/js/extendPagination.js"></script>
   		
   		<!-- 分页插件end -->
<script type="text/javascript">
/* $(function(){
	    var options = {
        currentPage: 4,
        totalPages: 10,
    	numberOfPages:5
    }

    $('#example').bootstrapPaginator(options);}); */
    
			 $(function(){
					
					
					    var totalCount = Number($('#totalCount').val()) || 1, showCount = 10,
					    	limit = Number($('#limit').val()) || 10,nowpage = Number($('#nowpage').val()) ;
					
					    
					    $('#callBackPager').extendPagination({
					    	currPage:nowpage,
					        totalCount: totalCount,
					
					        showCount: showCount,
					
					        limit: limit,
					
					        callback: function (curr, limit, totalCount) {
								
					        	createTable(curr, limit, totalCount);
					        }
					
					    });
			});
		 

		function createTable(currPage, limit, total) {
			var page =currPage;
		    var html = [], showNum = limit;
		
		    if (total - (currPage * limit) < 0) showNum = total - ((currPage - 1) * limit);
		    
		    top.jzts();	
			if(true && document.forms[0]){
				var url = document.forms[0].getAttribute("action");
				if(url.indexOf('?')>-1){url += "&currentPage=";}
				else{url += "?currentPage=";}
				url = url + page + "&showCount=10";
				document.forms[0].action = url;
				document.forms[0].submit();
			}else{
				var url = document.location+'';
				if(url.indexOf('?')>-1){
					if(url.indexOf('currentPage')>-1){
						var reg = /currentPage=\d*/g;
						url = url.replace(reg,'currentPage=');
					}else{
						url += "&currentPage=";
					}
				}else{url += "?currentPage=";}
				url = url + page + "&showCount=10";
				document.location = url;
			} 
		    
		
		}

</script>
<jsp:include page="../admin/floor.jsp" flush="true" />

   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!--<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="static/shop/js/bootstrap.min.js"></script>
    
		 <script src="static/shop/js/bootstrap.min.js" type="text/javascript"></script>
		 
 <script type="text/javascript">
 
 	//主页
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
  </body>
</html>

