package com.stackroute.sellerservice.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stackroute.sellerservice.entity.Address;
import com.stackroute.sellerservice.entity.BankDetails;
import com.stackroute.sellerservice.entity.Seller;
import com.stackroute.sellerservice.exception.SellerServiceException;
import com.stackroute.sellerservice.service.SellerService;

@RestController
@RequestMapping("/sellerService")
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	
	@GetMapping("seller/{id}")
	public Seller getSellerDetail(@PathVariable Integer id) throws SellerServiceException
	{
		return this.sellerService.getSellerDetail(id);
	}

	@GetMapping("sellers")
	public List<Seller> getSellerDetails() throws SellerServiceException
	{
		return this.sellerService.getSellerDetails();
	}
	
	@PostMapping("seller")
	public Seller addSeller(@RequestBody Seller seller ) throws SellerServiceException
	{
		return this.sellerService.addSeller(seller);
	}
	
	@PutMapping("seller/profile/{userId}/{gstNumber}/{licenseDetails}/{panCardDetails}/{shopName}/{businessRange}")
	public Seller completeSellerProfile(@PathVariable Integer userId,@PathVariable  String gstNumber,
			@PathVariable String licenseDetails,@PathVariable String panCardDetails,@PathVariable String shopName,
			@PathVariable Double businessRange ,@RequestBody Address shopLocation) throws SellerServiceException
	{
		System.out.println(shopLocation.toString());
		return this.sellerService.completeSellerProfile(userId, gstNumber, licenseDetails, panCardDetails, shopName, shopLocation, businessRange);
	}
	
	@PutMapping("seller")
	public Seller updateSeller(@RequestBody Seller seller ) throws SellerServiceException
	{
		return this.sellerService.updateSeller(seller);
	}
	
	@PatchMapping("seller/{id}/{range}") 
	public String updateBusinessRange(@PathVariable Integer id,@PathVariable Double range) throws SellerServiceException
	{
		return this.sellerService.updateBusinessRange(id, range);
	}
	
	@DeleteMapping("seller/{id}")
	public Seller deleteSeller(@PathVariable Integer id) throws SellerServiceException
	{
		return this.sellerService.deleteSeller(id);
	}
}
