package com.SE.Warehousing;

//  @ Project : 소공 약국
//  @ File Name : DBManager.java
//  @ Date : 2019-05-20
//  @ Author : 신충섭

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBManager {

	private static final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Seoul";
	private static final String USER = "root"; // DB ID
	private static final String PASS = "1234"; // DB 패스워드

	public DBManager() {

	}

	/** DB연결 메소드 */
	public Connection getConn() {

		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	/** 입고정보를 등록 */
	public boolean insertWarehousingInfo(WarehousingInfo warehousingInfo) {
		WarehousingInfo data = new WarehousingInfo();

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령

//		query = "insert into date_menu (L_place, D_day, menu_id) value ('"+savelocation+"', '"+savedate+"', '"+menu[i]+"')";
//		stmt.executeUpdate(query);
		try {

			con = getConn();
//			String query = "insert into warehousing (warehousingCode, productCode, supplierCode, userCode, warehousingDate, unit, qty, unitPrice,"
//					+ " warehousingState) value ('" + wi.getWarehousingCode() + "', '" + wi.getProductCode() + "', '"
//					+ wi.getSupplierName() + "'," + " '" + wi.getUserCode() + "', '" + wi.getWarehousingDate() + "', '"
//					+ wi.getUnit() + "', '" + wi.getQty() + "', '" + wi.getUnitPrice() + "', '"
//					+ wi.getWarehousingState() + "')";
//			stmt = con.createStatement();
//			stmt.executeUpdate(query);

			String query = "insert into warehousing (warehousingCode, productCode, supplierCode, userCode, warehousingDate,"
					+ " unit, qty, unitPrice, warehousingState) values (?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, warehousingInfo.getWarehousingCode());
			pstmt.setString(2, warehousingInfo.getProductCode());
			pstmt.setString(3, warehousingInfo.getSupplierCode());
			pstmt.setString(4, warehousingInfo.getUserCode());
			pstmt.setString(5, warehousingInfo.getWarehousingDate());
			pstmt.setString(6, warehousingInfo.getUnit());
			pstmt.setInt(7, warehousingInfo.getQty());
			pstmt.setInt(8, warehousingInfo.getUnitPrice());
			pstmt.setString(9, warehousingInfo.getWarehousingState());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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

	/** productName으로부터 productCode를 얻는 메소드 */
	public Vector<String> selectProductCodesByProductName(String productName) {

		Vector<String> codes = new Vector<String>();

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {

			con = getConn();
			String query = "select productCode from product where productName=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();

			codes.add(productName); // 0번째는 제품명

			while (rs.next()) {
				codes.add(rs.getString("productCode"));
			}
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

		return codes;
	}

	/** productCode로부터 productName을 얻는 메소드 */
	public String selectProductNameByProductCode(String productCode) {

		String name = null;

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {

			con = getConn();
			String query = "select productName from product where productCode=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				name = rs.getString("productName");
			}
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

		return name;
	}

	/** supplierName으로부터 supplierCode를 얻는 메소드 */
	public String selectSupplierCodeBySupplierName(String name) {
		String code = null;

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {

			con = getConn();
			String query = "select supplierCode from supplier where name=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				code = rs.getString("supplierCode");
			}
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

		return code;
	}

	/** supplierCode으로부터 supplierName을 얻는 메소드 */
	public String selectSupplierNameBySupplierCode(String supplierCode) {
		String name = null;

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {

			con = getConn();
			String query = "select name from supplier where supplierCode=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, supplierCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				name = rs.getString("name");
			}
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

		return name;
	}

	/** userCode로부터 userName을 얻는 메소드 */
	public String selectUserNameByUserCode(String userCode) {

		String name = null;

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {

			con = getConn();
			String query = "select userName from userinfo where userCode=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				name = rs.getString("userName");
			}
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

		return name;
	}

	/** productCodes로 warehousingInfos를 얻는 메소드 */
	public Vector<Vector<String>> selectWarehousingInfosByProductCodes(Vector<String> productCodes) {

		Vector<Vector<String>> warehousingInfos = new Vector<Vector<String>>();

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String query = "select * from warehousing where productCode=?";
			for (int i = 2; i < productCodes.size(); i++) {
				query += "OR productCode=?";
			}
			pstmt = con.prepareStatement(query);
			for (int i = 1; i < productCodes.size(); i++) {
				pstmt.setString(i, productCodes.get(i));
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<String> warehousingInfo = new Vector<String>();
				warehousingInfo.add(rs.getString("warehousingCode"));
				warehousingInfo.add(productCodes.get(0));
				warehousingInfo.add(rs.getString("warehousingDate"));
				warehousingInfo.add(rs.getString("unit"));
				warehousingInfo.add(rs.getInt("qty") + "");
				warehousingInfo.add(rs.getInt("unitPrice") + "");
				warehousingInfo.add(rs.getString("warehousingState"));
				warehousingInfo.add(selectSupplierNameBySupplierCode(rs.getString("supplierCode")));
				warehousingInfo.add(selectUserNameByUserCode(rs.getString("userCode")));
				warehousingInfos.add(warehousingInfo);
			}

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

		return warehousingInfos;
	}

	/** warehousingDate로 warehousingInfos를 얻는 메소드 */
	public Vector<Vector<String>> selectWarehousingInfosByWarehousingDate(String warehousingDate) {

		Vector<Vector<String>> warehousingInfos = new Vector<Vector<String>>();

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String query = "select * from warehousing where date_format(warehousingDate, '%Y-%m-%d')=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, warehousingDate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<String> warehousingInfo = new Vector<String>();
				warehousingInfo.add(rs.getString("warehousingCode"));
				warehousingInfo.add(selectProductNameByProductCode(rs.getString("productCode")));
				warehousingInfo.add(rs.getString("warehousingDate"));
				warehousingInfo.add(rs.getString("unit"));
				warehousingInfo.add(rs.getInt("qty") + "");
				warehousingInfo.add(rs.getInt("unitPrice") + "");
				warehousingInfo.add(rs.getString("warehousingState"));
				warehousingInfo.add(selectSupplierNameBySupplierCode(rs.getString("supplierCode")));
				warehousingInfo.add(selectUserNameByUserCode(rs.getString("userCode")));
				warehousingInfos.add(warehousingInfo);
			}

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

		return warehousingInfos;
	}

	/** warehousingDate와 productCodes로 warehousingInfos를 얻는 메소드 */
	public Vector<Vector<String>> selectWarehousingInfos(Vector<String> productCodes, String warehousingDate) {

		Vector<Vector<String>> warehousingInfos = new Vector<Vector<String>>();

		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String query = "select * from warehousing where (productCode=?";
			for (int i = 2; i < productCodes.size(); i++) {
				query += "OR productCode=?";
			}
			query += ") AND date_format(warehousingDate, '%Y-%m-%d')=?";

			pstmt = con.prepareStatement(query);

			int index = 1;
			for (index = 1; index < productCodes.size(); index++) {
				pstmt.setString(index, productCodes.get(index));
			}
			pstmt.setString(index, warehousingDate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<String> warehousingInfo = new Vector<String>();
				warehousingInfo.add(rs.getString("warehousingCode"));
				warehousingInfo.add(productCodes.get(0));
				warehousingInfo.add(rs.getString("warehousingDate"));
				warehousingInfo.add(rs.getString("unit"));
				warehousingInfo.add(rs.getInt("qty") + "");
				warehousingInfo.add(rs.getInt("unitPrice") + "");
				warehousingInfo.add(rs.getString("warehousingState"));
				warehousingInfo.add(selectSupplierNameBySupplierCode(rs.getString("supplierCode")));
				warehousingInfo.add(selectUserNameByUserCode(rs.getString("userCode")));
				warehousingInfos.add(warehousingInfo);
			}

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

		return warehousingInfos;
	}

	/** warehousingCode로 warehousingInfos를 얻는 메소드 */
	public WarehousingInfo selectWarehousingInfosByWarehousingCode(String warehousingCode) {

		WarehousingInfo warehousingInfo = new WarehousingInfo();
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String query = "select * from warehousing where warehousingCode=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, warehousingCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				warehousingInfo.setWarehousingCode(rs.getString("warehousingCode"));
				warehousingInfo.setProductCode(rs.getString("productCode"));
				warehousingInfo.setWarehousingDate(rs.getString("warehousingDate"));
				warehousingInfo.setUnit(rs.getString("unit"));
				warehousingInfo.setQty(rs.getInt("qty"));
				warehousingInfo.setUnitPrice(rs.getInt("unitPrice"));
				warehousingInfo.setWarehousingState(rs.getString("warehousingState"));
				warehousingInfo.setSupplierCode(rs.getString("supplierCode"));
				warehousingInfo.setUserCode(rs.getString("userCode"));
			}

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

		return warehousingInfo;
	}

	/** warehousingInfo를 받아서 warehousingInfo를 수정하는 메소드 */
	public boolean updateWarehousingInfo(WarehousingInfo warehousingInfo) {
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String query = "update warehousing set productCode=?, supplierCode=?, userCode=?, "
					+ "warehousingDate=?, unit=?" + ", qty=?, unitPrice=?, warehousingState=?"
					+ " where warehousingCode=?";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, warehousingInfo.getProductCode());
			pstmt.setString(2, warehousingInfo.getSupplierCode());
			pstmt.setString(3, warehousingInfo.getUserCode());
			pstmt.setString(4, warehousingInfo.getWarehousingDate());
			pstmt.setString(5, warehousingInfo.getUnit());
			pstmt.setInt(6, warehousingInfo.getQty());
			pstmt.setInt(7, warehousingInfo.getUnitPrice());
			pstmt.setString(8, warehousingInfo.getWarehousingState());
			pstmt.setString(9, warehousingInfo.getWarehousingCode());

			int result = pstmt.executeUpdate();
	
			if(result>0) return true;
			else return false;
			
		} catch (Exception e) {
			e.printStackTrace();
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
	/** warehousingInfo를 받아서 warehousingInfo를 수정하는 메소드 */
	public boolean deleteWarehousingInfo(String warehousingCode) {
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String query = "delete from warehousing where warehousingCode=?";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, warehousingCode);
			
			int result = pstmt.executeUpdate();
	
			if(result>0) return true;
			else return false;
			
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

}
