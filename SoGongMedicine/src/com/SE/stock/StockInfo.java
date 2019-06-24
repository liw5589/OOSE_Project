package com.SE.stock;
public class StockInfo{
	private String productCode;
	private String productname;
	private String qty;
	private String approStock;
	private String expirationDate;
	
	public StockInfo() {}

	public StockInfo(String productCode, String productname, String qty, String approStock, String expirationDate) {
		super();
		this.productCode = productCode;
		this.productname = productname;
		this.qty = qty;
		this.approStock = approStock;
		this.expirationDate = expirationDate;
	}

	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductname() {
		return productname;
	}
	
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public String getQty() {
		return qty;
	}
	
	public void setQty(String qty) {
		this.qty = qty;
	}
	
	public String getApproStock() {
		return approStock;
	}
	
	public void setApproStock(String approStock) {
		this.approStock = approStock;
	}
	
	public String getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	
}