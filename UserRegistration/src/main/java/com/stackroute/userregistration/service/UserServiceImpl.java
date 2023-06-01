package com.stackroute.userregistration.service;

import com.stackroute.userregistration.dao.UserRepository;
import com.stackroute.userregistration.entity.User;
import com.stackroute.userregistration.entity.UserType;
import com.stackroute.userregistration.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User registerUserAsSeller(User user) throws UserException {

        Optional<User> optUser = userRepository.findByEmail(user.getUsername());
        if(optUser.isPresent()){
            throw new UserException("Email id already registered. Try with new Email.");
        }
        user.setUserType(UserType.SELLER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return userRepository.findById(user.getId()).get();
    }

    @Override
    public User registerUserAsBuyer(User user) throws UserException {

        Optional<User> optUser = userRepository.findByEmail(user.getUsername());
        if(optUser.isPresent()){
            throw new UserException("Email id already registered. Try with new Email.");
        }
        user.setUserType(UserType.BUYER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return userRepository.findById(user.getId()).get();
    }

    @Override
    public User updateUser(User user) throws UserException {

        Optional<User> optUser = userRepository.findByEmail(user.getUsername());
        if(optUser.isEmpty()){
            throw new UserException("User not found. Please provide correct user details.");
        }
        userRepository.save(user);

        return userRepository.findById(user.getId()).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User deleteUserById(Integer id) throws UserException {
        Optional<User> optUser = userRepository.findById(id);

        if(optUser.isEmpty()){
            throw new UserException("User does not exist");
        }
        User deletedUser = optUser.get();
        userRepository.delete(optUser.get());
        return deletedUser;
    }

    @Override
    public User getUserByEmailId(String email) throws UserException {
        Optional<User> optUser = userRepository.findByEmail(email);

        if(optUser.isEmpty()){
            throw new UserException("User does not exist");
        }
        User user = optUser.get();

        return user;
    }
}
