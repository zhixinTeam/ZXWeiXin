package com.zhixin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.PicturesDao;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Picture;


@Service(value="picturesDao") 
public class PicturesDaoImpl extends DaoSupportImpl<Sys_Picture> implements PicturesDao{

	@Override
	public PageBean findPictures(String query_title,String currentPage, String id) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*10;
		String param ="";
		if (!"".equals(query_title)){
			param ="p.title ='"+query_title+"' and ";
		}
		Query query=getSession().createQuery("from Sys_Picture  p where "+param+"   p.doc_factory.id='"+id+"' ");  //带条件的查询语句  
		int count =query.list().size();
		  query.setFirstResult(firdata);  
		  query.setMaxResults(10); 
		  List<Sys_Picture> list=query.list();
		  while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =(curpage-1)*10;
			   query=getSession().createQuery("from Sys_Picture p where "+param+"   p.doc_factory.id='"+id+"' ");  //带条件的查询语句  
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
	public Sys_Picture findPictureByID(String picture_id) {
		// TODO Auto-generated method stub
		return (Sys_Picture) getSession().createQuery(//
				"from Sys_Picture   where  id=?")//
				.setParameter(0, picture_id)//
				.uniqueResult();
	}

	@Override
	public List<Sys_Picture> findPicturesByFactoryid(String[] factory_IDs) {
		// TODO Auto-generated method stub
		List<Sys_Picture> listpics =new ArrayList<>();
		for(String factory_id:factory_IDs){
			Query	query=getSession().createQuery("from Sys_Picture p where   p.doc_factory.id='"+factory_id+"' ");  //带条件的查询语句  
			
			listpics.addAll(query.list());
		}
		
		return listpics;
	}
	@Override
	//关联对象表
	public List<Sys_Picture> findPicturesByOpenid(String open_id) {
		// TODO Auto-generated method stub
		/*Query query=getSession().createQuery("from Wx_BindCustomer u where   u.openid='"+open_id+"' ");  //带条件的查询语句  
		List<Wx_BindCustomer>  listbindcustomers=query.list();
		if(listbindcustomers.size()>0){
			Wx_BindCustomer wx_bindcustomer =listbindcustomers.get(0);
			List<Sys_Picture> listpics =new ArrayList<>();
			//todo
			//获取关联表对象
			Link_BindCustomers_Factorys   linkbindcustomerfactory=wx_bindcustomer.getBindcustomer_factory();
			Set<Doc_Factory> factorys=linkbindcustomerfactory.getFactorys();
			for(Doc_Factory factory:factorys){
				Query	query2 =getSession().createQuery("from Sys_Picture p where   p.doc_factory.id='"+factory.getId()+"' ");  //带条件的查询语句  
				
				listpics.addAll(query2.list());
			}
			return listpics;
		}*/
		return new ArrayList<Sys_Picture>();
	}

	@Override
	public void update_PictureLog(Sys_Picture picture) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("update Sys_Picture  t set t.flag = '"+0+"' ");
		query.executeUpdate();
		this.getSession().update(picture);
	}

	@Override
	public List<Sys_Picture> findPicturesByOrginalID(String orginalID) {
		// TODO Auto-generated method stub
		//获取集团
		Doc_Company company =(Doc_Company) getSession().createQuery(//
				"from Doc_Company c where   c.originalID=?")//
				.setParameter(0, orginalID)//
				.uniqueResult();
		Set<Doc_Factory> setfac =company.getDoc_factorys();
		List<Sys_Picture> listpics =new ArrayList<>();
		for(Doc_Factory factory:setfac){
			Query	query2 =getSession().createQuery("from Sys_Picture p where   p.doc_factory.id='"+factory.getId()+"' ");  //带条件的查询语句  
			
			listpics.addAll(query2.list());
		}
		return listpics;
		
		}

	//获取logo
	@Override
	public Sys_Picture findPictoryBy_flag() {
		// TODO Auto-generated method stub
		
		return (Sys_Picture) getSession().createQuery(//
				"from Sys_Picture   where  flag=?")//
				.setParameter(0, 1)//
				.uniqueResult();
	}

	
}