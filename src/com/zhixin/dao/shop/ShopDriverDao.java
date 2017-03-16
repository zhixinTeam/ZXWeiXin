package com.zhixin.dao.shop;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.entity.Json_Driver;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;

public interface ShopDriverDao extends DaoSupport<Shop_Driver> {

	Shop_Driver saveDriver(Shop_Driver driver, String shopuserid);

	Shop_Driver findDriverByCardID(String idnumber);
	
	void deleteShoperDriver(String did,Shop_Driver driver);
	
	Json_Driver findJsonDriverByD_ID(String d_id);
	
	Shop_Driver findDriverByD_ID(String d_id);
	
	void updateShoperDriver(Shop_Driver driver);
	
}
