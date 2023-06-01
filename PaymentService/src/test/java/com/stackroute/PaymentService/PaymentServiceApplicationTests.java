package com.stackroute.PaymentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stackroute.PaymentService.dto.Payment;
import com.stackroute.PaymentService.exception.PaymentException;
import com.stackroute.PaymentService.repository.PaymentRepository;
import com.stackroute.PaymentService.service.PaymentService;

@SpringBootTest
class PaymentServiceApplicationTests {

	@Autowired
	private PaymentRepository repo;

	@Mock
	private PaymentRepository paymentRepository;

	@Mock
	private PaymentService paymentService;

	@BeforeEach
	void setUp() throws Exception {

		Payment payment1 = new Payment("1", "12", "21", "Success", "234",null ,4000, "");
		Payment payment2 = new Payment("2", "13", "21", "Success", "234", null,3000, "");
		Payment payment3 = new Payment("3", "14", "21", "Success", "234", null,2000, "");
		List<Payment> listOfPayments = new ArrayList<>();
		listOfPayments.add(payment3);
		listOfPayments.add(payment2);
		listOfPayments.add(payment1);

		Mockito.when(paymentService.readPaymentById("21")).thenReturn(listOfPayments);

	}

//	@Test
//	@DisplayName("When Valid orderId Returns")
//	void validPaymentId_thenReturnOrderId() {
//		String paymentId = "ch_3MJERuJ3oyvk2Ycb1ZAPQaS8";
//		String orderId = "2";
//
//		assertEquals(orderId, repo.findById(paymentId).get().getOrderId());
//	//	 Mockito.when(paymentRepository.findById(paymentId).get().getOrderId()).thenReturn("2");
//	}
//
//	@Test
//	@DisplayName("When InValid orderId Returns")
//	void InvalidPaymentId_thenReturnOrderId() {
//		String paymentId = "ch_3MJERuJ3oyvk2Ycb1ZAPQaS8";
//		String orderId = "12";
//		assertNotEquals(orderId, repo.findById(paymentId).get().getOrderId());
//	}

	@Test
	void testReadPaymentsByUserIdMock() throws PaymentException {

		// Stubbing
		Mockito.when(paymentRepository.findById("ch_3MH3dLJ3oyvk2Ycb0u42No1Y"))
				.thenReturn(Optional.of(new Payment("1", "12", "21", "Success", "234",null, 4000, "")));

		Payment payment1 = new Payment("1", "12", "21", "Success", "234",null, 4000, "");
		Payment payment2 = new Payment("2", "13", "21", "Success", "234", null,3000, "");
		Payment payment3 = new Payment("3", "14", "21", "Success", "234", null,2000, "");
		List<Payment> listOfPayments = new ArrayList<>();
		listOfPayments.add(payment3);
		listOfPayments.add(payment2);
		listOfPayments.add(payment1);

		// Stubbing
		Mockito.when(paymentRepository.findAll()).thenReturn(listOfPayments);
		Mockito.when(paymentService.readPaymentById("21")).thenReturn(listOfPayments);

	}

}
