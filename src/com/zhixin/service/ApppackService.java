package com.zhixin.service;

import java.util.List;

import com.zhixin.entity.Json_Apppack;
import com.zhixin.model.App_pack;
import com.zhixin.model.PageBean;

public interface ApppackService {

	PageBean findpageapppack(String factoryid, String currentPage);

	void saveAppactivity(App_pack app_pack);

	void delete_Tp(String apppack_id);

	List<App_pack> findAppack_list();

	App_pack findAppackByid(String apppackid);
	
	void updateApp_pack(App_pack app_pack);
	/**
	 * 查询新闻公告的json数据
	 * @param factoryId
	 * @return
	 */
	List<Json_Apppack> findAppPackByFactoryid(String factoryId,String currentPage);
	
	/**
	 * 通过id查询单个公告信息的详细的json数据
	 * @param id
	 * @return
	 */
	Json_Apppack findAppPackByid(String id);
	/**
	 * 通过页面查询返回的新闻列表显示的新闻
	 * @param currentPage
	 * @return
	 */
	List<Json_Apppack> findAppPackNewsBack(String currentPage,String factoryId);

}
