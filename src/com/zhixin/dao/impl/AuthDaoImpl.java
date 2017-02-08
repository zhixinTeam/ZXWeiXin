package com.zhixin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.AuthDao;
import com.zhixin.model.Link_Roles_Auths;
import com.zhixin.model.Sys_Auth;
import com.zhixin.right_utils.PageData;

@Service(value="authDao")
public class AuthDaoImpl extends DaoSupportImpl<Sys_Auth> implements AuthDao {

	@Override
	public List<Sys_Auth> findAuthByPID(String aUTH_ID) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("from Sys_Auth a where a.parent.id = '"+aUTH_ID+"'");  //带条件的查询语句     
		List<Sys_Auth> list=query.list();
		
		return list;
		  
	}

	@Override
	public void editicon(PageData pd) {
		// TODO Auto-generated method stub
		String authid=pd.getString("MENU_ID");
		String iconcls =pd.getString("MENU_ICON");
		Query query = getSession().createQuery("update Sys_Auth t set t.iconcls = '"+iconcls+"' where id = '"+authid+"'");
		query.executeUpdate();
	}

	@Override
	public List<Sys_Auth> findparentauth() {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("from Sys_Auth a where a.parent.id is null");  //带条件的查询语句     
		List<Sys_Auth> list=query.list();
		return list;
	}

	@Override
	public void updateAuthtoParent(Sys_Auth authmodel, String pARENT_ID) {
		// TODO Auto-generated method stub
		getSession().flush();
        getSession().clear();
        //不要用update()方法
        Sys_Auth pauth =this.getById(pARENT_ID);
        authmodel.setParent(pauth);
        getSession().merge(authmodel);
	}

	@Override
	public void deleteauthById(String authid) {
		// TODO Auto-generated method stub
		//删关联需要把子菜单的关联一并删除
		Query querylist=getSession().createQuery("from Sys_Auth a where a.parent.id = '"+authid+"'");  //带条件的查询语句     
		List<Sys_Auth> list=querylist.list();
		for(Sys_Auth auth:list){
			String hbnasql1 ="delete from auths_roles  where A_ID ='"+auth.getId()+"'";
			SQLQuery query4 = getSession().createSQLQuery(hbnasql1);
			query4.executeUpdate();
		}
		String hbnasql ="delete from auths_roles  where A_ID ='"+authid+"'";
		SQLQuery query = getSession().createSQLQuery(hbnasql);
		SQLQuery query1 =getSession().createSQLQuery("delete from Sys_Auth  where parentid ='"+authid+"'");
		SQLQuery query2 =getSession().createSQLQuery("delete from Sys_Auth  where id ='"+authid+"'");
		query.executeUpdate();
		query1.executeUpdate();
		query2.executeUpdate();
		
		
	}

	@Override
	public List<Sys_Auth> findAuthByRoleID(String roleid) {
		// TODO Auto-generated method stub
		//获取关联表集合
		Query querylist=getSession().createQuery("from Link_Roles_Auths l where l.role.id = '"+roleid+"'");  //带条件的查询语句     
		List<Link_Roles_Auths> listlinkras=querylist.list();
		List<Sys_Auth> listauth = new ArrayList<>();
		for(Link_Roles_Auths role_auth :listlinkras){
			Sys_Auth auth = new Sys_Auth();
			role_auth.getAuth().getAuthname();
			auth =role_auth.getAuth();
			listauth.add(auth);
		}
		return listauth;
	}

		
	
		
		
	
}
