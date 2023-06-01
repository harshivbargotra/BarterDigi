package com.stackroute.UserService.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Document(collection = "products")
public class Product {
    @NotNull(message = "productId can't be null")
    @NotBlank(message = "productId can't be blank")
    private String productId;

    @NotNull(message = "productName can't be null")
    @NotBlank(message = "productName can't be blank")
    private String productName;

    @NotNull(message = "productPrice can't be null")
    @NotBlank(message = "productPrice can't be blank")
    private Double productPrice;

    @NotNull(message = "productExpiryDate can't be null")
    @NotBlank(message = "productExpiryDate can't be blank")
    private Date productExpiryDate;

    @NotNull(message = "sellerId can't be null")
    @NotBlank(message = "sellerId can't be blank")
    private Integer sellerId;

    private Integer individualQuantity;

    public Integer getIndividualQuantity() {
        return individualQuantity;
    }

    public void setIndividualQuantity(Integer individualQuantity) {
        this.individualQuantity = individualQuantity;
    }

    private Boolean availability;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Date getProductExpiryDate() {
        return productExpiryDate;
    }

    public void setProductExpiryDate(Date productExpiryDate) {
        this.productExpiryDate = productExpiryDate;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }


    public Product() {

    }

    public Product(String productId, String productName, Double productPrice, Date productExpiryDate, Integer sellerId, Boolean availability) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productExpiryDate = productExpiryDate;
        this.sellerId = sellerId;
        this.availability = availability;
    }
}
