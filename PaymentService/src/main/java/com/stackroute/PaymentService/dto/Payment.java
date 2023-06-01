package com.stackroute.PaymentService.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("payment")
public class Payment {
	
     @Id
	private String paymentId;
	private String orderId;
	private String userId;
	private String paymentStatus;
	private String paymentMethod;
	private BankDetails bankDetails;
	private Integer paymentAmount;
	private String transactionId;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public Payment(String paymentId, String orderId, String userId, String paymentStatus, String paymentMethod,
			BankDetails bankDetails, Integer paymentAmount, String transactionId) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.userId = userId;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.bankDetails = bankDetails;
		this.paymentAmount = paymentAmount;
		this.transactionId = transactionId;
	}


	
	


}
