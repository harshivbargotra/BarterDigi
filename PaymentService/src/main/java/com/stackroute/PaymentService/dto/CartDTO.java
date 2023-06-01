package com.stackroute.PaymentService.dto;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class CartDTO {
   
    @NotNull(message = "cartId can't be null")
    @NotBlank(message = "cartId can't be blank")
    private String cartId;

    @NotNull(message = "totalQuantity can't be null")
    @NotBlank(message = "totalQuantity can't be blank")
    private Integer totalQuantity;
    private Double totalAmount;
    Map<String,Integer> products = new HashMap<>();

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Map<String,Integer>  getProducts() {
        return products;
    }


    public void setProducts(Map<String,Integer>  products) {
        this.products = products;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CartDTO() {
    }

	public CartDTO(
			@NotNull(message = "cartId can't be null") @NotBlank(message = "cartId can't be blank") String cartId,
			@NotNull(message = "totalQuantity can't be null") @NotBlank(message = "totalQuantity can't be blank") Integer totalQuantity,
			Double totalAmount, Map<String, Integer> products) {
		super();
		this.cartId = cartId;
		this.totalQuantity = totalQuantity;
		this.totalAmount = totalAmount;
		this.products = products;
	}


}
