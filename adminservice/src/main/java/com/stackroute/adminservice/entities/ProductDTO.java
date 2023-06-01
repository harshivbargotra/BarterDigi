package com.stackroute.adminservice.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {
	
	@NotNull(message="The productId entered cannot be null")
	@NotBlank(message="The productId entered cannot be blank")
	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
