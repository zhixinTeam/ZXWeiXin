<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
    <html lang="en">
    <head>
    <base href="<%=basePath%>">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
    
        <title>防伪码查询</title>
		<link rel="stylesheet" href="static/muselect/docs/css/bootstrap-3.3.2.min.css" type="text/css">
	 	<link rel="stylesheet" href="static/loading/jquery.loading.css" type="text/css">
		 <link rel="stylesheet" href="static/bootstrap-select-news/dist/css/bootstrap-select.css">
		 <script type="text/javascript" src="static/muselect/docs/js/jquery-2.1.3.min.js"></script>
		 <script type="text/javascript" src="static/muselect/docs/js/bootstrap-3.3.2.min.js"></script>
		  <script type="text/javascript" src="static/bootstrap3.0.3/bootstrap.min.js"></script> 
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/bootstrap-select.js"></script>
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/i18n/defaults-zh_CN.js"></script>
		<script src="static/js/bootbox.js"></script>
       <!--  <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.min.css">
 -->
   
     
        <style>
            .bs-docs-home
            {
            background-color: #FFFFFF;
            background-image: url(line.png);
            }
    </style>
      <script>
/**
 * Author:ZengWeilong Version 1.4
 * V1.4
 */
(function() {
	/**
	 * options = {action:"open",id:loadingId}
	 */
	$.fn.loading = function(options) {
		/*默认值不可随意更改*/
		var dfault = {
			id : null, /*loading Id*/
			rgba : 0.6, /*背景透明度*/
			action : "close", /*执行方式true打开 其他值关闭*/
			before : null, /* 动作执行前函数*/
			after : null, /*动作执行后函数*/
			font:10,
			position : "absolute" /*fixed 固定绝对位置 absolute相对位置*/
		};
		var json = $.extend({},dfault, options);

		if (json.id == null)
			return;
		
		var winobj = $(this);
		if (typeof (json.before) == "function")
			json.before();
		
		/*core*/
		var core = {
			restyle : function(winobj) {
				var height = $(winobj).innerHeight();
				var width = $(winobj).innerWidth();
				var top = $(winobj).offset() == undefined ? 0 : $(winobj).offset().top;
				var left = $(winobj).offset() == undefined ? 0 : $(winobj).offset().left;
				json.position = $(winobj).offset() == undefined ? "fixed":"absolute";
				var style = "z-index:10000;position:" + json.position + ";left:" + left + "px;top:"
						+ top + "px;width:" + width + "px;"
						+ "text-align:center;background:rgba(168,168,168," + json.rgba
						+ ");height:" + height + "px;line-height:" + height + "px;";
				return style;
			},
			open:function(){
				if (!$("#" + json.id).attr("style")) {
					var style = core.restyle(winobj);
					var divhtml = "<div id='" + json.id + "' style='display:none;" + style
							+ "'><span class='spinner-loader' style='font-size:"+json.font+"px!important;'>Loading&#8230;</span></div>";
					$("body").append(divhtml);
				}
				$("#" + json.id).fadeIn(200, function() {
					if (typeof (json.after) == "function")
						json.after();
				});
			},
			close:function(){
				$("#" + json.id).fadeOut(500, function() {
					if (typeof (json.after) == "function")
						json.after();
				}).remove();
			},
			resize:function(){
				var style = core.restyle(winobj);
				$("#" + json.id).removeAttr("style").attr("style", style);
			}
		}
		
		/*open*/
		if ("open" == json.action) {
			core.open();
		}
		/*close*/
		if ("close" == json.action) {
			core.close();
		}
		/*window change Listen*/
		$(window).resize(function() {
			core.resize();
		});
	}

})();





	
</script>
    
     
</head>

<body class="bs-docs-home">
    <div class="container theme-showcase">
        <h1 style=" line-height:2em;"> </h1><br />
      
        <div class="row">
            <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                   <!--  <h3 class="panel-title"><strong>防伪码验证</strong></h3> -->
                </div>
                <div class="panel-body">
                   
              		 <div id="error_div" >
                       
                       </div>
                       <form role="form" name="form1">
							
							<div class="form-group">
                               <label for="IDCard">请选择工厂</label>
		       					<select id="factoryid" class="selectpicker show-tick form-control" >
	        						<c:forEach items="${factoryList}" var="factory">
										<option value="${factory.id }" >${factory.factoryname }</option>
									</c:forEach>
						        </select>
		       
		       
		      					
                           </div>
							
							<div id="error_code" >
                       
                       		</div>
                           <div class="form-group">
                               <label for="IDCard">请输入产品防伪码</label>
                               <div class="input-group">
                                   <input type="text" class="form-control" id="secode" name="secode" placeholder="产品防伪码" >
                                   <span class="input-group-btn">
                                       <button id="body-button" class="btn btn-default" type="button" onclick="query_form();" >查询</button>
                                   </span>
                               </div>
                           </div>
        
                          
      
                       </form>
           
                      
            
                       <div class="table-responsive">
                           <table  id ="add_table" border="0" cellspacing="0" cellpadding="0" class="table">
                               <tr class=" label-primary">
                                   <th scope="col" width="50%" ><span style="color:white">查询结果</span></th>
                                   <th scope="col"><span style="color:white"></span></th>
                               </tr>
                              
                                <tbody  id ="add_div">
                                	<!--  <tr class="active">
	                                   <td width="50%">水泥厂商：</td>
	                                   <td>测试第一厂</td>
                               		</tr>
	                               <tr class="success">
	                                   <td width="50%">产品型号：</td>
	                                   <td>袋装水泥#525</td>
	                               </tr> -->
                                
		 					 </tbody>
                              
                           </table>
                       </div>         
                   </div>
               </div>
           </div>
     
           <div class="col-sm-3"></div>
        </div>
    </div> 
    <script type="text/javascript">
     	function query_form(){
     		var factoryid =$('#factoryid').val();
     		var err_html="";
     		$("#error_div").html(""); 
     		$("#error_code").html(""); 
     		if( factoryid==""){
     			 err_html += " <div  class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button><strong>注意!</strong> 请选择工厂</div>";
                 
               	$("#error_div").html(err_html); 
				 /* bootbox.alert("请选择工厂", function () {
				      }); */
				 $("#factoryid").focus();
				 return false;
			 }
			 var secode =$('#secode').val();
			 if( secode==""){
				 err_html += " <div  class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button><strong>注意!</strong> 请输入防伪码</div>";
                 
	             $("#error_code").html(err_html); 
				 /* bootbox.alert("请输入产品防伪码", function () {
			      }); */
				 $("#secode").focus();
				 return false;
			 }
			//打开
			 var id = "body-loading";
			 $(window).loading({action:"open",id:id,font:40});
			 url = "<%=basePath%>tool/query_code?tm="+new Date().getTime();
			  $.ajax({
					type: "POST",
					url: url,
			    	data: {factoryid:factoryid,secode:secode},
			    	timeout: 10000,
					dataType:'json',
					cache: false,
					error: function(XMLHttpRequest, textStatus, errorThrown){
						var html_tr=" <tr class=\"active\">"+
						"<td width=\"50%\">查询结果：</td>"+
						"<td>网络异常请稍后再试！</td>"+
                 		"</tr>";
						 $("#add_div").html(""); 
	                     $("#add_div").html(html_tr);
	                     var time = setTimeout(function(){
	         				//关闭
	         				$(window).loading({action:"close",id:id});
	         			},1000);
					         }, 
					success: function(data){
						if(data["errmsg"]=="ok"){
							var html_tr=" <tr class=\"active\">"+
							"<td width=\"50%\">水泥厂商：</td>"+
							"<td>"+data["fac_name"]+"</td>"+
                     		"</tr>"                         +
	                        "<tr class=\"success\">" +
	                        " <td width=\"50%\">产品型号：</td>"+
	                        " <td>"+data["goods_name"]+"</td>"+
	                       "  </tr>"  ;
	                       $("#add_div").html(""); 
	                       $("#add_div").html(html_tr);
	                       var time = setTimeout(function(){
		         				//关闭
		         				$(window).loading({action:"close",id:id});
		         			},1000);
							
						}else if(data["errmsg"]=="error"){
							var html_tr=" <tr class=\"active\">"+
								"<td width=\"50%\">查询结果：</td>"+
								"<td>无该防伪码！</td>"+
	                     		"</tr>";
							 $("#add_div").html(""); 
		                     $("#add_div").html(html_tr);
		                     var time = setTimeout(function(){
			         				//关闭
			         				$(window).loading({action:"close",id:id});
			         			},1000);
						}else if(data["errmsg"]=="timeout"){
							var html_tr=" <tr class=\"active\">"+
							"<td width=\"50%\">查询结果：</td>"+
							"<td>网络异常请稍后再试！</td>"+
                     		"</tr>";
						 $("#add_div").html(""); 
	                     $("#add_div").html(html_tr);
	                     var time = setTimeout(function(){
		         				//关闭
		         				$(window).loading({action:"close",id:id});
		         			},1000);
					}
						
					}
				}); 
			
     	}
     </script>
   <!--  <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
     --><script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
</body>
</html>