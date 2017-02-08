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
	
			<!-- 检索  -->
			<form action="factory/listfacs" method="post" name="userForm" id="userForm">
			<table border='0'>
				<tr>
				
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="facname" value="${pd.facname }" placeholder="这里输入工厂名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="com_id" id="com_id" data-placeholder="请选择集团" style="vertical-align:top;width: 120px;">
						<option value=""></option>
						<c:forEach items="${listcoms}" var="com">
							<option value="${com.id }" <c:if test="${pd.com_id==com.id}">selected</c:if>>${com.companyname}</option>
						</c:forEach>
					  	</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="STATUS" id="STATUS" data-placeholder="状态" style="vertical-align:top;width: 79px;">
						<option value=""></option>
						<option value="1" <c:if test="${pd.STATUS == '1' }">selected</c:if> >正常</option>
						<option value="0" <c:if test="${pd.STATUS == '0' }">selected</c:if> >停止</option>
						</select>
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
					
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
						<th>工厂ID</th>
						<th>工厂名称</th>
						<th>服务地址</th>
						<th>服务参数</th>
						<th><i class="icon-time hidden-phone"></i>到期日期</th>
						<th>集团名称</th>
						<th>工厂管理员</th>
						<th class="center">状态</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty pagebeanlist}">
						<c:forEach items="${pagebeanlist}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.id}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td>${var.id}</td>
										<td>${var.factoryname}</td>
										<td>${var.serviceurl}</td>
										<td>${var.serviceparam}</td>
										<td>${var.enddate}</td>
										<td>${var.companyname}</td>
										<td>${var.adminname}</td>
										<c:if test="${var.status == 1  }">
				<td  class='center'><label><input type='checkbox' class='ace-switch ace-switch-3' checked='checked' 
				onclick="kf_qx1('${var.id}','${var.status }');" /><span class='lbl'></span></label></td>
				</c:if>
				<c:if test="${var.status != 1  }">
				<td  class='center'><label><input type='checkbox' class='ace-switch ace-switch-3'   
				 onclick="kf_qx1('${var.id}','${var.status }');"/><span class='lbl'></span></label></td>
				</c:if>
								<td style="width: 30px;" class="center">
										
										<div>
											<li><a style="cursor:pointer;" title="编辑" onclick="editservice('${var.id}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
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
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			
			<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;"><ul>
	<li><a>共<font color=red>${pd.pagebean.recordCount}</font>条</a></li>
	<li><input type="number" value="" id="toGoPage" style="width:50px;text-align:center;float:left" placeholder="页码"/></li>
	<li style="cursor:pointer;"><a onclick="toTZ();"  class="btn btn-mini btn-success">跳转</a></li>
	<c:if test="${pd.pagebean.currentPage=='1'}"><li><a>首页</a></li></c:if>
	<c:if test="${pd.pagebean.currentPage !='1'}"><li><a onclick="nextPage('1');"  style="cursor:pointer;">首页</a></li></c:if>
	
	<c:if test="${pd.pagebean.currentPage !='1'}">
	<li><a onclick="nextPage('${pd.pagebean.currentPage-1 }');"    style="cursor:pointer;" >上页</a></li>
	</c:if>
	<c:if test="${pd.pagebean.currentPage =='1'}">
	<li><a >上页</a></li>
	</c:if>
	
<li><a><font color='#808080'>${pd.pagebean.currentPage}</font></a></li>


	<c:if test="${pd.pagebean.currentPage !=pd.pagebean.pageCount}">
		<li><a onclick="nextPage('${pd.pagebean.currentPage+1 }');" style="cursor:pointer;">下页</a></li>
		</c:if>
		<c:if test="${pd.pagebean.currentPage ==pd.pagebean.pageCount}">
		<li><a >下页</a></li>
		</c:if>



	<c:if test="${pd.pagebean.currentPage == pd.pagebean.pageCount}"><li><a>尾页</a></li></li></c:if>
	<c:if test="${pd.pagebean.currentPage != pd.pagebean.pageCount}"><li><a onclick="nextPage('${pd.pagebean.pageCount }');" style="cursor:pointer;">尾页</a></li></li></c:if>
	
	<li><a>第${pd.pagebean.currentPage}页</a></li>
	<li><a>共${pd.pagebean.pageCount}页</a></li>
	
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
		
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script> <!--下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<!-- 确认窗口 -->
		<!-- 引入 -->
		
		<!-- <script type="text/javascript" src="static/js/myjs/system/factory/factorys_list.js"></script> -->
		<!-- delete start -->
		 
		 
		 <!--<script src="static/js/ace-elements.min.js"></script> 
		 	<script src="static/js/ace.min.js"></script>
		 	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>	
		 		<script type="text/javascript" src="static/js/jquery.tips.js">	
		 		<script type="text/javascript" src="static/js/bootbox.min.js"></script>
		 		 -->
		 
		 
		<!-- delete end -->
		<script type="text/javascript">
		
		function kf_qx1(id,status){
			var url = "<%=basePath%>company/editstatus?factoryid="+id+"&status="+status+"&guid="+new Date().getTime();
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
		
		
		
		
		//修改部门工厂
		function editservice(factoryId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="修改工厂";
			 diag.URL = "http://localhost:8080/wxplatform/company/goEditFac?factoryId="+factoryId+"&currentPage="+$("#nowpage").val();
			 diag.Width = 245;
			 diag.Height = 435;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location.reload()",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
				
		}
			
		
		
		
		
		
		
		
		</script>
	</body>
</html>

