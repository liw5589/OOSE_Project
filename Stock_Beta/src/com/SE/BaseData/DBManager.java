package com.SE.BaseData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBManager{
	Statement stmt;
	Connection conn;
	ResultSet rs;
	 public DBManager() throws SQLException, ClassNotFoundException  {
		
		  String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
		   Connection conn;
		   PreparedStatement pstmt = null;
			conn = DriverManager.getConnection(url, "root", "1234");
			
			stmt = conn.createStatement();
			
		} 
		
	
	public int login(String userCode) {
		final int LOGIN_SUCCESS = 1;
		final int LOGIN_FAIL = 0;
		int loginCheck = 0 ;
		
		String query = "select userCode from userinfo where userCode='"+userCode+"'";
		//String query = "select userCode from userinfo";
		try {
			 rs = stmt.executeQuery(query);
		
			
			if(rs.next()) {
				loginCheck = LOGIN_SUCCESS;
			}
			else {
				loginCheck = LOGIN_FAIL;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		return loginCheck;
		
	}
	public void insertUser(UserInfo userInfo)
	{
		
		String query = "insert into userinfo(userCode, userName, number, accessRight) value('"+userInfo.getUserCode()+"', '"+userInfo.getUserName()+"', '"+userInfo.getPhoneNum()+"','"+userInfo.getAccessRight()+"')";
		 try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "사용자 코드가 중복되었습니다.", "error", JOptionPane.ERROR_MESSAGE);
		}
		 finally {
			
				if(stmt != null)
					try {
						stmt.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
	}
	public void deleteUser(String userCode)
	{
		String query = "delete from userinfo where userCode ='"+userCode+"'";
		try {
			
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public void modifyUser(UserInfo userInfo)
	{
		String userCode = userInfo.getUserCode();
		String userName = userInfo.getUserName();
		String phoneNum = userInfo.getPhoneNum();
		String accessRight = userInfo.getAccessRight();
		String query = "update userinfo SET userName = '"+userName+"',number = '"+phoneNum+"',accessRight='"+accessRight+"'where userCode='"+userCode+"'";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public UserInfo[] lookupUser(String userName)
	{
		String userCode = "";
		String phoneNum = "";
		String accessRight = "";
		UserInfo []userInfo = new UserInfo[100];
		int cnt=0;
		
		
		String query = "select userCode, userName, number, accessRight from userinfo where userName ='"+userName+"'";
		try {
			
			rs= stmt.executeQuery(query);
		
			while(rs.next())
			{
				userCode = rs.getString("userCode");
				userName = rs.getString("userName");
				phoneNum = rs.getString("number");
				accessRight = rs.getString("accessRight");
				userInfo[cnt] = new UserInfo(userCode,userName,phoneNum,accessRight);
				cnt++;
				
			}
			userInfo[cnt] = new UserInfo();
			cnt++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return userInfo;
	}
	
}
