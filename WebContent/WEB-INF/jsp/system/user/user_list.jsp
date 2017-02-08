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
			<form action="user/listUsers.do" method="post" name="userForm" id="userForm">
			<input type="hidden"  name="nowpage" id="nowpage"  value="${pd.pageuser.currentPage }" >
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="USERNAME" value="${pd.USERNAME}" placeholder="这里输入用户名" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="ROLE_ID" id="role_id" data-placeholder="请选择职位" style="vertical-align:top;width: 120px;">
						<option value=""></option>
						<c:forEach items="${roleList}" var="role">
							<option value="${role.id }" <c:if test="${pd.selectroleid==role.id}">selected</c:if>>${role.rolename }</option>
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
						<th>序号</th><!-- 
						<th>编号</th> -->
						<th>用户名</th>
						<th>姓名</th>
						<th>职位</th>
						<th><i class="icon-envelope"></i>邮箱</th>
						<th><i class="icon-time hidden-phone"></i>最近登录</th>
						<th>上次登录IP</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty userList}">
						<c:forEach items="${userList}" var="user" varStatus="vs">
									
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${user.id }" id="" alt="${user.mobile }"/><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td><%-- 
								<td>${user.susernumber }</td> --%>
								<td><a>${user.username }</a></td>
								<td>${user.truename }</td>
								<td>${user.sys_role.rolename}</td>
								<td>${user.email }</td><%-- 
								<td><a title="发送电子邮件" style="text-decoration:none;cursor:pointer;" onclick="sendEmail('772854887@qq.com');">${user.email }&nbsp;<i class="icon-envelope"></i></a></td>
								 --%>
								<td>${user.last_login }</td>
								<td>${user.ip }</td>
								<td style="width: 60px;">
									<div class='hidden-phone visible-desktop btn-group'>
										
										<%-- <a class='btn btn-mini btn-warning' title="发送短信" onclick="sendSms('${user.mobile }');"><i class='icon-envelope'></i></a>
										 --%>
											 <c:if test="${user.parent.id == pd.loginid }">
											 <a class='btn btn-mini btn-info' title="编辑" onclick="editUser('${user.id }');"><i class='icon-edit'></i></a>
											  <a class='btn btn-mini btn-danger' title="删除" onclick="delUser('${user.id }','${user.username }');"><i class='icon-trash'></i></a>
											 </c:if>
											
									</div>
											 <c:if test="${user.parent.id  != pd.loginid }">
											 	无权限
											 </c:if>
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
					<!-- <a title="批量发送电子邮件" class="btn btn-small btn-info" onclick="makeAll('确定要给选中的用户发送邮件吗?');"><i class="icon-envelope-alt"></i></a>
					<a title="批量发送短信" class="btn btn-small btn-warning" onclick="makeAll('确定要给选中的用户发送短信吗?');"><i class="icon-envelope"></i></a>
					 -->
					<!-- <a title="批量删除" class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" ><i class='icon-trash'></i></a>
					 -->
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;"><ul>
	<li><a>共<font color=red>${pd.pageuser.recordCount}</font>条</a></li>
	<li><input type="number" value="" id="toGoPage" style="width:50px;text-align:center;float:left" placeholder="页码"/></li>
	<li style="cursor:pointer;"><a onclick="toTZ();"  class="btn btn-mini btn-success">跳转</a></li>
	<c:if test="${pd.pageuser.currentPage=='1'}"><li><a>首页</a></li></c:if>
	<c:if test="${pd.pageuser.currentPage !='1'}"><li><a onclick="nextPage('1');"  style="cursor:pointer;">首页</a></li></c:if>
	
	<c:if test="${pd.pageuser.currentPage !='1'}">
	<li><a onclick="nextPage('${pd.pageuser.currentPage-1 }');"    style="cursor:pointer;" >上页</a></li>
	</c:if>
	<c:if test="${pd.pageuser.currentPage =='1'}">
	<li><a >上页</a></li>
	</c:if>
	
<li><a><font color='#808080'>${pd.pageuser.currentPage}</font></a></li>


	<c:if test="${pd.pageuser.currentPage !=pd.pageuser.pageCount}">
		<li><a onclick="nextPage('${pd.pageuser.currentPage+1 }');" style="cursor:pointer;">下页</a></li>
		</c:if>
		<c:if test="${pd.pageuser.currentPage ==pd.pageuser.pageCount}">
		<li><a >下页</a></li>
		</c:if>



	<c:if test="${pd.pageuser.currentPage == pd.pageuser.pageCount}"><li><a>尾页</a></li></li></c:if>
	<c:if test="${pd.pageuser.currentPage != pd.pageuser.pageCount}"><li><a onclick="nextPage('${pd.pageuser.pageCount }');" style="cursor:pointer;">尾页</a></li></li></c:if>
	
	<li><a>第${pd.pageuser.currentPage}页</a></li>
	<li><a>共${pd.pageuser.pageCount}页</a></li>
	
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
		<script type="text/javascript" src="static/js/bootbox.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<!-- 确认窗口 -->
		<!-- 引入 -->
		</script><!--提示框-->
		
		<!-- delete 开始
		
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script type="text/javascript" src="static/js/jquery.tips.js">
		<script src="static/js/ace.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		 	 delete 结束 -->
		
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
		
		
		//去发送电子邮件页面
		function sendEmail(EMAIL){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="发送电子邮件";
			 diag.URL = '<%=basePath%>head/goSendEmail.do?EMAIL='+EMAIL;
			 diag.Width = 660;
			 diag.Height = 470;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		
		//去发送短信页面
		function sendSms(phone){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="发送短信";
			 diag.URL = '<%=basePath%>head/goSendSms.do?PHONE='+phone+'&msg=appuser';
			 diag.Width = 600;
			 diag.Height = 265;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		//新增http://woaizd.nat123.net/
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = "<%=basePath%>user/goAddU"+"?currentPage="+$("#nowpage").val();;
			 diag.Width = 225;
			 diag.Height = 415;
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
		
		//修改
		function editUser(user_id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="资料";
			 diag.URL = "<%=basePath%>user/goEditU?USER_ID="+user_id+"&currentPage="+$("#nowpage").val();
			 diag.Width = 225;
			 diag.Height = 415;
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
		
		//删除
		function delUser(userId,msg){
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				if(result) {
					top.jzts(); 
					var url = "<%=basePath%>user/deleteU?USER_ID="+userId+"&tm="+new Date().getTime()+"&currentPage="+$("#nowpage").val();
					
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
		}
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					var emstr = '';
					var phones = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  	
						  	if(emstr=='') emstr += document.getElementsByName('ids')[i].id;
						  	else emstr += ';' + document.getElementsByName('ids')[i].id;
						  	
						  	if(phones=='') phones += document.getElementsByName('ids')[i].alt;
						  	else phones += ';' + document.getElementsByName('ids')[i].alt;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>user/deleteAllU?tm='+new Date().getTime(),
						    	data: {USER_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}else if(msg == '确定要给选中的用户发送邮件吗?'){
							sendEmail(emstr);
						}else if(msg == '确定要给选中的用户发送短信吗?'){
							sendSms(phones);
						}
						
						
					}
				}
			});
		}
		
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//日期框
			$('.date-picker').datepicker();
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
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
		
		//导出excel
		function toExcel(){
			var USERNAME = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var ROLE_ID = $("#role_id").val();
			window.location.href='<%=basePath%>user/excel.do?USERNAME='+USERNAME+'&lastLoginStart='+lastLoginStart+'&lastLoginEnd='+lastLoginEnd+'&ROLE_ID='+ROLE_ID;
		}
		
		//打开上传excel页面
		function fromExcel(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="EXCEL 导入到数据库";
			 diag.URL = '<%=basePath%>user/goUploadExcel.do';
			 diag.Width = 300;
			 diag.Height = 150;
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

