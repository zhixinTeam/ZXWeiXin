package com.zhixin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;





import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.UserDao;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Role;
import com.zhixin.model.Sys_User;
@Service(value="userDao")
public class UserDaoImpl extends DaoSupportImpl<Sys_User> implements UserDao {

	
	private JdbcTemplate jdbcTemplate; 
	
	
	@Override
	public Sys_User findUserByNameAndPwd(String passwd){
		// TODO Auto-generated method stub
		/*String queryString="from Sys_User f where f.password=?";
		Query queryObject =sf.getCurrentSession().createQuery(queryString);
		queryObject.setParameter(0, passwd);
		ArrayList list_user =(ArrayList) queryObject.list();
		if(list_user.size()>0)
			return  (Sys_User) list_user.get(0);
		return sys_user;*/
		return (Sys_User) getSession().createQuery(//
				"from Sys_User f where f.password=?")//
				.setParameter(0, passwd)//
				.uniqueResult();
	}

	@Override
	public List<Sys_User> listSysUsers(String id, String factoryid) {
		// TODO Auto-generated method stub
		String queryString="from Sys_User U left join U.doc_factory as f  where  f.id=? and U.id !=?";
		Query queryObject =this.getSession().createQuery(queryString);
		queryObject.setParameter(0, factoryid);
		queryObject.setParameter(1, id);
		return queryObject.list();
	}

	@Override
	public Doc_Factory findFacByID(String factoryid) {
		// TODO Auto-generated method stub
		return (Doc_Factory) getSession().createQuery(//
				"from Doc_Factory f where f.id=?")//
				.setParameter(0, factoryid)//
				.uniqueResult();
	}

	@Override
	public Sys_Role findSysroleByID(String roleid) {
		// TODO Auto-generated method stub
		return (Sys_Role) getSession().createQuery(//
				"from Sys_Role f where f.id=?")//
				.setParameter(0, roleid)//
				.uniqueResult();
	}

	@Override
	public Sys_User findUserByName(String username) {
		// TODO Auto-generated method stub
		return (Sys_User) getSession().createQuery(//
				"from Sys_User f where f.username=?")//
				.setParameter(0, username)//
				.uniqueResult();
	}

	@Override
	public List<Sys_User> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return getSession().createQuery(//
				"from Sys_User f where f.email=?")//
				.setParameter(0, email).list();
	}

	@Override
	public Sys_Role findRolebyID(String roleid) {
		// TODO Auto-generated method stub
		return (Sys_Role) getSession().createQuery(//
				"from Sys_Role f where f.id=?")//
				.setParameter(0,roleid)//
				.uniqueResult();
	}

	@Override
	public void deleteU(String userid) {
		   // TODO Auto-generated method stub
		   String hql="delete Sys_User as p where p.id=?";
		   Query query=getSession().createQuery(hql);
		   query.setString(0,userid);
		   query.executeUpdate();
		
	}

	//修改user 与role
	@Override
	public void updateuser(Sys_User yluser) {
		// TODO Auto-generated method stub
		getSession().flush();
        getSession().clear();
        //不要用update()方法
        //getSession().merge(yluser);
	}

	@Override
	public PageBean findpageusers(String currentPage, String factoryid ,String username, String roleid) {
		// TODO Auto-generated method stub
		int curpage =Integer.parseInt(currentPage);
		int firdata =(curpage-1)*10;
		String userparam =username.trim();
		String roleidparam =roleid.trim();
		String param ="";
		if(! userparam.equals(""))
			param+="   u.username like '%"+userparam+"%'  and";
		if(! roleidparam.equals(""))
			param+="   u.sys_role.id = '"+roleidparam+"'  and";
		Query query=getSession().createQuery("from Sys_User u where "+param+"  u.doc_factory.id='"+factoryid+"' ");  //带条件的查询语句  

		  int count =query.list().size();
		  
		  query.setFirstResult(firdata);  
		  query.setMaxResults(10);  
		  List<Sys_User> list=query.list();
		  while (list.size()==0 && curpage >1){
			   curpage -=1;
			   firdata =(curpage-1)*10;
			   query=getSession().createQuery("from Sys_User u where "+param+"   u.doc_factory.id='"+factoryid+"' ");  //带条件的查询语句  
			   count =query.list().size();
			   query.setFirstResult(firdata);  
			   query.setMaxResults(10);  
			   list=query.list();
			   if(list.size()>0)
				   break;
		  }
		  for(Sys_User user:list){
			  user.getSys_role().getRolename();
		  }
		 PageBean pagebean = new PageBean( curpage, 10, count, list);
		return pagebean;
	}

	@Override
	public void updateUsermerge(Sys_User yluser) {
		// TODO Auto-generated method stub
		getSession().flush();
        getSession().clear();
        //不要用update()方法
        getSession().merge(yluser);
	}
	
	
	
	
	

}
