package com.zhixin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.ApppackDao;
import com.zhixin.entity.Json_AppPicture;
import com.zhixin.entity.Json_Apppack;
import com.zhixin.model.App_activity;
import com.zhixin.model.App_pack;
import com.zhixin.model.App_picture;
import com.zhixin.model.PageBean;

@Service(value="apppackDao")
public class ApppackDaoImpl extends DaoSupportImpl<App_pack> implements ApppackDao {

	@Override
	public PageBean findpageapppack(String factoryid, String currentPage) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*10;
		String param ="";
		Query query=getSession().createQuery("from App_pack  p where    p.factory.id='"+factoryid+"' ");  //带条件的查询语句  
		int count =query.list().size();
		  query.setFirstResult(firdata);  
		  query.setMaxResults(10); 
		  List<App_activity> list=query.list();
		  while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =(curpage-1)*10;
			   query=getSession().createQuery("from App_pack p where    p.factory.id='"+factoryid+"' ");  //带条件的查询语句  
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
	public App_pack findApp_packById(String id) {
		// TODO Auto-generated method stub/
		Criteria criteria=getSession().createCriteria(App_pack.class).add(Restrictions.ge("id",id ));
		App_pack app_pack=(App_pack) criteria.uniqueResult();
		return app_pack;
	}

	@Override
	public void updateApp_pack(App_pack apppack) {
		// TODO Auto-generated method stub
		getSession().update(apppack);
	}

	@Override
	public List<Json_Apppack> findAppPackByFactoryid(String factoryId,String currentPage) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*2;
		String param ="";
		Query query=getSession().createQuery("from App_pack  p where    p.factory.id='"+factoryId+"' ");  //带条件的查询语句  
		int count =query.list().size();
		  query.setFirstResult(firdata);  
		  query.setMaxResults(2); 
		  List<App_pack> list=query.list();
		  while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =(curpage-1)*1;
			   query=getSession().createQuery("from App_pack p where    p.factory.id='"+factoryId+"' ");  //带条件的查询语句  
			   count =query.list().size();
			   query.setFirstResult(firdata);  
			   query.setMaxResults(2);  
			   list=query.list();
			   if(list.size()>0)
				   break;
		  }
		PageBean pagebean = new PageBean( curpage, 2, count, list);		
		List<App_pack> listpack=pagebean.getRecordList();
		List<Json_Apppack> list_json = new ArrayList<>();
		for(App_pack pack: listpack){
			Json_Apppack json_app =new Json_Apppack();
			json_app.setId(pack.getId());
			json_app.setPath(pack.getPath());
			json_app.setPicname(pack.getPicname());
			json_app.setContext(pack.getContext());
			json_app.setMiaosu(pack.getMiaosu());
			json_app.setPageCount(pagebean.getPageCount());
			list_json.add(json_app);
		}
		return list_json;
	}

	@Override
	public Json_Apppack findAppPackByid(String id) {
		// TODO Auto-generated method stub
		Query	query =getSession().createQuery("from App_pack p where   p.id='"+id+"' ");
		App_pack pack=(App_pack) query.uniqueResult();
		Json_Apppack jsonpack=new Json_Apppack();
		jsonpack.setId(pack.getId());
		jsonpack.setPath(pack.getPath());
		jsonpack.setPicname(pack.getPicname());
		jsonpack.setContext(pack.getContext());
		jsonpack.setMiaosu(pack.getMiaosu());
		return jsonpack;
	}

	@Override
	public List<Json_Apppack> findAppPackNewsBack(String currentPage,String factoryId) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =0;
		String param ="";
		Query query=getSession().createQuery("from App_pack  p where    p.factory.id='"+factoryId+"' ");  //带条件的查询语句  
		int count =query.list().size();
		  query.setFirstResult(firdata);  
		  query.setMaxResults(2*curpage); 
		  List<App_pack> list=query.list();
		  while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =0;
			   query=getSession().createQuery("from App_pack p where    p.factory.id='"+factoryId+"' ");  //带条件的查询语句  
			   count =query.list().size();
			   query.setFirstResult(firdata);  
			   query.setMaxResults(2*curpage);  
			   list=query.list();
			   if(list.size()>0)
				   break;
		  }
		PageBean pagebean = new PageBean( curpage, 2, count, list);		
		List<App_pack> listpack=pagebean.getRecordList();
		List<Json_Apppack> list_json = new ArrayList<>();
		for(App_pack pack: listpack){
			Json_Apppack json_app =new Json_Apppack();
			json_app.setId(pack.getId());
			json_app.setPath(pack.getPath());
			json_app.setPicname(pack.getPicname());
			json_app.setContext(pack.getContext());
			json_app.setMiaosu(pack.getMiaosu());
			json_app.setPageCount(pagebean.getPageCount());
			list_json.add(json_app);
		}
		return list_json;
	}

	

}
