package com.stackroute.PaymentService.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.PaymentService.dto.Payment;


@Component
public class MessagePublisher {
	
	@Autowired
	private RabbitTemplate template;
	
	
	public String publishMessageAfterPayment( Payment payment)
	{
				
		template.convertAndSend(MQConfigPaymentPublisher.EXCHANGE, MQConfigPaymentPublisher.ROUTING_KEY, payment);
		return "Message Published to queue!";
		
	}
	
	public String publishMessageForRefund( Payment refundedPayment)
	{
				
		template.convertAndSend(MQConfigRefundPublisher.EXCHANGE, MQConfigRefundPublisher.ROUTING_KEY,refundedPayment);
		return "Message Published to queue!";
		
	}

}
