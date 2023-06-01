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
public class MQConfigRefundPublisher {
	public static final String QUEUE = "refund_publisher_queue";
	public static final String EXCHANGE = "refund_publisher_exchange";
	public static final String ROUTING_KEY = "refund_publisher_routingKey";

	@Bean
	public Queue queue3() {
		return new Queue(QUEUE);
	}

	@Bean
	public TopicExchange exchange3() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public Binding binding3() {
		return BindingBuilder.bind(queue3()).to(exchange3()).with(ROUTING_KEY);
	}

	@Bean
	public MessageConverter messageConverter3() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template3(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter3());
		return template;
	}
}
