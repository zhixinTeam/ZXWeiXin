<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <!DOCTYPE html>
    <html lang="en">
    <head>
    <base href="<%=basePath%>">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
    
        <title>待装车辆查询</title>
		<script src="static/loading/jquery.loading.js"></script>
		<script src="static/loading/jquery.2.14.min.js"></script>
		<link rel="stylesheet" href="static/loading/jquery.loading.css" type="text/css">
         <link rel="stylesheet" href="static/muselect/docs/css/bootstrap-3.3.2.min.css" type="text/css">
	 
		 <link rel="stylesheet" href="static/bootstrap-select-news/dist/css/bootstrap-select.css">
		 <script type="text/javascript" src="static/muselect/docs/js/jquery-2.1.3.min.js"></script>
		 <script type="text/javascript" src="static/muselect/docs/js/bootstrap-3.3.2.min.js"></script>
		  <script type="text/javascript" src="static/bootstrap3.0.3/bootstrap.min.js"></script> 
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/bootstrap-select.js"></script>
		 <script type="text/javascript" src="static/bootstrap-select-news/dist/js/i18n/defaults-zh_CN.js"></script>
		<script src="static/js/bootbox.js"></script> 
        <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    
        <style>
            .bs-docs-home
            {
            background-color: #FFFFFF;
            background-image: url(line.png);
            }
    </style>
    
     <script type="text/javascript">
     	function query_car(){
     		var factoryid =$('#factoryid').val();
     		if( factoryid==""){
				 bootbox.alert("请选择工厂", function () {
				      });
				 $("#factoryid").focus();
				 return false;
			 }
     		//打开
			var id = "red-loading";
     		alert("+++");
     		alert($("#red"));
			//$("#red").loading({action:"open",id:id,font:10});
			alert($("#red").loading({action:"open",id:id,font:10}));
			alert("dds");
			 <%-- url = "<%=basePath%>tool/query_car?tm="+new Date().getTime();
			  $.ajax({
					type: "POST",
					url: url,
			    	data: {factoryid:factoryid},
					dataType:'json',
					cache: false,
					success: function(data){
						
						var html_tr="";
						var html_class ="";
						for(var i=0;i<data["listcars"].length;i++){
							if(i%2 ==0)
								html_class="active"
							else
								html_class="success"	
							html_tr+="<tr class="+html_class+">"+
                           "<td width=\"50%\">"+data["listcars"][i]["goodsname"]+"</td>"+
                          " <td>"+data["listcars"][i]["vehicle_Lane"]+"</td>"+
							"   <td>"+data["listcars"][i]["car_num"]+"</td>"
                      " </tr>";
                      }
						 $("#add_tr").html(""); 
	                     $("#add_tr").html(html_tr);
						 
					}
				});  
			 --%>
			var time = setTimeout(function(){
				//关闭
				$("#red").loading({
					
					action:"close",id:id
					
				});
			},5000);
			
			
     	}
     </script>
</head>

<body class="bs-docs-home">

<div id="red" style="height:300px;width:50%;background:red;padding:10px;">
		测试~~~
	</div>
    <div class="container theme-showcase" >
        <h1 style=" line-height:2em;"> </h1><br />
      
        <div class="row">
            <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><strong>待装车辆查询结果</strong></h3>
                </div>
                
							
                <div class="panel-body">
                   
              		<form role="form" name="form1">
							
							<div class="form-group">
                               <label for="IDCard">请选择工厂:</label><br>
		       					<select id="factoryid" class="selectpicker"   >
	        						<c:forEach items="${factoryList}" var="factory">
										<option value="${factory.id }" >${factory.factoryname }</option>
									</c:forEach>
						        </select> <button class="btn btn-default" type="button" onclick="query_car();" >查询</button>
                         	</div>
							
                           
        				</form>
                       
                           <table  border="0" cellspacing="0" cellpadding="0" class="table">
                               <tr class=" label-primary">
                                   <th scope="col" width="50%" ><span style="color:white">物料名称</span></th>
                                   <th scope="col"><span style="color:white">开放通道</span></th>
								   <th scope="col"><span style="color:white">排队车辆</span></th>
                               </tr>
                               <tbody id="add_tr">
                               
                               </tbody>
                               <!-- <tr class="active">
                                   <td width="50%">P.C32.5R袋装</td>
                                   <td>3</td>
								   <td>4</td>
                               </tr>
                               <tr class="success">
                                   <td>P.C32.5R袋装</td>
                                   <td>2</td>
								   <td>3</td>
                               </tr>
                              <tr class="active">
                                   <td>P.C65.5R袋装</td>
                                   <td>1</td>
								   <td>0</td>
                               </tr> -->
                           </table>
                           
                   </div>
               </div>
           </div>
     
           <div class="col-sm-3"></div>
        </div>
    </div> <!-- 
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script> -->
    <script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
</body>
</html>