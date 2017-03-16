jQuery(function() {
		$.getJSON ("static/shop_pc/json/locat.json", function (data) {
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
						window.location.href=locat+"/shop/login_tologin";
					else {
						var finallist = ""
						for(i = 0; i<=data["list_driver"].length - 1; i++) {
							finallist += "<tr>" +
								"<td class='am-text-center'>" + data["list_driver"][i]["name"] + "</td>" +
								"<td class='am-text-center'>" + data["list_driver"][i]["phone"] + "</td>" +
								" <td class='am-text-center'>" + data["list_driver"][i]["tracknumber"] + "</td>" +
								"  <td class='am-text-center'>" +
								"	<button type=\"button\" class=\"am-btn am-btn-primary am-btn-xs am-radius\" onclick='updateDriver(\"" + data["list_driver"][i]["d_id"] + "\")'>修改</button>" +
								"	<button type=\"button\" class=\"am-btn am-btn-danger am-btn-xs am-radius \" onclick='delDriver(\"" + data["list_driver"][i]["d_id"] + "\")'>删除</button>" +
								"</td>" +
								" </tr>    ";
						}
						$("#table_driver").append(finallist);
						$(
						'#example')
						.DataTable();
					}

				},
				error: function(data) {
					swal("","服务异常！","error");
					window.location.href=locat+"/shop/login_toLogin";
				}
			});
		});
	
	
	$('#doc-vld-msg').validator({
	    onValid: function(validity) {
	      $(validity.field).closest('.am-form-group').find('.am-alert').hide();
	    },

	    onInValid: function(validity) {
	      var $field = $(validity.field);
	      var $group = $field.closest('.am-form-group');
	      var $alert = $group.find('.am-alert');
	      var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

	      if (!$alert.length) {
	        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
	          appendTo($group);
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
	

});

function save(){
	var drivername = $("#drivername").val();
	var driverphone = $("#driverphone").val();
	var tracknumber = $("#tracknumber").val();
	if(drivername==""||driverphone==""||tracknumber==""){
			$("#doc-vld-msg").submit();
			return false;
	}else{
		if(driverphone.length!=11||tracknumber.length!=7){
			$("#doc-vld-msg").submit();
			return false;
		}else{
			var id = "body-loading";
  	$(window).loading({action:"open",id:id,font:40}); 
  	$.getJSON ("static/shop_pc/json/locat.json", function (data) {
			var  locat=data["url"];
			var factoryid=data["factoryid"];
			var token = $("#token").val();
	        $.ajax(locat+"/clientUser/saveDriver", {
	            type: "POST",
	            xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            data: {
	                drivername: drivername,
	                driverphone: driverphone,
	                tracknumber: tracknumber,
	                token:token
	            },
	            crossDomain: true,
	            success: function(data, status, xhr) {
	            		var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);
						if("success" == data["msg"]){
								swal("新增成功","","success");
								window.location.href=locat+"/shop/totjsja";
						}
	            },
	            error:function(data){
	            	var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);
	            	swal("","服务异常！","error");
	            	window.location.href=locat+"/shop/login_toLogin";
	            }
	        });
    });  
		}
		
	}
	



}





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
		$.getJSON ("static/shop_pc/json/locat.json", function (data) {
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
						swal("Good!", "删除成功", "success"); 
						window.location.href=locat+"/shop/totjsja";
					} else if(data["mes"] =="errorr"){
						window.location.href=locat+"/shop/login_toLogin";
					}else{
						swal("此司机还有订单，不能删除", "", "error");
					}
				},
				error: function(data) {
					swal("","服务异常","error");
					window.location.href=locat+"/shop/login_toLogin";
				}
			});
		});

	});

}

//修改driver
function updateDriver(driverid) {
	//var cookie=  $.AMUI.utils.cookie;
	//var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
	$.getJSON ("static/shop_pc/json/locat.json", function (data) {
		var locat = data["url"];
		window.location.href = locat+"/shop/toxgsj?driverid=" + driverid;
	
	});
	
}




function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}