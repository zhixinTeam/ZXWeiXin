package com.zhixin.service;

import java.util.List;
import java.util.Set;

import com.zhixin.entity.Page;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.PageData;

public interface UserService {

	

	Sys_User getUserByNameAndPWd(String passwd);

	List<Sys_User> listSysUsers(Sys_User user,String factoryid);

	List<Sys_Role> listSysRole(String factoryid);

	List<Sys_Role> listAllchildRoles(String roleid);

	Sys_User findByUId(String username);

	Boolean findByUE(String email, String username);

	Boolean findByUN(String username, String susernumber);

	Sys_Role findRolebyID(String roleid);

	void savesUser(Sys_User newuser);

	Sys_User findByUserName(String username);

	void deleteU(String userid);

	Sys_User findUserByID(String userid);

	void updateUser(Sys_User yluser);

	PageBean findpageusers(String currentPage, String factoryid ,String username, String roleid);

	void updateLastLogin(Sys_User sys_user);

	void updateYlUser(Sys_User yluser);

	void updateUserName(Sys_User yluser);

	void updateUsermerge(Sys_User yluser);

}
