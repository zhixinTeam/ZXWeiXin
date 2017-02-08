package com.zhixin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.AppbookDao;
import com.zhixin.model.App_activity;
import com.zhixin.model.App_book;
import com.zhixin.model.PageBean;
@Service(value="appbookDao")
public class AppbookDaoImpl  extends DaoSupportImpl<App_book> implements AppbookDao{

	@Override
	public PageBean findpageappbook(String factoryid, String currentPage) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*10;
		String param ="";
		Query query=getSession().createQuery("from App_book  p where    p.factory.id='"+factoryid+"' ");  //带条件的查询语句  
		int count =query.list().size();
		  query.setFirstResult(firdata);  
		  query.setMaxResults(10); 
		  List<App_activity> list=query.list();
		  while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =(curpage-1)*10;
			   query=getSession().createQuery("from App_book  where    p.factory.id='"+factoryid+"' ");  //带条件的查询语句  
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

}
