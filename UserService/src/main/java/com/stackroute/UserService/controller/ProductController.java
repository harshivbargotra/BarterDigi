package com.stackroute.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.UserService.dto.ProductDTO;
import com.stackroute.UserService.exception.ProductException;
import com.stackroute.UserService.repository.ProductRepository;

@RestController
@RequestMapping("/userService")
public class ProductController {
	
	@Autowired
	ProductRepository productRepo;
	
	@PostMapping("product")
	ProductDTO addProduct(@RequestBody ProductDTO product) throws ProductException
	{
		if(product !=null)
		{
			return this.productRepo.save(product);
		}
		else {
			throw  new ProductException("product is null");
		}
	}
	
	@GetMapping("products")
	List<ProductDTO>  getProducts()
	{
		return this.productRepo.findAll();
				
	}

}
