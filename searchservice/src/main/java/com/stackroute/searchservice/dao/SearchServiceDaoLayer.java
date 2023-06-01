package com.stackroute.searchservice.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.searchservice.collection.Product;

@SuppressWarnings("unused")
@Repository
public interface SearchServiceDaoLayer extends MongoRepository<Product,String>{
	
	public Product findProductByProductId(String productId);
	
	public List<Product> findProductBySellerId(Integer sellerId);
	
	public List<Product> findBySellerIdAndAvailability(Integer sellerId, boolean availability);
	
	public List<Product> findByProductPriceBetween(Double from, Double to);
	
	public void deleteProductByProductId(String productId);
	
	public List<Product> findProductByProductName(String productName);
	
	 	
}
