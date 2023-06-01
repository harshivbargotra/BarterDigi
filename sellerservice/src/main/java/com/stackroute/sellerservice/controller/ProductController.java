package com.stackroute.sellerservice.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.sellerservice.config.MQConfig;
import com.stackroute.sellerservice.entity.Product;
import com.stackroute.sellerservice.exception.SellerServiceException;
import com.stackroute.sellerservice.service.ProductService;

@RestController
@RequestMapping("/sellerService")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@GetMapping("products")
	List<Product> getProducts() throws SellerServiceException {
		return this.productService.getProducts();
	}

	@GetMapping("products/{sellerId}")
	List<Product> getProductsBySellerId(@PathVariable Integer sellerId) throws SellerServiceException {
		return this.productService.getProductBySellerId(sellerId);
	}

	@GetMapping("product/{productId}")
	Product getProductsById(@PathVariable String productId) throws SellerServiceException {
		return this.productService.getProductDetail(productId);
	}

	@PostMapping("product")
	Product addProduct(@RequestBody Product product) throws SellerServiceException {
		Product newProduct = this.productService.addProduct(product);
		rabbitTemplate.convertAndSend(MQConfig.exchangeName, MQConfig.PRODUCT_ROUTING_KEY, newProduct);
		return newProduct;
	}

	@DeleteMapping("product/{productId}")
	Product deleteProduct(@PathVariable String productId) throws SellerServiceException {
		Product deletedProduct = this.productService.deleteProduct(productId);
		deletedProduct.setAvailability(false);
		rabbitTemplate.convertAndSend(MQConfig.exchangeName, MQConfig.PRODUCT_ROUTING_KEY, deletedProduct);
		return deletedProduct;
	}

	@PatchMapping("product/price/{productId}/{price}")
	String updateProductPrice(@PathVariable String productId, Double price) throws SellerServiceException {
		Product existingProduct = this.productService.getProductDetail(productId);
		existingProduct.setProductPrice(price);
		rabbitTemplate.convertAndSend(MQConfig.exchangeName, MQConfig.PRODUCT_ROUTING_KEY, existingProduct);
		return this.productService.updateProductPrice(productId, price);
	}

	@PatchMapping("product/status/{productId}/{status}")
	String updateProductStatus(@PathVariable String productId, boolean status) throws SellerServiceException {
		Product existingProduct = this.productService.getProductDetail(productId);
		existingProduct.setAvailability(status);
		rabbitTemplate.convertAndSend(MQConfig.exchangeName, MQConfig.PRODUCT_ROUTING_KEY, existingProduct);
		return this.productService.updateProductAvailability(productId, status);
	}

	@RequestMapping(consumes = "multipart/form-data", method = RequestMethod.PATCH, value = "product/picture/{productId}")
	String updateProductPicture(@PathVariable String productId, @RequestBody MultipartFile productPic)
			throws SellerServiceException {
		if (productPic != null) {
			String returnResponse = this.productService.updateProductPicture(productId, productPic);
			rabbitTemplate.convertAndSend(MQConfig.exchangeName, MQConfig.PRODUCT_ROUTING_KEY,
					this.productService.getProductDetail(productId));
			return returnResponse;
		} else {
			throw new SellerServiceException("Picture could not upload");
		}
	}

	@PutMapping("product")
	Product updateProduct(@RequestBody Product product) throws SellerServiceException {
		Product updatedProduct = this.productService.updateProduct(product);
		rabbitTemplate.convertAndSend(MQConfig.exchangeName, MQConfig.PRODUCT_ROUTING_KEY, updatedProduct);
		return updatedProduct;
	}
}
