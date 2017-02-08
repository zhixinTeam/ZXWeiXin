package com.zhixin.service;

import java.util.List;

import com.zhixin.entity.Json_Auth;
import com.zhixin.model.Sys_Auth;
import com.zhixin.right_utils.PageData;


public interface AuthService  {

	List<Sys_Auth> listAllAuthmenu();

	List<Json_Auth> listSubAuthByParentId(String aUTH_ID);

	void updateediticon(PageData pd);

	List<Sys_Auth> listAllParentAuth();

	void saveAuthparent(Sys_Auth pauth);

	List<Sys_Auth> findAllAuth();

	void updateAuthByParentID(Sys_Auth pauth, String parentid);

	Sys_Auth getAuthByID(String authid);

	void updateAuthparent(Sys_Auth authmodel);

	void updateAuthandParent(Sys_Auth authmodel, String pId, String pARENT_ID);

	void deleteauthById(String authid);

	List<Sys_Auth> findAuthByRoleID(String roleid);

	

}
