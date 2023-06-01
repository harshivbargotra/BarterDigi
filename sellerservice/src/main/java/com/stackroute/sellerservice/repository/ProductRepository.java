package com.stackroute.sellerservice.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.sellerservice.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	List<Product> findBySellerId(Integer sellerId);
}
