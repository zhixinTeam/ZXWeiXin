package com.zhixin.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Doc_Company  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9093301425288267683L;

	private String id;
	
	private String companyname;
	
	private String wechatsub;
	
	private String appid;
	
	private String secrectid;
	
	
	private Integer status;
	
	private Timestamp createdate ;
	
	private String originalID; //原始id
	
	private Set<Doc_Factory> doc_factorys = new HashSet<>();

	
	private Set<X_Msg_Type> x_msg_types = new HashSet<>();

	


	public String getSecrectid() {
		return secrectid;
	}


	public void setSecrectid(String secrectid) {
		this.secrectid = secrectid;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCompanyname() {
		return companyname;
	}


	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}


	public String getWechatsub() {
		return wechatsub;
	}


	public void setWechatsub(String wechatsub) {
		this.wechatsub = wechatsub;
	}


	public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}


	

	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Timestamp getCreatedate() {
		return createdate;
	}


	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}


	public Set<Doc_Factory> getDoc_factorys() {
		return doc_factorys;
	}


	public void setDoc_factorys(Set<Doc_Factory> doc_factorys) {
		this.doc_factorys = doc_factorys;
	}


	public String getOriginalID() {
		return originalID;
	}


	public void setOriginalID(String originalID) {
		this.originalID = originalID;
	}


	public Set<X_Msg_Type> getX_msg_types() {
		return x_msg_types;
	}


	public void setX_msg_types(Set<X_Msg_Type> x_msg_types) {
		this.x_msg_types = x_msg_types;
	}
	
	
	
}
