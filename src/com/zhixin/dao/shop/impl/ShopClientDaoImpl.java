package com.zhixin.dao.shop.impl;

import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.shop.ShopClientDao;
import com.zhixin.model.Shop_Client;


@Service(value="shopclientDao")
public class ShopClientDaoImpl extends DaoSupportImpl<Shop_Client> implements ShopClientDao{

}
