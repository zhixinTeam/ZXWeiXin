package com.zhixin.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Sys_Auth  implements Serializable, Comparable<Sys_Auth>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151183139913707967L;

	private String id;
	
	private  String authname;
	
	private String authpath;
	
	private Sys_Auth parent ;
	
	private String state;
	
	private String iconcls;
	
	private String authtype;
	
	private String authorder;
	
	
	private Set<Sys_Auth> children = new HashSet<>();
	
	private Set<Link_Roles_Auths> roles_auths = new HashSet<>();
	
	
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

	

	public void setChildren(Set<Sys_Auth> children) {
		this.children = children;
	}
	public Sys_Auth getParent() {
		return parent;
	}

	public void setParent(Sys_Auth parent) {
		this.parent = parent;
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

	public Set<Sys_Auth> getChildren() {
		return children;
	}



	

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	public String getAuthorder() {
		return authorder;
	}

	public void setAuthorder(String authorder) {
		this.authorder = authorder;
	}

	@Override
	public int compareTo(Sys_Auth o) {
		// TODO Auto-generated method stub
		return this.getAuthorder().compareTo(o.getAuthorder());
	}

	public Set<Link_Roles_Auths> getRoles_auths() {
		return roles_auths;
	}

	public void setRoles_auths(Set<Link_Roles_Auths> roles_auths) {
		this.roles_auths = roles_auths;
	}

	

	
	
	
}
