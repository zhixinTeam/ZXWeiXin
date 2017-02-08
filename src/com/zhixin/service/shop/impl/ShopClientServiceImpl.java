package com.zhixin.service.shop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.shop.ShopClientDao;
import com.zhixin.model.Shop_Client;
import com.zhixin.service.shop.ShopClientService;

@Service(value="shopclientService")
public class ShopClientServiceImpl  implements ShopClientService{

	
	@Resource(name="shopclientDao")
	private ShopClientDao shopclientDao;

	@Override
	public Shop_Client findShopClientByID(String client_id) {
		// TODO Auto-generated method stub
		return shopclientDao.getById(client_id);
	}
	
	
}
