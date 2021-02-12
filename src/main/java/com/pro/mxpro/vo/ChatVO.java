package com.pro.mxpro.vo;

import java.util.Date;

public class ChatVO {
	private int mymsgId;
	private String name;
	private String message;
	private Date time;
	private String sessionId;
	
	public ChatVO() {
	}
	public int getMymsgId() {
		return mymsgId;
	}
	public void setMymsgId(int mymsgId) {
		this.mymsgId = mymsgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
