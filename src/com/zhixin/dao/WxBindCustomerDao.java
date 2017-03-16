package com.zhixin.dao;

import java.util.List;
import java.util.Set;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.Wx_BindUser;

public interface WxBindCustomerDao  extends DaoSupport<Wx_BindCustomer>{

	PageBean findpagewxbindcustomer(String factoryid,String currentPage ,String status,String namepinyin );

	List<Doc_Factory> findFactoryByOriginalID(String originalID);

	void save_wxbindUser(Wx_BindCustomer wx_bind ,List <Doc_Factory> listfactorys);

	Wx_BindCustomer findFactoryByOpenID(String openid);

	Wx_BindCustomer findwxbindcustomerByOpenID(String openid);

	void update_wxbindUser(Wx_BindCustomer wx_bind ,List <Doc_Factory> listfactorys );

	void update_wxbindUserStatus(Wx_BindCustomer wx_bind);

	List<Wx_BindUser> findwxUserByFactoryID(String factoryid);

	List<Wx_BindCustomer> findwxbindcusUserByFactoryID(String factoryid);

	Set<Sys_User> findsysUserByFactoryID(String factoryid);

	Wx_BindCustomer findsysUserByFactoryID(String phone, String factoryid);

	void saveShopClient(List<Shop_Client> listjb);

	Shop_Client findClientByFactory(String clientnumber,String factoryID);

	void deleteByClientID(List<String> listdel);

	Wx_BindCustomer findByUSername(String newusername);

	List<Shop_Client> findClientByCustomerId(String customerid, String factoryid);

	Wx_BindCustomer findFactoryBycustomerID(String customerID);

	Set<Wx_BindCustomer> findBindCustomerFactoryID(String tracknumber, String factoryid);


	/**
	 * pc¶ËÓÃ»§×¢²á
	 * @param wx_bind
	 */
	void saveCustomer(Wx_BindCustomer wx_bind);

	void updateLinkFactoryBycustomer(Doc_Factory factory, Wx_BindCustomer wx_BindCustomer);

	

}
