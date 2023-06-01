package com.stackroute.userregistration.service;

import com.stackroute.userregistration.entity.User;
import com.stackroute.userregistration.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User registerUserAsSeller(User user) throws UserException;
    User registerUserAsBuyer(User user) throws UserException;

    User updateUser(User user) throws UserException;
    List<User> getAllUsers();

    User deleteUserById(Integer id) throws UserException;
    User getUserByEmailId(String email) throws UserException;
}
