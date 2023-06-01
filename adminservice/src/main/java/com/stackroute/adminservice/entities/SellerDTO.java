package com.stackroute.adminservice.entities;

import javax.validation.constraints.Min;

public class SellerDTO {
	
	@Min(value=(long) 1.0,message="Give valid sellerId")
	private Integer sellerId;

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
}
