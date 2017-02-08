package com.zhixin.controller.shop;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zhixin.entity.Json_Goods;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Shop_User;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.AppUtil;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.DateUtil;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.Tools;
import com.zhixin.service.shop.ShopUserService;
import com.zhixin.tools.MobileMessageSend;
import com.zhixin.tools.TokenProccessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value="/shop")
public class ShopLoginController extends BaseController{

	
	@Resource(name="shopuserService")
	private ShopUserService shopuserService;
	
	
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
	 * 访问商城主页
	 * @return
	 */
	@RequestMapping(value="/shop")
	public ModelAndView toIndex()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
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
			
			mv.addObject("shopuser",shopuser);
		}
		
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("shop/login/index");
		return mv;
	}
	
	
	
	/**
	 * 访问商城主页
	 * @return
	 */
	@RequestMapping(value="/edit_pwd")
	public ModelAndView edit_pwd()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
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
			
			mv.addObject("shopuser",shopuser);
		}
		
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("shop/login/edit_pwd");
		return mv;
	}
	
	
	
	/**
	 * 根据phone 获取验证码
	 */
	@RequestMapping(value="/find_code")
	public void find_code(HttpServletResponse response){
		logBefore(logger, "SysUserController_deleteU");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String resu =MobileMessageSend.sendMsg(pd.getString("username"));
			String obj=JSON.parseObject(resu).getString("obj");
			String code=JSON.parseObject(resu).getString("code");
			 JSONObject jo = new JSONObject();
			 jo.put("code",code);
			 jo.put("obj", obj);
			 session.setAttribute("old_code", obj);
			 PrintWriter out;
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write("");
			 out.flush();
			 out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}
	
	/**
	 * 根据phone 修改密码
	 */
	@RequestMapping(value="/save_pwd")
	public void save_pwd(HttpServletResponse response,HttpServletRequest request){
		logBefore(logger, "SysUserController_deleteU");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String code =pd.getString("code");
			String err_str="密码修改成功";
			String old_code= (String) session.getAttribute("old_code");
			//String old_code="1234";
			String username =pd.getString("username");
			String password =pd.getString("password");
			String factoryid=request.getParameter("factoryid");
			Shop_User shopuser =shopuserService.findShopUserByUsername(username,factoryid);
			if(shopuser ==null){
				err_str="用户名不存在!";
			}else{
				if(!code.equals(old_code))
					err_str="验证码失效，请再次获取";
				else{
					String passwd = new  SimpleHash("SHA-1",username+factoryid,password).toString();
					shopuserService.updateShopUserPwd(username, passwd, factoryid);
				}
			}
			
			session.removeAttribute("old_code");
			List<String> list_str= new ArrayList<>();
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			list_str.add(err_str);
			JSONObject jo = new JSONObject();
			jo.put("err_str",err_str);
			out.write(jo.toString());
			out.flush();
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}
	
	
	
	/**
	 * 访问运营页面
	 * @return
	 */
	@RequestMapping(value="/process")
	public ModelAndView process()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");

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
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		mv.addObject("pd",pd);
		mv.addObject("shopuser",shopuser);
		mv.setViewName("shop/login/process");
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
		mv.setViewName("shop/login/login-page");
		return mv;
	}
	
	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/shop_login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object login()throws Exception{
		logBefore(logger, "LoginController_login_login");
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo ="";
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qqbigbug", "").replaceAll("qqbigbug", "").split(",zx,");
		if(null !=KEYDATA && KEYDATA.length==3){
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String sessionCode =(String)session.getAttribute(Const.SESSION_SECURITY_CODE);
			String code =KEYDATA[2];
			if(null ==code || "".equals(code)){
				errInfo ="nullcode";
			}else{
				String USERNAME =KEYDATA[0];
				String PASSWORD =KEYDATA[1];
				pd.put("USERNAME", USERNAME);
				if(Tools.notEmpty(sessionCode)&&sessionCode.equalsIgnoreCase(code)){
					String passwd = new  SimpleHash("SHA-1",USERNAME,PASSWORD).toString();
					Shop_User shop_user =shopuserService.getShopUserByNameAndPWd(passwd);
					if(shop_user !=null){
							//更新最后登录时间
							//String last_login =DateUtil.getTime().toString();
							shop_user.setLast_login(DateUtil.getTime().toString());
							shop_user.setIp(this.getRemortIP());
							shopuserService.updateLastLogin(shop_user);
							session.setAttribute(Const.SESSION_SHOPUSER, shop_user);
							session.removeAttribute(Const.SESSION_SECURITY_CODE);
							Subject subject =SecurityUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(USERNAME,PASSWORD);
							try{
								subject.login(token);
							}catch(AuthenticationException ex){
								errInfo = "身份验证失败！";
							}
						}else{
							errInfo ="usererror";
						}
				}else{
					errInfo ="codeerror";
				}
				
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
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		session.removeAttribute(Const.SESSION_SHOPUSER);
		/*session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_allmenuList);
		session.removeAttribute(Const.SESSION_menuList);
		session.removeAttribute(Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute("changeMenu");*/
		session.removeAttribute(Const.SESSION_SHOPUSERROL);
		
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		
		pd = this.getPageData();
		String  msg = pd.getString("msg");
		pd.put("msg", msg);
		
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.setViewName("shop/login/login-page");
		mv.addObject("pd",pd);
		return mv;
	}
	
	
	
	
	
}
