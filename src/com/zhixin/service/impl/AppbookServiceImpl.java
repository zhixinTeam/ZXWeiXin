package com.zhixin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.AppbookDao;
import com.zhixin.model.App_book;
import com.zhixin.model.PageBean;
import com.zhixin.service.AppbookService;

@Service(value="appbookService")
public class AppbookServiceImpl  implements AppbookService{
	
	
		@Resource(name="appbookDao")
		private AppbookDao appbookDao;

		@Override
		public PageBean findpageappbook(String factoryid, String currentPage) {
			// TODO Auto-generated method stub
			return appbookDao.findpageappbook( factoryid,  currentPage);
		}

		@Override
		public void saveAppbook(App_book appbook) {
			// TODO Auto-generated method stub
			appbookDao.save(appbook);
			
		}

}
