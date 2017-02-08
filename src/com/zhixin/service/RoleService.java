/**
 * 
 */
package com.zhixin.service;

import com.zhixin.model.Sys_Role;

/**
 * @author big bug
 *
 */
public interface RoleService {

	/**
	 * @param id
	 * @return 
	 */
	Sys_Role findRoleByID(String id);

	void saveRole(Sys_Role new_role);

	String deleteService(String delrolid);

	Sys_Role findRoleById(String rOLE_ID);

	void updateRole(Sys_Role updaterole);

	void updateRoleauth(String rOLE_ID, String menuIds);

}
