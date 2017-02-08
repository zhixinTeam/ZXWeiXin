/**
 * 
 */
package com.zhixin.dao;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.Sys_Role;

/**
 * @author big bug
 *
 */
public interface RoleDao extends DaoSupport<Sys_Role>{

	void updateRoleAuth(String roleid, String authids);

	boolean findUserBYroleid(String roleid);

	boolean findRoleByParentID(String delrolid);

	void deleterole(String delrolid);

}
