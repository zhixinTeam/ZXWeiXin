package com.zhixin.model;

import java.io.Serializable;

public class App_pack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993707972273118911L;

	private String id;
	
	private String path;
	
	private String picname;
	
	private String context;
	
	private String miaosu;
	
	private Doc_Factory factory;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	

	public String getMiaosu() {
		return miaosu;
	}

	public void setMiaosu(String miaosu) {
		this.miaosu = miaosu;
	}

	public Doc_Factory getFactory() {
		return factory;
	}

	public void setFactory(Doc_Factory factory) {
		this.factory = factory;
	}
	
	
	
}
