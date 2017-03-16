 $(document).ready(function() {
 	
	$.getJSON ("static/shop_pc/json/locat.json", function (data) {
		var locat = data["url"];
		$.ajax(locat + "/shop/tocommon", {
			type: "GET",
			xhrFields: {
				withCredentials: true,
				useDefaultXhrHeader: false
			},
			crossDomain: true,
			success: function(data, status, xhr) {
				if("error" == data["msg"])
					window.location.href=locat+"/shop/login_toLogin";
				else {
					var finallist = ""
					if(data["msg"]=="err"){
						finallist += "<li>" +
						"<a  class='am-cf'>"+
						"<span class='am-icon-check'></span>未获取到工厂信息</a>"+
							" </li>    ";
						$("#collapse-nav").append(finallist);
					}else{
							for(i = 0; i<=data["list_Json_Doc_Factory"].length - 1; i++) {
								finallist += "<li>" +
								"<a href=\"javascript:void(0);\" class='am-cf' onclick='cwddlb(\"" + data["list_Json_Doc_Factory"][i]["factoryName"] + "\",\"" + data["list_Json_Doc_Factory"][i]["id"] + "\")' " +
								">"+
								"<span class='am-icon-check'></span>"+data["list_Json_Doc_Factory"][i]["factoryName"]+"</a>"+
									" </li>    ";
							}
							$("#collapse-nav").append(finallist);
					}
					

				}
				
				
			},
			error: function(data) {
				swal("","服务异常","error");
				window.location.href=locat+"/shop/login_toLogin";
			}
		});
	});
	
	
	//cookie.set("companyid",companyid,time,"/");
 })
 
 
 function logout(){
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
		$.getJSON ("static/shop_pc/json/locat.json", function (data) {
	
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
					window.location.href=locat+"/shop/login_toLogin";
			},
			error: function() {
				sweetAlert("服务异常", "请稍后再试", "error");
				window.location.href =locat+"/shop/toindex";
				
			}
		});

	});
		
		
	});
	
	
}
 
 function cwddlb(factoryName,factoryid){
		$.getJSON ("static/shop_pc/json/locat.json", function (data) {
			var locat = data["url"];
			//如果没有登录。先进行登录
			window.location.href=locat+"/shop/tocwddlb?factoryid="+factoryid;

		});
			
	}
 
 
 function go_back(){
	 window.history.go(-1);
	}
 
 function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return unescape(r[2]);
		return null;
	}