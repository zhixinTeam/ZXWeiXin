package com.zhixin.dao;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.entity.Json_Apppack;
import com.zhixin.model.App_pack;
import com.zhixin.model.PageBean;

public interface ApppackDao extends DaoSupport<App_pack>{

	/**
	 * 公告分页
	 * @param factoryid
	 * @param currentPage
	 * @return
	 */
	PageBean findpageapppack(String factoryid, String currentPage);
	/**
	 * 通过id查询要修改的公告的信息
	 * @param id
	 * @return
	 */
	App_pack findApp_packById(String id);
	/**
	 * 修改公告信息
	 * @param apppack
	 */
	void updateApp_pack(App_pack apppack);
	
	/**
	 * 分页查询新闻公告的json数据
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
	List<Json_Apppack> findAppPackNewsBack(String currentPage,String factoryid);
}
