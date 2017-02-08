package com.zhixin.dao;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
public interface UserDao extends DaoSupport<Sys_User>{

	Sys_User findUserByNameAndPwd(String passwd);

	List<Sys_User> listSysUsers(String id,String factoryid);

	Doc_Factory findFacByID(String factoryid);

	Sys_Role findSysroleByID(String roleid);

	Sys_User findUserByName(String username);

	List<Sys_User>  findUserByEmail(String email);

	Sys_Role findRolebyID(String roleid);

	void deleteU(String userid);


	void updateuser(Sys_User yluser);

	PageBean findpageusers(String currentPage, String factoryid,String username, String roleid);

	void updateUsermerge(Sys_User yluser);



}
