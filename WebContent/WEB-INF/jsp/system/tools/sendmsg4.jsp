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
		 <link rel="stylesheet" href="static/bootstrap-select-news/dist/css/bootstrap-select.css">
		 <script type="text/javascript" src="static/muselect/docs/js/jquery-2.1.3.min.js"></script>
		 <script type="text/javascript" src="static/muselect/docs/js/bootstrap-3.3.2.min.js"></script>
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/bootstrap-select.js"></script>
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/i18n/defaults-zh_CN.js"></script>
		 <script type="text/javascript">
		 window.index = 1;
		 //发送消息
		 function sendmsg(){
			 alert("+++===");
			 var msg_type =$('#msg_type').val();
			 alert(msg_type);
			 var url ="";
			 $("#recive_openIDs").val(($("#recive_openID").val()).join(","));
			 $("#msgtype").val(msg_type);
			 if(msg_type=="text_msg"){
				 url = "<%=basePath%>tool/sendmsg?tm="+new Date().getTime();
				 
				 
				 var recive_openIDs =$('#recive_openIDs').val();
				 var content_text =$('#content_text').val();
				 var msgtype =$('#msgtype').val();
				 $.ajax({
						type: "POST",
						url: url,
				    	data: {recive_openIDs:recive_openIDs,msgtype:msgtype,content_text:content_text},
						dataType:'json',
						cache: false,
						success: function(data){
							alert(data);
						}
					}); 
			 }else if(msg_type=="imgtex_msg"){
				 	alert("上传");
				 	$( "#msgForm" ).enctype = 'multipart/form-data';
				    var formData = new FormData($( "#msgForm" )[0]);
				    $.ajax({
				      url: "<%=basePath%>tool/sendimgmsg?tm="+new Date().getTime() ,
				      type: 'POST',
				      data: formData,
				      async: false,
				      cache: false,
				      contentType: false,
				      processData: false,
				      success: function (returndata) {
				    	  alert("success");
				          //alert(returndata);
				      },
				      error: function (returndata) {
				    	  alert("error");
				          //alert(returndata);
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
        </select><span id="valierr" style="color:#FF9966">*</span>
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
  
		  <div class="form-group" id ="add_div">
		  </div>
		  
		   <!--   <div class="container-fluid">
		     <a class="btn btn-info" onclick="sendmsg();">发送消息</a>
			<a class="btn btn-warning" onclick="reset();">重置消息</a>
		   </div>
    -->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		

		<script type="text/javascript">
		window.jQuery || document.write("<script src='js/jquery-1.9.1.min.js'>\x3C/script>");
		</script>
		
		 <script type="text/javascript">
		$(top.hangge());
		
		function add_imgmsg(){
			 var start_div=""+
				" <form action='tool/sendmsg' name='msgForm' id='msgForm' method='post'   >"+
			  "<input type='hidden' name='recive_openIDs' id='recive_openIDs' />"+
			  "<input type='hidden' name='msgtype' id=' msgtype' />"+
			 
			 "<div class='img' id='img_div"+index+"'><div class='row'>"+
			  
			 " <div class='col-xs-4'>"+
			    "  <label for='name'>标题</label>"+
			      
			   " <input type='text'  id ='title"+index+"'  name='title"+index+"'  class='form-control' placeholder='请输入标题'>"+
			 " </div>"+
			 
			 " <div class='col-xs-4'>"+
			 " <label for='name'>阅读全文</label>"+
			 "   <input type='text' class='form-control' placeholder='请输入链接'>"+
			"  </div>"+
			  
			"   <div class='col-xs-2'>"+
			"  <label for='name'>作者</label>"+
			"    <input type='text' class='form-control' placeholder='请输入作者'>"+
		"	  </div>"+
			  
		"	</div>"+
		"	<div class='row'>"+
		"	  <div class='col-xs-4'>"+
		"	  <label for='name'>描述</label>"+
		"	    <input type='text' class='form-control'  placeholder='请输入图文描述'>"+
		"	  </div>"+
		"	   <div class='col-xs-2'>"+
		"	  <label for='name' >图片</label>"+
		"	    <input id='tp' name='tp' type=\"file\" class=\"file\" >"+
		"	  </div>"+
		"	</div>"+
		"	<div class='form-group' id ='title_group'>"+
		"	      <label for='name'>内容</label>"+
		"	       <div class='container-fluid'>"+
		"	         <textarea rows='3' class='form-control' id='content_text' placeholder='请输入内容'></textarea>"+
		"	         </div>"+
		"	   </div></div>"+
		"<div class='container-fluid'>"+
		"     <a class='btn btn-info' onclick='sendmsg();'>发送消息</a>"+
		"	<a class='btn btn-warning' onclick='reset();'>重置消息</a>"+
		"   </div></form>"; 
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
					 
					 
				 }
				 
			 
			 });
				
			});
			
		</script> 
		
		
	
		
	</body>
</html>

