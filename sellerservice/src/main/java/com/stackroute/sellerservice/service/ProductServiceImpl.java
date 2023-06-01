package com.stackroute.sellerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.sellerservice.entity.Product;
import com.stackroute.sellerservice.exception.SellerServiceException;
import com.stackroute.sellerservice.repository.ProductRepository;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	@Override
	public Product addProduct(Product product) throws SellerServiceException {
		try {
			if (product.getProfileURL() != null) {
				String profileURL = product.getProfileURL();
				product.setProductPicture(this.imageToByteArray(profileURL));

				long availableProducts = this.productRepo.count();
				long sellerId = product.getSellerId();
				product.setProductId("P" + sellerId + "" + availableProducts);

				return this.productRepo.save(product);
			} else {
				long availableProducts = this.productRepo.count();
				long sellerId = product.getSellerId();
				product.setProductId("P" + sellerId + "" + availableProducts);

				return this.productRepo.save(product);
			}
		} catch (Exception e) {
			throw new SellerServiceException("Product could not be added. Something went wrong !!!");
		}
	}

	@Override
	public Product deleteProduct(String productId) throws SellerServiceException {
		try {
			Optional<Product> existedProduct = this.productRepo.findById(productId);
			if (existedProduct.isPresent()) {
				Product currentproduct = existedProduct.get();
				this.productRepo.deleteById(productId);
				return currentproduct;
			} else {
				throw new SellerServiceException("Product is not existing");
			}
		} catch (Exception e) {
			throw new SellerServiceException("Product could not be delete. Something went wrong !!!");
		}
	}

	@Override
	public Product getProductDetail(String productId) throws SellerServiceException {
		try {
			Optional<Product> existedProduct = this.productRepo.findById(productId);
			if (existedProduct.isPresent()) {
				Product currentproduct = existedProduct.get();
				return currentproduct;
			} else {
				throw new SellerServiceException("Product is not existing");
			}
		} catch (Exception e) {
			throw new SellerServiceException("Product could not be found. Something went wrong !!!");
		}
	}

	@Override
	public List<Product> getProducts() throws SellerServiceException {
		try {
			List<Product> productList = this.productRepo.findAll();
			if (productList.size() <= 0) {
				throw new SellerServiceException("No products are present");
			}
			return productList;
		} catch (Exception e) {
			throw new SellerServiceException("Product List could not be found. Something went wrong !!!");
		}
	}

	@Override
	public String updateProductAvailability(String productId, boolean status) throws SellerServiceException {
		try {
			Product currentProduct = this.productRepo.findById(productId).get();
			if (this.productRepo.findById(productId).isPresent()) {
				currentProduct.setAvailability(status);
				this.productRepo.save(currentProduct);
				return "Successfully Updated";
			} else {
				throw new SellerServiceException("Product is not existing");
			}
		} catch (Exception e) {
			throw new SellerServiceException("Product could not found!!!");
		}
	}

	@Override
	public String updateProductPrice(String productId, Double updatedPrice) throws SellerServiceException {
		try {
			if (this.productRepo.findById(productId).isPresent()) {
				Product currentProduct = this.productRepo.findById(productId).get();
				currentProduct.setProductPrice(updatedPrice);
				this.productRepo.save(currentProduct);
				return "Successfully Updated";
			} else {
				throw new SellerServiceException("Product is not existing");
			}
		} catch (Exception e) {
			throw new SellerServiceException("Product could not found !!!");
		}
	}

	@Override
	public Product updateProduct(Product product) throws SellerServiceException {

		try {
			return this.productRepo.save(product);
		} catch (Exception e) {
			throw new SellerServiceException("Product could not be update. Something went wrong !!!");
		}

	}

	@Override
	public List<Product> getProductBySellerId(Integer sellerId) throws SellerServiceException {
		try {
			List<Product> productsList = this.productRepo.findBySellerId(sellerId);
			if (productsList.size() > 0)
				return productsList;
			else
				throw new SellerServiceException("Could not found any item for this seller");

		} catch (Exception e) {
			throw new SellerServiceException("Could not found any item for this seller");
		}
	}

	public byte[] imageToByteArray(String pictureURL) throws IOException {
		BufferedImage bImage = ImageIO.read(new File(pictureURL));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos);
		byte[] data = bos.toByteArray();
		return data;
	}

	public byte[] fileToByteArray(MultipartFile picture) throws IOException {
		byte[] byteArr = picture.getBytes();
		return byteArr;
	}

	@Override
	public String updateProductPicture(String productId, MultipartFile picture) throws SellerServiceException {
		try {
			if (this.productRepo.findById(productId).isPresent()) {
				Product currentProduct = this.productRepo.findById(productId).get();
				currentProduct.setProductPicture(this.fileToByteArray(picture));
				this.productRepo.save(currentProduct);
				return "Product picture is successfully updated";
			} else {
				throw new SellerServiceException("Product is not existing");
			}
		} catch (Exception e) {
			throw new SellerServiceException("Product could not found !!!");
		}
	}

}
