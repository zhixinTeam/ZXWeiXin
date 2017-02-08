package com.zhixin.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.zhixin.service.SendMsgService;

import weixin.popular.api.MessageAPI;
import weixin.popular.api.UserAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.support.TokenManager;
import weixin.popular.util.JsonUtil;
public class SendMsgManagerListener  implements ServletContextListener{

	
	private SendMsgService	sendmsgservice;
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext context = sce.getServletContext();
        //取得appliction上下文
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        //取得特定bean
        sendmsgservice = (SendMsgService) ctx.getBean("sendmsgservice");
		this.init(sendmsgservice);
		
	}

	
	public  void init(final SendMsgService sendmsgservice){
		final SendMsgManagerListener sendmsgListener = new SendMsgManagerListener();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				
				 if(TokenManager.getDefaultToken() !=null){
					 sendmsgListener.sendMesg(sendmsgservice);	
				 }
					 
			}
		},0,1000*60);
		
	}
	
	public void sendMesg(SendMsgService sendmsgservice){
		/*weixin.popular.bean.FollowResult followresult =UserAPI.userGet(TokenManager.getDefaultToken(), null);
		List<String> list_openid = new ArrayList<>();
		
		LinkedHashMap<String, TemplateMessageItem> data = new LinkedHashMap<>();
		String first = "部门会议通知";
		String remark ="明日开机会自动运行升级程序，请不要人工干涉谢谢您的配合，祝工作愉快！测试";
		data.put("first", new TemplateMessageItem(first, "#173177"));
		TemplateMessageItem itemj = new TemplateMessageItem("2016年3月31日 10:36", "#173177");
		data.put("keyword1",itemj);
		String key_str="1、所有服务号都可以在功能->添加功能插件处看到申请模板消息功能的入口，但只有认证后的服务号才可以申请模板消息的使用权限并获得该权限；"+
		"2、需要选择公众账号服务所处的2个行业，每月可更改1次所选行业；" +
		"3、在所选择行业的模板库中选用已有的模板进行调用；"+
		"4、每个账号可以同时使用25个模板。";
		TemplateMessageItem itemj2 = new TemplateMessageItem(key_str, "#173177");
		data.put("keyword2",itemj2);
		TemplateMessage template = new TemplateMessage();
		template.setData(data);
		template.setTemplate_id("3FYfTC-p3JUgX9KGqtaXRgy1PXLD1CyP8VUHBvaOveA"); 
		//template.setUrl("http://www.baidu.com");
		data.put("remark", new TemplateMessageItem(remark, "#173177"));
		for(int i=0;i<followresult.getData().getOpenid().length;i++){
			list_openid.add(followresult.getData().getOpenid()[i]);
			template.setTouser(followresult.getData().getOpenid()[i]);
			TemplateMessageResult templateresult =MessageAPI.messageTemplateSend(TokenManager.getDefaultToken(), template);
		}
		
		*/
		
		
		
		 
		
		try{
			sendmsgservice.update_sendMsgService();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
}
