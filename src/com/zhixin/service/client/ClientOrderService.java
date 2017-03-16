package com.zhixin.service.client;

import java.util.List;

import com.zhixin.entity.Json_ShopOrder;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Order;

public interface ClientOrderService {

	/**
	 * 通过userid分页查询订单信息
	 * @param currentPage
	 * @param u_id
	 * @return
	 */
	List<Json_ShopOrder> listOrders(String currentPage, String id);
	
	/**
	 * 通过订单的id查询订单详细信息
	 * @param o_id
	 * @return
	 */
	Shop_Order oneOrder(String o_id);
	
	/**
	 * 修改订单状态
	 */
	void updateShopOrder(Shop_Order shop_Order);

	List<Shop_Driver> findDriversByCustomerId(String customerid);
	
	/**
	 * 通过用户id分页查询订单信息 一页10条数据
	 * @param currentPage
	 * @param u_id
	 * @return
	 */
	List<Json_ShopOrder> list_shop_Orders(String currentPage, String id);
	
	List<Shop_Order> list_shop_Order(String currentPage, String id);
}
