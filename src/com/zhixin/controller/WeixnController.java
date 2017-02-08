package com.zhixin.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhixin.base.BaseController;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.service.WeixinEventService;
import com.zhixin.service.WxBindCustomerService;
import com.zhixin.tools.NewsMsgUtil;

import weixin.popular.api.MenuAPI;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLImageMessage;
import weixin.popular.bean.xmlmessage.XMLMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.TokenManager;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.XMLConverUtil;

@Controller
@RequestMapping(value="/weixin")
public class WeixnController extends BaseController{
	
	private static final long serialVersionUID = 1L;

	@Resource(name="wxbindcustomerService")
	private  WxBindCustomerService wxbindcustomerService;
	
	//从官方获取
	private String token = "weixin";

	//重复通知过滤
    private static ExpireKey expireKey = new DefaultExpireKey();
	
   @Resource(name="weixinEventService")
   private WeixinEventService weixinEventService;
   
	@ResponseBody
	@RequestMapping("/getxml")
	public String getxml(@RequestBody String body){
		return "helloworld! " + new Date();
	}
	
	@RequestMapping("/weixinapi")
	public void testServletAPI(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logBefore(logger, "WeixnController_weixinapi");
		try{
			
		   InputStreamReader inputStream =new InputStreamReader(request.getInputStream(),"UTF-8") ;
	       ServletOutputStream outputStream = response.getOutputStream();
	       //判断是否为微信公共账号发送的请求
	       String returnstr="关注须知\n1:请注册用户信息;\n2:请尽快到工厂部门绑定客户账号。\n志信科技 © big bug 2016";
	       String signature = request.getParameter("signature");
	       String timestamp = request.getParameter("timestamp");
	       String nonce = request.getParameter("nonce");
	       String echostr = request.getParameter("echostr");
	       //首次请求申请验证,返回echostr
	       if(echostr!=null){
	           outputStreamWrite(outputStream,echostr);
	           return;
	       }

	       //验证请求签名
	       if(!signature.equals(SignatureUtil.generateEventMessageSignature(token,timestamp,nonce))){
	           return;
	       }
	       if(inputStream!=null){
	           //转换XML
	           EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class,inputStream);
	          
	           String key = eventMessage.getFromUserName() + "__"
	           				   + eventMessage.getToUserName() + "__"
	           				   + eventMessage.getMsgId() + "__"
	           				   + eventMessage.getCreateTime();
	           
	          
	           if(expireKey.exists(key)){
	            	//重复通知不作处理
	            	return;
	            }else{
	            	expireKey.add(key);
	            }
	           //判断是否为关注或取消关注事件
        	   String params ="";
	           if(eventMessage.getEvent()!=null && ("".equals(eventMessage.getEventKey()) )){
	        	   if("subscribe".equals(eventMessage.getEvent())){
	        		   weixinEventService.update_suborunsub(eventMessage);
	        		}else if("unsubscribe".equals(eventMessage.getEvent())){
		        	   weixinEventService.update_suborunsub(eventMessage);
	        	   }
	        			   
	           }else if("V1001_reast".equals(eventMessage.getEventKey())){
    	           /*params+="?fromUserName="+eventMessage.getFromUserName();
    	           params+="&toUserName="+eventMessage.getToUserName();
    	           returnstr="<a href=\"http://101.200.87.118/wxplatform/wxuser/goAddU"+params+"\">vip注册</a>";*/
	        	   String bindxml=NewsMsgUtil.getBindNewsXML(eventMessage);
	        	   outputStream.write(bindxml.getBytes("utf-8"));
				   outputStream.flush();
		           return;
	        	   
	           }else if("V1001_system".equals(eventMessage.getEventKey())){
	        	   String bindxml=NewsMsgUtil.getSystemNewsXML(eventMessage);
	        	   outputStream.write(bindxml.getBytes("utf-8"));
				   outputStream.flush();
		           return;
	           }else if("V2001_secode".equals(eventMessage.getEventKey())){
	        	   
	        	   String bindxml=NewsMsgUtil.getSecodeNewsXML(eventMessage);
	        	   outputStream.write(bindxml.getBytes("utf-8"));
				   outputStream.flush();
		           return;
	           }else if("V2002_install".equals(eventMessage.getEventKey())){
	        	   String bindxml=NewsMsgUtil.getCarsNewsXML(eventMessage);
	        	   outputStream.write(bindxml.getBytes("utf-8"));
				   outputStream.flush();
		           return;
	           }else if("V2003_report".equals(eventMessage.getEventKey())){
	        	   String bindxml=NewsMsgUtil.getReportNewsXML(eventMessage);
	        	   outputStream.write(bindxml.getBytes("utf-8"));
				   outputStream.flush();
		           return;
	           }
	           //创建回复
	           XMLTextMessage xmlTextMessage = new XMLTextMessage(
	                   eventMessage.getFromUserName(),
	                   eventMessage.getToUserName(),
	                   returnstr);
	          
	          
	           xmlTextMessage.outputStreamWrite(outputStream);
	           
	          /* String xml ="";
	           
	           
	           outputStream.write(xml.getBytes("utf-8"));
			   outputStream.flush();*/
	           return;
	       }
	      
	       outputStreamWrite(outputStream,"");
	} catch(Exception e){
		logger.error(e.toString(), e);
	}finally {
		logAfter(logger);
	}
	}
	
	
	
	 /**
	    * 数据流输出
	    * @param outputStream
	    * @param text
	    * @return
	    */
	   private boolean outputStreamWrite(OutputStream outputStream,String text){
		   logBefore(logger, "WeixnController_weixinapi_outputStream");
	       try {
	           outputStream.write(text.getBytes("utf-8"));
	       } catch (UnsupportedEncodingException e) {
	           // TODO Auto-generated catch block
	    	   logger.error(e.toString(), e);
	           e.printStackTrace();
	           return false;
	       } catch (IOException e) {
	           // TODO Auto-generated catch block
	    	   logger.error(e.toString(), e);
	           return false;
	       }finally {
				logAfter(logger);
			}
	       return true;
	   }
	
	
}
