package com.zhixin.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.WeixinEventDao;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.Wx_BindUser;
import com.zhixin.tools.TimestampUtil;

import weixin.popular.bean.message.EventMessage;


@Repository(value="weixineventdao")
public class WeixinEventDaoImpl  extends DaoSupportImpl<Wx_BindCustomer> implements WeixinEventDao{

	
	
	
	
	//关注公共账号插入数据库
	@Override
	public void save_subfactory(EventMessage eventMessage) {
		
		
		Wx_BindUser wx_bindUser = new Wx_BindUser();
		wx_bindUser.setOpenid(eventMessage.getFromUserName());
		wx_bindUser.setBinddate(TimestampUtil.getnowtime());
		wx_bindUser.setOriginalID(eventMessage.getToUserName());
		this.getSession().save(wx_bindUser);
		
		
	}

	//删除公共号关注情况
	@Override
	public void unsubfactory(EventMessage eventMessage) {
		//删除关注表
		
		String  openid = eventMessage.getFromUserName();
		String hql = "delete from Wx_BindUser where openid='"+openid+"'";
		Query query =this.getSession().createQuery(hql);
		query.executeUpdate();//
		//删除关联
		String queryString="from Wx_BindCustomer    where  openid=? ";
		Wx_BindCustomer  bind_customer =(Wx_BindCustomer) this.getSession().createQuery(queryString).setParameter(0, openid).uniqueResult();
		//bind_customer.getBindcustomer_factorys();
		String delsql ="delete from Link_BindCustomers_Factorys   where BindcustomerID  ='"+bind_customer.getId()+"'"; //
		getSession().createQuery(delsql).executeUpdate();
		//删除客户表数据
		String delsqlwxcustomer ="delete from Wx_BindCustomer where openid='"+openid+"'";
		this.getSession().createQuery(delsqlwxcustomer).executeUpdate();
		
		
	}

	

}
