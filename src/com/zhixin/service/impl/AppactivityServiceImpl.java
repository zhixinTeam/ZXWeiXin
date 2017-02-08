package com.zhixin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.AppactivityDao;
import com.zhixin.entity.Json_AppActivity;
import com.zhixin.model.App_activity;
import com.zhixin.model.PageBean;
import com.zhixin.service.AppactivityService;
@Service(value="appactivityService")
public class AppactivityServiceImpl implements AppactivityService {
	
	@Resource(name="appactivitydao")
	private AppactivityDao appactivitydao;

	@Override
	public PageBean findpageactivity(String factoryid, String currentPage) {
		// TODO Auto-generated method stub
		return appactivitydao.findpageactivity( factoryid,  currentPage);
	}

	@Override
	public void saveAppactivity(App_activity activity) {
		// TODO Auto-generated method stub
		appactivitydao.save(activity);
	}

	@Override
	public void delete_Tp(String appactivity_id) {
		// TODO Auto-generated method stub
		appactivitydao.delete(appactivity_id);
	}

	@Override
	public List<App_activity> findAppactivity_list(String factory_id) {
		// TODO Auto-generated method stub
		return appactivitydao.findAppactivity_list( factory_id);
	}

	@Override
	public App_activity findAppactivityById(String id) {
		// TODO Auto-generated method stub
		return appactivitydao.findAppactivityById(id);
	}

	@Override
	public void updateApp_activity(App_activity appactivity) {
		// TODO Auto-generated method stub
		appactivitydao.updateApp_activity(appactivity);
	}

	@Override
	public List<Json_AppActivity> findAppActivityByFactoryid(String factoryid) {
		// TODO Auto-generated method stub


		List<App_activity> app_list = appactivitydao.findAppactivity_list(factoryid);
		List<Json_AppActivity> list_json = new ArrayList<>();
		for(App_activity activity: app_list){
			Json_AppActivity json_app =new Json_AppActivity();
			json_app.setContext(activity.getContext());
			json_app.setCreatedate(activity.getCreatedate());
			json_app.setDatetime(activity.getDatetime());
			json_app.setId(activity.getId());
			json_app.setPath(activity.getPath());
			json_app.setPicname(activity.getPicname());
			json_app.setTitle(activity.getTitle());;
			
			
			list_json.add(json_app);
		}
		return list_json;
	}
}
