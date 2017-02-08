package com.zhixin.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.zhixin.service.ApppictureService;
import com.zhixin.service.PicturesService;
import com.zhixin.tools.TimestampUtil;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value="apppicture")
public class ApppictureController extends BaseController{
	
	
	@Resource(name="apppictureService")
	private ApppictureService apppictureService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "PicturesController_list");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
			String currentPage="";
			if(pd.toString().contains("currentPage"))
				currentPage =pd.getString("currentPage");
			else
				currentPage ="1";
			PageBean pagepicture    =apppictureService.findAppPictures(currentPage,user.getDoc_factory().getId());
			page.setPd(pd);
			mv.setViewName("system/apppicture/apppicture_list");
			mv.addObject("varList", pagepicture.getRecordList());
			pd.put("pagepicture", pagepicture);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			e.printStackTrace();
		}finally {
			logAfter(logger);
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
			mv.setViewName("system/apppicture/picture_add");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}						
		return mv;
	}
	
	
	/**
	 * 新增保存
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(
			@RequestParam(required=false) MultipartFile file
			) throws Exception{
		logBefore(logger, "PicturesController_save");
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
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
				
				App_picture app_picture = new App_picture();
				app_picture.setFactory(factory);
				app_picture.setPath(ffile + "/" + fileName);
				app_picture.setPicname(fileName);
				app_picture.setCreatedate(TimestampUtil.getnowtime());
				
				apppictureService.savePicture(app_picture);
				map.put("result", "ok");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
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
			String picture_id =pd.getString("picture_id");
			String currentPage =pd.getString("currentPage");
			DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("path")); //删除图片
			apppictureService.delete_Tp(picture_id);
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
	 * 获取app图片
	 * @param msgtype
	 * @param response
	 */
	@RequestMapping(value="/getapppicture")
	public void getapppicture(HttpServletResponse response)throws Exception{
		logBefore(logger, "ToolController_gettemplate");
		try {
			
				List list1 = new ArrayList<>();
				List<App_picture> list_app=apppictureService.findAppicture_list();
				for(App_picture app: list_app){
					list1.add("http://192.168.0.133:8080/wxplatform/uploadFiles/uploadImgs/"+app.getPath());
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

}
