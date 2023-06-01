package com.stackroute.UserService.consumer;

import com.stackroute.UserService.config.MQConfig;
import com.stackroute.UserService.dto.ProductDTO;
import com.stackroute.UserService.exception.ProductException;
import com.stackroute.UserService.repository.CartRepository;
import com.stackroute.UserService.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    Logger logger = LoggerFactory.getLogger(ProductConsumer.class);


    @Autowired
    private CartRepository cartRepository;

    @RabbitListener(queues = MQConfig.PRODUCT_QUEUE)
    public void listen(ProductDTO productDTO) throws ProductException {
        logger.info("product from the search service" + productDTO);
        if(productDTO.isAvailability() == false) {
            this.cartRepository.deleteById(productDTO.getProductId());
        }
    }
}
