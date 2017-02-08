package com.zhixin.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.AppactivityDao;
import com.zhixin.model.App_activity;
import com.zhixin.model.App_pack;
import com.zhixin.model.PageBean;


@Service(value="appactivitydao") 
public class AppactivityDaoImpl extends DaoSupportImpl<App_activity> implements AppactivityDao{

	@Override
	public PageBean findpageactivity(String factoryid, String currentPage) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*10;
		String param ="";
		Query query=getSession().createQuery("from App_activity  p where    p.factory.id='"+factoryid+"' ");  //带条件的查询语句  
		int count =query.list().size();
		  query.setFirstResult(firdata);  
		  query.setMaxResults(10); 
		  List<App_activity> list=query.list();
		  while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =(curpage-1)*10;
			   query=getSession().createQuery("from App_picture p where    p.factory.id='"+factoryid+"' ");  //带条件的查询语句  
			   count =query.list().size();
			   query.setFirstResult(firdata);  
			   query.setMaxResults(10);  
			   list=query.list();
			   if(list.size()>0)
				   break;
		  }
		  
		  PageBean pagebean = new PageBean( curpage, 10, count, list);
			return pagebean;
	}

	@Override
	public List<App_activity> findAppactivity_list(String factory_id) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("from App_activity  p where    p.factory.id='"+factory_id+"' ");  //带条件的查询语句  
		List<App_activity> list=query.list();
		return list;
	}

	@Override
	public App_activity findAppactivityById(String id) {
		// TODO Auto-generated method stub
		Criteria criteria=getSession().createCriteria(App_activity.class).add(Restrictions.eq("id",id ));
		App_activity Appactivity=(App_activity) criteria.uniqueResult();
		return Appactivity;
	}

	@Override
	public void updateApp_activity(App_activity appactivity) {
		// TODO Auto-generated method stub
		getSession().update(appactivity);
		
	}
	
	

}
