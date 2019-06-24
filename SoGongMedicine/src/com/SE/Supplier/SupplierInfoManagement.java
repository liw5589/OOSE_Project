
//@ Project : 소공 약국
//@ File Name : SupplierInfoManagement.java
//@ Date : 2019-06-06
//@ Author : 박경린

package com.SE.Supplier;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Vector;

public class SupplierInfoManagement {
   private static int cnt = 0;
   private DBManager db;
   
   public SupplierInfoManagement() {
      db = new DBManager();
   }
   
   //거래처 입력
   public void insertSupplier(Vector<Vector> infos) {   
      if(infos.get(0).get(0)==null) return;
      SupplierInfo supplierInfo = null;
      for(int i=0;i<infos.size();i++) {
         if(infos.get(i).get(0)==null) return;
         int serial = db.createSupplierCode();
         String suffix = String.format("%03d", serial);
         String supplierCode = "SUPL-"+suffix;
         
         supplierInfo = new SupplierInfo();
         supplierInfo.setName((String)infos.get(i).get(0));
         supplierInfo.setEmail((String)infos.get(i).get(1));
         supplierInfo.setManager((String)infos.get(i).get(2));
         supplierInfo.setPhoneNum((String)infos.get(i).get(3));
         supplierInfo.setSupplierCode(supplierCode);
         db.insertSupplierInfo(supplierInfo);
      }
   }

   //거래처 수정
   public boolean modifySupplier(SupplierInfo supplierInfo) {      
      return db.updateSupplierInfo(supplierInfo);
   }
   
   //거래처 삭제
   public boolean deleteSupplier(String supplierCode) {
      return db.deleteSupplierInfo(supplierCode);
   }
   
   //거래처 조회
   public Vector<Vector<String>> lookupSupplier(){
      Vector<Vector<String>> supplierInfo = new Vector<Vector<String>>();
      supplierInfo = db.selectSupplierInfo();
      cnt=supplierInfo.size();
      return db.selectSupplierInfo();
   }
   
   //거래처 검색(거래처 코드)
   public SupplierInfo lookupSupplierAsSupplierCode(String supplierCode) {
      SupplierInfo supplierInfo = db.selectSupplierInfoBySupplierCode(supplierCode);
      return supplierInfo;
   }
}