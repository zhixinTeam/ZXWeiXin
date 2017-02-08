package com.zhixin.controller.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tempuri.SrvWebchat;
import org.tempuri.SrvWebchatAction;
import org.tempuri.SrvWebchatActionResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Json_Client;
import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Goods;
import com.zhixin.entity.Page;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.ShopLink_User_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Shop_User;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Eventmsg;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.DateUtil;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.Tools;
import com.zhixin.service.CompanyService;
import com.zhixin.service.FactoryService;
import com.zhixin.service.WebserviceService;
import com.zhixin.service.WxBindCustomerService;
import com.zhixin.service.shop.ShopClientService;
import com.zhixin.service.shop.ShopGoodsService;
import com.zhixin.service.shop.ShopOrderService;
import com.zhixin.service.shop.ShopUserService;
import com.zhixin.tools.MakeOrderNum;
import com.zhixin.tools.ParseXml;
import com.zhixin.tools.RoleUtil;
import com.zhixin.tools.TimestampUtil;
import com.zhixin.tools.TokenProccessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
public class ShopOrderController  extends BaseController{

	@Resource(name="shoporderService")
	private ShopOrderService shoporderService;
	
	
	@Resource(name="shopuserService")
	private ShopUserService shopuserService;
	
	@Resource(name="factoryService")
	private FactoryService factoryService;
	
	@Resource(name="shopclientService")
	private ShopClientService shopclientService;
	
	@Resource(name="shopgoodsService")
	private ShopGoodsService shopgoodsService;
	
	@Resource(name="wxbindcustomerService")
	private  WxBindCustomerService wxbindcustomerService;
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	
	@Resource(name="webserviceservice")
	private WebserviceService webserviceservice;
	
	/**
	 * 显示订单列表
	 */
	@RequestMapping(value="/shop/listOrders")
	public ModelAndView listOrders(Page page)throws Exception{
		logBefore(logger, "SysUserController_listUsers");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);
			if(shopuser !=null){
				Shop_User shopuserr = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSERROL);
				if(null == shopuserr){
					session.setAttribute(Const.SESSION_SHOPUSERROL, shopuser);
				}else{
					shopuser =shopuserr;
				}
				String currentPage="";
				if(pd.toString().contains("currentPage"))
					 currentPage =pd.getString("currentPage");
				else
					currentPage ="1";
				PageBean pageorders = shopuserService.listOrders(currentPage,shopuserr.getU_id());
				pd.put("pageorders", pageorders);	
				mv.addObject("shopuser",shopuser);
				mv.setViewName("shop/order/order-list");
				Shop_Order shop_order = null;
				if(! pageorders.getRecordList().isEmpty()){
					shop_order =(Shop_Order) pageorders.getRecordList().get(0);
					if(shop_order.getStatus()==0)
						mv.addObject("orderNo1",shop_order.getOrdernumber());
					else
						mv.addObject("orderNo1","");
				}else{
					mv.addObject("orderNo1","");
				}
				mv.addObject("pagebeanlist", pageorders.getRecordList());
				mv.addObject("pd", pd);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 登录成功进入下单页面
	 * @return
	 */
	@RequestMapping(value="/shop/order")
	public ModelAndView toIndex()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);
			if(shopuser !=null){
				Shop_User shopuserr = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSERROL);
				if(null == shopuserr){
					session.setAttribute(Const.SESSION_SHOPUSERROL, shopuser);
				}else{
					shopuser =shopuserr;
				}
				String shopuserid = shopuser.getU_id();
				Shop_User shop_user =shopuserService.getShopUserByid(shopuserid);
				//获取客户list
				List<Shop_Client> clientlist =shopuserService.findShopClients(shopuser.getU_id());
				//获取工厂
				//去除工厂选择
				Set<Doc_Factory> faset = new HashSet<>();
				/*for(Shop_Client client:clientlist){
					Doc_Factory factory =client.getDoc_factory();
					if(client.getDoc_factory() !=null)
						faset.add(factory);
					
				 }*/
				//List<Doc_Factory> faclist =shopuserService.findDocFactorys(shopuser.getU_id());
				Set<ShopLink_User_Driver> linkuser_driverSet =   shopuser.getShoplinkuserdrivers();
				List<Shop_Driver> driverlist =  shopuserService.findShopDrivers(linkuser_driverSet);
				for(Shop_Driver driver:driverlist){
					System.out.println(driver.getIdnumber()+driver.getTracknumber());
				}
				//mv.addObject("faset", faset);
				mv.addObject("faset", faset);
				//mv.addObject("clientlist", clientlist);
				mv.addObject("driverlist", driverlist);
					
				pd.put("wx_token", wx_token);
				mv.addObject("pd",pd);
				mv.addObject("shopuser",shopuser);
				mv.setViewName("shop/order/place-order");	
					
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		return mv;
	}
	
	/**
	 * 显示订单列表
	 */
	@RequestMapping(value="/listOrders")
	public ModelAndView listUsers(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			
			Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);
			if(shopuser !=null){
				Shop_User shopuserr = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSERROL);
				if(null == shopuserr){
					session.setAttribute(Const.SESSION_SHOPUSERROL, shopuser);
				}else{
					shopuser =shopuserr;
				}
				PageBean pageuser =null;
				pd.put("pageuser", pageuser);
				mv.setViewName("shop/order/order_list");
				mv.addObject("shopuser",shopuser);
				mv.addObject("pd", pd);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return mv;
	}
	
	
	
	/**
	 * 根据工厂id获取 商品列表，客户列表
	 */
	@RequestMapping(value="/shop/findclient_goods")
	public void findclient_goods(HttpServletResponse response){
		logBefore(logger, "SysUserController_deleteU");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			
			Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);
			if(shopuser !=null){
				Shop_User shopuserr = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSERROL);
				if(null == shopuserr){
					session.setAttribute(Const.SESSION_SHOPUSERROL, shopuser);
				}else{
					shopuser =shopuserr;
				}
			}
			String shopuserid = shopuser.getU_id();
			String factoryid =pd.getString("factory_id");
			List<Json_Goods> listgoods =shopuserService.findShopGoodsByfacid(factoryid);
			
			List<Json_Client> listclient = shopuserService.findShopClients(shopuserid,factoryid); 
			/*Map map =new HashMap();*/
			JSONArray jsongoods = JSONArray.fromObject(listgoods);
			JSONArray jsonclient = JSONArray.fromObject(listclient);
			 JSONObject jo = new JSONObject();
			 jo.put("listgoods",jsongoods );
			 jo.put("listclient", jsonclient);
			 PrintWriter out;
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 
			 
			 out.flush();
			 out.close();
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}
	/**
	 * 根据工厂订单号获取 客户号与商品
	 * @throws IOException 
	 */
	@RequestMapping(value="/shop/findorder_no")
	public void findorder_no(HttpServletResponse response) throws IOException{
		logBefore(logger, "SysUserController_deleteU");
		PageData pd = new PageData();
		List<Json_Goods> listgoods = new ArrayList<>();
		List<Json_Client> listclient = new ArrayList<>();
		JSONObject jo = new JSONObject();
		JSONArray jsongoods = null;
		JSONArray jsonclient = null;
		try{
			pd = this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			
			Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);
			if(shopuser !=null){
				Shop_User shopuserr = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSERROL);
				if(null == shopuserr){
					session.setAttribute(Const.SESSION_SHOPUSERROL, shopuser);
				}else{
					shopuser =shopuserr;
				}
			}
			String shopuserid = shopuser.getU_id();
			String factoryid =pd.getString("factory_id");
			String fac_order_no =pd.getString("fac_order_no");
			
		    //List<Json_Goods> listgoods =shopuserService.findShopGoodsByfacid(factoryid);
			
			//List<Json_Client> listclient = shopuserService.findShopClients(shopuserid,factoryid); 
			Doc_Factory factory =factoryService.findFactoryById(factoryid);
			String factory_url =factory.getServiceurl();
			String reviceClient =factory.getServiceparam();
			//String reviceClient ="reviceTest";
			//String factory_url="http://192.168.11.3:8000/bin";
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]  {"client-bean.xml"});
			SrvWebchat srvwebchat = (SrvWebchat) context.getBean(reviceClient);
			SrvWebchatAction action = new SrvWebchatAction();
			String str_data ="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
							+"	<Head>"
							+"	  <Command>148</Command>"
							+"	  <Data>"+fac_order_no+"</Data>"
							+"	  <ExtParam>"+shopuser.getUsername()+"</ExtParam>"
							+"	  <RemoteUL>"+factory_url+"</RemoteUL>"
							+"	</Head>";
			action.setNData(str_data);
			
			action.setNFunName("Bus_BusinessWebchat");
			SrvWebchatActionResponse reponse =srvwebchat.action(action);
			Element theElement=null,  root=null;
			String clientnumber ="";
			String cusName  ="";
			String paramxml =reponse.getNData();
			//paramxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><DATA><head><CusId>0001</CusId><CusName>测试客户</CusName></head><Items><Item><StockNo>0001</StockNo><StockName>袋装#525</StockName><MaxNumber>1000</MaxNumber></Item></Items></DATA>";
			
			if(reponse.isResult()){
				
			Document xmldoc=ParseXml.toDocment(paramxml);
			root=xmldoc.getDocumentElement();
				theElement=(Element) ParseXml.selectSingleNode("/DATA/head/CusId", root);
				clientnumber=theElement.getTextContent();
				theElement=(Element) ParseXml.selectSingleNode("/DATA/head/CusName", root);
				cusName=theElement.getTextContent();
				Json_Client client = new Json_Client();
				client.setClientnumber(clientnumber);
				client.setClientname(cusName);
				listclient.add(client);
				 NodeList nodelist =ParseXml.selectNodes("/DATA/Items/Item", root);
				 for (int i = 0; i < nodelist.getLength(); i++) {
					 	Json_Goods json_goods = new Json_Goods();
			    		Node employee = nodelist.item(i);
			    		NodeList datalist =employee.getChildNodes();
			    		for(int j=0;j<datalist.getLength();j++){
			    			Node data = datalist.item(j);
			    			if ("StockNo".equals(data.getNodeName())) {
			    				json_goods.setGoodsID(data.getTextContent());
							}else if ("StockName".equals(data.getNodeName())) {
								json_goods.setGoodsname(data.getTextContent());
							}else if ("MaxNumber".equals(data.getNodeName())) {
								json_goods.setMax_tun(data.getTextContent());
							}    
			    		}
			    		listgoods.add(json_goods);
				 	}

				 
			
			
			/*Json_Client json_client = new Json_Client();
			Json_Goods json_goods = new Json_Goods();
			json_goods.setGoodsID("PO-001");
			json_goods.setGoodsname("散装_42.5");
			Json_Goods json_goods1 = new Json_Goods();
			json_goods1.setGoodsID("POS-001");
			json_goods1.setGoodsname("散装_42.5缓凝");*/
			/*Map map =new HashMap();*/
			//listgoods.add(json_goods1);
			//listgoods.add(json_goods);
			//json_client.setClientnumber("KH0004");
			//listclient.add(json_client);
			}
			jsongoods = JSONArray.fromObject(listgoods);
			jsonclient = JSONArray.fromObject(listclient);
			 //jo.put("msg","ok" );
			if(listgoods.size()>0)
				jo.put("msg","ok" );
			else
				jo.put("msg","er" );
			 
			 /*PrintWriter out;
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());*/
			 
			 
			
		} catch(Exception e){
			 
			 jo.put("msg","error" );
			e.printStackTrace();
			logger.error(e.toString(), e);
		}finally{
			 PrintWriter out;
			 response.setCharacterEncoding("utf-8");
			 jo.put("listgoods",jsongoods );
			 jo.put("listclient", jsonclient);
			 out = response.getWriter();
			 out.write(jo.toString());
			 out.flush();
			 out.close();
		}
		
	}
	
	/**
	 * 根据司机ID 获取司机详细信息
	 */
	@RequestMapping(value="/shop/finddriver_detail")
	public void finddriver_detail(HttpServletResponse response){
		logBefore(logger, "SysUserController_deleteU");
		PageData pd = new PageData();
		try{
			 pd = this.getPageData();
			 String driverid =pd.getString("driverid");
			 Json_Driver shopdriver =shopuserService.findShopDriverByID(driverid);
			 JSONObject jo = new JSONObject();
			 jo.put("shopdriver",shopdriver );
			 PrintWriter out;
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
	
	//校验客户下单的吨数
	@RequestMapping(value="/shop/check_dun")
	public void check_dun(HttpServletResponse response) throws IOException {
		logBefore(logger, "SysUserController_deleteU");
		PageData pd = new PageData();
		JSONObject jo = new JSONObject();
		try{
			 pd = this.getPageData();
			 //{datetime=1474683234586, goodsnumber=67, snkh=0001}
			 String factoryid = pd.getString("factoryid");
			 String client_id =pd.getString("snkh");
			 String goods_id =pd.getString("snxh");
			 Doc_Factory factory =factoryService.findFactoryById(factoryid);
			 Shop_Client client =shopclientService.findShopClientByID(client_id);
			 Shop_Goods goods =shopgoodsService.findShopGoodsByID(goods_id);
			 String factory_url =factory.getServiceurl();
			 String reviceClient =factory.getServiceparam();
			 String clientnumber =client.getClientnumber();
			 String goodsnumber =goods.getGoodsID();/*
			 String reviceClient ="reviceTest";
			 String factory_url="http://192.168.11.3:8000/bin";*/
			 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]  {"client-bean.xml"});
			 SrvWebchat srvwebchat = (SrvWebchat) context.getBean(reviceClient);
			 SrvWebchatAction action = new SrvWebchatAction();
			 String str_data ="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
								+"	<Head>"
								+"	  <Command>137</Command>"
								+"	  <Data>"+clientnumber+"</Data>"
								+"	  <ExtParam>"+goodsnumber+"</ExtParam>"
								+"	  <RemoteUL>"+factory_url+"</RemoteUL>"
								+"	</Head>";
			 action.setNData(str_data);
			 action.setNFunName("Bus_BusinessWebchat");
			 SrvWebchatActionResponse reponse =srvwebchat.action(action);
			 String paramxml =reponse.getNData();

			 Element theElement=null,  root=null;
			 Document xmldoc=ParseXml.toDocment(paramxml);
			 root=xmldoc.getDocumentElement();
			 String maxtonnage ="";
			 try{
					theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/MaxTonnage", root);
					maxtonnage=theElement.getTextContent();
				}catch(Exception e){
					e.printStackTrace();
			 }
			 jo.put("msg","ok" );
			 jo.put("maxtonnage",maxtonnage );
			 //jo.put("maxtonnage","123" );
			 //MaxTonnage
			 
		} catch(Exception e){
			System.out.println(e);
			jo.put("msg","timeout");
			logger.error(e.toString(), e);
		}finally {
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.write(jo.toString());
			out.flush();
			out.close();
			logAfter(logger);
		}
		
	}
	
	
	
	
	
	/**
	 * 下单
	 */
	@RequestMapping(value="/shop/save_order")
	public ModelAndView saveU(HttpServletResponse response) throws Exception{
		logBefore(logger, "SysUserController_saveU");
		//ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			//{siji=00002, khmc=0002, chepaihao=410222386434, gcbm=402880e651332f2d0151332f2f570000, sijisjhao=11211312345, thsj=2016-07-05, snxh=00001}
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String pd_wx_token=pd.getString("wx_token");
			String wx_token =(String)session.getAttribute("wx_token");
			Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);
			if(shopuser !=null){
				Shop_User shopuserr = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSERROL);
				if(null == shopuserr){
					session.setAttribute(Const.SESSION_SHOPUSERROL, shopuser);
				}else{
					shopuser =shopuserr;
				}
			String snxh =pd.getString("snxh");
			String goodsname =pd.getString("goodsname");
			String factoryid = pd.getString("factoryid");
			//如果数据库里没有商品，商品添加到数据库中
			Shop_Goods shopgoods =null;//shopgoodsService.updateShopGoods(factoryid,snxh,goodsname);
			String shopuserid = shopuser.getU_id();
			String driverid = pd.getString("driverid");
			String drivername =pd.getString("drivername");
			String driverphone=pd.getString("driverphone");
			String tracknumber =pd.getString("tracknumber");
			String fac_order_no =pd.getString("fac_order_no");
			String thsj =pd.getString("thsj");
			String data =pd.getString("goodsnumber");
			String idnumber =pd.getString("idnumber");
			Shop_Driver driver = null;
				if(pd_wx_token.equals(wx_token)){
					session.removeAttribute("wx_token");
				 if("".equals(driverid)){
					 //新增司机
					 driver=new Shop_Driver();
					 driver.setName(drivername);
					 driver.setPhone(driverphone);
					 driver.setTracknumber(tracknumber);
					 driver.setIdnumber(idnumber);
					 driver =	 shopuserService.saveDriver(driver,shopuserid);
					 
				 }else{
					 driver =	 shopuserService.findDriverByID(driverid); 
					 driver.setName(drivername);
					 driver.setPhone(driverphone);
					 driver.setTracknumber(tracknumber);
					 driver.setIdnumber(idnumber);
					 shopuserService.updateshopDriver(driver);
				 }
				
				 ///Doc_Factory fac =factoryService.findFactoryById(factoryid);
				 //Shop_Goods shopgoods = shopuserService.findShopGoodsByID(snxh);
				 //网上下单
				 Shop_Order shop_order = new Shop_Order();
				 shop_order.setOrderdate(TimestampUtil.getnowtime());
				 //shop_order.setOrdernumber("E"+MakeOrderNum.makeOrderNum());
				 shop_order.setOrdernumber(MakeOrderNum.makeOrderNum());
				 //shop_order.setDoc_factory(fac);
				 shop_order.setShopuser(shopuser);
				 shop_order.setShopgoods(shopgoods);
				 shop_order.setShopdriver(driver);
				 shop_order.setFac_order_no(fac_order_no);
				 shop_order.setThdate(thsj);
				 shop_order.setData(data);
				 shop_order.setIdnumber(idnumber);
				 shop_order.setStatus(0);
				String orderid = shopuserService.saveShopOrder(shop_order);
				 
				//shopuserService.updateOrderfac(orderid,factoryid);
				}
			/*String currentPage="";
			if(pd.toString().contains("currentPage"))
				currentPage =pd.getString("currentPage");
			else
				currentPage ="1";
			PageBean pageorders = shopuserService.listOrders(currentPage,shopuserr.getU_id());
			pd.put("pageorders", pageorders);
			mv.setViewName("shop/order/order-list");
			mv.addObject("pagebeanlist", pageorders.getRecordList());
			mv.addObject("pd", pd);*/
		}
			
		} catch(Exception e){
			
			System.out.println(e);
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return new ModelAndView("redirect:listOrders"); 
		//return mv;
	}
	
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value="/shop/cancle")
	public void cancle(PrintWriter out){
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String orderid =pd.getString("orderid");
			String currentPage =pd.getString("currentPage");
			//userService.deleteU(userid);
			shopuserService.updateorderstatus(orderid);
			
			out.write(currentPage);
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}
	
	/**
	 * app下单
	 */
	@RequestMapping(value="/shop/place_order")
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
			 String sessionId= pd.getString("sessionId");
			 Shop_User shopuser = (Shop_User) session.getAttribute(sessionId);
			 if(shopuser==null&&sessionId!=null){
					shopuser =shopuserService.getShopUserByid(sessionId);
				}
				//Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);
				if(shopuser !=null){
					Shop_User shopuserr = (Shop_User) session.getAttribute(sessionId);
					if(null == shopuserr){
						session.setAttribute(sessionId, shopuser);
					}else{
						shopuser =shopuserr;
					}
			    String 	c_id =pd.getString("c_id");
			    String 	stockno =pd.getString("stockno");
			    String 	stockname =pd.getString("stockname");
			    String 	driverid =pd.getString("driverid");
			    String 	clientname =pd.getString("clientname");
			    String billnumber =pd.getString("billnumber");
			    String 	goodsnumber =pd.getString("goodsnumber");
			    String 	thrq =pd.getString("thrq");
			    
				
				String factoryid = pd.getString("factoryid");
				//如果数据库里没有商品，商品添加到数据库中
				String g_id =shopgoodsService.updateShopGoods(factoryid,stockno,stockname);
				Shop_Driver driver = null;
				driver =	 shopuserService.findDriverByID(driverid);
				Shop_Order shop_order = new Shop_Order();
				 shop_order.setOrderdate(TimestampUtil.getnowtime());
				 //shop_order.setOrdernumber("E"+MakeOrderNum.makeOrderNum());
				 String thorderno=MakeOrderNum.makeOrderNum();
				 shop_order.setOrdernumber(thorderno);
				 //shop_order.setDoc_factory(fac);
				 shop_order.setShopuser(shopuser);
				 //shop_order.setShopgoods(shopgoods);
				 shop_order.setShopdriver(driver);
				 shop_order.setData(goodsnumber);
				 shop_order.setStatus(0);
				 shop_order.setThdate(thrq);
				 shop_order.setFac_order_no(billnumber);
				 String orderid = shopuserService.saveShopOrder(shop_order);
				 shopuserService.updateOrderfac(orderid,factoryid,c_id,g_id);
				 jo.put("msg","success" );
				 
				 
				//下单成功发送模板消息
					String phone =shopuser.getUsername();
					String companyid=pd.getString("companyid");
					Wx_BindCustomer wx_bind=wxbindcustomerService.findFactoryByPhone(phone,factoryid);
					
					JSONObject jobject = new JSONObject();
					if(wx_bind.getOpenid() !=null){
						Doc_Company company =companyService.findCompanyByID(companyid);
						String originalid =company.getOriginalID();
						System.out.println(originalid);
						
						
						jobject.put("thorderno", thorderno);
						jobject.put("clientname", clientname);
						jobject.put("stockname", stockname);
						jobject.put("billnumber", billnumber);
						jobject.put("goodsnumber", goodsnumber);
						jobject.put("thrq", thrq);
						//Map<String, String> map1 = new HashMap<String, String>();
						
						X_Eventmsg x_eventmsg = new X_Eventmsg();
						
						
						
						x_eventmsg.setReviceuser(wx_bind.getOpenid());
						x_eventmsg.setContent(jobject.toString());
						x_eventmsg.setSenduser(originalid);
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
			System.out.println(e);
			logger.error(e.toString(), e);
		}finally {
			
			
			logAfter(logger);
		}
		
	}
}
