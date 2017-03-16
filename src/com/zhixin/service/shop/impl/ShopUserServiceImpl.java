package com.zhixin.service.shop.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.shop.ShopDriverDao;
import com.zhixin.dao.shop.ShopOrderDao;
import com.zhixin.dao.shop.ShopUserDao;
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
import com.zhixin.service.shop.ShopUserService;


@Service(value="shopuserService")
public class ShopUserServiceImpl implements ShopUserService{

	
	@Resource(name="shopuserDao")
	private ShopUserDao shopuserDao;

	
	@Resource(name="shopdriverDao")
	private ShopDriverDao shopdriverDao;
	
	
	/*@Resource(name="shopgoodsDao")
	private ShopGoodsDao shopgoodsDao;*/
	
	
	@Resource(name="shoporderDao")
	private ShopOrderDao shoporderDao;
	

	
	
	@Override
	public Wx_BindCustomer getWx_BindCustomerByNameAndPWd(String passwd) {
		// TODO Auto-generated method stub
		return shopuserDao.getWx_BindCustomerByNameAndPWd(passwd);
	}




	@Override
	public void updateWx_BindCustomerPassword(Wx_BindCustomer wx_BindCustomer) {
		// TODO Auto-generated method stub
		shopuserDao.updateWx_BindCustomerPassword(wx_BindCustomer);
	}




	@Override
	public Wx_BindCustomer getWx_BindCustomerByid(String wx_BindCustomer_id) {
		// TODO Auto-generated method stub
		return shopuserDao.getWx_BindCustomerByid(wx_BindCustomer_id);
	}




	@Override
	public List<Shop_Driver> findShopDrivers(Set<ShopLink_Customer_Driver> shopLink_Customer_Driver) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopDrivers(shopLink_Customer_Driver);
	}
















	@Override
	public Json_Driver findShopDriverByID(String driverid) {
		// TODO Auto-generated method stub
		return shopuserDao.findJsonDriverByD_ID(driverid);
	}




	@Override
	public Shop_Driver saveDriver(Shop_Driver driver, String shopuserid) {
		// TODO Auto-generated method stub
		return shopdriverDao.saveDriver(driver, shopuserid);
	}




	@Override
	public Shop_Driver findDriverByID(String driverid) {
		// TODO Auto-generated method stub
		return shopdriverDao.getById(driverid);
	}








	@Override
	public String saveShopOrder(Shop_Order shop_order) {
		// TODO Auto-generated method stub
		shoporderDao.save(shop_order);
		return shop_order.getO_id();
	}




	@Override
	public void updateOrderfac(String orderid, String factoryid, String c_id, String g_id) {
		// TODO Auto-generated method stub
		shoporderDao.saveShopOrder(orderid,factoryid, c_id,g_id);
	}







	@Override
	public void updateshopDriver(Shop_Driver driver) {
		// TODO Auto-generated method stub
		shopdriverDao.updateShoperDriver(driver);
	}








	@Override
	public void deleteShoperDriver(String did, Shop_Driver driver) {
		// TODO Auto-generated method stub
		shopdriverDao.deleteShoperDriver(did,driver);
		
	}




	@Override
	public Json_Driver findJsonDriverByD_ID(String d_id) {
		// TODO Auto-generated method stub
		return shopuserDao.findJsonDriverByD_ID(d_id);
	}




	@Override
	public Shop_Driver findDriverByD_ID(String d_id) {
		// TODO Auto-generated method stub
		return shopuserDao.findDriverByD_ID(d_id);
	}








	@Override
	public List<Shop_Order> findShopOrderByDriverId(Shop_Driver driver) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopOrderByDriverId(driver);
	}




	@Override
	public Set<ShopLink_Customer_Driver> findShopLink_Customer_DriverByU_id(Wx_BindCustomer customer) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopLink_Customer_DriverByU_id(customer);
	}




	@Override
	public void updateLastLogin(Wx_BindCustomer wx_BindCustomer) {
		// TODO Auto-generated method stub
		shopuserDao.updateLastLogin(wx_BindCustomer);
	}


	

	@Override
	public Shop_Client findShopClientByID(String c_id) {
		// TODO Auto-generated method stub
		 return shopuserDao.findShopClientByID(c_id);
	}




	@Override
	public Wx_BindCustomer getWx_BindCustomerPWd(String pc_passwd) {
		// TODO Auto-generated method stub
		return shopuserDao.getWx_BindCustomerPWd(pc_passwd);
	}

	

	

	
	
}
