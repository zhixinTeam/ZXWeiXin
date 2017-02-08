package com.zhixin.dao.shop;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.Shop_Goods;

public interface ShopGoodsDao extends DaoSupport<Shop_Goods>{

	void saveShopGoods(List<Shop_Goods> listjb);

	void deleteShopGoods(List<String>  listdel);

	void updateShopGoods(List<Shop_Goods> listjb);

	Shop_Goods findShopGoodsByID(String goodsid,String factoryid);

}
