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
	<script src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/bootbox.min.js"></script>
	<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
	</head>
<body>	
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="wxuser/listusers" method="post" name="userForm" id="userForm">
			<table border='0'>
				<tr>
				
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="NAMEPY" value="${pd.NAMEPY }" placeholder="这里输入用户名" />
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
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th>序号</th>
						<th>用户昵称</th>
						<th>微信号</th>
						<th>客户号</th>
						<th>邮箱</th>
						<th>电话号码</th>
						<th>关注日期</th>
						<th>查看报表权限</th>
						<!-- <th class="center">状态</th>
						<th class="center">操作</th> -->
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty binduserlist}">
						<c:forEach items="${binduserlist}" var="binduser" varStatus="vs">
									
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${binduser.id }" id="${binduser.id }" alt="${binduser.namepinyin }"/><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td>${binduser.namepinyin }</td>
								<td>${binduser.wxUserName }</td>
								<td>${binduser.suserNumber }</td>
								<td>${binduser.email }</td>
								<td>${binduser.phone }</td>
								<td>${binduser.binddate }</td>
								
								<c:choose>
								<c:when test="${binduser.status!=0}">
								
									<td  class='center'><label><input type='checkbox' class='ace-switch ace-switch-3' checked='checked' 
										onclick="editStatus('${binduser.openid}','${binduser.status }');" /><span class='lbl'></span></label></td>
										
										</c:when>
								<c:otherwise>
									
									<td  class='center'><label><input type='checkbox' class='ace-switch ace-switch-3'   
								 			onclick="editStatus('${binduser.openid}','${binduser.status }');"/><span class='lbl'></span></label></td>
									
								</c:otherwise>
							</c:choose>
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
			
			<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">
			
			<ul>
	<li><a>共<font color=red>${pd.pagecustomer.recordCount}</font>条</a></li>
	<li><input type="number" value="" id="toGoPage" style="width:50px;text-align:center;float:left" placeholder="页码"/></li>
	<li style="cursor:pointer;"><a onclick="toTZ();"  class="btn btn-mini btn-success">跳转</a></li>
	<c:if test="${pd.pagecustomer.currentPage=='1'}"><li><a>首页</a></li></c:if>
	<c:if test="${pd.pagecustomer.currentPage !='1'}"><li><a onclick="nextPage('1');"  style="cursor:pointer;">首页</a></li></c:if>
	
	<c:if test="${pd.pagecustomer.currentPage !='1'}">
	<li><a onclick="nextPage('${pd.pagecustomer.currentPage-1 }');"    style="cursor:pointer;" >上页</a></li>
	</c:if>
	<c:if test="${pd.pagecustomer.currentPage =='1'}">
	<li><a >上页</a></li>
	</c:if>
	
<li><a><font color='#808080'>${pd.pagecustomer.currentPage}</font></a></li>


	<c:if test="${pd.pagecustomer.currentPage !=pd.pagecustomer.pageCount}">
		<li><a onclick="nextPage('${pd.pagecustomer.currentPage+1 }');" style="cursor:pointer;">下页</a></li>
		</c:if>
		<c:if test="${pd.pagecustomer.currentPage ==pd.pagecustomer.pageCount}">
		<li><a >下页</a></li>
		</c:if>



	<c:if test="${pd.pagecustomer.currentPage == pd.pagecustomer.pageCount}"><li><a>尾页</a></li></li></c:if>
	<c:if test="${pd.pagecustomer.currentPage != pd.pagecustomer.pageCount}"><li><a onclick="nextPage('${pd.pagecustomer.pageCount }');" style="cursor:pointer;">尾页</a></li></li></c:if>
	
	<li><a>第${pd.pagecustomer.currentPage}页</a></li>
	<li><a>共${pd.pagecustomer.pageCount}页</a></li>
	
	</li>
</ul>
			
			
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
			$("#userForm").submit();
		}
		
		
		//修改status
		function editStatus(open_id,status){
                	$.ajax({
					    url:"<%=basePath%>wxuser/editStatus",    //请求的url地址
					    dataType:"json",   //返回格式为json
					  //  async:true,//请求是否异步，默认为异步，这也是ajax重要特性
					    data:{"open_id":open_id },
					    type:"GET",   //请求方式
					    success:function(data){
					        //请求成功时处理
					        if (data["message"]=="已开启查看报表权限") {
					        	bootbox.alert("已开启查看报表权限");
					        	$("#"+open_id).text("关闭");
							}else{
								bootbox.alert("已关闭查看报表权限");
								$("#"+open_id).text("打开");
							}
					    }
					  
					});
                
              
		}
			
		
		
		//修改
		function editUser(user_id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="客户审核";
			 diag.URL = '<%=basePath%>wxuser/goEditU?USER_ID='+user_id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
}
				diag.close();
			 };
			 diag.show();
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
		
			 </script>
		
	</body>
</html>

