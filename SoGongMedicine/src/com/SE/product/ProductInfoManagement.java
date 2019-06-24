package com.SE.product;

import java.io.*;
import java.util.*;

public class ProductInfoManagement {
	
	private Vector<ProductInfo> v;
	
	ProductInfoManagement()
	{
		v = new Vector<ProductInfo>();
	}
	
	public Vector<ProductInfo> getV() {
		return v;
	}
	
	public void resetVector() {
		v = null;
		v = new Vector<ProductInfo>();
	}
	
	private DBManager dbm = new DBManager();
	
	
	public boolean modifyProduct(ProductInfo productInfo) {
	
		return dbm.updateProductInfo(productInfo);
	}
	
	public boolean deleteProduct(String productCode) {
		return dbm.deleteProductInfo(productCode);
	}

	public void lookupProduct() {
		v = dbm.selectProductInfos();
		
	}
	
	public ProductInfo lookupProductInfo(String productCode) {
		return dbm.selectProductInfo(productCode);
	}
	
	public void outputBarcode(String productCode) {
	
	}
	
	public void uploadProductList(String fileAddress) {
		 
	}
	public Vector<?> readProductList(String fileAddress) {
		v = new Vector<ProductInfo>();
		
		 try{
	            //파일 객체 생성
	            File file = new File(fileAddress);
	            //스캐너로 파일 읽기
	            Scanner s = new Scanner(file);
	            while(s.hasNextLine()){
	                String tmpLine = s.nextLine();
	                String argu[] = tmpLine.split(",");
	                //argu[0]은 목록번호 이므로 생략함.
	                //객체 생성해서 리스트에 넣기
	                ProductInfo pi = new ProductInfo(argu[0],argu[1],argu[2],argu[3],Integer.parseInt(argu[4]),Integer.parseInt(argu[5]));
	                v.add(pi);
	                //dbm.insertDBProduct(productCode, productClassification, productName, productManufacturer, attentionLevel, productRetailPrice);
	            }
	            
	        }catch (FileNotFoundException e) {
	           System.out.println("파일없음");
	        }
		 
		return v;
	    
	}
}
