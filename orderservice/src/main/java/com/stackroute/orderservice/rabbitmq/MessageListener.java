package com.stackroute.orderservice.rabbitmq;


import org.slf4j.*;

import java.util.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.orderservice.entity.NotificationDetail;
import com.stackroute.orderservice.entity.OrderDTO;
import com.stackroute.orderservice.entity.OrderPublisher;
import com.stackroute.orderservice.entity.Payment;
import com.stackroute.orderservice.exception.OrderServiceException;
import com.stackroute.orderservice.repository.OrderRepo;
import com.stackroute.orderservice.repository.PaymentRepo;
import com.stackroute.orderservice.service.OrderService;


@Component
public class MessageListener {
	@Autowired
	private OrderService orderService;
	
	@Autowired 
	private MessageProducer messageProducer;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	private NotificationDetail notify = new NotificationDetail();
	
	
	
	public static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
	
	@RabbitListener(queues = MQConfig.ORDER_QUEUE)
	public void orderListener(OrderPublisher orderPublisher) throws OrderServiceException {
		logger.info(orderPublisher.toString());
		orderRepo.save(orderPublisher.getOrderDTO());
		if(orderPublisher.getOrderPublisherId()==null) {
			throw new OrderServiceException("Invalid Order");
		}
		orderService.placeOrder(orderPublisher);
	}
	
	@RabbitListener(queues = MQConfig.PAYMENT_STATUS_QUEUE)
	public void paymentListener(Payment payment)throws OrderServiceException{
		if(payment.getPaymentId()==null) {
			throw new OrderServiceException("Invalid Payment Object");
		}
		logger.info(payment.toString());
        orderService.updateOrderStatus(payment);
	}
	
	@RabbitListener(queues = MQConfig.REFUND_STATUS_QUEUE)
	public void refundListener(Payment payment)throws OrderServiceException{
		if(payment.getPaymentId()==null) {
			throw new OrderServiceException("Invalid Payment Object");
		}
		logger.info(payment.toString());
        orderService.updateOrderStatus(payment);
        
	}
	
	
	@RabbitListener(queues = MQConfig.SELLER_ORDER_CANCEL_QUEUE)
	public void sellerOrderCancelListener(String orderId)throws OrderServiceException{
		if(orderId==null || orderId.equals("")) {
			throw new OrderServiceException("Invalid Order ID");
		}
		logger.info("Canceling Order:"+orderId);
		OrderDTO order = orderRepo.findById(orderId).get();
		order.setOrderStatus("Cancelled");
		orderRepo.save(order);
		messageProducer.sendRefundToPayment(orderId);
	}
	
	@RabbitListener(queues = MQConfig.USER_ORDER_CANCEL_QUEUE)
	public void userOrderCancelListener(String orderId)throws OrderServiceException{
		if(orderId==null || orderId.equals("")) {
			throw new OrderServiceException("Invalid Order ID");
		}
		logger.info("Canceling Order:"+orderId);
		OrderDTO order = orderRepo.findById(orderId).get();
		order.setOrderStatus("Cancelled");
		orderRepo.save(order);
		messageProducer.sendRefundToPayment(orderId);
	}
	
	@RabbitListener(queues = MQConfig.ORDER_STATUS_QUEUE)
	public void orderSellerStatusListner(Map<String, Map<String, String>> orderUpdatedStatus)throws OrderServiceException{
		logger.info("Updated order status from seller: "+orderUpdatedStatus.toString());
		// TODO: take action based on Harsh's reaction.
		Iterator outerItr = orderUpdatedStatus.entrySet().iterator();
		
		Map.Entry mapElement
        = (Map.Entry)outerItr.next();
		String orderId = (String)mapElement.getKey();
		
		Iterator innerItr = ((Map<String, String>)mapElement.getValue()).entrySet().iterator();
		while(innerItr.hasNext()) {
			if (((Map.Entry)innerItr.next()).getValue().equals("Out of Stock")) {
				messageProducer.sendRefundToPayment(orderId);
				return;
			}
		}
		
	}
	
}
