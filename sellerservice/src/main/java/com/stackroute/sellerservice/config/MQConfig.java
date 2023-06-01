package com.stackroute.sellerservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
	
	public static final String ORDER_QUEUE="orderDetailQueue";
	public static final String ORDER_ROUTING_KEY="orderRoutingKey";
	
	public static final String ADMIN_SELLER_QUEUE="seller_queue";
	public static final String ADMIN_SELLER__ROUTING_KEY="seller_routing_key";
	
	public static final String ORDER_STATUS_QUEUE="statusDetailQueue";
	public static final String ORDER_STATUS_ROUTING_KEY="statusRoutingKey";
	
	
	public static final String PRODUCT_QUEUE="productDetailQueue";
	public static final String PRODUCT_ROUTING_KEY="productRoutingKey";
	
	public static final String USER_QUEUE="userDetailQueue";
	public static final String USER_ROUTING_KEY="userRoutingKey";
	
    public static final String PRODUCT_DELETE_QUEUE="product_queue";
	public static final String PRODUCT_DELETE_ROUTING_KEY="product_routing_key";
	
	public static final String exchangeName="DetailExchange";
	
	@Bean
	public TopicExchange topicExcahange()
	{
		return new TopicExchange(exchangeName);
	}
	
	@Bean
	public Queue productQueue()
	{
		return new Queue(PRODUCT_QUEUE);
	}
	
	@Bean
	public Queue productDeleteQueue()
	{
		return new Queue(PRODUCT_DELETE_QUEUE);
	}
	
	
	@Bean
	public Queue orderQueue()
	{
		return new Queue(ORDER_QUEUE);
	}
	
	@Bean
	public Queue userQueue()
	{
		return new Queue(USER_QUEUE);
	}
	
	@Bean
	public Queue sellerQueue()
	{
		return new Queue(ADMIN_SELLER_QUEUE);
	}
	
	@Bean
	public Queue statusQueue()
	{
		return new Queue(ORDER_STATUS_QUEUE);
	}
	

	@Bean
	public Binding productQueueBinding()
	{
		return BindingBuilder.bind(productQueue()).to(topicExcahange()).with(PRODUCT_ROUTING_KEY);
	}
	
	@Bean
	public Binding productDeleteQueueBinding()
	{
		return BindingBuilder.bind(productDeleteQueue()).to(topicExcahange()).with(PRODUCT_DELETE_ROUTING_KEY);
	}
	

	@Bean
	public Binding orderQueueBinding()
	{
		return BindingBuilder.bind(orderQueue()).to(topicExcahange()).with(ORDER_ROUTING_KEY);
	}
	

	@Bean
	public Binding userQueueBinding()
	{
		return BindingBuilder.bind(userQueue()).to(topicExcahange()).with(USER_ROUTING_KEY);
	}
	
	@Bean
	public Binding sellerQueueBinding()
	{
		return BindingBuilder.bind(sellerQueue()).to(topicExcahange()).with(ADMIN_SELLER__ROUTING_KEY);
	}
	
	@Bean
	public Binding statusQueueBinding()
	{
		return BindingBuilder.bind(statusQueue()).to(topicExcahange()).with(ORDER_STATUS_ROUTING_KEY);
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
