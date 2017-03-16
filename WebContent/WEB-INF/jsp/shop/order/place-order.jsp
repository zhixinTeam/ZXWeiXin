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
    <!-- sweetalert start-->
 <script src="static/shop/sweetalert/dist/sweetalert-dev.js"></script>
  <link rel="stylesheet" href="static/shop/sweetalert/dist/sweetalert.css">
 <!-- sweetalert end -->
    <link rel="stylesheet" href="static/loading/jquery.loading.css" type="text/css">
   <script src="<%=basePath%>static/shop/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script> 
    <link rel="stylesheet" href="static/shop/css/place_order.css" />
       <script src="static/shop/js/jquery.min.js" type="text/javascript"></script>  
      <!-- 校验js -->
 		 <script src="static/shop/js/validata/jquery-1.11.1.js" type="text/javascript"></script>
 		  <script src="static/shop/js/validata/jquery.validate.js" type="text/javascript"></script>
 		  
       <script src="static/loading/jquery.loading.js" type="text/javascript"></script> 
   <!-- 确认窗口 -->
      <script type="text/javascript" src="static/shop/js/place-order.js"></script>  
      <script type="text/javascript" src="static/js/bootbox.js"></script>  
   		 <script type="text/javascript" src="static/bootstrap3.0.3/bootstrap.min.js"></script>
  </head>
  <body>
  <style type="text/css">
	.form-control-xxx {
    width:200px;
    display:inline;
}
.form-control1 {
    width:100%;
    display: block;
}
</style>
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

<div id="bgcolor" >
	
	<div id="place_order" >
	<h1 class="animated slideInLeft">提货申请单</h1>
 <form id="order_form" name="order_form" class="form-horizontal" method="post"
		action="shop/save_order">
		<input type="hidden" name="wx_token" id="wx_token" value="${pd.wx_token}" />
			<input type="hidden" name="goodsname" id="goodsname"  />
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-2 control-label">水泥工厂：</label>
				<div class="col-sm-3" style="height:65px;width:28%" >
				<!-- onchange="facchange(this.options[this.options.selectedIndex].value)"-->
					<select  class="form-control"  name="factoryid" id="factoryid"  onchange="facchange(this.options[this.options.selectedIndex].value)"  autofocus="on"  >
							<option  value="">--请选择工厂--</option>
							 <c:forEach items="${faset}" var="factory">
										<option value="${factory.id }" >${factory.factoryname }</option>
									</c:forEach>
						</select>
				</div>
				<label class="col-sm-2 control-label">订单编号：</label>
				<div class="input-group col-xs-3" >  
			        
			        <input type="text" name="fac_order_no"   id="fac_order_no"  class="form-control" placeholder="请输入订单号">  
			        <span class="input-group-btn">  
			            <button class="btn btn-success" onclick="search_order()" type="button">查询订单</button>  
			        </span>  
			    </div>
				
				
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			
			<div class="form-group">
			
				<label class="col-sm-2 control-label">提货吨数：</label>
				<div class="col-sm-3" style="height:64px;width:28%">
				<input type="number" class="form-control" name="goodsnumber" id="goodsnumber" placeholder="请输入数量" autofocus="on" >
						
					
				</div>
				
				<label class="col-sm-2 control-label">司机名称：</label>
				
				<div class="input-group col-xs-3" >  
			        <div class="input-group-btn">  
			            <select  class="form-control" id="driverid" name="driverid"  onChange="changeF(this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex]);"  style="width: auto;">  
			                 <option value="" >选择</option>
								 <c:forEach items="${driverlist}" var="driver">
										<option value="${driver.d_id }" >${driver.name }</option>
									</c:forEach>
			            </select>  
			        </div>  
			        <input type="text" name="drivername" id="drivername" class="form-control" placeholder="请输入司机名称">  
			        
			    </div>  
				
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-2 control-label">提货日期：</label>
				<div class="col-sm-3" style="height:63px;width:28%">
					<input type="text" class="form-control" readonly="readonly" id="thsj" name="thsj" placeholder="请选择提货日期"  class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"   >
					</div>
				<label class="col-sm-2 control-label">司机手机：</label>
				<div class="col-sm-3" style="width:28.5% ;position:relative; left:-17px; top:0px; ">
				<input type="number" class="form-control" name="driverphone" placeholder="请输入司机手机号"  id="driverphone"   autofocus="on" >
						</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
			<label class="col-sm-2 control-label">客户名称：</label>
				<div class="col-sm-3" style="height:62px;width:28%">
					<select class="form-control" name="snkh" id="snkh" >
							<option value="">--请选择客户--</option>
						</select></div>
				
				<label class="col-sm-2 control-label">车牌号码：</label>
				<div class="col-sm-3" style="width:28.5% ;position:relative; left:-17px; top:0px; ">
						<input type="text" class="form-control" name="tracknumber" placeholder="请输入车牌号" id="tracknumber" autofocus="on" >
							
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-2 control-label">水泥型号：</label>
				<div class="col-sm-3" style="height:60px;width:28%">
					<select class="form-control" name="snxh" id="snxh"  >
							<option value="">--请选择水泥型号--</option>
						</select></div>
				<label class="col-sm-2 control-label">司机凭证：</label>
				<div class="col-sm-3" style="height:62px;width:28.5% ;position:relative; left:-17px; top:0px; ">
					<input type="text" class="form-control" name="idnumber" placeholder="请输入司机身份证号" id="idnumber" autofocus="on" >
						
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
		</div> 
		<footer class="panel-footer text-center bg-light lter">
			<button type="button" id="cmxform_Submit"     class="btn btn-success btn-s-xs">提交</button>
			<input type="reset" onclick="readwrite();" class="btn btn-waring btn-s-xs" value="重置" />
		<!-- <input type="button" onclick="readwrite();" class="btn btn-waring btn-s-xs" value="可写"/> -->
		</footer> 
		</section>
	</form>
 
  
  </div>
	
</div>




<div class="clearfix"></div>
 
        <script type="text/javascript">
        
        function HQDate()
		{
		    var day="";
		    var month="";
		    var ampm="";
		    var ampmhour="";
		    var myweekday="";
		    var year="";
		    mydate=new Date();
		    myweekday=mydate.getDay();
		    mymonth=mydate.getMonth()+1;
		    myday= mydate.getDate();
		    myyear= mydate.getYear();
		    year=(myyear > 200) ? myyear : 1900 + myyear;
		    var now_date =year+"-"+mymonth+"-"+myday;
		    return now_date; 
		}
       
      
        $("#cmxform_Submit").click(
                function(){
                	 
          if($("#order_form").valid()){
        	  var fac_order_no =$.trim($("#fac_order_no").val());
        	  var drivername =$.trim($("#drivername").val());
        	  if(fac_order_no==""){
					//bootbox.alert("司机名称不能为空!");
					swal("订单不能为空!");
					$("#fac_order_no").focus();
					return false;
				}
        	  if(drivername==""){
        		    swal("司机名称不能为空!");
					//bootbox.alert("司机名称不能为空!");
					$("#drivername").focus();
					return false;
				}
        	  
        	var id = "body-loading";
  			$(window).loading({action:"open",id:id,font:40}); 
  			 document.getElementById('goodsname').value=$("#snxh").find("option:selected").text();
             
  			$("#order_form").submit();
  			var time = setTimeout(function(){
   				//关闭
   				$(window).loading({action:"close",id:id});
   			},10000);
        	 
          }
        	}); 
    
		$( document ).ready( function () {
			
			// 日期必要为今后
		    jQuery.validator.addMethod("isdateLtNow", function(value, element) { 
		    	
		         return this.optional(element) || new Date(value) >=new Date(HQDate());       
		    }, "提货日期选择今天之后的日期");  
			
			// 提货吨数小于1000
		    jQuery.validator.addMethod("isIntLtMax", function(value, element) { 
		         value=parseFloat(value);      
		         return this.optional(element) || value<=1000;       
		    }, "最大吨数为1000");  
			
		 // 提货吨数大于0
		    jQuery.validator.addMethod("isIntGlMin", function(value, element) { 
		         value=parseFloat(value);      
		         return this.optional(element) || value >0;       
		    }, "最小吨数大于0");  
			jQuery.validator.addMethod("isPhone", function(value, element) {    
			      var tel = /^\s*1\d{10}\s*$/;    
			      return this.optional(element) || (tel.test(value));    
			    }, "请正确填写您的电话号码。");
			// 身份证号码验证
		    jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
		      //var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;   
		      return this.optional(element) || isIdCardNo(value); 
		      
		    }, "请输入正确的身份证号码。");
		 // 车牌号验证
		    jQuery.validator.addMethod("isPlateNo", function(value, element) { 
		      //var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;   
		      return this.optional(element) || isPlateNo(value); 
		      
		    }, "车牌号格式不正确");
			$( "#order_form" ).validate( {
				rules: {
					/* fac_order_no:"required", */
					factoryid: {
						required: true,
						minlength:0
					},
					snkh: {
						required: true,
						minlength:0
					},
					thsj: {
						required: true,
						isdateLtNow:true
					},
					
					snxh: {
						required: true,
						minlength:0
					},
					tracknumber: {
						required: true,
						isPlateNo:true
					},
					driverphone:{
						required: true,
						isPhone:true
					},
					goodsnumber: {
						required: true,
						isIntLtMax:true,
						isIntGlMin:true
					},
					idnumber:{
						required: true,
						isIdCardNo:true
					}
				},
				messages: {
					fac_order_no: "请输入订单编号",
					factoryid: {
						required: "请选择工厂",
						minlength: "请选择工厂"
					},
					snkh: {
						required: "请选择客户名称",
						minlength: "请选择客户名称"
					},
					thsj: {
						required: "请选择提货日期",
						isdateLtNow: "提货日期选择今天之后的日期"
					},
					snxh: {
							required: "请选择水泥型号",
							minlength: "请选择水泥型号"
						},
					drivername:"请输入司机名称",
					tracknumber: {
						required: "请输入车牌号",
						isPlateNo: "车牌号格式不正确"
					},
					driverphone: {
						required: "请输入手机号",
						isPhone: "手机号格式不正确"
					},
					goodsnumber: {
						required: "请输入水泥吨数",
						isIntLtMax: "提货吨数不能超过1000",
						isIntGlMin: "提货吨数必须大于0",
					},
					idnumber:{
						required: "请输入司机身份证号",
						isIdCardNo:"身份证格式不正确"
					}
				},
				errorElement: "em",
				errorPlacement: function ( error, element ) {
					// Add the `help-block` class to the error element
					error.addClass( "help-block" );

					if ( element.prop( "type" ) === "checkbox" ) {
						error.insertAfter( element.parent( "label" ) );
					} else {
						error.insertAfter( element );
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-sm-3" ).addClass( "has-error" ).removeClass( "has-success" );
				},
				unhighlight: function (element, errorClass, validClass) {
					$( element ).parents( ".col-sm-3" ).addClass( "has-success" ).removeClass( "has-error" );
				}
			} );

		} );
	</script>
        
        
        
    <script type="text/javascript">
    
    function search_order(){
    	/* swal({
			  title: "查看失败",
			  text: "没有更多参赛人员，感谢您的支持。",
			  timer: 1000,
			  showConfirmButton: true
			}); */
    	var factoryid =$("#factoryid").val();
    	if(factoryid==""){
			//bootbox.alert("请选择工厂!");
			swal("请选择工厂!")
			$("#factoryid").focus();
			return false;
		}

   		 var fac_order_no =$.trim($("#fac_order_no").val());
    	if(fac_order_no==""){
			//bootbox.alert("请输入订单编号!");
			swal("请输入订单编号!");
			return false;
		}
    	var id = "body-loading";
    	$(window).loading({action:"open",id:id,font:40}); 
    	
		var url = "<%=basePath%>shop/findorder_no";
    	$.ajax({
			   type: "post", 
			   cache: false,  
			    url: url,  
			    timeout:10000,
			    data:{
					factory_id:factoryid,
					fac_order_no:fac_order_no,
					datetime:new Date().getTime()
				},
			    dataType: "json",  
			    success: function (data) { 
			    	//var str = data.toJSONString();
			    	var obj1=document.getElementById('snxh');
					obj1.options.length=0;
					var obj2=document.getElementById('snkh');
					obj2.options.length=0; 
			    	document.getElementById("snxh").options.add(new Option("--请选择型号--",""));
					document.getElementById("snkh").options.add(new Option("--请选择客户名称--",""));
					var time = setTimeout(function(){
						//关闭
						$(window).loading({action:"close",id:id});
					},100);
					if(data["msg"]=="ok"){
						/* debugger;
						bootbox.alert("订单号有效，请选择客户与水泥型号!"); */
						var obj = document.getElementById("fac_order_no"); 
						obj.setAttribute("readOnly",true); 
						swal("","订单号有效，请选择客户与水泥型号!");
				    	for(var i=0;i<data["listgoods"].length;i++){
				    		document.getElementById("snxh").options.add(new Option(data["listgoods"][i]["goodsname"],data["listgoods"][i]["goodsID"]));
				    	}
				    	for(var i=0;i<data["listclient"].length;i++){
				    		document.getElementById("snkh").options.add(new Option(data["listclient"][i]["clientname"],data["listclient"][i]["clientnumber"]));
				    	}
			    	
					}else {
						
						//bootbox.alert("订单号无效或工厂服务器异常，请稍后再试!");
						sweetAlert("", "订单号无效或工厂服务器异常，请稍后再试!", "error");
					}
			    },
			    error: function (data) {
			    	sweetAlert("", "订单号无效或工厂服务器异常，请稍后再试!", "error");
			    	var time = setTimeout(function(){
						//关闭
						$(window).loading({action:"close",id:id});
					},100);
			    }
		});
    	
    	
    	
    }
    
    
    function changeF(value,val){
			 var driverid =document.getElementById('driverid').value;
		     var url = "<%=basePath%>shop/finddriver_detail";
		        $.ajax({
				    type: "post", 
				    cache: false,  
				    url: url,  
				    timeout:10000,
				    data:{
				    	driverid:driverid,
	  					datetime:new Date().getTime()
	  				},
				    dataType: "json",  
				    success: function (data) { 
				    	document.getElementById('tracknumber').value=data["shopdriver"]["tracknumber"];
				    	document.getElementById('idnumber').value=data["shopdriver"]["idnumber"];
				    	document.getElementById('driverphone').value=data["shopdriver"]["phone"];
				    	
				    	
				    },
	  				error:function (data){
				    	document.getElementById('tracknumber').value=data["shopdriver"]["tracknumber"];
				    	document.getElementById('idnumber').value=data["shopdriver"]["idnumber"];
				    	document.getElementById('driverphone').value=data["shopdriver"]["phone"];
				    	
	  				}
			    }); 
		       
		        document.getElementById('drivername').value=val.innerHTML; 
		    }  
    function readwrite(){ 
    	document.getElementById("fac_order_no").readOnly=false;
    	$("#snkh").find("option").remove();
    	$("#snxh").find("option").remove();
    	document.getElementById("snxh").options.add(new Option("--请选择型号--",""));
		document.getElementById("snkh").options.add(new Option("--请选择客户名称--",""));
    	} 
    
    function facchange(){
      	document.getElementById("fac_order_no").readOnly=false;
    	$("#snkh").find("option").remove();
    	$("#snxh").find("option").remove();
    	document.getElementById("snxh").options.add(new Option("--请选择型号--",""));
		document.getElementById("snkh").options.add(new Option("--请选择客户名称--",""));
    	document.getElementById('fac_order_no').value="";
    }
    
  /*   function isreadonly(){ 
    	var obj = document.getElementById("fac_order_no"); 
    	obj.setAttribute("readOnly",true); 
    	obj.style.backgroundColor="#d2d2d2"; 
    	}  */
		 
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


