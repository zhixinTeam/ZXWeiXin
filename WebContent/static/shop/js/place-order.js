//身份证号码的验证规则
        function isIdCardNo(num){ 
        　　 var len = num.length,re; 
        　　 if (len == 15) 
        　　 		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/); 
        　　 else if (len == 18) 
        　　		 re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/); 
        　　 else {
                //alert("输入的数字位数不对。"); 
                return false;
            } 
        　　 var a = num.match(re); 
        　　 if (a != null) 
        　　 { 
		        　　 if (len==15) 
		        　　 { 
		        　　 var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]); 
		        　　 var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
		        　　}
		        　　 else 
		        　　 { 
		        　　 var D = new Date(a[3]+"/"+a[4]+"/"+a[5]); 
		        　　 var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
		        　　 } 
		        　　 if (!B) {
		                //alert("输入的身份证号 "+ a[0] +" 里出生日期不对。"); 
		                return false;
		            } 
        　　 } 
        　　 if(!re.test(num)){
                //alert("身份证最后一位只能是数字和字母。");
                return false;
            }
        　　 return true; 
        } 
    
      //车牌号校验
        function isPlateNo(plateNo){
            var re = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
            if(re.test(plateNo)){
                return true;
            }
            return false;
        }
    
		
		
		function checkDun(snkh,snxh,factoryid){
			var return_data="0";
			var url = "<%=basePath%>shop/check_dun?tm="+new Date().getTime();
			
	        $.ajax({
			    type: "post", 
			    cache: false,  
			    async: false,
			    url: url,  
			    timeout:10000,
			    data:{
			    	snkh:snkh,
			    	snxh:snxh,
			    	factoryid:factoryid
  				},
			    dataType: "json", 
			    error: function(XMLHttpRequest, textStatus, errorThrown){
					 alert("error");
                    
				         }, 
			    success: function (data) { 
			    	if(data["msg"]=="ok"){
			    		return_data= data["maxtonnage"];
			    	}
			    }  
		    }); 
	       
			return return_data;
		}
		
		
		
    
		 function save(){
			 
			 
			 
			
			 /*var factoryid =$("#factoryid").val();
			 var snkh =$("#snkh").val();
			 var snxh =$("#snxh").val();
			
			  if(factoryid=="0"){
					//bootbox.alert("请选择工厂!");
					//$("#factoryid").focus();

					$("#factoryid").closest('div').removeClass('success').addClass('error');
					document.getElementById('factoryid').autofocus="on";
					return false;
				}
			  var fac_order_no =$("#fac_order_no").val();
		    	if(fac_order_no==""){
					bootbox.alert("请输入订单编号!");
					return false;
				}
			 if(snkh=="0"){
					bootbox.alert("请选择客户!");
					$("#snkh").focus();
					return false;
				}
			 if($("#thsj").val()==""){
					bootbox.alert("提货日期不能为空!");
					$("#thsj").focus();
					return false;
				}
			 if( HQDate()>$("#thsj").val()){
				 bootbox.alert("提货日期选择今天之后的日期!");
					$("#thsj").focus();
					return false;
			 }
			 if(snxh=="0"){
					bootbox.alert("请选择水泥型号!");
					$("#snxh").focus();
					return false;
				}*/
			 if($("#drivername").val()==""){
					bootbox.alert("司机名称不能为空!");
					$("#drivername").focus();
					return false;
				}
			/* var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
			 if($("#driverphone").val()==""){
					bootbox.alert("手机号不能为空!");
					$("#driverphone").focus();
					return false;
				}else if($("#driverphone").val().length != 11 && !myreg.test($("#driverphone").val())){
					bootbox.alert("手机号格式不正确!");
					$("#driverphone").focus();
					return false;
				}
			 
			 
			 
			 if($("#tracknumber").val()==""){
					bootbox.alert("车牌号不能为空!");
					$("#tracknumber").focus();
					return false;
				}
			 
			 if($("#goodsnumber").val()==""){
					bootbox.alert("购买量不能为空!");
					$("#goodsnumber").focus();
					return false;
				}else if(200<Number($("#goodsnumber").val())){
					bootbox.alert("提货单吨数不能超过60吨!");
					$("#goodsnumber").focus();
					return false;
			}  
			
			  if($("#idnumber").val()=="0"){
					bootbox.alert("身份证号不能为空!");
					$("#idnumber").focus();
					return false;
				}else {
					if (IdentityCodeValid($("#idnumber").val())==false){
						$("#idnumber").focus();
						return false;
					} 
					$("#idnumber").focus();
					return false;  
				} */
			  
			 
			document.getElementById('goodsname').value=$("#snxh").find("option:selected").text();
			var id = "body-loading";
			$(window).loading({action:"open",id:id,font:40});  
			$("#order_form").submit();
			var time = setTimeout(function(){
 				//关闭
 				$(window).loading({action:"close",id:id});
 			},1000);
			/*<%-- var id = "body-loading";
			$(window).loading({action:"open",id:id,font:40});
			var time = setTimeout(function(){
 				//关闭
 				$(window).loading({action:"close",id:id});
 			},11000);
			var return_data="0";
			var url = "<%=basePath%>shop/check_dun?tm="+new Date().getTime();
	        $.ajax({
	        	type: "POST",
				url: url,
				 data:{snkh:snkh,snxh:snxh,factoryid:factoryid},
		    	timeout: 10000,
				dataType:'json',
				cache: false,
			    error: function(XMLHttpRequest, textStatus, errorThrown){
				         }, 
			    success: function (data) {
			    	if(data["msg"]=="ok"){
			    		return_data= data["maxtonnage"];
			    	}
			    	 
			    }  
		    }); 
			var time = setTimeout(function(){
			var num =parseFloat(return_data); 
			if(num==0){
					
			    	bootbox.alert("工厂服务异常，请稍后再试!");
					return false;
			}else if(num<Number($("#goodsnumber").val())){
				
					bootbox.alert("提货单吨数超出订单总量!");
					return false;
			}  
			$("#order_form").submit();
			},11000); --%>
			*/
			
			}
		 
		 
		 function rest(){
			 	document.getElementById('factoryid').value="";
			 	document.getElementById('fac_order_no').value=""
			 	document.getElementById('snkh').value="";
			 	document.getElementById('snxh').value="";
			 	document.getElementById('driverid').value="";
			 	document.getElementById('tracknumber').value="";
			 	document.getElementById('drivername').value="";
			 	document.getElementById('driverphone').value="";
			 	document.getElementById('goodsnumber').value= "";
			 	document.getElementById('idnumber').value="";
			 	document.getElementById('thsj').value= "";
		 }
    
		 
		 
		