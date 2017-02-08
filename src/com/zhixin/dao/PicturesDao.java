package com.zhixin.dao;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Picture;

public interface PicturesDao  extends DaoSupport<Sys_Picture>{

	PageBean findPictures(String query_title,String currentPage, String id);

	Sys_Picture findPictureByID(String picture_id);


	List<Sys_Picture> findPicturesByOpenid(String open_id);

	List<Sys_Picture> findPicturesByFactoryid(String[] factory_IDs);

	void update_PictureLog(Sys_Picture picture);

	List<Sys_Picture> findPicturesByOrginalID(String orginalID);

	Sys_Picture findPictoryBy_flag();

}
