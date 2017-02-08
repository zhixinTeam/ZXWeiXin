package com.zhixin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.AuthDao;
import com.zhixin.entity.Json_Auth;
import com.zhixin.model.Sys_Auth;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.AuthService;
@Service(value="authService")
public class AuthServiceImpl  implements AuthService{

	@Resource(name="authDao")
	private AuthDao authDao;
	
	@Override
	public List<Sys_Auth> listAllAuthmenu() {
		// TODO Auto-generated method stub
		return authDao.findAll();
	}

	@Override
	public List<Json_Auth> listSubAuthByParentId(String aUTH_ID) {
		// TODO Auto-generated method stub
		List<Json_Auth> jsonauthlist =new ArrayList<>();
	    List<Sys_Auth>	childAuth = authDao.findAuthByPID(aUTH_ID);
	    for(Sys_Auth sys_auth:childAuth){
	    	Json_Auth jauth = new Json_Auth();
	    	jauth.setAuthname(sys_auth.getAuthname());
	    	jauth.setAuthpath(sys_auth.getAuthpath());
	    	jauth.setIconcls(sys_auth.getIconcls());
	    	jauth.setId(sys_auth.getId());
	    	jauth.setParentID(sys_auth.getParent().getId());
	    	jauth.setState(sys_auth.getState());
	    	jauth.setAuthorder(sys_auth.getAuthorder());
	    	jsonauthlist.add(jauth);
	    }
		return jsonauthlist;
	}

	@Override
	public void updateediticon(PageData pd) {
		// TODO Auto-generated method stub
		authDao.editicon(pd);
	}

	@Override
	public List<Sys_Auth> listAllParentAuth() {
		// TODO Auto-generated method stub
		return authDao.findparentauth();
	}

	@Override
	public void saveAuthparent(Sys_Auth pauth) {
		// TODO Auto-generated method stub
		authDao.save(pauth);
	}

	@Override
	public List<Sys_Auth> findAllAuth() {
		// TODO Auto-generated method stub
		return authDao.findAll();
	}

	@Override
	public void updateAuthByParentID(Sys_Auth pauth, String parentid) {
		// TODO Auto-generated method stub
		Sys_Auth parAuth=  authDao.getById(parentid);
		pauth.setParent(parAuth);
		authDao.save(pauth);
	}

	@Override
	public Sys_Auth getAuthByID(String authid) {
		// TODO Auto-generated method stub
		return authDao.getById(authid);
	}

	@Override
	public void updateAuthparent(Sys_Auth authmodel) {
		// TODO Auto-generated method stub
		authDao.update(authmodel);
	}

	@Override
	public void updateAuthandParent(Sys_Auth authmodel, String pId, String pARENT_ID) {
		authDao.updateAuthtoParent(authmodel,pARENT_ID);
		
	}

	@Override
	public void deleteauthById(String authid) {
		// TODO Auto-generated method stub
		authDao.deleteauthById(authid);
		
	}

	@Override
	public List<Sys_Auth> findAuthByRoleID(String roleid) {
		// TODO Auto-generated method stub
		return authDao.findAuthByRoleID(roleid);
	}

}
