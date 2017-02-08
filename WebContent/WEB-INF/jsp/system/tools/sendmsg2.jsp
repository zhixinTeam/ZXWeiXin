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
		 $(document).ready(function(){
			function save(){
				alert("++++++");
			}
			 
			 //根据消息类型显示不同的表单
			  $('#msg_type').change(function(){
				 var msg_type =$('#msg_type').val();

					var start_div ="<div id ='context_group'>";
				 if("text_msg"==msg_type){
					  start_div+=" <div class='form-group'>"+
					   " <label for='name'>内容</label>"+
				        "<div class='container-fluid'>"+
				        "<textarea rows='3' class='form-control' id='name' "+
					        " placeholder='请输入内容'</textarea>"+
				        " </div>"+
				   "</div> </div>";
					 $("#type_group").append(start_div);
				 }else{
					 var url = "<%=basePath%>tool/getmsgtype?msg_type="+msg_type+"&guid="+new Date().getTime();
					 $.get(url,function(data){
							if(data.length>0){
								$("#context_group").remove();
								$.each(data,function(i){
									start_div+=" <div class='form-group'>"+
									   " <label for='name'>内容</label>"+
								        "<div class='container-fluid'>"+
								      "<input type='text' class='form-control' id='name' "+
								        " placeholder='请输入内容'>"+
								        " </div>"+
								   "</div> ";
									
								});
								var end_div =start_div+"</div>"
								
								$("#title_group").append(end_div);
								
							}else{
								$("#context_group").remove();
							}
						},"json");
					 
				 }
				 
			 
			 });
			 
			 
			 
			 
			 //获取模版消息的，模版参数
			 $('#template_id').change(function(){
				 var templateid =$('#template_id').val();
				 var url = "<%=basePath%>tool/gettemplate?templateid="+templateid+"&guid="+new Date().getTime();
				 $.get(url,function(data){
						if(data.length>0){
							$("#context_group").remove();
							var start_div ="<div id ='context_group'>";
							$.each(data,function(i){
								start_div+=" <div class='form-group'>"+
								   " <label for='name'>内容</label>"+
							        "<div class='container-fluid'>"+
							      "<input type='text' class='form-control' id='name' "+
							        " placeholder='请输入内容'>"+
							        " </div>"+
							   "</div> ";
								
							});
							var end_div =start_div+"</div>"
							
							$("#title_group").append(end_div);
							
						}else{
							$("#context_group").remove();
						}
					},"json");
			 
			 });
				
			});
		 
		 
		 </script>
		<style type="text/css">
		
		</style>
	
	</head> 
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <form role="form">
	<div class="form-group" id ="type_group">
      <label for="name">消息类型</label>
      <select id="msg_type" class="selectpicker" multiple data-max-options="1">
          <option value="text_msg">文本消息</option>
          <option value="imgtex_msg">图文消息</option>
          <option value="temple_msg">模板消息</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <label for="name">消息接受者</label>
          <select class="selectpicker" multiple data-live-search="true" data-live-search-placeholder="Search" data-actions-box="true">
            <optgroup label="客户">
              <option>option1</option>
              <option>option2</option>
              <option>option3</option>
              <option>option4</option>
            </optgroup>
            <optgroup label="员工">
              <option>option1</option>
              <option>option2</option>
              <option>option3</option>
              <option>option4</option>
            </optgroup>
            <optgroup label="其他">
              <option>option1</option>
              <option>option2</option>
              <option>option3</option>
              <option>option4</option>
            </optgroup>
          </select>


    </div>
	<div class="form-group">
      <label for="name">模版类别</label>
      <div class="col-lg-10">
        
         <select id="template_id" class="selectpicker show-menu-arrow form-control" multiple data-max-options="1">
          <option value="1">chicken</option>
          <option value="2">turkey</option>
          <option value="3">duck</option>
          <option value="4">goose</option>
        </select>
      </div>
    </div>
    
   
	
   <div class="form-group" id ="title_group">
      <label for="name">标题</label>
       <div class="container-fluid">
      <input type="text" class="form-control" id="name" 
         placeholder="请输入标题">
         </div>
   </div>
   
   
   
   
   
    <div class="form-group">
      <label for="name">备注</label>
        <div class="container-fluid">
      <input type="text" class="form-control" id="name" 
         placeholder="请输入备注">
         </div>
   </div>
   
   
     <div class="container-fluid">
     <a class="btn btn-mini btn-primary"  onclick="javascript:save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="reset();">重置</a>
   <button type="button" class="btn btn-info" onclick="javascript:save();">发送消息</button>
   <input type="button"  onclick="javascript:save();"  value ="发送消息">
   <button type="button" class="btn btn-warning">重置消息</button>
   </div>
</form>
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		
		
	
		
		<script type="text/javascript">
    $(document).ready(function() {
        $('#example-getting-started').multiselect();
    });
    
</script>








		<script type="text/javascript">
		window.jQuery || document.write("<script src='js/jquery-1.9.1.min.js'>\x3C/script>");
		</script>
		
		<script type="text/javascript">
		$(top.hangge());
		</script>
		
		
	
		
	</body>
</html>

