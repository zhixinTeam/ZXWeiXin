package com.zhixin.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class Sys_User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5446903586986018328L;

	private String id;
	
	private String username;
	
	private String password;
	
	private String truename;
	
	private String mobile;
	
	
	private Sys_User parent;
	
	
	private Timestamp createdate;
	
	private Integer status;
	
	private Integer isonline;
	
	private String email;
	
	private String susernumber;
	
	private String  bz;
	
	
	private String last_login;
	
	private String ip;
	
	
	private  Sys_Role sys_role;
	
	private Doc_Factory doc_factory;
	
	private Doc_Factory adminfactory;
	
	
	private Set<Sys_User> children =new HashSet<>();
	
	

	public Sys_User getParent() {
		return parent;
	}

	public void setParent(Sys_User parent) {
		this.parent = parent;
	}

	public Set<Sys_User> getChildren() {
		return children;
	}

	public void setChildren(Set<Sys_User> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsonline() {
		return isonline;
	}

	public void setIsonline(Integer isonline) {
		this.isonline = isonline;
	}

	public Sys_Role getSys_role() {
		return sys_role;
	}

	public void setSys_role(Sys_Role sys_role) {
		this.sys_role = sys_role;
	}

	public Doc_Factory getDoc_factory() {
		return doc_factory;
	}

	public void setDoc_factory(Doc_Factory doc_factory) {
		this.doc_factory = doc_factory;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSusernumber() {
		return susernumber;
	}

	public void setSusernumber(String susernumber) {
		this.susernumber = susernumber;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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

	public Doc_Factory getAdminfactory() {
		return adminfactory;
	}

	public void setAdminfactory(Doc_Factory adminfactory) {
		this.adminfactory = adminfactory;
	}

	
	
	
	
}
