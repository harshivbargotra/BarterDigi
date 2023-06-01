package com.stackroute.searchservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.searchservice.collection.ProductConverter;
import com.stackroute.searchservice.collection.ProductDTO;
import com.stackroute.searchservice.exception.SearchServiceCustomException;
import com.stackroute.searchservice.service.SearchServiceImpl;

@RestController
@RequestMapping("/searchService")
public class SearchServiceController {
	
	@SuppressWarnings("unused")
	@Autowired
	private ProductConverter productConverter;
	
	@Autowired
	private SearchServiceImpl service;
	
	private static final Logger logger=LoggerFactory.getLogger(SearchServiceController.class);
	
	@PostMapping("/product-save")
	public ProductDTO saveProductToDataBase(@Valid @RequestBody ProductDTO product) throws SearchServiceCustomException
	{
		logger.info("Inside saveProductToDataBase");
		return service.saveProduct(product);
	}
	
	@GetMapping("/product/{productId}")
	public ProductDTO getProductByProductId(@PathVariable String productId) throws SearchServiceCustomException
	{
		logger.info("Inside getProductByProductId");
		return service.getProductByProductId(productId);
	}
	
	@GetMapping("/product-sellerId/{sellerId}")
	public List<ProductDTO> getProductsBySellerId(@PathVariable Integer sellerId) throws SearchServiceCustomException
	{
		logger.info("Inside getProductsBySellerId");
		return service.getProductsBySellerId(sellerId);
	}
	
	@GetMapping("/product-sellerId-availability")
	public List<ProductDTO> getProductsBySellerIdAndAvailability(@RequestParam("sellerId") Integer sellerId, @RequestParam("availability") boolean availability) throws SearchServiceCustomException
	{
		logger.info("Inside getProductsBySellerIdAndAvailability");
		return service.getProductsBySellerIdAndAvailability(sellerId, availability);
	}
	
	@GetMapping("/products")
	public List<ProductDTO> getAllProducts() throws SearchServiceCustomException
	{
		logger.info("Inside getAllProducts");
		return service.findAllProducts();
	}
	
	@GetMapping("/products-within-price-range")
	public List<ProductDTO> getProductsWithinPriceRange(@RequestParam("from")Double from, @RequestParam("to")Double to) throws SearchServiceCustomException
	{
		logger.info("Inside getProductsWithinPriceRange");
		return service.findByProductPriceRange(from, to);
	}
	
	@PutMapping("/product-update")
	public ProductDTO updateProduct(@RequestParam("productId")String productId,@Valid @RequestBody ProductDTO product) throws SearchServiceCustomException
	{
		logger.info("Inside updateProduct");
		return service.updateProduct(productId, product);
	}
	
	@DeleteMapping("/product-delete/{productId}")
	public String deleteProductById(@RequestParam("productId")String productId) throws SearchServiceCustomException
	{
		logger.info("Inside deleting the product by productId");
		return service.deleteProductByProductIdFromUser(productId);
		
	}
	
	@GetMapping("/product/byProductName")
	public List<ProductDTO> getProductByName(@RequestParam("productName")String productName) throws SearchServiceCustomException
	{
		logger.info("Inside the get product by name");
		return service.getProductsByName(productName);
	}

}
