package com.zhixin.dao;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.entity.Json_Factory;
import com.zhixin.entity.Json_Factory;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;

public interface CompanyDao extends DaoSupport<Doc_Company>{

	PageBean findcompanys(String currentPage,String COMPANYNAME,String status);

	List<Json_Factory> getFactoryByCompanyID(String companyid);

	void saveCompany(Doc_Company com, Doc_Factory fac, Sys_Role role, Sys_User adminuser);

	void updateCompany(Doc_Company ylcompany, Doc_Factory fac, Sys_Role role, Sys_User adminuser);

	void updateCompany(Doc_Company company);

	List<Doc_Company> find_tokens();

	Doc_Company findCompanyByAppid(String string);

	Doc_Company findCompanyByOriginalID(String originalID);

}
