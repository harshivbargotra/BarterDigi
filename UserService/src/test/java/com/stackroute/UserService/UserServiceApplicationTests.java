package com.stackroute.UserService;
import com.stackroute.UserService.dto.CartDTO;
import com.stackroute.UserService.dto.ProductDTO;
import com.stackroute.UserService.exception.CartException;
import com.stackroute.UserService.service.*;
import com.stackroute.UserService.repository.*;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class UserServiceApplicationTests {

//	@MockBean
//	protected CartRepository cartRepository;
//	@MockBean
//	protected ProductRepository productRepository;
//
//	@Autowired
//	protected CartService cartService;

//	CartDTO cartDTO ;
//	@BeforeEach
//	void init(){
//		cartService = new CartServiceImpl();
//		 cartDTO = new CartDTO();
//		ProductDTO product=new ProductDTO();
//		Mockito.when(cartRepository.save(cartDTO)).thenReturn(cartDTO);
//		Mockito.when(productRepository.findById("p101")).thenReturn(Optional<ProductDTO> product);
//	}

	@Test
	void addToCartFunctionality() throws CartException {
//		CartDTO cartDTO = cartService.addToCart(101, "63a9b87e5393202d9a321c24", 5);
//		Mockito.when(cartRepository.save(cartDTO)).thenReturn(cartDTO);
//		assertEquals(cartDTO, cartService.addToCart(101,"63a9b87e5393202d9a321c24",5));
	}




}
