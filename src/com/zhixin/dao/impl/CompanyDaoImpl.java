package com.zhixin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.CompanyDao;
import com.zhixin.entity.Json_Factory;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.tools.TimestampUtil;

@Service(value="companydao")
public class CompanyDaoImpl extends DaoSupportImpl<Doc_Company> implements CompanyDao{

	@Override
	public PageBean findcompanys(String currentPage ,String COMPANYNAME,String status) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*10;
		
		String companyname =COMPANYNAME.trim();
		String param ="";
		if(! status.equals(""))
			param+="  and  c.status ='"+status+"'";
		if(! companyname.equals(""))
			param+="  and  c.companyname like '%"+companyname+"%'  ";
		
		Query query=getSession().createQuery("from Doc_Company c where '1'='1'" + param);  //带条件的查询语句  

		int count =query.list().size();
		  
		query.setFirstResult(firdata);  
	    query.setMaxResults(10);
		  
		List<Doc_Company> listcom = query.list();  
	    PageBean pagebean = new PageBean( curpage, 10, count, listcom);
		return pagebean;
	}

	@Override
	public List<Json_Factory> getFactoryByCompanyID(String companyid) {
		// TODO Auto-generated method stub
		Doc_Company company =(Doc_Company) getSession().createQuery(//
				"from Doc_Company c where c.id=?")//
				.setParameter(0, companyid)//
				.uniqueResult();
		Set<Doc_Factory> setf = company.getDoc_factorys();
		List<Json_Factory> retunlist = new ArrayList<>();
		for(Doc_Factory fac:setf){
			Json_Factory jfac = new Json_Factory();
			jfac.setFactoryname(fac.getFactoryname());
			jfac.setCompanyid(companyid);
			jfac.setId(fac.getId());
			jfac.setServiceparam(fac.getServiceparam());
			jfac.setServiceurl(fac.getServiceurl());
			jfac.setAdminname(fac.getUser().getUsername());
			jfac.setStatus(fac.getStatus());
			jfac.setRoleid(fac.getUser().getSys_role().getId());
			jfac.setCreatedate(TimestampUtil.timestamptoString(fac.getCreatedate()));
			retunlist.add(jfac);
		}
		
		return retunlist;
	}

	@Override
	public void saveCompany(Doc_Company com, Doc_Factory fac, Sys_Role role, Sys_User adminuser) {
		// TODO Auto-generated method stub
		this.getSession().save(com);
		this.getSession().save(fac);
		this.getSession().save(role);
		this.getSession().save(adminuser);
	}

	@Override
	public void updateCompany(Doc_Company ylcompany, Doc_Factory fac, Sys_Role role, Sys_User adminuser) {
		// TODO Auto-generated method stub
		this.getSession().update(ylcompany);
		this.getSession().save(fac);
		this.getSession().save(role);
		this.getSession().save(adminuser);
	}

	@Override
	public void updateCompany(Doc_Company company) {
		// TODO Auto-generated method stub
		Query query = getSession().createSQLQuery("update doc_factory f   set f.status='0' and f.editlog ='集团关闭' where companyid= '"+company.getId()+"' ");
		query.executeUpdate();
		this.getSession().update(company);
	}

	@Override
	public List<Doc_Company> find_tokens() {
		// TODO Auto-generated method stub
		Query query =getSession().createQuery("from Doc_Company c  where c.status='1' ");
		List<Doc_Company> listcom =query.list();
		return listcom;
	}

	@Override
	public Doc_Company findCompanyByAppid(String appid) {
		// TODO Auto-generated method stub
		Query query =getSession().createQuery("from Doc_Company c  where c.appid='"+appid+"' ");
		List<Doc_Company> listcom =query.list();
		if(listcom.size()>0){
			return listcom.get(0);
		}else{
			return new Doc_Company();
		}
	}

	@Override
	public Doc_Company findCompanyByOriginalID(String originalID) {
		// TODO Auto-generated method stub
		return (Doc_Company)getSession().createQuery("from Doc_Company c  where c.originalID='"+originalID+"' ").uniqueResult();
		
	}

	

}
