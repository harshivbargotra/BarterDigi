package com.stackroute.PaymentService.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.PaymentService.RabbitMQ.MessagePublisher;
import com.stackroute.PaymentService.dto.ChargeRequest;
import com.stackroute.PaymentService.dto.ChargeRequest.Currency;
import com.stackroute.PaymentService.dto.OrderPublisher;
import com.stackroute.PaymentService.dto.Payment;
import com.stackroute.PaymentService.exception.PaymentException;
import com.stackroute.PaymentService.repository.PaymentRepository;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	StripeService stripeService;
	
	@Autowired
	MessagePublisher messagePublisher;

	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Override
	public Payment initiatePayment(@Valid OrderPublisher orderPublisher) throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException, PaymentException {

		logger.info("Inside serviceIMPL initiatePayment");
		ChargeRequest chargeRequest = new ChargeRequest(orderPublisher.getOrderDTO().getPaymentMethod(),orderPublisher.getOrderDTO().getTotalPrice().intValue(),
				Currency.USD, orderPublisher.getOrderDTO().getUserEmail(), "tok_visa");
		Charge charge = stripeService.charge(chargeRequest);

		if (null != charge) {
			Payment payment = new Payment(charge.getId(),orderPublisher.getOrderDTO().getOrderId(),orderPublisher.getOrderDTO().getUserId(), charge.getStatus(),
					charge.getSource().getId(),orderPublisher.getBuyerDTO().getUserBankDetails(), orderPublisher.getOrderDTO().getTotalPrice().intValue(), charge.getBalanceTransaction());
			paymentRepository.save(payment);
			return payment;
		} else {
			logger.info("Inside serviceIMPL initiatePayment  exception");
			throw new PaymentException("Exception occured while processing Payment ! Please try again");
		}

	}

	@Override
	public List<Payment> readPaymentById(String id) throws PaymentException {
		logger.info("Inside serviceIMPL readPaymentById");
		List<Payment> list = new ArrayList<>();
		List<Payment> list1 = this.paymentRepository.findAll();
		if (list1.isEmpty()) {
			throw new PaymentException("No Payments of user Present");
		}
		for (Payment payment : list1) {

			if (payment.getUserId().equalsIgnoreCase(id)) {
				list.add(payment);
			}
		}
		return list;
	}

	@Override
	public Payment initiateRefund(String orderId) throws PaymentException, AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException, APIException {
		logger.info("Inside initiateRefund service");
		Payment payment = this.paymentRepository.readPaymentById(orderId);
		Map<String, Object> params = new HashMap<>();
		Refund refund;
		if (null != payment && payment.getPaymentStatus().equalsIgnoreCase("succeeded")) {
			String chargeId = payment.getPaymentId();
			params.put("charge", chargeId);
			refund = Refund.create(params);
			payment.setTransactionId(refund.getId());
			payment.setPaymentStatus("Refunded");
			messagePublisher.publishMessageForRefund(payment);
			return this.paymentRepository.save(payment);
		}

		else {
			throw new PaymentException("There is no payment exists with OrderID: " + orderId + "");

		}

	}

	

}
