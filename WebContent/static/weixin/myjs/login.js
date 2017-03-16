


function severCheck() {
        var loginname = $("#loginname").val();
		var password = $("#password").val();
		var companyid =$("#companyid").val();
		var cookie=  $.AMUI.utils.cookie;
    	var time=new Date();
    	time=new Date(time.valueOf() +7*24*60*60*1000);
    	cookie.set("companyid",companyid,time,"/");
		if(loginname==""||password==""){
					$("#doc-vld-msg").submit();
					return false;
		 }	
		if(companyid==""||companyid==null){
			swal("未获取公众号信息，请重新由集团公众号进入！");
			return false;
		}
		var id = "body-loading";
  		$(window).loading({action:"open",id:id,font:40}); 
		var code = "qqbigbug"+loginname+",zx,"+password;
		//static/weixin/json/locat.json
		$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
			var  locat=data["url"];
			var factoryid=data["factoryid"];
	        $.ajax(locat+"/clientUser/login", {
	            type: "POST",
	            xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            data: {
	                KEYDATA: code,
	                factoryid: factoryid,
	                companyid:companyid
	            },
	            crossDomain: true,
	            success: function(data, status, xhr) {
	            		var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);
			        	
			        	cookie.set("loginname",loginname,time,"/");
			        	cookie.set("password",password,time,"/");
			        	cookie.set("companyid",companyid,time,"/");
			        	cookie.set("factoryid",factoryid,time,"/");
						if("success" == data.result){
								window.location.href=locat+"/clientUser/touser?companyid="+data.companyid;
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
 

jQuery(function() {
		var cookie=  $.AMUI.utils.cookie;
		var loginname = cookie.get("loginname");
		var password = cookie.get("password");
		if (typeof(loginname) != "undefined"
				&& typeof(password) != "undefined") {
			$("#loginname").val(loginname);
			$("#password").val(password);
		}
		});
		


/*function logout(){
	var flag=true;
	swal({
		title: "您确定要退出吗？",
		text: "",
		type: "warning",
		showCancelButton: true,
		closeOnConfirm: true,
		cancelButtonText: "取消",
		confirmButtonText: "确定",
		confirmButtonColor: "#ec6c62"
	}, function() {
		flag=false;
		alert(flag);
	})
	alert(flag);
	if(flag)
		WeixinJSBridge.call('closeWindow');
}*/
function toUser(){
	 var cookie=  $.AMUI.utils.cookie;
		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
		$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
			var locat = data["url"];
			//如果没有登录。先进行登录
			window.location.href=locat+"/clientUser/touser?companyid="+companyid;

		});
	
}

function go_back(){
	 window.history.go(-1);
	}	

function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}