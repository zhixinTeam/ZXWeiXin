package com.zhixin.dao;

import java.util.List;
import java.util.Map;

import com.zhixin.base.DaoSupport;
import com.zhixin.entity.Json_AppPicture;
import com.zhixin.model.App_picture;
import com.zhixin.model.PageBean;

public interface ApppictureDao  extends DaoSupport<App_picture>{

	List<App_picture> findApppicture_list(String factory_id);
	
	PageBean findAppPictures(String currentPage, String factoryid);

	Map findApppicturesByOpenid(String open_id);

	Map findApppicturesByOrginalID(String toUserName);

	Map findApppacksByOpenid(String open_id);

	Map findApppacksByOrginalID(String toUserName);

	List<Json_AppPicture> findAppPicturesByFactoryid(String factoryid);
}
