package com.stackroute.UserService.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MQConfig {
    public static final String QUEUE = "UserQueue";
    public static final String EXCHANGE = "UserExchange";
    public static final String ROUTING_KEY = "User_Routing_Key";

    public static final String ORDER_QUEUE = "OrderQueue";
    public static final String ORDER_EXCHANGE = "OrderExchange";
    public static final String ORDER_ROUTING_KEY = "Order_Routing_Key";

    public static final String CANCEL_ORDER_QUEUE = "CancelOrderQueue";
    public static final String CANCEL_ORDER_EXCHANGE = "CancelOrderExchange";
    public static final String CANCEL_ORDER_ROUTING_KEY = "Cancel_Order_Routing_Key";

    public static final String PRODUCT_QUEUE = "user_queue";
    public static final String PRODUCT_EXCHANGE = "DetailExchange";
    public static final String PRODUCT_ROUTING_KEY = "user_routingKey";
    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public Queue Orderqueue(){
        return new Queue(ORDER_QUEUE);
    }

    @Bean
    public TopicExchange Orderexchange(){
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Binding Orderbinding(){
        return BindingBuilder.bind(Orderqueue()).to(Orderexchange()).with(ORDER_ROUTING_KEY);

    }

    @Bean
    public Queue Productqueue(){ return new Queue(PRODUCT_QUEUE);
    }

    @Bean
    public TopicExchange Productexchange(){
        return new TopicExchange(PRODUCT_EXCHANGE);
    }

    @Bean
    public Binding Productbinding(){
        return BindingBuilder.bind(Productqueue()).to(Productexchange()).with(PRODUCT_ROUTING_KEY);

    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue CancerOrderqueue(){ return new Queue(CANCEL_ORDER_QUEUE);
    }

    @Bean
    public TopicExchange CancelOrderexchange(){
        return new TopicExchange(CANCEL_ORDER_EXCHANGE);
    }

    @Bean
    public Binding Cancelbinding(){
        return BindingBuilder.bind(CancerOrderqueue()).to(CancelOrderexchange()).with(CANCEL_ORDER_ROUTING_KEY);

    }
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

}
