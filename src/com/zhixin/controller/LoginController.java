package com.zhixin.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhixin.base.BaseController;
import com.zhixin.model.Sys_Auth;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.AppUtil;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.DateUtil;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.Tools;
import com.zhixin.service.AuthService;
import com.zhixin.service.RoleService;
import com.zhixin.service.UserService;


/*
 * 总入口
 */
@Controller
public class LoginController  extends  BaseController{

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="authService")
	private AuthService authService;
	
	
	
	
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
	 * 访问登录页
	 * @return
	 */
	//@RequestMapping(value="/login_toLogin")
	@RequestMapping(value="/sys")
	public ModelAndView toLogin()throws Exception{
		logBefore(logger, "LoginController_login_toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd =this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		mv.setViewName("system/admin/login");
		return mv;
	}
	
	
	
	
	
	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/login_login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object login()throws Exception{
		logBefore(logger, "LoginController_login_login");
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo ="";
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qqbigbug", "").replaceAll("qqbigbug", "").split(",fh,");
		
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
					Sys_User sys_user =userService.getUserByNameAndPWd(passwd);
					if(sys_user !=null){
						//更新最后登录时间
						//String last_login =DateUtil.getTime().toString();
						sys_user.setLast_login(DateUtil.getTime().toString());
						sys_user.setIp(this.getRemortIP());
						userService.updateLastLogin(sys_user);
						session.setAttribute(Const.SESSION_USER, sys_user);
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
				if(Tools.isEmpty(errInfo)){
					
					errInfo = "success";					//验证成功
					
				}
				
			}
		}else{
			errInfo ="error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
		
	}
	
	/**
	 * 访问系统首页
	 */
	@RequestMapping(value="/main/{changeMenu}")
	public ModelAndView login_index(@PathVariable("changeMenu") String changeMenu){
		logBefore(logger, "LoginController_main_"+changeMenu);
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			if(user !=null){
				Sys_User userr = (Sys_User) session.getAttribute(Const.SESSION_USERROL);
				if(null == userr){
					session.setAttribute(Const.SESSION_USERROL, user);
				}else{
					user =userr;
				}
				List<Sys_Auth> allauthList =null;
				Sys_Role role = user.getSys_role();
				if("admin".equals(user.getUsername())){
					allauthList=authService.findAllAuth();
				}else{
					
					allauthList =authService.findAuthByRoleID(role.getId());
				}
				if(null == session.getAttribute(Const.SESSION_allmenuList)){
					session.setAttribute(Const.SESSION_allmenuList, allauthList);			//菜单权限放入session中
				}else{
					allauthList = (List<Sys_Auth>)session.getAttribute(Const.SESSION_allmenuList);
				}
				//切换菜单
				List<Sys_Auth> authList = new ArrayList<Sys_Auth>();
				
				if(null == session.getAttribute(Const.SESSION_menuList) || ("yes".equals(changeMenu))){
					List<Sys_Auth> menuList1 = new ArrayList<Sys_Auth>();
					List<Sys_Auth> menuList2 = new ArrayList<Sys_Auth>();
					//拆分菜单
					for(Sys_Auth auth:allauthList){
						if("1".equals(auth.getAuthtype()))
							menuList1.add(auth);
						else
							menuList2.add(auth);
					}
					session.removeAttribute(Const.SESSION_menuList);
					if("2".equals(session.getAttribute("changeMenu"))){
						session.setAttribute(Const.SESSION_menuList, menuList1);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "1");
						authList = menuList1;
					}else{
						session.setAttribute(Const.SESSION_menuList, menuList2);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "2");
						authList = menuList2;
					}
				}else{
					authList = (List<Sys_Auth>)session.getAttribute(Const.SESSION_menuList);
				}
				// 菜单树行排列
				List<Sys_Auth> rootlist = new ArrayList<Sys_Auth>();
				Sys_Auth auth2 = new Sys_Auth();
				for(Sys_Auth auth1:authList){
					if(auth1.getParent()==null){
						rootlist.add(auth1);
					}
				}
				pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
				session.setAttribute(Const.SESSION_authList, rootlist);
				mv.addObject("pd",pd);
				mv.addObject("user",user);
				mv.setViewName("system/admin/index");
				mv.addObject("authList",rootlist);
				
			}
		}catch(Exception e){
			mv.setViewName("system/admin/login");
			logger.error(e.getMessage(), e);
		}finally {
			logAfter(logger);
		}
		
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 进入tab标签
	 * @return
	 */
	@RequestMapping(value="/tab")
	public String tab(){
		return "system/admin/tab";
	}
	
	/**
	 * 进入首页后的默认页面
	 * @return
	 */
	@RequestMapping(value="/login_default")
	public String defaultPage(){
		return "system/admin/default";
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
		Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
		user.setIsonline(0);
		userService.updateUser(user);
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_allmenuList);
		session.removeAttribute(Const.SESSION_menuList);
		session.removeAttribute(Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute(Const.SESSION_USERROL);
		session.removeAttribute("changeMenu");
		
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		
		pd = this.getPageData();
		String  msg = pd.getString("msg");
		pd.put("msg", msg);
		
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.setViewName("system/admin/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	
    
	
	
}
