package com.stackroute.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.orderservice.entity.OrderDTO;



public interface OrderRepo extends MongoRepository<OrderDTO, String> {
	
	
}
