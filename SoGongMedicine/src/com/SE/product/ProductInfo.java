package com.SE.product;


public class ProductInfo {
	private String productCode;
	private String productClassification;
	private String productName;
	private String productManufacturer;
	private int attentionLevel;
	private int productRetailPrice;
	
	public ProductInfo() {
		
	}
	
	public ProductInfo(String productCode, String productClassification, String productName, String productManufacturer,
			int attentionLevel, int productRetailPrice) {
		super();
		this.productCode = productCode;
		this.productClassification = productClassification;
		this.productName = productName;
		this.productManufacturer = productManufacturer;
		this.attentionLevel = attentionLevel;
		this.productRetailPrice = productRetailPrice;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductClassification() {
		return productClassification;
	}
	public void setProductClassification(String productClassification) {
		this.productClassification = productClassification;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductManufacturer() {
		return productManufacturer;
	}
	public void setProductManufacturer(String productManufacturer) {
		this.productManufacturer = productManufacturer;
	}
	public int getAttentionLevel() {
		return attentionLevel;
	}
	public void setAttentionLevel(int attentionLevel) {
		this.attentionLevel = attentionLevel;
	}
	public int getProductRetailPrice() {
		return productRetailPrice;
	}
	public void setProductRetailPrice(int productRetailPrice) {
		this.productRetailPrice = productRetailPrice;
	}

	
}
