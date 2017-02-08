package com.zhixin.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class App_activity implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1410512002996220034L;

	private String id;
	
	private String title;
	
	private String path;
	
	private String picname;
	
	private String context;
	
	private String datetime;
	
	private Timestamp createdate;
	
	private Doc_Factory factory;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPicname() {
		return picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
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
