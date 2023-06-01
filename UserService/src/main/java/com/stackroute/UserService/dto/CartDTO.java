package com.stackroute.UserService.dto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
@Document(collection = "cart")
public class CartDTO {
    @Id
    @NotNull(message = "cartId can't be null")
    @NotBlank(message = "cartId can't be blank")
    private String cartId;

    @NotNull(message = "totalQuantity can't be null")
    @NotBlank(message = "totalQuantity can't be blank")
    private Integer totalQuantity=0;
    private Double totalAmount=0.0;
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

    public CartDTO(String cartId) {
    	this.cartId=cartId;
    	this.products=new HashMap<>();
    	this.totalAmount=0.0;
    	this.totalQuantity=0;
    }

}
