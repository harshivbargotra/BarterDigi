package com.stackroute.orderservice.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.orderservice.entity.OrderDTO;
import com.stackroute.orderservice.entity.OrderPublisher;
import com.stackroute.orderservice.entity.Payment;
import com.stackroute.orderservice.exception.OrderServiceException;
import com.stackroute.orderservice.rabbitmq.MQConfig;
import com.stackroute.orderservice.rabbitmq.MessageListener;
import com.stackroute.orderservice.rabbitmq.MessageProducer;
import com.stackroute.orderservice.repository.OrderRepo;
import com.stackroute.orderservice.repository.PaymentRepo;
import com.stackroute.orderservice.service.OrderServiceImpl;

@RestController
@RequestMapping("/orderService")
public class OrderController {
	
	@Autowired
	private MessageListener listner;
	
	@Autowired
	private MessageProducer producer;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private PaymentRepo payRepo;
	
	@Autowired
	private OrderServiceImpl orderImp;
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	MessageProducer msgproducer;
	
	@PostMapping("/order/Cancelorder/id/")
    public String cancelOrder(@PathVariable("id") String id) throws OrderServiceException {

	orderImp.cancelOrder(id);
	return "order cancel";
	}
	
	@PostMapping("/order/placeorder")
	public String placeOrder(@RequestBody OrderPublisher orderPublisher) {
		rabbitTemplate.convertAndSend(MQConfig.ORDER_EXCHANGE,MQConfig.ORDER_ROUTING_KEY ,orderPublisher);
		return "orderplaced";
	}
	@PostMapping("/order/sendforpay")
	public String sendForPay(@RequestBody OrderPublisher orderPublisher) {
		msgproducer.sendOrderForPayment(orderPublisher);
		return "send order for pay";
	}
	@PostMapping("/order/paymentstatus")
	public String paymentStatus(@RequestBody Payment payment) {
		rabbitTemplate.convertAndSend(MQConfig.PAYMENT_STATUS_EXCHANGE,MQConfig.PAYMENT_STATUS_ROUTING_KEY,payment);
		return "got payment status";
	}
	@GetMapping("/order/orderstatus/id/{id}")
	public String getStatus(@PathVariable("id") String id)throws OrderServiceException
	{
		OrderDTO order= orderRepo.findById(id).get();
		return order.getOrderStatus();
	}
	@GetMapping("/order/orderstatus/transaction/id/{id}")
	public String getStatusByTransactionId(@PathVariable("id") String id)throws OrderServiceException
	{
		return orderRepo.findById(payRepo.findByTransactionId(id).getOrderId()).get().getOrderStatus();
	}
}
