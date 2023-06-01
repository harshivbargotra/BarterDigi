package com.stackroute.adminservice.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderAdminDTO {
	
	@NotNull(message="The orderId entered cannot be null")
	@NotBlank(message="The orderId entered cannot be blank")
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
