 $(document).ready(function() {
 	var cookie=  $.AMUI.utils.cookie;
	var time=new Date();
	time=new Date(time.valueOf() + 7*24*60*60*1000);
	var companyid = GetQueryString("companyid");
	 companyid=(GetQueryString("companyid")==null)?"":GetQueryString("companyid");
	if(companyid==""){
		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
		 if(companyid=="")
			 swal("未获取公众号信息，请重新由集团的公众号进入！");
	}
	cookie.set("companyid",companyid,time,"/");
 })
 
 
 function toUser(){
	 var cookie=  $.AMUI.utils.cookie;
		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
		if(companyid==""){
			swal("未获取公众号信息，请重新由集团的公众号进入！");
		}
		$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
			var locat = data["url"];
			//如果没有登录。先进行登录
			window.location.href=locat+"/clientUser/touser?companyid="+companyid;

		});
	
 }
 
 function toLsdd(){
	 var cookie=  $.AMUI.utils.cookie;
		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
		if(companyid==""){
			swal("未获取公众号信息，请重新由集团的公众号进入！");
		}
		$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
			var locat = data["url"];
			//如果没有登录。先进行登录
			window.location.href=locat+"/clientOrder/tolsdd";

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