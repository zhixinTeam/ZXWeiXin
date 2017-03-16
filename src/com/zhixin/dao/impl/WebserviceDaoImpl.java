package com.zhixin.dao.impl;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.WebserviceDao;
import com.zhixin.model.Link_BindCustomers_Factorys;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Eventmsg;
@Repository(value="webservicedao")
public class WebserviceDaoImpl extends DaoSupportImpl<X_Eventmsg> implements WebserviceDao {

	@Override
	public List<Wx_BindCustomer> findCustomerList(String fac_id) {
		// TODO Auto-generated method stub
		
		Query query1=getSession().createQuery("from Link_BindCustomers_Factorys  l where l.factory.id = '"+fac_id+"' ");  //带条件的查询语句     
		List<Link_BindCustomers_Factorys> list1=query1.list();
		List<Wx_BindCustomer> list = new ArrayList();
		if(list1.size()>0){
			for(Link_BindCustomers_Factorys link_bind_fac:list1){
				list.add(link_bind_fac.getBindcustomer());
			}
		}
		
		return list;
		

	}

	@Override
	public void updateLinkfac_customer(String fac_id, String bindcustomerid, int is_bind) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("update Link_BindCustomers_Factorys   set isbind  ='"+is_bind+"'  where factory.id =? and bindcustomer.id=? ");
		query.setParameter(0, fac_id);
		query.setParameter(1, bindcustomerid);
		query.executeUpdate();
	}

	@Override
	public void updatecomplete_shoporders(String ordernumber, int status) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("update Shop_Order   set Status  ='"+status+"'  where ordernumber=? ");
		query.setParameter(0, ordernumber);
		query.executeUpdate();
	}

	/*@Override
	public List<X_Eventmsg> findMsg_IsNoSend() {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String sql ="select c.Openid,f.OrgCode,x.Content,x.issend from wx_event_msg x ,doc_factory f  ,wx_bindcustomer c ,doc_company d where x.SendUser =f.OrgCode  and x.ReviceUser =c.SuserNumber and x.issend != ? GROUP BY Openid ,Content,OrgCode"; //删除 菜单角色关联
		SQLQuery query4 = getSession().createSQLQuery(sql);
		List<Map<String, Object>> listmap=	query4.list();
		
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		List<Map<String, Object>> listmap=null;
		try {
			
			listmap =jdbcUtils.findModeResult(sql, params);
			jdbcUtils.releaseConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return new ArrayList();
	}
*/
	
}
