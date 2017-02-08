 
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
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />

<script type="text/javascript" src="static/js/bootbox.min.js"></script>
<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
	
	});
	
	//保存
	function save(){
				
		var result=document.getElementById("result").value;
		if(result=="error"){
			alert("此类型已存在，请重新填写！");
		}else{
			$("#comForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
	
	}
</script>
<style>
.control-group {
	margin-left: 100px;
}
.legend{

}
</style>
	</head>
<body>
	<form class="form-horizontal" action="template/${msg}" name="comForm" id="comForm" method="post" enctype="multipart/form-data">
	
		<input type="hidden" name="currentPage" id="currentPage" value="${pd.currentPage }"/>
	<div id="zhongxin">
    <fieldset>
      <div id="legend" class="" align="center">
        <legend class="">新增模板消息</legend>
      </div>
		<div class="control-group">

          <!-- Text input-->
          <label class="control-label" >模板ID:</label>
          <div class="controls">
            <input placeholder=""   name="templateid" id="templateid" style="width: 360px"  class="input-xlarge" type="text" value="${pd.xmt.templateid }">
            <input placeholder=""   name="tid" id="tid" style="width: 360px"  class="input-xlarge" type="hidden" value="${pd.xmt.id }">
            <p class="help-block"></p>
          </div>
        </div> 
        
  
        
          <div class="control-group">
			<label class="control-label">集团名称：</label>
        <div class="controls">
		 <input placeholder=""   style="width: 360px;text-align: center;" readonly="readonly" name="com_id" id="com_id"   class="input-xlarge" type="text" value="${pd.doc_Company.companyname}">			  	
        </div>
 		</div>
    <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">模板名称：</label>
          <div class="controls">
           <input placeholder=""  style="width: 360px;text-align: center;" name="remark" id="remark" value="${pd.xmt.remark }" class="input-xlarge" type="text">
           <input placeholder=""  style="width: 360px;text-align: center;" name="result" id="result" value="success" class="input-xlarge" type="hidden">
          </div>
        </div>
         <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">模板类型：</label>
          <div class="controls">
           <input placeholder=""  style="width: 360px;text-align: center;" name="typebs" id="typebs" value="${pd.xmt.typebs}" class="input-xlarge" type="text" onblur="chkvalue(this)">
            <input   style="width: 360px;text-align: center;" name="typebs1" id="typebs1" value="${pd.xmt.typebs}" class="input-xlarge" type="hidden" >
          </div>
        </div>
       
	
    <div class="control-group">
         
          <!-- Button -->
          <div class="controls">
            <a class="btn btn-mini btn-primary" onclick="save();">保存</a>
			<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
          </div>
          
        </div>
        

    </fieldset>
  </div>
  </form>
	<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>

		<!-- 引入 -->
		
		<script type="text/javascript">
		
$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});
		
		function chkvalue(txt) {
			   if(txt.value=="") {
				   alert("文本框里必须填写内容!")
				   return false;
				   };
			   var typebs1=document.getElementById("typebs1").value;
			 
			   $.ajax({
				    url:"<%=basePath%>template/verifyTypebs",    //请求的url地址
				    dataType:"json",   //返回格式为json
				  //  async:true,//请求是否异步，默认为异步，这也是ajax重要特性
				    data:{"typebs":txt.value,
				    		"typebs1":typebs1
				    		},
				    
				    type:"GET",   //请求方式
				    success:function(data){
				        //请求成功时处理
				        if (data["message"]=="error") {
				        	alert("此类型已存在请重新填写");
				        	document.getElementById("typebs").style.borderStyle="solid";//边框样式
				        	document.getElementById("typebs").style.borderColor="#ff0000";//边框颜色
				        	document.getElementById("typebs").style.borderWidth="1px";//边框宽度
				        	$("#typebs").css({"color":"red"});
				        	document.getElementById("result").value="error";
				        	return false;
						}else{
							alert("此类型可以使用");
							document.getElementById("typebs").style.borderStyle="solid";//边框样式
				        	document.getElementById("typebs").style.borderColor="#00ff01";//边框颜色
				        	document.getElementById("typebs").style.borderWidth="1px";//边框宽度
				        	$("#typebs").css({"color":"#00ff01"});
							document.getElementById("result").value="success";
							return true;
						}
				    }
				  
				});
			   
			}
		
		
		
		</script>
	
</body>
</html>
 
 
 
 