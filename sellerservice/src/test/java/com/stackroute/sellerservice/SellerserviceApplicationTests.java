package com.stackroute.sellerservice;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.sellerservice.entity.Product;
import com.stackroute.sellerservice.exception.SellerServiceException;
import com.stackroute.sellerservice.repository.ProductRepository;
import com.stackroute.sellerservice.service.ProductServiceImpl;

@SpringBootTest
class SellerserviceApplicationTests {

	/*@MockBean
	ProductRepository productRepo;
	
	@Mock
	Product mockProduct;
	
	@InjectMocks
	protected ProductServiceImpl serviceObject;
	
	Product newProduct;
	
	@BeforeEach
	void init()
	{
		serviceObject = new ProductServiceImpl();
		newProduct = new Product();
	}*/
	
}
