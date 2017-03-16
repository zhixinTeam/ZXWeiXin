

 function save(){
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
  		$.getJSON ("static/shop_pc/json/locat.json", function (data) {
		var locat=data["url"];
		
		 $.ajax(locat+"/shop/updatePassword", {
	            type: "post",
	            xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            data: {
	                "password": password
	            },
	            crossDomain: true,
	            success: function(data, status, xhr) {
	           /* 	var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);*/
			   		if(data["msg"]=="success"){
			   			swal("Good","修改成功","success");
			   			window.location.href=locat+"/shop/login_toLogin";
			   		}else{
			   			swal("请先登录再修改");
			   			window.location.href=locat+"/shop/login_toLogin";
			   		}
					
					
    	
	            },
				error:function(data){
					/*var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);*/
	            	swal("服务异常","","error");
	            	window.location.href=locat+"/shop/toindex";
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