package com.stackroute.adminservice.rabbitmq;

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
	
	
	public static final String ADMIN_EXCHANGE="admin_exchange";
	
	
	public static final String ORDER_QUEUE="order_queue";
	
	
	public static final String ORDER_ROUTING_KEY="order_routing_key";
	
	
	public static final String PRODUCT_QUEUE="product_queue";
	
	
	public static final String PRODUCT_ROUTING_KEY="product_routing_key";
	
	public static final String SELLER_QUEUE="seller_queue";
	
	public static final String SELLER_ROUTING_KEY="seller_routing_key";
	
	public static final String NOTIFICATION_QUEUE="notification_queue";
	
	public static final String NOTIFICATION_ROUTING_KEY="notification_routing_key";
	
	public static final String USER_REG_QUEUE="user_reg_queue";
	
	public static final String USER_ROUTING_KEY="user_routing_key";
	
	
	@Bean
	public TopicExchange exchangeTopic()
	{
		return new TopicExchange(ADMIN_EXCHANGE);
	}
	
	@Bean
	public Queue userRegQueue()
	{
		return new Queue(USER_REG_QUEUE);
	}
	
	@Bean
	public Queue orderQueue()
	{
		return new Queue(ORDER_QUEUE);
	}
	
	@Bean
	public Queue productQueue()
	{
		return new Queue(PRODUCT_QUEUE);
	}
	
	@Bean
	public Queue sellerQueue()
	{
		return new Queue(SELLER_QUEUE);
	}
	
	@Bean
	public Queue notificationQueue()
	{
		return new Queue(NOTIFICATION_QUEUE);
	}
	
	@Bean
	public Binding userRegQueuebinding()
	{
		return BindingBuilder.bind(userRegQueue()).to(exchangeTopic()).with(USER_ROUTING_KEY);
	}
	
	@Bean
	public Binding orderQueuebinding()
	{
		return BindingBuilder.bind(orderQueue()).to(exchangeTopic()).with(ORDER_ROUTING_KEY);
	}
	
	@Bean
	public Binding productQueuebinding()
	{
		return BindingBuilder.bind(productQueue()).to(exchangeTopic()).with(PRODUCT_ROUTING_KEY);
	}
	
	@Bean
	public Binding sellerQueueBinding()
	{
		return BindingBuilder.bind(sellerQueue()).to(exchangeTopic()).with(SELLER_ROUTING_KEY);
	}
	
	@Bean
	public Binding notificationQueueBinding()
	{
		return BindingBuilder.bind(notificationQueue()).to(exchangeTopic()).with(NOTIFICATION_ROUTING_KEY);
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
