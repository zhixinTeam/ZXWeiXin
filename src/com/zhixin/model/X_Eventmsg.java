package com.zhixin.model;

import com.zhixin.webservice.bean.Event_msg;

public class X_Eventmsg extends Event_msg{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6948584785614425490L;


	private String id;

	
	private Integer issend;
	
	private Integer sendcount;
	
	private String factoryid ;
	
	private String openid;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

	public Integer getIssend() {
		return issend;
	}

	public void setIssend(Integer issend) {
		this.issend = issend;
	}

	

	public Integer getSendcount() {
		return sendcount;
	}

	public void setSendcount(Integer sendcount) {
		this.sendcount = sendcount;
	}

	public String getFactoryid() {
		return factoryid;
	}

	public void setFactoryid(String factoryid) {
		this.factoryid = factoryid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
	

}
