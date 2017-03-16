
		
//加载 大票号		
function findorderlist(c_id,clientnumber,clientname,factoryid){
		var biaoshi=document.getElementsByName("khf_biaoshi"+clientnumber);
		if((($("#flag").val().indexOf('div_'+clientnumber) < 0)||biaoshi.length==0)){
			var id = "body-loading";
	  		$(window).loading({action:"open",id:id,font:40}); 
	  		
			$.getJSON ("static/shop_pc/json/locat.json", function (data) {
					var  locat=data["url"];
		        	$.ajax(locat+"/clientUser/ordr_list", {
			            type: "GET",
			            xhrFields: {
			                withCredentials: true,
			                useDefaultXhrHeader: false
			            },
			            data:{
			            	clientnumber:clientnumber,
			            	factoryid:factoryid
			            },
			            crossDomain: true,
			            success: function(data, status, xhr) {
			            		var time = setTimeout(function(){
				   				//关闭
				   					$(window).loading({action:"close",id:id});
				   				},100);
								if("error" == data["msg"]) 
									window.location.href=locat+"/shop/login_toLogin";
								else{
									var finallist="";
									if(data["json_card_orders"].length ==0){
										
									
										sweetAlert("订单获取失败", "请先去工厂下单", "error");
										finallist+="<tr name='khf_biaoshi"+data["clientnumber"]+"' >" +
													"<td class='am-text-center' style='color:blue'>无"+
													"<td class='am-text-center'><span class=\"am-time\">无</span><br /></td>"+
													"<td class='am-text-center'><span class=\"am-spmc\">无</span><br /></td>"+
													"<td class='am-text-center'><span class=\"am-sysl\">0吨</span><br /></td>"+
													"<td class='am-text-center'><button type='button' class='am-btn am-btn-primary am-btn-xs am-btn-warning'>剩余数量为0，不能提货</button></td>"+
													"</tr>";
													
										
									}
									else{
										for(i = data["json_card_orders"].length - 1; i >= 0; i--) {
											if(data["json_card_orders"][i]["maxnumber"]==0){
												finallist+="<tr name='khf_biaoshi"+data["clientnumber"]+"'>" +
												"<td class='am-text-center' style='color:blue'>"+data["json_card_orders"][i]["billnumber"]+
												"<td class='am-text-center'><span class=\"am-time\">"+data["json_card_orders"][i]["setdate"]+"</span><br /></td>"+
												"<td class='am-text-center'><span class=\"am-spmc\">"+data["json_card_orders"][i]["stockname"]+"</span><br /></td>"+
												"<td class='am-text-center'><span class=\"am-sysl\">"+data["json_card_orders"][i]["maxnumber"]+"</span><br /></td>"+
												"<td class='am-text-center'><button type='button' class='am-btn am-btn-primary am-btn-xs am-btn-warning'>剩余数量为0，不能提货</button></td>"+
												"</tr>";
											}else{
												finallist+="<tr name='khf_biaoshi"+data["clientnumber"]+"'>" +
												"<td class='am-text-center' style='color:blue'>"+data["json_card_orders"][i]["billnumber"]+
												"<td class='am-text-center'><span class=\"am-time\">"+data["json_card_orders"][i]["setdate"]+"</span><br /></td>"+
												"<td class='am-text-center'><span class=\"am-spmc\">"+data["json_card_orders"][i]["stockname"]+"</span><br /></td>"+
												"<td class='am-text-center'><span class=\"am-sysl\">"+data["json_card_orders"][i]["maxnumber"]+"</span><br /></td>"+
												"<td class='am-text-center'><button type='button' class='am-btn am-btn-primary am-btn-xs am-radius'onclick='place_order(\"" + data['json_card_orders'][i]['stockno'] + "\",\"" + data['json_card_orders'][i]['stockname'] + "\",\"" + data['json_card_orders'][i]['billnumber'] + "\",\"" + data['json_card_orders'][i]['maxnumber'] + "\",\"" + clientname+ "\" ,\"" + clientnumber+ "\" ,\"" + c_id+ "\")'>我要提货</button></td>"+
												"</tr>";
												
											}
										    
										}
									}
									$("#flag").val('div_'+clientnumber+","+$("#flag").val());//flag 赋值;
									$('#div_'+clientnumber).empty(); //移除节点内的子元素
									$('#div_'+clientnumber).append(finallist);
								}
						
				            },
				            error:function(data){
				            	var time = setTimeout(function(){
				   				//关闭
				   					$(window).loading({action:"close",id:id});
				   				},100);
				            	swal("","服务异常","error");
				            	window.location.href=locat+"/shop/login_toLogin";
				            }
		        	});
	    	});  
		}else {
			var key_flag =document.getElementsByName("key_flag");
			//什么都不做；
		}
		
}

//进入下单页面
function place_order(stockno,stockname,billnumber,maxnumber,clientname,clientnumber,c_id){
	var cookie=  $.AMUI.utils.cookie;
	var time=new Date();
	time=new Date(time.valueOf() +2*60*1000);
	var factoryName =$("#factoryName").val();
	cookie.set("stockno",stockno,time,"/");
	cookie.set("stockname",escape(stockname),time,"/");
	cookie.set("billnumber",billnumber,time,"/");
	cookie.set("maxnumber",maxnumber,time,"/");
	cookie.set("clientname",escape(clientname),time,"/");
	cookie.set("clientnumber",clientnumber,time,"/");
	cookie.set("c_id",c_id,time,"/");
	$.getJSON ("static/shop_pc/json/locat.json", function (data) {
		var  locat=data["url"];
		window.location.href=locat+"/shop/toxthd";
	})
	
}

//新增大票号
function addorder(){
	sweetAlert("网上下单业务未开启", "", "error");
}


function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}


