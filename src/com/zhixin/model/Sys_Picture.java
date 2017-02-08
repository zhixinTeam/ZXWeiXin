package com.zhixin.model;

import java.sql.Timestamp;

public class Sys_Picture {

	
	private String id;
	
	private String title;
	
	private String name;
	
	private String path;
	
	private Timestamp createdate;
	
	private String bz;
	
	private Integer flag;
	
	
	private Doc_Factory doc_factory ;


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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}



	public Timestamp getCreatedate() {
		return createdate;
	}


	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}


	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}


	public Integer getFlag() {
		return flag;
	}


	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	public Doc_Factory getDoc_factory() {
		return doc_factory;
	}


	public void setDoc_factory(Doc_Factory doc_factory) {
		this.doc_factory = doc_factory;
	}
	
}
