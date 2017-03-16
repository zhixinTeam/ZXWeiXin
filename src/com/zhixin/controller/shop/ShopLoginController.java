package com.zhixin.controller.shop;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zhixin.base.BaseController;
import com.zhixin.entity.Json_Client;
import com.zhixin.entity.Json_Doc_Factory;
import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Goods;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.right_utils.AppUtil;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.DateUtil;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.Tools;
import com.zhixin.service.FactoryService;
import com.zhixin.service.WxBindCustomerService;
import com.zhixin.service.shop.ShopUserService;
import com.zhixin.tools.MobileMessageSend;
import com.zhixin.tools.TimestampUtil;
import com.zhixin.tools.TokenProccessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value="/shop")
public class ShopLoginController extends BaseController{

	@Resource(name="factoryService")
	private FactoryService factoryService;
	
	
	@Resource(name="shopuserService")
	private ShopUserService shopuserService;
	
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
	 * 车辆管理页面
	 * @return
	 */
	@RequestMapping(value="/totjsja")
	public ModelAndView toTjsj(HttpServletRequest request)throws Exception{
		logBefore(logger, "LoginController_login_totjsja");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Wx_BindCustomer wx_BindCustomer=(Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		String uuid=get32UUID().toString();
		request.setAttribute("token", uuid);
		HttpSession ss=(HttpSession)request.getSession();
		ss.setAttribute("token", uuid);
		mv.addObject("pd",pd);
		mv.addObject("wx_BindCustomer",wx_BindCustomer);
		mv.setViewName("shop_pc/user/tjsja");
		return mv;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 访问商城主页
	 * @return
	 */
	@RequestMapping(value="/toindex")
	public ModelAndView toIndex()throws Exception{
		logBefore(logger, "LoginController_login_toIndex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("shop_pc/login/index");
		return mv;
	}
	
	
	

	
	/**
	 * 访问登录页面
	 * @return
	 */
	@RequestMapping(value="/login_toLogin")
	public ModelAndView toLogin()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("shop_pc/login/login");
		return mv;
	}
	
	/**
	 * 访问登录页面
	 * @return
	 */
	@RequestMapping(value="/toxgmm")
	public ModelAndView toUpdatePassword()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("shop_pc/user/xgmm");
		return mv;
	}
	
	/**
	 * 开始修改密码
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
				if(wx_BindCustomer.getPassword()==null){
					String passwd = new  SimpleHash("SHA-1",username,password).toString();
					wx_BindCustomer.setPassword(passwd);	
				}
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
	

	
	
	
	/**
	 *
	 * 到公共内容
	 *
	 */
	@ResponseBody
	@RequestMapping("/tocommon")
	public void common( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		JSONObject jo = new JSONObject();
		PageData pd = new PageData();
		try {
			pd=this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Wx_BindCustomer wx_BindCustomer= (Wx_BindCustomer) session.getAttribute(Const.SESSION_CLIENTUSER);
			if(wx_BindCustomer==null)
				jo.put("msg", "error");	
			else {
				List<Json_Doc_Factory> list_Json_Doc_Factory=factoryService.findFactoryByCustomerId(wx_BindCustomer.getId());
				if(list_Json_Doc_Factory.size()==0||list_Json_Doc_Factory==null){
					jo.put("msg", "err");	
				}else{
					jo.put("list_Json_Doc_Factory", list_Json_Doc_Factory);
					jo.put("msg", "success");	
				}
				}
			
			}catch(Exception e) {
				e.printStackTrace();
				jo.put("msg", "err");
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
	 * 访问注册页面
	 * @return
	 */
	@RequestMapping(value="/toregister")
	public ModelAndView toregister()throws Exception{
		logBefore(logger, "LoginController_toregister");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("shop_pc/login/register");
		return mv;
	}
	
	
	/**
	 * 开始注册用户
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/register")
	public void beginregister( HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		    response.setHeader("P3P","CP=CAO PSA OUR"); 
		    JSONObject jo = new JSONObject();
		    Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Wx_BindCustomer wx_BindCustomer=new Wx_BindCustomer();
				String phone=request.getParameter("phone");
				String email=request.getParameter("email");
				String password=request.getParameter("password");
				String username=request.getParameter("username");
				String passwd = new  SimpleHash("SHA-1",username,password).toString();
				String id=this.get32UUID();
				wx_BindCustomer.setPc_password(passwd);
				wx_BindCustomer.setNamepinyin(username);
				wx_BindCustomer.setEmail(email);
				wx_BindCustomer.setPhone(phone);
				wx_BindCustomer.setBinddate(TimestampUtil.getnowtime());
				wx_BindCustomer.setId(id);
				wx_BindCustomer.setStatus(0);
				wx_BindCustomer.setOpenid(null);
				wxbindcustomerService.saveCustomer(wx_BindCustomer);
				jo.put("msg", "success");
			
			 PrintWriter out;
			 response.setContentType("application/json;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 out.flush();
			 out.close();
		
	}
	
	
	
	/**
	 * 访问注册页面
	 * @return
	 */
	@RequestMapping(value="/toadd")
	public ModelAndView toadd()throws Exception{
		logBefore(logger, "LoginController_toregister");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("shop_pc/user/add");
		return mv;
	}
	
	/**
	 * 访问注册页面
	 * @return
	 */
	@RequestMapping(value="/toxgsj")
	public ModelAndView toxgsj()throws Exception{
		logBefore(logger, "LoginController_toregister");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("shop_pc/user/xgsj");
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
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo ="";
		String sessionid="";
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qqbigbug", "").replaceAll("qqbigbug", "").split(",zx,");
		if(null !=KEYDATA && KEYDATA.length==2){
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String USERNAME =KEYDATA[0];
			String PASSWORD =KEYDATA[1];
			pd.put("USERNAME", USERNAME);
			
			String passwd = new  SimpleHash("SHA-1",USERNAME,PASSWORD).toString();
			Wx_BindCustomer wx_BindCustomer =shopuserService.getWx_BindCustomerPWd(passwd);
			if(wx_BindCustomer !=null){
							shopuserService.updateLastLogin(wx_BindCustomer);
							sessionid =wx_BindCustomer.getId();
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
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	

	
	
	
}
