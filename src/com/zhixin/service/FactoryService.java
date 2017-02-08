package com.zhixin.service;

import java.util.List;

import com.zhixin.entity.Json_Sys_picture;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;

public interface FactoryService {

	Doc_Factory findFactoryById(String factoryId);

	void updateFactory(Doc_Factory factory);

	void updatefacStatus(String factoryid, String status);

	List<Doc_Factory> findFactorysByIDS(String[] arrayids);

	PageBean findFactorys(String currentPage, String status, String facname, String com_id);

	void updatecheckfac_status();




	/**
	 * appJson数据查询
	 * @param factoryId
	 * @return
	 */
	List<Json_Sys_picture> findFacturePictureByFactoryId(String factoryId, String currentPage);
}
