package com.zhixin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.WxTemplateDao;
import com.zhixin.entity.Json_Factory;
import com.zhixin.entity.Json_X_Msg_Type;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.X_Msg_Type;
import com.zhixin.tools.TimestampUtil;

@Service(value="wxTemplateDao")
public class WxTemplateDaoImpl extends DaoSupportImpl<X_Msg_Type> implements WxTemplateDao {

	@Override
	public PageBean findpageX_Msg_Type(String currentPage,String com_id) {
		// TODO Auto-generated method stub
			String param ="";
			int curpage =Integer.parseInt(currentPage);
			int firdata =(curpage-1)*10;
			com_id =com_id.trim();
			Query query=null;
			int count =0;
			PageBean pagebean=null;
			List<Json_X_Msg_Type> retunlist = new ArrayList<>();
			String sql ="";
			if(! "".equals(com_id)){

				List listxmsg =null;
				sql ="from X_Msg_Type f left join f.doc_company as c   where  c.id='"+com_id+"' " + param;
				query=getSession().createQuery(sql);  //带条件的查询语句  
				count =query.list().size();
			    query.setFirstResult(firdata);  
				query.setMaxResults(10);
				listxmsg = query.list();  
			    for(int i=0;i<listxmsg.size();i++){
			    	Object [] obj =(Object[]) listxmsg.get(i);
			    	X_Msg_Type mst=(X_Msg_Type) obj[0];
			    	Json_X_Msg_Type jmst = new Json_X_Msg_Type();
			    	jmst.setCompanyname(mst.getDoc_company().getCompanyname());
			    	jmst.setId(mst.getId());
			    	jmst.setRemark(mst.getRemark());
			    	jmst.setTemplateid(mst.getTemplateid());
			    	jmst.setTypebs(mst.getTypebs());
					retunlist.add(jmst);
			    }
			    pagebean = new PageBean( curpage, 10, count, retunlist);
				
				return pagebean;
				
			}else{
				List<X_Msg_Type> listxmsg =null;
				sql="from X_Msg_Type f where '1'='1'" + param;
				query=getSession().createQuery(sql);  //带条件的查询语句  
				count =query.list().size();
			    query.setFirstResult(firdata);  
				query.setMaxResults(10);
				listxmsg = query.list();  
			    for(X_Msg_Type msg:listxmsg){
			    	Json_X_Msg_Type jxmt = new Json_X_Msg_Type();
			    	jxmt.setCompanyname(msg.getDoc_company().getCompanyname());
			    	jxmt.setId(msg.getId());
			    	jxmt.setRemark(msg.getRemark());
			    	jxmt.setTemplateid(msg.getTemplateid());
			    	jxmt.setTypebs(msg.getTypebs());
					retunlist.add(jxmt);
				}
			    pagebean = new PageBean( curpage, 10, count, retunlist);
				
				return pagebean;
			}
			
		}
			
	

	@Override
	public X_Msg_Type findX_Msg_TypeById(String id) {
		// TODO Auto-generated method stub
		Criteria criteria=getSession().createCriteria(X_Msg_Type.class).add(Restrictions.eq("id",id ));
		X_Msg_Type x_Msg_Type=(X_Msg_Type) criteria.uniqueResult();
		return x_Msg_Type;
	}

	@Override
	public void updateX_Msg_Type(X_Msg_Type x_Msg_Type) {
		// TODO Auto-generated method stub
			getSession().update(x_Msg_Type);
	}

	@Override
	public void deleteX_Msg_Type(X_Msg_Type x_Msg_Type) {
		// TODO Auto-generated method stub
		getSession().delete(x_Msg_Type);
	}

	@Override
	public void saveX_Msg_Type(X_Msg_Type x_Msg_Type) {
		// TODO Auto-generated method stub
		getSession().save(x_Msg_Type);
	}



	@Override
	public X_Msg_Type findX_Msg_TypeByTypebsAndcom_id(String typebs, Doc_Company doc_company) {
		// TODO Auto-generated method stub
		Criteria criteria=getSession().createCriteria(X_Msg_Type.class).add(Restrictions.eq("typebs",typebs))
				.add(Restrictions.eq("doc_company",doc_company));
		X_Msg_Type x_Msg_Type=(X_Msg_Type) criteria.uniqueResult();
		
		return x_Msg_Type;
	}

}
