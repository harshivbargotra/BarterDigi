package com.stackroute.orderservice.service;

import java.util.Map;



import com.stackroute.orderservice.entity.OrderDTO;
import com.stackroute.orderservice.entity.OrderPublisher;
import com.stackroute.orderservice.entity.Payment;
import com.stackroute.orderservice.entity.Product;
import com.stackroute.orderservice.exception.OrderServiceException;

public interface OrderService {
	
	
	OrderDTO  cancelOrder(String orderId) throws OrderServiceException;
	OrderDTO updateOrderStatus(Payment payment) throws OrderServiceException;
	OrderPublisher placeOrder(OrderPublisher orderpublisher) throws OrderServiceException;
		
}
