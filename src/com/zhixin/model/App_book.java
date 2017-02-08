package com.zhixin.model;

import java.io.Serializable;

public class App_book implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2032451111791031920L;

	private String id;
	
	private String datetime;
	
	private String phone;
	
	private String bz;
	
	private Integer num;
	
	private Doc_Factory factory;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Doc_Factory getFactory() {
		return factory;
	}

	public void setFactory(Doc_Factory factory) {
		this.factory = factory;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
}
