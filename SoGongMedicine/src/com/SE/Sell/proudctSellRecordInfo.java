package com.SE.Sell;


//상품에 대한 판매정보 엔티티
public class proudctSellRecordInfo {
	
	private String productCode;
	private String productName;
	private String sellcode;
	private int qty;
	private int totalprice;
	private int productPrice;
	private int cancle;
	private String attentionLevel;
	private int confidenceRate;
	
	public int getConfidenceRate() {
		return confidenceRate;
	}

	public void setConfidenceRate(int confidenceRate) {
		this.confidenceRate = confidenceRate;
	}

	//기본 생성자
	public proudctSellRecordInfo() {}
	
	//판매 생성자
	public proudctSellRecordInfo(String productCode, int qty)
	{
		this.productCode = productCode;
		this.qty = qty;
	}
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSellcode() {
		return sellcode;
	}

	public void setSellcode(String sellcode) {
		this.sellcode = sellcode;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getCancle() {
		return cancle;
	}

	public void setCancle(int cancle) {
		this.cancle = cancle;
	}

	public String getAttentionLevel() {
		return attentionLevel;
	}

	public void setAttentionLevel(String attentionLevel) {
		this.attentionLevel = attentionLevel;
	}
	
}