package com.SE.Sell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//판매 이력을 위한 DB 연결을 위한 DAO 부분.
public class DBManager {

	private static final String URL = "jdbc:mysql://localhost:3306/mydb?&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "root"; // DB ID
	private static final String PASS = "1234"; // DB 패스워드
	private Connection connection;
	private ResultSet resultSet;

	public DBManager() {

	}

	/*--------------DB연결 부분--------------*/

	public Connection getConn() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	/*--------------날짜를 얻어오는 부분--------------*/

	public String getDate() {

		PreparedStatement pstmt;
		String SQL = "SELECT NOW()";

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "\n ERROR : 현재 시간을 가져올 수 없습니다 \n"; // 데이터베이스 오류
	}

	/*--------------인덱스를 얻어오는 부분--------------*/

	public int getIndex() {

		PreparedStatement pstmt;
		String SQL = "SELECT COUNT(*) FROM sell";

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt(1) + 1;
			} else {
				return 1;// 첫 번째 컬럼인 경우
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 코드 생성 오류\n");
		return 0;
	}

	/*--------------손님 코드로 손님 정보 받아오는 부분--------------*/

	public String getCustomerName(String coustomerCode) {

		// SQL 관련 변수
		String SQL;
		PreparedStatement pstmt;

		String customer = " ";

		// 고객 이름 받아오기
		SQL = "SELECT customerName FROM customer WHERE customerCode=?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, coustomerCode);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {

				customer = resultSet.getString("customerName");
				return customer;

			} else {
				return "비회원";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 손님 이름 받아오기 불가함");
		return null;
	}

	/*--------------고객 코드를 가져오는 부분--------------*/

	public String getCustomerCode(String name) {

		// SQL 관련 변수
		String SQL;
		PreparedStatement pstmt;

		String customer = " ";

		// 고객 이름 받아오기
		SQL = "SELECT customerCode FROM customer WHERE customerName=?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, name);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				customer = resultSet.getString("customerCode");
				return customer;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 손님 정보 받아오기 불가함 \n");
		return null;
	}

	/*--------------재고량을 더하는 부분--------------*/

	public int StockUpdateIncrease(int value, String productCode) {

		// SQL 관련 변수
		String SQL;
		PreparedStatement pstmt;

		SQL = "UPDATE stock SET qty = qty + ? WHERE productCode = ?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, value);
			pstmt.setString(2, productCode);

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: 재고량 조회 불가능 \n");
		return -1;
	}

	/*--------------재고량을 줄이는 부분--------------*/

	public int StockUpdateDecrase(int value, String productCode) {

		// SQL 관련 변수
		String SQL;
		PreparedStatement pstmt;

		SQL = "UPDATE stock SET qty = qty - ? WHERE productCode = ?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, value);
			pstmt.setString(2, productCode);

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: 재고량 조회 불가능 \n");
		return -1;
	}
	
	/*--------------고객 위험도 셋팅하기 --------------*/

	public int customerAttentionLevel(int value, String customerCode) {

		// SQL 관련 변수
		String SQL;
		PreparedStatement pstmt;

		SQL = "UPDATE customer SET interestedCustomer = ? WHERE customerCode  = ?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, value);
			pstmt.setString(2, customerCode);

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: 고객 정보 업데이트 불가능 \n");
		return -1;
	}

	/*--------------상품정보를 가져오는 부분--------------*/

	public proudctSellRecordInfo getProductInfo(String productCode) {

		// SQL 관련 변수
		PreparedStatement pstmt;
		String SQL = "select productName, attentionLevel, productRetailPrice from product where productCode=?";

		// 제품명, 제품 가격, 주의 레벨을 가져온다
		proudctSellRecordInfo info = new proudctSellRecordInfo();

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, productCode);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				info.setProductName(resultSet.getString("productName"));
				info.setAttentionLevel(resultSet.getString("attentionLevel"));
				info.setProductPrice(resultSet.getInt("productRetailPrice"));
				info.setProductCode(productCode);
				return info;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("ERROR: 상품정보를 받아오지못함");
		return null;
	}

	/*--------------판매정보를 테이블에 추가한다--------------*/

	public int sellInfoInsert(sellRecordInfo sellInfo) {

		// SQL 관련 변수
		String SQL = "INSERT INTO sell VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt;

		// 정보를 담기 위한 임시 변수들
		String sellCode = sellInfo.getSellCode();
		String customerCode = sellInfo.getCustomerCode();
		int paymentMethod = sellInfo.getPaymentMethod();
		// String sellPersonChargeCode = sellInfo.getSellPersonChargeCode();
		int totalPrice = sellInfo.getTotalPrice();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);

			pstmt.setString(1, sellCode); //
			pstmt.setString(2, customerCode); //
			pstmt.setInt(3, paymentMethod);
			pstmt.setString(4, "SELM-001"); // getSellPersonChargeName(); 나중에 가져올 수 있는 경우
			pstmt.setString(5, getDate()); //
			pstmt.setInt(6, totalPrice); // 총가격
			pstmt.setInt(7, 0); // 0이면 판매 된 상태 1이면 판매 취소 된 상태임

			pstmt.executeUpdate();

			return 0;

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: 판매정보를 추가할 수 없음 \n");
		return -1; // 데이터베이스 오류
	}

	/*--------------상품에 대한 정보를 테이블에 추가한다--------------*/

	public int sellProduct(ArrayList<proudctSellRecordInfo> productRecodeInfo) {

		// SQL 관련 변수
		String SQL;
		PreparedStatement pstmt;

		SQL = "INSERT INTO product_has_sell VALUES(?,?,?,?,?,?)";

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);

			for (int i = 0; i < productRecodeInfo.size(); i++) {
				pstmt.setString(1, productRecodeInfo.get(i).getProductCode()); // 상품코드
				pstmt.setString(2, productRecodeInfo.get(i).getSellcode()); // 판매코드
				pstmt.setInt(3, productRecodeInfo.get(i).getQty()); // 판매 갯수
				pstmt.setInt(4, productRecodeInfo.get(i).getTotalprice()); // 전체가격
				pstmt.setInt(5, 0); // 결제방법 0:현금 / 1: 카드
				pstmt.setString(6, productRecodeInfo.get(i).getProductName()); // 상품이름

				pstmt.executeUpdate();
			}

			return 0;

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: 상품 판매 정보를 등록할 수 없음\n");
		return -1; // 데이터베이스 오류
	}

	/*--------------판매에 관한 모든 리스트를 가져온다--------------*/

	public ArrayList<sellRecordInfo> getSellList(String sellCode) {

		// SQL 관련 변수
		String SQL = "SELECT * FROM sell WHERE cancel = 0 AND sellCode = ?";
		PreparedStatement pstmt;

		ArrayList<sellRecordInfo> list = new ArrayList<sellRecordInfo>();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				sellRecordInfo sellInfo = new sellRecordInfo();

				// 구매날짜, 판매코드, 결제방법, 총 판매가격, 구매자코드
				sellInfo.setSellCode(resultSet.getString("sellCode"));
				sellInfo.setCustomerCode(resultSet.getString("customerCode"));
				sellInfo.setPaymentMethod(resultSet.getInt("paymentMethod"));
				sellInfo.setTotalPrice(resultSet.getInt("totalPrice"));

				list.add(sellInfo);

			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 판매 정보를 가져올 수 없음 \n");
		return null; // 데이터베이스 오류
	}

	/*--------------판매코드에 대한 정보를 가져온다--------------*/

	public sellRecordInfo getSellInfo(String sellCode) {

		// SQL 관련 변수
		String SQL = "SELECT * FROM sell WHERE sellCode = ?";
		PreparedStatement pstmt;

		sellRecordInfo sellInfo = new sellRecordInfo();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {

				// 구매날짜, 판매코드, 결제방법, 총 판매가격, 구매자코드
				sellInfo.setSellCode(resultSet.getString("sellCode"));
				sellInfo.setCustomerCode(resultSet.getString("customerCode"));
				sellInfo.setPaymentMethod(resultSet.getInt("paymentMethod"));
				sellInfo.setSellDate(resultSet.getString("sellDate"));
				sellInfo.setTotalPrice(resultSet.getInt("totalPrice"));
				sellInfo.setcancel(resultSet.getInt("cancel"));

				return sellInfo;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 판매 정보를 가져올 수 없음 \n");
		return null; // 데이터베이스 오류
	}

	/*--------------같은 판매코드를 가진 제품판매리스트 이름 가져오기--------------*/

	public StringBuilder getsellProductNameList(String sellCode) {

		StringBuilder strBuilder = new StringBuilder();

		// SQL 관련 변수
		String SQL = "SELECT productName FROM product_has_sell WHERE sellCode = ?";
		PreparedStatement pstmt;

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				strBuilder.append(resultSet.getString("productName") + ",");
			}

			return strBuilder;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 판매된 제품 이름 가져오기 불가 \n");
		return null;
	}
	
	/*--------------판매코드로 상품 정보랑 개수 가져오기--------------*/
	
	public ArrayList<proudctSellRecordInfo> getProductInfoforcancel(String sellCode) {

		ArrayList<proudctSellRecordInfo> productInfo = new ArrayList<proudctSellRecordInfo>();

		// SQL 관련 변수
		String SQL = "SELECT productCode, qty FROM product_has_sell WHERE sellCode = ?";
		PreparedStatement pstmt;

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				proudctSellRecordInfo product = new proudctSellRecordInfo();
				
				product.setProductCode(resultSet.getString("productCode"));
				product.setQty(resultSet.getInt("qty"));
				
				productInfo.add(product);
			}

			return productInfo;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 판매된 제품 이름 가져오기 불가 \n");
		return null;
	}


	/*--------------날짜를 통한 판매 조회--------------*/

	public ArrayList<sellRecordInfo> getSellInfoFromDate(String sellDate) {

		// SQL 관련 변수
		String SQL = "SELECT * FROM sell WHERE cancel = 0 AND date_format(sellDate,'%Y-%m-%d')=?";
		PreparedStatement pstmt;
		ArrayList<sellRecordInfo> sellInfoList = new ArrayList<sellRecordInfo>();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellDate);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				sellRecordInfo sellInfo = new sellRecordInfo();

				// 구매날짜, 판매코드, 결제방법, 총 판매가격, 구매자코드
				sellInfo.setSellCode(resultSet.getString("sellCode"));
				sellInfo.setCustomerCode(resultSet.getString("customerCode"));
				sellInfo.setPaymentMethod(resultSet.getInt("paymentMethod"));
				sellInfo.setSellDate(resultSet.getString("sellDate"));
				sellInfo.setTotalPrice(resultSet.getInt("totalPrice"));
				sellInfo.setcancel(resultSet.getInt("cancel"));

				sellInfoList.add(sellInfo);
			}

			return sellInfoList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 판매 정보를 가져올 수 없음 \n");
		return null; // 데이터베이스 오류
	}

	/*--------------상품제품판매를 취소하는 부분--------------*/

	public int sellProudctcancel(String sellCode) {
		System.out.println(sellCode);
		// SQL 관련 변수
		String SQL = "UPDATE product_has_sell SET cancel='1' WHERE sellCode = ? ";
		PreparedStatement pstmt;

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			pstmt.executeUpdate();

			return 1;

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: 상품판매취소에 실패했습니다 \n");
		return -1;
	}

	/*--------------상품판매를 취소하는 부분--------------*/

	public int sellInfocancel(String sellCode) {

		// SQL 관련 변수
		String SQL = "UPDATE sell SET cancel='1' WHERE sellCode = ? ";
		PreparedStatement pstmt;

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			pstmt.executeUpdate();

			return 1;

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: 판매정보취소에 실패했습니다 \n");
		return -1;
	}

	// 판매코드로 제품 정보 받아오기
	public ArrayList<proudctSellRecordInfo> getsellProductList(String sellCode) {

		// 날짜, 상품명, 수량, 가격, 총 판매 금액

		ArrayList<proudctSellRecordInfo> proudctInfoList = new ArrayList<proudctSellRecordInfo>();

		// SQL 관련 변수
		String SQL = "SELECT qty, totalPrice, productName FROM product_has_sell WHERE sellCode = ?";
		PreparedStatement pstmt;

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				proudctSellRecordInfo productInfo = new proudctSellRecordInfo();

				productInfo.setQty(Integer.parseInt(resultSet.getString("qty")));
				productInfo.setTotalprice(resultSet.getInt("totalPrice"));
				productInfo.setProductName(resultSet.getString("productName"));

				proudctInfoList.add(productInfo);

			}

			return proudctInfoList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 제품 정보 받아오기 불가 \n");
		return null;
	}

	// 판매코드에 대한 제품 리스트 가져오기
	public ArrayList<String> getProductCodeList(String sellCode) {

		// SQL 관련 변수
		String SQL = "SELECT productCode FROM product_has_sell WHERE sellCode = ?";
		PreparedStatement pstmt;
		ArrayList<String> productCodeList = new ArrayList<String>();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				String productCode;

				productCode = resultSet.getString("productCode");
				productCodeList.add(productCode);
			}

			return productCodeList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 판매 정보를 가져올 수 없음 \n");
		return null; // 데이터베이스 오류
	}

	// 고객 정보로 sellCode 알아오기
	public ArrayList<String> getSellCodeList(String customerCode) {

		// SQL 관련 변수
		String SQL = "SELECT sellCode FROM sell WHERE customerCode = ?";
		PreparedStatement pstmt;
		ArrayList<String> sellCodeList = new ArrayList<String>();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, customerCode);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				String sellCode;

				sellCode = resultSet.getString("sellCode");
				sellCodeList.add(sellCode);
			}

			return sellCodeList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 판매 정보를 가져올 수 없음 \n");
		return null; // 데이터베이스 오류
	}

	// 상품코드로 위험 수준 알아오기
	public int getAttentionLevel(String productCode) {

		// SQL 관련 변수
		String SQL = "SELECT attentionLevel FROM product WHERE productCode = ?";
		PreparedStatement pstmt;
		int attentionLevel = 0;

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, productCode);

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {

				attentionLevel = resultSet.getInt("attentionLevel");
			}

			return attentionLevel;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: 판매 정보를 가져올 수 없음 \n");
		return -1; // 데이터베이스 오류
	}
	
	

}
