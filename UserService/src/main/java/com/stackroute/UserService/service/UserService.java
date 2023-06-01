package com.stackroute.UserService.service;

import com.stackroute.UserService.dto.BuyerDTO;
import com.stackroute.UserService.entity.Address;
import com.stackroute.UserService.exception.UserException;
import com.stackroute.UserService.repository.BuyerRepository;
import com.stackroute.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuyerRepository buyerRepository;

    public BuyerDTO addAddress(Integer buyerId,Address address) throws UserException {
        Optional<BuyerDTO> userOpt = this.buyerRepository.findById(buyerId);
        if(userOpt.isEmpty())
            throw new UserException("userId does not exist!!");
        userOpt.get().setAddress(address);
        return this.buyerRepository.save(userOpt.get());

    }
}
