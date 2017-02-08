package com.zhixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.ApppackDao;
import com.zhixin.entity.Json_Apppack;
import com.zhixin.model.App_pack;
import com.zhixin.model.PageBean;
import com.zhixin.service.ApppackService;
@Service(value="apppackService")
public class ApppackServiceImpl implements ApppackService {
	
	@Resource(name="apppackDao")     
	private ApppackDao apppackDao;
	
	@Override
	public PageBean findpageapppack(String factoryid, String currentPage) {
		// TODO Auto-generated method stub
		return apppackDao.findpageapppack(factoryid,currentPage);
	}

	@Override
	public void saveAppactivity(App_pack app_pack) {
		// TODO Auto-generated method stub
		apppackDao.save(app_pack);
	}

	@Override
	public void delete_Tp(String apppack_id) {
		// TODO Auto-generated method stub
		apppackDao.delete(apppack_id);
	}

	@Override
	public List<App_pack> findAppack_list() {
		// TODO Auto-generated method stub
		return apppackDao.findAll();
	}

	@Override
	public App_pack findAppackByid(String apppackid) {
		// TODO Auto-generated method stub
		return apppackDao.getById(apppackid);
	}

	@Override
	public void updateApp_pack(App_pack app_pack) {
		// TODO Auto-generated method stub
		apppackDao.update(app_pack);
	}

	@Override
	public List<Json_Apppack> findAppPackByFactoryid(String factoryId,String currentPage) {
		// TODO Auto-generated method stub
		return apppackDao.findAppPackByFactoryid(factoryId,currentPage);
	}

	@Override
	public Json_Apppack findAppPackByid(String id) {
		// TODO Auto-generated method stub
		return apppackDao.findAppPackByid(id);
	}

	@Override
	public List<Json_Apppack> findAppPackNewsBack(String currentPage,String factoryId) {
		// TODO Auto-generated method stub
		return apppackDao.findAppPackNewsBack(currentPage,factoryId);
	}

}
