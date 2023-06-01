package com.stackroute.UserService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stackroute.UserService.dto.BuyerDTO;
import com.stackroute.UserService.dto.CartDTO;
import com.stackroute.UserService.dto.ProductDTO;
import com.stackroute.UserService.exception.BuyerException;
import com.stackroute.UserService.exception.CartException;
import com.stackroute.UserService.exception.ProductException;

import java.util.List;

public interface CartService {


    CartDTO addToCart(Integer userId,String productId, Integer quantity)throws CartException;
    BuyerDTO removefromCart(Integer userId, String productId, Integer quantity) throws CartException, ProductException;
    BuyerDTO resetCart(Integer buyerId);

    void deleteAll() throws ProductException;

    Object getProductById(String productId) throws ProductException, JsonMappingException, JsonProcessingException;;

    Object getProductBySellerId(Integer sellerId) throws ProductException, JsonMappingException, JsonProcessingException;

    Object getProductBySellerIdAndAvailability(Integer sellerId,Boolean availability) throws ProductException, JsonMappingException, JsonProcessingException;

    Object getProductWithinPriceRange(Double from,Double to) throws ProductException, JsonMappingException, JsonProcessingException;
    
    Object getProductByName(String productName) throws ProductException, JsonMappingException, JsonProcessingException;

    List<CartDTO> getProductFromCart();
        
	BuyerDTO addBuyer(BuyerDTO buyerDTO) throws BuyerException;
	
	BuyerDTO completeProfile(BuyerDTO buyerDTO)throws BuyerException;

}
