package com.stackroute.adminservice.service;

import java.util.Optional;

import com.stackroute.adminservice.model.User;
import com.stackroute.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;


	
	@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the user form the Database

		Optional<User> optUser = this.userRepository.findByEmail(userName);

		if(optUser.isEmpty()){
			throw  new UsernameNotFoundException("User not found with the given credentials.");
		}
		return optUser.get();

    }
	


}
