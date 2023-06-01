package com.stackroute.sellerservice.listener;

import com.stackroute.sellerservice.config.MQConfig;
import com.stackroute.sellerservice.entity.Product;
import com.stackroute.sellerservice.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderNotificationListener {

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	RabbitTemplate rabbitTemplate;

	private static Logger logger = LoggerFactory.getLogger(OrderNotificationListener.class);

	@RabbitListener(queues = MQConfig.ORDER_QUEUE)
	public void listener(Map<String, Map<String, Integer>> orderDetails) {
		logger.info("Order details has received from the order queue");
		Set<String> orderId = orderDetails.keySet();
		Map<String, Map<String, String>> orderUpdatedStatus=new HashMap<>();
		Map<String,String> updatedStatus=new HashMap<>();
		for (String oId : orderId) {
			Set<String> productId = orderDetails.get(oId).keySet();
			for (String pId : productId) {
               Product existingProduct=this.productRepo.findById(pId).get();
               if(existingProduct.isAvailability())
               { 
                   updatedStatus.put(pId, "dispatched");
               }
               else {
                   updatedStatus.put(pId, "Out of Stock");
               }
			}
			orderUpdatedStatus.put(oId,updatedStatus);
		}
		
		rabbitTemplate.convertAndSend(MQConfig.exchangeName, MQConfig.ORDER_STATUS_ROUTING_KEY, orderUpdatedStatus);
		logger.info("status details : "+orderUpdatedStatus);
		logger.info("Updated order status sent");
	}

}
