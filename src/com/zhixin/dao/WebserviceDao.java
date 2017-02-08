package com.zhixin.dao;


import com.zhixin.base.DaoSupport;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Eventmsg;

public interface WebserviceDao extends DaoSupport<X_Eventmsg> {

	Wx_BindCustomer findUniqueCustomer(String facid ,String phone);

	void updateLinkfac_customer(String fac_id, String bindcustomerid, int is_bind);

	void updatecomplete_shoporders(String ordernumber, int status);

	//List<X_Eventmsg> findMsg_IsNoSend();

	

	/*void savemsg_event(Event_msg event_msg);*/

}
