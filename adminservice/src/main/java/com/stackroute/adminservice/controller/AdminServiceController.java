package com.stackroute.adminservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.adminservice.entities.OfferDetails;
import com.stackroute.adminservice.entities.OrderAdminDTO;
import com.stackroute.adminservice.entities.ProductDTO;
import com.stackroute.adminservice.entities.SellerDTO;
import com.stackroute.adminservice.exception.AdminServiceCustomException;
import com.stackroute.adminservice.model.JWTRequest;
import com.stackroute.adminservice.model.JWTResponse;
import com.stackroute.adminservice.service.AdminService;
import com.stackroute.adminservice.service.UserService;
import com.stackroute.adminservice.utility.JWTUtility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/adminService")
@Api(tags="adminService")
public class AdminServiceController {
	
	 @Autowired
	 private JWTUtility jwtUtility;
	
	@Autowired(required=true)
	private AdminService serviceLayer;
	
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 
	 @Autowired
	 private UserService userService;
	 

	
	@DeleteMapping("/order/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ApiOperation(value="This method is used to send orderDTO to order service to delete the order")
	public String deleteOrderFromOrderService(@Valid @RequestBody OrderAdminDTO order)
	{
		return serviceLayer.deleteOrderFromOrderRecord(order);
	}
	
	
	@DeleteMapping("/product/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ApiOperation(value="This method is used to send productDTO to seller service to delete the product")
	public String deleteProductFromSellerService(@Valid @RequestBody ProductDTO product)
	{
		return serviceLayer.deleteProductFromProductRecord(product);
	}
	
	
	@DeleteMapping("/seller/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ApiOperation(value="This method is used to send sellerDTO to seller service to delete the seller")
	public String sellerDeletionFromSellerService(@Valid @RequestBody SellerDTO seller)
	{
		return serviceLayer.deleteSellerFromSellerService(seller);
	}
	
	
	@PatchMapping("/offer/mail")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ApiOperation(value="This method is used to send offer details DTO to notification service")
	public String sendOfferMailToUsers(@Valid @RequestBody OfferDetails offerDetails)
	{
		return serviceLayer.sendOfferToUsers(offerDetails);
	}
	
	
	@GetMapping("welcome-admin")
	@ApiOperation(value="This method is to welcome admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String welcomeAdmin()
	{
		return "Welcome to Admin Service!";
	}
	
	
	
	

}
