package com.zhixin.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Wx_BindCustomer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7948615158044818387L;

	private String id;
	
	private String openid;
	
	private String namepinyin;
	
	private Timestamp binddate;
	
	private Integer status;
	
	private String phone;
	
	private String password;
	
	private String email;
	
	private String pc_password;
	public String getPc_password() {
		return pc_password;
	}

	public void setPc_password(String pc_password) {
		this.pc_password = pc_password;
	}

	private Set<ShopLink_Customer_Driver>  shoplinkcustomerdrivers = new HashSet<>();
	
	private Set<Shop_Client> shopclients = new HashSet<>();
	
	private Set<Shop_Order> shoporders = new HashSet<>();
	
	////
	private Set<Link_BindCustomers_Factorys>  bindcustomer_factorys = new HashSet<>();
	
	
	

	public Set<Shop_Order> getShoporders() {
		return shoporders;
	}

	public void setShoporders(Set<Shop_Order> shoporders) {
		this.shoporders = shoporders;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNamepinyin() {
		return namepinyin;
	}

	public void setNamepinyin(String namepinyin) {
		this.namepinyin = namepinyin;
	}

	public Timestamp getBinddate() {
		return binddate;
	}

	public void setBinddate(Timestamp binddate) {
		this.binddate = binddate;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Link_BindCustomers_Factorys> getBindcustomer_factorys() {
		return bindcustomer_factorys;
	}

	public void setBindcustomer_factorys(Set<Link_BindCustomers_Factorys> bindcustomer_factorys) {
		this.bindcustomer_factorys = bindcustomer_factorys;
	}
	
	

	public Set<ShopLink_Customer_Driver> getShoplinkcustomerdrivers() {
		return shoplinkcustomerdrivers;
	}

	public void setShoplinkcustomerdrivers(Set<ShopLink_Customer_Driver> shoplinkcustomerdrivers) {
		this.shoplinkcustomerdrivers = shoplinkcustomerdrivers;
	}

	
	
	
	public Set<Shop_Client> getShopclients() {
		return shopclients;
	}

	public void setShopclients(Set<Shop_Client> shopclients) {
		this.shopclients = shopclients;
	}

	}

	

	
	
	
	
	
	
	
	
