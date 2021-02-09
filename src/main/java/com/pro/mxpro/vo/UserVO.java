package com.pro.mxpro.vo;

import java.util.Date;

public class UserVO {
	private int cnt;
	private int id;
	private String userName;
	private String password;
	private String nickname;
	private Date createDate;
	private String email;
	private int userLevel;
	private String snsType;
	public UserVO() {
	}
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getSnsType() {
		return snsType;
	}
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}

	@Override
	public String toString() {
		return "UserVO [cnt=" + cnt + ", id=" + id + ", userName=" + userName + ", password=" + password + ", nickname="
				+ nickname + ", createDate=" + createDate + ", email=" + email + ", userLevel=" + userLevel
				+ ", snsType=" + snsType + "]";
	}
}
