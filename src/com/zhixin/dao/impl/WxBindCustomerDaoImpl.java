﻿package com.zhixin.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.WxBindCustomerDao;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Link_BindCustomers_Factorys;
import com.zhixin.model.PageBean;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Sys_Auth;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.Wx_BindUser;
@Service(value="wxbindcustomerDao")
public class WxBindCustomerDaoImpl extends DaoSupportImpl<Wx_BindCustomer> implements WxBindCustomerDao {

	@Override
	public PageBean findpagewxbindcustomer(String factoryid ,String currentPage ,String status,String namepinyin ) {
		// TODO Auto-generated method 
		Query query1=getSession().createQuery("from Link_BindCustomers_Factorys  l where l.factory.id = '"+factoryid+"'");  //带条件的查询语句     
		List<Link_BindCustomers_Factorys> list1=query1.list();
		PageBean pagebean =null;
		List c_id = new ArrayList();
		if(list1.size()>0){
			for(Link_BindCustomers_Factorys link_bind_fac:list1){
				c_id.add(link_bind_fac.getBindcustomer().getId());
			}
		}else{
			pagebean = new PageBean( 1, 10, 0, new ArrayList());
		}
		if(c_id.size()>0){
					
					int curpage =Integer.parseInt(currentPage);
					int firdata =(curpage-1)*10;
					String param ="";
					namepinyin =namepinyin.trim();
					status =status.trim();
					if(! namepinyin.equals(""))
						param+="   namepinyin like '%"+namepinyin+"%'  and";
					if(! status.equals(""))
						param+="   status = '"+status+"'  and";
					Query query=getSession().createQuery("from Wx_BindCustomer   where "+param+" id in(:alist) ");  //带条件的查询语句  
					query.setParameterList("alist", c_id); 
					int count =query.list().size();
					query.setFirstResult(firdata);  
					query.setMaxResults(10);  
					List<Sys_User> list=query.list();
					pagebean = new PageBean( curpage, 10, count, list);
				
				}else{
					pagebean = new PageBean( 1, 10, 0, new ArrayList());
				}
		
		return pagebean;
	}

	@Override
	public List<Doc_Factory> findFactoryByOriginalID(String originalID) {
		// TODO Auto-generated method stub
		String queryString="from Doc_Company    where  originalID=? ";
		Doc_Company  com =(Doc_Company) this.getSession().createQuery(queryString).setParameter(0, originalID).uniqueResult();
		if(com !=null){
			Set<Doc_Factory> setfac =com.getDoc_factorys();
			List listfac =new ArrayList<>(setfac);
			return listfac;
		}else{
			return new ArrayList();
		}
		
	}


	//TODO 关联对象修改
	@Override
	public void save_wxbindUser(Wx_BindCustomer wx_bind ,List <Doc_Factory> listfactorys) {
		// TODO Auto-generated method stub
		//删除关注数据库
	    String openid =	wx_bind.getOpenid();
	    String delsql ="delete from wx_binduser  where openid  =?"; //
		SQLQuery query4 = getSession().createSQLQuery(delsql);
		query4.setParameter(0, openid);
		query4.executeUpdate();
		this.getSession().save(wx_bind);
		for(Doc_Factory factory: listfactorys){
			Link_BindCustomers_Factorys link_bind_fac = new Link_BindCustomers_Factorys();
			link_bind_fac.setBindcustomer(wx_bind);
			link_bind_fac.setIsbind(0);
			link_bind_fac.setFactory(factory);
			this.getSession().merge(link_bind_fac);
		}
		
		
		
	}

	@Override
	public Wx_BindCustomer findFactoryByOpenID(String openid) {
		// TODO Auto-generated method stub
		return (Wx_BindCustomer) getSession().createQuery(//
				"from Wx_BindCustomer f where f.openid=?")//
				.setParameter(0, openid)//
				.uniqueResult();
	}

	@Override
	public Wx_BindCustomer findwxbindcustomerByOpenID(String openid) {
		// TODO Auto-generated method stub
		Wx_BindCustomer wx_bind= (Wx_BindCustomer) getSession().createQuery(//
				"from Wx_BindCustomer f where f.openid=?")//
				.setParameter(0, openid)//
				.uniqueResult();
		if(wx_bind==null)
			wx_bind=new Wx_BindCustomer();
		 return wx_bind ;
	}
	//TODO 关联对象修改
	@Override
	public void update_wxbindUser(Wx_BindCustomer wx_bind ,List <Doc_Factory> listfactorys) {
		// TODO Auto-generated method stub
		String delsql ="delete from link_bindcustomers_factorys   where BindcustomerID  =?"; //
		SQLQuery query4 = getSession().createSQLQuery(delsql);
		query4.setParameter(0, wx_bind.getId());
		query4.executeUpdate();
		getSession().update(wx_bind);
		for(Doc_Factory factory: listfactorys){
			Link_BindCustomers_Factorys link_bind_fac = new Link_BindCustomers_Factorys();
			link_bind_fac.setBindcustomer(wx_bind);
			link_bind_fac.setFactory(factory);
			link_bind_fac.setIsbind(0);
			this.getSession().save(link_bind_fac);
		}
	}

	@Override
	public void update_wxbindUserStatus(Wx_BindCustomer wx_bind) {
		// TODO Auto-generated method stub
		getSession().update(wx_bind);
	}

	//根据工厂id获取一般关注用户
	@Override
	public List<Wx_BindUser> findwxUserByFactoryID(String factoryid) {
		// TODO Auto-generated method stub
		Doc_Factory factory =(Doc_Factory) getSession().createQuery("from Doc_Factory  f where f.id = '"+factoryid+"'").uniqueResult();  //带条件的查询语句     
		
		String originalid =factory.getDoc_company().getOriginalID();
		Query query1=getSession().createQuery("from Wx_BindUser  l where l.originalID = '"+originalid+"'");  //带条件的查询语句     
		List<Wx_BindUser> list1=query1.list();
		return list1;
	}

	@Override
	public List<Wx_BindCustomer> findwxbindcusUserByFactoryID(String factoryid) {
		// TODO Auto-generated method stub
		Query query1=getSession().createQuery("from Link_BindCustomers_Factorys  l where l.factory.id = '"+factoryid+"'");  //带条件的查询语句     
		List<Link_BindCustomers_Factorys> list1=query1.list();
		List<Wx_BindCustomer> list_wxbindcustomers = new ArrayList();
		for(Link_BindCustomers_Factorys linkcus_factory:list1){
			list_wxbindcustomers.add(linkcus_factory.getBindcustomer());
		}
		return list_wxbindcustomers;
	}

	@Override
	public Set<Sys_User> findsysUserByFactoryID(String factoryid) {
		// TODO Auto-generated method stub
		Doc_Factory factory =(Doc_Factory) getSession().createQuery("from Doc_Factory  f where f.id = '"+factoryid+"'").uniqueResult();  //带条件的查询语句     
		
		return factory.getSys_users();
	}

	@Override
	public Wx_BindCustomer findsysUserByFactoryID(String phone, String factoryid) {
		// TODO Auto-generated method stub
		List<Wx_BindCustomer> list_wx_bind=	getSession().createQuery(//
				"from Wx_BindCustomer f where f.phone=?")//
				.setParameter(0, phone).list();//
		Wx_BindCustomer wx_bind = new Wx_BindCustomer ();
		if(list_wx_bind.size()==1){
			wx_bind=	list_wx_bind.get(0);
			System.out.println(wx_bind);
			return wx_bind;
		}
		else if(list_wx_bind.size()>1){
			Query query1=getSession().createQuery("from Link_BindCustomers_Factorys  l where l.factory.id = '"+factoryid+"'");  //带条件的查询语句     
			List<Link_BindCustomers_Factorys> list1=query1.list();
			List<Wx_BindCustomer> list_wxbindcustomers = new ArrayList();
			for(Link_BindCustomers_Factorys linkcus_factory:list1){
				//list_wxbindcustomers.add(linkcus_factory.getBindcustomer());
				for(Wx_BindCustomer wx_bind_new:list_wx_bind){
					if(wx_bind_new.getId().equals(linkcus_factory.getBindcustomer().getId()))
						return wx_bind_new;
				}
			}
		}
			
		return new Wx_BindCustomer();
	}

	@Override
	public void saveShopClient(List<Shop_Client> listjb) {
		// TODO Auto-generated method stub
		
		for (Shop_Client shopclient:listjb){
			System.out.println(shopclient);
			this.getSession().save(shopclient);
		}
		

	}

	@Override
	public Shop_Client findClientByFactory(String clientnumber,String factoryID) {
		// TODO Auto-generated method stub
		System.out.println("==========findClientByFactory===========");
		
		String queryStr ="from Shop_Client c left join c.doc_factory as u where u.id=? and c.clientnumber=?";
		Query queryObj =this.getSession().createQuery(queryStr);
		queryObj.setParameter(0, factoryID);
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
	public Wx_BindCustomer findByUSername(String newusername) {
		// TODO Auto-generated method stub
		return  (Wx_BindCustomer) getSession().createQuery(//
				"from Wx_BindCustomer f where f.namepinyin=?")//
				.setParameter(0, newusername)//
				.uniqueResult();
	}

	@Override
	public List<Shop_Client> findClientByCustomerId(String customerid, String factoryid) {
		// TODO Auto-generated method stub
		Query query1=getSession().createQuery("from Shop_Client  c where c.doc_factory.id = '"+factoryid+"' and c.bindcustmoer.id ='"+customerid+"'");  //带条件的查询语句     
		List<Shop_Client> list1=query1.list();
		return list1;
	}

	@Override
	public Wx_BindCustomer findFactoryBycustomerID(String customerID) {
		// TODO Auto-generated method stub
		return  (Wx_BindCustomer) getSession().createQuery(//
				"from Wx_BindCustomer f where f.id=?")//
				.setParameter(0, customerID)//
				.uniqueResult();
	}

	@Override
	public Set<Wx_BindCustomer> findBindCustomerFactoryID(String tracknumber, String factoryid) {
		// TODO Auto-generated method stub
		List<Shop_Driver> list_drivers =getSession().createQuery(//
				"from Shop_Driver f where f.tracknumber=?")//
				.setParameter(0, tracknumber).list();//
		Set<Wx_BindCustomer> list_wxbindcustomers = new HashSet();
		for(Shop_Driver driver:list_drivers){
			Query query1=getSession().createQuery("from Link_BindCustomers_Factorys  l where l.factory.id = '"+factoryid+"' and l.bindcustomer.phone='"+driver.getPhone()+"'");  //带条件的查询语句     
			List<Link_BindCustomers_Factorys> list1=query1.list();
			for(Link_BindCustomers_Factorys linkcus_factory:list1){
				list_wxbindcustomers.add(linkcus_factory.getBindcustomer());
			}
		}
		return list_wxbindcustomers;
		
	}


	@Override
	public void saveCustomer(Wx_BindCustomer wx_bind) {
		// TODO Auto-generated method stub
		this.getSession().save(wx_bind);

	}	
	@Override
	public void updateLinkFactoryBycustomer(Doc_Factory factory, Wx_BindCustomer wx_BindCustomer) {
		// TODO Auto-generated method stub
		Link_BindCustomers_Factorys link_bind_factory=(Link_BindCustomers_Factorys) getSession().createQuery("from Link_BindCustomers_Factorys  l where l.factory.id = '"+factory.getId()+"' and l.bindcustomer.id='"+wx_BindCustomer.getId()+"'").uniqueResult();  //带条件的查询语句     
		if(link_bind_factory==null){
			Link_BindCustomers_Factorys link=new Link_BindCustomers_Factorys();
			link.setBindcustomer(wx_BindCustomer);
			link.setFactory(factory);
			this.getSession().save(link);
		}
			
	}

	
	
	
}
