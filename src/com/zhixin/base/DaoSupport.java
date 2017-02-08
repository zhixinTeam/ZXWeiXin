package com.zhixin.base;

import java.util.List;

import com.zhixin.model.PageBean;
import com.zhixin.right_utils.QueryHelper;

public interface DaoSupport<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(String id);

	/**
	 * 按id查询
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(String[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 公共的查询分页信息的方法
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param hql
	 *            查询数据列表的HQL
	 * @param parameters
	 *            参数列表，与HQL中问号一一对应
	 * @return
	 */
	@Deprecated
	PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters);

	/**
	 * 公共的查询分页信息的方法（最终版）
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param queryHelper
	 *            HQL语句与参数列表
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);

}
