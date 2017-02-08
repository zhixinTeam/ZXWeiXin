package com.zhixin.dao;

import com.zhixin.base.DaoSupport;
import com.zhixin.model.App_book;
import com.zhixin.model.PageBean;

public interface AppbookDao extends DaoSupport<App_book> {

	PageBean findpageappbook(String factoryid, String currentPage);

}
