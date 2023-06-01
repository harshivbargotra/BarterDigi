package com.stackroute.sellerservice.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.sellerservice.config.MQConfig;
import com.stackroute.sellerservice.entity.Seller;
import com.stackroute.sellerservice.entity.User;

@RestController("user")
public class UserController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@PostMapping("addUser")
	String addUser(@RequestBody User user)
	{
		rabbitTemplate.convertAndSend(MQConfig.exchangeName, MQConfig.USER_ROUTING_KEY, user);
		return "sent the user details to the queue";
	}

}
