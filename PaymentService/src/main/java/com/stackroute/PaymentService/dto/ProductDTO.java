package com.stackroute.PaymentService.dto;


import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {
	
	@NotBlank(message="Please do not give blank productId")
	@NotNull(message="Please do not give null productId")
	private String productId;
	
	@NotBlank(message="Please do not give blank productName")
	@NotNull(message="Please do give null productName")
	private String productName;
	
	@Min(value=(long) 10.0,message="Give valid productPrice")
	private Double productPrice;
	
	private Date productExpiryDate;
	
	@Min(value=1,message="Please give a valid sellerId")
	private Integer sellerId;
	
	
	private boolean availability;
	
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Date getProductExpiryDate() {
		return productExpiryDate;
	}
	public void setProductExpiryDate(Date productExpiryDate) {
		this.productExpiryDate = productExpiryDate;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productExpiryDate=" + productExpiryDate + ", sellerId=" + sellerId + ", availability="
				+ availability +"]";
	}
	public ProductDTO(String productId, String productName, Double productPrice, Date productExpiryDate,
			Integer sellerId, boolean availability) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productExpiryDate = productExpiryDate;
		this.sellerId = sellerId;
		this.availability = availability;
		
	}
	public ProductDTO() {
		super();
		
	}
	
	
	
	
}
