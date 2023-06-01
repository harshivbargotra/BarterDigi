package com.stackroute.UserService.repository;

import com.stackroute.UserService.dto.CartDTO;
import com.stackroute.UserService.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<CartDTO,String> {
}
