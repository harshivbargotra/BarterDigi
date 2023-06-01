package com.stackroute.UserService.consumer;

import com.stackroute.UserService.config.MQConfig;
import com.stackroute.UserService.controller.OrderPublisher;
import com.stackroute.UserService.dto.BuyerDTO;
import com.stackroute.UserService.dto.UserDTO;
import com.stackroute.UserService.exception.BuyerException;
import com.stackroute.UserService.repository.UserRepository;
import com.stackroute.UserService.service.CartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserListener {

    Logger logger = LoggerFactory.getLogger(UserListener.class);
    
    @Autowired
    private CartService cartService;

    
    @RabbitListener(queues = MQConfig.QUEUE)
    public void listen(UserDTO user) throws BuyerException{
    	logger.info("User Information Received " + user);
    	BuyerDTO addedBuyer=cartService.addBuyer(new BuyerDTO(user));
        logger.info("Buyer Created for user " + user.getId()+" with buyer id : "+addedBuyer.getBuyerId());
        
    }
}
