package com.stackroute.adminservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.adminservice.entities.OfferDetails;
import com.stackroute.adminservice.entities.OrderAdminDTO;
import com.stackroute.adminservice.entities.ProductDTO;
import com.stackroute.adminservice.entities.SellerDTO;
import com.stackroute.adminservice.rabbitmq.MQConfig;

@Service
public class AdminService {
	
	@Autowired(required=true)
	private RabbitTemplate template;
	
	private static final String ORDERMESSAGE="Sent OrderDTO to Order Service to delete the order";
	
	private static final String PRODUCTMESSAGE="Sent ProductDTO to Seller Service to delete the product";
	
	private static final String SELLERMESSAGE="Sent SellerDTO to Seller Service to verify and remove the seller";
	
	private static final String NOTIFICATIONMESSAGE="Sent OfferDetails to Notification for offer details";
	
	private static final Logger logger=LoggerFactory.getLogger(AdminService.class);
	
	public String deleteOrderFromOrderRecord(OrderAdminDTO order){
		
		template.convertAndSend(MQConfig.ADMIN_EXCHANGE, MQConfig.ORDER_ROUTING_KEY, order);
		logger.info("OrderDTO sent to order queue: {}",order);
		return ORDERMESSAGE;
	}
	
	public String deleteProductFromProductRecord(ProductDTO product)
	{		
		template.convertAndSend(MQConfig.ADMIN_EXCHANGE, MQConfig.PRODUCT_ROUTING_KEY, product);
		logger.info("ProductDTO sent to order queue: {}",product);
		return PRODUCTMESSAGE;
	}
	
	public String deleteSellerFromSellerService(SellerDTO seller)
	{
		template.convertAndSend(MQConfig.ADMIN_EXCHANGE, MQConfig.SELLER_ROUTING_KEY, seller);
		logger.info("SellerDTO sent to order queue: {}",seller);
		return SELLERMESSAGE;
	}

	public String sendOfferToUsers(OfferDetails offerDetails) {
	
		template.convertAndSend(MQConfig.ADMIN_EXCHANGE, MQConfig.NOTIFICATION_ROUTING_KEY,offerDetails);
		logger.info("Offer Details DTO sent to notification queue: {}",offerDetails);
		return NOTIFICATIONMESSAGE;
	}

}
