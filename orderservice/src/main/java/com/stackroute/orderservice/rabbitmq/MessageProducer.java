package com.stackroute.orderservice.rabbitmq;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stackroute.orderservice.entity.NotificationDetail;
import com.stackroute.orderservice.entity.OrderDTO;
import com.stackroute.orderservice.entity.OrderPublisher;

@Service
public class MessageProducer {
	
    

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    


    public void sendOrderForPayment(OrderPublisher orderpublisher){
        LOGGER.info(String.format("order sent -> %s", orderpublisher.toString()));
        rabbitTemplate.convertAndSend(MQConfig.ORDER_PAYMENT_EXCHANGE, MQConfig.ORDER_PAYMENT_ROUTING_KEY, orderpublisher);
    }
    public void sendRefundToPayment(String orderid){
        LOGGER.info(String.format("order sent -> %s", orderid.toString()));
        rabbitTemplate.convertAndSend(MQConfig.REFUND_EXCHANGE, MQConfig.REFUND_ROUTING_KEY, orderid);
    }
    
    //Map<sellerId, Map<productId, quantity>>
    public void sendOrderToSeller(Map<String, Map<String, Integer>> sellerMap){
        LOGGER.info(String.format("order sent -> %s", sellerMap.toString()));
        rabbitTemplate.convertAndSend(MQConfig.SELLER_EXCHANGE, MQConfig.SELLER_ROUTING_KEY, sellerMap);
    }
    public void sendMailTouser(NotificationDetail notify) {
    	LOGGER.info(String.format("email sent -> %s", notify.toString()));
        rabbitTemplate.convertAndSend(MQConfig.MAIL_EXCHANGE, MQConfig.MAIL_ROUTING_KEY, notify);
    }
    
    
    
}
