package com.stackroute.sellerservice.entity;

public class Address {

	String houseNumber;
	String streetLocality;
	String landmark;
	String city;
	String state;
	String pinCode;

	public Address() {
		super();
	}

	public Address(String houseNumber, String streetLocality, String landmark, String city, String state,
			String pinCode) {
		super();
		this.houseNumber = houseNumber;
		this.streetLocality = streetLocality;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreetLocality() {
		return streetLocality;
	}

	public void setStreetLocality(String streetLocality) {
		this.streetLocality = streetLocality;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Address [houseNumber=" + houseNumber + ", streetLocality=" + streetLocality + ", landmark=" + landmark
				+ ", city=" + city + ", state=" + state + ", pinCode=" + pinCode + "]";
	}
	

}
