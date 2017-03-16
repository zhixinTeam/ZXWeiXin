package com.zhixin.dao;

import java.util.List;

import com.zhixin.base.DaoSupport;
import com.zhixin.entity.Json_Doc_Factory;
import com.zhixin.entity.Json_Sys_picture;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.PageBean;

public interface FactoryDao extends DaoSupport<Doc_Factory> {


	PageBean findFactorys(String currentPage, String status, String facname, String com_id);

	void checkfac_status();

	Doc_Factory findFactoryById(String factoryId);

	
	
	
	/**
	 * appJson数据查询
	 * @param factoryId
	 * @return
	 */
	List<Json_Sys_picture> findFacturePictureByFactoryId(String factoryId,String currentPage);

	List<Doc_Factory> findFactoryByComID(String companyid);
	
	/**
	 * 通过客户ID查询出与客户关联的工厂信息
	 * @param c_id
	 * @return
	 */
	List<Doc_Factory> findFactorysByCustomerId(String c_id);
	
	List<Json_Doc_Factory> findFactoryByCompanyID(String companyid);
	
	/**
	 * 通过客户ID查询出与客户关联的工厂信息
	 * @param c_id
	 * @return
	 */
	List<Json_Doc_Factory> findFactoryByCustomerId(String c_id);
}
