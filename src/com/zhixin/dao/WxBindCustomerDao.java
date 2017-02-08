package com.zhixin.dao;

import java.util.List;
import java.util.Set;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.Wx_BindUser;

public interface WxBindCustomerDao  extends DaoSupport<Wx_BindCustomer>{

	PageBean findpagewxbindcustomer(String factoryid,String currentPage ,String status,String namepinyin );

	List<Doc_Factory> findFactoryByOriginalID(String originalID);

	void save_wxbindUser(Wx_BindCustomer wx_bind ,List <Doc_Factory> listfactorys);

	Wx_BindCustomer findFactoryByOpenID(String openid);

	Wx_BindCustomer findwxbindcustomerByOpenID(String openid);

	void update_wxbindUser(Wx_BindCustomer wx_bind ,List <Doc_Factory> listfactorys ,String old_facids_str);

	void update_wxbindUserStatus(Wx_BindCustomer wx_bind);

	List<Wx_BindUser> findwxUserByFactoryID(String factoryid);

	List<Wx_BindCustomer> findwxbindcusUserByFactoryID(String factoryid);

	Set<Sys_User> findsysUserByFactoryID(String factoryid);

	Wx_BindCustomer findsysUserByFactoryID(String phone, String factoryid);

}
