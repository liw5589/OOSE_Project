package com.SE.stock;
import com.SE.Warehousing.*;

import java.util.Vector;

public class StockInfoManagement {
	private static int cnt = 0;
	private DBManager db;
	public StockInfoManagement() {
		db = new DBManager();
	}
	
	public void insertStockbyWarehousing(WarehousingInfo warehousingInfo) {
		db.insertStockInfo(warehousingInfo);
	}
	
	public Vector<Vector<String>> lookupTotalStock() {
		return db.selectTotalStock();
	}
	
	public Vector<Vector<String>> lookupStockAsProductCode(String productCode){
		return db.selectStockInfoAsproductCode(productCode);
	}
	
	public Vector<Vector<String>> lookupStockAsExpirationDate(String expirationDate_1, String ExpirationDate_2){
		return db.selectStockInfoAsexpirationDate(expirationDate_1, ExpirationDate_2);
	}
	
	public void settingApproStock(String productCode, String approStock) {
		db.updateApproStock(productCode, approStock);
	}
}
