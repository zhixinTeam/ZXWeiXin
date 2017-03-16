jQuery(function() {
 	
});

function save(){
	var cookie=  $.AMUI.utils.cookie;
	var username = $("#doc-vld-name-2").val();
	var password = $("#doc-vld-pwd-1").val();
	var password2 = $("#doc-vld-pwd-2").val();
	var email=$("#doc-vld-email-2-1").val();
	var phone=$("#doc-vld-phone-1").val();
	if(username==""||password==""||password2==""||email==""||phone==""){
			$("#doc-vld-msg").submit();
			return false;
	}else{
		if(username.length<3||phone.length!=11){
			$("#doc-vld-msg").submit();
			return false;
		}else{
  	$.getJSON ("static/shop_pc/json/locat.json", function (data) {
			var  locat=data["url"];
	        $.ajax(locat+"/shop/register", {
	            type: "POST",
	            xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            data: {
	            	username: username,
	                password: password,
	                email: email,
	                phone:phone
	            },
	            crossDomain: true,
	            success: function(data, status, xhr) {
						if("success" == data["msg"]){
								swal("Good","注册成功","success");
								window.location.href=locat+"/shop/login_toLogin";
						}
	            },
	            error:function(data){
	            	swal("","服务异常！","error");
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