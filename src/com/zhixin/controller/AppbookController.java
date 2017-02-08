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
import org.springframework.web.servlet.ModelAndView;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Page;
import com.zhixin.model.App_book;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.AppbookService;
import com.zhixin.service.FactoryService;

import net.sf.json.JSONArray;
import weixin.popular.api.MessageAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.support.TokenManager;
@Controller
@RequestMapping(value="appbook")
public class AppbookController  extends BaseController{

	@Resource(name="appbookService")
	private AppbookService appbookService;
	
	@Resource(name="factoryService")
	private FactoryService factoryService;
	
	/**
	 * 显示用户列表(用户组)
	 */
	@RequestMapping(value="/list")
	public ModelAndView listappactivitys(Page page)throws Exception{
		logBefore(logger, "WxBindCustomerController_listusers");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//按照条件检索
		try{
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			String factoryid =user.getDoc_factory().getId();
			//分页查询
			String currentPage="";
			if(pd.toString().contains("currentPage"))
				 currentPage =pd.getString("currentPage");
			else
				currentPage ="1";
			
			PageBean pageappactivity =appbookService.findpageappbook(factoryid,currentPage);
			
			page.setPd(pd);
			mv.setViewName("system/appbook/appbook_list");
			mv.addObject("varList", pageappactivity.getRecordList());
			pd.put("pagepicture", pageappactivity);
			mv.addObject("pd", pd);
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	
	/**
	 * 预定
	 * @param msgtype
	 * @param response
	 */
	@RequestMapping(value="/book")
	public void book(HttpServletResponse response)throws Exception{
		logBefore(logger, "ToolController_gettemplate");
		try {
				ModelAndView mv = this.getModelAndView();
				PageData pd = new PageData();
				pd = this.getPageData();
				/*String factory_id =pd.getString("factory_id");
				String datetime =pd.getString("datetime");
				String bz =pd.getString("bz");
				String iphone =pd.getString("iphone");
				String num =pd.getString("num");
				Doc_Factory factory =factoryService.findFactoryById(factory_id);
				App_book appbook = new App_book();
				appbook.setBz(bz);
				appbook.setNum(Integer.parseInt(num));
				appbook.setDatetime(datetime);
				appbook.setPhone(iphone);
				appbook.setFactory(factory);
				appbookService.saveAppbook(	appbook);*/
				List list1 = new ArrayList<>();
			    JSONArray arr = JSONArray.fromObject(list1);
				PrintWriter out;
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				String json = arr.toString();
				out.write(json);
				out.write("hello big2 !");
				out.flush();
				out.close();
		} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
	}
	
}
