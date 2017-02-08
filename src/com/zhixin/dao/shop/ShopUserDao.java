package com.zhixin.dao.shop;

import java.util.List;
import java.util.Set;

import com.zhixin.base.DaoSupport;
import com.zhixin.entity.Json_Client;
import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Goods;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.ShopLink_User_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_User;

public interface ShopUserDao extends DaoSupport<Shop_User>{

	Shop_User findShopUserByNameAndPwd(String passwd);

	Shop_User findShopUserByPhoneandFactoryid(String phone,String factoryid);

	void saveShopUserandClient(List<Shop_Client> listjb, Shop_User shopuser);

	void saveShopClient(List<Shop_Client>  listjb);

	void deleteByClientID(List<String> listdel);

	void updateShopClients(List<Shop_Client> listjb);

	Shop_User findShopUserByID(String shopuserid);

	List<Shop_Driver> findShopDriver(Set<ShopLink_User_Driver> linkuser_driverSet);

	List<Json_Goods> findShopGoodsByfacid(String factoryid);

	List<Json_Client> findShopClients(String shopuserid, String factoryid);

	List<Shop_Client> findShopClients(String u_id);

	Json_Driver findShopDriverByID(String driverid);

	void updateShopUserPwd(String username, String passwd,String factoryid);

	Shop_User findShopUserByUsername(String username,String factoryid);
	
	/**
	 * 通过shopuser查询ShopLink_User_Driver
	 * @param user
	 * @return
	 */
	Set<ShopLink_User_Driver> findShopLink_User_DriverByU_id(Shop_User user);

	Shop_Client findShop_Clinet(String clientnumber, String u_id);

}
