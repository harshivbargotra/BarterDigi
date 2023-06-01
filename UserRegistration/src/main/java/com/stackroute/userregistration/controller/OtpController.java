package com.stackroute.userregistration.controller;

import com.stackroute.userregistration.config.MQConfig;
import com.stackroute.userregistration.entity.JwtRequest;
import com.stackroute.userregistration.entity.NotificationDetails;
import com.stackroute.userregistration.entity.User;
import com.stackroute.userregistration.service.OtpService;
import com.stackroute.userregistration.service.UserDetailsServiceImpl;
import com.stackroute.userregistration.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrationService")
public class OtpController {
    @Autowired
    private OtpService otpService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("forgotpassword/{email}")
    public Integer generateOtp(@PathVariable String email) throws UsernameNotFoundException {

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        User user = (User) userDetails;
        Integer otp = otpService.generateOTP(email);
        NotificationDetails msg = new NotificationDetails();
        msg.setRecipient(email);
        msg.setSubject("One Time Password");
        msg.setMsgBody("Hi "+user.getFirstName()+", your otp for resetting password is: "+otp);

        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.NOTIFICATION_ROUTING_KEY,msg);
        return otp;
    }

    @PostMapping("resetpassword/{otpNumber}")
    public User validateOtp(@PathVariable Integer otpNumber, @RequestBody JwtRequest jwtRequest) throws Exception{

            Integer storedOtp = otpService.getOtp(jwtRequest.getEmail());
            User user = (User) userDetailsService.loadUserByUsername(jwtRequest.getEmail());
            if(otpNumber.equals(storedOtp)){

                otpService.clearOTP(jwtRequest.getEmail());

                user.setPassword(passwordEncoder.encode(jwtRequest.getPassword()));

                NotificationDetails msg = new NotificationDetails();
                msg.setRecipient(jwtRequest.getEmail());
                msg.setSubject("Password Reset");
                msg.setMsgBody("Hi "+user.getFirstName()+", your password has been changed successfully.");

                template.convertAndSend(MQConfig.EXCHANGE,MQConfig.NOTIFICATION_ROUTING_KEY,msg);

            }
            else{
                throw new Exception("Invalid Otp.");
            }


        return userService.updateUser(user);
    }
}
