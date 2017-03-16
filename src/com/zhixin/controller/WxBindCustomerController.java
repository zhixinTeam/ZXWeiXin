package com.zhixin.controller;


import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.zhixin.base.BaseController;
import com.zhixin.entity.Page;
import com.zhixin.interceptor.Token;
import com.zhixin.model.App_activity;
import com.zhixin.model.App_pack;
import com.zhixin.model.App_picture;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Picture;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Eventmsg;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.PathUtil;
import com.zhixin.service.ApppackService;
import com.zhixin.service.ApppictureService;
import com.zhixin.service.CompanyService;
import com.zhixin.service.FactoryService;
import com.zhixin.service.PicturesService;
import com.zhixin.service.SendMsgService;
import com.zhixin.service.WxBindCustomerService;
import com.zhixin.tools.TimestampUtil;
import com.zhixin.tools.TokenProccessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Token(remove=true)
@Controller
@RequestMapping(value="/wxuser")
public class WxBindCustomerController  extends BaseController{
	
	@Resource(name="wxbindcustomerService")
	private  WxBindCustomerService wxbindcustomerService;
	
	@Resource(name="factoryService")
	private FactoryService factoryService;
	
	
	@Resource(name="picturesService")
	private PicturesService picturesService;
	
	@Resource(name="sendmsgservice")
	private SendMsgService	sendmsgservice;
	
	@Resource(name="apppictureService")
	private ApppictureService apppictureService;
	
	@Resource(name="apppackService")
	private ApppackService apppackService;
	
	@Resource(name="companyService")
	private CompanyService companyService;
	/**
	 * 去修改绑定页面
	 */
	@RequestMapping(value="/goEditU")
	public ModelAndView goEditU(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		logBefore(logger, "WxBindCustomerController_goEditU");
		try {
			mv.setViewName("system/appuser/appuser_edit");
			mv.addObject("msg", "editU");
			mv.addObject("pd", pd);
			mv.addObject("roleList", new ArrayList());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	
	
	/**
	 * 修改审核状态
	 */
	@RequestMapping(value="/editU")
	public ModelAndView editU(PrintWriter out) throws Exception{
		logBefore(logger, "WxBindCustomerController_editU");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String status =pd.getString("STATUS");
			String wxbin_id=pd.getString("USER_ID");
			Wx_BindCustomer wx_bind =wxbindcustomerService.findwxbindcustomerByID(wxbin_id);
			if("1".equals(status))
				wx_bind.setStatus(1);
			else
				wx_bind.setStatus(0);
			wxbindcustomerService.update_wxbindUserStatus(wx_bind);
			mv.addObject("msg","success");
			mv.setViewName("save_result");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 注册
	 * @param restar
	 * @param response
	 */
	@RequestMapping(value="/register")
	public void register(HttpServletResponse response,HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try{
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String pd_wx_token=pd.getString("wx_token");
			String wx_token =(String)session.getAttribute("wx_token");
			String flagID =pd.getString("id");
			String factory_IDs =pd.getString("factory_IDs");
			String originalID =pd.getString("originalID");
			String phone  =pd.getString("phone");
			String openid =pd.getString("openid");
			String namepinyin =pd.getString("username");
			String [] arrayids=factory_IDs.split(",");
			String factory_ID ="";
			List <Doc_Factory> listfactorys= factoryService.findFactorysByIDS(arrayids);
			Doc_Company company =companyService.findCompanyByOriginalID(originalID);
			Set setfac=new HashSet<Doc_Factory>(listfactorys);
			String pwd =phone.substring(phone.length()-6, phone.length());
			//if(pd_wx_token.equals(wx_token)){
				if("".equals(flagID)){
					Wx_BindCustomer wx_bind = new Wx_BindCustomer();
					wx_bind.setBinddate(TimestampUtil.getnowtime());
					wx_bind.setStatus(0);
					//wx_bind.setBindcustomer_factorys(bindcustomer_factorys);
					//wx_bind.setFactory(factory);
					//TODO 关联对象修改
					//wx_bind.setFactorys(setfac);
					wx_bind.setNamepinyin(namepinyin);
					wx_bind.setOpenid(openid);
					wx_bind.setPassword(new SimpleHash("SHA-1", namepinyin+company.getId(), pwd).toString());
					wx_bind.setPc_password(new SimpleHash("SHA-1", namepinyin, pwd).toString());
					wx_bind.setPhone(phone);
					//wx_bind.setWxUserName(wxusername);
					if(pd_wx_token.equals(wx_token)){
						session.removeAttribute("wx_token");
						wxbindcustomerService.save_wxbindUser(wx_bind,listfactorys);
					}
				}
				else{
					Wx_BindCustomer new_wx_bind =wxbindcustomerService.findwxbindcustomerByID(flagID);
					//已经绑定的，不从新关联;
					//TODO 关联对象修改
					new_wx_bind.setNamepinyin(namepinyin);
					new_wx_bind.setOpenid(openid);
					new_wx_bind.setPhone(phone);
					new_wx_bind.setPassword(new SimpleHash("SHA-1", namepinyin+company.getId(), pwd).toString());
					if(pd_wx_token.equals(wx_token)){
						session.removeAttribute( "wx_token");
						wxbindcustomerService.update_wxbindUser(new_wx_bind,listfactorys);
					}
				}
				
			//}
			List list1 = new ArrayList<>();
		    JSONArray arr = JSONArray.fromObject(list1);
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String json = arr.toString();
			out.write(json);
			out.flush();
			out.close();
			
			
			
			
			
			
			
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
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
			if(wxbindcustomerService.findByUSername(newusername) != null){
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
	 * 保存用户
	 */
	@RequestMapping(value="/saveU")
	public ModelAndView saveU(PrintWriter out) throws Exception{
		logBefore(logger, "WxBindCustomerController_saveU");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Sys_User newuser = new Sys_User();
			mv.setViewName("tinybar/wxadmin/index");
			mv.addObject("binduserlist", new ArrayList());
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	
	/**
	 * 到达手机系统主页
	 */
	@RequestMapping(value="/tophoneindex")
	public ModelAndView toEdit( String factory_ID )throws Exception{
		logBefore(logger, "WxBindCustomerController_tophoneindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String open_id =pd.getString("fromUserName");
			String toUserName = pd.getString("toUserName");
			//String open_id ="oKeyUxDnWcL8foq_a4ZL9UZ5bLU8";
			//String toUserName="gh_d6c13daeefce";
			//Map map_picture = new HashMap();
			Map map_picture=apppictureService.findApppicturesByOpenid(open_id);
			//List<Sys_Picture> listpictures =picturesService.findPicturesByOpenid(open_id);
			//如果不是绑定用户，查询集团下工厂的宣传图
			/*if(listapppictures.size()==0){
				listapppictures =apppictureService.findApppicturesByOrginalID(toUserName);
			}*/
			List<App_picture> list_app_pictures =new ArrayList();
			List<App_activity> list_app_activitys = new ArrayList();
			list_app_pictures = (List<App_picture>) map_picture.get("app_pictures");
			if(list_app_pictures.size()==0){
				map_picture = apppictureService.findApppicturesByOrginalID(toUserName);
				list_app_pictures = (List<App_picture>) map_picture.get("app_pictures");
					}
			
			list_app_activitys = (List<App_activity>) map_picture.get("list_app_activitys");
			
			
			pd.put("fromUserName", open_id);
			pd.put("toUserName", toUserName);
			mv.addObject("pd", pd);
			mv.setViewName("weixin/nologin/index");
			mv.addObject("list_app_pictures",list_app_pictures);
			mv.addObject("list_app_activitys",list_app_activitys);
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 到达运营页
	 */
	@RequestMapping(value="/toprocess")
	public ModelAndView toprocess( String factory_ID )throws Exception{
		logBefore(logger, "WxBindCustomerController_tophoneindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String open_id =pd.getString("fromUserName");
			String toUserName = pd.getString("toUserName");
			//String open_id ="oKeyUxEgSYnl1j1d0bt_cwJsIdMo";
			//String toUserName="gh_d6c13daeefce";
			List<App_picture> list_app_pictures =new ArrayList();
			List<App_pack> list_app_packs = new ArrayList();
			Map map_pack=apppictureService.findApppacksByOpenid(open_id);
			//如果不是绑定用户，查询集团下工厂的宣传图
			list_app_pictures = (List<App_picture>) map_pack.get("app_pictures");
			if(list_app_pictures.size()==0){
				map_pack =apppictureService.findApppacksByOrginalID(toUserName);
				list_app_pictures = (List<App_picture>) map_pack.get("app_pictures");
			}
			
			list_app_packs = (List<App_pack>) map_pack.get("list_app_packs");
			pd.put("fromUserName", open_id);
			pd.put("toUserName", toUserName);
			mv.addObject("pd", pd);
			mv.setViewName("weixin/nologin/process");
			mv.addObject("list_app_pictures",list_app_pictures);
			mv.addObject("list_app_packs",list_app_packs);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	

	
	/**
	 * 到达动态详情页
	 */
	@RequestMapping(value="/process_details")
	public ModelAndView process_details( String factory_ID )throws Exception{
		logBefore(logger, "WxBindCustomerController_tophoneindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String open_id =pd.getString("fromUserName");
			String toUserName = pd.getString("toUserName");
			String apppackid = pd.getString("apppackid");
			//String open_id ="oKeyUxEgSYnl1j1d0bt_cwJsIdMo";
			//String toUserName="gh_d6c13daeefce";
			Map map_pack=apppictureService.findApppacksByOpenid(open_id);
			List<App_picture> list_app_pictures =new ArrayList();
			List<App_pack> list_app_packs = new ArrayList();
			//如果不是绑定用户，查询集团下工厂的宣传图
			list_app_pictures = (List<App_picture>) map_pack.get("app_pictures");
			if(list_app_pictures.size()==0){
				map_pack =apppictureService.findApppacksByOrginalID(toUserName);
				list_app_pictures = (List<App_picture>) map_pack.get("app_pictures");
			}
			
			list_app_packs = (List<App_pack>) map_pack.get("list_app_packs");
			App_pack app_pack=apppackService.findAppackByid(apppackid);
			pd.put("fromUserName", open_id);
			pd.put("toUserName", toUserName);
			pd.put("apppackid", apppackid);
			mv.addObject("pd", pd);
			mv.setViewName("weixin/nologin/process_details");
			mv.addObject("list_app_pictures",list_app_pictures);
			mv.addObject("app_pack",app_pack);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 显示用户列表(用户组)
	 */
	@RequestMapping(value="/listusers")
	public ModelAndView listUsers(Page page)throws Exception{
		logBefore(logger, "WxBindCustomerController_listusers");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//按照条件检索
		try{
			String status ="";
			String namepinyin ="";
			if(pd.toString().contains("STATUS"))
				status = pd.getString("STATUS");
			if(pd.toString().contains("NAMEPY"))
				namepinyin = pd.getString("NAMEPY");
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
			PageBean pagecustomer =wxbindcustomerService.findpagewxbindcustomer(factoryid,currentPage,status,namepinyin);
			pd.put("pagecustomer", pagecustomer);
			mv.setViewName("system/appuser/appuser_list");
			mv.addObject("binduserlist", pagecustomer.getRecordList());
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 客户关注微信公众号注册
	 */
	@RequestMapping(value="/goAddU")
	public ModelAndView goAddU(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		logBefore(logger, "WxBindCustomerController_goAddU");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		JSONObject jo = new JSONObject();
		try{
			String fromUserName = pd.getString("fromUserName");
			String toUserName = pd.getString("toUserName");
			
			//String fromUserName="oKeyUxEgSYnl1j1d0bt_cwJsIdMo";
			//String toUserName="gh_d6c13daeefce";
			String openid = fromUserName;
			//防止重复提交
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			
			session.setAttribute("wx_token", wx_token);
			
			//查询出已经选中的工厂列表
			List<Doc_Factory> oldfactorys =     wxbindcustomerService.findFactoryByOpenID(openid);
			//获取绑定账号
			Wx_BindCustomer wx_bindcustomer = wxbindcustomerService.findwxbindcustomerByOpenID(openid);
			System.out.println(wx_bindcustomer.getNamepinyin()+"存在不存在");
			System.out.println(wx_bindcustomer.getNamepinyin()+"存在不存在");
			String originalID =toUserName;  //集团的原始id
			List<Doc_Factory>  factoryList=wxbindcustomerService.findFactoryByOriginalID(originalID);
			System.out.println(factoryList.size());
			if(wx_bindcustomer.getNamepinyin()!=null){
				request.setAttribute("message", "oldsuccess");
			}else{
				request.setAttribute("message", "success");
			}
			pd.put("phone", wx_bindcustomer.getPhone());
			pd.put("id", wx_bindcustomer.getId());
			pd.put("wx_token", wx_token);
			pd.put("username", wx_bindcustomer.getNamepinyin());
			pd.put("fromUserName", openid);
			pd.put("toUserName", toUserName);
			mv.setViewName("weixin/appuser/appuser_rest");
			mv.addObject("msg", "saveU");
			mv.addObject("pd", pd);
			mv.addObject("factoryList", factoryList);
			mv.addObject("oldfactoryList", oldfactorys);
			mv.addObject("wx_bindcustomer", wx_bindcustomer);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	
	
	
	
	
	/**
	 * 查看详情Message details msgDeatils
	 */
	@RequestMapping(value="/msgDeatils")
	public ModelAndView msgDeatils(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		logBefore(logger, "WxBindCustomerController_msgDeatils");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String msg_id =pd.getString("msg_id");
			X_Eventmsg x_eventmsg =sendmsgservice.findmsgbyID(msg_id);
			String content =x_eventmsg.getContent();
			JSONObject jb = JSONObject.fromObject(content);
			String type = pd.getString("type");
			String html_str="";
			//开单
			if("1".equals(type)){
				html_str="<table class=\"table\"><tr class=\"active\">"+
					"<td>客户名字：</td>"+
					"<td>"+jb.get("CusName").toString()+"</td>"+
				"</tr>"+
				"<tr class=\"success\">"+
					"<td>订单编号：</td>"+
					"<td>"+jb.get("billid").toString()+"</td>"+
				"</tr>"+
				"<tr class=\"warning\">"+
					"<td>物料编号：</td>"+
					"<td>"+jb.get("stockno").toString()+"</td>"+
				"</tr>"+
				"<tr class=\"danger\">"+
					"<td>物料名称：</td>"+
					"<td>"+jb.get("stockname").toString()+"</td>"+
				"</tr>"+
				"<tr class=\"active\">"+
					"<td>客户资金：</td>"+
					"<td>"+jb.get("cusaccount").toString()+"</td>"+
				"</tr>"+
			"</table>";
			//出厂
			}else if("2".equals(type)){
				html_str="<table class=\"table\"><tr class=\"active\">"+
						"<td>客户名字：</td>"+
						"<td>"+jb.get("CusName").toString()+"</td>"+
					"</tr>"+
					"<tr class=\"success\">"+
						"<td>订单编号：</td>"+
						"<td>"+jb.get("billid").toString()+"</td>"+
					"</tr>"+
					"<tr class=\"warning\">"+
						"<td>车牌号码：</td>"+
						"<td>"+jb.get("truck").toString()+"</td>"+
					"</tr>"+
					"<tr class=\"danger\">"+
						"<td>物料名称：</td>"+
						"<td>"+jb.get("stockname").toString()+"</td>"+
					"</tr>"+
					"<tr class=\"active\">"+
						"<td>运输商务：</td>"+
						"<td>"+jb.get("transname").toString()+"</td>"+
					"</tr>"+
				"</table>";
			//报表
			}else if("3".equals(type)){
				JSONArray ja = jb.getJSONArray("content");
				html_str+="时间:"+jb.get("start_date").toString()+"-"+jb.get("end_date").toString()+"<br><table class=\"table\"><thead><tr><th>物料编号</th><th>物料名称</th><th>车次</th><th>总量</th></tr></thead>";
				String class_tab="active";
				for (int i = 0; i < ja.size(); i++){
					if(i % 2 != 0)
						class_tab="success";
					else
						class_tab="active";
					html_str +="<tr class="+class_tab+">"+
					"<td>"+ja.getJSONObject(i).getString("stockno")+"</td>"+
					"<td>"+ja.getJSONObject(i).getString("stockname")+"</td>"+
					"<td>"+ja.getJSONObject(i).getString("count")+"</td>"+
					"<td>"+ja.getJSONObject(i).getString("qty")+"</td>"+
					"</tr>";
				}
				html_str+="</table>";
			}else if("4".equals(type)){
				html_str="<table class=\"table\"><tr class=\"active\">"+
						"<td>客户名字：</td>"+
						"<td>"+jb.get("CusName").toString()+"</td>"+
					"</tr>"+
					"<tr class=\"success\">"+
						"<td>订单编号：</td>"+
						"<td>"+jb.get("stockno").toString()+"</td>"+
					"</tr>"+
					"<tr class=\"warning\">"+
						"<td>物料名称：</td>"+
						"<td>"+jb.get("stockname").toString()+"</td>"+
					"</tr>"+
				"</table>";
			//提货通知单
			}else if("5".equals(type)){
				String thorderno =jb.get("thorderno").toString()+".png";
				String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + thorderno;  //存放路径
				File file = new File(filePath);
				if(file.exists()){
					filePath ="http://www.hnzxtech.cn/wxplatform/uploadFiles/twoDimensionCode/"+thorderno;
				}else
					filePath ="http://www.hnzxtech.cn/wxplatform/uploadFiles/twoDimensionCode/"+"404.jpg";
				
				html_str="<div align='center'><img src='"+filePath+"' alt='提货二维码' /></div>";
			//报表
			}else{
				html_str+="无数据！";
			}
			pd.put("html_str", html_str);
			mv.setViewName("weixin/appuser/msg_deatils");
			mv.addObject("msg", "list_msg");
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;

	
	}
	/**
	 * 修改查看报表的权限
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editStatus")
	public void editStatus(HttpServletResponse response, HttpServletRequest request)throws Exception{
//		logBefore(logger, "WxBindCustomerController_listusers");
//		ModelAndView mv = this.getModelAndView();
		JSONObject jo = new JSONObject();
		String open_id=request.getParameter("open_id");
		Wx_BindCustomer wx_BindCustomer=wxbindcustomerService.findwxbindcustomerByOpenID(open_id);
		String message ="";
		if (wx_BindCustomer.getStatus()==0) {
			wx_BindCustomer.setStatus(1);
			 message ="已开启查看报表权限";
		}else{
			wx_BindCustomer.setStatus(0);
			 message ="已关闭查看报表权限";
		}
		wxbindcustomerService.update_wxbindUserStatus(wx_BindCustomer);
		jo.put("message", message);
//		mv.setViewName("system/appuser/appuser_list");
//		mv.addObject("message", message);
		PrintWriter out;
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		out = response.getWriter();
		out.write(jo.toString());
		out.flush();
		out.close();
	}
	
}
