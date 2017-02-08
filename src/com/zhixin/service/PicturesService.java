package com.zhixin.service;

import java.util.List;

import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Picture;

public interface PicturesService {

	void savePicture(Sys_Picture sys_picture);

	PageBean findPictures(String query_title,String currentPage, String id);

	Sys_Picture findPictureById(String picture_id);

	void delete_Tp(String picture_id);

	void update_Picture(Sys_Picture old_picture);

	void delete_pictureByID(String picture_id);

	
	List<Sys_Picture> findPicturesByOpenid(String open_id);

	List<Sys_Picture> findPicturesByFactoryids(String[] factory_IDs);

	void update_PictureLog(Sys_Picture picture);

	List<Sys_Picture> findPictureByOrginalID(String toUserName);

	Sys_Picture findpictoryBy_flag();

}
