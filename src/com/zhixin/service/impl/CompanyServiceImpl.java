package com.zhixin.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Service;

import com.zhixin.dao.CompanyDao;
import com.zhixin.entity.Json_Factory;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.service.CompanyService;

import weixin.popular.api.TokenAPI;
import weixin.popular.support.TokenManager;

@Service(value="companyService")
public class CompanyServiceImpl  implements CompanyService{

	@Resource(name="companydao")
	private CompanyDao companydao;

	@Override
	public PageBean findcompanys(String currentPage ,String COMPANYNAME,String status) {
		// TODO Auto-generated method stub
		return companydao.findcompanys(currentPage,COMPANYNAME,status);
	}

	@Override
	public List<Json_Factory> getFactoryByCompyID(String companyid) {
		// TODO Auto-generated method stub
		return companydao.getFactoryByCompanyID(companyid);
	}

	@Override
	public void saveCompany(Doc_Company com, Doc_Factory fac, Sys_Role role, Sys_User adminuser) {
		// TODO Auto-generated method stub
		companydao.saveCompany(com,fac,role,adminuser);
	}

	@Override
	public Doc_Company findCompanyByID(String companyID) {
		// TODO Auto-generated method stub
		return companydao.getById(companyID);
	}

	@Override
	public void updateCompany(Doc_Company company) {
		// TODO Auto-generated method stub
		
		companydao.updateCompany(company);
	}

	@Override
	public List<Doc_Company> findAllCompany() {
		// TODO Auto-generated method stub
		return companydao.findAll();
	}

	@Override
	public void updateCompany(Doc_Company ylcompany, Doc_Factory fac, Sys_Role role, Sys_User adminuser) {
		// TODO Auto-generated method stub
		companydao.updateCompany(ylcompany,fac,role,adminuser);
	}

	@Override
	public void find_tokens() {
		// TODO Auto-generated method stub
		List<Doc_Company> listcom =companydao.find_tokens();
		for(Doc_Company com:listcom){
			if(TokenManager.getToken(com.getAppid()) ==null){
				TokenManager.init(com.getAppid(), com.getSecrectid());
			}
			/*while(TokenManager.getToken(com.getAppid()) ==null){
				System.out.println(TokenManager.getToken(com.getAppid()));
			}*/
		}
				
				
	}

	@Override
	public String find_tokenByappid(String app_id) {
		// TODO Auto-generated method stub
		Doc_Company com = companydao.findCompanyByAppid(app_id);
		//String access_token=TokenAPI.token(app_id, com.getSecrectid()).getAccess_token();
		TokenManager.init(app_id, com.getSecrectid());
		return TokenManager.getToken(app_id);
	}

	@Override
	public Doc_Company findCompanyByOriginalID(String originalID) {
		// TODO Auto-generated method stub
		return companydao.findCompanyByOriginalID(originalID);
	}
	
	
	
}
