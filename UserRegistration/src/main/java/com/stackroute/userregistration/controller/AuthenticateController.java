package com.stackroute.userregistration.controller;

import com.stackroute.userregistration.config.JwtUtil;
import com.stackroute.userregistration.config.MQConfig;
import com.stackroute.userregistration.entity.JwtRequest;
import com.stackroute.userregistration.entity.JwtResponse;
import com.stackroute.userregistration.entity.User;
import com.stackroute.userregistration.entity.UserType;
import com.stackroute.userregistration.exception.UserException;
import com.stackroute.userregistration.service.UserDetailsServiceImpl;
import com.stackroute.userregistration.service.UserServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrationService")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RabbitTemplate template;
    @PostMapping("/authenticate")
    public JwtResponse generateToken(@RequestBody JwtRequest jwtRequest) throws UserException {

        try{
            authenticate(jwtRequest.getEmail(),jwtRequest.getPassword());
        } catch(UserException e){
            throw new UserException(e.getMessage());
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        User admin = this.userService.getUserByEmailId(jwtRequest.getEmail());

        return new JwtResponse(token);

    }
    private void authenticate(String username, String password) throws UserException{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e){
            throw new UserException(e.getMessage());
        } catch (BadCredentialsException e){
            throw new UserException(e.getMessage());
        }
    }
}
