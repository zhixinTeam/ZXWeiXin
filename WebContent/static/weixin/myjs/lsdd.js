$(document)
		.ready(
				function() {

					var cookie = $.AMUI.utils.cookie;
					var companyid = (cookie.get("companyid") == null) ? ""
							: cookie.get("companyid");
					$
							.getJSON(
									"static/weixin/json/" + companyid + ".json",
									function(data) {
										var cookie = $.AMUI.utils.cookie;
										var sessionId = cookie.get("sessionId");
										if (sessionId == null) {
											sessionId = "";
										}
										var factoryId = (cookie
												.get("factoryid") == null) ? ""
												: cookie.get("factoryid");
										var factoryid = data["factoryid"];
										var locat = data["url"];
										if (factoryId != factoryid)
											window.location.href = locat
													+ "/clientUser/tologin";
										// 订单列表，如果没有登录。先进行登录
										$
												.ajax(
														locat
																+ '/clientOrder/order_list?sessionId='
																+ sessionId,
														{
															dataType : 'json',
															type : 'get',
															timeout : 100000,
															xhrFields : {
																withCredentials : true,
																useDefaultXhrHeader : false
															},
															crossDomain : true,
															success : function(
																	data) {
																if ("error" == data["msg"]) {
																	window.location.href = locat
																			+ "/clientUser/tologin";
																} else {
																	if (data["message"] == "0") {
																		swal(
																				{
																					title : "您还没有下提货单呢",
																					text : "赶快下提货单吧",
																					type : "warning",
																					showCancelButton : true,
																					closeOnConfirm : false,
																					cancelButtonText : "取消",
																					confirmButtonText : "确定",
																					confirmButtonColor : "#ec6c62"
																				},
																				function() {
																					window.location.href = locat
																							+ "/clientUser/touser?companyid="
																							+ companyid;
																				})
																	} else {
																		var finallist = '';
																		var o_id = '';
																		// swal(locat+"/uploadFiles/twoDimensionCode/"+data["encoderImgId"]);
																		for (i = data["jsonorderlists"].length - 1; i >= 0; i--) {

																			var finallist1 = "<div data-am-tabs class='am-tabs'>"
																					+ "<ul class='am-tabs-nav am-nav am-nav-tabs'>"
																					+ "<li class='am-active'><a href='#tab1'>查看订单详情</a></li>"
																					+ " <li class=''><a href='#tab2'>查看二维码</a></li></ul>";

																			if (data["jsonorderlists"][i]["status"] == 0) {

																				// swal("111111"+o_id);
																				var status = 0;
																				var finallist2 = "<div class='am-tabs-bd'><div  class='am-tab-panel am-fade am-in am-active' id='tab1'><div class='am-panel-bd' >"
																						+ " <div class='am-ckdd'>"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>工厂名称:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["doc_factoryname"]
																						+ "</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4 '>订单编号:</div><div class='am-u-sm-8 ' >"
																						+ data["jsonorderlists"][i]["fac_order_no"]
																						+ "</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>商品名称:</div><div class='am-u-sm-8 '>"
																						+ data["jsonorderlists"][i]["shopgoodsname"]
																						+ "</div></div>"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>订单数量:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["data"]
																						+ "(吨)</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>车牌号码:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["drivertracknumber"]
																						+ "</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4 '>提货日期:</div><div class='am-u-sm-8 '>"
																						+ data["jsonorderlists"][i]["thdate"]
																						+ "</div></div>"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>操作</div><div class='am-u-sm-8'>"
																						+ "<button id='"
																						+ data['jsonorderlists'][i]['ordernumber']
																						+ "' type='button' class='am-btn am-qx am-btn-primary am-radius ' onclick='editOrder(\""
																						+ data['jsonorderlists'][i]['ordernumber']
																						+ "\",\""
																						+ data['jsonorderlists'][i]['o_id']
																						+ "\")'  >取消订单</button></div></div></div></div></div>"
																						+ "<div  class='am-tab-panel am-fade ' id='tab2'>"
																						+ " <figure data-am-widget='figure' class='am am-figure am-figure-default' data-am-figure=\"{ pureview: '"
																						+ true
																						+ "' }\">"
																						+ "<img src='"
																						+ locat
																						+ "/uploadFiles/twoDimensionCode/"
																						+ data["encoderImgId"]
																						+ "' id='123"
																						+ data["jsonorderlists"][i]["ordernumber"]
																						+ "' /><figcaption class='am-figure-capition-btm'>"
																						+ " 提货单号： "
																						+ data["jsonorderlists"][i]["ordernumber"]
																						+ "</br><span>"
																						+ data["jsonorderlists"][i]["doc_factoryname"]
																						+ "</span><br /> </figcaption></figure></div>"
																						+ "</div></div>";

																			} else if (data["jsonorderlists"][i]["status"] == 2) {

																				var status = 2;
																				var finallist2 = "<div class='am-tabs-bd'><div  class='am-tab-panel am-fade am-in am-active' id='tab1'><div class='am-panel-bd' >"
																						+ " <div class='am-ckdd'>"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>工厂名称:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["doc_factoryname"]
																						+ "</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4 '>订单编号:</div><div class='am-u-sm-8 ' >"
																						+ data["jsonorderlists"][i]["fac_order_no"]
																						+ "</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>商品名称:</div><div class='am-u-sm-8 '>"
																						+ data["jsonorderlists"][i]["shopgoodsname"]
																						+ "</div></div>"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>订单数量:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["data"]
																						+ "(吨)</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>车牌号码:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["drivertracknumber"]
																						+ "</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4 '>提货日期:</div><div class='am-u-sm-8 '>"
																						+ data["jsonorderlists"][i]["thdate"]
																						+ "</div></div>"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>状态</div><div class='am-u-sm-8'>"
																						+ "<button type='button'  class='am-btn am-qx am-btn-danger am-radius ' >订单已取消</button></div></div></div></div></div>"
																						+ "<div  class='am-tab-panel am-fade ' id='tab2'>"
																						+ " <figure data-am-widget='figure' class='am am-figure am-figure-default' data-am-figure=\"{ pureview: '"
																						+ true
																						+ "' }\">"
																						+ "<img src='static/weixin/img/error.jpg' /><figcaption class='am-figure-capition-btm'>"
																						+ " 提货单号： "
																						+ data["jsonorderlists"][i]["ordernumber"]
																						+ "</br><span>"
																						+ data["jsonorderlists"][i]["doc_factoryname"]
																						+ "</span><br /> </figcaption></figure></div>"
																						+ "</div></div>";

																			} else {

																				var status = 1;

																				var finallist2 = "<div class='am-tabs-bd'><div  class='am-tab-panel am-fade am-in am-active' id='tab1'><div class='am-panel-bd' >"
																						+ " <div class='am-ckdd'>"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>工厂名称:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["doc_factoryname"]
																						+ "</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4 '>订单编号:</div><div class='am-u-sm-8 '>"
																						+ data["jsonorderlists"][i]["fac_order_no"]
																						+ "</div></div>	"
																						+ "<div class='am-g am-button '><div class='am-u-sm-4'>商品名称:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["shopgoodsname"]
																						+ "</div></div>"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>订单数量:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["data"]
																						+ "(吨)</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>车牌号码:</div><div class='am-u-sm-8'>"
																						+ data["jsonorderlists"][i]["drivertracknumber"]
																						+ "</div></div>	"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4 '>提货日期:</div><div class='am-u-sm-8 '>"
																						+ data["jsonorderlists"][i]["thdate"]
																						+ "</div></div>"
																						+ "<div class='am-g am-button'><div class='am-u-sm-4'>状态:</div><div class='am-u-sm-8'>"
																						+ "<button type='button'  class='am-btn am-qx am-btn-success am-radius ' >订单已出厂</button></div></div></div></div></div>"
																						+ +"<div  class='am-tab-panel am-fade ' id='tab2'>"
																						+ " <figure data-am-widget='figure' class='am am-figure am-figure-default' data-am-figure=\"{ pureview: '"
																						+ true
																						+ "' }\">"
																						+ "<img src='static/weixin/img/success.jpg' /><figcaption class='am-figure-capition-btm'>"
																						+ " 提货单号： "
																						+ data["jsonorderlists"][i]["ordernumber"]
																						+ "</br><span>"
																						+ data["jsonorderlists"][i]["doc_factoryname"]
																						+ "</span><br /> </figcaption></figure></div>"
																				"</div></div>";
																			}
																			finallist = finallist1
																					+ finallist2;
																			var pagecount = data["jsonorderlists"][i]["pageCount"];
																		}
																		$(
																				"#orderlist")
																				.append(
																						finallist);
																		document
																				.getElementById('pagecount').value = pagecount;
																		document
																				.getElementById('editOrder').value = o_id;
																		document
																				.getElementById('status').value = status;

																	}
																}

															},
															error : function(
																	data) {
																swal("",
																		"服务异常",
																		"error");
																window.location.href = locat
																		+ "/clientUser/tologin";

															}
														});

									});

				})

function see_more() {
	var currentPage = Number($("#currentPage").val()) + 1;
	// swal(currentPage);

	var pagecount = Number($("#pagecount").val());
	var cookie = $.AMUI.utils.cookie;
	var companyid = (cookie.get("companyid") == null) ? "" : cookie
			.get("companyid");
	$
			.getJSON(
					"static/weixin/json/" + companyid + ".json",
					function(data) {
						var locat = data["url"];
						var cookie = $.AMUI.utils.cookie;
						var sessionId = cookie.get("sessionId")
						if (sessionId == null) {
							sessionId = "";
						}
						if (currentPage > pagecount) {
							swal("亲,没有更多了");
						} else {
							$("#loading").button('loading');
							$
									.ajax(
											locat
													+ '/clientOrder/order_list?sessionId='
													+ sessionId,
											{
												dataType : 'json',
												data : {
													"currentPage" : currentPage
												},
												type : 'get',
												timeout : 10000,
												xhrFields : {
													withCredentials : true,
													useDefaultXhrHeader : false
												},
												crossDomain : true,
												success : function(data) {
													var finallist = '';
													var o_id = "";
													for (i = data["jsonorderlists"].length - 1; i >= 0; i--) {

														var finallist1 = "<div data-am-tabs class='am-tabs'>"
																+ "<ul class='am-tabs-nav am-nav am-nav-tabs'>"
																+ "<li class='am-active'><a href='#tab1'>查看订单详情</a></li>"
																+ " <li class=''><a href='#tab2'>查看二维码</a></li></ul>";

														if (data["jsonorderlists"][i]["status"] == 0) {

															// swal("111111"+o_id);
															var status = 0;
															var finallist2 = "<div class='am-tabs-bd'><div  class='am-tab-panel am-fade am-in am-active' id='tab1'><div class='am-panel-bd' >"
																	+ " <div class='am-ckdd'>"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>工厂名称:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["doc_factoryname"]
																	+ "</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4 '>订单编号:</div><div class='am-u-sm-8 ' >"
																	+ data["jsonorderlists"][i]["fac_order_no"]
																	+ "</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>商品名称:</div><div class='am-u-sm-8 '>"
																	+ data["jsonorderlists"][i]["shopgoodsname"]
																	+ "</div></div>"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>订单数量:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["data"]
																	+ "(吨)</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>车牌号码:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["drivertracknumber"]
																	+ "</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4 '>提货日期:</div><div class='am-u-sm-8 '>"
																	+ data["jsonorderlists"][i]["thdate"]
																	+ "</div></div>"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>操作</div><div class='am-u-sm-8'>"
																	+ "<button id='"
																	+ data['jsonorderlists'][i]['ordernumber']
																	+ "' type='button' class='am-btn am-qx am-btn-primary am-radius ' onclick='editOrder(\""
																	+ data['jsonorderlists'][i]['ordernumber']
																	+ "\",\""
																	+ data['jsonorderlists'][i]['o_id']
																	+ "\")'  >取消订单</button></div></div></div></div></div>"
																	+ "<div  class='am-tab-panel am-fade ' id='tab2'>"
																	+ " <figure data-am-widget='figure' class='am am-figure am-figure-default' data-am-figure=\"{ pureview: '"
																	+ true
																	+ "' }\">"
																	+ "<img src='"
																	+ locat
																	+ "/uploadFiles/twoDimensionCode/"
																	+ data["encoderImgId"]
																	+ "' id='123"
																	+ data["jsonorderlists"][i]["ordernumber"]
																	+ "' /><figcaption class='am-figure-capition-btm'>"
																	+ " 提货单号： "
																	+ data["jsonorderlists"][i]["ordernumber"]
																	+ "</br><span>"
																	+ data["jsonorderlists"][i]["doc_factoryname"]
																	+ "</span><br /> </figcaption></figure></div>"
																	+ "</div></div>";

														} else if (data["jsonorderlists"][i]["status"] == 2) {

															var status = 2;
															var finallist2 = "<div class='am-tabs-bd'><div  class='am-tab-panel am-fade am-in am-active' id='tab1'><div class='am-panel-bd' >"
																	+ " <div class='am-ckdd'>"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>工厂名称:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["doc_factoryname"]
																	+ "</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4 '>订单编号:</div><div class='am-u-sm-8 ' >"
																	+ data["jsonorderlists"][i]["fac_order_no"]
																	+ "</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>商品名称:</div><div class='am-u-sm-8 '>"
																	+ data["jsonorderlists"][i]["shopgoodsname"]
																	+ "</div></div>"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>订单数量:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["data"]
																	+ "(吨)</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>车牌号码:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["drivertracknumber"]
																	+ "</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4 '>提货日期:</div><div class='am-u-sm-8 '>"
																	+ data["jsonorderlists"][i]["thdate"]
																	+ "</div></div>"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>状态</div><div class='am-u-sm-8'>"
																	+ "<button type='button'  class='am-btn am-qx am-btn-danger am-radius ' >订单已取消</button></div></div></div></div></div>"
																	+ "<div  class='am-tab-panel am-fade ' id='tab2'>"
																	+ " <figure data-am-widget='figure' class='am am-figure am-figure-default' data-am-figure=\"{ pureview: '"
																	+ true
																	+ "' }\">"
																	+ "<img src='static/weixin/img/error.jpg' /><figcaption class='am-figure-capition-btm'>"
																	+ " 提货单号： "
																	+ data["jsonorderlists"][i]["ordernumber"]
																	+ "</br><span>"
																	+ data["jsonorderlists"][i]["doc_factoryname"]
																	+ "</span><br /> </figcaption></figure></div>"
																	+ "</div></div>";

														} else {

															var status = 1;

															var finallist2 = "<div class='am-tabs-bd'><div  class='am-tab-panel am-fade am-in am-active' id='tab1'><div class='am-panel-bd' >"
																	+ " <div class='am-ckdd'>"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>工厂名称:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["doc_factoryname"]
																	+ "</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4 '>订单编号:</div><div class='am-u-sm-8 '>"
																	+ data["jsonorderlists"][i]["fac_order_no"]
																	+ "</div></div>	"
																	+ "<div class='am-g am-button '><div class='am-u-sm-4'>商品名称:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["shopgoodsname"]
																	+ "</div></div>"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>订单数量:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["data"]
																	+ "(吨)</div></div>	"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>车牌号码:</div><div class='am-u-sm-8'>"
																	+ data["jsonorderlists"][i]["drivertracknumber"]
																	+ "</div></div>	"

																	+ "<div class='am-g am-button'><div class='am-u-sm-4 '>提货日期:</div><div class='am-u-sm-8 '>"
																	+ data["jsonorderlists"][i]["thdate"]
																	+ "</div></div>"
																	+ "<div class='am-g am-button'><div class='am-u-sm-4'>状态:</div><div class='am-u-sm-8'>"
																	+ "<button type='button'  class='am-btn am-qx am-btn-success am-radius ' >订单已出厂</button></div></div></div></div></div>"
																	+ +"<div  class='am-tab-panel am-fade ' id='tab2'>"
																	+ " <figure data-am-widget='figure' class='am am-figure am-figure-default' data-am-figure=\"{ pureview: '"
																	+ true
																	+ "' }\">"
																	+ "<img src='static/weixin/img/success.jpg' /><figcaption class='am-figure-capition-btm'>"
																	+ " 提货单号： "
																	+ data["jsonorderlists"][i]["ordernumber"]
																	+ "</br><span>"
																	+ data["jsonorderlists"][i]["doc_factoryname"]
																	+ "</span><br /> </figcaption></figure></div>"
															"</div></div>";
														}
														finallist = finallist1
																+ finallist2;
													}

													$("#orderlist").append(
															finallist);
													document
															.getElementById('currentPage').value = data["currentPage"];
													document
															.getElementById('editOrder').value = o_id;
													document
															.getElementById('status').value = status;
													$("#loading").button(
															'reset');
												},
												error : function() {
													$("#loading").button(
															'reset');
													swal("", "服务异常", "error");
													window.location.href = locat
															+ "/clientUser/tologin";
												}
											});
						}

					});
}

function editOrder(id, o_id) {
	var status = Number($("#status").val());
	swal({
		title : "您确定要取消吗？",
		text : "您确定要取消这个订单吗？",
		type : "warning",
		showCancelButton : true,
		closeOnConfirm : false,
		cancelButtonText : "取消",
		confirmButtonText : "确定",
		confirmButtonColor : "#ec6c62"
	}, function() {
		var cookie = $.AMUI.utils.cookie;
		var companyid = (cookie.get("companyid") == null) ? "" : cookie
				.get("companyid");
		$.getJSON("static/weixin/json/" + companyid + ".json", function(data) {
			var locat = data["url"];
			$.ajax(locat + '/clientOrder/order_cancel?o_id=' + o_id, {
				xhrFields : {
					withCredentials : true,
					useDefaultXhrHeader : false
				},
				crossDomain : true,
				type : 'GET',
				timeout : 10000,
				success : function(data, status, xhr) {
					$('#' + id).css({
						"background-color" : "red",
						"border" : "none"
					}).text("订单已取消");
					$('#123' + id).attr('src', 'static/weixin/img/error.jpg');
					$('#' + id).removeAttr('onclick');
					swal("订单已取消");
				},
				error : function(data) {
					swal("服务或网络异常，稍后再试！", "", "error");
				}
			});
		});
	})
}
