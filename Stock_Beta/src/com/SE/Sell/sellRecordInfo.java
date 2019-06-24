package com.SE.Sell;
//판매에 대한 엔티티
public class sellRecordInfo {
	
	private String sellCode; // 판매코드
	private String customerCode;
	private String customerName; // 고객 이름
	private int paymentMethod; // 결제 방법 0:현금, 1:카드
	private String sellPersonChargeCode; // 판매 담당자 코드
	private String sellDate; // 판매날짜
	private int totalPrice; // 전체 판매 가격
	private StringBuilder sellProductList;
	private int cancel; //취소여부 0: 정상, 1: 취소
	private String productClassification;

	public String getProductClassification() {
		return productClassification;
	}

	public void setProductClassification(String productClassification) {
		this.productClassification = productClassification;
	}

	public int getcancel() {
		return cancel;
	}

	public void setcancel(int cancel) {
		this.cancel = cancel;
	}

	public StringBuilder getSellProductList() {
		return sellProductList;
	}

	public void setSellProductList(StringBuilder sellProductList) {
		this.sellProductList = sellProductList;
	}

	sellRecordInfo() {}
	
	sellRecordInfo(String customerName, int paymentMethod)
	{
		this.customerName = customerName;
		this.paymentMethod = paymentMethod;
	}
	
	public String getSellCode() {
		return sellCode;
	}

	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getSellPersonChargeCode() {
		return sellPersonChargeCode;
	}

	public void setSellPersonChargeCode(String sellPersonChargeCode) {
		this.sellPersonChargeCode = sellPersonChargeCode;
	}

	public String getSellDate() {
		return sellDate;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
}
