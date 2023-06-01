package com.stackroute.userregistration.dao;


import com.stackroute.userregistration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String username);
}
