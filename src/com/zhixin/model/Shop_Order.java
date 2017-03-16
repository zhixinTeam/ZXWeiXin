package com.zhixin.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Shop_Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6462573714060923951L;

	private String o_id;
	
	private String ordernumber;
	
	private Timestamp orderdate;
	
	
	private String data;
	
	
	private Integer status;
	
	private String idnumber;
	
	private String fac_order_no;
	
	private Wx_BindCustomer bindcustmoer;
	
	private Shop_Driver shopdriver;
	
	private Shop_Goods shopgoods;
	
	
	private Shop_Client shopclient;

	private Doc_Factory doc_factory;
	
	
	private String thdate;

	public String getO_id() {
		return o_id;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
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

	
	public String getFac_order_no() {
		return fac_order_no;
	}

	public void setFac_order_no(String fac_order_no) {
		this.fac_order_no = fac_order_no;
	}

	

	public Wx_BindCustomer getBindcustmoer() {
		return bindcustmoer;
	}

	public void setBindcustmoer(Wx_BindCustomer bindcustmoer) {
		this.bindcustmoer = bindcustmoer;
	}

	public Shop_Driver getShopdriver() {
		return shopdriver;
	}

	public void setShopdriver(Shop_Driver shopdriver) {
		this.shopdriver = shopdriver;
	}

	public Shop_Goods getShopgoods() {
		return shopgoods;
	}

	public void setShopgoods(Shop_Goods shopgoods) {
		this.shopgoods = shopgoods;
	}

	public Doc_Factory getDoc_factory() {
		return doc_factory;
	}

	public void setDoc_factory(Doc_Factory doc_factory) {
		this.doc_factory = doc_factory;
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

	public Shop_Client getShopclient() {
		return shopclient;
	}

	public void setShopclient(Shop_Client shopclient) {
		this.shopclient = shopclient;
	}

	
	
	
	
	
}
