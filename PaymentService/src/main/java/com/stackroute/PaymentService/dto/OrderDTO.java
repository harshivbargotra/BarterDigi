package com.stackroute.PaymentService.dto;

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class OrderDTO {
    
    @NotNull(message = "orderId can't be null")
    @NotBlank(message = "orderId can't be blank")
    private String orderId;

    @NotNull(message = "userId can't be null")
    @NotBlank(message = "userId can't be blank")
    private String userId;

    private Date orderPlacedOn;

    @NotNull(message = "paymentStatus can't be null")
    @NotBlank(message = "paymentStatus can't be blank")
    private String paymentStatus;

    @NotNull(message = "paymentMethod can't be null")
    @NotBlank(message = "paymentMethod can't be blank")
    private String paymentMethod;

    @NotNull(message = "orderStatus can't be null")
    @NotBlank(message = "orderStatus can't be blank")
    private String orderStatus;

    @NotNull(message = "userEmail can't be null")
    @NotBlank(message = "userEmail can't be blank")
    private String userEmail;

    private Map<String,Integer> purchasedProduct;

    private Map<String, Integer> productSellerMap;

    private Integer totalPrice;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Map<String, Integer> getProductSellerMap() {
        return productSellerMap;
    }

    public void setProductSellerMap(Map<String, Integer> totalQuantity) {
        this.productSellerMap = totalQuantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
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

    public Map<String, Integer> getPurchasedProduct() {
        return purchasedProduct;
    }

    public void setPurchasedProduct(Map<String, Integer> purchasedProduct) {
        this.purchasedProduct = purchasedProduct;
    }

    public OrderDTO() {
    }

	public OrderDTO(
			@NotNull(message = "orderId can't be null") @NotBlank(message = "orderId can't be blank") String orderId,
			@NotNull(message = "userId can't be null") @NotBlank(message = "userId can't be blank") String userId,
			Date orderPlacedOn,
			@NotNull(message = "paymentStatus can't be null") @NotBlank(message = "paymentStatus can't be blank") String paymentStatus,
			@NotNull(message = "paymentMethod can't be null") @NotBlank(message = "paymentMethod can't be blank") String paymentMethod,
			@NotNull(message = "orderStatus can't be null") @NotBlank(message = "orderStatus can't be blank") String orderStatus,
			@NotNull(message = "userEmail can't be null") @NotBlank(message = "userEmail can't be blank") String userEmail,
			Map<String, Integer> purchasedProduct, Map<String, Integer> productSellerMap, Integer totalPrice) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderPlacedOn = orderPlacedOn;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.orderStatus = orderStatus;
		this.userEmail = userEmail;
		this.purchasedProduct = purchasedProduct;
		this.productSellerMap = productSellerMap;
		this.totalPrice = totalPrice;
	}

}
