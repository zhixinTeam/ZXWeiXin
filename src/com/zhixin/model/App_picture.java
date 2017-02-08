package com.zhixin.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class App_picture implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6659771395598685247L;

	private String id;
	
	private String picname;
	
	private String path ;
	
	private Timestamp createdate;
	
	private Doc_Factory factory;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicname() {
		return picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Doc_Factory getFactory() {
		return factory;
	}

	public void setFactory(Doc_Factory factory) {
		this.factory = factory;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	
	
}
