package com.zhixin.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.tempuri.SrvWebchat;
import org.tempuri.SrvWebchatAction;
import org.tempuri.SrvWebchatActionResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Json_Car;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Sys_Picture;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.Wx_BindUser;
import com.zhixin.right_utils.AppUtil;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.DateUtil;
import com.zhixin.right_utils.FileUpload;
import com.zhixin.right_utils.FileUtil;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.PathUtil;
import com.zhixin.right_utils.TwoDimensionCode;
import com.zhixin.service.CompanyService;
import com.zhixin.service.FactoryService;
import com.zhixin.service.UserService;
import com.zhixin.service.WxBindCustomerService;
import com.zhixin.tools.DeleteFileUtil;
import com.zhixin.tools.ImgListForm;
import com.zhixin.tools.ImgMsg;
import com.zhixin.tools.ParseXml;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weixin.popular.api.MediaAPI;
import weixin.popular.api.MessageAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.media.Media;
import weixin.popular.bean.media.MediaType;
import weixin.popular.bean.message.Article;
import weixin.popular.bean.message.MessageSendResult;
import weixin.popular.support.TokenManager;

/** 
 * 类名称：ToolController
 * 创建人： 
 * 创建时间：
 * @version
 */
@Controller
@RequestMapping(value="/tool")
public class ToolController extends BaseController {
	
	
	@Resource(name="wxbindcustomerService")
	private  WxBindCustomerService wxbindcustomerService;
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	
	@Resource(name="factoryService")
	private FactoryService factoryService;
	
	@Resource(name="userService")
	private UserService userService;
	
	
	
	/**
	 * 二维码页面
	 */
	@RequestMapping(value="/goTwoDimensionCode")
	public ModelAndView goTwoDimensionCode() throws Exception{
		logBefore(logger, "SysUserController_goTwoDimensionCode");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/twoDimensionCode");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 *	生成二维码
	 * @param args
	 * @throws Exception
	 */
	@RequestMapping(value="/createTwoDimensionCode")
	@ResponseBody
	public Object createTwoDimensionCode(){
		logBefore(logger, "SysUserController_createTwoDimensionCode");
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try{
			String orderNO = pd.getString("orderNO");
			String errInfo = "success", encoderImgId = this.get32UUID()+".png"; //encoderImgId此处二维码的图片名
			if(null == orderNO ||"".equals(orderNO)){
				errInfo = "error";
			}else{
				encoderImgId =orderNO+".png";
				try {
					String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + encoderImgId;  //存放路径
					//如果已经生成二维码，不在生成
					File file = new File(filePath);
					if(file.exists()){
						
					}
					else
						 com.zhixin.tools.QRCodeUtil.encode(orderNO,"",filePath,true);
						//TwoDimensionCode.encoderQRCode(orderNO, filePath, "png");					//执行生成二维码
					
				} catch (Exception e) {
					errInfo = "error";
				}
			}
			map.put("result", errInfo);						//返回结果
			map.put("encoderImgId", encoderImgId);			//二维码图片名
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 *	解析二维码
	 * @param args
	 * @throws Exception
	 */
	@RequestMapping(value="/readTwoDimensionCode")
	@ResponseBody
	public Object readTwoDimensionCode(){
		logBefore(logger, "SysUserController_readTwoDimensionCode");
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String errInfo = "success",readContent="";
			String imgId = pd.getString("imgId");//内容
			if(null == imgId){
				errInfo = "error";
			}else{
				try {
					String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + imgId;  //存放路径
					readContent = TwoDimensionCode.decoderQRCode(filePath);//执行读取二维码
				} catch (Exception e) {
					errInfo = "error";
				}
			}
			map.put("result", errInfo);						//返回结果
			map.put("readContent", readContent);			//读取的内容
		} catch(Exception e){
			logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	/**
	 * 发送消息页面
	 */
	@RequestMapping(value="/goSendMsg")
	public ModelAndView goSendEmail() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
		String factoryid = user.getDoc_factory().getId();
		Doc_Factory factory =factoryService.findFactoryById(factoryid);
		Doc_Company company = factory.getDoc_company();
		//只关注的用户
		List<Wx_BindUser> list_bindusers =  wxbindcustomerService.findwxUserByFactoryID(factoryid);
		//已经绑定的用户
		List<Wx_BindCustomer> list_bindcususers =wxbindcustomerService.findwxbindcusUserByFactoryID(factoryid);
		//获取公司员工
		Set<Sys_User> set_users =  wxbindcustomerService.findsysUserByFactoryID(factoryid);
		List<Wx_BindCustomer>  employees = new ArrayList<>();
		List<Wx_BindCustomer>  client_users = new ArrayList<>();
		for(Wx_BindCustomer  bindcustomer:list_bindcususers){
			boolean flag = true;
			for(Sys_User sys_user:set_users){
				if(bindcustomer.getPhone().equals(sys_user.getMobile())){
					employees.add(bindcustomer);
					flag = false;
					break;
				}/*else{
					client_users.add(bindcustomer);
					break;
				}*/
			}
			if(flag)
				client_users.add(bindcustomer);
		}
		mv.addObject("employees", employees);
		mv.addObject("client_users", client_users);
		mv.addObject("app_id", company.getAppid());
		mv.addObject("list_bindusers", list_bindusers);
		//wxbindcustomerService.findWxUserByFactoryID();
		mv.setViewName("system/tools/sendmsg");
		mv.addObject("pd", pd);
		return mv;
	}
	
	
	
	
	
	/**
	 * 获取消息类型显示不同的表单
	 * @param msgtype
	 * @param response
	 */
	@RequestMapping(value="/getmsgtype")
	public void getmsgtype(@RequestParam String msg_type,HttpServletResponse response)throws Exception{
		logBefore(logger, "ToolController_gettemplate");
		try {
			
				List list1 = new ArrayList<>();
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
	 * 发送text消息
	 * @param msgtype
	 * @param response
	 */
	@RequestMapping(value="/sendmsg")
	public void sendmsg(HttpServletResponse response)throws Exception{
		logBefore(logger, "ToolController_gettemplate");
		try {
				ModelAndView mv = this.getModelAndView();
				PageData pd = new PageData();
				pd = this.getPageData();
				String recive_openIDs = pd.getString("recive_openIDs");
				String content_text =pd.getString("content_text");
				String [] opinids=recive_openIDs.split(",");
				String wx_token =TokenManager.getToken(pd.getString("app_id"));
				String errmsg= "";
				String errcode ="";
				/*for(String opinid:opinids){
					String keyq ="{\"touser\":\""+opinid+"\",\"msgtype\": \"text\",\"text\": { \"content\": \""+content_text+"\"}}";
					BaseResult result=MessageAPI.messageCustomSend(wx_token, keyq);
					//MessageAPI.messageMassSend(access_token, messageJson)
					errcode =result.getErrcode();
					if("40001".equals(errcode)){
						String app_id =pd.getString("app_id");
						wx_token =companyService.find_tokenByappid(app_id);
						result=MessageAPI.messageCustomSend(wx_token, keyq);
						
					}

					errmsg =result.getErrmsg();
				}*/
				if(opinids.length>1){

					JSONObject jo1 = new JSONObject();
					 JSONObject jb = new JSONObject();
					 jb.put("content", content_text);
					 jo1.put("msgtype", "text");
					 jo1.put("text", jb);
					 /*List list1 = new ArrayList();
					 list1.add("o2hr-wnlVRxI7-qGpOqF7QiI0Jdo");
					 list1.add("o2hr-whKpeanWrMiWV-rwn7qzW4A");*/
					 //JSONArray jsonclient = JSONArray.fromObject(opinids);
					 jo1.put("touser", opinids);
					
					 MessageSendResult  result1=MessageAPI.messageMassSend(wx_token, jo1.toString());
					 if("40001".equals(result1.getErrcode())){
							String app_id =pd.getString("app_id");
							wx_token =companyService.find_tokenByappid(app_id);
							result1=MessageAPI.messageMassSend(wx_token, jo1.toString());
							
							
						}
					 errmsg =result1.getErrmsg();
					 errcode =result1.getErrcode();
				}else{
					String keyq ="{\"touser\":\""+opinids[0]+"\",\"msgtype\": \"text\",\"text\": { \"content\": \""+content_text+"\"}}";
					BaseResult result=MessageAPI.messageCustomSend(wx_token, keyq);
					//MessageAPI.messageMassSend(access_token, messageJson)
					errcode =result.getErrcode();
					if("40001".equals(errcode)){
						String app_id =pd.getString("app_id");
						wx_token =companyService.find_tokenByappid(app_id);
						result=MessageAPI.messageCustomSend(wx_token, keyq);
						
					}
					errcode =result.getErrcode();
					errmsg =result.getErrmsg();
				}
				
				
				
				
				JSONObject jo = new JSONObject();
				jo.put("errmsg",errmsg );
				jo.put("errcode",errcode );
				PrintWriter out;
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				out.write(jo.toString());
				out.flush();
				out.close();
		} catch (Exception e) {
				logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
	}
	
	
	
	
	/**
	 * 发送text消息
	 * @param msgtype
	 * @param response
	 */
	/*@RequestMapping(value="/sendmsg")
	public void sendmsg(HttpServletResponse response)throws Exception{
		logBefore(logger, "ToolController_gettemplate");
		try {
				ModelAndView mv = this.getModelAndView();
				PageData pd = new PageData();
				pd = this.getPageData();
				String recive_openIDs = pd.getString("recive_openIDs");
				String content_text =pd.getString("content_text");
				String [] opinids=recive_openIDs.split(",");
				String wx_token =TokenManager.getToken(pd.getString("app_id"));
				String errmsg= "";
				String errcode ="";
				for(String opinid:opinids){
					String keyq ="{\"touser\":\""+opinid+"\",\"msgtype\": \"text\",\"text\": { \"content\": \""+content_text+"\"}}";
					BaseResult result=MessageAPI.messageCustomSend(wx_token, keyq);
					//MessageAPI.messageMassSend(access_token, messageJson)
					errcode =result.getErrcode();
					if("40001".equals(errcode)){
						String app_id =pd.getString("app_id");
						wx_token =companyService.find_tokenByappid(app_id);
						result=MessageAPI.messageCustomSend(wx_token, keyq);
						
					}

					errmsg =result.getErrmsg();
				}
				
				
				
				
				JSONObject jo = new JSONObject();
				jo.put("errmsg",errmsg );
				PrintWriter out;
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				out.write(jo.toString());
				out.flush();
				out.close();
		} catch (Exception e) {
				logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
	}
	*/
	/**
	 * 图文消息上传临时素材
	 * @param msgtype
	 * @param response
	 */
	@RequestMapping(value="/uploadimgmsg")   
	public void uploadimgmsg(
			@RequestParam(value="recive_openIDs",required=false) String recive_openIDs,
			@RequestParam(value="msgtype",required=false) String msgtype,
			@RequestParam(value="app_id",required=false) String app_id,
			ImgListForm imglistform,
			HttpServletResponse response)throws Exception{
		logBefore(logger, "ToolController_gettemplate");
		try {
				ModelAndView mv = this.getModelAndView();
				PageData pd = new PageData();
				pd = this.getPageData();
				String wx_token =TokenManager.getToken(app_id);
				List<Article> articles = new ArrayList<>();
				
				for (ImgMsg imgmsg : imglistform.getImgMsgs()) {
					  String midia_id ="";
					  Article art = new Article();
					  MultipartFile file = imgmsg.getFile();
					  String  ffile = DateUtil.getDays(), fileName = "";
		         	  String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
		         	  fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
					  String file_path =filePath + "/" + fileName;
					  File new_file = new File(file_path);
		              Media media1 =MediaAPI.mediaUpload(wx_token, MediaType.image, new_file);
		              
		              if("40001".equals(media1.getErrcode())){
							wx_token =companyService.find_tokenByappid(pd.getString("app_id"));
							media1 =MediaAPI.mediaUpload(wx_token, MediaType.image, new_file);
							
						}
					  midia_id =media1.getMedia_id();
				      DeleteFileUtil.delete(file_path);      //执行删除
				      //articles 添加
				      art.setAuthor(imgmsg.getAuthor());
				      art.setContent(imgmsg.getContent_text());
				      art.setContent_source_url(imgmsg.getUrl_my());
				      art.setDigest(imgmsg.getDigest());
				      art.setShow_cover_pic("1");
				      art.setThumb_media_id(media1.getMedia_id());
				      art.setTitle(imgmsg.getTitle());
				      articles.add(art);
				    }
				
				Media media_view =MessageAPI.mediaUploadnews(wx_token,articles);
				//客服循环发送消息
				String [] opinids=recive_openIDs.split(",");
				String media_viewid =media_view.getMedia_id();
				String errmsg ="";
				String errcode="";
				BaseResult result =null;
				if(opinids.length==1){
					 String key ="{\"touser\":\""+opinids[0]+"\", \"msgtype\":\"mpnews\",\"mpnews\": {\"media_id\": \""+media_viewid+"\"}}";
					 result=MessageAPI.messageCustomSend(wx_token, key);
					 errmsg =result.getErrmsg();
					 errcode =result.getErrcode();
				}else{
					/*List<String> list_openid = new ArrayList<>();
					Map map1 = new HashMap();
					map1.put("media_id", media_viewid);
					Map map = new HashMap();
					for(String opinid:opinids){
						list_openid.add(opinid);
					}
					map.put("touser", list_openid);
					map.put("msgtype", "mpnews");
					map.put("mpnews", map1);
					String json_str =JsonUtil.toJSONString(map);
					 result=MessageAPI.messageMassSend(wx_token,json_str);*/
					 
					 JSONObject jo1 = new JSONObject();
					 JSONObject jb1 = new JSONObject();
					 jb1.put("media_id", media_viewid);
					 jo1.put("msgtype", "mpnews");
					 jo1.put("mpnews", jb1);
					 jo1.put("touser", opinids);
					 MessageSendResult  result1=MessageAPI.messageMassSend(wx_token, jo1.toString());
					 errmsg =result1.getErrmsg();
					 errcode =result1.getErrcode();
					/*for(String opinid:opinids){
						String key ="{\"touser\":\""+opinid+"\", \"msgtype\":\"mpnews\",\"mpnews\": {\"media_id\": \""+media_viewid+"\"}}";
						
						result=MessageAPI.messageCustomSend(wx_token, key);
					}*/
					
					
					
				}
				//客服发送图文消息
				/*for(String opinid:opinids){
					String key ="{\"touser\":\""+opinid+"\", \"msgtype\":\"mpnews\",\"mpnews\": {\"media_id\": \""+media_viewid+"\"}}";
					BaseResult result=MessageAPI.messageCustomSend(wx_token, key);
				}*/
				//群发
				
				
				JSONObject jo = new JSONObject();
				jo.put("errmsg",errmsg );
				jo.put("errcode", errcode);
				PrintWriter out;
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				out.write(jo.toString());
				out.flush();
				out.close();
		} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.toString(), e);
		}finally {
			logAfter(logger);
		}
	}
	
	
	
	/**
	 * 客户关注微信公众号注册
	 */
	@RequestMapping(value="/go_query")
	public ModelAndView go_query(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		logBefore(logger, "WxBindCustomerController_goAddU");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String fromUserName = pd.getString("fromUserName");
			String toUserName = pd.getString("toUserName");
			//String fromUserName="o2hr-wnlVRxI7-qGpOqF7QiI0Jdo";
			//String toUserName="gh_d6c13daeefce";
			String openid = fromUserName;
			String originalID =toUserName;  //集团的原始id
			List<Doc_Factory>  factoryList=wxbindcustomerService.findFactoryByOriginalID(originalID);
			//防止重复提交
			
			
			/*//查询出已经选中的工厂列表
			List<Doc_Factory> oldfactorys =     wxbindcustomerService.findFactoryByOpenID(openid);
			//获取绑定账号
			Wx_BindCustomer wx_bindcustomer = wxbindcustomerService.findwxbindcustomerByOpenID(openid);
			String originalID =toUserName;  //集团的原始id
			List<Doc_Factory>  factoryList=wxbindcustomerService.findFactoryByOriginalID(originalID);
			//pd.put("wxusername", wx_bindcustomer.getWxUserName());
			pd.put("susernumber", wx_bindcustomer.getSuserNumber());
			pd.put("phone", wx_bindcustomer.getPhone());
			pd.put("email", wx_bindcustomer.getEmail());
			pd.put("id", wx_bindcustomer.getId());
			pd.put("username", wx_bindcustomer.getNamepinyin());
			//把已关注的工厂id 
			String old_facids_str ="";
			for(Doc_Factory fac:oldfactorys){
				if(fac.getIs_bind()==1){
					old_facids_str+=fac.getId();
				}
			}
			pd.put("old_facids_str", old_facids_str);*/
			pd.put("fromUserName", openid);
			pd.put("toUserName", toUserName);

			mv.setViewName("weixin/query/query_code");
			mv.addObject("msg", "saveU");
			mv.addObject("pd", pd);

			mv.addObject("factoryList", factoryList);
			/*
			mv.addObject("factoryList", factoryList);
			mv.addObject("oldfactoryList", oldfactorys);
			mv.addObject("wx_bindcustomer", wx_bindcustomer);*/
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 防伪码
	 * @param msgtype
	 * @param response
	 */
	@RequestMapping(value="/query_code")
	public void query_code(HttpServletResponse response)throws Exception{
		logBefore(logger, "ToolController_gettemplate");
		JSONObject jo = new JSONObject();
		try {
				
				PageData pd = new PageData();
				pd = this.getPageData();
				String factoryid = pd.getString("factoryid");
				String secode =pd.getString("secode");
				Doc_Factory factory =factoryService.findFactoryById(factoryid);
				String factory_url =factory.getServiceurl();
				String reviceClient =factory.getServiceparam();
				//String reviceClient ="reviceTest";
				//String factory_url="http://192.168.11.3:8000/bin";
				ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]  {"client-bean.xml"});
				SrvWebchat srvwebchat = (SrvWebchat) context.getBean(reviceClient);
				SrvWebchatAction action = new SrvWebchatAction();
				String str_data ="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
								+"	<Head>"
								+"	  <Command>$0091</Command>"
								+"	  <Data>"+secode+"</Data>"
								+"	  <ExtParam>param</ExtParam>"
								+"	  <RemoteUL>"+factory_url+"</RemoteUL>"
								+"	</Head>";
				action.setNData(str_data);
				
				action.setNFunName("Bus_BusinessWebchat");
				SrvWebchatActionResponse reponse =srvwebchat.action(action);
				Element theElement=null,  root=null;
				String stockName ="";
				String phone  ="";
				String paramxml =reponse.getNData();
				Document xmldoc=ParseXml.toDocment(paramxml);
				root=xmldoc.getDocumentElement();
				try{
					theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/StockName", root);
					stockName=theElement.getTextContent();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(reponse.isResult()){
					jo.put("errmsg","ok");
					jo.put("fac_name", factory.getFactoryname());
					if("".equals(stockName))
						jo.put("errmsg","error");
					else
						jo.put("goods_name",stockName);
				}else{
					jo.put("errmsg","error");
				}
				
		} catch (Exception e) {
			jo.put("errmsg","timeout");
			System.out.println(e);
			logger.error(e.toString(), e);
		}finally {
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.write(jo.toString());
			out.flush();
			out.close();
			logAfter(logger);
		}
	}
	
	
	/**
		 * 进入待装车辆查询界面
	 */
	@RequestMapping(value="/go_car")
	public ModelAndView go_car(HttpServletRequest request,
					HttpServletResponse response)throws Exception{
				logBefore(logger, "WxBindCustomerController_goAddU");
				ModelAndView mv = this.getModelAndView();
				PageData pd = new PageData();
				pd = this.getPageData();
				try{
					String fromUserName = pd.getString("fromUserName");
					String toUserName = pd.getString("toUserName");
					//String fromUserName="oARNTwGkqsgD2y2iPAw4ZpDj9fKk";
					//String toUserName="gh_9e89ebcb9e48";
					String openid = fromUserName;
					String originalID =toUserName;  //集团的原始id
					List<Doc_Factory>  factoryList=wxbindcustomerService.findFactoryByOriginalID(originalID);
					//防止重复提交
					
					
					/*//查询出已经选中的工厂列表
					List<Doc_Factory> oldfactorys =     wxbindcustomerService.findFactoryByOpenID(openid);
					//获取绑定账号
					Wx_BindCustomer wx_bindcustomer = wxbindcustomerService.findwxbindcustomerByOpenID(openid);
					String originalID =toUserName;  //集团的原始id
					List<Doc_Factory>  factoryList=wxbindcustomerService.findFactoryByOriginalID(originalID);
					//pd.put("wxusername", wx_bindcustomer.getWxUserName());
					pd.put("susernumber", wx_bindcustomer.getSuserNumber());
					pd.put("phone", wx_bindcustomer.getPhone());
					pd.put("email", wx_bindcustomer.getEmail());
					pd.put("id", wx_bindcustomer.getId());
					pd.put("username", wx_bindcustomer.getNamepinyin());
					//把已关注的工厂id 
					String old_facids_str ="";
					for(Doc_Factory fac:oldfactorys){
						if(fac.getIs_bind()==1){
							old_facids_str+=fac.getId();
						}
					}
					pd.put("old_facids_str", old_facids_str);*/
					pd.put("fromUserName", openid);
					pd.put("toUserName", toUserName);

					mv.setViewName("weixin/query/query_car");
					mv.addObject("msg", "saveU");
					mv.addObject("pd", pd);
					mv.addObject("factoryList", factoryList);
					/*
					mv.addObject("factoryList", factoryList);
					mv.addObject("oldfactoryList", oldfactorys);
					mv.addObject("wx_bindcustomer", wx_bindcustomer);*/
				} catch(Exception e){
					logger.error(e.toString(), e);
				}
				return mv;
			}
	
	
	
	
	
	/**
	 * 查询车辆
	 * @param msgtype
	 * @param response
	 */
	@RequestMapping(value="/query_car")
	public void query_car(HttpServletResponse response)throws Exception{
		logBefore(logger, "ToolController_gettemplate");
		JSONObject jo = new JSONObject();
		try {
				PageData pd = new PageData();
				pd = this.getPageData();
				//String factoryid="8aac0f915871b08f01588f1161140001";
				String factoryid = pd.getString("factoryid");
				Doc_Factory factory =factoryService.findFactoryById(factoryid);
				String factory_url =factory.getServiceurl();
				//String factory_url="http://192.168.11.3:8000/bin";
				String reviceClient =factory.getServiceparam();
				//String reviceClient ="reviceTest";
				ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]  {"client-bean.xml"});
				SrvWebchat srvwebchat = (SrvWebchat) context.getBean(reviceClient);
				SrvWebchatAction action = new SrvWebchatAction();
				String str_data ="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
								+"	<Head>"
								+"	  <Command>$0092</Command>"
								+"	  <Data>param</Data>"
								+"	  <ExtParam>param</ExtParam>"
								+"	  <RemoteUL>"+factory_url+"</RemoteUL>"
								+"	</Head>";
				action.setNData(str_data);
				
				action.setNFunName("Bus_BusinessWebchat");
				
				SrvWebchatActionResponse reponse =srvwebchat.action(action);
				
				Element theElement=null,  root=null;
				String stockName ="";
				String lineCount ="";
				String truckCount ="";
				String phone  ="";
				String param =reponse.getNData();
				/*Document xmldoc=ParseXml.toDocment(paramxml);
				root=xmldoc.getDocumentElement();
				try{
					theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/StockName", root);
					stockName=theElement.getTextContent();
					theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/LineCount", root);
					lineCount=theElement.getTextContent();
					theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/TruckCount", root);
					truckCount=theElement.getTextContent();
				}catch(Exception e){
					e.printStackTrace();
				}*/
				
				
				List listcars = new ArrayList();
				Document xmldoc=ParseXml.toDocment(param);
				root=xmldoc.getDocumentElement();
			    NodeList nodelist =ParseXml.selectNodes("/DATA/Items/Item", root);
			    for (int i = 0; i < nodelist.getLength(); i++) {
			    		Node employee = nodelist.item(i);
			    		NodeList datalist =employee.getChildNodes();
			    		for(int j=0;j<datalist.getLength();j++){
			    			Node data = datalist.item(j);
			    			if ("StockName".equals(data.getNodeName())) {
			    				stockName=data.getTextContent();
							} else if ("LineCount".equals(data.getNodeName())) {
								lineCount=data.getTextContent();
							}  else if ("TruckCount".equals(data.getNodeName())) {
								truckCount=data.getTextContent();
							} 
			    		}
			    		Json_Car car = new Json_Car(stockName,lineCount,truckCount);
			    		listcars.add(car);
			    }
			    	
				/*JSONObject jo = new JSONObject();
				List listcars = new ArrayList();
				Json_Car car1 = new Json_Car("P.C32.5R袋装","3","1");
				Json_Car car2 = new Json_Car("P.C32.5R袋装","2","4");
				Json_Car car3 = new Json_Car("P.C65.5R袋装","1","0");
				listcars.add(car1);
				listcars.add(car2);
				listcars.add(car3);*/
				jo.put("listcars",listcars );
				jo.put("errmsg","ok");
				
		} catch (Exception e) {
				e.printStackTrace();
				jo.put("errmsg","error");
				logger.error(e.toString(), e);
		}finally {
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.write(jo.toString());
			out.flush();
			out.close();
			logAfter(logger);
		}
		
	}
}
