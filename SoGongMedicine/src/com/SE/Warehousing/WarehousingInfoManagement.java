package com.SE.Warehousing;
import java.time.LocalDateTime;
import com.SE.stock.*;
import java.util.Date;
import java.util.Vector;

//  @ Project : 소공 약국
//  @ File Name : WarehousingInfoManagement.java
//  @ Date : 2019-05-20
//  @ Author : 신충섭


public class WarehousingInfoManagement {
	private static int cnt = 0;
	private DBManager db;
	public WarehousingInfoManagement() {
		db = new DBManager();
	}
	public boolean insertWarehousings(Vector<Vector> infos) {
		WarehousingInfo warehousingInfo = null;
		for(int i=0; i<infos.size(); i++) {
			if(infos.get(i).get(i)==null)	continue;
			warehousingInfo = new WarehousingInfo();
			warehousingInfo.setProductCode((String)infos.get(i).get(0));
	        String name = db.selectProductNameByProductCode(warehousingInfo.getProductCode());
	        warehousingInfo.setProductName(name);
			warehousingInfo.setWarehousingDate((String)infos.get(i).get(1));
			warehousingInfo.setUnit((String)infos.get(i).get(2));
			warehousingInfo.setQty(Integer.parseInt((String)infos.get(i).get(3)));
			warehousingInfo.setUnitPrice(Integer.parseInt((String)infos.get(i).get(4)));
			warehousingInfo.setWarehousingState((String)infos.get(i).get(5));
			
			warehousingInfo.setSupplierName((String)infos.get(i).get(6));		
			String supplierCode = db.selectSupplierCodeBySupplierName(warehousingInfo.getSupplierName());
			warehousingInfo.setSupplierCode(supplierCode);		
			
			warehousingInfo.setUserCode((String)infos.get(i).get(7));
			warehousingInfo.setWarehousingCode(createWarehousingCode());
			boolean result = db.insertWarehousingInfo(warehousingInfo);
			if(result==false) return result;
			else {
				StockInfoManagement mgmt = new StockInfoManagement();
				System.out.println(warehousingInfo.getProductCode());
				mgmt.insertStockbyWarehousing(warehousingInfo);
			}
		}
		
		return true;
		
	}
	
	private String createWarehousingCode() {
		LocalDateTime now = LocalDateTime.now();
		String code = "";
		int[] date = new int[5];	// year, month, day, hour, minute
		date[0] = now.getYear()%100;
		date[1] = now.getMonthValue();
		date[2] = now.getDayOfWeek().getValue();
		date[3] = now.getHour();
		date[4] = now.getMinute();
		
		for(int i=0; i<5; i++) {
			if(date[i]<10) {
				code+="0";
			}
			code+=date[i];
		}
		int numberCount = 0;
		int tmp = ++cnt;
		while(tmp!=0) {
			tmp/=10;
			numberCount++;
		}
		for(int i=4; i>numberCount; i--) {
			code+="0";
		}
		code+=cnt;
		return code;
	}
	
	public boolean modifyWarehousing(WarehousingInfo warehousingInfo) {
		String supplierCode = db.selectSupplierCodeBySupplierName(warehousingInfo.getSupplierName());
		warehousingInfo.setSupplierCode(supplierCode);		
		return db.updateWarehousingInfo(warehousingInfo);		
	}
	
	public boolean deleteWarehousing(String warehousingCode) {
		return db.deleteWarehousingInfo(warehousingCode);
	}
	
	public Vector<Vector<String>> lookupWarehousings(String productName, String warehousingDate) {
		Vector<String> codes = db.selectProductCodesByProductName(productName);
		return db.selectWarehousingInfos(codes, warehousingDate);
	}

	public WarehousingInfo lookupWarehousingsAsWarehousingCode(String warehousingCode) {
		WarehousingInfo warehousingInfo = db.selectWarehousingInfosByWarehousingCode(warehousingCode);
		String supplierCode = warehousingInfo.getSupplierCode();
		String supplierName = db.selectSupplierNameBySupplierCode(supplierCode);
		warehousingInfo.setSupplierName(supplierName);
		return warehousingInfo;
	}
	public Vector<Vector<String>> lookupWarehousingsAsProductName(String productName) {
		Vector<String> codes = db.selectProductCodesByProductName(productName);
		return db.selectWarehousingInfosByProductCodes(codes);
		
	}
	
	public Vector<Vector<String>> lookupWarehousingsAsWarehousingDate(String warehousingDate) {
		return db.selectWarehousingInfosByWarehousingDate(warehousingDate);
	}
	
	public void printWarehousingProof() {
	
	}
}
