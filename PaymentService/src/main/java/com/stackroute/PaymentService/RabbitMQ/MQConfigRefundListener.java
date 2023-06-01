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
public class MQConfigRefundListener {
//	public static final String QUEUE="refund_listener_queue";
//	public static final String EXCHANGE="refund_listener_exchange";
//	public static final String ROUTING_KEY="refund_listener_routingKey";

	public static final String QUEUE = "refund_queue";
	public static final String EXCHANGE = "refund_exchange";
	public static final String ROUTING_KEY = "refund_routingKey";

	@Bean
	public Queue queue2() {
		return new Queue(QUEUE);
	}

	@Bean
	public TopicExchange exchange2() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public Binding binding2() {
		return BindingBuilder.bind(queue2()).to(exchange2()).with(ROUTING_KEY);
	}

	@Bean
	public MessageConverter messageConverter2() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template2(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter2());
		return template;
	}
}
