package com.zhixin.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Shop_Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7842137427088029079L;

	
	private String d_id;
	
	private String name;
	
	private String tracknumber;
	
	private String idnumber;
	
	private String phone;
	
	
	private Set<ShopLink_Customer_Driver>  shoplinkcustomerdrivers = new HashSet<>();

	private Set<Shop_Order> shoporders = new HashSet<>();
	
	

	public String getD_id() {
		return d_id;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTracknumber() {
		return tracknumber;
	}

	public void setTracknumber(String tracknumber) {
		this.tracknumber = tracknumber;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	
	public Set<ShopLink_Customer_Driver> getShoplinkcustomerdrivers() {
		return shoplinkcustomerdrivers;
	}

	public void setShoplinkcustomerdrivers(Set<ShopLink_Customer_Driver> shoplinkcustomerdrivers) {
		this.shoplinkcustomerdrivers = shoplinkcustomerdrivers;
	}

	public Set<Shop_Order> getShoporders() {
		return shoporders;
	}

	public void setShoporders(Set<Shop_Order> shoporders) {
		this.shoporders = shoporders;
	}

	@Override
	public String toString() {
		return "Shop_Driver [d_id=" + d_id + ", name=" + name + ", tracknumber=" + tracknumber + ", idnumber="
				+ idnumber + "]";
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Shop_Driver(String d_id, String tracknumber) {
		this.d_id = d_id;
		this.tracknumber = tracknumber;
	}

	public Shop_Driver() {
	}
	
	
	
	
}
