package com.zhixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.FactoryDao;
import com.zhixin.entity.Json_Doc_Factory;
import com.zhixin.entity.Json_Sys_picture;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;
import com.zhixin.service.FactoryService;

@Service(value="factoryService")
public class FactoryServiceImpl implements FactoryService {

	
	@Resource(name="factorydao")
	private FactoryDao factorydao;

	
	@Override
	public List<Doc_Factory> findFactorysByIDS(String[] arrayids) {
		// TODO Auto-generated method stub
		return factorydao.getByIds(arrayids);
	}
	
	@Override
	public Doc_Factory findFactoryById(String factoryId) {
		// TODO Auto-generated method stub
		//factorydao.ge
		return factorydao.findFactoryById(factoryId);
	}

	@Override
	public void updateFactory(Doc_Factory factory) {
		// TODO Auto-generated method stub
		factorydao.update(factory);
	}

	@Override
	public void updatefacStatus(String factoryid, String status) {
		// TODO Auto-generated method stub
		Doc_Factory factory = factorydao.getById(factoryid);
		if("1".equals(status)){
			factory.setStatus(0);
			factory.setEditlog("快速关闭服务！");
		}else{
			factory.setStatus(1);
			factory.setEditlog("快速打开服务！");
		}
			
		factorydao.update(factory);
	}

	

	@Override
	public PageBean findFactorys(String currentPage, String status, String facname, String com_id) {
		// TODO Auto-generated method stub
		return factorydao.findFactorys(currentPage,status,facname,com_id);
	}

	@Override
	public void updatecheckfac_status() {
		// TODO Auto-generated method stub
		factorydao.checkfac_status();
	}

	@Override
	public List<Json_Sys_picture> findFacturePictureByFactoryId(String factoryId, String currentPage) {
		// TODO Auto-generated method stub
		return factorydao.findFacturePictureByFactoryId(factoryId,currentPage);
	}

	@Override
	public List<Doc_Factory> findFactoryByComID(String companyid) {
		// TODO Auto-generated method stub
		return factorydao.findFactoryByComID( companyid);
	}

	@Override
	public List<Json_Doc_Factory> findFactoryByCompanyID(String companyid) {
		// TODO Auto-generated method stub
		return factorydao.findFactoryByCompanyID(companyid);
	}

	@Override
	public List<Json_Doc_Factory> findFactoryByCustomerId(String c_id) {
		// TODO Auto-generated method stub
		return factorydao.findFactoryByCustomerId(c_id);
	}

	@Override
	public List<Doc_Factory> findFactorysByCustomerId(String c_id) {
		// TODO Auto-generated method stub
		return factorydao.findFactorysByCustomerId(c_id);
	}

	

	
	
}
