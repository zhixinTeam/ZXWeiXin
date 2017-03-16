$(document)
		.ready(
				function() {
					var driverid = GetQueryString("driverid");
					$
							.getJSON(
									"static/shop_pc/json/locat.json",
									function(data) {
										var locat = data["url"];
										$
												.ajax(
														locat
																+ "/clientUser/updateDriver1",
														{
															type : "GET",
															xhrFields : {
																withCredentials : true,
																useDefaultXhrHeader : false
															},
															data : {
																"driverid" : driverid
															},
															crossDomain : true,
															success : function(
																	data,
																	status, xhr) {
																if (data["msg"] == "error") {
																	window.location.href = locat
																			+ "/shop/login_toLogin";
																} else {
																	document
																			.getElementById('drivername').value = data['jsondriver']['name'];
																	document
																			.getElementById('driverphone').value = data['jsondriver']['phone'];
																	document
																			.getElementById('tracknumber').value = data['jsondriver']['tracknumber'];
																	document
																			.getElementById('drivername1').value = data['jsondriver']['name'];
																	document
																			.getElementById('driverphone1').value = data['jsondriver']['phone'];
																	document
																			.getElementById('tracknumber1').value = data['jsondriver']['tracknumber'];
																}

															},
															error : function(
																	data) {
																swal(
																		"",
																		"服务异常！",
																		"error");
																window.location.href = locat
																		+ "/shop/login_toLogin";
															}

														});

									});

					$('#doc-vld-msg')
							.validator(
									{
										onValid : function(validity) {
											$(validity.field).closest(
													'.am-form-group').find(
													'.am-alert').hide();
										},

										onInValid : function(validity) {
											var $field = $(validity.field);
											var $group = $field
													.closest('.am-form-group');
											var $alert = $group
													.find('.am-alert');
											var msg = $field
													.data('validationMessage')
													|| this
															.getValidationMessage(validity);

											if (!$alert.length) {
												$alert = $(
														'<div class="am-alert am-alert-danger"></div>')
														.hide()
														.appendTo($group);
											}

											$alert.html(msg).show();
										}
									});

					if ($.AMUI && $.AMUI.validator) {
						$.AMUI.validator.patterns.mobile = /^\s*1\d{10}\s*$/;
					}

					if ($.AMUI && $.AMUI.validator) {
						$.AMUI.validator.patterns.tracknumber = /^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$/;
					}

				})

function update() {
	var cookie = $.AMUI.utils.cookie;
	var driverid = GetQueryString("driverid");
	var name = $("#drivername").val();
	var phone = $("#driverphone").val();
	var tracknumber = $("#tracknumber").val();
	if (name == "" || phone == "" || tracknumber == "") {
		$("#doc-vld-msg").submit();
		return false;
	} else {
		if (phone.length != 11 || tracknumber.length != 7) {

		} else {
			var id = "body-loading";
			$(window).loading({
				action : "open",
				id : id,
				font : 40
			});
			$.getJSON("static/shop_pc/json/locat.json", function(data) {
				var locat = data["url"];
				$.ajax(locat + "/clientUser/updateDriver", {
					type : "post",
					xhrFields : {
						withCredentials : true,
						useDefaultXhrHeader : false
					},
					data : {
						"driverid" : driverid,
						"name" : name,
						"phone" : phone,
						"tracknumber" : tracknumber

					},
					crossDomain : true,
					success : function(data, status, xhr) {
						var time = setTimeout(function() {
							// 关闭
							$(window).loading({
								action : "close",
								id : id
							});
						}, 100);

						swal("Good", "修改成功", "success");
						window.location.href = locat + "/shop/totjsja";

					},
					error : function(data) {
						var time = setTimeout(function() {
							// 关闭
							$(window).loading({
								action : "close",
								id : id
							});
						}, 100);
						swal("服务异常", "", "error");
						window.location.href = locat + "/shop/login_toLogin";
					}

				});
			});
		}

	}

}

function resets() {
	document.getElementById('drivername').value = document
			.getElementById('drivername1').value;
	document.getElementById('driverphone').value = document
			.getElementById('driverphone1').value;
	document.getElementById('tracknumber').value = document
			.getElementById('tracknumber1').value;
}

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}