package com.stackroute.PaymentService.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.PaymentService.dto.OrderPublisher;
import com.stackroute.PaymentService.dto.Payment;
import com.stackroute.PaymentService.exception.PaymentException;
import com.stackroute.PaymentService.service.PaymentService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

@RestController
@RequestMapping("/paymentService")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@PostMapping("payment")
	public Payment initiatePayment(@Valid @RequestBody OrderPublisher orderPublisher) throws PaymentException, AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException, APIException {
		logger.info("Inside initiatePayment");
		return paymentService.initiatePayment(orderPublisher);
	}

	@GetMapping("payment/{userId}")
	public List<Payment> readPaymentById(@PathVariable("userId") String userId) throws PaymentException {

		logger.info("Inside readPaymentById");
		return this.paymentService.readPaymentById(userId);
	}

	@PostMapping("payment/{orderId}")
	public Payment initiateRefund(@Valid @PathVariable String orderId) throws PaymentException, AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException, APIException {
		logger.info("Inside initiateRefund");
        
		return paymentService.initiateRefund(orderId);
	}

}
