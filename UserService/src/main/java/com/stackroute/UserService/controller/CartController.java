package com.stackroute.UserService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stackroute.UserService.config.MQConfig;
import com.stackroute.UserService.dto.*;
import com.stackroute.UserService.exception.BuyerException;
import com.stackroute.UserService.exception.CartException;
import com.stackroute.UserService.exception.ProductException;
import com.stackroute.UserService.repository.BuyerRepository;
import com.stackroute.UserService.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userService")
public class CartController {

    Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartService cartService;
    
    @Autowired
    private BuyerRepository buyerRepository;
    
    @Autowired
    private  RabbitTemplate rabbitTemplate;
    
    
    @PostMapping("user/userDetails")
	public String getUserDetails(@RequestBody UserDTO user) throws BuyerException {
		logger.info("Received User Details : ---->" + user);
		rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, user);
		return "User Details sent for seller registration " + user;
	}

	@PostMapping("cart/add/{buyerId}/{productId}/{quantity}")
	public CartDTO addToCart(@PathVariable("buyerId") Integer buyerId, @PathVariable("productId") String productId,
			@PathVariable("quantity") Integer quantity) throws CartException {
		logger.info("Before addToCart ");
		return this.cartService.addToCart(buyerId, productId, quantity);
	}

	@PatchMapping("cart/remove/{buyerId}/{productId}/{quantity}")
	public BuyerDTO removefromCart(@PathVariable("buyerId") Integer buyerId,
			@PathVariable("productId") String productId, @PathVariable("quantity") Integer quantity)
			throws CartException, ProductException {
		return this.cartService.removefromCart(buyerId, productId, quantity);
	}
	
    @GetMapping("carts")
    public List<CartDTO> getCartDetails() throws CartException {
        logger.info("Before getCartDetails ");
        return this.cartService.getProductFromCart();
    }


    @DeleteMapping("resetProduct")
    public void deleteAll() throws ProductException {
         this.cartService.deleteAll();
    }


    @GetMapping("cart/{buyerId}")
	public CartDTO getCart(@PathVariable Integer buyerId) throws CartException {
		CartDTO cart = this.buyerRepository.findById(buyerId).get().getCartDTO();
		if (cart != null)
			return cart;
		else
			throw new CartException("Could not find cart details");
	}
    
    @GetMapping("cart/reset/{buyerId}")
	public BuyerDTO resetCart(@PathVariable("buyerId") Integer buyerId) {
		logger.info("Before ResetCart");
		return this.cartService.resetCart(buyerId);
	}

    @GetMapping("productDetails/{productId}")
    public Object getProductById(@PathVariable("productId") String productId) throws ProductException, JsonMappingException, JsonProcessingException{
        logger.info("Search Service getProductById");
        return this.cartService.getProductById(productId);
    }

    @GetMapping("product/{sellerId}")
    public Object getProductBySellerId(@PathVariable("sellerId") Integer sellerId) throws ProductException, JsonMappingException, JsonProcessingException{
        logger.info("Search Service getProductBySellerId");
        return this.cartService.getProductBySellerId(sellerId);
    }

    @GetMapping("product/{sellerId}/{availability}")
    public Object getProductBySellerIdAndAvailability(@RequestParam("sellerId") Integer sellerId,@RequestParam("availability") Boolean availability) throws ProductException, JsonMappingException, JsonProcessingException{
        logger.info("Search Service getProductBySellerIdAndAvailability");
        return this.cartService.getProductBySellerIdAndAvailability(sellerId,availability);
    }

    @GetMapping("product/{from}/{to}")
    public Object getProductWithinPriceRange(@RequestParam("from") Double from, @RequestParam("to") Double to) throws ProductException, JsonMappingException, JsonProcessingException{
        logger.info("Search Service getProductWithinPriceRange");
        return this.cartService.getProductWithinPriceRange(from, to);
    }


}
