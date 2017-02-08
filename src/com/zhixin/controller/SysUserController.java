package com.zhixin.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Page;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.RoleService;
import com.zhixin.service.UserService;
import com.zhixin.tools.RoleUtil;


@Controller
@RequestMapping(value="/user")
public class SysUserController extends  BaseController{

	
	@Resource(name="userService")
	private UserService userService;
	
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	/**
	 * 显示用户列表(用户组)
	 */
	@RequestMapping(value="/listUsers")
	public ModelAndView listUsers(Page page)throws Exception{
		logBefore(logger, "SysUserController_listUsers");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String roleid ="";
			String username ="";
			if(pd.toString().contains("ROLE_ID"))
				roleid = pd.getString("ROLE_ID");
			if(pd.toString().contains("USERNAME"))
				username = pd.getString("USERNAME");
			pd.put("selectroleid", roleid);	
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			pd.put("roleid", user.getSys_role().getId());
			pd.put("loginid", user.getId());
			page.setPd(pd); 
			String currentPage="";
			if(pd.toString().contains("currentPage"))
				 currentPage =pd.getString("currentPage");
			else
				currentPage ="1";
			PageBean pageuser =userService.findpageusers(currentPage ,user.getDoc_factory().getId(),username,roleid);
			pd.put("pageuser", pageuser);	
			Sys_Role roletop =roleService.findRoleById(user.getSys_role().getId()); 
			List<Sys_Role> toplist = new ArrayList<>();
			toplist.add(roletop);
			List<Sys_Role> listroles=RoleUtil.getAllRoles(toplist);
			mv.setViewName("system/user/user_list");
			mv.addObject("userList", pageuser.getRecordList());
			mv.addObject("roleList", listroles);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	
	/**
	 * 去新增用户页面
	 */
	@RequestMapping(value="/goAddU")
	public ModelAndView goAddU()throws Exception{
		logBefore(logger, "SysUserController_goAddU");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String currentPage =pd.getString("currentPage");
			pd.put("currentPage", currentPage);
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			pd.put("USERNAME", user.getUsername());
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
			mv.setViewName("system/user/user_edit");
			mv.addObject("msg", "saveU");
			mv.addObject("pd", pd);
			mv.addObject("roleList", rolist);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	/**
	 * 去修改系统用户页面
	 */
	@RequestMapping(value="/goEditU")
	public ModelAndView goEditU() throws Exception{
		logBefore(logger, "SysUserController_goEditU");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String currentPage =pd.getString("currentPage");
			String fx = pd.getString("fx");
			pd.put("currentPage", currentPage);
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User creauser = (Sys_User) session.getAttribute(Const.SESSION_USER);
			pd.put("USERNAME", creauser.getUsername());
			Sys_Role roletop =roleService.findRoleById(creauser.getSys_role().getId()); 
			List<Sys_Role> toplist = new ArrayList<>();
			toplist.add(roletop);
			List<Sys_Role> rolist= RoleUtil.getAllRoles(toplist);
			for(Sys_Role role:rolist){
				if(role.getId().equals(roletop.getId())){
					rolist.remove(role);
					break;
				}
			}
			String userid ="";
			if("head".equals(fx)){
				mv.addObject("fx", "head");
				userid=creauser.getId();
			}else{
				mv.addObject("fx", "user");
				userid =pd.getString("USER_ID");
			}
			
			Sys_User user = userService.findUserByID(userid);
			pd.put("username", user.getUsername());
			pd.put("susernumber", user.getSusernumber());
			pd.put("password", "");
			pd.put("chkpwd", "");
			pd.put("truename", user.getTruename());
			pd.put("mobile", user.getMobile());
			pd.put("email", user.getEmail());
			pd.put("BZ", user.getBz());
			pd.put("ylroid", user.getSys_role().getId());
			pd.put("USER_ID", user.getId());
			mv.setViewName("system/user/user_edit");
			mv.addObject("msg", "editU");
			mv.addObject("pd", pd);
			mv.addObject("roleList", rolist);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping(value="/editU")
	public ModelAndView editU(PrintWriter out) throws Exception{
		logBefore(logger, "SysUserController_editU");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String currentPage =pd.getString("currentPage");
		pd.put("currentPage", currentPage);
		try{
			String roleid =pd.getString("ROLE_ID");
			String userid =pd.getString("USER_ID");
			Sys_Role sys_role = userService.findRolebyID(roleid);
			Sys_User yluser = userService.findUserByID(userid);
			if(pd.getString("password") != null && !"".equals(pd.getString("password"))){
				yluser.setPassword(new SimpleHash("SHA-1", pd.getString("username"), pd.getString("password")).toString());
			}
			yluser.setTruename(pd.getString("truename"));
			yluser.setMobile(pd.getString("mobile"));
			yluser.setBz(pd.getString("BZ"));
			userService.updateYlUser(yluser);
			yluser.setSys_role(sys_role);
			userService.updateUsermerge(yluser);
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}

		mv.addObject("pd", pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 保存用户
	 */
	@RequestMapping(value="/saveU")
	public ModelAndView saveU(PrintWriter out) throws Exception{
		logBefore(logger, "SysUserController_saveU");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Sys_User newuser = new Sys_User();
			Sys_User xtuser = userService.findByUserName(pd.getString("USERNAME"));
			newuser.setParent(xtuser);
			newuser.setUsername(pd.getString("username"));
			newuser.setSusernumber(pd.getString("susernumber"));
			String pswd=new SimpleHash("SHA-1", pd.getString("username"), pd.getString("password")).toString();
			newuser.setPassword(pswd);
			newuser.setTruename(pd.getString("truename"));
			newuser.setMobile(pd.getString("mobile"));
			newuser.setEmail(pd.getString("email"));
			newuser.setBz(pd.getString("BZ"));
			String roleid =pd.getString("ROLE_ID");
			Sys_Role roleu = userService.findRolebyID(roleid);
			Doc_Factory fac =roleu.getDoc_factory();
			newuser.setSys_role(roleu);
			newuser.setDoc_factory(fac);
			mv.addObject("page1","1");
			userService.savesUser(newuser);
			mv.addObject("msg","success");
			mv.setViewName("save_result");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	/**
	 * 判断用户名是否存在
	 */
	@RequestMapping(value="/hasU")
	public void hasU(PrintWriter out){
		logBefore(logger, "SysUserController_hasU");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String newusername =pd.getString("newusername");
			if(userService.findByUId(newusername) != null){
				out.write("error");
			}else{
				out.write("success");
			}
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}
	
	/**
	 * 判断邮箱是否存在
	 */
	@RequestMapping(value="/hasE")
	public void hasE(PrintWriter out){
		logBefore(logger, "SysUserController_hasE");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String email =pd.getString("email");
			String username =pd.getString("USERNAME");
			if(userService.findByUE(email,username) ){
				out.write("success");
			}else{
				out.write("error");
			}
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}
	
	/**
	 * 判断编码是否存在
	 */
	@RequestMapping(value="/hasN")
	public void hasN(PrintWriter out){
		logBefore(logger, "SysUserController_hasN");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String susernumber = pd.getString("susernumber");
			String username =pd.getString("USERNAME");
			if(userService.findByUN(username,susernumber)){
				out.write("success");
			}else{
				out.write("error");
			}
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}


	/**
	 * 删除用户
	 */
	@RequestMapping(value="/deleteU")
	public void deleteU(PrintWriter out){
		logBefore(logger, "SysUserController_deleteU");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String userid =pd.getString("USER_ID");
			String currentPage =pd.getString("currentPage");
			userService.deleteU(userid);
			out.write(currentPage);
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}

	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAllU")
	@ResponseBody
	public Object deleteAllU() {
		logBefore(logger, "SysUserController_deleteAllU");
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String USER_IDS = pd.getString("USER_IDS");
			if(null != USER_IDS && !"".equals(USER_IDS)){
				String ArrayUSER_IDS[] = USER_IDS.split(",");
				//userService.deleteAllU(ArrayUSER_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return new ArrayList();
	}
	
	
	
}
