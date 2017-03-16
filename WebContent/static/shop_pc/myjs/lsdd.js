$(document)
		.ready(
				function() {

					
						$
						.getJSON(
								"static/shop_pc/json/locat.json",
								function(data) {
									var locat= data["url"];
									$
											.ajax(
													locat
															+ '/shop/listOrders'
															,
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
																+ "/shop/login_toLogin";
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
																						+ "/shop/totjsja";
																						
																			})
																} else {
																	var finallist = '';
																	var o_id = '';
																	for (i =0; i <=data["jsonorderlists"].length - 1 ; i++) {

																		var finallist1 = "<tr>"
																				+ "<td class='am-text-center'>"
																				+ data["jsonorderlists"][i]["fac_order_no"]
																				+ "</td>"
																				+ "<td class='am-text-center'>"
																				+ data["jsonorderlists"][i]["doc_factoryname"]
																				+ "</td>"
																				+ "<td class='am-text-center'>"
																				+ data["jsonorderlists"][i]["shopgoodsname"]
																				+ "</td>"
																				+ "<td class='am-text-center'>"
																				+ data["jsonorderlists"][i]["shopdrivername"]
																				+ "</td>"
																				+ "<td class='am-text-center'>"
																				+ data["jsonorderlists"][i]["thdate"]
																				+ "</td>"
																				+ "<td class='am-text-center'>"
																				+ data["jsonorderlists"][i]["data"]
																				+ "</td>"
																				+ "<td class='am-text-center'>"
																				+ data["jsonorderlists"][i]["drivertracknumber"]
																				+ "</td>";
																		if (data["jsonorderlists"][i]["status"] == 0) {
																			var status = 0;
																			var finallist2 = "<td class='am-text-center'>"
																					+ "<button  type='button' class='am-btn am-btn-secondary am-btn-xs am-radius'  "
																					+ "data-am-modal=\"{target: '#1234"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "', closeViaDimmer: 0, width: 450, height: 450}\">查看</button>"
																					+ " <div class='am-modal am-modal-no-btn' tabindex='-1' id='1234"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "'>"
																					+ " <div class='am-modal-dialog'> <div class='am-modal-hd'>"
																					+ "<a href='javascript: void(0)' class='am-close' data-am-modal-close>&times;</a> </div>"
																					+ " <div class='am-modal-bd'><img src='"
																					+ locat
																					+ "/uploadFiles/twoDimensionCode/"
																					+ data["encoderImgId"][i]
																					+ "' id='123"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "'></div>"
																					+ "<div class='am-modal-hd'>"
																					+ data["jsonorderlists"][i]["doc_factoryname"]
																					+ "<br/>"
																					+ "订单编号:"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "</div></div></div></td>"
																					+ " <td class='am-text-center'><button id='"
																					+ data['jsonorderlists'][i]['ordernumber']
																					+ "' "
																					+ "type='button' class='am-btn am-btn-primary am-btn-xs am-radius' onclick='editOrder(\""
																					+ data['jsonorderlists'][i]['ordernumber']
																					+ "\",\""
																					+ data['jsonorderlists'][i]['o_id']
																					+ "\")'>取消</button>"
																					+ "</td></tr>";
																		} else if (data["jsonorderlists"][i]["status"] == 1) {
																			var status = 1;
																			var finallist2 = "<td class='am-text-center'>"
																					+ "<button  type='button' class='am-btn am-btn-secondary am-btn-xs am-radius'  "
																					+ "data-am-modal=\"{target: '#1234"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "', closeViaDimmer: 0, width: 450, height: 450}\">查看</button>"
																					+ " <div class='am-modal am-modal-no-btn' tabindex='-1' id='1234"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "'>"
																					+ " <div class='am-modal-dialog'> <div class='am-modal-hd'>"
																					+ "<a href='javascript: void(0)' class='am-close' data-am-modal-close>&times;</a> </div>"
																					+ " <div class='am-modal-bd'><img src='static/shop_pc/images/success.jpg'  class='am-img-responsive am-img-center' ></div>"
																					+ "<div class='am-modal-hd'>"
																					+ data["jsonorderlists"][i]["doc_factoryname"]
																					+ "<br/>"
																					+ "订单编号:"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "</div></div></div></td>"
																					+ " <td class='am-text-center'><button  type='button' class='am-btn am-btn-primary am-btn-xs am-radius'>订单已出厂</button>"
																					+ "</td></tr>";
																		} else {
																			var status = 2;
																			var finallist2 = "<td class='am-text-center'>"
																					+ "<button  type='button' class='am-btn am-disabled am-btn-secondary am-btn-xs am-radius'data-am-modal=\"{target: '#1234"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "', closeViaDimmer: 0, width: 450, height: 450}\">查看</button>"
																					+ " <div class='am-modal am-modal-no-btn' tabindex='-1' id='1234"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "'>"
																					+ " <div class='am-modal-dialog'> <div class='am-modal-hd'>"
																					+ "<a href='javascript: void(0)' class='am-close' data-am-modal-close>&times;</a> </div>"
																					+ " <div class='am-modal-bd'><img src='static/shop_pc/images/error.jpg' ></div>"
																					+ "<div class='am-modal-hd'>"
																					+ data["jsonorderlists"][i]["doc_factoryname"]
																					+ "<br/>"
																					+ "订单编号:"
																					+ data["jsonorderlists"][i]["ordernumber"]
																					+ "</div></div></div></td>"
																					+ " <td class='am-text-center'><button  type='button' class='am-btn am-btn-danger am-btn-xs am-radius'>订单已取消</button>"
																					+ "</td></tr>";
																		}
																		finallist = finallist1
																				+ finallist2;
																		var pagecount = data["jsonorderlists"][i]["pageCount"];
																		$(
																				"#orderlist")
																				.append(
																						finallist);

																	}
																	$(
																			'#example')
																			.DataTable();

																}
															}

														},
														error : function(
																data) {
															swal("",
																	"服务异常",
																	"error");
															window.location.href = locat
																	+ "/shop/login_toLogin";

														}
													});

								});
					
					
					

				})

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
		$.getJSON("static/shop_pc/json/locat.json", function(data) {
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
					$('#123' + id).attr('src',
							'static/shop_pc/images/error.jpg');
					$('#' + id).removeAttr('onclick');
					swal("订单已取消","","success");
					/*window.location.href = locat
					+ "/shop/tolsdd";*/
				},
				error : function(data) {
					swal("服务或网络异常，稍后再试！", "", "error");
					window.location.href = locat
					+ "/shop/login_toLogin";
				}
			});
		});
	})
}
