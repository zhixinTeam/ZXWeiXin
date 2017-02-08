package com.zhixin.service.shop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.FactoryDao;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Shop_Goods;
import com.zhixin.service.shop.ShopGoodsService;


@Service(value="shopgoodsService")
public class ShopGoodsServiceImpl implements ShopGoodsService{

	@Resource(name="shopgoodsDao")
	private com.zhixin.dao.shop.ShopGoodsDao ShopGoodsDao;

	@Resource(name="factorydao")
	private FactoryDao factorydao;
	
	@Override
	public Shop_Goods findShopGoodsByID(String goods_id) {
		// TODO Auto-generated method stub
		return ShopGoodsDao.getById(goods_id);
	}

	@Override
	public String updateShopGoods(String factoryid,String shopgoodsid, String goodsname) {
		// TODO Auto-generated method stub
		Shop_Goods shop_goods =ShopGoodsDao.findShopGoodsByID(shopgoodsid,factoryid);
		if(shop_goods ==null){
			Doc_Factory factory =factorydao.getById(factoryid);
			Shop_Goods shop_good = new Shop_Goods ();
			shop_good.setGoodsID(shopgoodsid);
			shop_good.setGoodsname(goodsname);
			shop_good.setDoc_factory(factory);
			ShopGoodsDao.save(shop_good);
			shop_goods =ShopGoodsDao.findShopGoodsByID(shopgoodsid,factoryid);
		}
		
		return shop_goods.getG_id();
			
	}
}
