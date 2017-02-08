package com.zhixin.model;

import java.io.Serializable;
import java.util.List;

/**
 * 分页功能中的一页的信息
 * 
 * @author tyg
 * 
 */
public class PageBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8098542669720563525L;

	// 指定的或是页面参数
	private int currentPage; // 当前页
	
	private int pageSize; // 每页显示多少条

	// 查询数据库
	private int recordCount; // 总记录数
	private List recordList; // 本页的数据列表

	// 计算
	private int pageCount; // 总页数
	
	/**
	 * 只接受前4个必要的属性，会自动的计算出其他3个属生的值
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param recordCount
	 * @param recordList
	 */
	public PageBean(int currentPage, int pageSize, int recordCount, List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;

		// 计算总页码
		pageCount = (recordCount + pageSize - 1) / pageSize;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	

}
