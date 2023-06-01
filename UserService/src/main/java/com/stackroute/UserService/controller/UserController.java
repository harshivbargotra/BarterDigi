package com.stackroute.UserService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.UserService.dto.BuyerDTO;
import com.stackroute.UserService.dto.OrderPublisher;
import com.stackroute.UserService.entity.Address;
import com.stackroute.UserService.enumm.PaymentMethod;
import com.stackroute.UserService.exception.BuyerException;
import com.stackroute.UserService.exception.PaymentException;
import com.stackroute.UserService.exception.UserException;
import com.stackroute.UserService.repository.BuyerRepository;
import com.stackroute.UserService.repository.UserRepository;
import com.stackroute.UserService.service.CartService;
import com.stackroute.UserService.service.UserService;

@RestController
@RequestMapping("/userService")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private BuyerRepository buyerRepository;

    Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @PutMapping("buyer/address/{buyerId}")
    public BuyerDTO changeAddress(@RequestBody Address address,@PathVariable("buyerId")Integer buyerId) throws UserException {
        logger.info("change address of user");
        return this.userService.addAddress(buyerId, address);
    }
    
    @PostMapping("buyer/completeProfile")
    public BuyerDTO completeProfile(@RequestBody BuyerDTO buyerDTO) throws BuyerException {
       logger.info("complete Buyer Profile ---->");
      return this.cartService.completeProfile(buyerDTO);
    }
	
	@GetMapping("buyer/{buyerId}")
	public BuyerDTO BuyerDetails(@PathVariable("buyerId") Integer buyerId) throws BuyerException {
		logger.info("Buyer Details---->");
		BuyerDTO existingBuyer = this.buyerRepository.findById(buyerId).get();
		if (existingBuyer == null)
			throw new BuyerException(buyerId + " does not exists");
		return existingBuyer;
	}
	
//	@GetMapping("buyer/paymentStatus")
//	public String PaymentStatus(@RequestParam("paymentMethod") String paymentMethod) throws PaymentException {
//		OrderPublisher orderPublisher = new OrderPublisher();
//		if(paymentMethod.equalsIgnoreCase(PaymentMethod.creditcard)) {
//			orderPublisher.getOrderDTO().setPaymentMethod("CREDITCARD");
//		}else if(paymentMethod.equalsIgnoreCase(PaymentMethod.debitcard)) {
//		    orderPublisher.getOrderDTO().setPaymentMethod("DEBITCARD");
//		}else {
//			throw new PaymentException("this payment method is not allowed !!");
//		}
//		return orderPublisher.getOrderDTO().getPaymentMethod();
//		
//	}

}

