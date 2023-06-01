package com.stackroute.searchservice.rabbitmq;

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
public class MQConfig {
	
	public static final String QUEUE="productDetailQueue";
	public static final String ROUTING_KEY="productRoutingKey";
	
	public static final String USER_QUEUE="user_queue";
	public static final String USER_ROUTING_KEY="user_routingKey";
	
	public static final String EXCHANGE="DetailExchange";
	
	
	@Bean
	public TopicExchange exchangeTopic()
	{
		return new TopicExchange(EXCHANGE);
	}
		
	@Bean
	public Queue messageQueue()
	{
		return new Queue(QUEUE);
	}
	
	@Bean 
	public Queue userQueue()
	{
		return new Queue(USER_QUEUE);
	}
	
		
	@Bean
	public Binding messageQueuebinding()
	{
		return BindingBuilder.bind(messageQueue()).to(exchangeTopic()).with(ROUTING_KEY);
	}
	
	@Bean
	public Binding userQueuebinding()
	{
		return BindingBuilder.bind(userQueue()).to(exchangeTopic()).with(USER_ROUTING_KEY);
	}
	
	@Bean
	public MessageConverter messageConverter()
	{
		return new Jackson2JsonMessageConverter();
	}

	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory)
	{
		RabbitTemplate template=new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}
}
