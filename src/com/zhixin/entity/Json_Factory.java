package com.zhixin.entity;

import java.sql.Date;

public class Json_Factory {

	private String id;
	
	private String factoryname;
	
	private String serviceurl;
	
	private String serviceparam;
	
	private String companyid;
	
	private String companyname ;
	
	private String adminname;
	
	private Integer status;
	
	private String createdate ;
	
	private String roleid;
	
	private Date startdate;
	
	private Date enddate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getServiceurl() {
		return serviceurl;
	}

	public void setServiceurl(String serviceurl) {
		this.serviceurl = serviceurl;
	}

	public String getServiceparam() {
		return serviceparam;
	}

	public void setServiceparam(String serviceparam) {
		this.serviceparam = serviceparam;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Json_Factory(String id, String factoryname) {
		super();
		this.id = id;
		this.factoryname = factoryname;
	}

	public Json_Factory() {
		// TODO Auto-generated constructor stub
	}

	

	
	

	
	
	
}
