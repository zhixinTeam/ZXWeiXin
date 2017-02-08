package com.zhixin.service.shop;

import java.util.List;
import java.util.Set;

import com.zhixin.entity.Json_Client;
import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Goods;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.ShopLink_User_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Shop_User;

public interface ShopUserService {

	Shop_User getShopUserByNameAndPWd(String passwd);

	void updateLastLogin(Shop_User shop_user);

	Shop_User getShopUserByid(String shopuserid);

	List<Shop_Driver> findShopDrivers(Set<ShopLink_User_Driver> linkuser_driverSet);

	

	List<Json_Goods> findShopGoodsByfacid(String factoryid);

	List<Json_Client> findShopClients(String shopuserid, String factoryid);

	List<Shop_Client> findShopClients(String u_id);

	Json_Driver findShopDriverByID(String driverid);

	Shop_Driver saveDriver(Shop_Driver driver, String shopuserid);

	//Shop_Goods findShopGoodsByID(String goodsid);

	Shop_Driver findDriverByID(String driverid);

	

	PageBean listOrders(String currentPage, String u_id);

	String saveShopOrder(Shop_Order shop_order);

	void updateOrderfac(String orderid, String factoryid,String c_id,String g_id);

	void updateorderstatus(String orderid);

	void updateshopDriver(Shop_Driver driver);

	void updateShopUserPwd(String username, String passwd,String factoryid);

	Shop_User findShopUserByUsername(String username,String factoryid);

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
	 * 修改司机
	 * @param driver
	 */
	void updateShoperDriver(Shop_Driver driver);
	
	/**
	 * 通过司机id查询司机的货单信息
	 * @param d_id
	 * @return
	 */
	List<Shop_Order> findShopOrderByDriverId(Shop_Driver shopdriver);

	/**
	 * 通过shopuser查询ShopLink_User_Driver
	 * @param user
	 * @return
	 */
	Set<ShopLink_User_Driver> findShopLink_User_DriverByU_id(Shop_User user);
	
}
