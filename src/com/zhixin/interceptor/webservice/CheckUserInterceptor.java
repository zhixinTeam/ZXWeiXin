package com.zhixin.interceptor.webservice;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.Element;

import com.zhixin.model.Sys_User;
import com.zhixin.service.FactoryService;
import com.zhixin.service.UserService;
import com.zhixin.service.WebserviceService;
import com.zhixin.service.impl.UserServiceImpl;

/**
 * 查检用户的拦截器
 * @author xfzhang
 *
 */
@WebService
public class CheckUserInterceptor extends AbstractPhaseInterceptor<SoapMessage>  {

	
	@Resource(name="userService")
	private UserService userService;
	
	
	public CheckUserInterceptor() {
		super(Phase.PRE_PROTOCOL);
		System.out.println("CheckUserInterceptor()");
	}

	/*
 	<Envelope>
 		<head>
 			<zhixin>
 				<name>xfzhang</name>
 				<password>123456</password>
 			</zhixin>
 			
 		<head>
 		<Body>
 			<sayHello>
 				<arg0>BOB</arg0>
 			<sayHello>
 		</Body>
 	</Envelope>
 */
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		Header header = message.getHeader(new QName("zhixin"));
		//userService.f
		if(header!=null) {
			Element element = (Element) header.getObject();
			String name = element.getElementsByTagName("name").item(0).getTextContent();
			String password = element.getElementsByTagName("password").item(0).getTextContent();
			String passwd = new  SimpleHash("SHA-1",name,password).toString();
			Sys_User sys_user =userService.getUserByNameAndPWd(passwd);
			if(sys_user != null) {
				System.out.println("Server 通过拦截器....");
				return;
			}
		}
		
		
		//不能通过
		System.out.println("Server 没有通过拦截器....");
		throw new Fault(new RuntimeException("请求需要一个正确的用户名和密码!"));
	}

	

}
