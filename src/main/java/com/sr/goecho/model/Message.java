package com.sr.goecho.model;

import lombok.*;


public class Message {
	private String username;
	private String messageid;
	private String senderid;
	private String receiverid;
	private String timestamp;
	private String content;
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Message(String messageid, String senderid, String receiverid, String timestamp, String content,String username) {
		super();
		this.messageid = messageid;
		this.senderid = senderid;
		this.receiverid = receiverid;
		this.timestamp = timestamp;
		this.content = content;
		this.username=username;
	}

	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(String receiverid) {
		this.receiverid = receiverid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
