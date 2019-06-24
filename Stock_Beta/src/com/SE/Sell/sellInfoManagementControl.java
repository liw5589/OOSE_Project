package com.SE.Sell;

import java.util.ArrayList;

public class sellInfoManagementControl {

	DBManager db;

	// 기본 생성자
	public sellInfoManagementControl() {

	}

	// 제품을 판매한다
	public int sellProduct(ArrayList<proudctSellRecordInfo> productRecodeInfo, sellRecordInfo sellInfo) {

		db = new DBManager();
		String sellCode = createSellCode();

		sellInfo.setSellCode(sellCode);

		for (int i = 0; i < productRecodeInfo.size(); i++) {

			productRecodeInfo.get(i).setSellcode(sellCode);
		}

		db.sellInfoInsert(sellInfo);
		db.sellProduct(productRecodeInfo);

		return 1;
	}

	// 판매를 취소한다
	public int sellProudctcancel(String sellCode) {
		db = new DBManager();

		// 1. 상품판매에서 먼저 취소
		db.sellProudctcancel(sellCode);

		// 2. 판매정보에서 취소
		db.sellInfocancel(sellCode);
		
		// 3. sellCode로 제품 판매 정보 가져오기
		ArrayList<proudctSellRecordInfo> productInfo = new ArrayList<proudctSellRecordInfo>();
		productInfo = db.getProductInfoforcancel(sellCode);
		
		// 4. 재고를 더해준다.
		for(int i = 0; i < productInfo.size(); i++) {
			increaseStockQty(productInfo.get(i).getQty(), productInfo.get(i).getProductCode());
		}

		return 1;
	}

	// 요청한 영수증을 출력한다
	public ArrayList<StringBuilder> requestOutputReceipt(String sellCode) {

		// 날짜, 상품명, 수량, 가격, 총 판매 금액
		ArrayList<proudctSellRecordInfo> proudctInfoList = new ArrayList<proudctSellRecordInfo>();
		sellRecordInfo sellInfo = new sellRecordInfo();
		ArrayList<StringBuilder> sellInfoList = new ArrayList<StringBuilder>();

		StringBuilder productName = new StringBuilder();
		StringBuilder qty = new StringBuilder();
		StringBuilder productPrice = new StringBuilder();
		StringBuilder date = new StringBuilder();
		StringBuilder totalPrice = new StringBuilder();

		db = new DBManager();

		sellInfo = db.getSellInfo(sellCode);
		proudctInfoList = db.getsellProductList(sellCode);

		date.append(sellInfo.getSellDate());
		sellInfoList.add(date);

		totalPrice.append(sellInfo.getTotalPrice());
		sellInfoList.add(totalPrice);

		for (int i = 0; i < proudctInfoList.size(); i++) {

			productName.append(proudctInfoList.get(i).getProductName()+ "\n");
			qty.append(proudctInfoList.get(i).getQty()+ "\n");
			productPrice.append(proudctInfoList.get(i).getTotalprice()+ "\n");

		}

		sellInfoList.add(productName);
		sellInfoList.add(qty);
		sellInfoList.add(productPrice);

		for (int i = 0; i < sellInfoList.size(); i++) {
			System.out.println(sellInfoList.get(i));
		}

		return sellInfoList;

	}

	// 판매 코드로 판매이력조회를 한다
	public sellRecordInfo getLookupSellRecorde(String sellCode) {

		sellRecordInfo sellInfo = new sellRecordInfo();
		db = new DBManager();

		// 판매정보를 가져옴
		sellInfo = db.getSellInfo(sellCode);

		if (sellInfo == null) {
			return null;
		}

		if (sellInfo.getcancel() == 0) {
			// 판매정보에서 가져올 수 없는 것을 다른 테이블 가져옴
			sellInfo.setSellProductList(db.getsellProductNameList(sellCode));
			sellInfo.setCustomerName(db.getCustomerName(sellInfo.getCustomerCode()));

			return sellInfo;
		}

		return null;
	}

	// 날짜로 판매이력조회를 한다
	public ArrayList<sellRecordInfo> getSellDateLookupRecorde(String sellDate) {

		ArrayList<sellRecordInfo> sellInfoList = new ArrayList<sellRecordInfo>();
		db = new DBManager();

		sellInfoList = db.getSellInfoFromDate(sellDate);

		for (int i = 0; i < sellInfoList.size(); i++) {

			sellInfoList.get(i).setSellProductList(db.getsellProductNameList(sellInfoList.get(i).getSellCode()));
			sellInfoList.get(i).setCustomerName(db.getCustomerName(sellInfoList.get(i).getCustomerCode()));
		}

		return sellInfoList;
	}

	// 재고량을 더한다
	public void increaseStockQty(int value, String productCode) {
		db = new DBManager();

		// 양수 값 입력
		db.StockUpdateIncrease(value, productCode);
	}

	// 재고량을 줄인다
	public void decreaseStockQty(int value, String productCode) {
		DBManager db = new DBManager();

		// 양수 값 입력
		db.StockUpdateDecrase(value, productCode);
	}

	// 가입자인지 확인한다
	public String customerCheck(String name) {

		db = new DBManager();
		String code = db.getCustomerCode(name);

		if (code == null) {
			return "비회원";
		} else {
			return code;
		}
	}

	// 비회원 코드 가져오기
	public String getNoCustomerCode() {

		db = new DBManager();
		String code = db.getCustomerCode("비회원");

		return code;
	}

	// 회원 코드 가져오기
	public String getCustomerCode(String name) {

		db = new DBManager();
		String code = db.getCustomerCode(name);

		return code;
	}

	// 회원 이름 가져오기
	public String getCustomerName(String customerCode) {

		db = new DBManager();
		String customerName = db.getCustomerCode(customerCode);

		return customerCode;
	}

	// 할인율을 적용한다
	public int setCustomerDiscount(int totalPrice) {

		return totalPrice * 5 / 100;
	}

	// 판매코드를 생성한다
	public String createSellCode() {

		db = new DBManager();
		int serial = db.getIndex();

		String suffix = String.format("%03d", serial);
		String sellCode = "SELL-" + suffix;

		System.out.println(sellCode + "판매코드 생성됨");

		return sellCode;
	}

	// 상품에 대한 정보를 받아온다
	public proudctSellRecordInfo getProductInfo(String productCode) {
		DBManager db = new DBManager();

		if (db.getProductInfo(productCode) == null) {
			return null;
		} else {
			return db.getProductInfo(productCode);
		}
	}

	// 고객 위험 수준 가져오기
	public int getInterestedCustomer(String customerName) {
		DBManager db = new DBManager();
		int level = 0;

		// 고객이름으로 고객코드 가져오기
		String CusomerCode = db.getCustomerCode(customerName);

		// 고객코드로 판매정보 가져오기
		ArrayList<String> sellCodeList = new ArrayList<String>();
		sellCodeList = db.getSellCodeList(CusomerCode);

		// 판매코드로 상품코드 받아오기
		ArrayList<String> productCodeList = new ArrayList<String>();
		for (int i = 0; i < sellCodeList.size(); i++) {
			productCodeList.addAll(db.getProductCodeList(sellCodeList.get(i)));
			System.out.println(productCodeList.get(i));
		}

		// 상품코드로 위험레벨 가져오기
		for (int i = 0; i < productCodeList.size(); i++) {
			
			int attention = db.getAttentionLevel(productCodeList.get(i));
			System.out.println(attention);
			if (attention == 0) {
				level += 10;
			} else if (attention == 1) {
				level += 5;
			} else {
				level += 1;
			}
		}
		
		return level;
	}
	
	public void setAttentionCustomer(int level, String customerCode) {
		DBManager db = new DBManager();

		int result = db.customerAttentionLevel(level, customerCode);
		
		if(result == -1) { System.out.println("업데이트 실패함");}
		else {
			System.out.println("업데이트 성공함");
		}
	}
}
