package com.zhixin.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.ApppictureDao;
import com.zhixin.entity.Json_AppPicture;
import com.zhixin.entity.Json_Goods;
import com.zhixin.model.App_activity;
import com.zhixin.model.App_pack;
import com.zhixin.model.App_picture;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Link_BindCustomers_Factorys;
import com.zhixin.model.PageBean;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Sys_Picture;
import com.zhixin.model.Sys_User;
@Service(value="apppictureDao")
public class ApppictureDaoImpl extends DaoSupportImpl<App_picture> implements ApppictureDao{

	@Override
	public PageBean findAppPictures(String currentPage, String factoryid) {
		// TODO Auto-generated method stub
		
		
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*10;
		String param ="";
		
		Query query=getSession().createQuery("from App_picture  p where    p.factory.id='"+factoryid+"' ");  //带条件的查询语句  
		int count =query.list().size();
		  query.setFirstResult(firdata);  
		  query.setMaxResults(10); 
		  List<App_picture> list=query.list();
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
	public Map findApppicturesByOpenid(String open_id) {
		// TODO Auto-generated method stub
		Map map_picture = new HashMap();
		String queryStr ="from Link_BindCustomers_Factorys l  left join l.bindcustomer as b where b.openid=? ";
		Query queryObj =this.getSession().createQuery(queryStr);
		List list =queryObj.setParameter(0, open_id).list();
		
		Iterator it=list.iterator();   
		List<App_picture> list_app_pictures =new ArrayList();
		List<App_activity> list_app_activitys = new ArrayList();
	    String fac_id =null  ;  
	    while(it.hasNext()){   
		       	Object[] obj=(Object[])it.next(); 
		       	Link_BindCustomers_Factorys link_custom_fac =(Link_BindCustomers_Factorys) obj[0]; 
		        fac_id =link_custom_fac.getFactory().getId();
	    }
	    if(fac_id !=null){
	    	String queryStr2 ="from App_picture a left join  a.factory f where f.id=? ";
			Query queryObj2 =this.getSession().createQuery(queryStr2);
			List list1 =queryObj2.setParameter(0, fac_id).list();
		
			Iterator it1=list1.iterator();
			 while(it1.hasNext()){   
			       	Object[] obj=(Object[])it1.next(); 
			       	App_picture app_picture =(App_picture) obj[0]; 
			       	list_app_pictures.add(app_picture)  ;
		    }
			
			 String queryStr3 ="from App_activity a left join  a.factory f where f.id=? ";
				Query queryObj3 =this.getSession().createQuery(queryStr3);
				List list2 =queryObj3.setParameter(0, fac_id).list();
				Iterator it2=list2.iterator();
				 while(it2.hasNext()){   
				       	Object[] obj=(Object[])it2.next(); 
				       	App_activity app_activity =(App_activity) obj[0]; 
				       	list_app_activitys.add(app_activity);
			    } 
			 
	    }
	    map_picture.put("app_pictures", list_app_pictures);
	    
	    map_picture.put("list_app_activitys", list_app_activitys);
	    
	    return map_picture;
	}

	@Override
	public Map findApppicturesByOrginalID(String toUserName) {
		// TODO Auto-generated method stub
		Map map_picture = new HashMap();
		List<App_picture> list_app_pictures =new ArrayList();
		List<App_activity> list_app_activitys = new ArrayList();
		//获取集团
		Doc_Company company =(Doc_Company) getSession().createQuery(//
			"from Doc_Company c where   c.originalID=?")//
			.setParameter(0, toUserName)//
			.uniqueResult();
		
		Set<Doc_Factory> setfac =company.getDoc_factorys();
		for(Doc_Factory factory:setfac){
			Query	query2 =getSession().createQuery("from App_picture p where   p.factory.id='"+factory.getId()+"' ");  //带条件的查询语句  
					
			list_app_pictures.addAll(query2.list());
					
			Query	query3 =getSession().createQuery("from App_activity p where   p.factory.id='"+factory.getId()+"' ");  //带条件的查询语句  
					
			list_app_activitys.addAll(query3.list());
			}
			map_picture.put("app_pictures", list_app_pictures);
				    
			map_picture.put("list_app_activitys", list_app_activitys);
				
				
			return map_picture;
	}

	@Override
	public Map findApppacksByOpenid(String open_id) {
		// TODO Auto-generated method stub
		Map map_pack = new HashMap();
		String queryStr ="from Link_BindCustomers_Factorys l  left join l.bindcustomer as b where b.openid=? ";
		Query queryObj =this.getSession().createQuery(queryStr);
		List list =queryObj.setParameter(0, open_id).list();
		Iterator it=list.iterator();   
		List<App_picture> list_app_pictures =new ArrayList();
		List<App_pack> list_app_packs = new ArrayList();
	    String fac_id =null  ;  
	    while(it.hasNext()){   
		       	Object[] obj=(Object[])it.next(); 
		       	Link_BindCustomers_Factorys link_custom_fac =(Link_BindCustomers_Factorys) obj[0]; 
		       fac_id =link_custom_fac.getFactory().getId();
	    }
	    if(fac_id !=null){
	    	String queryStr2 ="from App_picture a left join  a.factory f where f.id=? ";
			Query queryObj2 =this.getSession().createQuery(queryStr2);
			List list1 =queryObj2.setParameter(0, fac_id).list();
			Iterator it1=list1.iterator();
			 while(it1.hasNext()){   
			       	Object[] obj=(Object[])it1.next(); 
			       	App_picture app_picture =(App_picture) obj[0]; 
			       	list_app_pictures.add(app_picture)  ;
		    }
			 
			String queryStr3 ="from App_pack a left join  a.factory f where f.id=? ";
				Query queryObj3 =this.getSession().createQuery(queryStr3);
				List list2 =queryObj3.setParameter(0, fac_id).list();
				Iterator it2=list2.iterator();
				 while(it2.hasNext()){   
				       	Object[] obj=(Object[])it2.next(); 
				       	App_pack app_pack =(App_pack) obj[0]; 
				       	list_app_packs.add(app_pack);
			    } 
	    	
	    }
	    map_pack.put("app_pictures", list_app_pictures);
	    
	    map_pack.put("list_app_packs", list_app_packs);
	    
		return map_pack;
	}

	@Override
	public Map findApppacksByOrginalID(String toUserName) {
		// TODO Auto-generated method stub
		Map map_pack = new HashMap();
		List<App_picture> list_app_pictures =new ArrayList();
		List<App_pack> list_app_packs = new ArrayList();
		//获取集团
		Doc_Company company =(Doc_Company) getSession().createQuery(//
			"from Doc_Company c where   c.originalID=?")//
			.setParameter(0, toUserName)//
			.uniqueResult();
		Set<Doc_Factory> setfac =company.getDoc_factorys();
		for(Doc_Factory factory:setfac){
			Query	query2 =getSession().createQuery("from App_picture p where   p.factory.id='"+factory.getId()+"' ");  //带条件的查询语句  
					
			list_app_pictures.addAll(query2.list());
					
			Query	query3 =getSession().createQuery("from App_pack p where   p.factory.id='"+factory.getId()+"' ");  //带条件的查询语句  
					
			list_app_packs.addAll(query3.list());
			}
		map_pack.put("app_pictures", list_app_pictures);
				    
		map_pack.put("list_app_packs", list_app_packs);
				
				
			return map_pack;
	}

	@Override
	public List<App_picture> findApppicture_list(String factory_id) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("from App_picture  p where    p.factory.id='"+factory_id+"' ");  //带条件的查询语句  
		List<App_picture> list=query.list();
		return list;
	}
	
	//返回json list


		@Override
		public List<Json_AppPicture> findAppPicturesByFactoryid(String factoryid) {
			// TODO Auto-generated method stub


			Query	query =getSession().createQuery("from App_picture p where   p.factory.id='"+factoryid+"' ");  //带条件的查询语句  

			List<App_picture> listpicture =  	query.list();
			List<Json_AppPicture> list_json = new ArrayList<>();
			for(App_picture picture: listpicture){
				Json_AppPicture json_app =new Json_AppPicture();
				json_app.setId(picture.getId());
				json_app.setPath(picture.getPath());
				json_app.setPicname(picture.getPicname());
				json_app.setCreatedate(picture.getCreatedate());
				list_json.add(json_app);
			}
			return list_json;
		}

}
