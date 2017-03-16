 $(document).ready(function() {
 	
		var cookie=  $.AMUI.utils.cookie;
		var stockname = unescape(cookie.get("stockname"));
		var clientname = unescape(cookie.get("clientname"));
		var billnumber =cookie.get("billnumber");
		var maxnumber =cookie.get("maxnumber");
		var c_id =cookie.get("c_id");
		var stockno =cookie.get("stockno");
		document.getElementById("stockno").value=stockno;
		document.getElementById("stockname").value=stockname;
		document.getElementById("clientname").value=clientname;
		document.getElementById("billnumber").value=billnumber;
		document.getElementById("maxnumber").value=maxnumber;
		document.getElementById("c_id").value=c_id;
		var span="<span style='color:sandybrown'><剩余吨数："+maxnumber+"吨></span>";
		$("#maxnumber1").append(span);
		//获取当前用户的司机
		$.getJSON ("static/shop_pc/json/locat.json", function (data) {
		var locat = data["url"];
		$.ajax(locat + "/clientUser/driver_list", {
			type: "GET",
			xhrFields: {
				withCredentials: true,
				useDefaultXhrHeader: false
			},
			crossDomain: true,
			success: function(data, status, xhr) {
				if("error" == data["msg"]) {
					window.location.href=locat+"/shop/login_toLogin";
				}else if(stockno==""||stockno==null){
					window.location.href=locat+"/shop/totjsja";
				}
				else {
					//司机
					if("drierror" == data["msg"]) {
						swal("没有司机不能提货 ", "先去添加司机", "error");
						swal({
							title: "您还没有提货司机",
							text: "是否添加提货司机",
							type: "warning",
							showCancelButton: true,
							closeOnConfirm: false,
							cancelButtonText: "取消",
							confirmButtonText: "确定",
							confirmButtonColor: "#ec6c62"
						}, function() {
							window.location.href = locat+"/shop/totjsja";
						})

					} else {
						for(i = data["list_driver"].length - 1; i >= 0; i--) {
							document.getElementById("driverid").options.add(new Option(data["list_driver"][i]["tracknumber"], data["list_driver"][i]["d_id"]));
								}
					}
				}

			},
			error: function(data) {
				swal("", "服务异常！", "error");
				window.location.href=locat+"/shop/to_toLogin";
			}
		});
	});

});



//保存订单
function save(){

	var billnumber =$("#billnumber").val();   
	var c_id =$("#c_id").val();  
	var stockno =$("#stockno").val(); 
	var stockname =$("#stockname").val(); 
	var driverid =$("#driverid").val(); 
	var clientname = $("#clientname").val(); 
	var goodsnumber =$("#goodsnumber").val(); 
	var thrq =$("#thrq").val(); 
	var thrqmsg=$("#thrqmsg").val(); 
	var numbermsg=$("#numbermsg").val(); 
	var cookie=  $.AMUI.utils.cookie;
	if(billnumber==""||c_id==""||stockname==""||driverid==""||goodsnumber==""||thrq==""){
			$("#doc-vld-ajax").submit();
			return false;
		}else{
			if(thrqmsg=="error"||numbermsg=="error"){
				swal("填写有误，请认真填写","","error")
			}else{
				var id = "body-loading";
  	$(window).loading({action:"open",id:id,font:40}); 
	$.getJSON ("static/shop_pc/json/locat.json", function (data) {
			var  locat=data["url"];
	        $.ajax(locat+"/clientOrder/place_order", {
	            type: "POST",
	            xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            data: {
	                billnumber: billnumber,
	                c_id: c_id,
	                stockno:stockno,
	                stockname: stockname,
	                driverid: driverid,
	                clientname:clientname,
	                goodsnumber: goodsnumber,
	                thrq: thrq
	            },
	            crossDomain: true,
	            success: function(data, status, xhr) {
	            		var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);
			   			if("success" == data["msg"]) {
							swal("下单成功！");
							cookie.unset("stockno");
							cookie.unset("stockname");
							cookie.unset("billnumber");
							cookie.unset("maxnumber");
							cookie.unset("clientname");
							cookie.unset("clientnumber");
							cookie.unset("c_id");
							window.location.href=locat+"/shop/tolsdd";
						}else{
							sweetAlert("下单异常", "请稍后再试", "error");
						}
						
						
	            },
	            error:function(data){
	            	var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);
					sweetAlert("服务异常", "请稍后再试", "error");
					window.location.href = locat+"/shop/login_toLogin";
	            	
	            }
	        });
    });  
	
			}
		}
	//提货单保存
	
	
	

}
	function chkvalue(txt) {
		var cookie=  $.AMUI.utils.cookie;
		var maxgoodnumber=Number(cookie.get("maxnumber"));
		var goodsnumber=Number(txt.value);
		
		if(maxgoodnumber<goodsnumber){
			swal("提货吨数不能大于剩余货物吨数","","error");
			  $("#goodsnumber").css({
           	"color": "red",
           });
           document.getElementById("numbermsg").value="error";
		}else{
			if(txt.value<=0){
				swal("提货吨数不能小于或等于0","","error");
			  $("#goodsnumber").css({
           		"color": "red",
           });
           document.getElementById("numbermsg").value="error";
			}else{
				 $("#goodsnumber").css({
           		"color": "black",
           		});
           document.getElementById("numbermsg").value="success";
			}
			
		}
	}
	


 function chkDate (){
        var pdate = document.getElementById ('thrq');
        var d = new Date;
        var today = new Date(d.getFullYear (), d.getMonth (), d.getDate ());
     
        var reg = /\d+/g;
        var temp = pdate.value.match (reg);
        
        var foday = new Date (temp[0], parseInt (temp[1]) - 1, temp[2]);
        
        if (foday < today)
        {
           swal("提货日期不能在今天之前"," ","error");
           $("#thrq").css({
           	"color": "red",
           });
           document.getElementById("thrqmsg").value="error";
           
       }else{
       	 $("#thrq").css({
           	"color": "black",
           });
           document.getElementById("thrqmsg").value="success";
       }
       
    }
 
function goback(){
	window.history.go(-1);
	
}




function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}