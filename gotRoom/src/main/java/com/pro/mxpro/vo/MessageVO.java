package com.pro.mxpro.vo;

import java.util.Date;

public class MessageVO { 
	private int mymsgId;
	private int speaker;
	private int participant1;
	private int participant2;
	private String nickname1;
	private String nickname2;
	private String targetUserid;
	private String msgLog;
	private Date msgDate;
	public MessageVO() {
	}
	public int getMymsgId() {
		return mymsgId;
	}
	public void setMymsgId(int mymsgId) {
		this.mymsgId = mymsgId;
	}
	public int getSpeaker() {
		return speaker;
	}
	public void setSpeaker(int speaker) {
		this.speaker = speaker;
	}
	public int getParticipant1() {
		return participant1;
	}
	public void setParticipant1(int participant1) {
		this.participant1 = participant1;
	}
	public int getParticipant2() {
		return participant2;
	}
	public void setParticipant2(int participant2) {
		this.participant2 = participant2;
	}
	public String getNickname1() {
		return nickname1;
	}
	public void setNickname1(String nickname1) {
		this.nickname1 = nickname1;
	}
	public String getNickname2() {
		return nickname2;
	}
	public void setNickname2(String nickname2) {
		this.nickname2 = nickname2;
	}
	public String getTargetUserid() {
		return targetUserid;
	}
	public void setTargetUserid(String targetUserid) {
		this.targetUserid = targetUserid;
	}
	public String getMsgLog() {
		return msgLog;
	}
	public void setMsgLog(String msgLog) {
		this.msgLog = msgLog;
	}
	public Date getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}
	@Override
	public String toString() {
		return "MessageVO [mymsgId=" + mymsgId + ", speaker=" + speaker + ", participant1="
				+ participant1 + ", participant2=" + participant2 + ", nickname1=" + nickname1 + ", nickname2="
				+ nickname2 + ", targetUserid=" + targetUserid + ", msgLog=" + msgLog + ", msgDate=" + msgDate + "]";
	}
}
