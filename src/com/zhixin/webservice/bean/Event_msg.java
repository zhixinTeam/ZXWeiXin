package com.zhixin.webservice.bean;

import java.io.Serializable;

public class Event_msg  implements Serializable{

	private String senduser; 		//消息发送方
	private String reviceuser;	//消息接收方
	private Long createTime;		//消息创建时间 （整型）
	private String msgType;			//消息类型，event
	private String content;	
	private String picurl;    //图片地址
	private String event;			//事件类型
	public String getSenduser() {
		return senduser;
	}
	public void setSenduser(String senduser) {
		this.senduser = senduser;
	}
	public String getReviceuser() {
		return reviceuser;
	}
	public void setReviceuser(String reviceuser) {
		this.reviceuser = reviceuser;
	}
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
