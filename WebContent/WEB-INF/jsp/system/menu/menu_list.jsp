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
	
			
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
		<thead>
		<tr>
			<th class="center"  style="width: 50px;">序号</th>
			<th class='center'>名称</th>
			<th class='center'>资源路径</th>
			<th class='center' style='width:50px;'>排序</th>
			<th class='center'>操作</th>
		</tr>
		</thead>
		<c:choose>
			<c:when test="${not empty menuList}">
				<c:forEach items="${menuList}" var="auth" varStatus="vs">
				<tr id="tr${auth.id }">
				<td class="center">${vs.index+1}</td>
				<td class='center' ><i class="${auth.iconcls }">&nbsp;</i>${auth.authname }&nbsp;
					<c:if test="${auth.authtype == '1' }">
					<span class="label label-success arrowed">系统</span>
					</c:if>
					<c:if test="${auth.authtype != '1' }">
					<span class="label label-important arrowed-in">业务</span>
					</c:if>
				</td>
				<td>${auth.authpath == '#'? '': auth.authpath}</td>
				<td class='center'>${auth.authorder }</td>
				<td style="width: 25%;">
				<a class='btn btn-mini btn-warning' onclick="openClose('${auth.id }',this,${vs.index })" >展开</a>
				<a class='btn btn-mini btn-purple' title="图标" onclick="editTb('${auth.id }')" ><i class='icon-picture'></i></a>
				<a class='btn btn-mini btn-info' title="编辑" onclick="editmenu('${auth.id }')" ><i class='icon-edit'></i></a>
				<a class='btn btn-mini btn-danger' title="删除"  onclick="delmenu('${auth.id }',true)"><i class='icon-trash'></i></a>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
				<td colspan="100">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
			
		<div class="page_and_btn">
		<div>
			&nbsp;&nbsp;<a class="btn btn-small btn-success" onclick="addmenu();">新增</a>
		</div>
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
		</script><!-- 确认窗口 -->
		<!-- 引入 -->
		<!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		
		<!--  delete start -->
		
			<!-- 
			<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script>
			
			<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
				<script src="static/js/ace.min.js"></script>	
			<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
			<script src="static/js/ace-elements.min.js"></script>
			 
			 
			 -->
		
		
		<!--  delete end -->
		<!-- 上下页 -->
	<script type="text/javascript">
	$(top.hangge());	
	
	//新增
	function addmenu(){
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="新增菜单";
		 diag.URL = '<%=basePath%>auth/toAdd';
		 diag.Width = 223;
		 diag.Height = 256;
		 diag.CancelEvent = function(){ //关闭事件
			if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				top.jzts(); 
				setTimeout("location.reload()",100);
			}
			diag.close();
		 };
		 diag.show();
	}
	
	//修改
	function editmenu(menuId){
		 top.jzts();
	   	 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="编辑菜单";
		 diag.URL = '<%=basePath%>auth/toEdit?AUTH_ID='+menuId;
		 diag.Width = 223;
		 diag.Height = 256;
		 diag.CancelEvent = function(){ //关闭事件
			if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				top.jzts(); 
				setTimeout("location.reload()",100);
			}
			diag.close();
		 };
		 diag.show();
	}
	
	//编辑顶部菜单图标
	function editTb(menuId){
		 top.jzts();
	   	 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="编辑图标";
		 diag.URL = '<%=basePath%>auth/toEditicon?AUTH_ID='+menuId;
		 diag.Width = 530;
		 diag.Height = 150;
		 diag.CancelEvent = function(){ //关闭事件
			if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				top.jzts(); 
				setTimeout("location.reload()",100);
			}
			diag.close();
		 };
		 diag.show();
	}
	//删除菜单
	//删除
		<%-- function delmenu(menuId,isParent){
		alert("******");
			if(isParent){
				alert("isparent")
				alert(bootbox);
				bootbox.confirm("确定要删除该菜单吗？其下子菜单将一并删除！", function(result) {
					if(result) {
						top.jzts();
						var url = "<%=basePath%>auth/del?authid="+menuId+"&guid="+new Date().getTime();
						$.get(url,function(data){
							top.jzts();
							document.location.reload();
						});
						
						
					}
				});
			}else{
				bootbox.confirm("确定要删除该菜单吗？", function(result) {
					if(result) {
						top.jzts();
						var url = "<%=basePath%>auth/del?authid="+menuId+"&guid="+new Date().getTime();
						$.get(url,function(data){
							top.jzts();
							document.location.reload();
						});
						
						
					}
				});
			}
		}
	 --%>
	
	function delmenu(menuId,isParent){
		
		if(isParent){
			bootbox.confirm("确定要删除该菜单吗？其下子菜单将一并删除！", function(result) {
				if(result) {
					
					top.jzts();
					var url = "<%=basePath%>auth/del?authid="+menuId+"&guid="+new Date().getTime();
					$.get(url,function(data){
						top.jzts();
						document.location.reload();
					});
					
				}
			});
		}else{
			bootbox.confirm("确定要删除该菜单吗？", function(result) {
				if(result) {
					
					top.jzts();
					var url = "<%=basePath%>auth/del?authid="+menuId+"&guid="+new Date().getTime();
					$.get(url,function(data){
						top.jzts();
						document.location.reload();
					});
					
				}
			});
		}
		<%-- alert(flag);
		if(flag){
			top.jzts();
			var url = "<%=basePath%>auth/del?authid="+menuId+"&guid="+new Date().getTime();
			$.get(url,function(data){
				top.jzts();
				document.location.reload();
			});
		} --%>
	
		
	}
	
	function openClose(authID,curObj,trIndex){
		var txt = $(curObj).text();
		if(txt=="展开"){
			$(curObj).text("折叠");
			$("#tr"+authID).after("<tr id='tempTr"+authID+"'><td colspan='5'>数据载入中</td></tr>");
			if(trIndex%2==0){
				$("#tempTr"+authID).addClass("main_table_even");
			}
			var url = "<%=basePath%>auth/sub?AUTH_ID="+authID+"&guid="+new Date().getTime();
			$.get(url,function(data){
				if(data.length>0){
					var html = "";
					$.each(data,function(i){
						html = "<tr style='height:24px;line-height:24px;' name='subTr"+authID+"'>";
						html += "<td></td>";
						html += "<td><span style='width:80px;display:inline-block;'></span>";
						if(i==data.length-1)
							html += "<img src='static/images/joinbottom.gif' style='vertical-align: middle;'/>";
						else
							html += "<img src='static/images/join.gif' style='vertical-align: middle;'/>";
						html += "<span style='width:100px;text-align:left;display:inline-block;'>"+this.authname+"</span>";
						html += "</td>";
						html += "<td>"+this.authpath+"</td>";
						html += "<td class='center' style='width:50px;'>"+this.authorder+"</td>";
						html += "<td><a class='btn btn-mini btn-info' title='编辑' onclick='editmenu(\""+this.id+"\")'><i class='icon-edit'></i></a> <a class='btn btn-mini btn-danger' title='删除' onclick='delmenu(\""+this.id+"\",false)'><i class='icon-trash'></i></a></td>";
						html += "</tr>";
						$("#tempTr"+authID).before(html);
					});
					$("#tempTr"+authID).remove();
					if(trIndex%2==0){
						$("tr[name='subTr"+authID+"']").addClass("main_table_even");
					}
				}else{
					$("#tempTr"+authID+" > td").html("没有相关数据");
				}
			},"json");
		}else{
			$("#tempTr"+authID).remove();
			$("tr[name='subTr"+authID+"']").remove();
			$(curObj).text("展开");
		}
	}
</script>

		
	</body>
</html>

