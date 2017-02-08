package com.zhixin.service;

import com.zhixin.model.App_book;
import com.zhixin.model.PageBean;

public interface AppbookService {

	PageBean findpageappbook(String factoryid, String currentPage);

	void saveAppbook(App_book appbook);

}
