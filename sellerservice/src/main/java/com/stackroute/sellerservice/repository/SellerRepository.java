package com.stackroute.sellerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.sellerservice.entity.Seller;

@Repository
public interface SellerRepository extends MongoRepository<Seller, Integer> {
}
