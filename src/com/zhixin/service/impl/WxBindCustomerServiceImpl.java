package com.zhixin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.zhixin.dao.WxBindCustomerDao;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Link_BindCustomers_Factorys;
import com.zhixin.model.PageBean;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Sys_User;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.Wx_BindUser;
import com.zhixin.service.WxBindCustomerService;

@Service(value="wxbindcustomerService")
public class WxBindCustomerServiceImpl  implements WxBindCustomerService{

	
	@Resource(name="wxbindcustomerDao")
	private WxBindCustomerDao wxbindcustomerDao;
	@Override
	public PageBean findpagewxbindcustomer(String factoryid , String currentPage ,String status,String namepinyin ) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findpagewxbindcustomer(factoryid,currentPage,status,namepinyin);
	}
	@Override
	public List<Doc_Factory> findFactoryByOriginalID(String originalID) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findFactoryByOriginalID(originalID);
	}
	@Override
	public void save_wxbindUser(Wx_BindCustomer wx_bind ,List <Doc_Factory> listfactorys) {
		// TODO Auto-generated method stub
		wxbindcustomerDao.save_wxbindUser(wx_bind,listfactorys);
	}
	@Override
	public List<Doc_Factory> findFactoryByOpenID(String openid) {
		// TODO Auto-generated method stub
		Wx_BindCustomer wx_bindcustomer =  wxbindcustomerDao.findFactoryByOpenID(openid);
		List<Doc_Factory> listfacs =	new ArrayList<>();
		if(wx_bindcustomer !=null){
			//TODO 关联对象修改
			for(Link_BindCustomers_Factorys link_bind_fac: wx_bindcustomer.getBindcustomer_factorys()){
				Doc_Factory fac =link_bind_fac.getFactory();
				fac.setIs_bind(link_bind_fac.getIsbind());
				listfacs.add(fac);
			}
			
		}
		return listfacs;
	}
	@Override
	public Wx_BindCustomer findwxbindcustomerByOpenID(String openid) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findwxbindcustomerByOpenID(openid);
	}
	@Override
	public void update_wxbindUser(Wx_BindCustomer wx_bind ,List <Doc_Factory> listfactorys ) {
		// TODO Auto-generated method stub
		wxbindcustomerDao.update_wxbindUser(wx_bind , listfactorys);
		
		
	}
	@Override
	public Wx_BindCustomer findwxbindcustomerByID(String flagID) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.getById(flagID);
	}
	@Override
	public void update_wxbindUserStatus(Wx_BindCustomer wx_bind) {
		// TODO Auto-generated method stub
		wxbindcustomerDao.update_wxbindUserStatus(wx_bind);
	}
	
	
	//根据工厂id查询关注公众号的一般用户
	@Override
	public List<Wx_BindUser> findwxUserByFactoryID(String factoryid) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findwxUserByFactoryID( factoryid);
	}
	//查询注册的用户
	@Override
	public List<Wx_BindCustomer> findwxbindcusUserByFactoryID(String factoryid) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findwxbindcusUserByFactoryID( factoryid);
	}
	@Override
	public Set<Sys_User> findsysUserByFactoryID(String factoryid) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findsysUserByFactoryID( factoryid);
	}
	@Override
	public Wx_BindCustomer findFactoryByPhone(String phone, String factoryid) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findsysUserByFactoryID( phone,factoryid);
	}
	@Override
	public Wx_BindCustomer findByUSername(String newusername) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findByUSername( newusername) ;
	}
	@Override
	public List<Shop_Client> findClientByCustomerId(String customerid, String factoryid) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findClientByCustomerId(customerid,factoryid);
	}
	@Override
	public Set<Wx_BindCustomer> findBindCustomerFactoryID(String tracknumber, String factoryid) {
		// TODO Auto-generated method stub
		return wxbindcustomerDao.findBindCustomerFactoryID(tracknumber,factoryid);
	}
	@Override
	public void saveCustomer(Wx_BindCustomer wx_bind) {
		// TODO Auto-generated method stub
		wxbindcustomerDao.saveCustomer(wx_bind);
	}
	
	
	
	

}
