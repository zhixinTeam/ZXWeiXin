package com.zhixin.dao.client.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.client.ClientOrderDao;
import com.zhixin.entity.Json_ShopOrder;
import com.zhixin.model.PageBean;
import com.zhixin.model.Shop_Order;

@Service(value="clientOrderDao") 
public class ClientOrderDaoImpl extends DaoSupportImpl<Shop_Order> implements ClientOrderDao{

	@Override
	public List<Json_ShopOrder> listOrders(String currentPage, String u_id) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*1;
		Query query=getSession().createQuery("from Shop_Order o where  o.shopuser.u_id='"+u_id+"' order by o.status asc,o.orderdate desc ");  //带条件的查询语句  
		int count =query.list().size();
		query.setFirstResult(firdata);  
		query.setMaxResults(1);  
		List<Shop_Order> list=query.list();
		while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =(curpage-1)*1;
			   query=getSession().createQuery("from Shop_Order o where  o.shopuser.u_id='"+u_id+"' order by o.status asc,o.orderdate desc");  //带条件的查询语句  
			   count =query.list().size();
			   query.setFirstResult(firdata);  
			   query.setMaxResults(1);  
			   list=query.list();
			   if(list.size()>0)
				   break;
		  }
		 PageBean pagebean = new PageBean( curpage, 1, count, list);
		 List<Shop_Order> orderlist=pagebean.getRecordList();
		 List<Json_ShopOrder> json_orderlist=new ArrayList<>();
		 for (Shop_Order shop_Order : orderlist) {
			 Json_ShopOrder json_shopOrder=new Json_ShopOrder();
			 json_shopOrder.setO_id(shop_Order.getO_id());
			 json_shopOrder.setOrdernumber(shop_Order.getOrdernumber());
			 json_shopOrder.setOrderdate(shop_Order.getOrderdate());
			 json_shopOrder.setDoc_factoryname(shop_Order.getDoc_factory().getFactoryname());
			 json_shopOrder.setData(shop_Order.getData());
			 json_shopOrder.setStatus(shop_Order.getStatus());
			 json_shopOrder.setIdnumber(shop_Order.getIdnumber());
			 json_shopOrder.setThdate(shop_Order.getThdate());
			 json_shopOrder.setShopgoodsname(shop_Order.getShopgoods().getGoodsname());
			 json_shopOrder.setShopdrivername(shop_Order.getShopdriver().getName());
			 json_shopOrder.setDriverphonenumber(shop_Order.getShopdriver().getPhone());
			 json_shopOrder.setFac_order_no(shop_Order.getFac_order_no());
			 json_shopOrder.setPageCount(pagebean.getPageCount());
			 json_orderlist.add(json_shopOrder);
		}
		 
		
		return json_orderlist;
	}

	@Override
	public Shop_Order oneOrder(String o_id) {
		// TODO Auto-generated method stub
		Criteria criteria=getSession().createCriteria(Shop_Order.class).add(Restrictions.ge("id",o_id ));
		Shop_Order shop_order=(Shop_Order) criteria.uniqueResult();
		return shop_order;
	}

	/*@Override
	public void updateShopOrder(Shop_Order shop_Order) {
		// TODO Auto-generated method stub
		getSession().update(shop_Order);
	}*/

}
