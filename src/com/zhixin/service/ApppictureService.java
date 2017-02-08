package com.zhixin.service;

import java.util.List;
import java.util.Map;

import com.zhixin.entity.Json_AppPicture;
import com.zhixin.model.App_activity;
import com.zhixin.model.App_picture;
import com.zhixin.model.PageBean;

public interface ApppictureService {

	PageBean findAppPictures(String currentPage, String factoryid);

	void savePicture(App_picture app_picture);

	void delete_Tp(String picture_id);

	List<App_picture> findAppicture_list();

	Map findApppicturesByOpenid(String open_id);

	Map findApppicturesByOrginalID(String toUserName);

	Map findApppacksByOpenid(String open_id);

	Map findApppacksByOrginalID(String toUserName);
	
	List<App_picture> findApppicturelist(String factory_id);
	
	List<Json_AppPicture> findAppPicturesByFactoryid(String factoryid);
}
