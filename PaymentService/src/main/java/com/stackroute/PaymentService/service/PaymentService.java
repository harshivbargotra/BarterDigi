package com.stackroute.PaymentService.service;

import java.util.List;

import org.springframework.stereotype.Component;
import com.stackroute.PaymentService.dto.OrderPublisher;
import com.stackroute.PaymentService.dto.Payment;
import com.stackroute.PaymentService.exception.PaymentException;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

@Component
public interface PaymentService {

	Payment initiatePayment(OrderPublisher orderPublisher) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException,PaymentException;
	List <Payment> readPaymentById(String id) throws PaymentException;
	Payment initiateRefund(String orderId)throws PaymentException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException;
}
