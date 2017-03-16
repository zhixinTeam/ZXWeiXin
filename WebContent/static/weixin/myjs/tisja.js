$(document).ready(function() {

});

//保存司机
function save() {
	var cookie = $.AMUI.utils.cookie;
	var companyid = (cookie.get("companyid") == null) ? "" : cookie
			.get("companyid");
	var drivername = $("#drivername").val();
	var driverphone = $("#driverphone").val();
	var tracknumber = $("#tracknumber").val();
	if (drivername == "" || driverphone == "" || tracknumber == "") {
		$("#doc-vld-msg").submit();
		return false;
	} else {
		if (driverphone.length != 11 || tracknumber.length != 7) {
			$("#doc-vld-msg").submit();
			return false;
		} else {
			var token = $("#token").val();
			var id = "body-loading";
			$(window).loading({
				action : "open",
				id : id,
				font : 40
			});
			$.getJSON("static/weixin/json/" + companyid + ".json", function(
					data) {
				var locat = data["url"];
				var factoryid = data["factoryid"];
				$.ajax(locat + "/clientUser/saveDriver?companyid=" + companyid,
						{
							type : "POST",
							xhrFields : {
								withCredentials : true,
								useDefaultXhrHeader : false
							},
							data : {
								drivername : drivername,
								driverphone : driverphone,
								tracknumber : tracknumber,
								token : token
							},
							crossDomain : true,
							success : function(data, status, xhr) {
								var time = setTimeout(function() {
									//关闭
									$(window).loading({
										action : "close",
										id : id
									});
								}, 100);
								if ("success" == data["msg"]) {
									swal("新增成功", "", "success");
									window.location.href = locat
											+ "/clientUser/to_dirver";
								} else if ("repeat" == data["msg"]) {
									swal("已提交,请勿重复提交");
									window.location.href = locat
											+ "/clientUser/to_dirver";
								}
							},
							error : function(data) {
								var time = setTimeout(function() {
									//关闭
									$(window).loading({
										action : "close",
										id : id
									});
								}, 100);
								swal("", "服务异常！", "error");
								window.location.href = locat
										+ "clientUser/tologin";
							}
						});
			});

		}

	}

}
