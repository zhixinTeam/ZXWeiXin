package com.zhixin.controller;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Page;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.PageBean;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.CompanyService;
import com.zhixin.service.FactoryService;

@Controller
@RequestMapping(value="/factory")
public class FactoryController  extends BaseController{

	
	
	@Resource(name="factoryService")
	private FactoryService factoryService;
	
	@Resource(name="companyService")
	private CompanyService companyService;
	/**
	 * 显示用户列表(用户组)
	 */
	@RequestMapping(value="/listfacs")
	public ModelAndView listUsers(Page page)throws Exception{
		logBefore(logger, "FactoryController_listfacs");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String status ="";
		String facname ="";
		String com_id ="";
		try{
			if(pd.toString().contains("STATUS"))
				status = pd.getString("STATUS");
			if(pd.toString().contains("facname"))
				facname =pd.getString("facname");
			if(pd.toString().contains("com_id"))
				com_id =pd.getString("com_id");
			
			//Subject currentUser = SecurityUtils.getSubject();
			//Session session = currentUser.getSession();
			//Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			String currentPage="";
			if(pd.toString().contains("currentPage"))
				currentPage =pd.getString("currentPage");
			else
				currentPage ="1";
			
			//PageBean pagebean =factoryService.findcompanys(currentPage,COMPANYNAME);
			List<Doc_Company> listcoms=companyService.findAllCompany();
			PageBean pagebean =factoryService.findFactorys(currentPage,status,facname,com_id);
			pd.put("pagecompany", pagebean);
			mv.setViewName("system/factory/factorys_list");
			mv.addObject("pagebeanlist", pagebean.getRecordList());
			mv.addObject("listcoms", listcoms);
			pd.put("pagebean", pagebean);
			pd.put("com_id", com_id);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
		return mv;
	}
}
