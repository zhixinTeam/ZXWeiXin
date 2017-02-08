package com.zhixin.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.zhixin.base.BaseController;
import com.zhixin.entity.Json_Auth;
import com.zhixin.model.Sys_Auth;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.AuthService;
import com.zhixin.service.RoleService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="auth")
public class AuthController extends BaseController {
	
	
	@Resource(name="authService")
	private AuthService authService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	
	
	/**
	 * 显示菜单列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listauth")
	public ModelAndView list()throws Exception{
		logBefore(logger, "AuthController_listauth");
		ModelAndView mv = this.getModelAndView();
		try{
			PageData pd = new PageData();
			pd = this.getPageData();
			String USERNAME = pd.getString("USERNAME");
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			Sys_Role role_now =user.getSys_role();
			Sys_Role sys_role =roleService.findRoleById(role_now.getId());
			List<Sys_Auth> authlist =authService.findAllAuth();
			List<Sys_Auth> parentList =new ArrayList();
			for(Sys_Auth auth:authlist){
				if (auth.getParent()==null){
					parentList.add(auth);
				}
			}
			mv.addObject("menuList", parentList);
			mv.setViewName("system/menu/menu_list");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
		return mv;
	}

	
	/**
	 * 请求新增菜单页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd()throws Exception{
		logBefore(logger, "AuthController_toAdd");
		ModelAndView mv = this.getModelAndView();
		try{
			List<Sys_Auth> authList = authService.listAllParentAuth();
			mv.addObject("menuList", authList);
			mv.setViewName("system/menu/menu_add");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	/**
	 * 保存菜单信息
	 * @param menu
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(Sys_Auth auth)throws Exception{
		logBefore(logger, "AuthController_add");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
				String authorder =pd.getString("authorder");
				String authpath =pd.getString("authpath");
				String authname=pd.getString("authname");
				String authtype= pd.getString("authtype");
				String parentid = pd.getString("PARENT_ID");
				String authicon =pd.getString("form-field-radio");
				Sys_Auth pauth = new Sys_Auth();
				pauth.setAuthname(authname);
				pauth.setAuthorder(authorder);
				pauth.setAuthpath(authpath);
				pauth.setAuthtype(authtype);
				pauth.setState("1");
				if("0".equals(parentid)){
						pauth.setIconcls(authicon);
						authService.saveAuthparent(pauth);
				}else{
						authService.updateAuthByParentID(pauth,parentid);
				}
				mv.addObject("msg","success");
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}finally {
			logAfter(logger);
		}
		mv.setViewName("save_result");
		return mv;
		
	}
	
	
	/**
	 * 获取当前菜单的所有子菜单
	 * @param menuId
	 * @param response
	 */
	@RequestMapping(value="/sub")
	public void getSub(@RequestParam String AUTH_ID,HttpServletResponse response)throws Exception{
		logBefore(logger, "AuthController_sub");
		try {
			List<Json_Auth> subAuth = authService.listSubAuthByParentId(AUTH_ID);
			JSONArray arr = JSONArray.fromObject(subAuth);
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String json = arr.toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
	}
	
	
	 /* 请求编辑菜单图标页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toEditicon")
	public ModelAndView toEditicon(String AUTH_ID)throws Exception{
		logBefore(logger, "AuthController_toEditicon");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd.put("MENU_ID",AUTH_ID);
			mv.addObject("pd", pd);
			mv.setViewName("system/menu/menu_icon");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	/**
	 * 保存菜单图标 (顶部菜单)
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/editicon")
	public ModelAndView editicon()throws Exception{
		logBefore(logger, "AuthController_editicon");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			authService.updateediticon(pd);
			mv.addObject("msg","success");
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}finally {
			logAfter(logger);
		}
		mv.setViewName("save_result");
		return mv;
	}


	
	/**
	 * 请求编辑菜单页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(String AUTH_ID)throws Exception{
		logBefore(logger, "toEdit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String authid =pd.getString("AUTH_ID");
			List<Sys_Auth> parauthlist =authService.listAllParentAuth();
			Sys_Auth ylauth = authService.getAuthByID(authid);
			pd.put("authname",ylauth.getAuthname() );
			pd.put("authorder", ylauth.getAuthorder());
			pd.put("authpath", ylauth.getAuthpath());
			pd.put("authtype", ylauth.getAuthtype());
			pd.put("id", ylauth.getId());
			if (null != ylauth.getParent())
				pd.put("parentid", ylauth.getParent().getId());
			else
				pd.put("parentid", "0");
			mv.addObject("menuList", parauthlist);
			mv.addObject("pd", pd);
			mv.setViewName("system/menu/menu_edit");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	/**
	 * 保存编辑
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit()throws Exception{
		logBefore(logger, "edit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String PARENT_ID = pd.getString("PARENT_ID");//修改后的pid
			if(null == PARENT_ID || "".equals(PARENT_ID)){
				PARENT_ID = "0";
				pd.put("PARENT_ID", PARENT_ID);
			}
			String authorder = pd.getString("authorder");
			String authpath = pd.getString("authpath");
			String authname =pd.getString("authname");
			String pId = pd.getString("pId"); //原来的父id
			String id = pd.getString("id");
			String authtype=pd.getString("authtype");
			Sys_Auth authmodel = new Sys_Auth();
			authmodel.setId(id);
			authmodel.setAuthname(authname);
			authmodel.setAuthorder(authorder);
			authmodel.setAuthpath(authpath);
			authmodel.setAuthtype(authtype);
			if("0".equals(PARENT_ID)){
				authService.updateAuthparent(authmodel);
			}else{
				authService.updateAuthandParent(authmodel,pId,PARENT_ID);
			}
			mv.addObject("msg","success");
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}finally {
			logAfter(logger);
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/**
	 * 删除菜单
	 * @param menuId
	 * @param out
	 */
	@RequestMapping(value="/del")
	public void delete(@RequestParam String authid,PrintWriter out)throws Exception{
		
		try{
			authService.deleteauthById(authid);
			out.write("success");
			out.flush();
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}
	
}
