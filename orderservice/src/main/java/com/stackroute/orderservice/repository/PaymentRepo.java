package com.stackroute.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.stackroute.orderservice.entity.Payment;


public interface PaymentRepo extends MongoRepository<Payment, String>{

	public Payment findByTransactionId(String transactionId);
	public Payment findByOrderId(String orderId);
}
