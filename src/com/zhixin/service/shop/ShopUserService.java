package com.zhixin.service.shop;

import java.util.List;
import java.util.Set;

import com.zhixin.entity.Json_Client;
import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Goods;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.ShopLink_Customer_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Wx_BindCustomer;

public interface ShopUserService {

	Wx_BindCustomer getWx_BindCustomerByNameAndPWd(String passwd);

	void updateLastLogin(Wx_BindCustomer wx_BindCustomer);
	
	void updateWx_BindCustomerPassword(Wx_BindCustomer wx_BindCustomer);

	Wx_BindCustomer getWx_BindCustomerByid(String wx_BindCustomer_id);

	List<Shop_Driver> findShopDrivers(Set<ShopLink_Customer_Driver> shopLink_Customer_Driver);

	




	Json_Driver findShopDriverByID(String driverid);

	Shop_Driver saveDriver(Shop_Driver driver, String shopuserid);


	Shop_Driver findDriverByID(String driverid);

	


	String saveShopOrder(Shop_Order shop_order);

	void updateOrderfac(String orderid, String factoryid,String c_id,String g_id);


	void updateshopDriver(Shop_Driver driver);

	/**
	 * 删除司机
	 * @param driver
	 */
	void deleteShoperDriver(String did,Shop_Driver driver);
	
	/**
	 * 通过司机d_id查询司机json数据
	 * @param d_id
	 * @return
	 */
	Json_Driver findJsonDriverByD_ID(String d_id);
	
	Shop_Driver findDriverByD_ID(String d_id);
	
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
	
	Shop_Client findShopClientByID(String c_id);
	
	Wx_BindCustomer getWx_BindCustomerPWd(String pc_passwd);
}
