package com.zhixin.entity;

import java.sql.Timestamp;

import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_User;

public class Json_ShopOrder {

private String o_id;
	
	private String ordernumber;
	
	private Timestamp orderdate;
	
	
	private String data;
	
	
	private Integer status;
	
	private String idnumber;
	
	private String fac_order_no;
	
	
	
	private String  shopdrivername;
	
	private String  shopgoodsname;
	

	private String  doc_factoryname;
	
	private String driverphonenumber;
	
	private String thdate;
	
	private Integer pageCount;

	public String getO_id() {
		return o_id;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	public String getDriverphonenumber() {
		return driverphonenumber;
	}

	public void setDriverphonenumber(String driverphonenumber) {
		this.driverphonenumber = driverphonenumber;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	

	
	
	public String getShopdrivername() {
		return shopdrivername;
	}

	public void setShopdrivername(String shopdrivername) {
		this.shopdrivername = shopdrivername;
	}

	public String getShopgoodsname() {
		return shopgoodsname;
	}

	public void setShopgoodsname(String shopgoodsname) {
		this.shopgoodsname = shopgoodsname;
	}

	public String getDoc_factoryname() {
		return doc_factoryname;
	}

	public void setDoc_factoryname(String doc_factoryname) {
		this.doc_factoryname = doc_factoryname;
	}

	public String getFac_order_no() {
		return fac_order_no;
	}

	public void setFac_order_no(String fac_order_no) {
		this.fac_order_no = fac_order_no;
	}

	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getThdate() {
		return thdate;
	}

	public void setThdate(String thdate) {
		this.thdate = thdate;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
}
