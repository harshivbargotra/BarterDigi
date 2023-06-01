package com.stackroute.sellerservice.entity;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="products")
public class Product {

	@Id
	String productId;
	@NotNull(message = "Product Name can not be null")
	String productName;
	@NotNull(message = "Product price can not be null")
	Double productPrice;
	@NotNull(message = "Please mention the expiry date")
	Date productExpiryDate;
	
	@NotNull
	Integer sellerId;
	boolean availability;
	byte[] productPicture;
	
	String profileURL;

	public Product() {
		super();
	}

	public Product(String productId, String productName, Double productPrice, Date productExpiryDate, Integer sellerId,
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

	public byte[] getProductPicture() {
		return productPicture;
	}

	public String getProfileURL() {
		return profileURL;
	}

	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
	
	public void setProductPicture(byte[] productPicture) {
		this.productPicture = productPicture;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productExpiryDate=" + productExpiryDate + ", sellerId=" + sellerId + ", availability="
				+ availability + ", productPicture=" + Arrays.toString(productPicture) + "]";
	}

}
