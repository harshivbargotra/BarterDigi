package com.stackroute.UserService.repository;

import com.stackroute.UserService.dto.OrderDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderDTO,String> {
}
