package com.stackroute.PaymentService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.stackroute.PaymentService.dto.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String>{

	@Query("{'orderId':?0}")
	public Payment readPaymentById(String orderId);

	
}
