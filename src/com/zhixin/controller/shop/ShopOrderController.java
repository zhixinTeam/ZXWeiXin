package com.zhixin.controller.shop;

import java.io.File;
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
import javax.servlet.http.HttpServletRequest;
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
import com.zhixin.entity.Json_ShopOrder;
import com.zhixin.entity.Page;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Eventmsg;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.DateUtil;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.PathUtil;
import com.zhixin.right_utils.Tools;
import com.zhixin.service.CompanyService;
import com.zhixin.service.FactoryService;
import com.zhixin.service.WebserviceService;
import com.zhixin.service.WxBindCustomerService;
import com.zhixin.service.client.ClientOrderService;
import com.zhixin.service.shop.ShopGoodsService;
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

	/*@Resource(name="shoporderService")
	private ShopOrderService shoporderService;*/
	
	@Resource(name = "clientorderService")
	private ClientOrderService clientorderService;
	
	@Resource(name="shopuserService")
	private ShopUserService shopuserService;
	
	@Resource(name="factoryService")
	private FactoryService factoryService;
	
/*	@Resource(name="shopclientService")
	private ShopClientService shopclientService;*/
	
	@Resource(name="shopgoodsService")
	private ShopGoodsService shopgoodsService;
	
	@Resource(name="wxbindcustomerService")
	private  WxBindCustomerService wxbindcustomerService;
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	
	@Resource(name="webserviceservice")
	private WebserviceService webserviceservice;
	
	
	
	/**
	 * 去历史订单页面
	 * @return
	 */
	@RequestMapping(value="/shop/tolsdd")
	public ModelAndView toUser(HttpServletResponse response, String currentPage, HttpServletRequest request)throws Exception{
		logBefore(logger, "ClientOrderController_lsdd");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.setViewName("shop_pc/user/lsdd");
		return mv;
	}
	
	/**
	 * 去历史订单页面
	 * @return
	 */
	@RequestMapping(value="/shop/toxthd")
	public ModelAndView toXthd(HttpServletResponse response, HttpServletRequest request)throws Exception{
		logBefore(logger, "ClientOrderController_lsdd");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession(); 
		Doc_Factory factroy  = (Doc_Factory) session.getAttribute(Const.SESSION_SHOPFACTORY);
		
		
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("factroy",factroy);
		mv.setViewName("shop_pc/xthd/xthd");
		return mv;
	}
	
	
	
	/**
	 * 显示订单列表
	 */
	@RequestMapping(value="/shop/listOrders")
		public void place_order(HttpServletResponse response, String currentPage, HttpServletRequest request)
				throws Exception {
			response.setHeader("P3P","CP=CAO PSA OUR");
			currentPage = request.getParameter("currentPage");
			PageData pd = new PageData();
			pd = this.getPageData();
			System.out.println("+++++++++++查看提货单++++++++++++");
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
			List encoderImgIdlist=new ArrayList();
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
					List<Json_ShopOrder> jsonorderlist = clientorderService.list_shop_Orders(currentPage, wx_BindCustomer.getId());
					if (jsonorderlist.size() != 0) {
						for (int i = 0; i <jsonorderlist.size(); i++) {
							Json_ShopOrder json_shopOrder = jsonorderlist.get(i);
							JSONArray jsonorderlists = JSONArray.fromObject(jsonorderlist);
							jo.put("jsonorderlists", jsonorderlists);
							String orderNO = json_shopOrder.getOrdernumber();
							String errInfo = "success";
							if (null == orderNO || "".equals(orderNO)) {
								errInfo = "error";
							} else {
								encoderImgId = orderNO + ".png";
								encoderImgIdlist.add(encoderImgId);
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
				jo.put("encoderImgId", encoderImgIdlist);
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
	 * 去查看公司客户列表页面
	 * @return
	 */
	@RequestMapping(value="/shop/tocwddlb")
	public ModelAndView tocwddlb(HttpServletRequest request)throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		String factoryid =pd.getString("factoryid");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Wx_BindCustomer wx_BindCustomer  = (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
		Doc_Factory factroy  = (Doc_Factory) session.getAttribute(Const.SESSION_SHOPFACTORY);
		if(factroy==null){
			factroy = factoryService.findFactoryById(factoryid);
			session.setAttribute(Const.SESSION_SHOPFACTORY, factroy);
		}
		String  customerid ="";
		if(wx_BindCustomer !=null){
			  customerid =wx_BindCustomer.getId();
		}
		
		List<Shop_Client> list_shopclient =wxbindcustomerService.findClientByCustomerId(customerid,factoryid);
		pd.put("factoryid", factoryid);
		mv.addObject("pd",pd);
		mv.addObject("factroy",factroy);
		mv.addObject("list_shopclient",list_shopclient);
		mv.setViewName("shop_pc/xthd/cwddlb");
		return mv;
	}
	
	
	
	
	
	
	
	
}
