package com.zhixin.service.shop.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.shop.ShopDriverDao;
import com.zhixin.dao.shop.ShopGoodsDao;

import com.zhixin.dao.shop.ShopOrderDao;
import com.zhixin.dao.shop.ShopUserDao;
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
import com.zhixin.service.shop.ShopUserService;


@Service(value="shopuserService")
public class ShopUserServiceImpl implements ShopUserService{

	
	@Resource(name="shopuserDao")
	private ShopUserDao shopuserDao;

	
	@Resource(name="shopdriverDao")
	private ShopDriverDao shopdriverDao;
	
	
	@Resource(name="shopgoodsDao")
	private ShopGoodsDao shopgoodsDao;
	
	
	@Resource(name="shoporderDao")
	private ShopOrderDao shoporderDao;
	

	
	
	@Override
	public Shop_User getShopUserByNameAndPWd(String passwd) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopUserByNameAndPwd(passwd);
	}

	@Override
	public void updateLastLogin(Shop_User shop_user) {
		// TODO Auto-generated method stub
		shopuserDao.update(shop_user);
	}

	@Override
	public Shop_User getShopUserByid(String shopuserid) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopUserByID(shopuserid);
	}

	@Override
	public List<Shop_Driver> findShopDrivers(Set<ShopLink_User_Driver> linkuser_driverSet) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopDriver(linkuser_driverSet);
	}

	

	@Override
	public List<Json_Goods> findShopGoodsByfacid(String factoryid) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopGoodsByfacid(factoryid);
	}

	@Override
	public List<Json_Client> findShopClients(String shopuserid, String factoryid) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopClients(shopuserid,factoryid);
	}

	@Override
	public List<Shop_Client> findShopClients(String u_id) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopClients(u_id);
	}

	@Override
	public Json_Driver findShopDriverByID(String driverid) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopDriverByID(driverid);
	}

	@Override
	public Shop_Driver saveDriver(Shop_Driver driver, String shopuserid) {
		// TODO Auto-generated method stub
		return shopdriverDao.saveDriver(driver,shopuserid);
	}

	/*@Override
	public Shop_Goods findShopGoodsByID(String goodsid) {
		// TODO Auto-generated method stub
		return shopgoodsDao.findShopGoodsByID( goodsid);
	}*/

	@Override
	public Shop_Driver findDriverByID(String driverid) {
		// TODO Auto-generated method stub
		return shopdriverDao.getById(driverid);
	}

	

	@Override
	public PageBean listOrders(String currentPage, String u_id) {
		// TODO Auto-generated method stub
		return shoporderDao.listOrders(currentPage,u_id);
	}

	/*@Override
	public void saveShopOrder(Shop_Order shop_order, String factoryid) {
		// TODO Auto-generated method stub
		shoporderDao.save(shop_order);
		shoporderDao.saveShopOrder(shop_order.getO_id(),factoryid);
	}*/

	@Override
	public String saveShopOrder(Shop_Order shop_order) {
		// TODO Auto-generated method stub
		shoporderDao.save(shop_order);
		return shop_order.getO_id();
	}

	@Override
	public void updateOrderfac(String orderid, String factoryid,String c_id,String g_id) {
		// TODO Auto-generated method stub
		shoporderDao.saveShopOrder(orderid,factoryid, c_id,g_id);
	}

	@Override
	public void updateorderstatus(String orderid) {
		// TODO Auto-generated method stub
		shoporderDao.updateorderstatus(orderid);
	}

	@Override
	public void updateshopDriver(Shop_Driver driver) {
		// TODO Auto-generated method stub
		shopdriverDao.update(driver);
	}

	@Override
	public void updateShopUserPwd(String username, String passwd,String factoryid) {
		// TODO Auto-generated method stub
		shopuserDao.updateShopUserPwd( username,  passwd,factoryid);
	}

	@Override
	public Shop_User findShopUserByUsername(String username,String factoryid) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopUserByUsername( username,factoryid);
	}

	@Override
	public void deleteShoperDriver(String did,Shop_Driver driver) {
		// TODO Auto-generated method stub
		shopdriverDao.deleteShoperDriver(did,driver);
	}

	@Override
	public Json_Driver findJsonDriverByD_ID(String d_id) {
		// TODO Auto-generated method stub
		return shopdriverDao.findJsonDriverByD_ID(d_id);
	}

	@Override
	public void updateShoperDriver(Shop_Driver driver) {
		// TODO Auto-generated method stub
		shopdriverDao.updateShoperDriver(driver);
	}

	@Override
	public Shop_Driver findDriverByD_ID(String d_id) {
		// TODO Auto-generated method stub
		return shopdriverDao.findDriverByD_ID(d_id);
	}

	@Override
	public List<Shop_Order> findShopOrderByDriverId(Shop_Driver shopdriver) {
		// TODO Auto-generated method stub
		return shoporderDao.findShopOrderByDriverId(shopdriver);
	}

	@Override
	public Set<ShopLink_User_Driver> findShopLink_User_DriverByU_id(Shop_User user) {
		// TODO Auto-generated method stub
		return shopuserDao.findShopLink_User_DriverByU_id(user);
	}

	

	

	

	

	
	
}
