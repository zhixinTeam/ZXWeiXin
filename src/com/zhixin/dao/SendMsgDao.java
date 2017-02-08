package com.zhixin.dao;

import java.util.List;
import java.util.Map;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.X_Eventmsg;

public interface SendMsgDao extends DaoSupport<X_Eventmsg>{

	List<X_Eventmsg> findUnsendMsg();

	void update_issend_msg(String stub_str,int sendcount,int issend);

	Map<String,String> findMsgType(String factoryid,String type1);

	X_Eventmsg findMsgbyID(String msg_id);

	

}
