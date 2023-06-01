package com.stackroute.orderservice.service;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.orderservice.entity.NotificationDetail;
import com.stackroute.orderservice.entity.OrderDTO;
import com.stackroute.orderservice.entity.OrderPublisher;
import com.stackroute.orderservice.entity.Payment;
import com.stackroute.orderservice.exception.OrderServiceException;
import com.stackroute.orderservice.rabbitmq.MessageListener;
import com.stackroute.orderservice.rabbitmq.MessageProducer;
import com.stackroute.orderservice.repository.OrderRepo;
import com.stackroute.orderservice.repository.PaymentRepo;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderRepo  orderRepo;
	
	@Autowired
	MessageProducer producer;
	
	@Autowired
	PaymentRepo paymentRepo; 
	
	
	NotificationDetail notify =new NotificationDetail();
	
	public static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
	
	

	
	@Override
	public OrderPublisher placeOrder(OrderPublisher orderpublisher) throws OrderServiceException {
		try {
			producer.sendOrderForPayment(orderpublisher);
		}
		catch (Exception e) {
			throw new OrderServiceException("Could not place order " +e);
		}
		
		return orderpublisher;
	}
	

	@Override
	public OrderDTO updateOrderStatus(Payment payment) throws OrderServiceException {
		try {			
			if (payment.getPaymentStatus().toLowerCase().contains("refunded")) {
				String orderId = payment.getOrderId();
				OrderDTO order = orderRepo.findById(orderId).get();		
				order.setOrderStatus("cancelled");
				notify.setRecipient(order.getUserEmail());
	        	notify.setMsgBody("Your Order has been cancelled"+order.getOrderId());
	        	notify.setSubject("your order:"+order.getOrderId());
	        	notify.setAttachment(".....");
	        	producer.sendMailTouser(notify);
	        	order.setOrderStatus("Cancelled");
				order.setPaymentStatus("Refunded");
	        	paymentRepo.save(payment);
	        	return orderRepo.save(order);
			}
			
			paymentRepo.save(payment);
			OrderDTO order = orderRepo.findById(payment.getOrderId()).get();
			if (order==null) throw new OrderServiceException("404 - Unable to find Order");
			if(payment.getPaymentStatus().equalsIgnoreCase("succeeded"))
			{
				order.setPaymentStatus("paid");
				
				
				// YE KARNA PADEGA
				//Generating Map<sellerId, Map<ProductId, Quantity>>
//				Map<Integer, Map<String, Integer>> sellerPayloadMap = new HashMap<>();
//				
//				//Map<ProductId, quantity>
//				Map<String, Integer> productMap =  order.getPurchasedProduct();
//				
//				//Map<productId, SellerID>
//				Map<String, Integer> sellerMap = order.getProductSellerMap();
//				
//				
//		        Iterator sellerMapItr = sellerMap.entrySet().iterator();
//		        while (sellerMapItr.hasNext()) {
//		        	Map.Entry<String, Integer> productSeller
//	                = (Map.Entry)sellerMapItr.next();	
//		        	String productId = productSeller.getKey();
//		        	Integer sellerId = productSeller.getValue();
//		        	if (!sellerPayloadMap.containsKey(sellerId)) {
//		        		Map<String, Integer> tempMap = new HashMap<>();
//		        		tempMap.put(productId, productMap.get(productId));
//		        		sellerPayloadMap.put(sellerId, tempMap);
//		        	}else {
//		        		sellerPayloadMap.get(sellerId).put(productId, productMap.get(productId));
//		        	}
//		        }
//		        
//		        
//	        	logger.info(sellerPayloadMap.toString());
				
				// Map<orderId, Map<productId, quantity>>
				Map<String, Map<String, Integer>> sellerPayloadMap = new HashMap<>();
				
				//Map<ProductId, quantity>
				Map<String, Integer> productMap =  order.getPurchasedProduct();
				
				sellerPayloadMap.put(order.getOrderId(), productMap);
		        
				
	        	producer.sendOrderToSeller(sellerPayloadMap);
	        	//TODO: send notification to user
	        	
	        	notify.setRecipient(order.getUserEmail());
	        	notify.setMsgBody("Your Order has been placed"+order.getOrderId());
	        	notify.setSubject("your order:"+order.getOrderId());
	        	notify.setAttachment(".....");
	        	producer.sendMailTouser(notify);
				
			}else{
				order.setOrderStatus("rejected");
				order.setPaymentStatus("failed");
			}
			return orderRepo.save(order);
		} catch (Exception e) {
			throw new OrderServiceException(e.getMessage()+"\n\norder status could not update");
		}

	}


	@Override
	public OrderDTO cancelOrder(String orderId) throws OrderServiceException {
		OrderDTO order = orderRepo.findById(orderId).get();
		order.setOrderStatus("cancelled");
		//TODO: Notify Seller
		return orderRepo.save(order);
	}




}




	

	
	

