package com.zhixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.PicturesDao;
import com.zhixin.model.PageBean;
import com.zhixin.model.Sys_Picture;
import com.zhixin.service.PicturesService;
@Service(value="picturesService")
public class PicturesServiceImpl  implements PicturesService{

	
	
	@Resource(name="picturesDao")
	private PicturesDao picturesDao;
	
	
	@Override
	public void savePicture(Sys_Picture sys_picture) {
		// TODO Auto-generated method stub
		picturesDao.save(sys_picture);
	}


	@Override
	public PageBean findPictures(String query_title,String currentPage, String id) {
		// TODO Auto-generated method stub
		return picturesDao.findPictures( query_title,currentPage, id);
	}


	@Override
	public Sys_Picture findPictureById(String picture_id) {
		// TODO Auto-generated method stub
		return picturesDao.findPictureByID(picture_id);
	}


	@Override
	public void delete_Tp(String picture_id) {
		// TODO Auto-generated method stub
		Sys_Picture picture = picturesDao.getById(picture_id);
		picture.setPath("");
		picture.setName("");
		picturesDao.update(picture);
	}


	@Override
	public void update_Picture(Sys_Picture old_picture) {
		// TODO Auto-generated method stub
		picturesDao.update(old_picture);
	}


	@Override
	public void delete_pictureByID(String picture_id) {
		// TODO Auto-generated method stub
		picturesDao.delete(picture_id);
	}


	

	@Override
	public List<Sys_Picture> findPicturesByOpenid(String open_id) {
		// TODO Auto-generated method stub
		return picturesDao.findPicturesByOpenid(open_id);
	}


	@Override
	public List<Sys_Picture> findPicturesByFactoryids(String[] factory_IDs) {
		// TODO Auto-generated method stub
		return picturesDao.findPicturesByFactoryid(factory_IDs);
	}


	@Override
	public void update_PictureLog(Sys_Picture picture) {
		// TODO Auto-generated method stub
		picturesDao.update_PictureLog(picture);
	}


	@Override
	public List<Sys_Picture> findPictureByOrginalID(String orginalID) {
		// TODO Auto-generated method stub
		return picturesDao.findPicturesByOrginalID(orginalID);
	}


	@Override
	public Sys_Picture findpictoryBy_flag() {
		// TODO Auto-generated method stub
		return picturesDao.findPictoryBy_flag();
	}

}
