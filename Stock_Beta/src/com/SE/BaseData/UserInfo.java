package com.SE.BaseData;

public class UserInfo {
	private String userCode;
	private String userName;
	private String phoneNum;
	private String accessRight;
	
	public UserInfo() {
		this.userCode = "";
		this.userName = "";
		this.phoneNum = "";
		this.accessRight = "";
	}
	
	public UserInfo(String userCode, String userName, String phoneNum, String accessRight) {
		super();
		this.userCode = userCode;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.accessRight = accessRight;
	}
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAccessRight() {
		return accessRight;
	}
	public void setAccessRight(String accessRight) {
		this.accessRight = accessRight;
	}
	
}
