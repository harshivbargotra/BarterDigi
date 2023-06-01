package com.stackroute.UserService.dto;

import com.stackroute.UserService.entity.Address;
import com.stackroute.UserService.entity.BankDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Random;

@Document(collection="Buyer")
public class BuyerDTO {

    @Id
    private Integer buyerId;
    private UserDTO userDTO;
    private CartDTO cartDTO;
    private BankDetails userBankDetails;
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

    public BankDetails getUserBankDetails() {
        return userBankDetails;
    }

    public void setUserBankDetails(BankDetails userBankDetails) {
        this.userBankDetails = userBankDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
  
    
    public BuyerDTO() {
		super();
	}
	
	public BuyerDTO(UserDTO user) {
		
		System.out.println("User Details : "+user);
		this.buyerId=user.getId();
		this.userDTO=new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getUserType());
		System.out.println("Buyer Created");
	}
    
	

}

