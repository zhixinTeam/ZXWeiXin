/**
 * 
 */
package com.zhixin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.RoleDao;
import com.zhixin.model.Link_Roles_Auths;
import com.zhixin.model.Sys_Auth;
import com.zhixin.model.Sys_Role;

/**
 * @author big bug
 *
 */
@Service(value="roleDao")
public class RoleDaoImpl  extends DaoSupportImpl<Sys_Role> implements RoleDao{
	//XXXX修改关联关系
	@Override
	public void updateRoleAuth(String roleid, String authids) {
		// TODO Auto-generated method stub
			//删除原先的roleid
		   String hql="delete Link_Roles_Auths  as l where l.role.id=?";
		   Query querydel=getSession().createQuery(hql);
		   querydel.setString(0,roleid);
		   querydel.executeUpdate();
		   
		   String authidin="";
		    String array[] =authids.split(",");
		    for(int i=0;i<array.length;i++){
		    	if(i+1== array.length)
		    		authidin+="'"+array[i]+"'";
		    	else
		    		authidin+="'"+array[i]+"',";
		    }
		   Query query=getSession().createQuery("from Sys_Auth a  where  a.id in("+authidin+")");  //带条件的查询语句  
			List<Sys_Auth> listauth = query.list();
		   Sys_Role role =	this.getById(roleid);
		   
		   for(Sys_Auth auth :listauth){
			   Link_Roles_Auths link_role_auth = new Link_Roles_Auths();
			   link_role_auth.setAuth(auth);
			   link_role_auth.setRole(role);
			   this.getSession().merge(link_role_auth);
		   }
		   
	}

	@Override
	public boolean findUserBYroleid(String roleid) {
		// TODO Auto-generated method stub
		//from Sys_User U left join U.doc_factory as f  where  f.id=? and U.id !=?
		String queryString="from Sys_User U left join U.sys_role as r  where  r.id=?";
		Query queryObject =this.getSession().createQuery(queryString);
		queryObject.setParameter(0, roleid);
		if(queryObject.list().size()>0)
			return false;
		else
			return true;
		
	}

	@Override
	public boolean findRoleByParentID(String delrolid) {
		// TODO Auto-generated method stub
		Sys_Role role =(Sys_Role)getSession().createQuery(//
				"from Sys_Role f where f.id=?")//
				.setParameter(0, delrolid)//
				.uniqueResult();
		if(role.getChildren().size()>0)
			return false;
		else
			return true;
	}

	@Override
	public void deleterole(String delrolid) {
		// TODO Auto-generated method stub
		/*
		 * 角色删除先删除角色菜单管理
		 * 再删除角色
		 */
		SQLQuery sqlquery =getSession().createSQLQuery("delete from link_roles_auths  where RoleID =?");
		sqlquery.setParameter(0, delrolid);
		sqlquery.executeUpdate();
		
		SQLQuery sqlrolquery =getSession().createSQLQuery("delete from sys_role  where id= ?");
		sqlrolquery.setParameter(0, delrolid);
		sqlrolquery.executeUpdate();
		
	}

}
