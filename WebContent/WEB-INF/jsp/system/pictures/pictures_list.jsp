﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<!-- jsp文件头和头部 -->
	<%@ include file="../admin/top.jsp"%> 
	<!--查看图片插件 -->
	<link rel="stylesheet" media="screen" type="text/css" href="plugins/zoomimage/css/zoomimage.css" />
    <link rel="stylesheet" media="screen" type="text/css" href="plugins/zoomimage/css/custom.css" />
    
	<!--查看图片插件 -->
	</head> 
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">


	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="pictures/list" method="post" name="Form" id="Form">
			<input type="hidden"  name="nowpage" id="nowpage"  value="${pd.pagepicture.currentPage }" >
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="query_title" type="text" name="query_title" value="${pd.query_title}" placeholder="这里输入标题" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
					
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center" onclick="selectAll()">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th>序号</th>
						<th>图片</th>
						<th>标题</th>
						<th>创建时间</th>
						<th>内容</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.id}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td>
										<a href="<%=basePath%>uploadFiles/uploadImgs/${var.path}" title="${var.title}" class="bwGal"><img src="<%=basePath%>uploadFiles/uploadImgs/${var.path}" alt="${var.title}" width="100"></a>
										</td>
										<td>${var.title}</td>
										<td>${var.createdate}</td>
										<td>${var.bz}</td>
								<td style="width: 30px;" class="center">
										
										<div>
											<li><a style="cursor:pointer;" title="编辑" onclick="edit('${var.id}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
											<li><a style="cursor:pointer;" title="删除" onclick="del('${var.id}','${var.path}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>
										</div>
								</td>
							</tr>
						
						</c:forEach>
						
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
			
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			
			<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;"><ul>
	<li><a>共<font color=red>${pd.pagepicture.recordCount}</font>条</a></li>
	<li><input type="number" value="" id="toGoPage" style="width:50px;text-align:center;float:left" placeholder="页码"/></li>
	<li style="cursor:pointer;"><a onclick="toTZ();"  class="btn btn-mini btn-success">跳转</a></li>
	<c:if test="${pd.pagepicture.currentPage=='1'}"><li><a>首页</a></li></c:if>
	<c:if test="${pd.pagepicture.currentPage !='1'}"><li><a onclick="nextPage('1');"  style="cursor:pointer;">首页</a></li></c:if>
	
	<c:if test="${pd.pagepicture.currentPage !='1'}">
	<li><a onclick="nextPage('${pd.pagepicture.currentPage-1 }');"    style="cursor:pointer;" >上页</a></li>
	</c:if>
	<c:if test="${pd.pagepicture.currentPage =='1'}">
	<li><a >上页</a></li>
	</c:if>
	
<li><a><font color='#808080'>${pd.pagepicture.currentPage}</font></a></li>


	<c:if test="${pd.pagepicture.currentPage !=pd.pagepicture.pageCount}">
		<li><a onclick="nextPage('${pd.pagepicture.currentPage+1 }');" style="cursor:pointer;">下页</a></li>
		</c:if>
		<c:if test="${pd.pagepicture.currentPage ==pd.pagepicture.pageCount}">
		<li><a >下页</a></li>
		</c:if>



	<c:if test="${pd.pagepicture.currentPage == pd.pagepicture.pageCount}"><li><a>尾页</a></li></li></c:if>
	<c:if test="${pd.pagepicture.currentPage != pd.pagepicture.pageCount}"><li><a onclick="nextPage('${pd.pagepicture.pageCount }');" style="cursor:pointer;">尾页</a></li></li></c:if>
	
	<li><a>第${pd.pagepicture.currentPage}页</a></li>
	<li><a>共${pd.pagepicture.pageCount}页</a></li>
	
	</li>
</ul>

</div></td>
			
			</tr>
		</table>
		</div>
		</form>
	</div>
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		
		<script src="static/js/bootstrap.min.js"></script>
	
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		
		<!-- delete 开始
 		
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script>
		<script type="text/javascript" src="plugins/zoomimage/js/jquery.js"></script>
    	<script type="text/javascript" src="plugins/zoomimage/js/eye.js"></script>
    	<script type="text/javascript" src="plugins/zoomimage/js/utils.js"></script>
    	<script type="text/javascript" src="plugins/zoomimage/js/zoomimage.js"></script>
    	<script type="text/javascript" src="plugins/zoomimage/js/layout.js"></script>
			<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
		
		  	delete  结束	-->
		
		
		<!-- 上下页 -->
		<script type="text/javascript">
			function nextPage(page){ 
				top.jzts();	
				if(true && document.forms[0]){
					var url = document.forms[0].getAttribute("action");
					if(url.indexOf('?')>-1){url += "&currentPage=";}
					else{url += "?currentPage=";}
					url = url + page + "&showCount=10";
					document.forms[0].action = url;
					document.forms[0].submit();
				}else{
					var url = document.location+'';
					if(url.indexOf('?')>-1){
						if(url.indexOf('currentPage')>-1){
							var reg = /currentPage=\d*/g;
							url = url.replace(reg,'currentPage=');
						}else{
							url += "&currentPage=";
						}
					}else{url += "?currentPage=";}
					url = url + page + "&showCount=10";
					document.location = url;
				}
		}
	
		function toTZ(){
			var toPaggeVlue = document.getElementById("toGoPage").value;
			if(toPaggeVlue == ''){
				document.getElementById("toGoPage").value=1;
				return;
				}
			if(isNaN(Number(toPaggeVlue))){
				document.getElementById("toGoPage").value=1;
				return;
				}
			nextPage(toPaggeVlue);
			}
</script>


<script type="text/javascript">

	$(top.hangge());
	
	//检索
	function search(){
		top.jzts();
		$("#Form").submit();
	}
	
	//新增
	function add(){
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="新增";
		 diag.URL = '<%=basePath%>pictures/goAdd';
		 diag.Width = 800;
		 diag.Height = 490;
		 diag.CancelEvent = function(){ //关闭事件
			 if('${page.currentPage}' == '0'){
				 top.jzts();
				 setTimeout("self.location=self.location",100);
			 }else{
				 nextPage(${page.currentPage});
			 }
			diag.close();
		 };
		 diag.show();
	}

	//删除
	function del(Id,path){
		
		bootbox.confirm("确定要删除吗?", function(result) {
			if(result) {
				var url = "<%=basePath%>pictures/delete?picture_id="+Id+"&path="+path+"&tm="+new Date().getTime()+"&currentPage="+$("#nowpage").val();
				$.ajax({
					   type: "get", cache: false,  
					    url: url,  
					    data: "",  
					    dataType: "text",  
					    success: function (data) { 
					    	nextPage(data);
					    }  
				});
				
				
			}
		});
		
		<%-- if(confirm("确定要删除?")){ 
			top.jzts();
			var url = "<%=basePath%>pictures/delete?picture_id="+Id+"&path="+path+"&tm="+new Date().getTime()+"&currentPage="+$("#nowpage").val();
			
			$.ajax({
				   type: "get", cache: false,  
				    url: url,  
				    data: "",  
				    dataType: "text",  
				    success: function (data) { 
				    	nextPage(data);
				    }  
			});
		}  --%>
	}

	//修改
	function edit(Id){
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="编辑";
		 diag.URL = '<%=basePath%>pictures/goEdit?PICTURES_ID='+Id;
		 diag.Width = 600;
		 diag.Height = 465;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 top.jzts();
				setTimeout("self.location.reload()",100);
			}
			diag.close();
		 };
		 diag.show();
	}
		
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			/* //日期框
			$('.date-picker').datepicker();
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true});  */
			
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
		
		
		
		
		</script>
		
	</body>
</html>

