package com.stackroute.PaymentService.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfigPaymentListener {
	
//	public static final String QUEUE="payment_listener_queue";
//	public static final String EXCHANGE="payment_listener_exchange";
//	public static final String ROUTING_KEY="payment_listener_routingKey";

	public static final String QUEUE="send_payment_queue";
	public static final String EXCHANGE="payment_exchange";
	public static final String ROUTING_KEY="send_payment_routingKey";

	
	@Bean
	public Queue queue1()
	{
		return new Queue(QUEUE);
	}
	
	@Bean
	public TopicExchange exchange1()
	{
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public Binding binding1()
	{
		return BindingBuilder.bind(queue1()).to(exchange1()).with(ROUTING_KEY);
	}
	
	@Bean
	public MessageConverter messageConverter1()
	{
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template1(ConnectionFactory connectionFactory)
	{
		RabbitTemplate template=new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter1());
		return template;
	}
}
