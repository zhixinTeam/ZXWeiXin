package com.zhixin.entity;

public class Json_Driver {

	
	private String d_id;
	
	private String name;
	
	private String tracknumber;
	
	private String idnumber;
	
	
	private String phone;
	
	public String getD_id() {
		return d_id;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTracknumber() {
		return tracknumber;
	}

	public void setTracknumber(String tracknumber) {
		this.tracknumber = tracknumber;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Json_Driver(String d_id, String name, String tracknumber, String idnumber, String phone) {
		super();
		this.d_id = d_id;
		this.name = name;
		this.tracknumber = tracknumber;
		this.idnumber = idnumber;
		this.phone = phone;
	}

	public Json_Driver() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
