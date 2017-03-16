jQuery(function() {
 	var cookie=  $.AMUI.utils.cookie;
	var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
	$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
		var locat = data["url"];
		$.ajax(locat + "/clientUser/driver_list2", {
			type: "GET",
			xhrFields: {
				withCredentials: true,
				useDefaultXhrHeader: false
			},
			crossDomain: true,
			success: function(data, status, xhr) {
				if("error" == data["msg"])
					window.location.href=locat+"/clientUser/tologin";
				else {
					var finallist = ""
					for(i = data["list_driver"].length - 1; i >= 0; i--) {
						finallist += "<tr>" +
						" <td class='am-text-center'>" + data["list_driver"][i]["tracknumber"] + "</td>" +
							"<td class='am-text-center'>" + data["list_driver"][i]["phone"] + "</td>" +
							"<td class='am-text-center'>" + data["list_driver"][i]["name"] + "</td>" +
							"  <td class='am-text-center'>" +
							"	<button type=\"button\" class=\"am-btn   am-btn-success am-btn-xs am-round\" onclick='updateDriver(\"" + data["list_driver"][i]["d_id"] + "\")'>修改</button>" +
							"	<button type=\"button\" class=\"am-btn am-fr  am-btn-primary am-btn-xs am-round\" onclick='delDriver(\"" + data["list_driver"][i]["d_id"] + "\")'>删除</button>" +
							"</td>" +
							" </tr>    ";
					}
					$("#table_driver").append(finallist);

				}

			},
			error: function(data) {
				swal("","服务异常！","error");
				window.location.href=locat+"/clientUser/tologin";
			}
		});
	});

});

//删除driver
function delDriver(driverid) {
	swal({
		title: "您确定要删除吗？",
		text: "",
		type: "warning",
		showCancelButton: true,
		cancelButtonText: "取消",
		closeOnConfirm: false,
		confirmButtonText: "确定",
		confirmButtonColor: "#ec6c62"
	}, function() {
		var cookie=  $.AMUI.utils.cookie;
		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
		$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
			var locat = data["url"];
			$.ajax(locat + "/clientUser/delDriver?driverid=" + driverid, {
				type: "GET",
				xhrFields: {
					withCredentials: true,
					useDefaultXhrHeader: false
				},
				crossDomain: true,
				success: function(data, status, xhr) {
					if(data["mes"] == "success") {
						swal("删除成功!", "", "success"); 
						window.location.href=locat+"/clientUser/to_dirver";
					} else if(data["mes"] =="errorr"){
						window.location.href=locat+"/clientUser/tologin";
					}else{
						swal("此司机还有订单，不能删除", "", "error");
					}
				},
				error: function(data) {
					swal("服务异常","","error");
					window.location.href=locat+"/clientUser/tologin";
				}
			});
		});

	});

}

//修改driver
function updateDriver(driverid) {
	var cookie=  $.AMUI.utils.cookie;
	var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
	$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
		var locat = data["url"];
		window.location.href = locat+"/clientUser/toUpdateDriver?driverid=" + driverid;
	
	});
	
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}