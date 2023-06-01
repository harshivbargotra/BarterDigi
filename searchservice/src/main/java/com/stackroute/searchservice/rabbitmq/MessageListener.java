package com.stackroute.searchservice.rabbitmq;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.searchservice.collection.Product;
import com.stackroute.searchservice.collection.ProductDTO;
import com.stackroute.searchservice.dao.SearchServiceDaoLayer;
import com.stackroute.searchservice.exception.SearchServiceCustomException;
import com.stackroute.searchservice.service.SearchServiceImpl;

@Component
public class MessageListener {
	
	@Autowired
	private SearchServiceImpl service;
	
	@Autowired
	private SearchServiceDaoLayer dao;
	
	@Autowired
	private MessageProducer producer;
	
	private static final Logger logger=LoggerFactory.getLogger(MessageListener.class);
		
	
	@RabbitListener(queues=MQConfig.QUEUE)
	public void listener(@Valid ProductDTO product) throws SearchServiceCustomException
	{
		
		logger.info("Product from seller service: "+ product);
		
		Product productCheck=dao.findProductByProductId(product.getProductId());
		logger.info("The product from the database based on productId of received product from listener: "+productCheck);
		
		
		if(productCheck!=null && product.isAvailability()==false)
		{
			logger.info("Notification to user service that the product is not available!");
			String response=producer.publishMessage(product);
			logger.info(response);
			logger.info("The delete method by productId is being called");
			String deletionResponse=service.deleteProductById(product.getProductId());
			logger.info(deletionResponse);
			
		}
				
		else if(productCheck==null)
		{
			logger.info("The save Product method is being called");
			service.saveProduct(product);
		}
		
		else 
		{
			logger.info("The update Product is being called");
			service.updateProduct(product.getProductId(), product);
		}

				
		
	}

		
}
