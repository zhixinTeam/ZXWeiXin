package com.zhixin.tools;

import org.springframework.web.multipart.MultipartFile;

public class ImgMsg {

	private String title;
	
	private  String url_my;
	
	private String author;
	
	private String digest;
	
	private String recive_openIDs;
	
	private String msgtype;
	
	private String content_text;
	
	private MultipartFile file;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl_my() {
		return url_my;
	}

	public void setUrl_my(String url_my) {
		this.url_my = url_my;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getRecive_openIDs() {
		return recive_openIDs;
	}

	public void setRecive_openIDs(String recive_openIDs) {
		this.recive_openIDs = recive_openIDs;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}



	public String getContent_text() {
		return content_text;
	}

	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
