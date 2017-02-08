package com.zhixin.dao;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.Wx_BindCustomer;

import weixin.popular.bean.message.EventMessage;


public interface WeixinEventDao extends DaoSupport<Wx_BindCustomer> {

	void save_subfactory(EventMessage eventMessage);

	void unsubfactory(EventMessage eventMessage);

}
