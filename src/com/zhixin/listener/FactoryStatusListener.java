package com.zhixin.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zhixin.service.FactoryService;


/*
 * 部门工厂监听
 */
@Component
public class FactoryStatusListener  implements ServletContextListener{

	
	private FactoryService factoryService;
	//private ServletContextEvent sce
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext context = sce.getServletContext();
        //取得appliction上下文
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        //取得特定bean
        factoryService = (FactoryService) ctx.getBean("factoryService");
		//this.init(factoryService);
	}

	
	public void init(final FactoryService factoryService){
		
		final FactoryStatusListener facstatuslistener = new FactoryStatusListener();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				 facstatuslistener.check_status(factoryService);
			}
		},0,1000*60*60*24);
		
	}
	
	
	
	
	protected void check_status(FactoryService factoryService) {
		// TODO Auto-generated method stub
		try{
			factoryService.updatecheckfac_status();
		}catch( Exception e){
			e.printStackTrace();
		}
		
		
		
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
