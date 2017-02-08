package com.zhixin.webservice.beans;

import java.io.Serializable;

public class Event_msg  implements Serializable{

	private String toUserName; 		//开发者微信号
	private String fromUserName;	//发送方帐号（一个OpenID）
	private Integer createTime;		//消息创建时间 （整型）
	private String msgType;			//消息类型，event
	private String content;			
	private String msgid;
	private String picurl;    //图片地址
	private String event;			//事件类型
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public Integer getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
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
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
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
	@Override
	public String toString() {
		return "Event_msg [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", content=" + content + ", msgid=" + msgid + ", picurl=" + picurl
				+ ", event=" + event + "]";
	}
	
	
	
	
}
