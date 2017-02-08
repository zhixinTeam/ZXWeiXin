package com.zhixin.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import weixin.popular.support.TicketManager;

public class TicketManagerListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//WEB容器 初始化时调用
		//TicketManager 依赖 TokenManager，确保TokenManager.init 先被调用
		TicketManager.init("wxf91f187f9587856e");
		TicketManager.init("wx80d32387cee6c540");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//WEB容器  关闭时调用
		TicketManager.destroyed();
	}
}
