package com.SE.BaseData;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserInfoManagement {
	

	public boolean insertUser(String userCode,String userName,String phoneNum,String accessRight) throws ClassNotFoundException, SQLException
	{
		
		UserInfo userInfo = new UserInfo(userCode,userName,phoneNum,accessRight);
		boolean check=allItemsInsertCheck(userInfo);
		if(check == false)
		{
			JOptionPane.showMessageDialog(null, "필수 항목을 입력하지 않으셨습니다.", "error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		DBManager db = new DBManager();
		db.insertUser(userInfo);
		return true;
	}
	public boolean deleteUser(String userCode) //userCode가 프라이머리 키여서 이거만 받아도 삭제가능 
	{
		DBManager db;
		try {
				db = new DBManager();
				db.deleteUser(userCode);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean modifyUser(String userCode,String userName,String phoneNum,String accessRight)
	{
		DBManager db;
		UserInfo userInfo = new UserInfo(userCode,userName,phoneNum,accessRight);
		try {
			boolean check=allItemsInsertCheck(userInfo);
			if(check == false)
			{
				JOptionPane.showMessageDialog(null, "필수 항목을 입력하지 않으셨습니다.", "error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			db = new DBManager();
			db.modifyUser(userInfo);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return true;
	}
	public UserInfo[] lookupUser(String userName) throws ClassNotFoundException, SQLException {
		DBManager db = new DBManager();
		UserInfo []userInfo=db.lookupUser(userName);
		return userInfo;
		
	}
	private boolean allItemsInsertCheck(UserInfo userInfo) {
		String userCode = userInfo.getUserCode();
		String userName = userInfo.getUserName();
		String phoneNum = userInfo.getPhoneNum();
		String accessRight = userInfo.getAccessRight();
		if(userName.equals(""))
		{
			return false;
		}
		return true;
		
	}

}
