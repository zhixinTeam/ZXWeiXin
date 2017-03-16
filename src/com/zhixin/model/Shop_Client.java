package com.zhixin.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Shop_Client implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1534048188553408118L;

	private String c_id;
	
	private String clientnumber;
	
	private String cash;
	
	
	private String clientname;
	
	private Set<Shop_Order> shoporders = new HashSet<>();
	
	private Wx_BindCustomer bindcustmoer;
	
	private Doc_Factory doc_factory;

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getClientnumber() {
		return clientnumber;
	}

	public void setClientnumber(String clientnumber) {
		this.clientnumber = clientnumber;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	

	public Doc_Factory getDoc_factory() {
		return doc_factory;
	}

	public void setDoc_factory(Doc_Factory doc_factory) {
		this.doc_factory = doc_factory;
	}

	
	
	

	public String getClientname() {
		return clientname;
	}

	public Wx_BindCustomer getBindcustmoer() {
		return bindcustmoer;
	}

	public void setBindcustmoer(Wx_BindCustomer bindcustmoer) {
		this.bindcustmoer = bindcustmoer;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	@Override
	public String toString() {
		return "Shop_Client [c_id=" + c_id + ", clientnumber=" + clientnumber + ", cash=" + cash + "]";
	}

	public Set<Shop_Order> getShoporders() {
		return shoporders;
	}

	public void setShoporders(Set<Shop_Order> shoporders) {
		this.shoporders = shoporders;
	}
	
	
	
	
}
