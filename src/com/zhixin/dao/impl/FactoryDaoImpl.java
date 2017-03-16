package com.zhixin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.FactoryDao;
import com.zhixin.entity.Json_Factory;
import com.zhixin.entity.Json_AppPicture;
import com.zhixin.entity.Json_Apppack;
import com.zhixin.entity.Json_Doc_Factory;
import com.zhixin.entity.Json_Sys_picture;
import com.zhixin.model.App_pack;
import com.zhixin.model.App_picture;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Link_BindCustomers_Factorys;
import com.zhixin.model.PageBean;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Sys_Picture;
import com.zhixin.tools.TimestampUtil;

@Service(value="factorydao")
public class FactoryDaoImpl extends DaoSupportImpl<Doc_Factory> implements FactoryDao{

	

	@Override
	public PageBean findFactorys(String currentPage, String status, String facname, String com_id) {
		// TODO Auto-generated method stub
		String param ="";
		if(! "".equals(status)){
			param+="  and  f.status = '"+status+"'  ";
		}
		if(! "".equals(facname)){
			param+="  and  f.factoryname like '%"+facname+"%'  ";
		}
		
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*10;
		com_id =com_id.trim();
		Query query=null;
		int count =0;
		PageBean pagebean=null;
		List<Json_Factory> retunlist = new ArrayList<>();
		String sql ="";
		if(! "".equals(com_id)){

			List listfac =null;
			sql ="from Doc_Factory f left join f.doc_company as c   where  c.id='"+com_id+"' " + param;
			query=getSession().createQuery(sql);  //带条件的查询语句  
			count =query.list().size();
		    query.setFirstResult(firdata);  
			query.setMaxResults(10);
		    listfac = query.list();  
		    for(int i=0;i<listfac.size();i++){
		    	Object [] obj =(Object[]) listfac.get(i);
		    	Doc_Factory fac=(Doc_Factory) obj[0];
		    	Json_Factory jfac = new Json_Factory();
				jfac.setFactoryname(fac.getFactoryname());
				jfac.setCompanyid(fac.getDoc_company().getId());
				jfac.setCompanyname(fac.getDoc_company().getCompanyname());
				jfac.setId(fac.getId());
				jfac.setServiceparam(fac.getServiceparam());
				jfac.setServiceurl(fac.getServiceurl());
				jfac.setAdminname(fac.getUser().getUsername());
				jfac.setStatus(fac.getStatus());
				jfac.setRoleid(fac.getUser().getSys_role().getId());
				jfac.setCreatedate(TimestampUtil.timestamptoString(fac.getCreatedate()));
				jfac.setStartdate(fac.getStartdate());
				jfac.setEnddate(fac.getEnddate());
				retunlist.add(jfac);
		    }
		    pagebean = new PageBean( curpage, 10, count, retunlist);
			
			return pagebean;
			
		}else{
			List<Doc_Factory> listfac =null;
			sql="from Doc_Factory f where '1'='1'" + param;
			query=getSession().createQuery(sql);  //带条件的查询语句  
			count =query.list().size();
		    query.setFirstResult(firdata);  
			query.setMaxResults(10);
		    listfac = query.list();  
		    for(Doc_Factory fac:listfac){
				Json_Factory jfac = new Json_Factory();
				jfac.setFactoryname(fac.getFactoryname());
				jfac.setCompanyid(fac.getDoc_company().getId());
				jfac.setCompanyname(fac.getDoc_company().getCompanyname());
				jfac.setId(fac.getId());
				jfac.setServiceparam(fac.getServiceparam());
				jfac.setServiceurl(fac.getServiceurl());
				jfac.setAdminname(fac.getUser().getUsername());
				jfac.setStatus(fac.getStatus());
				jfac.setRoleid(fac.getUser().getSys_role().getId());
				jfac.setCreatedate(TimestampUtil.timestamptoString(fac.getCreatedate()));
				jfac.setStartdate(fac.getStartdate());
				jfac.setEnddate(fac.getEnddate());
				retunlist.add(jfac);
			}
		    pagebean = new PageBean( curpage, 10, count, retunlist);
			
			return pagebean;
		}
		
	}

	@Override
	public void checkfac_status() {
		// TODO Auto-generated method stub
		String updatesql ="update  Doc_Factory f set f.Status ='0', f.EditLog ='过期自动关闭服务' where f.Status=1 and  f.EndDate < (select CURDATE() FROM dual)";
		Query query =this.getSession().createSQLQuery(updatesql);
		query.executeUpdate();
	}

	@Override
	public Doc_Factory findFactoryById(String factoryId) {
		// TODO Auto-generated method stub
		return (Doc_Factory) this.getSession().createQuery(//
				"from Doc_Factory f where f.id=?")//
				.setParameter(0, factoryId)//
				.uniqueResult();
	}

	

	@Override
	public List<Json_Sys_picture> findFacturePictureByFactoryId(String factoryId,String currentPage) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*2;
		String param ="";
		Query query=getSession().createQuery("from Sys_Picture  p where    p.doc_factory.id='"+factoryId+"' ");  //带条件的查询语句  
		int count =query.list().size();
		  query.setFirstResult(firdata);  
		  query.setMaxResults(2); 
		  List<App_pack> list=query.list();
		  while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =(curpage-1)*2;
			   query=getSession().createQuery("from Sys_Picture  p where    p.doc_factory.id='"+factoryId+"' ");  //带条件的查询语句  
			   count =query.list().size();
			   query.setFirstResult(firdata);  
			   query.setMaxResults(2);  
			   list=query.list();
			   if(list.size()>0)
				   break;
		  }
		PageBean pagebean = new PageBean( curpage, 2, count, list);	
		
		
		List<Sys_Picture> listpicture=pagebean.getRecordList();
		
		List<Json_Sys_picture> list_json = new ArrayList<>();
		for(Sys_Picture picture: listpicture){
			Json_Sys_picture json_app =new Json_Sys_picture();
			json_app.setId(picture.getId());
			json_app.setPath(picture.getPath());
			json_app.setTitle(picture.getTitle());
			json_app.setCreatedate(picture.getCreatedate());
			json_app.setPageCount(pagebean.getPageCount());
			list_json.add(json_app);
		}
		return list_json;
	}

	@Override
	public List<Doc_Factory> findFactoryByComID(String companyid) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("from Doc_Factory f where    f.doc_company.id='"+companyid+"' ");  //带条件的查询语句  
		return query.list();
	}

	@Override
	public List<Json_Doc_Factory> findFactoryByCompanyID(String companyid) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("from Doc_Factory f where    f.doc_company.id='"+companyid+"' ");  //带条件的查询语句  
		List<Doc_Factory> list=query.list();
		List<Json_Doc_Factory> Listjson_Doc_Factory=new ArrayList<>();
		for (Doc_Factory doc_Factory : list) {
			Json_Doc_Factory json_Doc_Factory=new Json_Doc_Factory();
			json_Doc_Factory.setCompanyId(companyid);
			json_Doc_Factory.setFactoryName(doc_Factory.getFactoryname());
			json_Doc_Factory.setId(doc_Factory.getId());
			Listjson_Doc_Factory.add(json_Doc_Factory);
		}
		return Listjson_Doc_Factory;
	}

	@Override
	public List<Json_Doc_Factory> findFactoryByCustomerId(String c_id) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("from Link_BindCustomers_Factorys l where  l.bindcustomer.id='"+c_id+"' ");  //带条件的查询语句  
		List<Link_BindCustomers_Factorys> list=query.list();
		if(list.size()==0||list==null){
			return null;
		}else{
			List<Doc_Factory> listDoc_Factory=new ArrayList<Doc_Factory>();
			for (Link_BindCustomers_Factorys link_BindCustomers_Factorys : list) {
				String id=link_BindCustomers_Factorys.getFactory().getId();
				query=getSession().createQuery("from Doc_Factory d where  d.id='"+id+"' ");
				Doc_Factory doc_factory=(Doc_Factory) query.uniqueResult();
				listDoc_Factory.add(doc_factory);
			}
			List<Json_Doc_Factory> Listjson_Doc_Factory=new ArrayList<>();
			for (Doc_Factory doc_Factory1 : listDoc_Factory) {
				Json_Doc_Factory json_Doc_Factory=new Json_Doc_Factory();
				json_Doc_Factory.setFactoryName(doc_Factory1.getFactoryname());
				json_Doc_Factory.setId(doc_Factory1.getId());
				Listjson_Doc_Factory.add(json_Doc_Factory);
			}
			return Listjson_Doc_Factory;
			
		}
		
	}

	@Override
	public List<Doc_Factory> findFactorysByCustomerId(String c_id) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("from Link_BindCustomers_Factorys l where  l.bindcustomer.id='"+c_id+"' ");  //带条件的查询语句  
		List<Link_BindCustomers_Factorys> list=query.list();
		if(list.size()==0||list==null){
			return null;
		}else{
			List<Doc_Factory> listDoc_Factory=new ArrayList<Doc_Factory>();
			for (Link_BindCustomers_Factorys link_BindCustomers_Factorys : list) {
			String id=link_BindCustomers_Factorys.getFactory().getId();
			query=getSession().createQuery("from Doc_Factory d where  d.id='"+id+"' ");
			Doc_Factory doc_factory=(Doc_Factory) query.uniqueResult();
			listDoc_Factory.add(doc_factory);
		}
			return listDoc_Factory;
	}
	}
	    
	    

}
