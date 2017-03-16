package com.zhixin.dao.shop;

import java.util.List;
import java.util.Set;

import com.zhixin.base.DaoSupport;
import com.zhixin.entity.Json_Client;
import com.zhixin.entity.Json_Doc_Company;
import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Goods;
import com.zhixin.model.ShopLink_Customer_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Wx_BindCustomer;

public interface ShopUserDao extends DaoSupport<Wx_BindCustomer>{

	Wx_BindCustomer getWx_BindCustomerByNameAndPWd(String passwd);

	void updateWx_BindCustomerPassword(Wx_BindCustomer wx_BindCustomer);
	
	void updateLastLogin(Wx_BindCustomer wx_BindCustomer);
	
	Wx_BindCustomer getWx_BindCustomerByid(String wx_BindCustomer_id);

	List<Shop_Driver> findShopDrivers(Set<ShopLink_Customer_Driver> shopLink_Customer_Driver);

	

	List<Json_Goods> findShopGoodsByfacid(String factoryid);

	List<Json_Client> findShopClients(String shopuserid, String factoryid);

	List<Shop_Client> findShopClients(String u_id);

	Json_Driver findShopDriverByID(String driverid);

	void updateshopDriver(Shop_Driver driver);

	void updateShopUserPwd(String username, String passwd,String factoryid);

	
	/**
	 * 通过司机d_id查询司机json数据
	 * @param d_id
	 * @return
	 */
	Json_Driver findJsonDriverByD_ID(String d_id);
	
	Shop_Driver findDriverByD_ID(String d_id);
	/**
	 * 修改司机
	 * @param driver
	 */
	void updateShoperDriver(Shop_Driver driver);
	
	/**
	 * 通过司机id查询司机的货单信息
	 * @param d_id
	 * @return
	 */
	List<Shop_Order> findShopOrderByDriverId(Shop_Driver driver);

	/**
	 * 通过shopuser查询ShopLink_User_Driver
	 * @param user
	 * @return
	 */
	Set<ShopLink_Customer_Driver> findShopLink_Customer_DriverByU_id(Wx_BindCustomer customer);
	

	Shop_Client findShop_Clinet(String clientnumber, String u_id);
	
	Shop_Client findShopClientByID(String c_id);

	/**
	 * 通过用户id查询绑定到的工厂集团
	 * @return
	 */
	Json_Doc_Company findDoc_Company(String c_id);
	
	
	
	Wx_BindCustomer getWx_BindCustomerPWd(String pc_passwd);
}
