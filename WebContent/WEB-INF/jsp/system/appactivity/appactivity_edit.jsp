<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	$(document).ready(function() {

	});

	//保存
	function edit() {

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
	
	<form class="form-horizontal" action="appactivity/${msg}" name="comForm"
		id="comForm" method="post" enctype="multipart/form-data">

		<input type="hidden" name="currentPage" id="currentPage"
			value="${pd.currentPage }" />
		<div id="zhongxin">
			<fieldset>
				<div id="legend" class="">
					<legend
						style=" text-align: center; color: #ffffff; text-shadow: 1px 1px 1px #444;">修改主页宣传</legend>
				</div>






				<div class="control-group">

					<!-- Text input-->
					<label class="control-label" for="input01">主题</label>
					<div class="controls">
						<input placeholder="" style="width: 300px" name="title"
							id="miaosu" value="${pd.appactivity.title }" class="input-xlarge"
							type="text">
						<p class="help-block"></p>
					</div>
				</div>





				<%-- <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">日期</label>
          <div class="controls">
          <input class="span10 date-picker" name="startdate" id="startdate" value="${pd.startdate}" type="text" data-date-format="yyyy-mm-dd hh24:mi" readonly="readonly"  placeholder="开始日期"/>
          <p class="help-block"></p>
          </div>
        </div>
         --%>
				<div class="control-group">

					<!-- Text input-->
					<label class="control-label" for="input01">图片</label>
					<div class="controls">
						<input placeholder="" name="file" id="file" class="input-xlarge"
							type="file" value="${pd.appactivity.picname} ">
						<p class="help-block"></p>
						<img alt=""
							src="<%=basePath%>uploadFiles/uploadImgs/${pd.appactivity.path}"
							style="display: block; max-width: 100%; width: 200px; height: 150px; float: left;" />

					</div>
				</div>




				<div class="control-group">

					<!-- Textarea -->
					<label class="control-label">内容</label>
					<div class="controls">
						<div class="textarea">
							<textarea rows="5" cols="100" style="width: 300px" name="context"
								id="context" class="">${pd.appactivity.context}</textarea>
						</div>
					</div>
				</div>


				<div class="control-group">

					<!-- Button -->
					<div class="controls">
						<a class="btn btn-mini btn-primary" onclick="edit();">保存</a> <a
							class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
					</div>

				</div>


			</fieldset>
		</div>
	</form>
	<div id="zhongxin2" class="center" style="display: none">
		<br />
		<br />
		<br />
		<br />
		<br />
		<img src="static/images/jiazai.gif" /><br />
		<h4 class="lighter block green">提交中...</h4>
	</div>


	<!-- 引入 -->
	<!-- <script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");
	</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	下拉框
	<script type="text/javascript"
		src="static/js/bootstrap-datepicker.min.js"></script> -->
	<!-- 日期框 -->
	<script type="text/javascript">
		$(function() {

			//单选框
			/* $(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen({
				allow_single_deselect : true
			});

			//日期框
			$('.date-picker').datepicker(); */

		});
	</script>

</body>
</html>