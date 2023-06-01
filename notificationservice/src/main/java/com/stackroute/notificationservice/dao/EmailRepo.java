package com.stackroute.notificationservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.stackroute.notificationservice.entity.EmailDetails;

@Repository
public interface EmailRepo extends JpaRepository<EmailDetails, String> {
	
	
	EmailDetails findByEmail(String email);

}
