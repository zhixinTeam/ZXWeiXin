package com.zhixin.service;

import java.util.List;

import com.zhixin.entity.Json_Factory;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;

public interface CompanyService {

	PageBean findcompanys(String currentPage ,String COMPANYNAME,String status);

	List<Json_Factory> getFactoryByCompyID(String companyid);

	void saveCompany(Doc_Company com, Doc_Factory fac, Sys_Role role, Sys_User adminuser);

	Doc_Company findCompanyByID(String companyID);

	void updateCompany(Doc_Company company);

	List<Doc_Company> findAllCompany();

	void updateCompany(Doc_Company ylcompany, Doc_Factory fac, Sys_Role role, Sys_User adminuser);

	void find_tokens();

	String find_tokenByappid(String app_id);


}
