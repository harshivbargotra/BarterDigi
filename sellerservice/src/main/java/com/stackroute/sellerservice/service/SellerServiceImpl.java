package com.stackroute.sellerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.stackroute.sellerservice.entity.Address;
import com.stackroute.sellerservice.entity.BankDetails;
import com.stackroute.sellerservice.entity.Seller;
import com.stackroute.sellerservice.exception.SellerServiceException;
import com.stackroute.sellerservice.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {
	
	

	@Autowired
	SellerRepository sellerRepo;

	@Override
	public Seller addSeller(Seller seller) throws SellerServiceException {
		try {
			return this.sellerRepo.save(seller);
		} catch (Exception e) {
			throw new SellerServiceException("Could not add seller " +e);
		}
	}

	@Override
	public Seller deleteSeller(Integer sellerId) throws SellerServiceException {
		try {
			Optional<Seller> existingOptionalSeller = this.sellerRepo.findById(sellerId);
			if (existingOptionalSeller.isPresent()) {
				Seller existingSeller = existingOptionalSeller.get();
				this.sellerRepo.deleteById(sellerId);
				return existingSeller;
			} else {
				throw new SellerServiceException("This seller is not existing");
			}
		} catch (Exception e) {
			throw new SellerServiceException("Seller could not update");
		}

	}

	@Override
	public Seller getSellerDetail(Integer sellerId) throws SellerServiceException {
		try {
			Optional<Seller> existingOptionalSeller = this.sellerRepo.findById(sellerId);
			if (existingOptionalSeller.isPresent()) {
				Seller existingSeller = existingOptionalSeller.get();
				return existingSeller;
			} else {
				throw new SellerServiceException("This seller is not existing");
			}
		} catch (Exception e) {
			throw new SellerServiceException("Seller could not find");
		}
	}

	@Override
	public List<Seller> getSellerDetails() throws SellerServiceException {
		try {
			List<Seller> existingSellers = this.sellerRepo.findAll();
			if (existingSellers.size() > 0) {
				return existingSellers;
			} else {
				throw new SellerServiceException("No sellers are present");
			}
		} catch (SellerServiceException e) {
			throw new SellerServiceException("No sellers are present");
		}
		
	}

	@Override
	public Seller updateSeller(Seller seller) throws SellerServiceException {
		try {
			return this.sellerRepo.save(seller);
		} catch (Exception e) {
			throw new SellerServiceException("Seller could not update");
		}

	}

	@Override
	public String updateBusinessRange(Integer sellerId, Double updatedRange) throws SellerServiceException {
		try {
			Optional<Seller> existingOptionalSeller = this.sellerRepo.findById(sellerId);
			if (existingOptionalSeller.isPresent()) {
				Seller existingSeller = existingOptionalSeller.get();
				existingSeller.setBusinessRange(updatedRange);
				this.sellerRepo.save(existingSeller);
				return "Business Range is successfully updated";
			} else {
				throw new SellerServiceException("This seller is not existing");
			}

		} catch (Exception e) {
			throw new SellerServiceException("Seller business range could not update");
		}
	}

	@Override
	public Seller completeSellerProfile(Integer sellerId, String gstNumber, String licenseDetails, String panCardDetails,
			String shopName, Address shopLocation, Double businessRange) throws SellerServiceException {
		try {
			Optional<Seller> existingSellerOptional= this.sellerRepo.findById(sellerId);
			if(existingSellerOptional.isPresent())
			{
				Seller existingSeller = existingSellerOptional.get();
				existingSeller.setBusinessRange(businessRange);
				existingSeller.setGstNumber(gstNumber);
				existingSeller.setLicenseDetails(licenseDetails);
				existingSeller.setPanCardDetails(panCardDetails);
				existingSeller.setShopLocation(shopLocation);
				existingSeller.setShopName(shopName);
				return this.sellerRepo.save(existingSeller);		
			}
			else {
				throw new SellerServiceException("Could not complete seller profile");
			}
		}catch(Exception e)
		{
			throw new SellerServiceException("Could not complete seller profile ");
		}
	}

}
