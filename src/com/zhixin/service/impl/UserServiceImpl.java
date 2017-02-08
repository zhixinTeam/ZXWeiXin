package com.zhixin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.UserDao;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	
	
	@Override
	public Sys_User getUserByNameAndPWd(String passwd) {
		// TODO Auto-generated method stub
		return userDao.findUserByNameAndPwd(passwd);
	}
	@Override
	public List<Sys_User> listSysUsers(Sys_User user, String factoryid) {
		// TODO Auto-generated method stub
		
		Doc_Factory fac =  userDao.findFacByID(factoryid);
		Set<Sys_User> fusers=fac.getSys_users();
		List<Sys_User> userlist = new ArrayList(fusers);
		for(Sys_User u2:userlist){
			if(u2.getId().equals(user.getId())){
				userlist.remove(u2);
				break;
			}
			
		}
		return userlist;
	}
	@Override
	public List<Sys_Role> listSysRole(String factoryid) {
		// TODO Auto-generated method stub
		Doc_Factory fac =  userDao.findFacByID(factoryid);
		Set<Sys_Role> fusers=fac.getSys_roles();
		return new ArrayList(fusers);
	}
	@Override
	public List<Sys_Role> listAllchildRoles(String roleid) {
		// TODO Auto-generated method stub
		 Sys_Role role =userDao.findSysroleByID(roleid);
		 return new ArrayList(role.getChildren());
	}
	@Override
	public Sys_User findByUId(String username) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(username);
	}
	@Override
	public Boolean findByUE(String email, String username) {
		// TODO Auto-generated method stub
		
		List<Sys_User> listusers =userDao.findUserByEmail(email);
		if(listusers.isEmpty())
			return true;
		return false;
	}
	@Override
	public Boolean findByUN(String username, String susernumber) {
		// TODO Auto-generated method stub
		Sys_User suser =userDao.findUserByName(username);
		List<Sys_User> userlist =new ArrayList(suser.getDoc_factory().getSys_users());
		
		for(Sys_User u1: userlist){
			if(susernumber.equals(u1.getSusernumber())){
				return false;
			}
		}
		return true;
	}
	@Override
	public Sys_Role findRolebyID(String roleid) {
		// TODO Auto-generated method stub
		return userDao.findRolebyID(roleid);
	}
	@Override
	public void savesUser(Sys_User newuser) {
		// TODO Auto-generated method stub
		userDao.save(newuser);
	}
	@Override
	public Sys_User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(username);
	}
	@Override
	public void deleteU(String userid) {
		// TODO Auto-generated method stub
		userDao.deleteU(userid);
	}
	@Override
	public Sys_User findUserByID(String userid) {
		// TODO Auto-generated method stub
		return userDao.getById(userid);
	}
	
	@Override
	public void updateUser(Sys_User yluser) {
		// TODO Auto-generated method stub
		userDao.updateuser(yluser);
		//userDao.update(yluser);
	}
	@Override
	public PageBean findpageusers(String currentPage,  String factoryid ,String username, String roleid) {
		// TODO Auto-generated method stub
		return  userDao.findpageusers(currentPage,factoryid,username,roleid);
	}
	@Override
	public void updateLastLogin(Sys_User sys_user) {
		// TODO Auto-generated method stub
		sys_user.setIsonline(1);
		userDao.update(sys_user);
	}
	@Override
	public void updateYlUser(Sys_User yluser) {
		// TODO Auto-generated method stub
		userDao.update(yluser);
	}
	@Override
	public void updateUserName(Sys_User yluser) {
		// TODO Auto-generated method stub
		userDao.update(yluser);
	}
	@Override
	public void updateUsermerge(Sys_User yluser) {
		// TODO Auto-generated method stub
		userDao.updateUsermerge(yluser);
	}
	
	
	

}
