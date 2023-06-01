package com.stackroute.PaymentService.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.PaymentService.dto.OrderPublisher;
import com.stackroute.PaymentService.dto.Payment;
import com.stackroute.PaymentService.exception.PaymentException;
import com.stackroute.PaymentService.service.PaymentService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

@Component
public class MessageListener {

	@Autowired
	PaymentService paymentService;

	@Autowired
	MessagePublisher messagePublisher;

	@RabbitListener(queues = MQConfigPaymentListener.QUEUE)
	public void listenerPaymentRequest(OrderPublisher orderPublisher) throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException, PaymentException {

		System.out.println(orderPublisher);
		Payment payment = paymentService.initiatePayment(orderPublisher);
		messagePublisher.publishMessageAfterPayment(payment);
		System.out.println("message published to order service");
	}
	

	@RabbitListener(queues = MQConfigRefundListener.QUEUE)
	public void listenerRefund(String orderId) throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException, PaymentException {
		if (null != orderId) {
			System.out.println(orderId);
			Payment payment = paymentService.initiateRefund(orderId);
			System.out.println("message published to order service");
		} else {
			throw new PaymentException("no value in order");
		}

	}

}
