package com.stackroute.PaymentService.dto;



public class BuyerDTO {

   
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
		// TODO Auto-generated constructor stub
	}
	public BuyerDTO(Integer buyerId, UserDTO userDTO, CartDTO cartDTO, BankDetails userBankDetails, Address address) {
		super();
		this.buyerId = buyerId;
		this.userDTO = userDTO;
		this.cartDTO = cartDTO;
		this.userBankDetails = userBankDetails;
		this.address = address;
	}

}
