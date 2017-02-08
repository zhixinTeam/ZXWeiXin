/**
 * 
 */
package com.zhixin.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.RoleDao;
import com.zhixin.model.Sys_Auth;
import com.zhixin.model.Sys_Role;
import com.zhixin.service.RoleService;

/**
 * @author big bug
 *
 */
@Service(value="roleService")
public class RoleServiceImpl implements RoleService{

	/* (non-Javadoc)
	 * @see com.zhixin.service.RoleService#findRoleByID(java.lang.String)
	 */
	@Resource(name="roleDao")
	private RoleDao roleDao;
	
	
	@Override
	public Sys_Role findRoleByID(String id) {
		// TODO Auto-generated method stub
		return roleDao.getById(id);
	}


	@Override
	public void saveRole(Sys_Role new_role) {
		// TODO Auto-generated method stub
		roleDao.save(new_role);
	}


	@Override
	public String deleteService(String delrolid) {
		// TODO Auto-generated method stub
		//判断角色下是否有员工，如果有员工先删除员工
		boolean flag = roleDao.findUserBYroleid(delrolid);
		boolean childrole =roleDao.findRoleByParentID(delrolid);
		if(flag && childrole){
			roleDao.deleterole(delrolid);
			return "true";
		}else if(flag ==false){
			return "falseuser";
		}else {
			return "falsechildrole";
		}
			
	}


	@Override
	public Sys_Role findRoleById(String rOLE_ID) {
		// TODO Auto-generated method stub
		return roleDao.getById(rOLE_ID);
	}


	@Override
	public void updateRole(Sys_Role updaterole) {
		// TODO Auto-generated method stub
		roleDao.update(updaterole);
	}


	@Override
	public void updateRoleauth(String roleid, String authids) {
		// TODO Auto-generated method stub
		
		roleDao.updateRoleAuth(roleid,authids);
		
	}


	

}
