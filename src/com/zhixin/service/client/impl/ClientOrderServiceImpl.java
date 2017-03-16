package com.zhixin.service.client.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.client.ClientOrderDao;
import com.zhixin.entity.Json_ShopOrder;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Order;
import com.zhixin.service.client.ClientOrderService;

@Service(value="clientorderService")
public class ClientOrderServiceImpl implements ClientOrderService{

	@Resource(name="clientOrderDao")
	private ClientOrderDao clientOrderDao;
	
	@Override
	public List<Json_ShopOrder> listOrders(String currentPage, String id) {
		// TODO Auto-generated method stub
		return clientOrderDao.listOrders(currentPage, id);
	}

	@Override
	public Shop_Order oneOrder(String o_id) {
		// TODO Auto-generated method stub
		return clientOrderDao.getById(o_id);
	}

	@Override
	public void updateShopOrder(Shop_Order shop_Order) {
		// TODO Auto-generated method stub
		clientOrderDao.update(shop_Order);
	}

	@Override
	public List<Shop_Driver> findDriversByCustomerId(String customerid) {
		// TODO Auto-generated method stub
		return clientOrderDao.findDriversByCustomerId(customerid);
	}

	@Override
	public List<Json_ShopOrder> list_shop_Orders(String currentPage, String id) {
		// TODO Auto-generated method stub
		return clientOrderDao.list_shop_Orders(currentPage, id);
	}

	@Override
	public List<Shop_Order> list_shop_Order(String currentPage, String id) {
		// TODO Auto-generated method stub
		return clientOrderDao.list_shop_Order(currentPage, id);
	}

}
