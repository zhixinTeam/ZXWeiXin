package com.zhixin.service.shop;

import com.zhixin.model.Shop_Goods;

public interface ShopGoodsService {

	Shop_Goods findShopGoodsByID(String goods_id);

	String  updateShopGoods(String factoryid,String shopgoodsid, String goodsname);

}
