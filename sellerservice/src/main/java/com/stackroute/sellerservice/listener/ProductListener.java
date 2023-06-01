package com.stackroute.sellerservice.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.stackroute.sellerservice.config.MQConfig;
import com.stackroute.sellerservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductListener {

	private static Logger logger = LoggerFactory.getLogger(ProductListener.class);

	/*
	@RabbitListener(queues = MQConfig.PRODUCT_QUEUE)
	public void listener(Product product) {
		logger.info("Product Details Received  : " + product.getProductId());

	}*/

}
