package com.zhixin.controller.client;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.zhixin.entity.Json_Card_Order;
import com.zhixin.entity.Json_Driver;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.ShopLink_Customer_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.right_utils.AppUtil;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.Tools;
import com.zhixin.service.CompanyService;
import com.zhixin.service.FactoryService;
import com.zhixin.service.WxBindCustomerService;
import com.zhixin.service.client.ClientUserService;
import com.zhixin.service.shop.ShopUserService;
import com.zhixin.tools.ParseXml;
//import com.zhixin.tools.MySessionContext;
import com.zhixin.tools.TokenProccessor;

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
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	@Resource(name="wxbindcustomerService")
	private  WxBindCustomerService wxbindcustomerService;
	
	
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
	 * 访问登录页面
	 * @return
	 */
	@RequestMapping(value="/tologin")
	public ModelAndView toLogin()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("weixin/login/login");
		return mv;
	}
	
	/**
	 * 访问个人中心页面
	 * @return
	 */
	@RequestMapping(value="/touser")
	public ModelAndView toUser()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		//String companyid =pd.getString("companyid");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Wx_BindCustomer wx_BindCustomer  = (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		List<Doc_Factory> list_fac =factoryService.findFactorysByCustomerId(wx_BindCustomer.getId());
		mv.addObject("pd",pd);
		mv.addObject("list_fac",list_fac);
		mv.setViewName("weixin/user/user");
		return mv;
	}

	/**
	 * 访问客户号列表页面
	 * @return
	 */
	@RequestMapping(value="/tocwddlb")
	public ModelAndView tocwddlb()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		String factoryid =pd.getString("factoryid");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Wx_BindCustomer wx_BindCustomer  = (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
		String  customerid ="";
		if(wx_BindCustomer !=null){
			  customerid =wx_BindCustomer.getId();
		}
		List<Shop_Client> list_shopclient =wxbindcustomerService.findClientByCustomerId(customerid,factoryid);
		pd.put("factoryid", factoryid);
		mv.addObject("pd",pd);
		mv.addObject("list_shopclient",list_shopclient);
		mv.setViewName("weixin/xthd/cwddlb");
		return mv;
	}
	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
public Object login( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		System.out.println("++++++++++++++login++++++++++++++++");
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo ="";
		String companyid =pd.getString("companyid");
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qqbigbug", "").replaceAll("qqbigbug", "").split(",zx,");
		if(null !=KEYDATA && KEYDATA.length==2){
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String USERNAME =KEYDATA[0];
			String PASSWORD =KEYDATA[1];
			pd.put("USERNAME", USERNAME);
			String passwd = new  SimpleHash("SHA-1",USERNAME+companyid,PASSWORD).toString();
			Wx_BindCustomer wx_BindCustomer =shopuserService.getWx_BindCustomerByNameAndPWd(passwd);
			if(wx_BindCustomer !=null){
							//shopuserService.updateLastLogin(wx_BindCustomer);
							//session 赋值
							session.setAttribute(Const.SESSION_CLIENTUSER, wx_BindCustomer);
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
		map.put("companyid", companyid);
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
		
	
	/**
	 * 去司机列表页面
	 * @return
	 */
	@RequestMapping(value="/to_dirver")
	public ModelAndView toTjsj()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("weixin/user/tjsj");
		return mv;
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
		JSONObject jo = new JSONObject();
		jo.put("msg", "success");
		System.out.println("++++++++++++++++driver_list++++++++++++++++++");
		PageData pd = new PageData();
		pd=this.getPageData();
		try {
			pd=this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			Wx_BindCustomer wx_BindCustomer= (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
			if(wx_BindCustomer==null){
			}
			if (wx_BindCustomer != null) {
				String wx_BindCustomer_id = wx_BindCustomer.getId();
				Wx_BindCustomer wx_BindCustomerr =shopuserService.getWx_BindCustomerByid(wx_BindCustomer_id);	
				Set<ShopLink_Customer_Driver> linkuser_driverSet =   shopuserService.findShopLink_Customer_DriverByU_id(wx_BindCustomerr);
				List<Shop_Driver> driverlist =  shopuserService.findShopDrivers(linkuser_driverSet);
				if(driverlist.size()==0){
					jo.put("msg", "drierror");
				}else{
					List<Json_Driver> list_driver =new ArrayList<>();
					for(Shop_Driver driver:driverlist){
						String shop_tracknumber =driver.getTracknumber();
						Boolean flag =true;
						for(Json_Driver jsdriver:list_driver ){
							if(shop_tracknumber.equals(jsdriver.getTracknumber()))
								flag=false;
						}
						if(flag==true){
							Json_Driver json_driver = new Json_Driver(driver.getD_id(), driver.getName(), 
									driver.getTracknumber(), driver.getIdnumber(), driver.getPhone());
							list_driver.add(json_driver);
						}
						
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
	 * 司机列表
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	
	@ResponseBody
	@RequestMapping("/driver_list2")
	public void driver_list2(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		jo.put("msg", "success");
		PageData pd = new PageData();
		pd=this.getPageData();
		try {
			pd=this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			Wx_BindCustomer wx_BindCustomer= (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
			if(wx_BindCustomer==null){
			}
			if (wx_BindCustomer != null) {
				String wx_BindCustomer_id = wx_BindCustomer.getId();
				Wx_BindCustomer wx_BindCustomerr =shopuserService.getWx_BindCustomerByid(wx_BindCustomer_id);	
				Set<ShopLink_Customer_Driver> linkuser_driverSet =   shopuserService.findShopLink_Customer_DriverByU_id(wx_BindCustomerr);
				List<Shop_Driver> driverlist =  shopuserService.findShopDrivers(linkuser_driverSet);
				if(driverlist.size()==0){
					jo.put("msg", "drierror");
				}else{
					List<Json_Driver> list_driver =new ArrayList<>();
					for(Shop_Driver driver:driverlist){
						String shop_tracknumber =driver.getTracknumber();
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
	 * 访问修改页面
	 * @return
	 */
	@RequestMapping(value="/toUpdateDriver")
	public ModelAndView toUpdateDriver()throws Exception{
		logBefore(logger, "LoginController_toUpdateDriver");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("weixin/user/xgsja");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/ordr_list")
	public void ordr_list(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		response.setHeader("P3P","CP=CAO PSA OUR");
		PageData pd = new PageData(); 
		JSONObject jo = new JSONObject();
		JSONArray json_card_orders = null;
		jo.put("msg", "er");
		String clientnumber1=request.getParameter("clientnumber");
		jo.put("clientnumber", clientnumber1);
		List<Json_Card_Order> json_card_order_list = new ArrayList<>();
		try {
			pd = this.getPageData();
			String factoryid =pd.getString("factoryid");
			String clientnumber=pd.getString("clientnumber");
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			
				
				Doc_Factory factory =factoryService.findFactoryById(factoryid);
				String factory_url =factory.getServiceurl();
				String reviceClient =factory.getServiceparam();
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
				if(json_card_order_list.size()>0)
					jo.put("msg","ok" );
				else
					jo.put("msg","er" );
				
			
		} catch (Exception e) {
			PrintWriter out;
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			json_card_orders = JSONArray.fromObject(json_card_order_list);
			jo.put("json_card_orders",json_card_orders );
			out.write(jo.toString());
			out.flush();
			out.close();
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
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Wx_BindCustomer wx_BindCustomer= (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
			if(wx_BindCustomer==null){
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
		    JSONObject jo = new JSONObject();
			String driverid=request.getParameter("driverid");
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			String tracknumber=request.getParameter("tracknumber").toUpperCase();
			Shop_Driver driver=shopuserService.findDriverByD_ID(driverid);
			driver.setName(name);
			driver.setPhone(phone);
			driver.setTracknumber(tracknumber);
			shopuserService.updateshopDriver(driver);;
			
			 PrintWriter out;
			 response.setContentType("application/json;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 out.flush();
			 out.close();
		
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
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Wx_BindCustomer wx_BindCustomer= (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
		if(wx_BindCustomer==null){
			jo.put("mes", "errorr");
		}else{
			String driverid=request.getParameter("driverid");
			Shop_Driver driver=shopuserService.findDriverByD_ID(driverid);
			List<Shop_Order> orderlist=shopuserService.findShopOrderByDriverId(driver);
			if(orderlist.size()==0){
				shopuserService.deleteShoperDriver(driverid,driver);
				jo.put("mes", "success");
			}else{
				jo.put("mes", "error");
			}
		}
			 PrintWriter out;
			 response.setContentType("application/json;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 out.flush();
			 out.close();
			 }
	
	/**
	 * 去司机列表页面
	 * @return
	 */
	@RequestMapping(value="/to_savedirver")
	public ModelAndView toTjsja(HttpServletRequest request)throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		String uuid=get32UUID().toString();
		request.setAttribute("token", uuid);
		HttpSession ss=(HttpSession)request.getSession();
		ss.setAttribute("token", uuid);
		mv.setViewName("weixin/user/tjsja");
		return mv;
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
			String server_token=(String) session.getAttribute("token");
			//String sessionId= pd.getString("sessionId");
			Wx_BindCustomer wx_BindCustomer = (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
			if(wx_BindCustomer==null){
				jo.put("msg","error" );
			}
			if(wx_BindCustomer !=null){
			/*	Wx_BindCustomer wx_BindCustomerr = (Wx_BindCustomer) session.getAttribute(wx_BindCustomer.getId());
				if(null == wx_BindCustomerr){
					session.setAttribute(sessionId, wx_BindCustomer);
				}else{
					wx_BindCustomer =wx_BindCustomerr;
				}*/
				String wx_BindCustomerid = wx_BindCustomer.getId();
				//Wx_BindCustomer wx_BindCustomer1 =shopuserService.getWx_BindCustomerByid(wx_BindCustomerid);
				String drivername =pd.getString("drivername");
				String driverphone=pd.getString("driverphone");
				String tracknumber1 =pd.getString("tracknumber");
				String tracknumber=tracknumber1.toUpperCase();
				String request_token=pd.getString("token");
				System.out.println(request_token);
				System.out.println(server_token);
				if (request_token==server_token||request_token.equals(server_token)){
					Shop_Driver driver=new Shop_Driver();
					driver.setName(drivername);
					driver.setPhone(driverphone);
					driver.setTracknumber(tracknumber);
					driver =shopuserService.saveDriver(driver,wx_BindCustomerid);
					session.setAttribute("token", get32UUID());					
				}else{
					jo.put("msg","repeat" );
				}
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

		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		session.removeAttribute(Const.SESSION_CLIENTUSER);
		if(session.getAttribute(Const.SESSION_CLIENTUSER)==null){
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
	 * 访问修改页面
	 * @return
	 */
	@RequestMapping(value="/toUpdatePassword")
	public ModelAndView toUpdatePassword()throws Exception{
		logBefore(logger, "LoginController_toUpdateDriver");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("weixin/user/xgmm");
		return mv;
	}
	/**
	 * 开始密码
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updatePassword")
	public void updatePassword( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		    response.setHeader("P3P","CP=CAO PSA OUR"); 
		    JSONObject jo = new JSONObject();
		    Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Wx_BindCustomer wx_BindCustomer=(Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
			if(wx_BindCustomer==null){
				jo.put("msg", "error");
			}else{
				String companyid=request.getParameter("companyid");
				String password=request.getParameter("password");
				String username=wx_BindCustomer.getNamepinyin();
				String passwd = new  SimpleHash("SHA-1",username+companyid,password).toString();
				String pc_password=new  SimpleHash("SHA-1",username,password).toString();
				wx_BindCustomer.setPassword(passwd);
				wx_BindCustomer.setPc_password(pc_password);
				shopuserService.updateWx_BindCustomerPassword(wx_BindCustomer);
				jo.put("msg", "success");
			}
			
			
			 PrintWriter out;
			 response.setContentType("application/json;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 out.flush();
			 out.close();
		
	}
	
}



