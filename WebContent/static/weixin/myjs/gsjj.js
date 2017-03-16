
 $(document).ready(function(){
	var cookie=  $.AMUI.utils.cookie;
	var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid"); 
	$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {  
        	
           var  locat=data["url"];
           var factoryid=data["factoryid"];
		//公司简介显示
		$.ajax(locat+'/clientIndex/index?factoryid='+factoryid,{
	      			dataType:'json',
	      			type:'get',
	      			timeout:10000,
	      				
	      			success:function(data){
	      				var finallist ="";
	      				for(i=0;i<=data["jsonactivitys"].length-1;i++){
	      					finallist  =finallist+"<li class='am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-top'>"
							        +"<div class='am-list-thumb am-u-sm-12'>"
							          +" <a >"
							          +"   <img src=\"http://www.hnzxtech.cn/wxplatform/uploadFiles/uploadImgs/"+data["jsonactivitys"][i]["path"]+"\" />"
							         +"  </a>"
							         +"</div>"
							         +"<div class='am-list-main'>"
							          +"   <h3 class='am-list-item-hd'>"
							          +"   	<a href='' class=''>"+data["jsonactivitys"][i]["title"]+"</a></h3>"
							          +"   <div class='am-gs-text'>"+data["jsonactivitys"][i]["context"]+"</div>"
							   +"      </div>"
							   +"    </li>";
	      				}
	      				
	      				$("#add_li").append(finallist);
	      			},
	      			error:function(){
	      				swal("服务或网络异常，稍后再试！");
	      			}
	      	});
      	
	}) ;
      		
});


