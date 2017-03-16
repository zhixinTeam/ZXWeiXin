
		
//加载 大票号		
function findorderlist(c_id,clientnumber,clientname,factoryid){
		var biaoshi=document.getElementsByName("khf_biaoshi"+clientnumber);
		if((($("#flag").val().indexOf('div_'+clientnumber) < 0)||biaoshi.length==0)){
			var id = "body-loading";
	  		$(window).loading({action:"open",id:id,font:40}); 
	  		var cookie=  $.AMUI.utils.cookie;
	  		var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
			$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
					var  locat=data["url"];
		        	$.ajax(locat+"/clientUser/ordr_list?clientnumber="+clientnumber+"&factoryid="+factoryid, {
			            type: "GET",
			            xhrFields: {
			                withCredentials: true,
			                useDefaultXhrHeader: false
			            },
			            crossDomain: true,
			            success: function(data, status, xhr) {
			            		var time = setTimeout(function(){
				   				//关闭
				   					$(window).loading({action:"close",id:id});
				   				},100);
								if("error" == data["msg"]) 
									window.location.href=locat+"/clientUser/tologin";
								else{
									var finallist=" <ul class=\"am-list\">   	";
									if(data["json_card_orders"].length ==0){
										
									
										sweetAlert("订单获取失败", "请先去工厂下单", "error");
										finallist+="<li class=\"am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left\">  "+  
													      "  <div class=\" am-u-sm-12 am-list-main\" name='khf_biaoshi"+data["clientnumber"]+"' >"+
													      "      <h3 class=\"am-list-item-hd\">"+
													      "      	<a href=\"#\"  disabled='true'>订单编号：无</a>"+
													      "      </h3>"+
													      "      <div class=\"am-list-item-texta\">"+
													      "      	<span class=\"am-time\">开票时间：无</span><br />"+
													      "      	<span class=\"am-spmc\">商品名称：无</span><br />"+
													      "      	<span class=\"am-sysl\">剩余数量：0吨</span><br />    "+        	          	
													      "      	</div>"+
													      "  </div>"+
													     " </li>";
									}
									else{
										
										for(i = data["json_card_orders"].length - 1; i >= 0; i--) {
											if(data["json_card_orders"][i]["maxnumber"]==0){
												finallist+="<li class=\"am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left\">  "+  
											      "  <div class=\" am-u-sm-12 am-list-main\" name='khf_biaoshi"+data["clientnumber"]+"'  >"+
											      "      <h3 class=\"am-list-item-hd\">"+
											      "      	<a href=\"#\" disabled='true' class=\"\">订单编号："+data["json_card_orders"][i]["billnumber"]+"</a>"+
											      "      </h3>"+
											      "      <div class=\"am-list-item-texta\">"+
											      "      	<span class=\"am-time\">开票时间："+data["json_card_orders"][i]["setdate"]+"</span><br />"+
											      "      	<span class=\"am-spmc\">商品名称："+data["json_card_orders"][i]["stockname"]+"</span><br />"+
											      "      	<span class=\"am-sysl\">剩余数量："+data["json_card_orders"][i]["maxnumber"]+"吨</span><br />    "+        	          	
											      "      	</div>"+
											      "      	<button type=\"button\"  class=\"am-btn am-th am-radius am-fr am-btn-bom am-btn-block am-btn-warning\" >剩余数量为0，不能提货</button>	"+
											      "  </div>"+
											     " </li>";
											}else{
												finallist+="<li class=\"am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left\">  "+  
											      "  <div class=\" am-u-sm-12 am-list-main\" name='khf_biaoshi"+data["clientnumber"]+"'  >"+
											      "      <h3 class=\"am-list-item-hd\">"+
											      "      	<a href=\"#\" disabled='true' class=\"\">订单编号："+data["json_card_orders"][i]["billnumber"]+"</a>"+
											      "      </h3>"+
											      "      <div class=\"am-list-item-texta\">"+
											      "      	<span class=\"am-time\">开票时间："+data["json_card_orders"][i]["setdate"]+"</span><br />"+
											      "      	<span class=\"am-spmc\">商品名称："+data["json_card_orders"][i]["stockname"]+"</span><br />"+
											      "      	<span class=\"am-sysl\">剩余数量："+data["json_card_orders"][i]["maxnumber"]+"吨</span><br />    "+        	          	
											      "      	</div>"+
											      "      	<button type=\"button\" onclick='place_order(\"" + data['json_card_orders'][i]['stockno'] + "\",\"" + data['json_card_orders'][i]['stockname'] + "\",\"" + data['json_card_orders'][i]['billnumber'] + "\",\"" + data['json_card_orders'][i]['maxnumber'] + "\",\"" + clientname+ "\" ,\"" + clientnumber+ "\" ,\"" + c_id+ "\"       )' class=\"am-btn am-th am-radius am-fr am-btn-bom am-btn-block am-btn-secondary\">我要提货</button>	"+
											     
											      "  </div>"+
											     " </li>";
											}
										    
										}
									}
									finallist+="</ul>";
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
				            	window.location.href=locat+"/clientUser/tologin";
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
	cookie.set("stockno",stockno,time,"/");
	cookie.set("stockname",escape(stockname),time,"/");
	cookie.set("billnumber",billnumber,time,"/");
	cookie.set("maxnumber",maxnumber,time,"/");
	cookie.set("clientname",escape(clientname),time,"/");
	cookie.set("clientnumber",clientnumber,time,"/");
	cookie.set("c_id",c_id,time,"/");
	var companyid=(cookie.get("companyid")==null)?"":cookie.get("companyid");
	$.getJSON ("static/weixin/json/"+companyid+".json", function (data) {
		var  locat=data["url"];
		window.location.href=locat+"/clientOrder/toxthd";
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


