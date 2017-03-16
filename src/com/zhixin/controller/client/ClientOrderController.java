package com.zhixin.controller.client;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Json_ShopOrder;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Eventmsg;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.PathUtil;
import com.zhixin.right_utils.Tools;
import com.zhixin.right_utils.TwoDimensionCode;
import com.zhixin.service.FactoryService;
import com.zhixin.service.WebserviceService;
import com.zhixin.service.WxBindCustomerService;
import com.zhixin.service.client.ClientOrderService;
import com.zhixin.service.client.ClientUserService;
import com.zhixin.service.shop.ShopGoodsService;
import com.zhixin.service.shop.ShopUserService;
import com.zhixin.tools.MakeOrderNum;
import com.zhixin.tools.TimestampUtil;
import com.zhixin.tools.TokenProccessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/clientOrder")
public class ClientOrderController extends BaseController {

	@Resource(name = "clientorderService")
	private ClientOrderService clientorderService;

	@Resource(name = "clientuserService")
	private ClientUserService clientuserService;

	/*@Resource(name = "shoporderService")
	private ShopOrderService shoporderService;*/

	@Resource(name = "shopuserService")
	private ShopUserService shopuserService;

	@Resource(name = "factoryService")
	private FactoryService factoryService;
/*
	@Resource(name = "shopclientService")
	private ShopClientService shopclientService;*/

	@Resource(name = "shopgoodsService")
	private ShopGoodsService shopgoodsService;

	@Resource(name = "clientorderService")
	private ClientOrderService clientOrderService;

	@Resource(name="webserviceservice")
	private WebserviceService webserviceservice;
	
	
	@Resource(name="wxbindcustomerService")
	private  WxBindCustomerService wxbindcustomerService;
	/**
	 * 去历史订单页面
	 * @return
	 */
	@RequestMapping(value="/tolsdd")
	public ModelAndView toUser()throws Exception{
		logBefore(logger, "ClientOrderController_lsdd");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("weixin/user/lsdd");
		return mv;
	}
	
	
	
	/**
	 * app下提货单页面 1.若没有登录，先进入登录界面
	 *
	 */
	@ResponseBody
	@RequestMapping("/order_list")
	public void order_list(HttpServletResponse response, String currentPage, HttpServletRequest request)
			throws Exception {
		response.setHeader("P3P","CP=CAO PSA OUR");
		currentPage = request.getParameter("currentPage");
		PageData pd = new PageData();
		pd = this.getPageData();
		if (currentPage == "" || currentPage == null) {
			currentPage = "1";
		}
		int current = Integer.parseInt(currentPage);
		if (current <= 0) {
			currentPage = "1";
		}
		JSONObject jo = new JSONObject();
		jo.put("msg", "success");
		String encoderImgId = "";
		try {
			
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession(); 
			Wx_BindCustomer wx_BindCustomer = (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
			if(wx_BindCustomer==null){
				jo.put("msg", "error");
			}
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			if (wx_BindCustomer != null) {
				List<Json_ShopOrder> jsonorderlist = clientOrderService.listOrders(currentPage, wx_BindCustomer.getId());
				if (jsonorderlist.size() != 0) {
					Json_ShopOrder json_shopOrder = jsonorderlist.get(0);
					JSONArray jsonorderlists = JSONArray.fromObject(jsonorderlist);
					jo.put("jsonorderlists", jsonorderlists);
					String orderNO = json_shopOrder.getOrdernumber();
					String errInfo = "success";
					if (null == orderNO || "".equals(orderNO)) {
						errInfo = "error";
					} else {
						encoderImgId = orderNO + ".png";
						try {
							String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + encoderImgId; // 存放路径
							// 如果已经生成二维码，不在生成
							File file = new File(filePath);
							if (file.exists()) {
							} else {
								com.zhixin.tools.QRCodeUtil.encode(orderNO, "", filePath, true); // 执行生成二维码
							}
						} catch (Exception e) {
							errInfo = "error";
						}
					}
				}else{
					jo.put("message", "0");
				}
				
			} else {
				jo.put("msg", "error");

			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jo.put("currentPage", currentPage);
			jo.put("encoderImgId", encoderImgId);
			PrintWriter out;
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.write(jo.toString());
			out.flush();
			out.close();
		}

	}

	
	@ResponseBody
	@RequestMapping("/order_cancel")
	public void editOrder(HttpServletResponse response,String o_id, HttpServletRequest request){
		response.setHeader("P3P","CP=CAO PSA OUR");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Wx_BindCustomer wx_BindCustomer  = (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
		JSONObject jo = new JSONObject();
		Shop_Order shop_Order=null;
		if(wx_BindCustomer!=null){
			o_id=request.getParameter("o_id");
			shop_Order=clientorderService.oneOrder(o_id);
			shop_Order.setStatus(2);
			clientorderService.updateShopOrder(shop_Order);
		
			
			//开始发送撤销订单通知的信息
			String driverid=shop_Order.getShopdriver().getD_id();
			String factoryid=shop_Order.getDoc_factory().getId();
			Shop_Client client =shopuserService.findShopClientByID(shop_Order.getShopclient().getC_id());
			Shop_Driver driver = shopuserService.findDriverByID(driverid);
			 Set<Wx_BindCustomer> list_sjcustomers  =	wxbindcustomerService.findBindCustomerFactoryID(driver.getTracknumber(), factoryid);
			 System.out.println(list_sjcustomers);	
			 JSONObject jobject = new JSONObject();
				if(wx_BindCustomer.getOpenid() !=null){
					for(Wx_BindCustomer bindcustomer : list_sjcustomers){
						if(bindcustomer.getOpenid().equals(wx_BindCustomer.getOpenid()))
								System.out.println("");
							else{
								jobject.put("CusName", shop_Order.getShopclient().getClientname());
								jobject.put("stockno", shop_Order.getOrdernumber());
								jobject.put("billid", shop_Order.getShopgoods().getGoodsID());
								jobject.put("stockname", shop_Order.getShopgoods().getGoodsname());
								//jobject.put("billnumber", billnumber);
								//jobject.put("goodsnumber", goodsnumber);
								//jobject.put("thrq", thrq);
								//Map<String, String> map1 = new HashMap<String, String>();
								
								X_Eventmsg x_eventmsg = new X_Eventmsg();
								x_eventmsg.setReviceuser(bindcustomer.getOpenid());
								x_eventmsg.setContent(jobject.toString());
								//x_eventmsg.setCreateTime((String) mapparam.get(""));
								//根据传过来的type找到对应的模版id
								
								x_eventmsg.setFactoryid(factoryid);
								x_eventmsg.setCreateTime(System.currentTimeMillis());
								//x_eventmsg.setEvent(jb.get("event").toString());
								x_eventmsg.setMsgType("4");
								//x_eventmsg.setPicurl((String) mapparam.get("picurl"));
								x_eventmsg.setSendcount(0);
								x_eventmsg.setIssend(0);
								webserviceservice.saveeventMsg(x_eventmsg);
							}
						
					}
					jobject.put("CusName", shop_Order.getShopclient().getClientname());
					jobject.put("stockno", shop_Order.getOrdernumber());
					jobject.put("billid", shop_Order.getShopgoods().getGoodsID());
					jobject.put("stockname", shop_Order.getShopgoods().getGoodsname());
					//Map<String, String> map1 = new HashMap<String, String>();
					
					X_Eventmsg x_eventmsg = new X_Eventmsg();
					x_eventmsg.setReviceuser(client.getClientnumber());
					x_eventmsg.setContent(jobject.toString());
					//x_eventmsg.setCreateTime((String) mapparam.get(""));
					//根据传过来的type找到对应的模版id
					
					x_eventmsg.setFactoryid(factoryid);
					x_eventmsg.setCreateTime(System.currentTimeMillis());
					//x_eventmsg.setEvent(jb.get("event").toString());
					x_eventmsg.setMsgType("4");
					//x_eventmsg.setPicurl((String) mapparam.get("picurl"));
					x_eventmsg.setSendcount(0);
					x_eventmsg.setIssend(0);
					webserviceservice.saveeventMsg(x_eventmsg);
					
					
				}
			
			jo.put("msg", "success");
		}else{
			jo.put("msg", "error");
		}
		
		
	
		
	}
	
	@RequestMapping(value="/toxthd")
	public ModelAndView toxthd()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Wx_BindCustomer wx_BindCustomer  = (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
		String  customerid ="";
		if(wx_BindCustomer !=null){
			  customerid =wx_BindCustomer.getId();
		}
		List<Shop_Driver> list_drivers =clientorderService.findDriversByCustomerId(customerid);
		Set<Shop_Driver>   set_drivers = new HashSet();
		for(Shop_Driver driver:list_drivers){
			set_drivers.add(new Shop_Driver(driver.getD_id(),driver.getTracknumber()));
		}
		mv.addObject("pd",pd);
		mv.addObject("list_drivers",set_drivers);
		mv.setViewName("weixin/xthd/xthd");
		return mv;
	}
	
	/**
	 * app下单
	 */
	@RequestMapping(value="/place_order")
	@ResponseBody
	public void place_order(HttpServletResponse response){
		logBefore(logger, "SysUserController_deleteU");
		response.setHeader("P3P","CP=CAO PSA OUR");
		PageData pd = new PageData();
		JSONObject jo = new JSONObject();
		try{
			 pd = this.getPageData();
			 Subject currentUser = SecurityUtils.getSubject();

			 Session session = currentUser.getSession();
			 Wx_BindCustomer wx_BindCustomer  = (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
				String  customerid ="";
				if(wx_BindCustomer !=null){
					  	customerid =wx_BindCustomer.getId();
					    String 	c_id =pd.getString("c_id");
					    String 	stockno =pd.getString("stockno");
					    String 	stockname =pd.getString("stockname");
					    String 	driverid =pd.getString("driverid");
					    System.out.println(driverid+"driverid是不是车牌号");
					    String 	clientname =pd.getString("clientname");
					    String billnumber =pd.getString("billnumber");
					    String 	goodsnumber =pd.getString("goodsnumber");
					    String 	thrq =pd.getString("thrq");
					    //pc端进入下单从session中获取 factory
					    Doc_Factory factroy  = (Doc_Factory) session.getAttribute(Const.SESSION_SHOPFACTORY);
					    String factoryid ="";
						if(factroy==null){
							 factoryid = pd.getString("factoryid");
						}else
							factoryid =factroy.getId();
						//如果数据库里没有商品，商品添加到数据库中
						String g_id =shopgoodsService.updateShopGoods(factoryid,stockno,stockname);
						Shop_Driver driver = null;
						driver =	 shopuserService.findDriverByID(driverid);
						Shop_Order shop_order = new Shop_Order();
						 shop_order.setOrderdate(TimestampUtil.getnowtime());
						 String thorderno=MakeOrderNum.makeOrderNum();
						 shop_order.setOrdernumber(thorderno);
						 shop_order.setBindcustmoer(wx_BindCustomer);
						 shop_order.setShopdriver(driver);
						 shop_order.setData(goodsnumber);
						 shop_order.setStatus(0);
						 shop_order.setThdate(thrq);
						 shop_order.setFac_order_no(billnumber);
						 String orderid = shopuserService.saveShopOrder(shop_order);
						 shopuserService.updateOrderfac(orderid,factoryid,c_id,g_id);
						 jo.put("msg","success" );
						 
						 Shop_Client client =shopuserService.findShopClientByID(c_id);
						 
						//下单成功发送模板消息 给下单人员与司机发消息
						 System.out.println("发消息");
						
						 Set<Wx_BindCustomer> list_sjcustomers  =	wxbindcustomerService.findBindCustomerFactoryID(driver.getTracknumber(), factoryid);
						 System.out.println(list_sjcustomers);	
						 JSONObject jobject = new JSONObject();
							if(wx_BindCustomer.getOpenid() !=null){
								for(Wx_BindCustomer bindcustomer : list_sjcustomers){
									if(bindcustomer.getOpenid().equals(wx_BindCustomer.getOpenid()))
											System.out.println("");
										else{
											jobject.put("thorderno", thorderno);
											jobject.put("clientname", clientname);
											jobject.put("stockname", stockname);
											jobject.put("billnumber", billnumber);
											jobject.put("goodsnumber", goodsnumber);
											jobject.put("thrq", thrq);
											//Map<String, String> map1 = new HashMap<String, String>();
											
											X_Eventmsg x_eventmsg = new X_Eventmsg();
											x_eventmsg.setReviceuser(bindcustomer.getOpenid());
											x_eventmsg.setContent(jobject.toString());
											//x_eventmsg.setCreateTime((String) mapparam.get(""));
											//根据传过来的type找到对应的模版id
											
											x_eventmsg.setFactoryid(factoryid);
											x_eventmsg.setCreateTime(System.currentTimeMillis());
											//x_eventmsg.setEvent(jb.get("event").toString());
											x_eventmsg.setMsgType("5");
											//x_eventmsg.setPicurl((String) mapparam.get("picurl"));
											x_eventmsg.setSendcount(0);
											x_eventmsg.setIssend(0);
											webserviceservice.saveeventMsg(x_eventmsg);
										}
									
								}
								jobject.put("thorderno", thorderno);
								jobject.put("clientname", clientname);
								jobject.put("stockname", stockname);
								jobject.put("billnumber", billnumber);
								jobject.put("goodsnumber", goodsnumber);
								jobject.put("thrq", thrq);
								//Map<String, String> map1 = new HashMap<String, String>();
								
								X_Eventmsg x_eventmsg = new X_Eventmsg();
								x_eventmsg.setReviceuser(client.getClientnumber());
								x_eventmsg.setContent(jobject.toString());
								//x_eventmsg.setCreateTime((String) mapparam.get(""));
								//根据传过来的type找到对应的模版id
								
								x_eventmsg.setFactoryid(factoryid);
								x_eventmsg.setCreateTime(System.currentTimeMillis());
								//x_eventmsg.setEvent(jb.get("event").toString());
								x_eventmsg.setMsgType("5");
								//x_eventmsg.setPicurl((String) mapparam.get("picurl"));
								x_eventmsg.setSendcount(0);
								x_eventmsg.setIssend(0);
								webserviceservice.saveeventMsg(x_eventmsg);
								
								
							}
						 
			}else{
				jo.put("msg","error" );
			}
				
				PrintWriter out;
				 response.setContentType("application/json;charset=utf-8");
				 response.setCharacterEncoding("utf-8");
				 out = response.getWriter();
				 out.write(jo.toString());
				 
				 
				 out.flush();
				 out.close();
			 
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			
			
			logAfter(logger);
		}
		
	}

}
