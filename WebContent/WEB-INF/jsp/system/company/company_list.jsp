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
			<form action="company/listcoms" method="post" name="comForm" id="comForm">
			<input type="hidden"  name="nowpage" id="nowpage"  value="${pd.pagecompany.currentPage }" >
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="COMPANYNAME" value="${pd.COMPANYNAME}" placeholder="这里输入集团名" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="STATUS" id="STATUS" data-placeholder="状态" style="vertical-align:top;width: 79px;">
						<option value=""></option>
						<option value="1" <c:if test="${pd.STATUS == '1' }">selected</c:if> >正常</option>
						<option value="0" <c:if test="${pd.STATUS == '0' }">selected</c:if> >停止</option>
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
						<th>公司名称</th>
						<th>公共账号</th>
						<th>AppID</th>
						<th>SecrectID</th>
						<th>OriginalID</th>
						<th>创建时间</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty pagebeanlist}">
						<c:forEach items="${pagebeanlist}" var="com" varStatus="vs">
									
							<tr id="tr${com.id }">
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${com.id }" id="" alt=""/><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td>
								<c:if test="${com.status == '1' }">
								<span class="label label-success arrowed" >服务正常</span>
								</c:if>
								<c:if test="${com.status != '1' }">
								<span class="label label-important arrowed-in">服务关闭</span>
								</c:if>
								${com.companyname }
								</td>
								<td><a>${com.wechatsub }</a></td>
								<td><a>${com.appid }</a></td>
								<td><a>${com.secrectid }</a></td>
								<td>${com.originalID }</td>
								<td>${com.createdate}</td>
								<td style="width: 60px;">
									<div class='hidden-phone visible-desktop btn-group'>
											<a class='btn btn-mini btn-warning' onclick="openClose('${com.id }',this,${vs.index })" >查看工厂</a>
											 <a class='btn btn-mini btn-info' title="修改" onclick="editCom('${com.id }');"><i class='icon-edit'></i></a>
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
						<li><a>共<font color=red>${pd.pagecompany.recordCount}</font>条</a></li>
						<li><input type="number" value="" id="toGoPage" style="width:50px;text-align:center;float:left" placeholder="页码"/></li>
						<li style="cursor:pointer;"><a onclick="toTZ();"  class="btn btn-mini btn-success">跳转</a></li>
						<c:if test="${pd.pagecompany.currentPage=='1'}"><li><a>首页</a></li></c:if>
						<c:if test="${pd.pagecompany.currentPage !='1'}"><li><a onclick="nextPage('1');"  style="cursor:pointer;">首页</a></li></c:if>
						
						<c:if test="${pd.pagecompany.currentPage !='1'}">
						<li><a onclick="nextPage('${pd.pagecompany.currentPage-1 }');"    style="cursor:pointer;" >上页</a></li>
						</c:if>
						<c:if test="${pd.pagecompany.currentPage =='1'}">
						<li><a >上页</a></li>
						</c:if>
						
					<li><a><font color='#808080'>${pd.pagecompany.currentPage}</font></a></li>
					
					
						<c:if test="${pd.pagecompany.currentPage !=pd.pagecompany.pageCount}">
							<li><a onclick="nextPage('${pd.pagecompany.currentPage+1 }');" style="cursor:pointer;">下页</a></li>
							</c:if>
							<c:if test="${pd.pagecompany.currentPage ==pd.pagecompany.pageCount}">
							<li><a >下页</a></li>
							</c:if>
					
					
					
						<c:if test="${pd.pagecompany.currentPage == pd.pagecompany.pageCount}"><li><a>尾页</a></li></li></c:if>
						<c:if test="${pd.pagecompany.currentPage != pd.pagecompany.pageCount}"><li><a onclick="nextPage('${pd.pagecompany.pageCount }');" style="cursor:pointer;">尾页</a></li></li></c:if>
						
						<li><a>第${pd.pagecompany.currentPage}页</a></li>
						<li><a>共${pd.pagecompany.pageCount}页</a></li>
						
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
		 <!-- 上下页 -->
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
		
		
		//菜单权限
		function editRights(ROLE_ID){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag = true;
			 diag.Title = "工厂授权";
			 diag.URL = '<%=basePath%>role/auth?ROLE_ID='+ROLE_ID;
			 diag.Width = 280;
			 diag.Height = 370;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		
		
		function openClose(comID,curObj,trIndex){
			var txt = $(curObj).text();
			if(txt=="查看工厂"){
				$(curObj).text("折叠工厂");
				$("#tr"+comID).after("<tr id='tempTr"+comID+"'><td colspan='9'>数据载入中</td></tr>");
				if(trIndex%2==0){
					$("#tempTr"+comID).addClass("main_table_even");
				}
				var url = "<%=basePath%>company/sub?COMPANY_ID="+comID+"&guid="+new Date().getTime();
				var index=0;
				$.get(url,function(data){
					if(data.length>0){
						var html = "<tr name='subTr"+comID+"'><th></th><th>序号</th><th>工厂/部门</th><th>管理员</th><th>服务地址</th><th>服务参数</th><th>状态</th><th>创建时间</th><th class='center'>操作</th></tr>";
						$("#tempTr"+comID).before(html);
						$.each(data,function(i){
							index +=1;
							html = "<tr style='height:24px;line-height:24px;' name='subTr"+comID+"'>";
							html +="<td></td>";
							html += "<td class='center' style='width: 30px;'>"+index+"</td>";
							html += "<td>"+this.factoryname+"</td>";
							html += "<td>"+this.adminname+"</td>";
							html += "<td>"+this.serviceurl+"</td>";
							html += "<td>"+this.serviceparam+"</td>";
							if(this.status =='1')
								html += "<td style='width:60px;' class='center'><label><input type='checkbox' class='ace-switch ace-switch-3' id='qx1${vs.index+1}' checked='checked' onclick='kf_qx1(\""+this.id+"\",\""+this.status+"\")' /><span class='lbl'></span></label></td>";
							else
								html += "<td style='width:60px;' class='center'><label><input type='checkbox' class='ace-switch ace-switch-3' id='qx1${vs.index+1}'   onclick='kf_qx1(\""+this.id+"\",\""+this.status+"\")' /><span class='lbl'></span></label></td>";
							html += "<td class='center' style='width:50px;'>"+this.createdate+"</td>";
							html += "<td><a class='btn btn-mini btn-info' title='修改工厂' onclick='editservice(\""+this.id+"\")'><i class='icon-edit'></i></a>    <a class='btn btn-mini btn-purple' onclick='editRights(\""+this.roleid+"\");'><i class='icon-pencil'></i>授权</a>   </td>";
							html += "</tr>";
							$("#tempTr"+comID).before(html); 
						});
						$("#tempTr"+comID).remove();
						if(trIndex%2==0){
							$("tr[name='subTr"+comID+"']").addClass("main_table_even");
						}
					}else{
						$("#tempTr"+comID+" > td").html("没有相关数据");
					}
				},"json");
			}else{
				$("#tempTr"+comID).remove();
				$("tr[name='subTr"+comID+"']").remove();
				$(curObj).text("查看工厂");
			}
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
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#comForm").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = "<%=basePath%>company/goAdd"+"?currentPage="+$("#nowpage").val();;
			 diag.Width = 1100;
			 diag.Height = 915;
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
		
		//修改部门工厂
		function editservice(factoryId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="修改工厂";
			 diag.URL = "<%=basePath%>company/goEditFac?factoryId="+factoryId+"&currentPage="+$("#nowpage").val();
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
		
		
		//修改
		function editCom(com_id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="修改集团";
			 diag.URL = "<%=basePath%>company/goEditCom?com_id="+com_id+"&currentPage="+$("#nowpage").val();
			 diag.Width = 225;
			 diag.Height = 275;
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

		</script> 
		
	</body>
</html>

