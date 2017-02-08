package com.zhixin.entity;

import java.util.List;

public class Json_Auth {

	private String id;
	
	private  String authname;
	
	private String authpath;
	
	private String state;
	
	private String iconcls;
	
	private String authorder;
	
	private String authtype;
	
	private List<Json_Auth> childjsonauth ;
	
	private boolean hasAuth = false;
	
	private String parentID;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthname() {
		return authname;
	}

	public void setAuthname(String authname) {
		this.authname = authname;
	}

	public String getAuthpath() {
		return authpath;
	}

	public void setAuthpath(String authpath) {
		this.authpath = authpath;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getAuthorder() {
		return authorder;
	}

	public void setAuthorder(String authorder) {
		this.authorder = authorder;
	}

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	public List<Json_Auth> getChildjsonauth() {
		return childjsonauth;
	}

	public void setChildjsonauth(List<Json_Auth> childjsonauth) {
		this.childjsonauth = childjsonauth;
	}

	public boolean isHasAuth() {
		return hasAuth;
	}

	public void setHasAuth(boolean hasAuth) {
		this.hasAuth = hasAuth;
	}
	
}
