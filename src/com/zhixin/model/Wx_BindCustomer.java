package com.zhixin.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Wx_BindCustomer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7948615158044818387L;

	private String id;
	
	private String openid;
	
	private String namepinyin;
	
	private Timestamp binddate;
	
	private Integer status;
	////
	private String wxUserName;
	
	private String  suserNumber;
	
	private String phone;
	
	private String email;
	
	////
	private Set<Link_BindCustomers_Factorys>  bindcustomer_factorys = new HashSet<>();
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNamepinyin() {
		return namepinyin;
	}

	public void setNamepinyin(String namepinyin) {
		this.namepinyin = namepinyin;
	}

	public Timestamp getBinddate() {
		return binddate;
	}

	public void setBinddate(Timestamp binddate) {
		this.binddate = binddate;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	public String getWxUserName() {
		return wxUserName;
	}

	public void setWxUserName(String wxUserName) {
		this.wxUserName = wxUserName;
	}

	public String getSuserNumber() {
		return suserNumber;
	}

	public void setSuserNumber(String suserNumber) {
		this.suserNumber = suserNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Link_BindCustomers_Factorys> getBindcustomer_factorys() {
		return bindcustomer_factorys;
	}

	public void setBindcustomer_factorys(Set<Link_BindCustomers_Factorys> bindcustomer_factorys) {
		this.bindcustomer_factorys = bindcustomer_factorys;
	}

	@Override
	public String toString() {
		return "Wx_BindCustomer [id=" + id + ", openid=" + openid + ", namepinyin=" + namepinyin + ", binddate="
				+ binddate + ", status=" + status + ", wxUserName=" + wxUserName + ", suserNumber=" + suserNumber
				+ ", phone=" + phone + ", email=" + email + ", bindcustomer_factorys=" + bindcustomer_factorys + "]";
	}

	
	
	
	
	
	
	
}
