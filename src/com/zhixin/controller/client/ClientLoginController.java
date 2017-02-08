package com.zhixin.controller.client;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.springframework.context.support.ClassPathXmlApplicationContext;import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.zhixin.entity.Json_AppActivity;
import com.zhixin.entity.Json_AppPicture;
import com.zhixin.entity.Json_Card_Order;
import com.zhixin.entity.Json_Client;import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Factory;
import com.zhixin.entity.Json_Goods;import com.zhixin.entity.Json_ShopOrder;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.ShopLink_User_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Shop_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Eventmsg;
import com.zhixin.right_utils.AppUtil;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.DateUtil;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.PathUtil;
import com.zhixin.right_utils.Tools;
import com.zhixin.service.CompanyService;
import com.zhixin.service.FactoryService;
import com.zhixin.service.SendMsgService;
import com.zhixin.service.WxBindCustomerService;
import com.zhixin.service.client.ClientUserService;
import com.zhixin.service.shop.ShopUserService;
//import com.zhixin.tools.MySessionContext;
import com.zhixin.tools.ParseXml;import com.zhixin.tools.TokenProccessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value="/clientUser")
public class ClientLoginController extends BaseController{


	//
	@Resource(name="clientuserService")
	private ClientUserService clientuserService;
	
	@Resource(name="shopuserService")
	private ShopUserService shopuserService;
	
	
	@Resource(name="factoryService")
	private FactoryService factoryService;
	
	
	
	
	
	
	/**
	 * 获取登录用户的IP
	 * @throws Exception 
	 */
	public String  getRemortIP() throws Exception {  
		PageData pd = new PageData();
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    }else{
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		
		return ip;
	} 
	
	
	
	/**
	 * app主页 
	 * 1.查询 志信科技的app_picture
	 * 2.查询app_activity
	 */
	@RequestMapping(value="/login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object login( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		/*List<Json_AppPicture> list_picture =apppictureService.findAppPicturesByFactoryid("402880e651332f2d0151332f2f570000");
		List<Json_AppActivity> list_activity =appactivityService.findAppActivityByFactoryid("402880e651332f2d0151332f2f570000");
		JSONArray jsonpictures = JSONArray.fromObject(list_picture);
		JSONArray jsonactivitys = JSONArray.fromObject(list_activity);
		 
		 jo.put("jsonpictures",jsonpictures );
		 jo.put("jsonactivitys", jsonactivitys);*/
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo ="";
		String sessionid="";
		String factoryid=request.getParameter("factoryid");
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qqbigbug", "").replaceAll("qqbigbug", "").split(",zx,");
		if(null !=KEYDATA && KEYDATA.length==2){
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String USERNAME =KEYDATA[0];
			String PASSWORD =KEYDATA[1];
			pd.put("USERNAME", USERNAME);
			
			String passwd = new  SimpleHash("SHA-1",USERNAME+factoryid,PASSWORD).toString();
			System.out.println(passwd);
			Shop_User shop_user =shopuserService.getShopUserByNameAndPWd(passwd);
			if(shop_user !=null){
							//更新最后登录时间
							//String last_login =DateUtil.getTime().toString();
							shop_user.setLast_login(DateUtil.getTime().toString());
							shop_user.setIp(this.getRemortIP());
							shopuserService.updateLastLogin(shop_user);
							//session 赋值
							sessionid =shop_user.getU_id();
							session.setAttribute(sessionid, shop_user);
							session.setAttribute(Const.SESSION_SHOPUSER, shop_user);
							session.removeAttribute(Const.SESSION_SECURITY_CODE);
							map.put("sessionId", shop_user.getU_id());
							Subject subject =SecurityUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(USERNAME,PASSWORD);
							try{
								subject.login(token);
							}catch(Exception ex){
								errInfo = "身份验证失败！";
							}
			}else{
							errInfo ="usererror";
				 }
			
				
			
				
		if(Tools.isEmpty(errInfo)){
					errInfo = "success";					//验证成功
			}
		
		}else{
			errInfo ="error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	/**
	 * app下提货单页面
	 * 1.若没有登录，先进入登录界面
	 *
	 */
	@ResponseBody
	@RequestMapping("/page_order")
	public void page_order( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		jo.put("msg","success" );
		PageData pd = new PageData();
		pd = this.getPageData();
		String sessionId= pd.getString("sessionId");
		try{
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			Shop_User shopuser = (Shop_User) session.getAttribute(sessionId);
			if(shopuser==null&&sessionId!=null){
					shopuser =shopuserService.getShopUserByid(sessionId);
				}
			if(shopuser !=null){
				Shop_User shopuserr = (Shop_User) session.getAttribute(sessionId);
				if(null == shopuserr){
					session.setAttribute(sessionId, shopuser);
				}else{
					shopuser =shopuserr;
				}
				String shopuserid = shopuser.getU_id();
				Shop_User shop_user =shopuserService.getShopUserByid(shopuserid);
				//获取客户list
				List<Shop_Client> clientlist =shopuserService.findShopClients(shopuser.getU_id());
				//获取工厂
				Set<Json_Factory> faset = new HashSet<>();
				//去除工厂选择
				/*
				for(Shop_Client client:clientlist){
					Doc_Factory factory =client.getDoc_factory();
					if(client.getDoc_factory() !=null)
						faset.add(new Json_Factory(factory.getId(),factory.getFactoryname()));
					
				}*/
				//List<Doc_Factory> faclist =shopuserService.findDocFactorys(shopuser.getU_id());
				Set<ShopLink_User_Driver> linkuser_driverSet =   shopuser.getShoplinkuserdrivers();
				List<Shop_Driver> driverlist =  shopuserService.findShopDrivers(linkuser_driverSet);
				List<Json_Driver> list_driver =new ArrayList<>();
				for(Shop_Driver driver:driverlist){
					Json_Driver json_driver = new Json_Driver(driver.getD_id(), driver.getName(), 
							driver.getTracknumber(), driver.getIdnumber(), driver.getPhone());
					list_driver.add(json_driver);
					
				}
				
				
				
				/*jo.put("faset", faset);
				jo.put("driverlist", driverlist);	
				jo.put("wx_token", wx_token);	
				jo.put("shopuser", shopuser);	*/
				jo.put("list_driver", list_driver);
				jo.put("faset", faset);
			}else{
				jo.put("msg","error" );
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 //jo.put("jsonactivitys", jsonactivitys);
			 PrintWriter out;
			 response.setContentType("application/json;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 
			 
			 out.flush();
			 out.close();
		}
		
		
		
		
		
	}
	
	
	
	/**
	 * app新增提货司机
	 * 1.若没有登录，先进入登录界面
	 *
	 */
	@ResponseBody
	@RequestMapping("/saveDriver")
	public void saveDriver( HttpServletResponse response) throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		jo.put("msg","success" );
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			String sessionId= pd.getString("sessionId");
			Shop_User shopuser = (Shop_User) session.getAttribute(sessionId);
			if(shopuser==null&&sessionId!=null){
				shopuser =shopuserService.getShopUserByid(sessionId);
			}
			if(shopuser !=null){
				Shop_User shopuserr = (Shop_User) session.getAttribute(sessionId);
				if(null == shopuserr){
					session.setAttribute(sessionId, shopuser);
				}else{
					shopuser =shopuserr;
				}
				String shopuserid = shopuser.getU_id();
				Shop_User shop_user =shopuserService.getShopUserByid(shopuserid);
				String drivername =pd.getString("drivername");
				String driverphone=pd.getString("driverphone");
				String tracknumber =pd.getString("tracknumber");
				Shop_Driver driver=new Shop_Driver();
				driver.setName(drivername);
				driver.setPhone(driverphone);
				driver.setTracknumber(tracknumber);
				driver =	shopuserService.saveDriver(driver,shopuserid);
			}else{
				jo.put("msg","error" );
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 //jo.put("jsonactivitys", jsonactivitys);
			 PrintWriter out;
			 response.setContentType("application/json;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 
			 
			 out.flush();
			 out.close();
		}
	}
	/**
	 * 司机列表
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	
	@ResponseBody
	@RequestMapping("/driver_list")
	public void driver_list(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		// List<Json_ShopOrder>
		// jsonorderlist=clientOrderService.listOrders(currentPage,
		// "8aac0f915773bc9f015773cfc8c70001");
		// Json_ShopOrder json_shopOrder=jsonorderlist.get(0);
		JSONObject jo = new JSONObject();
		jo.put("msg", "success");
		PageData pd = new PageData();
		try {
			pd=this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			String sessionId= pd.getString("sessionId");
			Shop_User shopuser = (Shop_User) session.getAttribute(sessionId);
			if(shopuser==null&&sessionId!=null){
				shopuser =shopuserService.getShopUserByid(sessionId);
			}
			if (shopuser != null) {
				String shopuserid = shopuser.getU_id();
				Shop_User shop_user =shopuserService.getShopUserByid(shopuserid);	
				//Set<ShopLink_User_Driver> linkuser_driverSet1 =   shopuser.getShoplinkuserdrivers();
				Set<ShopLink_User_Driver> linkuser_driverSet =   shopuserService.findShopLink_User_DriverByU_id(shop_user);
				List<Shop_Driver> driverlist =  shopuserService.findShopDrivers(linkuser_driverSet);
				if(driverlist.size()==0){
					jo.put("msg", "drierror");
				}else{
					List<Json_Driver> list_driver =new ArrayList<>();
					for(Shop_Driver driver:driverlist){
						Json_Driver json_driver = new Json_Driver(driver.getD_id(), driver.getName(), 
								driver.getTracknumber(), driver.getIdnumber(), driver.getPhone());
						list_driver.add(json_driver);
					}
					jo.put("list_driver", list_driver);
				}
				
			} else {
				jo.put("msg", "error");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			PrintWriter out;
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.write(jo.toString());
			out.flush();
			out.close();
		}

	}
	

	/**
	 *
	 * 到修改页面
	 *
	 */
	@ResponseBody
	@RequestMapping("/updateDriver1")
	public void updateDriver1( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		PageData pd = new PageData();
		try {
			pd=this.getPageData();
			String sessionId= pd.getString("sessionId");
			if(sessionId==null||sessionId.equals("")){
				jo.put("msg", "error");	
			}else {
				String driverid=request.getParameter("driverid");
				Json_Driver jsondriver= shopuserService.findJsonDriverByD_ID(driverid);
				jo.put("jsondriver", jsondriver);
				jo.put("msg", "success");	
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				PrintWriter out;
				 response.setContentType("application/json;charset=utf-8");
				 response.setCharacterEncoding("utf-8");
				 out = response.getWriter();
				 out.write(jo.toString());
				 out.flush();
				 out.close();
			}
			 
		
	}
	
	/**
	 * 开始修改
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateDriver")
	public void updateDriver( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		    response.setHeader("P3P","CP=CAO PSA OUR"); 
		//JSONObject jo = new JSONObject();
			String driverid=request.getParameter("driverid");
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			String tracknumber=request.getParameter("tracknumber");
			Shop_Driver driver=shopuserService.findDriverByD_ID(driverid);
			driver.setName(name);
			driver.setPhone(phone);
			driver.setTracknumber(tracknumber);
			shopuserService.updateshopDriver(driver);;
			
			/* PrintWriter out;
			 response.setContentType("application/json;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 out.flush();
			 out.close();*/
		
	}
	
	/**
	 * 删除司机
	 * 
	 *
	 */
	@ResponseBody
	@RequestMapping("/delDriver")
	public void delDriver( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		/*Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String wx_token = TokenProccessor.getInstance().makeToken();
		session.setAttribute("wx_token", wx_token);
		Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);*/
		PageData pd = new PageData();
		pd = this.getPageData();
		String sessionId= pd.getString("sessionId");	
			String driverid=request.getParameter("driverid");
			Shop_Driver driver=shopuserService.findDriverByD_ID(driverid);
			List<Shop_Order> orderlist=shopuserService.findShopOrderByDriverId(driver);
			if(orderlist.size()==0){
				shopuserService.deleteShoperDriver(driverid,driver);
				jo.put("mes", "success");
			}else{
				jo.put("mes", "error");
			}
			 PrintWriter out;
			 response.setContentType("application/json;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 out.flush();
			 out.close();
			 }
		
	@ResponseBody
	@RequestMapping("/client_list")
	public void client_list(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		

		// List<Json_ShopOrder>
		// jsonorderlist=clientOrderService.listOrders(currentPage,
		// "8aac0f915773bc9f015773cfc8c70001");
		// Json_ShopOrder json_shopOrder=jsonorderlist.get(0);
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		jo.put("msg", "success");
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);

			String sessionId= pd.getString("sessionId");
			Shop_User shopuser = (Shop_User) session.getAttribute(sessionId);
			if(shopuser==null&&sessionId!=null){
				shopuser =shopuserService.getShopUserByid(sessionId);
			}
			if (shopuser != null) {
				String shopuserid = shopuser.getU_id();
				Shop_User shop_user =shopuserService.getShopUserByid(shopuserid);	
				//获取客户list
				List<Shop_Client> clientlist =shopuserService.findShopClients(shopuser.getU_id());
				List<Json_Client> list_client =new ArrayList<>();
				for(Shop_Client client:clientlist){
					Json_Client json_client = new Json_Client(client.getC_id(),client.getClientnumber(),client.getClientname());
					list_client.add(json_client);
				}
				
				jo.put("list_client", list_client);
			} else {
				jo.put("msg", "error");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
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
	@RequestMapping("/ordr_list")
	public void ordr_list(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		// List<Json_ShopOrder>
		// jsonorderlist=clientOrderService.listOrders(currentPage,
		// "8aac0f915773bc9f015773cfc8c70001");
		// Json_ShopOrder json_shopOrder=jsonorderlist.get(0);
		PageData pd = new PageData(); 
		JSONObject jo = new JSONObject();
		JSONArray json_card_orders = null;
		jo.put("msg", "success");
		List<Json_Card_Order> json_card_order_list = new ArrayList<>();
		try {
			pd = this.getPageData();
			String factoryid =pd.getString("factoryid");
			String clientnumber =pd.getString("clientnumber");
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String sessionId= pd.getString("sessionId");
			//Shop_User shopuser = (Shop_User) session.getAttribute(sessionId);
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			Shop_User shopuser = (Shop_User) session.getAttribute(sessionId);
			if(shopuser==null&&sessionId!=null){
				shopuser =shopuserService.getShopUserByid(sessionId);
			}
			if (shopuser != null) {
				String shopuserid = shopuser.getU_id();
				Doc_Factory factory =factoryService.findFactoryById(factoryid);
				String factory_url =factory.getServiceurl();
				String reviceClient =factory.getServiceparam();
				//String reviceClient ="reviceTest";
				//String factory_url="http://192.168.11.3:8000/bin";
				ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]  {"client-bean.xml"});
				SrvWebchat srvwebchat = (SrvWebchat) context.getBean(reviceClient);
				SrvWebchatAction action = new SrvWebchatAction();
				String str_data ="<?xml version=\"1.0\" encoding=\"utf-8\"?><Head>  <Command>0x103</Command> "
						+ " <Data>"+clientnumber+"</Data>  <ExtParam></ExtParam>  "
						+ "<RemoteUL>"+factory_url+"</RemoteUL></Head>";
				
				action.setNData(str_data);
				
				action.setNFunName("Bus_BusinessWebchat");
				SrvWebchatActionResponse reponse =srvwebchat.action(action);
				Element theElement=null,  root=null;
				String cusName  ="";
				String paramxml =reponse.getNData();
				//如果没有大票号，赋值默认的大票号测试
				/*if(!reponse.isResult())
				String paramxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><DATA><head><CusId>0001</CusId><CusName>测试客户</CusName></head><Items>"
							+ "<Item><SetDate>2016-12-24</SetDate><BillNumber>20161226</BillNumber><StockNo>0001</StockNo><StockName>袋装#525</StockName><MaxNumber>1000</MaxNumber></Item></Items></DATA>";*/
				if(reponse.isResult()){
					Document xmldoc=ParseXml.toDocment(paramxml);
					root=xmldoc.getDocumentElement();
						theElement=(Element) ParseXml.selectSingleNode("/DATA/head/CusId", root);
						clientnumber=theElement.getTextContent();
						theElement=(Element) ParseXml.selectSingleNode("/DATA/head/CusName", root);
						cusName=theElement.getTextContent();
						
						 NodeList nodelist =ParseXml.selectNodes("/DATA/Items/Item", root);
						 for (int i = 0; i < nodelist.getLength(); i++) {
							 Json_Card_Order json_card_order = new Json_Card_Order();
					    		Node employee = nodelist.item(i);
					    		NodeList datalist =employee.getChildNodes();
					    		for(int j=0;j<datalist.getLength();j++){
					    			Node data = datalist.item(j);
					    			if ("SetDate".equals(data.getNodeName())) {
					    				json_card_order.setSetdate(data.getTextContent());
									}else if ("BillNumber".equals(data.getNodeName())) {
										json_card_order.setBillnumber(data.getTextContent());
									}else if ("StockNo".equals(data.getNodeName())) {
										json_card_order.setStockno(data.getTextContent());
									}else if ("StockName".equals(data.getNodeName())) {
										json_card_order.setStockname(data.getTextContent());
									}else if ("MaxNumber".equals(data.getNodeName())) {
										json_card_order.setMaxnumber(data.getTextContent());
									}     
					    		}
					    		json_card_order_list.add(json_card_order);
						 	}

				
					}			
				json_card_orders = JSONArray.fromObject(json_card_order_list);
				 //jo.put("msg","ok" );
				if(json_card_order_list.size()>0)
					jo.put("msg","ok" );
				else
					jo.put("msg","er" );
				
			} else {
				jo.put("msg", "error");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			PrintWriter out;
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			jo.put("json_card_orders",json_card_orders );
			out.write(jo.toString());
			out.flush();
			out.close();
		}	
	}

	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public void logout(HttpServletResponse response)throws Exception{
		response.setHeader("P3P","CP=CAO PSA OUR");
		//shiro管理的session
		JSONObject jo = new JSONObject();
		PageData pd = new PageData();
		pd = this.getPageData();

		String sessionId= pd.getString("sessionId");
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		session.removeAttribute(sessionId);
		/*session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_allmenuList);
		session.removeAttribute(Const.SESSION_menuList);
		session.removeAttribute(Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute("changeMenu");*/
		session.removeAttribute(sessionId);
		if(session.getAttribute(sessionId)==null){
			jo.put("mes", "success");
		}else{
			jo.put("mes", "error");
		}
		
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		
		 PrintWriter out;
		 response.setContentType("application/json;charset=utf-8");
		 response.setCharacterEncoding("utf-8");
		 out = response.getWriter();
		 out.write(jo.toString());
		 out.flush();
		 out.close();
	}

	/**
	 *
	 * 到添加司机页面
	 *
	 */
	@ResponseBody
	@RequestMapping("/savedriver1")
	public void savedriver1( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		PageData pd = new PageData();
		try {
			pd=this.getPageData();
			String sessionId= pd.getString("sessionId");
			if(sessionId==null||sessionId.equals("")){
				jo.put("msg", "error");
			}else {
				jo.put("msg", "success");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				PrintWriter out;
				 response.setContentType("application/json;charset=utf-8");
				 response.setCharacterEncoding("utf-8");
				 out = response.getWriter();
				 out.write(jo.toString());
				 out.flush();
				 out.close();
			}
			 
		
	}
	
	
}



