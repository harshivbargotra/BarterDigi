package com.stackroute.orderservice.rabbitmq;

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
	
	// ============================================================================================================================	
	public static final String ORDER_QUEUE="OrderQueue";
	public static final String ORDER_ROUTING_KEY="Order_Routing_Key";
	public static final String ORDER_EXCHANGE="OrderExchange";
	
	@Bean
	public TopicExchange orderExchange()
    {
		return new TopicExchange(ORDER_EXCHANGE);
	}
    
	@Bean
	public Queue orderQueue()
	{
		return new Queue(ORDER_QUEUE);
	}
	
	@Bean
	public Binding orderQueueBinding()
	{
		return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(ORDER_ROUTING_KEY);
	}
	// ============================================================================================================================

	
	
	
	// ============================================================================================================================	
	public static final String ORDER_PAYMENT_EXCHANGE="payment_exchange"; // For send_payment_queue
	public static final String ORDER_PAYMENT_QUEUE="send_payment_queue";
	public static final String ORDER_PAYMENT_ROUTING_KEY="send_payment_routingKey";
	
	@Bean
	public TopicExchange orderPaymentExchange()
    {
		return new TopicExchange(ORDER_PAYMENT_EXCHANGE);
	}
    
	@Bean
	public Queue orderPaymentQueue()
	{
		return new Queue(ORDER_PAYMENT_QUEUE);
	}
	
	@Bean
	public Binding orderPaymentQueueBinding()
	{
		return BindingBuilder.bind(orderPaymentQueue()).to(orderPaymentExchange()).with(ORDER_PAYMENT_ROUTING_KEY);
	}
	// ============================================================================================================================

	
	

	// ============================================================================================================================
	public static final String PAYMENT_STATUS_EXCHANGE="payment_publisher_exchange";
	public static final String PAYMENT_STATUS_QUEUE="payment_publisher_queue";
	public static final String PAYMENT_STATUS_ROUTING_KEY="payment_publisher_routingKey";
	@Bean
	public TopicExchange paymentStatusExchange()
    {
		return new TopicExchange(PAYMENT_STATUS_EXCHANGE);
	}
    
	@Bean
	public Queue paymentStatusQueue()
	{
		return new Queue(PAYMENT_STATUS_QUEUE);
	}
	
	@Bean
	public Binding paymentStatusQueueBinding()
	{
		return BindingBuilder.bind(paymentStatusQueue()).to(paymentStatusExchange()).with(PAYMENT_STATUS_ROUTING_KEY);
	}
	// ============================================================================================================================

	
	
	
	// ============================================================================================================================
	public static final String SELLER_EXCHANGE="DetailExchange";
	public static final String SELLER_QUEUE="orderDetailQueue";
	public static final String SELLER_ROUTING_KEY="orderRoutingKey";
	public static final String ORDER_STATUS_QUEUE="statusDetailQueue";
	public static final String ORDER_STATUS_ROUTING_KEY="statusRoutingKey";
	@Bean
	public TopicExchange sellerExchange()
    {
		return new TopicExchange(SELLER_EXCHANGE);
	}
    
	@Bean
	public Queue sellerQueue()
	{
		return new Queue(SELLER_QUEUE);
	}
	
	@Bean
	public Binding sellerQueueBinding()
	{
		return BindingBuilder.bind(sellerQueue()).to(sellerExchange()).with(SELLER_ROUTING_KEY);
	}
	@Bean
	public Queue orderStatusQueue()
	{
		return new Queue(ORDER_STATUS_QUEUE);
	}
	
	@Bean
	public Binding orderStatusQueueBinding()
	{
		return BindingBuilder.bind(orderStatusQueue()).to(sellerExchange()).with(ORDER_STATUS_ROUTING_KEY);
	}
	// ============================================================================================================================

	
	// ============================================================================================================================
	public static final String MAIL_QUEUE="notification_queue";
	public static final String MAIL_ROUTING_KEY="NotificationRoutingKey";
	public static final String MAIL_EXCHANGE="notification_exchange";
	@Bean
	public TopicExchange mailExchange()
    {
		return new TopicExchange(MAIL_EXCHANGE);
	}
    
	@Bean
	public Queue mailQueue()
	{
		return new Queue(MAIL_QUEUE);
	}
	
	@Bean
	public Binding mailQueueBinding()
	{
		return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MAIL_ROUTING_KEY);
	}
	// ============================================================================================================================

	
	
	// ============================================================================================================================
	public static final String SELLER_ORDER_CANCEL_QUEUE="order_queue";
	public static final String SELLER_ORDER_CANCEL_ROUTING_KEY="order_routing_key";
	public static final String SELLER_ORDER_CANCEL_EXCHANGE="admin_exchange";
	@Bean
	public TopicExchange sellerOrderCancelExchange()
    {
		return new TopicExchange(SELLER_ORDER_CANCEL_EXCHANGE);
	}
    
	@Bean
	public Queue sellerOrderCancelQueue()
	{
		return new Queue(SELLER_ORDER_CANCEL_QUEUE);
	}
	
	@Bean
	public Binding sellerOrderCancelQueueBinding()
	{
		return BindingBuilder.bind(sellerOrderCancelQueue()).to(sellerOrderCancelExchange()).with(SELLER_ORDER_CANCEL_ROUTING_KEY);
	}
	// ============================================================================================================================

	
	// ============================================================================================================================
	public static final String USER_ORDER_CANCEL_QUEUE="CancelOrderQueue";
	public static final String USER_ORDER_CANCEL_ROUTING_KEY="Cancel_Order_Routing_Key";
	public static final String USER_ORDER_CANCEL_EXCHANGE="CancelOrderExchange";
	@Bean
	public TopicExchange userOrderCancelExchange()
    {
		return new TopicExchange(USER_ORDER_CANCEL_EXCHANGE);
	}
    
	@Bean
	public Queue userOrderCancelQueue()
	{
		return new Queue(USER_ORDER_CANCEL_QUEUE);
	}
	
	@Bean
	public Binding userOrderCancelQueueBinding()
	{
		return BindingBuilder.bind(userOrderCancelQueue()).to(userOrderCancelExchange()).with(USER_ORDER_CANCEL_ROUTING_KEY);
	}
	// ============================================================================================================================

	
	// ============================================================================================================================
	public static final String REFUND_QUEUE="refund_queue";
	public static final String REFUND_ROUTING_KEY="refund_routingKey";
	public static final String REFUND_EXCHANGE="refund_exchange";
	@Bean
	public TopicExchange refundExchange()
    {
		return new TopicExchange(REFUND_EXCHANGE);
	}
    
	@Bean
	public Queue refundQueue()
	{
		return new Queue(REFUND_QUEUE);
	}
	
	@Bean
	public Binding refundQueueBinding()
	{
		return BindingBuilder.bind(refundQueue()).to(refundExchange()).with(REFUND_ROUTING_KEY);
	}
	// ============================================================================================================================


	// ============================================================================================================================
	public static final String REFUND_STATUS_QUEUE = "refund_publisher_queue";
	public static final String REFUND_STATUS_EXCHANGE = "refund_publisher_exchange";
	public static final String REFUND_STATUS_ROUTING_KEY = "refund_publisher_routingKey";
	@Bean
	public TopicExchange refundStatusExchange()
    {
		return new TopicExchange(REFUND_STATUS_EXCHANGE);
	}
    
	@Bean
	public Queue refundStatusQueue()
	{
		return new Queue(REFUND_STATUS_QUEUE);
	}
	
	@Bean
	public Binding refundStatusQueueBinding()
	{
		return BindingBuilder.bind(refundStatusQueue()).to(refundStatusExchange()).with(REFUND_STATUS_ROUTING_KEY);
	}
	// ============================================================================================================================


	

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