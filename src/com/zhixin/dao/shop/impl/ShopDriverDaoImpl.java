package com.zhixin.dao.shop.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.shop.ShopDriverDao;
import com.zhixin.entity.Json_Driver;
import com.zhixin.model.ShopLink_Customer_Driver;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Wx_BindCustomer;
@Service(value="shopdriverDao")
public class ShopDriverDaoImpl extends DaoSupportImpl<Shop_Driver> implements ShopDriverDao{

	@Override
	public Shop_Driver saveDriver(Shop_Driver driver, String wx_BindCustomerid) {
		// TODO Auto-generated method stub
		this.getSession().save(driver);
		ShopLink_Customer_Driver link_customer_driver = new ShopLink_Customer_Driver();
		Wx_BindCustomer wx_BindCustomer=	(Wx_BindCustomer) getSession().createQuery(//
				"from Wx_BindCustomer u where u.id=?")//
				.setParameter(0, wx_BindCustomerid)//
				.uniqueResult();
		link_customer_driver.setWx_bindCustomer(wx_BindCustomer);
		link_customer_driver.setShopdriver(driver);
		this.getSession().save(link_customer_driver);
		return driver;
	}

	@Override
	public Shop_Driver findDriverByCardID(String idnumber) {
		// TODO Auto-generated method stub
		  Query query=getSession().createQuery(//
				"from Shop_Driver d where d.idnumber=?")//
				.setParameter(0, idnumber);//
		  List<Shop_Driver> list=query.list();
		 if(list.size()>0)
			return list.get(0);
		 else 
			return new Shop_Driver();
			 
			 
	}

	@Override
	public void deleteShoperDriver(String did,Shop_Driver driver) {
		// TODO Auto-generated method stub
		String hql = "delete ShopLink_Customer_Driver where DriverID ='"+did+"'";
		Query query=getSession().createQuery(hql);
		query.executeUpdate();
		getSession().delete(driver);
	}

	@Override
	public Json_Driver findJsonDriverByD_ID(String d_id) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(//
				"from Shop_Driver d where d.d_id=?")//
				.setParameter(0, d_id);//
		Shop_Driver driver=(Shop_Driver) query.uniqueResult();
		Json_Driver json_driver=new Json_Driver();
		json_driver.setD_id(driver.getD_id());
		json_driver.setIdnumber(driver.getIdnumber());
		json_driver.setName(driver.getName());
		json_driver.setPhone(driver.getPhone());
		json_driver.setTracknumber(driver.getTracknumber());
		return json_driver;
	}

	@Override
	public void updateShoperDriver(Shop_Driver driver) {
		// TODO Auto-generated method stub
		getSession().update(driver);
	}

	@Override
	public Shop_Driver findDriverByD_ID(String d_id) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(//
				"from Shop_Driver d where d.d_id=?")//
				.setParameter(0, d_id);//
		Shop_Driver driver=(Shop_Driver) query.uniqueResult();
		return driver;
	}

}
