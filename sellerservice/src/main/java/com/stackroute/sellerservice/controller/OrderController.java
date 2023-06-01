package com.stackroute.sellerservice.controller;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stackroute.sellerservice.config.MQConfig;


//@RestController("order")
//@RequestMapping("/sellerService")
public class OrderController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	
	@PostMapping("order/receive")
	public String receiveOrder(@RequestBody Map<String, Map<String, Integer>> orderDetails)
	{
		rabbitTemplate.convertAndSend(MQConfig.exchangeName, MQConfig.ORDER_ROUTING_KEY, orderDetails);
		return "Order received and successfully sent";
	}

}
