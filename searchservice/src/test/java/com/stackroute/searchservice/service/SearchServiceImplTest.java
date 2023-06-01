package com.stackroute.searchservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.searchservice.collection.Product;
import com.stackroute.searchservice.dao.*;

@SpringBootTest
class SearchServiceImplTest {
	
	@MockBean
	private SearchServiceDaoLayer repo;

	@BeforeEach
	void setUp() throws Exception {
		
		Product product1=new Product("2","Almonds",600.50,new Date(),2,true);
		Product product2=new Product("3","Cashew Nuts",500.50,new Date(),3,false);
		Product product3=new Product("4","Raisins",200.50,new Date(),2,true);
		Mockito.when(repo.findProductByProductId("2")).thenReturn(product1);
		List<Product> listOfProducts=new ArrayList<>();
		listOfProducts.add(product3);
		listOfProducts.add(product2);
		listOfProducts.add(product1);
		
		List<Product> listOfProductsSellerId=new ArrayList<>();
		listOfProductsSellerId.add(product1);
		listOfProductsSellerId.add(product3);
		
		List<Product> listOfProductsFalse=new ArrayList<>();
		listOfProductsFalse.add(product2);
		
		Mockito.when(repo.findProductBySellerId(2)).thenReturn(listOfProductsSellerId);
		Mockito.when(repo.findByProductPriceBetween(200.0, 550.0)).thenReturn(listOfProducts);
		Mockito.when(repo.findBySellerIdAndAvailability(2, true)).thenReturn(listOfProducts);
		Mockito.when(repo.findProductBySellerId(2)).thenReturn(listOfProductsSellerId);
		Mockito.when(repo.findBySellerIdAndAvailability(2, true)).thenReturn(listOfProductsSellerId);
		Mockito.when(repo.findBySellerIdAndAvailability(3, false)).thenReturn(listOfProductsFalse);
		Mockito.when(repo.findByProductPriceBetween(200.00, 650.00)).thenReturn(listOfProductsSellerId);
	}
	
	@Test
	@DisplayName("When Valid Product Id Return Product")
	void validProductId_thenReturnProduct()
	{
		String productId="2";
		Product found=repo.findProductByProductId(productId);
		assertEquals(productId,found.getProductId());
	}
	
	@Test
	@DisplayName("When Invalid Product Id No Product")
	void invalidProductId_NoProduct()
	{
		String falseProductId="13";
		String validProductId="2";
		Product found=repo.findProductByProductId(validProductId);
		assertNotEquals(falseProductId,found.getProductId());
		
	}
	
	@Test
	@DisplayName("When valid sellerId then valid Products")
	void validSellerId_Products()
	{
		Integer validSellerId=2;
		List<Product> found=repo.findProductBySellerId(validSellerId);
		assertNotNull(found);
	}
	
	@Test
	@DisplayName("When invalid sellerId then no Products")
	void invalidSellerId_NoProducts()
	{
		Integer invalidSellerId=4;
		List<Product> found=repo.findProductBySellerId(invalidSellerId);
		assertTrue(found.isEmpty());
	}
	
	@Test
	@DisplayName("When valid sellerId and availability true return available products")
	void validSellerId_availabilityTrue_returnProducts()
	{
		Integer validSellerId=2;
		boolean availability=true;
		List<Product> found=repo.findBySellerIdAndAvailability(validSellerId, availability);
		assertNotNull(found);
	}
	
	@Test
	@DisplayName("When valid sellerId and availability false then return available products")
	void validSellerId_availabilityFalse_returnProducts()
	{
		Integer validSellerId=3;
		boolean availability=false;
		List<Product> found=repo.findBySellerIdAndAvailability(validSellerId, availability);
		assertNotNull(found);
	}
	
	@Test
	@DisplayName("When valid product price range then return products")
	void whenValidProductPriceRange_thenReturnProducts()
	{
		Double from=200.00,to=650.00;
		List<Product> found=repo.findByProductPriceBetween(from, to);
		assertNotNull(found);
		
	}
	
	@Test
	@DisplayName("When invalid product price range then no products")
	void whenInvalidProductPriceRane_thenNoProducts()
	{

		Double from=150.00,to=190.00;
		List<Product> found=repo.findByProductPriceBetween(from, to);
		assertTrue(found.isEmpty());
	}

}
