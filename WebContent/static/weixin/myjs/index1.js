 $(document).ready(function(){
	
	
	var currentPage = GetQueryString("currentPage");
	var pagecount = GetQueryString("pagecount");
	if(currentPage ==null){
		document.getElementById("currentPage").value="1";
	}
	if(pagecount ==null){
		document.getElementById("pagecount").value="1";
	}
	var currentPage = GetQueryString("currentPage");
	var cookie=  $.AMUI.utils.cookie;
	 var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");  
	$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {  
        	
           var  locat=data["url"];
           var factoryid=data["factoryid"];
	$.ajax({
	 			url:locat+'/clientIndex/news_back?factoryid='+factoryid,
      			dataType:'json',
      			type:'get',
      			timeout:10000,
      			xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            data:{
	            	currentPage:currentPage
	            },
	            crossDomain: true,
      			success:function(data){
    				var finallist='';
      				for(i=0;i<=data["jsonpack"].length-1;i++){
     					 finallist=finallist+"<li class='am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left'>"
        				 +"<div class='am-u-sm-4 am-list-thumb'>"
          				 +"<a href="+locat+"/clientIndex/tonewsone?id="+data["jsonpack"][i]["id"]+"&currentPage="+data["currentPage"]+"&pagecount="+data["jsonpack"][i]["pageCount"]+"' class=''>"
           				 +"<img src=\"http://www.hnzxtech.cn/wxplatform/uploadFiles/uploadImgs/"+data["jsonpack"][i]["path"]+"\" />"
         				 +"</a></div><div class='am-u-sm-8 am-list-main'>"
          				 +"<h3 class='am-list-item-hd'>"
            			 +"	<a   href="+locat+"/clientIndex/tonewsone?id="+data["jsonpack"][i]["id"]+"&currentPage="+data["currentPage"]+"&pagecount="+data["jsonpack"][i]["pageCount"]+"' class=''>"+data["jsonpack"][i]["miaosu"]+"</a></h3>"
            			 +"<div class='am-list-item-text'>"+data["jsonpack"][i]["context"]+"</div></div></li>"
      					var pagecount=data["jsonpack"][i]["pageCount"];
      				}
					$("#news_list").append(finallist);
					//alert(data["currentPage"]);
					document.getElementById('currentPage').value=data["currentPage"];
					document.getElementById('pagecount').value=pagecount;
					
      			},
      			error:function(){
      				swal("服务或网络异常，稍后再试！");
      			}
      	});
	
    });  		
});
 
 
 
 
 
		
//新闻追加
function see_more(){
	var currentPage=Number($("#currentPage").val())+1;
	//alert(currentPage+"页码");
	var pagecount=Number($("#pagecount").val());
	//alert(pagecount+"页数");
	var cookie=  $.AMUI.utils.cookie;
	var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");  
	 	$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
	 		
        	var locat=data["url"];
        	var factoryid=data["factoryid"];
        	if(currentPage>pagecount){
        		swal("亲,没有更多了");
        	}else{
        		$("#loading").button('loading');
		 	$.ajax({
		 			url:locat+'/clientIndex/news_list?factoryid='+factoryid,
	      			data:{
						"currentPage":currentPage      				
	      			},
	      			dataType:'json',
	      			type:'get',
	      			timeout:10000,
	      			success:function(data){
	    				var finallist='';
	      				for(i=0;i<=data["jsonpack"].length-1;i++){
	     					 finallist=finallist+"<li class='am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left'>"
	        				 +"<div class='am-u-sm-4 am-list-thumb'>"
	          				 +"<a  href="+locat+"/clientIndex/tonewsone?id="+data["jsonpack"][i]["id"]+"&currentPage="+data["currentPage"]+"&pagecount="+data["jsonpack"][i]["pageCount"]+"' class=''>"
	           				 +"<img src=\"http://www.hnzxtech.cn/wxplatform/uploadFiles/uploadImgs/"+data["jsonpack"][i]["path"]+"\" class='lazy' />"
	         				 +"</a></div><div class='am-u-sm-8 am-list-main'>"
	          				 +"<h3 class='am-list-item-hd'>"
	            			 +"	<a href="+locat+"/clientIndex/tonewsone?id="+data["jsonpack"][i]["id"]+"&currentPage="+data["currentPage"]+"&pagecount="+data["jsonpack"][i]["pageCount"]+"' class=''>"+data["jsonpack"][i]["miaosu"]+"</a></h3>"
	            			 +"<div class='am-list-item-text'>"+data["jsonpack"][i]["context"]+"</div></div></li>"
	      				}
						$("#news_list").append(finallist);
						document.getElementById('currentPage').value=data["currentPage"];
						$("#loading").button('reset');

	      			},
	      			error:function(){
	      				$("#loading").button('reset');
    					swal("网络异常，稍后再试！");
	      			}
	      	});
        	}
           
      	
      	});
}
		
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}