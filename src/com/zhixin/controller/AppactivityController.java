package com.zhixin.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.zhixin.model.App_activity;
import com.zhixin.model.App_picture;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Picture;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.AppUtil;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.DateUtil;
import com.zhixin.right_utils.DelAllFile;
import com.zhixin.right_utils.FileUpload;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.PathUtil;
import com.zhixin.service.AppactivityService;
import com.zhixin.tools.TimestampUtil;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value="appactivity")
public class AppactivityController extends BaseController{
	@Resource(name="appactivityService")
	private AppactivityService appactivityService;
	
	
	
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
			
			PageBean pageappactivity =appactivityService.findpageactivity(factoryid,currentPage);
			
			page.setPd(pd);
			mv.setViewName("system/appactivity/appactivity_list");
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
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "PicturesController_goAdd");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("system/appactivity/appactivity_add");
			mv.addObject("pd", pd);
			mv.addObject("msg", "save");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}						
		return mv;
	}
	
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public ModelAndView save(
			@RequestParam(required=false) String title,
			@RequestParam(required=false) String context,
			@RequestParam(required=false) MultipartFile file
			) throws Exception{
		logBefore(logger, "PicturesController_save");

		ModelAndView mv = this.getModelAndView();
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
				Subject currentUser = SecurityUtils.getSubject();
				Session session = currentUser.getSession();
				Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
				Doc_Factory factory =user.getDoc_factory();
				String  ffile = DateUtil.getDays(), fileName = "";
				if (null != file && !file.isEmpty()) {
					String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
					fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
				}else{
				}
				
				App_activity activity = new App_activity();
				activity.setContext(context);
				activity.setFactory(factory);
				activity.setPath(ffile + "/" + fileName);
				activity.setPicname(fileName);
				activity.setTitle(title);
				activity.setCreatedate(TimestampUtil.getnowtime());
				appactivityService.saveAppactivity(	activity);
				mv.addObject("msg","success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
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
	public void delete(PrintWriter out){
		logBefore(logger, "PicturesController_delete");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String appactivity_id =pd.getString("appactivity_id");
			String currentPage =pd.getString("currentPage");
			DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("path")); //删除图片
			appactivityService.delete_Tp(appactivity_id);
			out.write(currentPage);
			out.close();
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		
	}
	
	
	/**
	 * 获取appactivity
	 * @param msgtype
	 * @param response
	 */
	@RequestMapping(value="/getappactivity")
	public void getapppicture(HttpServletResponse response)throws Exception{
		logBefore(logger, "ToolController_gettemplate");
		PageData pd = new PageData();
		try {
			    pd = this.getPageData();
				List list1 = new ArrayList<>();
				String factory_id =pd.getString("factory_id");
				List<App_activity> list_app=appactivityService.findAppactivity_list(factory_id);
				for(App_activity app :list_app){
					Map map1 = new HashMap();
					map1.put("context", app.getContext());
					map1.put("createtime", TimestampUtil.timestamptoString(app.getCreatedate()));
					map1.put("path","http://192.168.0.133:8080/wxplatform/uploadFiles/uploadImgs/"+app.getPath());
					map1.put("title", app.getTitle());
					list1.add(map1);
					
				}
				
				
			    JSONArray arr = JSONArray.fromObject(list1);
			    
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
	 * 去修改页面
	 */ 
	@RequestMapping(value="/go_Edit")
	public ModelAndView goUpdate(HttpServletRequest request)throws Exception{
		
		logBefore(logger, "PicturesController_go_Edit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String id=pd.getString("appid");
		appactivity =appactivityService.findAppactivityById(id);
		pd.put("appactivity", appactivity);
		pd.put("aid", appactivity.getId());
		try {
			mv.setViewName("system/appactivity/appactivity_edit");
			mv.addObject("pd", pd);
			mv.addObject("msg", "edit");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}						
		return mv;
	}
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public ModelAndView update(HttpServletRequest request,
			@RequestParam(required=false) MultipartFile file   ) throws Exception{
		logBefore(logger, "PicturesController_edit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String id=pd.getString("aid");
		try{
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			Doc_Factory factory =user.getDoc_factory();
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != file && !file.isEmpty()) {
				 String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());	
				//执行上传
			}else{
			}
			
			String currentPage =pd.getString("currentPage");
			pd.put("currentPage", currentPage);
			String context =request.getParameter("context");
			String title =request.getParameter("title");
			if (fileName==null||fileName=="") {
				fileName=appactivity.getPicname();
				String filePath=appactivity.getPath();
				appactivity.setPath(filePath);
				appactivity.setPicname(fileName);
				appactivity.setContext(context);
				appactivity.setFactory(factory);
				appactivity.setTitle(title);
				appactivity.setCreatedate(TimestampUtil.getnowtime());
			}else{
				appactivity.setContext(context);
				appactivity.setFactory(factory);
				appactivity.setPath(ffile + "/" + fileName);
				appactivity.setPicname(fileName);
				appactivity.setTitle(title);
				appactivity.setCreatedate(TimestampUtil.getnowtime());
			}
			appactivityService.updateApp_activity(appactivity);
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
	
	
	private App_activity appactivity;



	public App_activity getAppactivity() {
		return appactivity;
	}


	public void setAppactivity(App_activity appactivity) {
		this.appactivity = appactivity;
	}
	
	
}
