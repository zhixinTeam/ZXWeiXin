package com.zhixin.dao.shop.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.shop.ShopGoodsDao;
import com.zhixin.model.Shop_Goods;
@Service(value="shopgoodsDao")
public class ShopGoodsDaoImpl extends DaoSupportImpl<Shop_Goods> implements ShopGoodsDao{

	@Override
	public void saveShopGoods(List<Shop_Goods> listjb) {
		// TODO Auto-generated method stub
		for(Shop_Goods shopgoods:listjb){
			this.getSession().save(shopgoods);
		}
		
	}

	@Override
	public void deleteShopGoods(List<String> listdel) {
		// TODO Auto-generated method stub
		String hql="delete Shop_Goods  as c where c.goodsID=?";
		Query querydel= null;
		for(String goodsid:listdel){
			   querydel=getSession().createQuery(hql);
			   querydel.setString(0,goodsid);
			   querydel.executeUpdate();
		}
	}
	//修改商品
	@Override
	public void updateShopGoods(List<Shop_Goods> listjb) {
		// TODO Auto-generated method stub
		Shop_Goods shopgoods = null;
		for(Shop_Goods goods:listjb){
			shopgoods=(Shop_Goods) this.getSession().createQuery(//
					"from Shop_Goods g where g.goodsID=?")//
					.setParameter(0, goods.getGoodsID())//
					.uniqueResult();
			shopgoods.setGoodsname(goods.getGoodsname());
			shopgoods.setGoodstype(goods.getGoodstype());
			this.getSession().save(shopgoods);
		}
		
	}

	@Override
	public Shop_Goods findShopGoodsByID(String goodsid,String factoryid) {
		// TODO Auto-generated method stub
		  Query query= this.getSession().createQuery(//
				"from Shop_Goods g where g.goodsID=? and g.doc_factory.id=?")//
				.setParameter(0, goodsid)//
				.setParameter(1, factoryid);
		 List<Shop_Goods> list=query.list();
		 if(list.size()>0)
			 return list.get(0);
		 else 
			 return null;
	}
	
	
	

}
