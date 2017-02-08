package com.zhixin.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Wx_BindUser  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8844348301990427131L;

	private String id ;
	
	private String openid;
	
	
	private Timestamp binddate;
	
	

	private String originalID;
	
	private Doc_Factory factory;

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

	public Timestamp getBinddate() {
		return binddate;
	}

	public void setBinddate(Timestamp binddate) {
		this.binddate = binddate;
	}

	public Doc_Factory getFactory() {
		return factory;
	}

	public void setFactory(Doc_Factory factory) {
		this.factory = factory;
	}

	public String getOriginalID() {
		return originalID;
	}

	public void setOriginalID(String originalID) {
		this.originalID = originalID;
	}
	
	

}
