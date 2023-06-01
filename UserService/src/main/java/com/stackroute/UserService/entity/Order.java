package com.stackroute.UserService.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

public class Order {
    private String orderId;
    private String userId;
    private Date orderPlacedOn;
    private String paymentStatus;
    private String paymentMethod;
    private String orderStatus;
    private String userEmail;

    private Map<String,Integer> purchasedProduct;

    private Map<String, Integer> productSellerMap;

    private Double totalPrice;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderPlacedOn() {
        return orderPlacedOn;
    }

    public void setOrderPlacedOn(Date orderPlacedOn) {
        this.orderPlacedOn = orderPlacedOn;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Map<String, Integer> getProductSellerMap() {
        return productSellerMap;
    }

    public void setProductSellerMap(Map<String, Integer> productSellerMap) {
        this.productSellerMap = productSellerMap;
    }

    public Map<String, Integer> getPurchasedProduct() {
        return purchasedProduct;
    }

    public void setPurchasedProduct(Map<String, Integer> purchasedProduct) {
        this.purchasedProduct = purchasedProduct;
    }

    public Order() {
    }

}
