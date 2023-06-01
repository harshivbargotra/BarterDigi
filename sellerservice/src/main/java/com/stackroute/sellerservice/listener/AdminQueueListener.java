package com.stackroute.sellerservice.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.sellerservice.config.MQConfig;
import com.stackroute.sellerservice.entity.Product;
import com.stackroute.sellerservice.entity.ProductDTO;
import com.stackroute.sellerservice.entity.Seller;
import com.stackroute.sellerservice.entity.SellerDTO;
import com.stackroute.sellerservice.exception.SellerServiceException;
import com.stackroute.sellerservice.service.ProductService;
import com.stackroute.sellerservice.service.SellerService;

@Component
public class AdminQueueListener {
	
   @Autowired
   SellerService sellerService;
   
   @Autowired
   ProductService productService;

	private static Logger logger = LoggerFactory.getLogger(AdminQueueListener.class);

	@RabbitListener(queues = MQConfig.PRODUCT_DELETE_QUEUE)
    public void adminSellerListener(ProductDTO product) throws SellerServiceException {
		logger.info("Admin product deletion request received :");
		Product foundProduct= productService.getProductDetail(product.getProductId());
		if(foundProduct!= null)
		{
			productService.deleteProduct(product.getProductId());
			logger.info("Product is deleted");
		}
		else {
			throw new SellerServiceException("Product not found!!!");
		}
		
    }
	
	@RabbitListener(queues = MQConfig.ADMIN_SELLER_QUEUE)
    public void adminSellerListener(SellerDTO seller) throws SellerServiceException {
		logger.info("Admin seller deletion request received :");
		Seller foundSeller= sellerService.getSellerDetail(seller.getSellerId());
		if(foundSeller!= null)
		{
			sellerService.deleteSeller(seller.getSellerId());
			logger.info("Seller is deleted");
		}
		else {
			throw new SellerServiceException("Seller not found !!!");
		}
    }
}
