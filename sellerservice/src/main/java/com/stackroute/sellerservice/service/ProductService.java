package com.stackroute.sellerservice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stackroute.sellerservice.entity.Product;
import com.stackroute.sellerservice.exception.SellerServiceException;

public interface ProductService {

	Product addProduct(Product product) throws SellerServiceException;

	Product deleteProduct(String productId) throws SellerServiceException;

	Product getProductDetail(String productId) throws SellerServiceException;

	List<Product> getProductBySellerId(Integer sellerId) throws SellerServiceException;

	List<Product> getProducts() throws SellerServiceException;

	String updateProductAvailability(String productId, boolean status) throws SellerServiceException;

	String updateProductPrice(String productId, Double updatedPrice) throws SellerServiceException;
	
	String updateProductPicture(String productId, MultipartFile picture) throws SellerServiceException;

	Product updateProduct(Product product) throws SellerServiceException;
	
	

}
