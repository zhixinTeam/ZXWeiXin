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
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
	
	});
	
	//保存
	function save(){
		$("#comForm").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
		
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
	<form class="form-horizontal" action="apppack/${msg}" name="comForm" id="comForm" method="post" enctype="multipart/form-data">
	
		<input type="hidden" name="currentPage" id="currentPage" value="${pd.currentPage }"/>
	<div id="zhongxin">
    <fieldset>
      <div id="legend" class="" align="center">
        <legend class="">新增公司简介</legend>
      </div>
		<div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">描述</label>
          <div class="controls">
            <input placeholder=""   name="miaosu" id="miaosu" value="${pd.miaosu }"  class="input-xlarge" type="text">
            <p class="help-block"></p>
          </div>
        </div>
  
        
          <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">图片</label>
          <div class="controls">
            <input placeholder="" name="file" id="file"  class="input-xlarge" type="file">
            <p class="help-block"></p>
          </div>
        </div>

    <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">内容</label>
          <div class="controls">
            <div class="textarea">
                  <textarea    rows="5" cols="100"   name="context" id="context" value="${pd.context }" class=""> </textarea>
            </div>
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
			
			//单选框
		/* 	$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
 */			
			//日期框
			
			
		}); 
		
		</script>
	
</body>
</html>
