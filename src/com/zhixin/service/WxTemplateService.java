package com.zhixin.service;

import com.zhixin.model.Doc_Company;
import com.zhixin.model.PageBean;
import com.zhixin.model.X_Msg_Type;

public interface WxTemplateService {

	/**
	 * 公告分页
	 * @param factoryid
	 * @param currentPage
	 * @return
	 */
	PageBean findpageX_Msg_Type(String currentPage,String com_id);
	/**
	 * 通过id查询要修改的公告的信息
	 * @param id
	 * @return
	 */
	X_Msg_Type findX_Msg_TypeById(String id);
	/**
	 * 修改公告信息
	 * @param apppack
	 */
	void updateX_Msg_Type(X_Msg_Type x_Msg_Type);
	
	/**
	 * 删除公告信息
	 * @param apppack
	 */
	void deleteX_Msg_Type(X_Msg_Type x_Msg_Type);
	
	/**
	 * 新增公告信息
	 * @param apppack
	 */
	void saveX_Msg_Type(X_Msg_Type x_Msg_Type);

	/** 
	 * 通过typebs doc_company查询要修改的公告的信息
	 * @param typebs
	 * @param doc_company
	 * @return
	 */
	X_Msg_Type findX_Msg_TypeByTypebsAndcom_id(String typebs, Doc_Company doc_company);
}
