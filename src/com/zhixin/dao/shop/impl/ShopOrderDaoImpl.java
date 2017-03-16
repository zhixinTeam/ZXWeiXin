package com.zhixin.dao.shop.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.shop.ShopOrderDao;
import com.zhixin.entity.Json_Order;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Sys_Auth;

@Service(value="shoporderDao")
public class ShopOrderDaoImpl extends DaoSupportImpl<Shop_Order> implements ShopOrderDao{

	@Override
	public PageBean listOrders(String currentPage, String u_id) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*10;
		Query query=getSession().createQuery("from Shop_Order o where  o.shopuser.u_id='"+u_id+"' order by o.orderdate desc ");  //带条件的查询语句  
		int count =query.list().size();
		query.setFirstResult(firdata);  
		query.setMaxResults(10);  
		List<Shop_Order> list=query.list();
		while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =(curpage-1)*10;
			   query=getSession().createQuery("from Shop_Order o where  o.shopuser.u_id='"+u_id+"' order by o.orderdate desc");  //带条件的查询语句  
			   count =query.list().size();
			   query.setFirstResult(firdata);  
			   query.setMaxResults(10);  
			   list=query.list();
			   if(list.size()>0)
				   break;
		  }
		 PageBean pagebean = new PageBean( curpage, 10, count, list);
		return pagebean;
		
		
	}


	@Override
	public void saveShopOrder(String o_id, String factoryid,String c_id,String g_id) {
		// TODO Auto-generated method stub
		Query query = getSession().createSQLQuery("update shop_order o   set  o.FactoryID= '"+factoryid+"' , o.clientid= '"+c_id+"', o.goodsid= '"+g_id+"'  where o.o_id= '"+o_id+"' ");
		query.executeUpdate();
	}

	@Override
	public void updateorderstatus(String orderid) {
		// TODO Auto-generated method stub
		Query query = getSession().createSQLQuery("update shop_order o   set o.status= '"+2+"'   where o.o_id= '"+orderid+"' ");
		query.executeUpdate();
	}

	@Override
	public List<Json_Order> update_get_shoporders(String fac_id, String idnumber) {
		// TODO Auto-generated method stub
		List<Json_Order> json_orders=new ArrayList<>();
		Query querylist=getSession().createQuery("from Shop_Order o where o.status ='0' and  o.idnumber = '"+idnumber+"'  and o.doc_factory.id ='"+fac_id+"'");  //带条件的查询语句     
		List<Shop_Order> list_orders=querylist.list();
		for(Shop_Order shop_order:list_orders){
			Json_Order json_order = new Json_Order();
			json_order.setO_id(shop_order.getO_id());
			json_order.setIdnumber(idnumber);
			json_order.setOrdernumber(shop_order.getOrdernumber());
			json_order.setFac_order_no(shop_order.getFac_order_no());
			json_order.setData(shop_order.getData());
			json_order.setGoodsID(shop_order.getShopgoods().getGoodsID());
			json_order.setGoodsname(shop_order.getShopgoods().getGoodsname());
			json_order.setGoodstype(shop_order.getShopgoods().getGoodstype());
			json_order.setDrivername(shop_order.getShopdriver().getName());
			json_order.setDriverphone(shop_order.getShopdriver().getPhone());
			json_order.setTracknumber(shop_order.getShopdriver().getTracknumber());
			json_orders.add(json_order);
		}
		return json_orders;
	}

	@Override
	public List<Json_Order> update_get_shoporderByNO(String fac_id, String ordernumber) {
		// TODO Auto-generated method stub
		List<Json_Order> json_orders=new ArrayList<>();
		Query querylist=getSession().createQuery("from Shop_Order o where o.status ='0' and  o.ordernumber = '"+ordernumber+"'  and o.doc_factory.id ='"+fac_id+"'");  //带条件的查询语句     
		List<Shop_Order> list_orders=querylist.list();
		for(Shop_Order shop_order:list_orders){
			Json_Order json_order = new Json_Order();
			json_order.setO_id(shop_order.getO_id());
			json_order.setIdnumber(ordernumber);
			json_order.setOrdernumber(shop_order.getOrdernumber());
			json_order.setFac_order_no(shop_order.getFac_order_no());
			json_order.setData(shop_order.getData());
			json_order.setGoodsID(shop_order.getShopgoods().getGoodsID());
			json_order.setGoodsname(shop_order.getShopgoods().getGoodsname());
			json_order.setGoodstype(shop_order.getShopgoods().getGoodstype());
			json_order.setDrivername(shop_order.getShopdriver().getName());
			json_order.setDriverphone(shop_order.getShopdriver().getPhone());
			json_order.setTracknumber(shop_order.getShopdriver().getTracknumber());
			json_orders.add(json_order);
		}
		return json_orders;
	}

	@Override
	public List<Shop_Order> findShopOrderByDriverId(Shop_Driver shopdriver) {
		// TODO Auto-generated method stub
		Criteria criteria=getSession().createCriteria(Shop_Order.class).add(Restrictions.eq("shopdriver",shopdriver));
		List<Shop_Order> list=criteria.list();
		return list;
	}
	
}
