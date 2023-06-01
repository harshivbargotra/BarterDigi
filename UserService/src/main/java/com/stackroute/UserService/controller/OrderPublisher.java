package com.stackroute.UserService.controller;

import com.stackroute.UserService.config.MQConfig;
import com.stackroute.UserService.dto.*;
import com.stackroute.UserService.enumm.OrderStatus;
import com.stackroute.UserService.enumm.PaymentMethod;
import com.stackroute.UserService.enumm.PaymentStatus;
import com.stackroute.UserService.exception.BuyerException;
import com.stackroute.UserService.exception.OrderException;
import com.stackroute.UserService.repository.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/userService")
public class OrderPublisher {
	
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderPublisherRepository orderRepository;
    
    @Autowired
	private BuyerRepository buyerRepo;

    @Autowired
    private RabbitTemplate template;



    @PostMapping("/publishOrder")
	public String OrderPublish(@RequestBody com.stackroute.UserService.dto.OrderPublisher orderPublisher)
			throws OrderException, BuyerException {
		Optional<BuyerDTO> existingBuyerOptional= this.buyerRepo.findById(orderPublisher.getBuyerDTO().getBuyerId());
		
		if(existingBuyerOptional.get().getCartDTO() == null ) {
			throw new BuyerException("Please complete your profile.");
		}
		else if(existingBuyerOptional.get().getCartDTO().getTotalQuantity() <= 0) {
			throw new BuyerException("Cart is empty !!! Please add some item.");
		}
		System.out.println("Order Request Received for User Id : "+orderPublisher.getBuyerDTO().getBuyerId());
		orderPublisher.getOrderDTO().setOrderId(UUID.randomUUID().toString());
		orderPublisher.getOrderDTO().setUserId(String.valueOf(orderPublisher.getBuyerDTO().getUserDTO().getId()));
		orderPublisher.getOrderDTO().setOrderPlacedOn(orderPublisher.getOrderDTO().getOrderPlacedOn());
		orderPublisher.getOrderDTO().setPaymentStatus(PaymentStatus.inProgress);
		orderPublisher.getOrderDTO().setPaymentMethod(PaymentMethod.creditcard);
		orderPublisher.getOrderDTO().setOrderStatus(OrderStatus.inProgress);

		Optional<BuyerDTO> buyerDetails = this.buyerRepo.findById(orderPublisher.getBuyerDTO().getBuyerId());
		
		if (buyerDetails.isPresent()) {
			orderPublisher.getOrderDTO().setUserEmail(buyerDetails.get().getUserDTO().getEmail());
			orderPublisher.getBuyerDTO().setCartDTO(buyerDetails.get().getCartDTO());
			orderPublisher.getBuyerDTO().setUserBankDetails(buyerDetails.get().getUserBankDetails());
			orderPublisher.getBuyerDTO().setAddress(buyerDetails.get().getAddress());
			orderPublisher.getBuyerDTO().setUserDTO(buyerDetails.get().getUserDTO());
			orderPublisher.getOrderDTO().setPurchasedProduct(buyerDetails.get().getCartDTO().getProducts());
			orderPublisher.getOrderDTO().setTotalPrice(buyerDetails.get().getCartDTO().getTotalAmount());
			orderPublisher.getOrderDTO().setProductSellerMap(getProducts(buyerDetails.get().getCartDTO().getProducts()));
			
			template.convertAndSend(MQConfig.ORDER_EXCHANGE, MQConfig.ORDER_ROUTING_KEY, orderPublisher);
			this.orderRepository.save(orderPublisher);
			System.out.println("Order placed !!!");
			return orderPublisher.getOrderDTO().getOrderId() + "  Order Published.";
		} else {

			throw new OrderException("Order could not be placed");
		}
    }
    public Map<String, Integer> getProducts(Map<String,Integer> cart) {
        Set<String> products = cart.keySet();
        Map<String,Integer> sellerDetails = new HashMap<>();
        for(String productId :products){
            ProductDTO productDTO = this.productRepository.findById(productId).get();
            sellerDetails.put(productId,productDTO.getSellerId());
        }
        return sellerDetails;
    }

    @PostMapping("cancelOrder/{orderId}")
    public String CancelOrder(@PathVariable("orderId") String orderId) throws OrderException {
//        Optional<com.stackroute.UserService.dto.OrderPublisher> orderOpt = this.orderRepository.findById(Integer.valueOf(orderId));
//        if(orderOpt.isEmpty())
//            throw new OrderException(orderId + "does not exist !!");
        template.convertAndSend(MQConfig.CANCEL_ORDER_EXCHANGE,
                MQConfig.CANCEL_ORDER_ROUTING_KEY,orderId);
        return orderId + "Order Cancel.";
    }
}
