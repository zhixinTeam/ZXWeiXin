package com.zhixin.dao.shop;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.entity.Json_Order;
import com.zhixin.model.PageBean;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Order;

public interface ShopOrderDao extends DaoSupport<Shop_Order>{

	PageBean listOrders(String currentPage, String u_id);

	
	void saveShopOrder(String o_id, String factoryid,String c_id,String g_id);


	void updateorderstatus(String orderid);


	List<Json_Order> update_get_shoporders(String fac_id, String idnumber);


	List<Json_Order> update_get_shoporderByNO(String fac_id, String ordernumber);
	
	/**
	 * 通过司机id查询司机的货单信息
	 * @param d_id
	 * @return
	 */
	List<Shop_Order> findShopOrderByDriverId(Shop_Driver shopdriver);

}
