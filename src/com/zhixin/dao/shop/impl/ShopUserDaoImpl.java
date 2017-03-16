package com.zhixin.dao.shop.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.shop.ShopUserDao;
import com.zhixin.entity.Json_Client;
import com.zhixin.entity.Json_Doc_Company;
import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Goods;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.ShopLink_Customer_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Wx_BindCustomer;


@Service(value="shopuserDao")
public class ShopUserDaoImpl extends DaoSupportImpl<Wx_BindCustomer> implements ShopUserDao{

	@Override
	public Wx_BindCustomer getWx_BindCustomerByNameAndPWd(String passwd) {
		// TODO Auto-generated method stub
		return (Wx_BindCustomer) getSession().createQuery(//
				"from Wx_BindCustomer u where u.password=?")//
				.setParameter(0, passwd)//
				.uniqueResult();
	}


	

	@Override
	public List<Json_Goods> findShopGoodsByfacid(String factoryid) {
		// TODO Auto-generated method stub
		String queryStr ="from Shop_Goods g  left join g.doc_factory as f where f.id=? ";
		Query queryObj =this.getSession().createQuery(queryStr);
		queryObj.setParameter(0, factoryid);
		List list =queryObj.list();                    //执行查询   
        Iterator it=list.iterator();   
        List<Json_Goods> goodlist =new ArrayList();
        
        while(it.hasNext()){   
	       	Object[] obj=(Object[])it.next();  
	       	Json_Goods jsongoods = new Json_Goods();
	       	Shop_Goods goods =(Shop_Goods) obj[0]; 
	       	jsongoods.setG_id(goods.getG_id());
	       	jsongoods.setGoodsID(goods.getGoodsID());
	       	jsongoods.setGoodsname(goods.getGoodsname());
	       	jsongoods.setTypename(goods.getGoodstype());
	       	goodlist.add(jsongoods);
        }
		return goodlist;
	}

	
	@Override
	public List<Shop_Client> findShopClients(String u_id) {
		// TODO Auto-generated method stub
		String queryStr ="from Shop_Client c left join c.shopuser as u where u.u_id=?";
		Query queryObj =this.getSession().createQuery(queryStr);
		queryObj.setParameter(0, u_id);
		List list =queryObj.list();                    //执行查询   
         Iterator it=list.iterator();   
         List<Shop_Client> clientlist =new ArrayList();
         while(it.hasNext()){   
        	 Object[] obj=(Object[])it.next();  
        	 Shop_Client client =(Shop_Client) obj[0]; 
        	 clientlist.add(client);
         }
		
		return clientlist;
	}
	
	@Override
	public List<Json_Client> findShopClients(String shopuserid, String factoryid) {
		// TODO Auto-generated method stub
		
		String queryStr ="from Shop_Client c left join c.shopuser as u where u.u_id=?";
		Query queryObj =this.getSession().createQuery(queryStr);
		queryObj.setParameter(0, shopuserid);
		List list =queryObj.list();                    //执行查询   
        Iterator it=list.iterator();   
        List<Json_Client> clientlist =new ArrayList();
        ;
        while(it.hasNext()){   
        	 Object[] obj=(Object[])it.next();  
        	 Shop_Client client =(Shop_Client) obj[0]; 
        	 Json_Client jsonclient = new Json_Client();
        	/* if(client.getDoc_factory() !=null){
        		 if(factoryid.equals(client.getDoc_factory().getId())){*/
            jsonclient.setC_id(client.getC_id());
            jsonclient.setCash(client.getCash());
            jsonclient.setClientnumber(client.getClientnumber());
            clientlist.add(jsonclient);
            		 
            	/* }
        	 }*/
        	
        		 
         }
		return clientlist;
	}

	@Override
	public Json_Driver findShopDriverByID(String driverid) {
		// TODO Auto-generated method stub
		Shop_Driver driver = (Shop_Driver) getSession().createQuery(//
				"from Shop_Driver d where d.d_id=?")//
				.setParameter(0, driverid)//
				.uniqueResult();
		Json_Driver jsondriver = new Json_Driver();
		jsondriver.setD_id(driver.getD_id());
		jsondriver.setIdnumber(driver.getIdnumber());
		jsondriver.setName(driver.getName());
		jsondriver.setTracknumber(driver.getTracknumber());
		jsondriver.setPhone(driver.getPhone());
		return jsondriver;
	}

	@Override
	public void updateShopUserPwd(String username, String passwd,String factoryid) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("update Shop_User  set password ='"+passwd+"' where username =? and factoryid=?");
		query.setParameter(0, username).setParameter(1, factoryid);
		query.executeUpdate();
	}

	@Override
	public Set<ShopLink_Customer_Driver> findShopLink_Customer_DriverByU_id(Wx_BindCustomer customer) {
		// TODO Auto-generated method stub
		Criteria criteria=getSession().createCriteria(ShopLink_Customer_Driver.class).add(Restrictions.eq("wx_bindCustomer", customer));
		Set<ShopLink_Customer_Driver> linkuser_driverSet=new HashSet(criteria.list()); 
		return linkuser_driverSet;
	}

	@Override
	public Shop_Client findShop_Clinet(String clientnumber, String u_id) {
		// TODO Auto-generated method stub
		String queryStr ="from Shop_Client c left join c.shopuser as u where u.u_id=? and c.clientnumber=?";
		Query queryObj =this.getSession().createQuery(queryStr);
		queryObj.setParameter(0, u_id);
		queryObj.setParameter(1, clientnumber);
		List list =queryObj.list();                    //执行查询   
        Iterator it=list.iterator();  
        while(it.hasNext()){   
       	   Object[] obj=(Object[])it.next();  
       	   Shop_Client client =(Shop_Client) obj[0]; 
       	   return client;
        }
		return null;                    //执行查询   
	}

	@Override
	public void updateWx_BindCustomerPassword(Wx_BindCustomer wx_BindCustomer) {
		// TODO Auto-generated method stub
		getSession().update(wx_BindCustomer);
	}

	@Override
	public Wx_BindCustomer getWx_BindCustomerByid(String wx_BindCustomer_id) {
		// TODO Auto-generated method stub
		return (Wx_BindCustomer) getSession().createQuery(//
				"from Wx_BindCustomer u where u.id=?")//
				.setParameter(0, wx_BindCustomer_id)//
				.uniqueResult();
		
	}

	@Override
	public List<Shop_Driver> findShopDrivers(Set<ShopLink_Customer_Driver> shopLink_Customer_Driver) {
		String queryStr ="from ShopLink_Customer_Driver l where l.l_id=?";
		Query queryObj =this.getSession().createQuery(queryStr);
		List listdrivers = new ArrayList<Shop_Driver>();
		ShopLink_Customer_Driver linkuserdriver =null;
		for(ShopLink_Customer_Driver link_user_driver:shopLink_Customer_Driver){
			linkuserdriver=	(ShopLink_Customer_Driver) queryObj.setParameter(0, link_user_driver.getL_id()).uniqueResult();
		   //	linkuserdriver.getShopdriver();
			listdrivers.add(linkuserdriver.getShopdriver());
		}
		return listdrivers;
	}



	@Override
	public void updateshopDriver(Shop_Driver driver) {
		// TODO Auto-generated method stub
		getSession().update(driver);
	}


	@Override
	public Json_Driver findJsonDriverByD_ID(String d_id) {
		// TODO Auto-generated method stub
		Shop_Driver driver = (Shop_Driver) getSession().createQuery(//
				"from Shop_Driver d where d.d_id=?")//
				.setParameter(0, d_id)//
				.uniqueResult();
		Json_Driver jsondriver = new Json_Driver();
		jsondriver.setD_id(driver.getD_id());
		jsondriver.setIdnumber(driver.getIdnumber());
		jsondriver.setName(driver.getName());
		jsondriver.setTracknumber(driver.getTracknumber());
		jsondriver.setPhone(driver.getPhone());
		return jsondriver;
	}

	@Override
	public Shop_Driver findDriverByD_ID(String d_id) {
		// TODO Auto-generated method stub
		return (Shop_Driver) getSession().createQuery(//
				"from Shop_Driver u where u.d_id=?")//
				.setParameter(0, d_id)//
				.uniqueResult();
	}

	@Override
	public void updateShoperDriver(Shop_Driver driver) {
		// TODO Auto-generated method stub
		getSession().update(driver);
	}

	@Override
	public List<Shop_Order> findShopOrderByDriverId(Shop_Driver driver) {
		Criteria criteria=getSession().createCriteria(Shop_Order.class).add(Restrictions.eq("shopdriver",driver));
		List<Shop_Order> list=criteria.list();
		return list;
	}

	@Override
	public void updateLastLogin(Wx_BindCustomer wx_BindCustomer) {
		// TODO Auto-generated method stub
		getSession().update(wx_BindCustomer);
	}


	@Override
	public Shop_Client findShopClientByID(String c_id) {
		// TODO Auto-generated method stub
		return (Shop_Client) getSession().createQuery(//
				"from Shop_Client u where u.c_id=?")//
				.setParameter(0, c_id)//
				.uniqueResult();
	}




	@Override
	public Json_Doc_Company findDoc_Company(String c_id) {
		// TODO Auto-generated method stub
		
		return null;
	}




	@Override
	public Wx_BindCustomer getWx_BindCustomerPWd(String pc_passwd) {
		// TODO Auto-generated method stub
		return (Wx_BindCustomer) getSession().createQuery(//
				"from Wx_BindCustomer u where u.pc_password=?")//
				.setParameter(0, pc_passwd)//
				.uniqueResult();
	}





	

}
