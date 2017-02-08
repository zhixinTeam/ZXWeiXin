package com.zhixin.model;

public class Link_BindCustomers_Factorys {
	
	private String id;
	
	private  Wx_BindCustomer bindcustomer ;
	

	private Doc_Factory factory ;

	private String clientNumber;
	
	private Integer isbind;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Wx_BindCustomer getBindcustomer() {
		return bindcustomer;
	}

	public void setBindcustomer(Wx_BindCustomer bindcustomer) {
		this.bindcustomer = bindcustomer;
	}

	public Doc_Factory getFactory() {
		return factory;
	}

	public void setFactory(Doc_Factory factory) {
		this.factory = factory;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public Integer getIsbind() {
		return isbind;
	}

	public void setIsbind(Integer isbind) {
		this.isbind = isbind;
	}

	
	
}
