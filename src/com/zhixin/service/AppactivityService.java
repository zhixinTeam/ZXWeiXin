package com.zhixin.service;

import java.util.List;

import com.zhixin.entity.Json_AppActivity;
import com.zhixin.model.App_activity;
import com.zhixin.model.PageBean;

public interface AppactivityService {

	PageBean findpageactivity(String factoryid, String currentPage);

	void saveAppactivity(App_activity activity);

	void delete_Tp(String appactivity_id);

	List<App_activity> findAppactivity_list(String factory_id);
	/**
	 * 通过id查询App_activity
	 * @param id
	 * @return
	 */
	App_activity findAppactivityById(String id);
	/**
	 * 修改公司主页宣传信息
	 * @param appactivity
	 */
	void updateApp_activity(App_activity appactivity);
	List<Json_AppActivity> findAppActivityByFactoryid(String factoryid);
}
