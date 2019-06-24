package com.SE.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
	private final String USER_NAME = "root";
	private final String PASSWORD = "1234";

	public DBManager() {
		/*
		 * try { Class.forName(JDBC_DRIVER); conn = DriverManager.getConnection(DB_URL,
		 * USER_NAME, PASSWORD); System.out.println("접속 됨 ㅋ.ㅋ");
		 * 
		 * } catch (Exception e) { System.out.println("ERROR 1 : " + e.getMessage()); }
		 */
	}

	public Connection getConn() {

		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	public boolean insertCustomerInfo(CustomerInfo customerInfo) {

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령

		try {
			con = getConn();

			String SQL = "INSERT INTO customer(customerCode, customerName, phoneNum, address, birthDay) VALUES(?,?,?,?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, customerInfo.getCustomerCode());
			pstmt.setString(2, customerInfo.getCustomerName());
			pstmt.setString(3, customerInfo.getPhoneNum());
			pstmt.setString(4, customerInfo.getAddress());
			pstmt.setString(5, customerInfo.getBirthDay());

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("db_CREATECODE : " + e.getMessage());
			return false;
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return true;
	}

	public boolean deleteCustomerInfo(String customerCode) {
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String SQL = "delete from customer where customerCode=?";
			pstmt = con.prepareStatement(SQL);

			pstmt.setString(1, customerCode);

			int result = pstmt.executeUpdate();

			if (result > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return true;
	}

	public boolean modifyCustomerInfo(CustomerInfo customerInfo) {
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String SQL = "update customer set customerName=?, phoneNum=?, " + "birthDay=?, address=?"
					+ " where customerCode=?";
			pstmt = con.prepareStatement(SQL);

			pstmt.setString(1, customerInfo.getCustomerName());
			pstmt.setString(2, customerInfo.getPhoneNum());
			pstmt.setString(3, customerInfo.getBirthDay());
			pstmt.setString(4, customerInfo.getAddress());
			pstmt.setString(5, customerInfo.getCustomerCode());

			int result = pstmt.executeUpdate();

			if (result > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			return false;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	
	}

	public List<CustomerInfo> lookupCustomerInfo(String customerCode) {

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		List<CustomerInfo> CustomerInfoList = new ArrayList<>();

		String SQL = "SELECT * FROM customer";

		try {
			con = getConn();

			if (customerCode != "") {
				SQL += " WHERE customerCode=?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, customerCode);
			} else
				pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CustomerInfo c = new CustomerInfo();
				c.setCustomerCode(rs.getString("customerCode"));
				c.setCustomerName(rs.getString("customerName"));
				c.setPhoneNum(rs.getString("phoneNum"));
				c.setAddress(rs.getString("address"));
				c.setBirthday(rs.getString("birthDay"));
				CustomerInfoList.add(c);
			}

		} catch (Exception e) {
			System.out.println("db_READCODE 2 : " + e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return CustomerInfoList;
	}

	public List<CustomerInfo> interestedLookupCustomerInfo(String customerCode) {

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		List<CustomerInfo> CustomerInfoList = new ArrayList<>();

		String SQL = "SELECT * FROM customer WHERE interestedCustomer Between 80 and 101";

		try {
			con = getConn();

			if (customerCode != "") {
				SQL += " AND customerCode=?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, customerCode);

			} else
				pstmt = con.prepareStatement(SQL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CustomerInfo c = new CustomerInfo();
				c.setCustomerCode(rs.getString("customerCode"));
				c.setCustomerName(rs.getString("customerName"));
				c.setPhoneNum(rs.getString("phoneNum"));
				c.setAddress(rs.getString("address"));
				c.setBirthday(rs.getString("birthDay"));
				CustomerInfoList.add(c);
			}

		} catch (Exception e) {
			System.out.println("db_READCODE 2 : " + e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return CustomerInfoList;
	}
}
