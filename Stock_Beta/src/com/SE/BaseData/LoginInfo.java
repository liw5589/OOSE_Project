package com.SE.BaseData;
//alt shift sr 
public class LoginInfo {
	private String userCode;
	private String userName;
	private String acessRight;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAcessRight() {
		return acessRight;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public void setAcessRight(String acessRight) {
		this.acessRight = acessRight;
	}
	

}
