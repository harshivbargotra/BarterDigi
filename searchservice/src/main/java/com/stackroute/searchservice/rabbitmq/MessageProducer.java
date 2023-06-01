package com.stackroute.searchservice.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.stackroute.searchservice.collection.ProductDTO;

@RestController
public class MessageProducer {
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("product/delete")
	public String publishMessage(@RequestBody ProductDTO product)
	{
		template.convertAndSend(MQConfig.EXCHANGE, MQConfig.USER_ROUTING_KEY, product);
		return "Message Published to User Queue!";
	}

}
