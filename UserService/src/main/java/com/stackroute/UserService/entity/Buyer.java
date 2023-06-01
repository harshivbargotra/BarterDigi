package com.stackroute.UserService.entity;

import com.stackroute.UserService.dto.CartDTO;
import com.stackroute.UserService.dto.UserDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public class Buyer {

    private Integer buyerId;
    private UserDTO userDTO;
    private CartDTO cartDTO;
    private List<BankDetails> userBankDetails;
    private Address address;

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public CartDTO getCartDTO() {
        return cartDTO;
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
    }

    public List<BankDetails> getUserBankDetails() {
        return userBankDetails;
    }

    public void setUserBankDetails(List<BankDetails> userBankDetails) {
        this.userBankDetails = userBankDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
