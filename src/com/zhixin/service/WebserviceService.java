package com.zhixin.service;

import java.util.Map;

import com.zhixin.model.X_Eventmsg;
import com.zhixin.webservice.bean.Event_msg;

public interface WebserviceService {
	


	public String getCustomerInfo(String param);

	public String savemsg_event(String  jsonparam);

	public String update_bindfac(String parstr);

	public String updateget_shoporders(String params);
	
	/*public String save_shopgoods(String params);*/

	

	public String updateget_shoporderByNO(String params);
	
	public String updatecomplete_shoporders(String params);

	public void saveeventMsg(X_Eventmsg x_eventmsg);

	public String save_shopclients(String params);

}
