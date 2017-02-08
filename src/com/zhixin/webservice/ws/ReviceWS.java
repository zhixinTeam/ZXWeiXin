package com.zhixin.webservice.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.zhixin.webservice.bean.Event_msg;

@WebService
public interface ReviceWS {

	
	
	@WebMethod
	public String mainfuncs(String funcname,String params);
}
