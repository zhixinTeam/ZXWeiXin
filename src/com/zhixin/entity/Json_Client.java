package com.zhixin.entity;



public class Json_Client {

	
	private String c_id;
	
	private String clientnumber;
	
	private String cash;
	
	private String clientname;
	
	
	private String clientid;

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getClientnumber() {
		return clientnumber;
	}

	public void setClientnumber(String clientnumber) {
		this.clientnumber = clientnumber;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public Json_Client(String c_id, String clientnumber, String clientname) {
		super();
		this.c_id = c_id;
		this.clientnumber = clientnumber;
		this.clientname = clientname;
	}

	public Json_Client() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
