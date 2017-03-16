

function go_back() {
	window.history.go(-1);
}
var clock = '';
var nums = 30;
var btn;

function get_code(thisBtn) {

	var username = $("#doc-vld-528").val();
	var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
	if($("#doc-vld-528").val() == "") {
		swal("手机号不能为空!");
		$("#doc-vld-528").focus();
		return false;
	} else if($("#doc-vld-528").val().length != 11 && !myreg.test($("#doc-vld-528").val())) {
		swal("手机号格式不正确!");
		$("#doc-vld-528").focus();
		return false;
	}
	btn = thisBtn;
	btn.disabled = true; //将按钮置为不可点击
	btn.innerText = nums + '秒后获取';
	clock = setInterval(doLoop, 1000); //一秒执行一次
	swal(username);
	$.getJSON("json/locat.json", function(data) {
		var locat = data["url"];
		$.ajax({
			type: "post",
			cache: false,
			url: locat + '/shop/find_code',
			timeout: 30000,
			xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            crossDomain: true,
			data: {
				username: username,
				datetime: new Date().getTime()
			},
			dataType: "json",
			success: function(data) {
				//var str = data.toJSONString();

				/* for(var i=0;i<data["listgoods"].length;i++){
					document.getElementById("snxh").options.add(new Option(data["listgoods"][i]["typename"]+data["listgoods"][i]["goodsname"],data["listgoods"][i]["g_id"]));
				}
				for(var i=0;i<data["listclient"].length;i++){
					document.getElementById("snkh").options.add(new Option(data["listclient"][i]["clientnumber"],data["listclient"][i]["c_id"]));
					
					
				} */
			}
		});
	});
}

function save_pwd() {
	var username = $("#doc-vld-528").val();

	var code = $("#code").val();
	var password = $("#doc-vld-pwd-1").val();
	var checkpassword = $("#doc-vld-pwd-2").val();

	if($("#doc-vld-528").val() == "") {
		swal("手机号不能为空!");
		$("#doc-vld-528").focus();
		return false;
	} else if($("#doc-vld-528").val().length != 11 && !myreg.test($("#doc-vld-528").val())) {
		swal("手机号格式不正确!");
		$("#doc-vld-528").focus();
		return false;
	}
	if($("#code").val() == "") {
		swal("验证码不能为空!");
		$("#code").focus();
		return false;
	}
	if($("#doc-vld-pwd-1").val() == ""||$("#doc-vld-pwd-1").val().length!=6) {
		$("#doc-vld-pwd-1").focus();
		return false;
	}
	if($("#doc-vld-pwd-2").val() == ""||$("#doc-vld-pwd-2").val().length!=6) {
		swal("确认密码不能为空!");
		$("#doc-vld-pwd-2").focus();
		return false;
	}
	if($("#doc-vld-pwd-1").val() != $("#doc-vld-pwd-2").val()) {
		swal("两次密码不相同!");
		$("#doc-vld-pwd-2").focus();
		return false;
	}
	swal(password);
	$.getJSON("json/locat.json", function(data) {
		var locat = data["url"];

		$.ajax({
			type: "post",
			cache: false,
			url: locat + '/shop/save_pwd',
			timeout: 10000,
			xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            crossDomain: true,
			data: {
				"username": username,
				"code": code,
				"password": password,
				datetime: new Date().getTime()
			},
			dataType: "json",
			success: function(data) {
				
				
				if(data["err_str"]=="密码修改成功"){
					swal(data["err_str"]+"，您可以登录了");
					window.location.href = "login.html";
				}else{
					swal(data["err_str"]);
					window.location.href ="zhmm.html";
				}
				
			}
		});

	});

}

function doLoop() {
	nums--;
	if(nums > 0) {
		btn.innerText = nums + '秒后获取';
	} else {
		clearInterval(clock); //清除js定时器
		btn.disabled = false;
		btn.innerText = '获取验证码';
		nums = 10; //重置时间
	}
}