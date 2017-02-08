package com.zhixin.controller;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Json_Factory;
import com.zhixin.entity.Page;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.CompanyService;
import com.zhixin.service.FactoryService;
import com.zhixin.service.UserService;
import com.zhixin.tools.TimestampUtil;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value="company")
public class CompanyController extends BaseController{
	
	
	@Resource(name="userService")
	private UserService userService;
	
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	
	@Resource(name="factoryService")
	private FactoryService factoryService;
	
	/**
	 * 显示用户列表(用户组)
	 */
	@RequestMapping(value="/listcoms")
	public ModelAndView listUsers(Page page)throws Exception{
		logBefore(logger, "CompanyController_listcoms");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String COMPANYNAME ="";
		String status ="";
		try{
			if(pd.toString().contains("STATUS"))
				status = pd.getString("STATUS");
			if(pd.toString().contains("COMPANYNAME"))
				COMPANYNAME = pd.getString("COMPANYNAME");
			//Subject currentUser = SecurityUtils.getSubject();
			//Session session = currentUser.getSession();
			//Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			String currentPage="";
			if(pd.toString().contains("currentPage"))
				currentPage =pd.getString("currentPage");
			else
				currentPage ="1";
			PageBean pagebean =companyService.findcompanys(currentPage,COMPANYNAME,status);
			pd.put("pagecompany", pagebean);
			mv.setViewName("system/company/company_list");
			mv.addObject("pagebeanlist", pagebean.getRecordList());
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
		return mv;
	}
	
	
	/**
	 * 获取当前公司的所有部门
	 * @param menuId
	 * @param response
	 */
	@RequestMapping(value="/sub")
	public void getSub(@RequestParam String COMPANY_ID,HttpServletResponse response)throws Exception{
		logBefore(logger, "CompanyController_sub");
		try {
			    List<Json_Factory> listfac = companyService.getFactoryByCompyID(COMPANY_ID);
			    JSONArray arr = JSONArray.fromObject(listfac);
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
	
	
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		logBefore(logger, "CompanyController_goAdd");
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
			List<Doc_Company> comlist =companyService.findAllCompany();
			mv.setViewName("system/company/com_add");
			mv.addObject("msg", "saveCom");
			mv.addObject("pd", pd);
			mv.addObject("comlist", comlist);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	
	/**
	 * 保存集团工厂
	 */
	@RequestMapping(value="/saveCom")
	public ModelAndView saveU(PrintWriter out) throws Exception{
		logBefore(logger, "CompanyController_saveCom");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			if("comid".equals(pd.getString("category"))){
				Doc_Company com  = new Doc_Company();
				Doc_Factory fac = new Doc_Factory();
				Sys_User adminuser = new Sys_User();
				Sys_Role role = new Sys_Role();
				com.setAppid(pd.getString("appid"));
				com.setCompanyname(pd.getString("companyname"));
				com.setSecrectid(pd.getString("secrectid"));
				com.setWechatsub(pd.getString("wechatsub"));
				com.setOriginalID(pd.getString("originalID"));
				com.setCreatedate(TimestampUtil.getnowtime());
				com.setStatus(1);
				fac.setStatus(1);
				fac.setCreatedate(TimestampUtil.getnowtime());
				fac.setOrgcode(pd.getString("orgcode"));
				fac.setFactoryname(pd.getString("factoryname"));
				fac.setServiceparam(pd.getString("serviceparam")); 
				fac.setServiceurl(pd.getString("serviceurl"));
				role.setRolename("管理员");
				role.setRolebz("该公司最大管理员");
				adminuser.setUsername(pd.getString("adminname"));
				adminuser.setEmail(pd.getString("email"));
				adminuser.setMobile(pd.getString("mobile"));
				adminuser.setTruename(pd.getString("truename"));
				adminuser.setCreatedate(TimestampUtil.getnowtime());
				String pswd=new SimpleHash("SHA-1", pd.getString("adminname"), pd.getString("adminpassword")).toString();
				adminuser.setPassword(pswd);
				adminuser.setAdminfactory(fac);
				adminuser.setDoc_factory(fac);
				adminuser.setSys_role(role);
				Set< Doc_Factory> facset =new HashSet<Doc_Factory>();
				facset.add(fac);
				com.setDoc_factorys(facset);
				fac.setDoc_company(com);
				Set<Sys_Role> roleset = new HashSet<>();
				roleset.add(role);
				fac.setSys_roles(roleset);
				Set<Sys_User> setuser =new HashSet<>();
				setuser.add(adminuser);
				fac.setSys_users(setuser);
				fac.setUser(adminuser);
				role.setDoc_factory(fac);
				role.setSys_users(setuser);
				companyService.saveCompany(com,fac,role,adminuser);
			}else if("facid".equals(pd.getString("category"))){
				Doc_Company ylcompany =companyService.findCompanyByID(pd.getString("categorycom"));
				Doc_Factory fac = new Doc_Factory();
				Sys_User adminuser = new Sys_User();
				Sys_Role role = new Sys_Role();
				fac.setStatus(1);
				fac.setCreatedate(TimestampUtil.getnowtime());
				fac.setOrgcode(pd.getString("orgcode"));
				fac.setFactoryname(pd.getString("factoryname"));
				fac.setServiceparam(pd.getString("serviceparam"));
				fac.setServiceurl(pd.getString("serviceurl"));
				role.setRolename("管理员");
				role.setRolebz("该公司最大管理员");
				adminuser.setUsername(pd.getString("adminname"));
				adminuser.setCreatedate(TimestampUtil.getnowtime());
				String pswd=new SimpleHash("SHA-1", pd.getString("adminname"), pd.getString("adminpassword")).toString();
				adminuser.setPassword(pswd);
				adminuser.setAdminfactory(fac);
				adminuser.setDoc_factory(fac);
				adminuser.setSys_role(role);
				Set< Doc_Factory> facset =new HashSet<Doc_Factory>();
				facset.add(fac);
				ylcompany.setDoc_factorys(facset);
				fac.setDoc_company(ylcompany);
				Set<Sys_Role> roleset = new HashSet<>();
				roleset.add(role);
				fac.setSys_roles(roleset);
				Set<Sys_User> setuser =new HashSet<>();
				setuser.add(adminuser);
				fac.setSys_users(setuser);
				fac.setUser(adminuser);
				role.setDoc_factory(fac);
				role.setSys_users(setuser);
				companyService.updateCompany(ylcompany,fac,role,adminuser);
			}
			
			mv.addObject("msg","success");
			mv.setViewName("save_result");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	
	
	//修改公司 goEditCom
	@RequestMapping(value="/goEditCom")
	public ModelAndView goEditU() throws Exception{
		logBefore(logger, "CompanyController_goEditCom");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String currentPage =pd.getString("currentPage");
			String companyID =pd.getString("com_id");
			Doc_Company ylcompany =companyService.findCompanyByID(companyID);
			pd.put("companyID", ylcompany.getId());
			pd.put("companyname", ylcompany.getCompanyname());
			pd.put("wechatsub", ylcompany.getWechatsub());
			pd.put("Appid", ylcompany.getAppid());
			pd.put("Secrectid", ylcompany.getSecrectid());
			pd.put("status", ylcompany.getStatus());
			mv.setViewName("system/company/com_edit");
			mv.addObject("msg", "editC");
			mv.addObject("pd", pd);
			mv.addObject("roleList", new ArrayList());
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	/**
	 * 修改集团
	 */
	@RequestMapping(value="/editC")
	public ModelAndView editU(PrintWriter out) throws Exception{
		logBefore(logger, "CompanyController_editC");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String currentPage =pd.getString("currentPage");
			pd.put("currentPage", currentPage);
			String companyID =pd.getString("companyID");
			Doc_Company company =companyService.findCompanyByID(companyID);
			String companyname =pd.getString("companyname");
			String secrectid =pd.getString("Secrectid");
			String wechatsub =pd.getString("wechatsub");
			String appid =pd.getString("Appid");
			String status =pd.getString("status");
			company.setAppid(appid);
			company.setCompanyname(companyname);
			company.setSecrectid(secrectid);
			company.setWechatsub(wechatsub);
			company.setStatus(Integer.parseInt(status));
			companyService.updateCompany(company);
			mv.addObject("pd", pd);
			mv.addObject("msg","success");
			mv.setViewName("save_result");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	
	//修改公司 goEditFac
	@RequestMapping(value="/goEditFac")
	public ModelAndView goEditFac() throws Exception{
			logBefore(logger, "CompanyController_goEditFac");
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			try{
				String currentPage =pd.getString("currentPage");
				String factoryId =pd.getString("factoryId");
				Doc_Factory factory =factoryService.findFactoryById(factoryId);
				Sys_User sys_user = factory.getUser();
				pd.put("username", sys_user.getUsername());
				pd.put("sys_userid", sys_user.getId());
				pd.put("factoryID", factory.getId());
				pd.put("factoryname", factory.getFactoryname());
				pd.put("serviceurl", factory.getServiceurl());
				pd.put("serviceparam", factory.getServiceparam());
				pd.put("STATUS", factory.getStatus());
				pd.put("editlog", factory.getEditlog());
				pd.put("startdate", factory.getStartdate());
				pd.put("enddate", factory.getEnddate());
				mv.setViewName("system/company/fac_edit");
				mv.addObject("msg", "editF");
				mv.addObject("pd", pd);
				mv.addObject("roleList", new ArrayList());
			} catch(Exception e){
				logger.error(e.toString(), e);
			}finally {
				logAfter(logger);
			}
			//mv.addObject("roleList", new ArrayList());
			return mv;
		}
	
	/**
	 * 修改工厂
	 */
	@RequestMapping(value="/editF")
	public ModelAndView editF(PrintWriter out) throws Exception{
		logBefore(logger, "CompanyController_editF");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String user_password =pd.getString("password");
			//修改管理员密码
			if(!"".equals(user_password)){
				String sys_userid =pd.getString("sys_userid");
				Sys_User yluser =userService.findUserByID(sys_userid);
				String username =pd.getString("username");
				String pswd=new SimpleHash("SHA-1", username, user_password).toString();
				yluser.setUsername(username);
				yluser.setPassword(pswd);
				userService.updateUserName(yluser);
			}
			String currentPage =pd.getString("currentPage");
			pd.put("currentPage", currentPage);
			String factoryID =pd.getString("factoryID");
			Doc_Factory factory =factoryService.findFactoryById(factoryID);
			factory.setFactoryname(pd.getString("factoryname"));
			factory.setServiceurl(pd.getString("serviceurl"));
			factory.setServiceparam(pd.getString("serviceparam"));
			if("1".equals(pd.getString("STATUS")))
				factory.setStatus(1);
			else
				factory.setStatus(0);
			factory.setEditlog(pd.getString("editlog"));
			if(! "".equals(pd.getString("startdate")))
				factory.setStartdate(Date.valueOf(pd.getString("startdate")));
			if(! "".equals(pd.getString("enddate")))
				factory.setEnddate(Date.valueOf(pd.getString("enddate")));
			factoryService.updateFactory(factory);
			mv.addObject("pd", pd);
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
	 * 状态修改
	 */
	@RequestMapping(value="/editstatus")
	public ModelAndView editstatus()throws Exception{
		logBefore(logger, "CompanyController_editstatus");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String factoryid = pd.getString("factoryid");
			String status =pd.getString("status");
			factoryService.updatefacStatus(factoryid,status);
			mv.setViewName("save_result");
			mv.addObject("msg","success");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return mv;
	}
	
	
}
