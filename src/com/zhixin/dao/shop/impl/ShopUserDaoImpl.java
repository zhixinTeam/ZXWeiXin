package com.zhixin.dao.shop.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import com.zhixin.entity.Json_Driver;
import com.zhixin.entity.Json_Goods;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.ShopLink_User_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_User;


@Service(value="shopuserDao")
public class ShopUserDaoImpl extends DaoSupportImpl<Shop_User> implements ShopUserDao{

	@Override
	public Shop_User findShopUserByNameAndPwd(String passwd) {
		// TODO Auto-generated method stub
		return (Shop_User) getSession().createQuery(//
				"from Shop_User u where u.password=?")//
				.setParameter(0, passwd)//
				.uniqueResult();
	}

	@Override
	public Shop_User findShopUserByPhoneandFactoryid(String phone,String factoryid) {
		// TODO Auto-generated method stub
		return (Shop_User) getSession().createQuery(//
				"from Shop_User u  where  u.doc_factory.id=? and u.username=? ")//
				.setParameter(0, factoryid)
				.setParameter(1, phone).uniqueResult();//
		
				
	}

	@Override
	public void saveShopUserandClient(List<Shop_Client> listjb, Shop_User shopuser) {
		// TODO Auto-generated method stub
		this.getSession().save(shopuser);
		for (Shop_Client shopclient:listjb){
			this.getSession().save(shopclient);
		}
	}

	@Override
	public void saveShopClient(List<Shop_Client> listjb) {
		// TODO Auto-generated method stub
		for (Shop_Client shopclient:listjb){
			this.getSession().save(shopclient);
		}
	}

	@Override
	public void deleteByClientID(List<String> listdel) {
		// TODO Auto-generated method stub
		String hql="delete Shop_Client  as c where c.clientnumber=?";
		Query querydel =null;
		for(String clientid:listdel){
			   querydel=getSession().createQuery(hql);
			   querydel.setString(0,clientid);
			   querydel.executeUpdate();
		}
	}

	@Override
	public void updateShopClients(List<Shop_Client> listjb) {
		// TODO Auto-generated method stub
		Shop_Client shopclient = null;
		for(Shop_Client client:listjb){
			shopclient=(Shop_Client) this.getSession().createQuery(//
					"from Shop_Client g where g.clientnumber=?")//
					.setParameter(0, client.getClientnumber())//
					.uniqueResult();
			shopclient.setClientnumber(client.getClientnumber());
			shopclient.setCash(client.getCash());
			this.getSession().save(shopclient);
		}
	}

	@Override
	public Shop_User findShopUserByID(String shopuserid) {
		// TODO Auto-generated method stub
		return (Shop_User) getSession().createQuery(//
				"from Shop_User u where u.u_id=?")//
				.setParameter(0, shopuserid)//
				.uniqueResult();
	}

	@Override
	public List<Shop_Driver> findShopDriver(Set<ShopLink_User_Driver> linkuser_driverSet) {
		// TODO Auto-generated method stub
		String queryStr ="from ShopLink_User_Driver l where l.l_id=?";
		Query queryObj =this.getSession().createQuery(queryStr);
		List listdrivers = new ArrayList<Shop_Driver>();
		ShopLink_User_Driver linkuserdriver =null;
		for( ShopLink_User_Driver link_user_driver:linkuser_driverSet){
			linkuserdriver=	(ShopLink_User_Driver) queryObj.setParameter(0, link_user_driver.getL_id()).uniqueResult();
			listdrivers.add(linkuserdriver.getShopdriver());
		}
		return listdrivers;
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
	public Shop_User findShopUserByUsername(String username,String factoryid) {
		// TODO Auto-generated method stub
		return (Shop_User) getSession().createQuery(//
				"from Shop_User u where u.username=?and u.doc_factory.id=? ")//
				.setParameter(0, username)//
				.setParameter(1, factoryid).uniqueResult();
	}

	@Override
	public Set<ShopLink_User_Driver> findShopLink_User_DriverByU_id(Shop_User user) {
		// TODO Auto-generated method stub
		Criteria criteria=getSession().createCriteria(ShopLink_User_Driver.class).add(Restrictions.eq("shopuser", user));
		Set<ShopLink_User_Driver> linkuser_driverSet=new HashSet(criteria.list()); 
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

	

	

}
