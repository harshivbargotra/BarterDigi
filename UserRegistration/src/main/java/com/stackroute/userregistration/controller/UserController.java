package com.stackroute.userregistration.controller;

import com.stackroute.userregistration.config.MQConfig;
import com.stackroute.userregistration.entity.NotificationDetails;
import com.stackroute.userregistration.entity.User;
import com.stackroute.userregistration.exception.UserException;
import com.stackroute.userregistration.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/registrationService")
public class UserController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RabbitTemplate template;
    @PostMapping("user/seller")
    User registerUserAsSeller(@Valid @RequestBody User user) throws UserException {
        User registeredSeller = this.userServiceImpl.registerUserAsSeller(user);

        NotificationDetails msg = new NotificationDetails();
        msg.setRecipient(registeredSeller.getUsername());
        msg.setSubject("Registration Successful");
        msg.setMsgBody("Hi "+user.getFirstName()+", your account has been successfully registered as seller account at BarterDigi.");

        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.NOTIFICATION_ROUTING_KEY,msg);
        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.SELLER_SERVICE_ROUTING_KEY,registeredSeller);
       return registeredSeller;
    }

    @PostMapping("user/buyer")
    User registerUserAsBuyer(@Valid @RequestBody User user) throws UserException {
        User registeredBuyer = this.userServiceImpl.registerUserAsBuyer(user);

        NotificationDetails msg = new NotificationDetails();
        msg.setRecipient(registeredBuyer.getUsername());
        msg.setSubject("Registration Successful");
        msg.setMsgBody("Hi "+user.getFirstName()+", your account has been successfully registered as buyer account at BarterDigi.");

        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.NOTIFICATION_ROUTING_KEY,msg);
        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.USER_SERVICE_ROUTING_KEY,registeredBuyer);
        return registeredBuyer;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("users")
    List<User> getAllUser(){
       return this.userServiceImpl.getAllUsers();
    }


    @DeleteMapping("user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    User deleteUserById(@PathVariable Integer id) throws UserException {
       return this.userServiceImpl.deleteUserById(id);
    }


}
