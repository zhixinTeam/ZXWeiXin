package com.zhixin.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Page;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Msg_Type;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.DateUtil;
import com.zhixin.right_utils.DelAllFile;
import com.zhixin.right_utils.FileUpload;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.PathUtil;
import com.zhixin.service.CompanyService;
import com.zhixin.service.WxTemplateService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "template")
public class WxTemplateController extends BaseController {

	@Resource(name = "wxTemplateService")
	private WxTemplateService wxTemplateService;

	@Resource(name = "companyService")
	private CompanyService companyService;

	/**
	 * 显示模板列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView listappactivitys(Page page) throws Exception {
		logBefore(logger, "WxTemplateController_templateslist");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String com_id = "";

		// 按照条件检索
		try {

			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			String factoryid = user.getDoc_factory().getId();
			// 分页查询
			if (pd.toString().contains("com_id"))
				com_id = pd.getString("com_id");
			String currentPage = "";
			if (pd.toString().contains("currentPage")) {
				currentPage = pd.getString("currentPage");

			} else {
				currentPage = "1";

			}
			List<Doc_Company> listcoms = companyService.findAllCompany();
			PageBean templateslist = wxTemplateService.findpageX_Msg_Type(currentPage, com_id);
			page.setPd(pd);
			mv.setViewName("system/wxtemplate/template_list");
			mv.addObject("varList", templateslist.getRecordList());
			pd.put("templateslist", templateslist);
			mv.addObject("listcoms", listcoms);
			pd.put("com_id", com_id);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() throws Exception {
		logBefore(logger, "WxTemplateController_goAdd");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String currentPage = pd.getString("currentPage");
			pd.put("currentPage", currentPage);
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			pd.put("USERNAME", user.getUsername());
			List<Doc_Company> comlist = companyService.findAllCompany();
			mv.setViewName("system/wxtemplate/template_add");
			mv.addObject("msg", "saveTemplate");
			mv.addObject("pd", pd);
			mv.addObject("comlist", comlist);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return mv;
	}

	/**
	 * 新增
	 */
	@RequestMapping(value ="/saveTemplate")
	@ResponseBody
	public ModelAndView save(HttpServletRequest request) throws Exception {
		logBefore(logger, "WxTemplateController_save");

		ModelAndView mv = this.getModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			X_Msg_Type xmt = new X_Msg_Type();
			Doc_Company doc_Company = companyService.findCompanyByID(request.getParameter("com_id"));
			xmt.setId(this.get32UUID());
			xmt.setTemplateid(request.getParameter("templateid"));
			xmt.setTypebs(request.getParameter("types"));
			xmt.setDoc_company(doc_Company);
			xmt.setRemark(request.getParameter("remark"));
			wxTemplateService.saveX_Msg_Type(xmt);
			mv.addObject("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 去修改页面
	 */
	@RequestMapping(value = "/go_Edit")
	public ModelAndView goUpdate(HttpServletRequest request) throws Exception {

		logBefore(logger, "WxTemplateController_go_Edit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String id = pd.getString("com_id");
		try {
			xmt = wxTemplateService.findX_Msg_TypeById(id);
			doc_Company = companyService.findCompanyByID(xmt.getDoc_company().getId());
			List<Doc_Company> comlist = companyService.findAllCompany();
			pd.put("xmt", xmt);
			pd.put("doc_Company", doc_Company);
			mv.setViewName("system/wxtemplate/template_edit");
			mv.addObject("pd", pd);
			mv.addObject("msg", "edit");
			mv.addObject("comlist", comlist);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return mv;
	}

	/**
	 * 验证修改页面的类型是否可用
	 */
	@RequestMapping(value = "/verifyTypebs")
	public void verifyTypebs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jo = new JSONObject();
		String typebs1 = request.getParameter("typebs1");
		String message = "";
		xmt = wxTemplateService.findX_Msg_TypeByTypebsAndcom_id(request.getParameter("typebs"), doc_Company);

		if (xmt == null) {
			message = "success";
		} else {
			if (typebs1.equals(xmt.getTypebs())) {
				message = "success";
			} else {
				message = "error";
			}

		}
		
		jo.put("message", message);
		PrintWriter out;
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		out = response.getWriter();
		out.write(jo.toString());
		out.flush();
		out.close();
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public ModelAndView update(HttpServletRequest request) throws Exception {
		logBefore(logger, "WxTemplateController_edit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			Doc_Factory factory = user.getDoc_factory();
			X_Msg_Type xmt1 = new X_Msg_Type();
			xmt1.setDoc_company(doc_Company);
			xmt1.setRemark(request.getParameter("remark"));
			xmt1.setTemplateid(request.getParameter("templateid"));
			xmt1.setTypebs(request.getParameter("typebs"));
			xmt1.setId(request.getParameter("tid"));
			wxTemplateService.updateX_Msg_Type(xmt1);
			mv.addObject("pd", pd);
			mv.addObject("msg", "success");
			mv.setViewName("save_result");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return mv;
	}

	/**
	 * 验证新增页面的类型是否可用
	 */
	@RequestMapping(value = "/verifyTypebs2")
	public void verifyTypebs2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jo = new JSONObject();
		String message = "";
		String com_id = request.getParameter("com_id");
		Doc_Company doc_Company = companyService.findCompanyByID(com_id);
		xmt = wxTemplateService.findX_Msg_TypeByTypebsAndcom_id(request.getParameter("typebs"), doc_Company);

		if (xmt == null) {
			message = "success";
		} else {
			message = "error";
		}
		jo.put("message", message);
		PrintWriter out;
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		out = response.getWriter();
		out.write(jo.toString());
		out.flush();
		out.close();
	}
	
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out,HttpServletRequest request){
		logBefore(logger, "WxTemplateController_delete");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String com_id = request.getParameter("com_id");
			String currentPage= request.getParameter("currentPage");
			X_Msg_Type xmt1=wxTemplateService.findX_Msg_TypeById(com_id);
			wxTemplateService.deleteX_Msg_Type(xmt1);
			out.write(currentPage);
			out.close();
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}

	}
	
	

	private X_Msg_Type xmt;

	private Doc_Company doc_Company;

	public Doc_Company getDoc_Company() {
		return doc_Company;
	}

	public void setDoc_Company(Doc_Company doc_Company) {
		this.doc_Company = doc_Company;
	}

	public X_Msg_Type getXmt() {
		return xmt;
	}

	public void setXmt(X_Msg_Type xmt) {
		this.xmt = xmt;
	}

}
