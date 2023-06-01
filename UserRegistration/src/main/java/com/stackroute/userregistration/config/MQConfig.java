package com.stackroute.userregistration.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String NOTIFICATION_QUEUE="notification_queue";
    public static final String NOTIFICATION_ROUTING_KEY="NotificationRoutingKey";

    public static final String USER_SERVICE_QUEUE ="UserQueue";
    public static final String USER_SERVICE_ROUTING_KEY ="User_Routing_Key";

    public static final String SELLER_SERVICE_QUEUE ="userDetailQueue";
    public static final String SELLER_SERVICE_ROUTING_KEY ="userRoutingKey";

    public static final String ADMIN_SERVICE_QUEUE ="user_reg_queue";
    public static final String ADMIN_SERVICE_ROUTING_KEY ="user_routing_key";
    public static final String EXCHANGE="exchange";

    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue notificationQueue()
    {
        return new Queue(NOTIFICATION_QUEUE);
    }
    @Bean
    public Binding notificationQueueBinding()
    {
        return BindingBuilder.bind(notificationQueue()).to(exchange()).with(NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public Queue userServiceQueue()
    {
        return new Queue(USER_SERVICE_QUEUE);
    }
    @Bean
    public Binding userServiceQueueBinding()
    {
        return BindingBuilder.bind(userServiceQueue()).to(exchange()).with(USER_SERVICE_ROUTING_KEY);
    }

    @Bean
    public Queue sellerServiceQueue()
    {
        return new Queue(SELLER_SERVICE_QUEUE);
    }
    @Bean
    public Binding sellerServiceQueueBinding()
    {
        return BindingBuilder.bind(sellerServiceQueue()).to(exchange()).with(SELLER_SERVICE_ROUTING_KEY);
    }

    @Bean
    public Queue adminQueue()
    {
        return new Queue(ADMIN_SERVICE_QUEUE);
    }
    @Bean
    public Binding adminQueueBinding()
    {
        return BindingBuilder.bind(adminQueue()).to(exchange()).with(ADMIN_SERVICE_ROUTING_KEY);
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
