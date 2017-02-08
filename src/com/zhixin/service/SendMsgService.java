package com.zhixin.service;

import com.zhixin.model.X_Eventmsg;

public interface SendMsgService {

	void update_sendMsgService();

	X_Eventmsg findmsgbyID(String msg_id);

}
