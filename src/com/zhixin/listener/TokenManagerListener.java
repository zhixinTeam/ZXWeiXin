package com.zhixin.listener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zhixin.service.CompanyService;

import weixin.popular.support.TokenManager;
/*
 * 根据集团里添加的appid 与secret 获取token
 * 每天加载一次
 */
public class TokenManagerListener implements ServletContextListener{

	private CompanyService companyService;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//WEB容器 初始化时调用
		/*String appid="wxf91f187f9587856e";
		String secret ="ebb65c132c02dc35e813de208583c096";
		String appid1="wx9ae025a20262ce0b";
		String secret1 ="e79e415ff4ab1e9e3e0916207f419ddb";
		TokenManager.init(appid1, secret1);
		TokenManager.init(appid, secret);*/
		ServletContext context = sce.getServletContext();
        //取得appliction上下文
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        //取得特定bean
        companyService = (CompanyService) ctx.getBean("companyService");
        
		/*wx9ae025a20262ce0b?
				AppSecret(应用密钥) 
				e79e415ff4ab1e9e3e0916207f419ddb  */
		this.init(companyService);
	}

	
	
	public void init(final CompanyService companyService){
		
		/*TokenManagerListener tokenmanagerlistener = new TokenManagerListener();
		tokenmanagerlistener.init_tokens(companyService);*/
			//定时每天1点重新获取需要服务集团
			Calendar twentyOne = Calendar.getInstance();
	        twentyOne.set(Calendar.HOUR_OF_DAY, 0);
	        twentyOne.set(Calendar.MINUTE, 0);
	        twentyOne.set(Calendar.SECOND, 0);
		    final TokenManagerListener tokenmanagerlistener = new TokenManagerListener();
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					tokenmanagerlistener.init_tokens(companyService);
				}
			},twentyOne.getTime(),1000*60*60*24);
			
		}
	
	
	public void init_tokens(CompanyService companyService){
		try{
			companyService.find_tokens();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//WEB容器  关闭时调用
		TokenManager.destroyed();
	}
}
