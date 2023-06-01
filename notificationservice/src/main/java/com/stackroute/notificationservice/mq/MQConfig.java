package com.stackroute.notificationservice.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MQConfig {
	
	public static final String notificationexchangeName="notification_exchange";
	
	public static final String NOTIFICATION_QUEUE="notification_queue";
	public static final String ROUTING_KEY="NotificationRoutingKey";
	
	public static final String NOTIFICATION_QUEUE_ATTATCH="notification_queue_attatch";
	public static final String ATTACH_ROUTING_KEY="NotificationRoutingKeyAttatch";
	
	
	public static final String OFFER_QUEUE="offer_queue";
	public static final String OFFER_ROUTING_KEY="offerRoutingKey";
	
	@Bean
	public TopicExchange notificationExcahange()
	{
		return new TopicExchange(notificationexchangeName);
	}
	
	@Bean
	public Queue notificationQueue()
	{
		return new Queue(NOTIFICATION_QUEUE);
	}
	
	@Bean
	public Queue notificationAttatchQueue()
	{
		return new Queue(NOTIFICATION_QUEUE_ATTATCH);
	}
	
	@Bean
	public Queue offerNotiQueue()
	{
		return new Queue(OFFER_QUEUE);
	}


	@Bean
	public Binding offerQueueBinding()
	{
		return BindingBuilder.bind(offerNotiQueue()).to(notificationExcahange()).with(OFFER_ROUTING_KEY);
	}
	@Bean
	public Binding offerAttatchQueueBinding()
	{
		return BindingBuilder.bind(notificationAttatchQueue()).to(notificationExcahange()).with(ATTACH_ROUTING_KEY);
	}
	

	@Bean
	public Binding notificationQueueBinding()
	{
		return BindingBuilder.bind(notificationQueue()).to(notificationExcahange()).with(ROUTING_KEY);
	}
	
	@Bean
	public MessageConverter messageConvertor()
	{
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(this.messageConvertor());
        return  template;
    }
	
	
}
