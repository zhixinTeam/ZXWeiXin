 
 function save(){
	 var cookie=  $.AMUI.utils.cookie;
		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
		if(companyid==""){
			swal("未获取公众号信息，请重新由集团的公众号进入！");
		}
 	var password = $("#doc-vld-pwd-1").val();
 	var password1=$("#doc-vld-pwd-2").val();
 	if(password==""||password1==""){
 		$("#doc-vld-msg").submit();
			return false;
 	}else{
 		if(password!=password1){

 		}else{
			var id = "body-loading";
  		//$(window).loading({action:"open",id:id,font:40}); 
  		var cookie=  $.AMUI.utils.cookie;
  		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
  		$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
		var locat=data["url"];
		var factoryid=data["factoryid"];
		 $.ajax(locat+"/clientUser/updatePassword", {
	            type: "post",
	            xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            data: {
	                "password": password,
	                "companyid":companyid
	            },
	            crossDomain: true,
	            success: function(data, status, xhr) {
	           /* 	var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);*/
			   		if(data["msg"]=="success"){
			   			swal("修改成功","","success");
			   			window.location.href=locat+"/clientUser/tologin?companyid="+companyid;
			   		}else{
			   			swal("请先登录再修改");
			   			window.location.href=locat+"/clientUser/tologin?companyid="+companyid;
			   		}
					
					
    	
	            },
				error:function(data){
					/*var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);*/
	            	swal("服务异常","","error");
	            	window.location.href=locat+"/clientUser/tologin";
	            }
	           
	            }); 
	          }); 
		}
 		
 	}
 	
 	
 }
 

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}