package com.stackroute.UserService.entity;

import org.springframework.data.annotation.Id;
import java.util.HashMap;
import java.util.Map;


public class Cart {
    @Id
    private String cartId;

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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Cart() {
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    public Cart(String cartId, Integer totalQuantity, Double totalAmount, Map<String, Integer> products) {
        this.cartId = cartId;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.products = products;
    }
}
