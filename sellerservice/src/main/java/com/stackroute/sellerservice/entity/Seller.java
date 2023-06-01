package com.stackroute.sellerservice.entity;

import java.util.List;

import javax.annotation.processing.Generated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="seller")
public class Seller {

	User user;
	BankDetails userBankDetails;

	@Id
	Integer sellerId;
	String gstNumber;
	String licenseDetails;
	String panCardDetails;
	String shopName;
	Address shopLocation;
	@Min(10)
	Double businessRange;

	public Seller() {
		super();
	}

	public Seller(User user, BankDetails userBankDetails, String gstNumber, String licenseDetails,
			String panCardDetails, String shopName, Address shopLocation, Double businessRange) {
		super();
		this.user = user;
		this.userBankDetails = userBankDetails;
		this.gstNumber = gstNumber;
		this.licenseDetails = licenseDetails;
		this.panCardDetails = panCardDetails;
		this.shopName = shopName;
		this.shopLocation = shopLocation;
		this.businessRange = businessRange;
		this.sellerId=user.getId();
	}
	
	public Seller(User user) {
		super();
		this.user = user;
		this.sellerId=user.getId();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BankDetails getUserBankDetails() {
		return userBankDetails;
	}

	public void setUserBankDetails(BankDetails userBankDetails) {
		this.userBankDetails = userBankDetails;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getLicenseDetails() {
		return licenseDetails;
	}

	public void setLicenseDetails(String licenseDetails) {
		this.licenseDetails = licenseDetails;
	}

	public String getPanCardDetails() {
		return panCardDetails;
	}

	public void setPanCardDetails(String panCardDetails) {
		this.panCardDetails = panCardDetails;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Address getShopLocation() {
		return shopLocation;
	}

	public void setShopLocation(Address shopLocation) {
		this.shopLocation = shopLocation;
	}

	public Double getBusinessRange() {
		return businessRange;
	}

	public void setBusinessRange(Double businessRange) {
		this.businessRange = businessRange;
	}
	
	public Integer getSellerId() {
		this.sellerId = user.getId();
		return sellerId;
	}

	public void setSellerId() {
		this.sellerId = user.getId();
	}

	@Override
	public String toString() {
		return "Seller [user=" + user + ", userBankDetails=" + userBankDetails + ", sellerId=" + sellerId
				+ ", gstNumber=" + gstNumber + ", licenseDetails=" + licenseDetails + ", panCardDetails="
				+ panCardDetails + ", shopName=" + shopName + ", shopLocation=" + shopLocation + ", businessRange="
				+ businessRange + "]";
	}


}
