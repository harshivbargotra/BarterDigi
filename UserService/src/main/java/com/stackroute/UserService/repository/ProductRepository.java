package com.stackroute.UserService.repository;

import com.stackroute.UserService.dto.ProductDTO;
import com.stackroute.UserService.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDTO,String> {
}
