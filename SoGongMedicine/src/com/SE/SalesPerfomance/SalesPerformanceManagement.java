package com.SE.SalesPerfomance;


import java.util.Vector;

public class SalesPerformanceManagement {
	
	private static int cnt = 0;
	private DBManager db;
	
	public SalesPerformanceManagement() {
		db = new DBManager();
	}
	
	public Vector<Vector<String>> lookupSalesPerformanceAsProductCode(String productCode){
		return db.selectProductSellRecordInfo(productCode);
		
	}
	
	public Vector<Vector<String>> lookupSalesPerformanceAsDate(String sellDate1, String sellDate2){
		return db.selectSellRecordInfo(sellDate1,sellDate2);
	}
	
	
//	public Vector<Vector<String>> lookupSalesPerformanceAsProductClassification(String productClassification){
//		return db.selectClassificationInfo(productClassification);
//	}
	
	
	
}
