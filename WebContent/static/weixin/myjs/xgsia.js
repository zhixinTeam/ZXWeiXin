 $(document).ready(function() {
 	var cookie=  $.AMUI.utils.cookie;
	var driverid = GetQueryString("driverid");
	var cookie=  $.AMUI.utils.cookie;
	var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
	$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
		var locat=data["url"];
		 $.ajax(locat+"/clientUser/updateDriver1?driverid="+driverid, {
	            type: "GET",
	            xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            crossDomain: true,
	            success: function(data, status, xhr) {
	            	if(data["msg"]=="error"){
	            		window.location.href=locat+"/clientUser/tologin";
	            	}else{
	            	document.getElementById('drivername').value = data['jsondriver']['name'];
					document.getElementById('driverphone').value = data['jsondriver']['phone'];
					document.getElementById('tracknumber').value = data['jsondriver']['tracknumber'];
					document.getElementById('drivername1').value = data['jsondriver']['name'];
					document.getElementById('driverphone1').value = data['jsondriver']['phone'];
					document.getElementById('tracknumber1').value = data['jsondriver']['tracknumber'];
	            	}
	            	
	            },
				error:function(data){
					swal("服务异常","","error");
					window.location.href=locat+"/clientUser/tologin";
	            }
	           
	            
	        });
		
	});
	
	
	
	
})
 
 function update(){
 	var cookie=  $.AMUI.utils.cookie;
 	//var sessionId=(cookie.get("sessionId")==null)?"":cookie.get("sessionId");
 	var driverid = GetQueryString("driverid");
 	var name=$("#drivername").val();
 	var phone=$("#driverphone").val();
 	var tracknumber=$("#tracknumber").val();
 	if(name==""||phone==""||tracknumber==""){
 		$("#doc-vld-msg").submit();
			return false;
 	}else{
 		if(phone.length!=11||tracknumber.length!=7){
			
		}else{
			var id = "body-loading";
  		$(window).loading({action:"open",id:id,font:40}); 
  		var cookie=  $.AMUI.utils.cookie;
  		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
  		$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
		var locat=data["url"];
		 $.ajax(locat+"/clientUser/updateDriver", {
	            type: "post",
	            xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            data: {
	                "driverid": driverid,
	                "name": name,
	                "phone": phone,
	                "tracknumber": tracknumber
	                
	            },
	            crossDomain: true,
	            success: function(data, status, xhr) {
	            	var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);
			   		
	            	swal("修改成功","","success");
					window.location.href=locat+"/clientUser/to_dirver";
					
    	
	            },
				error:function(data){
					var time = setTimeout(function(){
			   				//关闭
			   				$(window).loading({action:"close",id:id});
			   			},100);
	            	swal("服务异常","","error");
	            	window.location.href=locat+"/clientUser/tologin";
	            }
	           
	            }); 
	          }); 
		}
 		
 	}
 	
 	
 }
 
function resets(){
	document.getElementById('drivername').value = document.getElementById('drivername1').value;
	document.getElementById('driverphone').value = document.getElementById('driverphone1').value;
	document.getElementById('tracknumber').value = document.getElementById('tracknumber1').value;
}

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}