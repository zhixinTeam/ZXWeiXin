package com.zhixin.service.client.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.client.ClientOrderDao;
import com.zhixin.entity.Json_ShopOrder;
import com.zhixin.model.Shop_Order;
import com.zhixin.service.client.ClientOrderService;

@Service(value="clientorderService")
public class ClientOrderServiceImpl implements ClientOrderService{

	@Resource(name="clientOrderDao")
	private ClientOrderDao clientOrderDao;
	
	@Override
	public List<Json_ShopOrder> listOrders(String currentPage, String u_id) {
		// TODO Auto-generated method stub
		return clientOrderDao.listOrders(currentPage, u_id);
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

}
