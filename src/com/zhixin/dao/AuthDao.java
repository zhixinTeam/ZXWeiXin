package com.zhixin.dao;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.Sys_Auth;
import com.zhixin.right_utils.PageData;

public interface AuthDao extends DaoSupport<Sys_Auth> {

	List<Sys_Auth> findAuthByPID(String aUTH_ID);

	void editicon(PageData pd);

	List<Sys_Auth> findparentauth();

	void updateAuthtoParent(Sys_Auth authmodel, String pARENT_ID);

	void deleteauthById(String authid);

	List<Sys_Auth> findAuthByRoleID(String roleid);


}
