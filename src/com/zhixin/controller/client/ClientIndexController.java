package com.zhixin.controller.client;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.zhixin.base.BaseController;
import com.zhixin.entity.Json_AppActivity;
import com.zhixin.entity.Json_AppPicture;
import com.zhixin.entity.Json_Apppack;
import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Factory;
import com.zhixin.entity.Json_Sys_picture;
import com.zhixin.model.App_activity;
import com.zhixin.model.App_pack;
import com.zhixin.model.App_picture;
import com.zhixin.model.PageBean;
import com.zhixin.model.ShopLink_User_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_User;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.AppactivityService;
import com.zhixin.service.ApppackService;
import com.zhixin.service.ApppictureService;
import com.zhixin.service.FactoryService;
import com.zhixin.service.shop.ShopUserService;
import com.zhixin.tools.TokenProccessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value="/clientIndex")
public class ClientIndexController extends BaseController{

	
	@Resource(name="appactivityService")
	private AppactivityService appactivityService;
	
	@Resource(name="apppictureService")
	private  ApppictureService apppictureService;
	
	@Resource(name="apppackService")
	private ApppackService apppackService;
	
	@Resource(name="factoryService")
	private FactoryService factoryService;
	
	
	@Resource(name="shopuserService")
	private ShopUserService shopuserService;
	/**

	 * app主页 

	 * 1.查询 志信科技的app_picture

	 * 2.查询app_activity

	 */
	@ResponseBody
	@RequestMapping("index")
	public void index( HttpServletResponse response,HttpServletRequest request) throws Exception {
		response.setHeader("P3P","CP=CAO PSA OUR");
		String factoryid=request.getParameter("factoryid");
		List<Json_AppActivity> list_activity =appactivityService.findAppActivityByFactoryid(factoryid);
		JSONArray jsonactivitys = JSONArray.fromObject(list_activity);
		 JSONObject jo = new JSONObject();
		 jo.put("jsonactivitys", jsonactivitys);
		 PrintWriter out;
		 response.setContentType("application/json;charset=utf-8");
		 response.setCharacterEncoding("utf-8");
		 out = response.getWriter();
		 out.write(jo.toString());
		 out.flush();
		 out.close();
		
		
	}
	
	
	/**
	 * app主页 
	 * 
	 * 1.查询志信科技app_pack
	 */
	@ResponseBody
	@RequestMapping("news_list")
	public void news_list(HttpServletResponse response,String currentPage,
			HttpServletRequest request	) throws Exception {
		response.setHeader("P3P","CP=CAO PSA OUR");
		currentPage=request.getParameter("currentPage");
		String factoryid=request.getParameter("factoryid");
		if (currentPage==""||currentPage==null) {
			currentPage="1";
		}
		int current=Integer.parseInt(currentPage);
		if (current<=0) {
			currentPage="1";
		}
		List<Json_Apppack> list_pack =apppackService.findAppPackByFactoryid(factoryid,currentPage);
		JSONArray jsonappacks = JSONArray.fromObject(list_pack);
		JSONObject jo = new JSONObject();
		jo.put("jsonpack", jsonappacks);
		jo.put("currentPage", currentPage);
		 PrintWriter out;
		 response.setContentType("application/json;charset=utf-8");
		 response.setCharacterEncoding("utf-8");
		 out = response.getWriter();
		 out.write(jo.toString());
		 out.flush();
		 out.close();
	}
	
	/**
	 * app主页 
	 * 
	 * 1.查询志信科技app_pack by id
	 */
	@ResponseBody
	@RequestMapping("news_ByID")
	public void newsById(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		response.setHeader("P3P","CP=CAO PSA OUR");
		String id=request.getParameter("id");
		Json_Apppack jsonpack=apppackService.findAppPackByid(id);
		JSONObject jo = new JSONObject();
		jo.put("jsonpack", jsonpack);
		 PrintWriter out;
		 response.setContentType("application/json;charset=utf-8");
		 response.setCharacterEncoding("utf-8");
		 out = response.getWriter();
		 out.write(jo.toString());
		 out.flush();
		 out.close();
	}
	
	/**
	 * app主页 
	 * 
	 * 1.查询志信科技app_pack
	 */
	@ResponseBody
	@RequestMapping("picture_list")
	public void factoryPicture(HttpServletResponse response,String currentPage,
			HttpServletRequest request) throws Exception {
		response.setHeader("P3P","CP=CAO PSA OUR");
		currentPage=request.getParameter("currentPage");
		if (currentPage==""||currentPage==null) {
			currentPage="1";
		}
		int current=Integer.parseInt(currentPage);
		if (current<=0) {
			currentPage="1";
		}
		List<Json_Sys_picture> list_picture=factoryService.findFacturePictureByFactoryId("402880e651332f2d0151332f2f570000",currentPage);
		JSONArray jsonapppictures = JSONArray.fromObject(list_picture);
		JSONObject jo = new JSONObject();
		jo.put("jsonapppicture",jsonapppictures );
		jo.put("currentPage", currentPage);
		 PrintWriter out;
		 response.setContentType("application/json;charset=utf-8");
		 response.setCharacterEncoding("utf-8");
		 out = response.getWriter();
		 out.write(jo.toString());
		 out.flush();
		 out.close();
	}
	
	/**
	 * app主页 
	 * 
	 * 1.查询志信科技app_pack
	 */
	@ResponseBody
	@RequestMapping("carousel")
	public void pricture_carousel(HttpServletResponse response,
			HttpServletRequest request	) throws Exception {
		response.setHeader("P3P","CP=CAO PSA OUR");
		List<Json_AppPicture> list_picture =apppictureService.findAppPicturesByFactoryid("402880e651332f2d0151332f2f570000");
		JSONArray jsonpictures = JSONArray.fromObject(list_picture);
		 JSONObject jo = new JSONObject();
		 jo.put("jsonpictures", jsonpictures);
		 PrintWriter out;
		 response.setContentType("application/json;charset=utf-8");
		 response.setCharacterEncoding("utf-8");
		 out = response.getWriter();
		 out.write(jo.toString());
		 
		 
		 out.flush();
		 out.close();
		
	}
	/**
	 * 新闻返回到新闻列表
	 * @param response
	 * @param currentPage
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("news_back")
	public void newsBack(HttpServletResponse response,String currentPage,
			HttpServletRequest request)throws Exception{
		
		response.setHeader("P3P","CP=CAO PSA OUR");
		currentPage=request.getParameter("currentPage");
		
		String factoryid=request.getParameter("factoryid");
		List<Json_Apppack> list_pack =apppackService.findAppPackNewsBack(currentPage,factoryid);
		JSONArray jsonappacks = JSONArray.fromObject(list_pack);
		JSONObject jo = new JSONObject();
		jo.put("jsonpack", jsonappacks);
		jo.put("currentPage", currentPage);
		 PrintWriter out;
		 response.setContentType("application/json;charset=utf-8");
		 response.setCharacterEncoding("utf-8");
		 out = response.getWriter();
		 out.write(jo.toString());
		 out.flush();
		 out.close();
	}
	
	
	
	
	/**

	 * 个人中心页 

	 */
	@ResponseBody
	@RequestMapping("touser")
	public void touser( HttpServletResponse response,HttpServletRequest request) throws Exception {
		JSONObject jo = new JSONObject();
		jo.put("msg","success" );
		PageData pd = new PageData();
		pd = this.getPageData();
		response.setHeader("P3P","CP=CAO PSA OUR");
		try{
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			String sessionId= pd.getString("sessionId");
			Shop_User shopuser = (Shop_User) session.getAttribute(sessionId);
			if(shopuser==null&&sessionId!=null){
				shopuser =shopuserService.getShopUserByid(sessionId);
			}
			if(shopuser !=null){
				Shop_User shopuserr = (Shop_User) session.getAttribute(sessionId);
				if(null == shopuserr){
					session.setAttribute(sessionId, shopuser);
				}else{
					shopuser =shopuserr;
				}
			}else{
				jo.put("msg","error" );
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 PrintWriter out;
			 response.setContentType("application/json;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			 out.write(jo.toString());
			 
			 
			 out.flush();
			 out.close();
		}
		
		
		
		
	}
}
