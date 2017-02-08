package com.zhixin.model;

import java.io.Serializable;

public class ShopLink_User_Driver implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6377963590934097731L;

	private String l_id;
	
	private String uid;
	
	private String did;
	
	
	private Shop_Driver shopdriver;
	
	private Shop_User shopuser;
	
	
	
	
	public String getL_id() {
		return l_id;
	}

	public void setL_id(String l_id) {
		this.l_id = l_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public Shop_Driver getShopdriver() {
		return shopdriver;
	}

	public void setShopdriver(Shop_Driver shopdriver) {
		this.shopdriver = shopdriver;
	}

	public Shop_User getShopuser() {
		return shopuser;
	}

	public void setShopuser(Shop_User shopuser) {
		this.shopuser = shopuser;
	}

	@Override
	public String toString() {
		return "ShopLink_User_Driver [l_id=" + l_id + ", uid=" + uid + ", did=" + did + "]";
	}

	
	
	
}
