<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basepath%>">
		
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<link rel="stylesheet" href="static/assets/css/font-awesome.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="static/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script src="static/assets/js/ace/elements.fileinput.js"></script>

		<!-- ace scripts -->
		
		
	
	<!-- delete 开始 
		
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/assets/js/ace/ace.js"></script>
		<script type="text/javascript">
			window.jQuery || document.write("<script src='static/assets/js/jquery.js'>"+"<"+"/script>");
		</script>
		
		delete  结束			 -->	
	
	
	<script type="text/javascript">
	
	
	
	//保存
	function save(){
			if($("#title").val()==""){
			$("#title").tips({
				side:3,
	            msg:'请输入标题',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#title").focus();
			return false;
		}
		if(typeof($("#tpz").val()) == "undefined"){
			if($("#tp").val()=="" || document.getElementById("tp").files[0] =='请选择图片'){
				
				$("#tp").tips({
					side:3,
		            msg:'请选图片',
		            bg:'#AE81FF',
		            time:3
		        });
				return false;
			}
		}
		if($("#bz").val()==""){
			$("#bz").tips({
				side:3,
	            msg:'请输入内容',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#bz").focus();
			return false;
		}
		
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
	//过滤类型
	function fileType(obj){
		var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(fileType != '.gif' && fileType != '.png' && fileType != '.jpg' && fileType != '.jpeg'){
	    	$("#tp").tips({
				side:3,
	            msg:'请上传图片格式的文件',
	            bg:'#AE81FF',
	            time:3
	        });
	    	$("#tp").val('');
	    	document.getElementById("tp").files[0] = '请选择图片';
	    }
	}
	
	//删除图片
	function delP(path,picture_id){
		 if(confirm("确定要删除图片？")){
			var url = "pictures/deltp?path="+path+"&picture_id="+picture_id+"&guid="+new Date().getTime();
			/* $.get(url,function(data){
				if(data=="success"){
					alert("删除成功!");
					document.location.reload();
				}
			}); */
			
			$.ajax({
				   type: "get", cache: false,  
				    url: url,  
				    data: "",  
				    dataType: "text",  
				    success: function (data) { 
				    	if(data=="success"){
							alert("删除成功!");
							document.location.reload();
						}
				    }  
			});
		} 
	}
	
	
	function edit_log(id,flaglog){
		var url = "pictures/editflag?picture_id="+id+"&flaglog="+flaglog+"&guid="+new Date().getTime();
		$.ajax({
			   type: "get", cache: false,  
			    url: url,  
			    data: "",  
			    dataType: "text",  
			    success: function (data) { 
			    	//nextPage(data);
			    }  
		});  
}
</script>
	</head>
<body>
	<form action="pictures/${msg }" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="picture_id" id="picture_id" value="${pd.picture_id}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<th>标题:</th>
				<td><input type="text" name="title" id="title" value="${pd.title}" maxlength="32" style="width:95%;" placeholder="这里输入标题" title="标题"/></td>
			</tr>
			<tr>
				<th>图片:</th>
				<td>
					<c:if test="${pd == null || pd.path == '' || pd.path == null }">
					<input type="file" id="tp" name="tp" onchange="fileType(this)"/>
					</c:if>
					<c:if test="${pd != null && pd.path != '' && pd.path != null }">
						<a href="<%=basepath%>uploadFiles/uploadImgs/${pd.path}" target="_blank"><img src="<%=basepath%>uploadFiles/uploadImgs/${pd.path}" width="210"/></a>
						<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.path}','${pd.picture_id }');" />
						<input type="hidden" name="tpz" id="tpz" value="${pd.path }"/>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<th>内容:</th>
				<td><input type="text" name="bz" id="bz" value="${pd.bz}" maxlength="32" style="width:95%;" placeholder="这里输入内容" title="内容"/></td>
			</tr>
			<tr>
				<th>是否为logo:</th>
				<c:if test="${pd.flag == 1  }">
				<td  class='center'><label><input type='checkbox' class='ace-switch ace-switch-3' checked='checked' 
				onclick="edit_log('${pd.picture_id}','${pd.flag }');" /><span class='lbl'></span></label></td>
				</c:if>
				<c:if test="${pd.flag != 1  }">
				<td  class='center'><label><input type='checkbox' class='ace-switch ace-switch-3'   
				 onclick="edit_log('${pd.picture_id}','${pd.flag }');"/><span class='lbl'></span></label></td>
				</c:if>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<!--[if !IE]> -->
		
		<!-- <![endif]-->
		<!--[if IE]>
		<script type="text/javascript">
		 	window.jQuery || document.write("<script src='static/assets/js/jquery1x.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
	
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			//上传
			$('#tp').ace_file_input({
				no_file:'请选择图片 ...',
				btn_choose:'选择',
				btn_change:'更改',
				droppable:false,
				onchange:null,
				thumbnail:false, //| true | large
				whitelist:'gif|png|jpg|jpeg',
				//blacklist:'gif|png|jpg|jpeg'
				//onchange:''
				//
			});
			
		});
		
		</script>
</body>
</html>