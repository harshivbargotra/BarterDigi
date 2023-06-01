package com.stackroute.UserService.repository;

import com.stackroute.UserService.dto.OrderPublisher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderPublisherRepository extends MongoRepository<OrderPublisher,Integer> {
}
