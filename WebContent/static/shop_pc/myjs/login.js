


function severCheck() {
        var loginname = $("#loginname").val();
		var password = $("#password").val();
		var companyid =$("#companyid").val();
		var cookie=  $.AMUI.utils.cookie;
    	var time=new Date();
    	time=new Date(time.valueOf() +7*24*60*60*1000);
    	//cookie.set("companyid",companyid,time,"/");
		if(loginname==""||password==""){
					$("#doc-vld-msg").submit();
					return false;
		 }	
		var id = "body-loading";
  		$(window).loading({action:"open",id:id,font:40}); 
		var code = "qqbigbug"+loginname+",zx,"+password;
		$.getJSON ("static/shop_pc/json/locat.json", function (data) {
			var  locat=data["url"];
	        $.ajax(locat+"/shop/login", {
	            type: "POST",
	            xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            data: {
	                KEYDATA: code
	            },
	            crossDomain: true,
	            success: function(data, status, xhr) {
	            		var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);
			        	
			        	cookie.set("loginname",loginname,time,"/");
			        	cookie.set("password",password,time,"/");
						if("success" == data.result){
								window.location.href=locat+"/shop/totjsja";
								alert( DateTime.MaxValue );
						}else if("usererror" == data.result){
								swal("用户名或密码错误");
								$("#loginname").focus();
						}else{	
								swal("缺少参数")
								$("#loginname").focus();
						}
	            },
	            error:function(data){
	            	swal("","服务异常","error");
	            }
	        });
    });  
}
 

/*jQuery(function() {
		var cookie=  $.AMUI.utils.cookie;
		var loginname = cookie.get("loginname");
		var password = cookie.get("password");
		if (typeof(loginname) != "undefined"
				&& typeof(password) != "undefined") {
			$("#loginname").val(loginname);
			$("#password").val(password);
		}
		});*/
		







function go_back(){
	 window.history.go(-1);
	}	

function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}