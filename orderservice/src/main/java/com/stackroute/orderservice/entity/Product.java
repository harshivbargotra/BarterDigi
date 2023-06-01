package com.stackroute.orderservice.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.mongodb.lang.NonNull;

public class Product {
	@Id
	String productId;
	@NonNull
	String productName;
	@NonNull
	Double productPrice;
	@NonNull
	Date productExpiryDate;
	@NonNull
	Product sellerId;
	boolean availability;
	byte[] productPicture;
	public Product() {
		super();
	}
	public Product(String productId, String productName, Double productPrice, Date productExpiryDate, Product sellerId,
			boolean availability, byte[] productPicture) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productExpiryDate = productExpiryDate;
		this.sellerId = sellerId;
		this.availability = availability;
		this.productPicture = productPicture;
	}
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
	public Product getSellerId() {
		return sellerId;
	}
	public void setSellerId(Product sellerId) {
		this.sellerId = sellerId;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public byte[] getProductPicture() {
		return productPicture;
	}
	public void setProductPicture(byte[] productPicture) {
		this.productPicture = productPicture;
	}
}