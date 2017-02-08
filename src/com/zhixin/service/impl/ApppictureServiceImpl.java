package com.zhixin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.ApppictureDao;
import com.zhixin.entity.Json_AppPicture;
import com.zhixin.model.App_picture;
import com.zhixin.model.PageBean;
import com.zhixin.service.ApppictureService;


@Service(value="apppictureService")
public class ApppictureServiceImpl implements ApppictureService {

	@Resource(name="apppictureDao")
	private ApppictureDao apppictureDao;

	@Override
	public PageBean findAppPictures(String currentPage, String factoryid) {
		// TODO Auto-generated method stub
		return apppictureDao.findAppPictures( currentPage,  factoryid);
	}

	@Override
	public void savePicture(App_picture app_picture) {
		// TODO Auto-generated method stub
		apppictureDao.save(app_picture);
	}

	@Override
	public void delete_Tp(String picture_id) {
		// TODO Auto-generated method stub
		apppictureDao.delete(picture_id);
		
	}

	@Override
	public List<App_picture> findAppicture_list() {
		// TODO Auto-generated method stub
		return apppictureDao.findAll();
	}

	@Override
	public Map findApppicturesByOpenid(String open_id) {
		// TODO Auto-generated method stub
		return apppictureDao.findApppicturesByOpenid( open_id) ;
	}

	@Override
	public Map findApppicturesByOrginalID(String toUserName) {
		// TODO Auto-generated method stub
		return apppictureDao.findApppicturesByOrginalID( toUserName);
	}

	@Override
	public Map findApppacksByOpenid(String open_id) {
		// TODO Auto-generated method stub
		return apppictureDao.findApppacksByOpenid( open_id);
	}

	@Override
	public Map findApppacksByOrginalID(String toUserName) {
		// TODO Auto-generated method stub
		return apppictureDao.findApppacksByOrginalID( toUserName);
	}

	@Override
	public List<App_picture> findApppicturelist(String factory_id) {
		// TODO Auto-generated method stub
		return apppictureDao.findApppicture_list(factory_id);
	}

	@Override
	public List<Json_AppPicture> findAppPicturesByFactoryid(String factoryid) {
		// TODO Auto-generated method stub
		 return apppictureDao.findAppPicturesByFactoryid(factoryid);
	}
}
