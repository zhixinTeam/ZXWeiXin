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
	<!-- jsp文件头和头部 -->
	<%@ include file="../admin/top.jsp"%>  
	
	</head>
<body>
		
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			
	<form action="template/list" method="post" name="comForm" id="comForm">
			<input type="hidden"  name="nowpage" id="nowpage"  value="${pd.templateslist.currentPage }" >
			 <table>
				<tr>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="com_id" id="com_id" data-placeholder="请选择集团" style="vertical-align:top;width: 200px;">
						<option value=""></option>
						<c:forEach items="${listcoms}" var="com">
							<option value="${com.id }" <c:if test="${pd.com_id==com.id}">selected</c:if>>${com.companyname}</option>
						</c:forEach>
					  	</select>
					</td>
					
					
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();" title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
					
				</tr>
			</table> 
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th>序号</th>
						<th>模板id</th>
						<th>公司名称</th>
						<th>模板标题</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="com" varStatus="vs">
									
							<tr id="${com.id }">
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${com.id }" id="" alt=""/><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td>
								${com.templateid }
								</td>
								<td><a>${com.companyname}</a></td> 
								<td><a>${com.remark }</a></td>
								<td style="width: 60px;">
									<div class='hidden-phone visible-desktop btn-group'>
											 <a class='btn btn-mini btn-danger' title="删除" onclick="del('${com.id }');"><i class='icon-trash'></i>	</a>
											 <a class='btn btn-mini btn-info' title="修改" onclick="newedit('${com.id }');"><i class='icon-edit'></i></a>
									</div>
											 
								</td>
							</tr>
						
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="10" class="center">没有相关数据</td>
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
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;"><ul>
						<li><a>共<font color=red>${pd.templateslist.recordCount}</font>条</a></li>
						<li><input type="number" value="" id="toGoPage" style="width:50px;text-align:center;float:left" placeholder="页码"/></li>
						<li style="cursor:pointer;"><a onclick="toTZ();"  class="btn btn-mini btn-success">跳转</a></li>
						<c:if test="${pd.templateslist.currentPage=='1'}"><li><a>首页</a></li></c:if>
						<c:if test="${pd.templateslist.currentPage !='1'}"><li><a onclick="nextPage('1');"  style="cursor:pointer;">首页</a></li></c:if>
						
						<c:if test="${pd.templateslist.currentPage !='1'}">
						<li><a onclick="nextPage('${pd.templateslist.currentPage-1 }');"    style="cursor:pointer;" >上页</a></li>
						</c:if>
						<c:if test="${pd.templateslist.currentPage =='1'}">
						<li><a >上页</a></li>
						</c:if>
						
					<li><a><font color='#8080'>${pd.templateslist.currentPage}</font></a></li>
					
					
						<c:if test="${pd.templateslist.currentPage !=pd.templateslist.pageCount}">
							<li><a onclick="nextPage('${pd.templateslist.currentPage+1 }');" style="cursor:pointer;">下页</a></li>
							</c:if>
							<c:if test="${pd.templateslist.currentPage ==pd.templateslist.pageCount}">
							<li><a >下页</a></li>
							</c:if>
					
					
					
						<c:if test="${pd.templateslist.currentPage == pd.templateslist.pageCount}"><li><a>尾页</a></li></li></c:if>
						<c:if test="${pd.templateslist.currentPage != pd.templateslist.pageCount}"><li><a onclick="nextPage('${pd.varList.pageCount }');" style="cursor:pointer;">尾页</a></li></li></c:if>
						
						<li><a>第${pd.templateslist.currentPage}页</a></li>
						<li><a>共${pd.templateslist.pageCount}页</a></li>
						
						</li>
					</ul>
					
					</div>
			</td>
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
		<script type="text/javascript" src="static/js/bootbox.min.js"></script>
		<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script> <!--下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<!-- 确认窗口 -->
		<!-- 引入 -->
		
		
		<script type="text/javascript">
		
		
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#userForm").submit();
		}
		
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
		
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = "<%=basePath%>template/goAdd"+"?currentPage="+$("#nowpage").val();;
			 diag.Width = 1100;
			 diag.Height = 915;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location.reload()",100);
					 }else{
						 alert(${page.currentPage});
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		
		
		
		
		
		//修改
		function newedit(id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="修改";
			 diag.URL = '<%=basePath%>template/go_Edit?com_id='+id;
			 diag.Width = 800;
			 diag.Height = 490;
			 diag.CancelEvent = function(){ //关闭事件
				 if('${page.currentPage}' == '0'){
					 top.jzts();
					 setTimeout("self.location=self.location",100);
				 }else{
					 nextPage();
				 }
				diag.close();
			 };
			 diag.show();
		}
		
		function del(id){
			
			var sure=confirm("确定要删除吗?" );
				if(sure==true){
					var url = "<%=basePath%>template/delete"+"?currentPage="+$("#nowpage").val();
					$.ajax({
						   type: "get", cache: false,  
						    url: url,  
						    data:'com_id='+id,  
						    dataType: "text",  
						    success: function (data) { 
						    	nextPage(data);
						    }  
					});
	} 
		
		}
		</script>
	</body>
</html>












