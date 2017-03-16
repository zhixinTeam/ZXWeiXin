 $(document).ready(function(){

	var id = GetQueryString("id");
	var currentPage = GetQueryString("currentPage");
	var pagecount = GetQueryString("pagecount");
	
	var currentPage = GetQueryString("currentPage");
//		alert(id);
//		alert(currentPage);
	var cookie=  $.AMUI.utils.cookie;
	var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
	$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {

		var locat = data["url"];
		$.ajax(locat + '/clientIndex/news_ByID?id='+id, {
			dataType: 'json',
			type: 'get',
			timeout: 10000,
			
			xhrFields: {
	                withCredentials: true,
	                useDefaultXhrHeader: false
	            },
	            crossDomain: true,
			success: function(data) {
				var finallist = "";
//				
				finallist = finallist + "<li class='am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-top'>" +
					"<div class='am-list-thumb am-u-sm-12'>" +
					"<a href='#' class=''>" +
					"<img src=\"http://www.hnzxtech.cn/wxplatform/uploadFiles/uploadImgs/" + data["jsonpack"]["path"] + "\" /></a></div><div class='am-list-main'>" +
					"<h3 class='am-list-item-hd'><a href='' class=''>" + data["jsonpack"]["miaosu"] + "</a></h3>" +
					"<div class='am-gs-text'>" + data["jsonpack"]["context"] + "</div></li>";
				$("#onenew").append(finallist);
				$("#one").append("<a  id='id' href="+locat+"/clientIndex/toindex1?currentPage="+currentPage+"&pagecount="+pagecount+"'><button class='am-btn am-btn-secondary am-btn-block am-radius' type='button'  >返回</button></a>");
//				alert($("#id").attr("href"));
			},
			error: function() {
				swal("服务或网络异常，稍后再试！","","error");
			}
		});
	});
});




function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}