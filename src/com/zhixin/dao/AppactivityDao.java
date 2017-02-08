package com.zhixin.dao;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.App_activity;
import com.zhixin.model.PageBean;
public interface AppactivityDao extends DaoSupport<App_activity> {

	PageBean findpageactivity(String factoryid, String currentPage);

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
	
}
