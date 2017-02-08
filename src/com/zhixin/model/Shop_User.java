package com.zhixin.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class Shop_User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3705701891688802491L;

	
	private String u_id;
	
	private String username;
	
	private String password;
	
	private Timestamp createdate;
	
	private String last_login;
	
	private String ip;
	
	private Doc_Factory doc_factory;
	
	private String openID;
	
	
	private Set<ShopLink_User_Driver>  shoplinkuserdrivers = new HashSet<>();
	
	private Set<Shop_Client> shopclients = new HashSet<>();
	
	private Set<Shop_Order> shoporders = new HashSet<>();

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Set<ShopLink_User_Driver> getShoplinkuserdrivers() {
		return shoplinkuserdrivers;
	}

	public void setShoplinkuserdrivers(Set<ShopLink_User_Driver> shoplinkuserdrivers) {
		this.shoplinkuserdrivers = shoplinkuserdrivers;
	}

	public Set<Shop_Client> getShopclients() {
		return shopclients;
	}

	public void setShopclients(Set<Shop_Client> shopclients) {
		this.shopclients = shopclients;
	}

	public Set<Shop_Order> getShoporders() {
		return shoporders;
	}

	public void setShoporders(Set<Shop_Order> shoporders) {
		this.shoporders = shoporders;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Doc_Factory getDoc_factory() {
		return doc_factory;
	}

	public void setDoc_factory(Doc_Factory doc_factory) {
		this.doc_factory = doc_factory;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	
	
}
