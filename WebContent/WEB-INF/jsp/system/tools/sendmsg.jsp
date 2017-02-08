<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	 <link rel="stylesheet" href="static/muselect/docs/css/bootstrap-3.3.2.min.css" type="text/css">
	 	 <link rel="stylesheet" href="static/loading/jquery.loading.css" type="text/css">
		 <link rel="stylesheet" href="static/bootstrap-select-news/dist/css/bootstrap-select.css">
		 <script type="text/javascript" src="static/muselect/docs/js/jquery-2.1.3.min.js"></script>
		 <script type="text/javascript" src="static/muselect/docs/js/bootstrap-3.3.2.min.js"></script>
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/bootstrap-select.js"></script>
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/i18n/defaults-zh_CN.js"></script>
		<script src="static/js/bootbox.js"></script>
		 <script type="text/javascript">
		 window.index = 0;
		 //发送消息
		 function sendmsg(){
			 if( $("#msg_type").val()==null){
				 bootbox.alert("请选择消息类型", function () {
				      });
				 $("#msg_type").focus();
				 return false;
			 }
			 if( $("#recive_openID").val()==null){
				 bootbox.alert("请选择消息接收者", function () {
				      });
				 $("#recive_openIDs").focus();
				 return false;
			 }
			 
			 if($("#msg_type").val()=="text_msg" && $("#content_text").val()==""){
				 bootbox.alert("请输入内容", function () {
				      });
				 $("#content_text").focus();
				 return false;
			 }
			 if($("#msg_type").val()=="imgtex_msg"){
				 for(var i=0;i<index;i++){
						if($("#title"+i).val()==""){
							bootbox.alert("标题不能为空！", function () {
						      });
						 $("#title"+i).focus();
						 return false;
						}
						/* if($("#url_my"+index)==""){
							bootbox.alert("标题不能为空！", function () {
						      });
						 $("#title"+index).focus();
						 return false;	
						} */
						if($("#author"+i).val()==""){
							bootbox.alert("作者不能为空！", function () {
						      });
						 $("#author"+i).focus();
						 return false;
						}
						if($("#digest"+i).val()==""){
							bootbox.alert("描述不能为空！", function () {
						      });
						 $("#digest"+i).focus();
						 return false;
						}
						if($("#file"+i).val()==""){
							bootbox.alert("图片不能为空！", function () {
						      });
						 $("#file"+i).focus();
						 return false;	
						}
						if($("#content_text"+i).val()==""){
							bootbox.alert("内容不能为空！", function () {
						      });
							 $("#content_text"+i).focus();
							 return false;
						}
						
						
					}
			 }
			 
			 
			 var msg_type =$('#msg_type').val();
			 var url ="";
			 $("#recive_openIDs").val(($("#recive_openID").val()).join(","));
			 $("#msgtype").val(msg_type);
			 var id = "body-loading";
			 $(window).loading({action:"open",id:id,font:40});
			 if(msg_type=="text_msg"){
				 url = "<%=basePath%>tool/sendmsg?tm="+new Date().getTime();
				 var recive_openIDs =$('#recive_openIDs').val();
				 var content_text =$('#content_text').val();
				 var app_id =$('#app_id').val();
				 var msgtype =$('#msgtype').val();
				 $.ajax({
						type: "POST",
						url: url,
				    	data: {app_id:app_id,recive_openIDs:recive_openIDs,msgtype:msgtype,content_text:content_text},
						dataType:'json',
						cache: false,
						success: function(data){
							 var time = setTimeout(function(){
			         				//关闭
			         				$(window).loading({action:"close",id:id});
			         			},50);
							if(data["errcode"]=="0"){
								bootbox.alert("消息发送成功", function () {
							      });
							}else{
								bootbox.alert("错误信息："+data["errmsg"]+",请稍后再试", function () {
							      });
							}
							
						}
					}); 
			 }else if(msg_type=="imgtex_msg"){
				 	$( "#msgForm" ).enctype = 'multipart/form-data';
				    var formData = new FormData($( "#msgForm" )[0]);
				    $.ajax({
				      url: "<%=basePath%>tool/uploadimgmsg?tm="+new Date().getTime() ,
				      type: 'POST',
				      data: formData,
				      async: false,
				      dataType:'json',
				      cache: false,
				      contentType: false,
				      processData: false,
				      success: function (data) {
				    	  var time = setTimeout(function(){
		         				//关闭
		         				$(window).loading({action:"close",id:id});
		         			},50);
				    	  if(data["errcode"]=="0"){
								bootbox.alert("消息发送成功", function () {
							      });
							}else{
								bootbox.alert("错误信息："+data["errmsg"]+",请稍后再试", function () {
							      });
							}
				    	 /*  bootbox.alert("图文消息发送成功", function () {
					      }); */
				      },
				      error: function (data) {
				    	  bootbox.alert("图文消息发送失败", function () {
					      });
				      }
				    });
			 }
			
			 
		 }
		 
		 
		 
		
		 
		 
		 </script>
		<style type="text/css">
		</style>
	
	</head> 
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  
  <br>
	<div class="form-group" id ="type_group">
      <label for="name">消息类型</label>
      <select id="msg_type" class="selectpicker" multiple data-max-options="1">
          <option value="text_msg">文本消息</option>
          <option value="imgtex_msg">图文消息</option>
        </select>
        
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <label for="name">消息接受者</label>
          <select  id="recive_openID" name="recive_openID"  class="selectpicker" multiple data-live-search="true" data-live-search-placeholder="Search" data-actions-box="true">
             <optgroup label="客户">
               <c:forEach items="${client_users}" var="client">
              <option value="${client.openid }" >${client.namepinyin } </option>
            </c:forEach>
            </optgroup>
            <optgroup label="员工">
              <c:forEach items="${employees}" var="emp">
              <option value="${emp.openid }" >${emp.namepinyin } </option>
            </c:forEach>
            </optgroup> 
            <optgroup label="其他">
            <c:forEach items="${list_bindusers}" var="Wx_BindUser" varStatus="vs">
              <option value="${Wx_BindUser.openid }" >关注者${vs.index+1}</option>
            </c:forEach>
            </optgroup>
          </select>
         <span id="valierr" style="color:#FF9966">*</span>&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label for="name">图文消息操作</label>
		 <a class="btn btn-small btn-success" id="add" disabled="disabled" onclick="add_imgmsg();" style="cursor:not-allowe;">新增</a>&nbsp;&nbsp;
		 <a class='btn btn-mini btn-danger'  id="del"  disabled="disabled" onclick="del_imgmsg();">删除</a>
    </div>
  		
 
		<form action='' name='msgForm' id='msgForm' method='post'   >
			  <input type='hidden' name='recive_openIDs' id='recive_openIDs' />
			  <input type='hidden' name='msgtype' id='msgtype' />
			  <input type='hidden' name='app_id' id='app_id' value="${app_id}"/>
			  
		  <div class="form-group" id ="add_div">
		  </div>
		<div class='container-fluid'>
		     <a class='btn btn-info' onclick='sendmsg();'>发送消息</a>
			<a class='btn btn-warning' onclick='reset();'>重置消息</a>
		  </div></form>
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<!-- <a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a> -->
		
		<!-- 引入 -->
		

		<script type="text/javascript">
		window.jQuery || document.write("<script src='js/jquery-1.9.1.min.js'>\x3C/script>");
		</script>
		
		 <script type="text/javascript">
		$(top.hangge());
		
		
		
		function add_imgmsg(){
			 var start_div=""+
			 
			 "<div class='img' id='img_div"+index+"'><div class='row'>"+
			 " <div class='col-xs-4'>"+
			    "  <label for='name'>标题</label>"+
			      
			   " <input type='text'  id =\"title"+index+"\"  name=\"imgMsgs["+index+"].title\"  class='form-control' placeholder='请输入标题'>"+
			 " </div>"+
			 
			 " <div class='col-xs-4'>"+
			 " <label for='name'>阅读全文</label>"+
			 "   <input type='text' id=\"url_my"+index+"\"   name=\"imgMsgs["+index+"].url_my\"   class='form-control' placeholder='请输入链接'>"+
			"  </div>"+
			  
			"   <div class='col-xs-2'>"+
			"  <label for='name'>作者</label>"+
			"    <input type='text'  id=\"author"+index+"\"  name=\"imgMsgs["+index+"].author\"  class='form-control' placeholder='请输入作者'>"+
		"	  </div>"+
			  
		"	</div>"+
		"	<div class='row'>"+
		"	  <div class='col-xs-4'>"+
		"	  <label for='name'>描述</label>"+
		"	    <input type='text'   id=\"digest"+index+"\" name=\"imgMsgs["+index+"].digest\" class='form-control'  placeholder='请输入图文描述'>"+
		"	  </div>"+
			 
			  
		
		"	   <div class='col-xs-2'>"+
		"	  <label for='name' >图片</label>"+
		"	    <input   type=\"file\" id=\"file"+index+"\" name=\"imgMsgs["+index+"].file\" class=\"file\" >"+
		"	  </div>"+
		"	</div>"+
		"	<div class='form-group' id ='title_group'>"+
		"	      <label for='name'>内容</label>"+
		"	       <div class='container-fluid'>"+
		//"<input  type=\"text\" class='form-control' id=\"content_text"+index+"\"  name=\"imgMsgs["+index+"].content_text\" placeholder='请输入内容' >"+
		"	         <textarea rows='3' class='form-control' id=\"content_text"+index+"\"   name=\"imgMsgs["+index+"].content_text\" placeholder='请输入内容'></textarea>"+
		"	         </div>"+
		"	   </div></div>"; 
		$("#add_div").append(start_div);
		//$("#add_div").innerHTML="<p>I inserted <em>this</em> content.</p>"
		//$("#add_div").appendChild(start_div);
		index+=1;
		 }
		 function del_imgmsg(){
			 index-=1;
			 $("#img_div"+index).remove(); 
		 }
		 
		 
		 $(function(){
			 //根据消息类型显示不同的表单
			  $('#msg_type').change(function(){
				 var msg_type =$('#msg_type').val();
				 if("text_msg"==msg_type){
					 $("#add").attr("disabled","disabled"); 
					 $("#del").attr("disabled","disabled"); 
					 var start_div ="<div id ='text_div'>";
					  start_div+=" <div class='form-group'>"+
					   " <label for='name'>内容</label>"+
				        "<div class='container-fluid'>"+
				        "<textarea rows='3' class='form-control' id='content_text' "+
					        " placeholder='请输入内容'></textarea>"+
				        " </div>"+
				   "</div> </div>";
				   // $("#add_div").innerHTML="<p>I inserted <em>this</em> content.</p>"
					 $("#add_div").append(start_div);
					 //$("#add_div").innerHTML=start_div
					 $(".img").remove();
				 }else if("imgtex_msg"==msg_type){
					 $("#del").removeAttr("disabled"); 
					 $("#add").removeAttr("disabled"); 
					 $("#text_div").remove(); 
					 add_imgmsg();
					 
					 
				 }else{
					 $("#text_div").remove();
					 $(".img").remove();
				 }
				 
			 
			 });
				
			});
			
		</script> 
		
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
	
		
	</body>
</html>

