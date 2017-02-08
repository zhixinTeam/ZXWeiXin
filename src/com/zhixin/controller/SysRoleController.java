package com.zhixin.controller;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zhixin.base.BaseController;
import com.zhixin.entity.Json_Auth;
import com.zhixin.entity.Page;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Sys_Auth;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.AppUtil;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.AuthService;
import com.zhixin.service.RoleService;
import com.zhixin.tools.RoleUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="/role")
public class SysRoleController  extends BaseController{
	
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@Resource(name="authService")
	private AuthService authService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/listroles")
	public ModelAndView list(Page page)throws Exception{
			logBefore(logger, "SysRoleController_listroles");
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			try{
				String USERNAME = pd.getString("USERNAME");
				Subject currentUser = SecurityUtils.getSubject();
				Session session = currentUser.getSession();
				Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
				if(null != USERNAME && !"".equals(USERNAME)){
					USERNAME = USERNAME.trim();
					pd.put("USERNAME", USERNAME);
				}
				Sys_Role roletop =roleService.findRoleById(user.getSys_role().getId()); 
				List<Sys_Role> toplist = new ArrayList<>();
				toplist.add(roletop);
				List<Sys_Role> rolist= RoleUtil.getAllRoles(toplist);
				for(Sys_Role role:rolist){
					if(role.getId().equals(roletop.getId())){
						rolist.remove(role);
						break;
					}
				}
				String roleId = pd.getString("ROLE_ID");
				if(roleId == null || "".equals(roleId)){
					pd.put("ROLE_ID", roletop.getId());
				}
				mv.addObject("pd", pd);
				mv.addObject("roleList",rolist);
				mv.setViewName("system/role/role_list");
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}finally {
				logAfter(logger);
			}	
		return mv;
	}
	
	

	/**
	 * 新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(Page page){
		logBefore(logger, "SysRoleController_toAdd");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			mv.setViewName("system/role/role_add");
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	
	
	/**
	 * 保存新增信息
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add()throws Exception{
		logBefore(logger, "SysRoleController_add");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			Sys_Role parentrole =user.getSys_role();
			Doc_Factory doc_factory =user.getDoc_factory();
			String parent_id = pd.getString("PARENT_ID");		//父类角色id
			String rolename =pd.getString("ROLE_NAME");
			Sys_Role new_role =new Sys_Role();
			new_role.setRolename(rolename);
			new_role.setParent(parentrole);
			new_role.setDoc_factory(doc_factory);
			roleService.saveRole(new_role);
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
	 * 删除
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object deleteRole(@RequestParam String ROLE_ID)throws Exception{
		logBefore(logger, "SysRoleController_delete");
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		String errInfo = "";
		try{
			pd.put("ROLE_ID", ROLE_ID);
			String delrolid =ROLE_ID;
			String bolean =roleService.deleteService(delrolid);
			if("true".equals(bolean))
				errInfo = "success";
			else if("falseuser".equals(bolean))
				errInfo ="falseuser";
			else if("falsechildrole".equals(bolean))
				errInfo ="falsechildrole";
				
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 请求编辑
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit( String ROLE_ID )throws Exception{
		logBefore(logger, "SysRoleController_toEdit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd.put("ROLE_ID", ROLE_ID);
		    Sys_Role rolesys = roleService.findRoleById(ROLE_ID);
			
			mv.setViewName("system/role/role_edit");
			mv.addObject("pd", rolesys);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit()throws Exception{
		logBefore(logger, "SysRoleController_edit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String role_id =pd.getString("ROLE_ID");
			String role_name =pd.getString("ROLE_NAME");
			Sys_Role updaterole =roleService.findRoleByID(role_id);
			updaterole.setRolename(role_name);
			roleService.updateRole(updaterole);
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
	 * 请求角色菜单授权页面
	 */
	@RequestMapping(value="/auth")
	public String auth(@RequestParam String ROLE_ID,Model model)throws Exception{
		logBefore(logger, "SysRoleController_auth");
		try{
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			Sys_User userr = (Sys_User) session.getAttribute(Const.SESSION_USERROL);
			if(null == userr){
					session.setAttribute(Const.SESSION_USERROL, user);
			}else{
					user =userr;
				}
			//Sys_Role editrole =roleService.findRoleByID(ROLE_ID);
			//XXXX修改关联关系
			List<Sys_Auth> edit_authlist=authService.findAuthByRoleID(ROLE_ID);
			//List<Sys_Auth> edit_authlist = new ArrayList(editrole.getSys_auths());
			List<Sys_Auth> allauthList =null;
			if("admin".equals(user.getUsername())){
				allauthList=authService.findAllAuth();
			}else{
				Sys_Role role = user.getSys_role();
				allauthList=authService.findAuthByRoleID(role.getId());
				//role =roleService.findRoleByID( role.getId());
				//XXXX修改关联关系
				//Set<Sys_Auth> set_auth = role.getSys_auths();
				//allauthList = new ArrayList<Sys_Auth>(set_auth);
			}
			List<Json_Auth> jsonauthlist = new ArrayList<>();
			for(Sys_Auth sysauth:allauthList){
				if(sysauth.getChildren().size()>0){
					//父级菜单
					Json_Auth jsonauth = new Json_Auth();
					jsonauth.setAuthname(sysauth.getAuthname());
					jsonauth.setAuthpath(sysauth.getAuthpath());
					jsonauth.setIconcls(sysauth.getIconcls());
					jsonauth.setId(sysauth.getId());
					//jsonauth.setParentID(sysauth.getParent().getId());
					jsonauth.setAuthorder(sysauth.getAuthorder());
					jsonauth.setAuthtype(sysauth.getAuthtype());
					//子菜单
					Set<Sys_Auth> chisysauth = sysauth.getChildren();
					List<Json_Auth> childjsonauth = new ArrayList<>();
					for(Sys_Auth childauth:chisysauth){
						Json_Auth jsonauthchild = new Json_Auth();
						jsonauthchild.setAuthname(childauth.getAuthname());
						jsonauthchild.setAuthpath(childauth.getAuthpath());
						jsonauthchild.setIconcls(childauth.getIconcls());
						jsonauthchild.setId(childauth.getId());
						jsonauthchild.setParentID(childauth.getParent().getId());
						jsonauthchild.setAuthorder(childauth.getAuthorder());
						jsonauthchild.setAuthtype(childauth.getAuthtype());
						jsonauthchild.setHasAuth(false);
						childjsonauth.add(jsonauthchild);
						////XXXX修改关联关系
						for(Sys_Auth rightauth:edit_authlist){
							if ((rightauth.getId()).equals(jsonauthchild.getId())){
								jsonauthchild.setHasAuth(true);
							}
								
						}
					}
				//XXXX修改关联关系
					jsonauth.setHasAuth(false);
					for(Sys_Auth rightauth:edit_authlist){
						if ((rightauth.getId()).equals(jsonauth.getId())){
							jsonauth.setHasAuth(true);
						}
							
					}
					jsonauth.setChildjsonauth(childjsonauth);
					
					jsonauthlist.add(jsonauth);
				}
				
			}
			JSONArray arr = JSONArray.fromObject(jsonauthlist);
			String json = arr.toString();
			json = json.replaceAll("authname", "name").replaceAll("childjsonauth", "nodes").replaceAll("hasAuth", "checked");
			model.addAttribute("zTreeNodes", json);
			model.addAttribute("roleId", ROLE_ID);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
		return "authorization";
	}
	
	
	/**
	 * 保存角色菜单权限
	 */
	@RequestMapping(value="/authsave")
	public void saveAuth(@RequestParam String ROLE_ID,@RequestParam String menuIds,PrintWriter out)throws Exception{
		logBefore(logger, "SysRoleController_authsave");
		PageData pd = new PageData();
		try{
			roleService.updateRoleauth(ROLE_ID,menuIds);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
	}
	
	
}
