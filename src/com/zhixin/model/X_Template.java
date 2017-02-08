package com.zhixin.model;

import java.util.HashSet;
import java.util.Set;

public class X_Template {

	private String id; 
	
	
	private String template_name;

	
	private Set<X_Tem_Params> set_temparams = new HashSet<>();
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTemplate_name() {
		return template_name;
	}


	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}


	public Set<X_Tem_Params> getSet_temparams() {
		return set_temparams;
	}


	public void setSet_temparams(Set<X_Tem_Params> set_temparams) {
		this.set_temparams = set_temparams;
	}
	
	
	
	
	
	
}
