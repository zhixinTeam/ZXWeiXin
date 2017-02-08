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
    
        <title>待装车辆查询</title>
		
        <link rel="stylesheet" href="static/loading/jquery.loading.css" type="text/css">
        <link rel="stylesheet" href="static/muselect/docs/css/bootstrap-3.3.2.min.css" type="text/css">
	 
		 <link rel="stylesheet" href="static/bootstrap-select-news/dist/css/bootstrap-select.css">
		 <script type="text/javascript" src="static/muselect/docs/js/jquery-2.1.3.min.js"></script>
		 <script type="text/javascript" src="static/muselect/docs/js/bootstrap-3.3.2.min.js"></script>
		  <script type="text/javascript" src="static/bootstrap3.0.3/bootstrap.min.js"></script> 
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/bootstrap-select.js"></script>
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/i18n/defaults-zh_CN.js"></script>
		<script src="static/js/bootbox.js"></script>
    
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
<body>

 <div class="container theme-showcase" >
        <h1 style=" line-height:2em;"> </h1><br />
      
        <div class="row">
            <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><strong>待装车辆查询结果</strong></h3>
                </div>
                
							
                <div class="panel-body">
                   
                   <div id="error_div" >
                       
                       </div>
              		<form role="form" name="form1">
							
							<div class="form-group">
                               <label for="IDCard">请选择工厂:</label><br>
		       					<select id="factoryid" class="selectpicker"   >
	        						<c:forEach items="${factoryList}" var="factory">
										<option value="${factory.id }" >${factory.factoryname }</option>
									</c:forEach>
						        </select> <button   id="body-button" class="btn btn-default" type="button" onclick="query_car();" >查询</button>
                         	</div>
							
                           
        				</form>
                       
                           <table  border="0" cellspacing="0" cellpadding="0" class="table">
                               <tr class=" label-primary">
                                   <th scope="col" width="50%" ><span style="color:white">物料名称</span></th>
                                   <th scope="col"><span style="color:white">开放通道</span></th>
								   <th scope="col"><span style="color:white">排队车辆</span></th>
                               </tr>
                               <tbody id="add_tr">
                               
                               </tbody>
                               <!-- <tr class="active">
                                   <td width="50%">P.C32.5R袋装</td>
                                   <td>3</td>
								   <td>4</td>
                               </tr>
                               <tr class="success">
                                   <td>P.C32.5R袋装</td>
                                   <td>2</td>
								   <td>3</td>
                               </tr>
                              <tr class="active">
                                   <td>P.C65.5R袋装</td>
                                   <td>1</td>
								   <td>0</td>
                               </tr> -->
                           </table>
                           
                   </div>
               </div>
           </div>
     
           <div class="col-sm-3"></div>
        </div>
    </div> 


<script type="text/javascript">
     	function query_car(){
     		var factoryid =$('#factoryid').val();
     		 $("#error_div").html(""); 
     		var err_html="";
     		if( factoryid ==""){
     			 err_html += " <div  class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button><strong>注意!</strong> 请选择工厂</div>";
                
              	$("#error_div").html(err_html); 
				/*  bootbox.alert("请选择工厂", function () {
				      }); */
				 $("#factoryid").focus();
				 return false;
			 } 
     		
			//打开
			 var id = "body-loading";
			$(window).loading({action:"open",id:id,font:40});
			
			url = "<%=basePath%>tool/query_car?tm="+new Date().getTime();
			$.ajax({
					type: "POST",
					url: url,
			    	data: {factoryid:factoryid},
			    	timeout: 10000,
					dataType:'json',
					cache: false,
					error: function(XMLHttpRequest, textStatus, errorThrown){
						var html_tr="";
						var html_class ="";
						html_class="active";
						html_tr+="<tr class="+html_class+">"+
                           "<td width=\"50%\"></td>"+
                          " <td>网络异常请稍后再试</td>"+
							"   <td></td>"
                      " </tr>";	
						 $("#add_tr").html(""); 
	                     $("#add_tr").html(html_tr);
	                     var time = setTimeout(function(){
	         				//关闭
	         				$(window).loading({action:"close",id:id});
	         			},1000);
					         }, 
					success: function(data){
						
						var html_tr="";
						var html_class ="";
						if(data["errmsg"]=="ok"){
							for(var i=0;i<data["listcars"].length;i++){
								if(i%2 ==0)
									html_class="active"
								else
									html_class="success"	
								html_tr+="<tr class="+html_class+">"+
	                           "<td width=\"50%\">"+data["listcars"][i]["goodsname"]+"</td>"+
	                          " <td>"+data["listcars"][i]["vehicle_Lane"]+"</td>"+
								"   <td>"+data["listcars"][i]["car_num"]+"</td>"
	                      " </tr>";
	                      }
							
						}else{
								html_class="active";
								html_tr+="<tr class="+html_class+">"+
		                           "<td width=\"50%\"></td>"+
		                          " <td>网络异常请稍后再试</td>"+
									"   <td></td>"
		                      " </tr>";
						}
						 $("#add_tr").html(""); 
	                     $("#add_tr").html(html_tr);
	                     var time = setTimeout(function(){
	         				//关闭
	         				$(window).loading({action:"close",id:id});
	         			},1000);
					}
				});
     	}
     </script>
     
    
      <script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
     
</body>
</html>
