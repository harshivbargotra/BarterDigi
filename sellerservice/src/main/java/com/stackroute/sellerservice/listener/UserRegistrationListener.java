package com.stackroute.sellerservice.listener;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory; 

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.sellerservice.config.MQConfig;
import com.stackroute.sellerservice.entity.Seller;
import com.stackroute.sellerservice.entity.User;
import com.stackroute.sellerservice.exception.SellerServiceException;
import com.stackroute.sellerservice.service.SellerService;

@Component
public class UserRegistrationListener {
	
	@Autowired
	SellerService sellerService;
	
	private static Logger logger = LoggerFactory.getLogger(UserRegistrationListener.class);

	@RabbitListener(queues = MQConfig.USER_QUEUE)
    public void sellerRegistrationListener(User user) throws SellerServiceException {
		Seller addedSeller=sellerService.addSeller(new Seller(user));
		logger.info("Seller details "+addedSeller);
		logger.info("Received user information and created seller");
    }
    
}
