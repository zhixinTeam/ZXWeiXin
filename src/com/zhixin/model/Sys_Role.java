package com.zhixin.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Sys_Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 630725745881627221L;

	private String id;
	
	private String rolename;
	
	private String rolebz;

	private Set<Sys_User> sys_users = new HashSet<>();
	
	
	private Set<Link_Roles_Auths> roles_auths = new HashSet<>();
	
	
	private Doc_Factory doc_factory ;
	
	// 父级角色
	
	private  Sys_Role parent;
	
	private Set<Sys_Role> children = new HashSet<>();
	
	//子级角色
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRolebz() {
		return rolebz;
	}

	public void setRolebz(String rolebz) {
		this.rolebz = rolebz;
	}

	public Set<Sys_User> getSys_users() {
		return sys_users;
	}

	public void setSys_users(Set<Sys_User> sys_users) {
		this.sys_users = sys_users;
	}

	

	public Doc_Factory getDoc_factory() {
		return doc_factory;
	}

	public void setDoc_factory(Doc_Factory doc_factory) {
		this.doc_factory = doc_factory;
	}

	public Sys_Role getParent() {
		return parent;
	}

	public void setParent(Sys_Role parent) {
		this.parent = parent;
	}

	public Set<Sys_Role> getChildren() {
		return children;
	}

	public void setChildren(Set<Sys_Role> children) {
		this.children = children;
	}

	public Set<Link_Roles_Auths> getRoles_auths() {
		return roles_auths;
	}

	public void setRoles_auths(Set<Link_Roles_Auths> roles_auths) {
		this.roles_auths = roles_auths;
	}

	
	
	
	
}
