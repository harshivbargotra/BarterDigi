package com.stackroute.sellerservice.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.stackroute.sellerservice.entity.Address;
import com.stackroute.sellerservice.entity.BankDetails;
import com.stackroute.sellerservice.entity.Product;
import com.stackroute.sellerservice.entity.Seller;
import com.stackroute.sellerservice.exception.SellerServiceException;

public interface SellerService {

	Seller addSeller(Seller seller) throws SellerServiceException;

	Seller deleteSeller(Integer sellerId) throws SellerServiceException;

	Seller getSellerDetail(Integer sellerId) throws SellerServiceException;

	List<Seller> getSellerDetails() throws SellerServiceException;

	Seller updateSeller(Seller seller) throws SellerServiceException;

	String updateBusinessRange(Integer sellerId,Double range) throws SellerServiceException;
	
	public Seller completeSellerProfile(Integer userId, String gstNumber,
			String licenseDetails,String panCardDetails,String shopName,
		 Address shopLocation, Double businessRange) throws SellerServiceException;;

}
