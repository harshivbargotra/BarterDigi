package com.stackroute.UserService.repository;

import com.stackroute.UserService.dto.BuyerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyerRepository extends MongoRepository<BuyerDTO,Integer> {
}
