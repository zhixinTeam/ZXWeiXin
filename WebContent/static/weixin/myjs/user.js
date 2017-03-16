$(document).ready(function() {

})

function loginout(){
	swal({
		title: "您确定要退出吗？",
		text: "",
		type: "warning",
		showCancelButton: true,
		closeOnConfirm: false,
		cancelButtonText: "取消",
		confirmButtonText: "确定",
		confirmButtonColor: "#ec6c62"
	}, function() {
		var cookie=  $.AMUI.utils.cookie;
		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
		$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
	
		var locat = data["url"];
		//如果没有登录。先进行登录
		
		$.ajax(locat+'/clientUser/logout', {
			dataType: 'json',
			type: 'get',
			timeout: 100000,
			xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            crossDomain: true,
			success: function(data) {
				if("success" == data["mes"])
					window.location.href=locat+"/clientUser/tologin?companyid="+companyid;
			},
			error: function() {
				sweetAlert("服务异常", "请稍后再试", "error");
				window.location.href =locat+"/clientIndex/toindex";
				
			}
		});

	});
		
		
	});
	
	
}

function cwddlb(factoryid){
	var cookie=  $.AMUI.utils.cookie;
	var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
	$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
		var locat = data["url"];
		//如果没有登录。先进行登录
		window.location.href=locat+"/clientUser/tocwddlb?factoryid="+factoryid;

	});
		
}

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}