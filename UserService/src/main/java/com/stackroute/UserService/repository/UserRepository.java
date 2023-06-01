package com.stackroute.UserService.repository;

import com.stackroute.UserService.dto.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDTO,Integer> {
}
