package com.zhixin.controller;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.zhixin.service.PicturesService;
import com.zhixin.tools.TimestampUtil;




@Controller
@RequestMapping(value="/pictures")
public class PicturesController  extends BaseController{

	
	
	
	@Resource(name="picturesService")
	private PicturesService picturesService;
	
	
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
			String query_title = pd.getString("query_title");
			if(null == query_title )
				query_title="";
			query_title = query_title.trim();
			PageBean pagepicture    =picturesService.findPictures(query_title,currentPage,user.getDoc_factory().getId());
			
				
			query_title = pd.getString("query_title");
				
			if(null != query_title && !"".equals(query_title)){
					query_title = query_title.trim();
					pd.put("query_title", query_title);
			}
				
			page.setPd(pd);
			mv.setViewName("system/pictures/pictures_list");
			mv.addObject("varList", pagepicture.getRecordList());
			pd.put("pagepicture", pagepicture);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
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
			mv.setViewName("system/pictures/pictures_add");
			mv.addObject("pd", pd);
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
				
				Sys_Picture sys_picture = new Sys_Picture();
				sys_picture.setDoc_factory(factory);
				sys_picture.setName(fileName);
				sys_picture.setPath(ffile + "/" + fileName);
				sys_picture.setTitle("图片");
				sys_picture.setCreatedate(TimestampUtil.getnowtime());
				picturesService.savePicture(sys_picture);
				map.put("result", "ok");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "PicturesController_goEdit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String picture_id=pd.getString("PICTURES_ID");
			
			Sys_Picture picture  = picturesService.findPictureById(picture_id);	//根据ID读取
			pd.put("picture_id", picture.getId());
			pd.put("name", picture.getName());
			pd.put("path", picture.getPath());
			pd.put("title", picture.getTitle());
			pd.put("bz", picture.getBz());
			pd.put("flag", picture.getFlag());	
				
			mv.setViewName("system/pictures/pictures_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}					
		return mv;
	}	
	
	
		//删除图片
		@RequestMapping(value="/deltp")
		public void deltp(PrintWriter out) {
			logBefore(logger, "PicturesController_deltp");
			try{
				PageData pd = new PageData();
				pd = this.getPageData();
				String path = pd.getString("path");	//图片路径
				String picture_id=pd.getString("picture_id");
				DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("path")); 	//删除图片
				if(path != null){
					picturesService.delete_Tp(picture_id);																//删除数据中图片数据
				}	
				out.write("success");
				out.close();
			}catch(Exception e){
				logger.error(e.toString(), e);
			}finally {
				logAfter(logger);
			}
		}
		
	
		/**
		 * 修改
		 */
		@RequestMapping(value="/edit")
		public ModelAndView edit(
				HttpServletRequest request,
				@RequestParam(value="tp",required=false) MultipartFile file,
				@RequestParam(value="tpz",required=false) String tpz,
				@RequestParam(value="picture_id",required=false) String picture_id,
				@RequestParam(value="title",required=false) String title,
				@RequestParam(value="bz",required=false) String bz
				) throws Exception{
			logBefore(logger, "PicturesController_edit");
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			try{
				Sys_Picture old_picture = picturesService.findPictureById(picture_id);	
				old_picture.setTitle(title);
				old_picture.setBz(bz);
				if(null == tpz){tpz = "";}
				String  ffile = DateUtil.getDays(), fileName = "";
				if (null != file && !file.isEmpty()) {
					String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
					fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
					pd.put("PATH", ffile + "/" + fileName);				//路径
					pd.put("NAME", fileName);
					old_picture.setPath(ffile + "/" + fileName);
					old_picture.setName(fileName);
				}
				picturesService.update_Picture(old_picture);				//执行修改数据库
				mv.addObject("msg","success");
				mv.setViewName("save_result");
			}catch(Exception e){
				logger.error(e.toString(), e);
			}finally {
				logAfter(logger);
			}
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
				String picture_id =pd.getString("picture_id");
				String currentPage =pd.getString("currentPage");
				DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("path")); //删除图片
				picturesService.delete_pictureByID(picture_id);
				out.write(currentPage);
				out.close();
			} catch(Exception e){
				logger.error(e.toString(), e);
			}finally {
				logAfter(logger);
			}
			
		}
		
		/**
		 * 修改是否为logo
		 */
		@RequestMapping(value="/editflag")
		public ModelAndView editflag()throws Exception{
			logBefore(logger, "PicturesController_editflag");
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				String picture_id = pd.getString("picture_id");
				String flaglog =pd.getString("flaglog");
				Sys_Picture picture =	picturesService.findPictureById(picture_id);
				if ("1".equals(flaglog)){
					picture.setFlag(0);
				}else
					picture.setFlag(1);
				picturesService.update_PictureLog(picture);
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
