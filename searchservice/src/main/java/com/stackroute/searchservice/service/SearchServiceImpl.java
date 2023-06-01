package com.stackroute.searchservice.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.searchservice.collection.Product;
import com.stackroute.searchservice.collection.ProductConverter;
import com.stackroute.searchservice.collection.ProductDTO;
import com.stackroute.searchservice.dao.SearchServiceDaoLayer;
import com.stackroute.searchservice.exception.SearchServiceCustomException;
@Service
public class SearchServiceImpl {
	
	@Autowired
	private SearchServiceDaoLayer repo;
	
	ModelMapper mapper=new ModelMapper();
	
	@Autowired
	private ProductConverter productConverter;
	
	private static final String DELETION_FAIL_MESSAGE="There is no product for this productId in the database, hence cannot delete the product!";
	
	private static final Logger logger=LoggerFactory.getLogger(SearchServiceImpl.class);
	
	public ProductDTO saveProduct(ProductDTO productDTO) throws SearchServiceCustomException
	{
		Product productCheck=repo.findProductByProductId(productDTO.getProductId());
		if(productCheck!=null)
		{
			logger.error("Throwing exception in saveProductFunctionality");
			throw new SearchServiceCustomException("The product with the productId already exists. Try update if want to for same productId");
		}
		Product product=productConverter.convertDtoToEntity(productDTO);
		product = repo.save(product);
		logger.info("Returning success response-->saveProduct");
		return productConverter.convertEntityToDto(product);
		
	}
	
	public ProductDTO getProductByProductId(String productId) throws SearchServiceCustomException
	{
		Product product=repo.findProductByProductId(productId);
		if(product==null)
		{
			logger.error("Throwing exception-->Search product by productId");
			throw new SearchServiceCustomException("The product was not found based on the given productId");
		}
		logger.info("Returning success response-->getProductByProductId");
		return productConverter.convertEntityToDto(product);
	}
	
	public List<ProductDTO> getProductsBySellerId(Integer sellerId) throws SearchServiceCustomException
	{
		List<Product> products=repo.findProductBySellerId(sellerId);
		if(products.isEmpty())
		{
			logger.error("Throwing exception-->getProductsBySellerId");
			throw new SearchServiceCustomException("There are no products for the given sellerId");
		}
		logger.info("Returning success response-->getProductsBySellerId");
		return Arrays.asList(mapper.map(products, ProductDTO[].class));
	}
	
	public List<ProductDTO> getProductsBySellerIdAndAvailability(Integer sellerId, boolean availability) throws SearchServiceCustomException
	{
		List<Product> products=repo.findBySellerIdAndAvailability(sellerId, availability);
		if(products.isEmpty())
		{
			logger.error("Throwing exception-->getProductsBySellerIdAndAvailability");
			throw new SearchServiceCustomException("There no products for the given sellerId and the given availability");
		}
		logger.info("Returning success response-->getProductsBySellerIdAndAvailability");
		return Arrays.asList(mapper.map(products, ProductDTO[].class));
	}
	
	public List<ProductDTO> findAllProducts() throws SearchServiceCustomException
	{
		List<Product> products=repo.findAll();
		if(products.isEmpty())
		{
			logger.error("Throwing exception-->findAllProducts");
			throw new SearchServiceCustomException("There are no products in the database!");
		}
		logger.info("Returning success response-->findAllProducts");
		return Arrays.asList(mapper.map(products, ProductDTO[].class));
	}
	
	public List<ProductDTO> findByProductPriceRange(Double from,Double to) throws SearchServiceCustomException
	{
		List<Product> products=repo.findByProductPriceBetween(from, to);
		if(products.isEmpty())
		{
			logger.error("Throwing exception-->findByProductPriceRange");
			throw new SearchServiceCustomException("There are no products within the specified productPrice range!");
		}
		logger.info("Returning success response-->findByProductPriceRange");
		return Arrays.asList(mapper.map(products, ProductDTO[].class));
	}
	
	public ProductDTO updateProduct(String productId, ProductDTO product) throws SearchServiceCustomException
	{
		Product productEntity=productConverter.convertDtoToEntity(product);
		Product productFromDb=repo.findProductByProductId(productId);
		if(productFromDb==null)
		{
			logger.error("Throwing exception-->updateProduct");
			throw new SearchServiceCustomException("There is no product for the given productId, hence it would be recommended to add the product first then update it in future.");
		}
		Double falsePrice=0.0;
		Integer falseSellerId=0;
		Date falseDate=new Date();
		boolean falseAvail=false;
		byte[] falseProductPicture=null;
		String falseProfileURL="";
		if(Objects.nonNull(productEntity.getProductName())&&!"".equalsIgnoreCase(productEntity.getProductName()))
		{
			productFromDb.setProductName(productEntity.getProductName());
		}
		if(Objects.nonNull(productEntity.getProductPrice())&&!falsePrice.equals(productEntity.getProductPrice()))
		{
			productFromDb.setProductPrice(productEntity.getProductPrice());
		}
		if(Objects.nonNull(productEntity.getSellerId())&&!falseSellerId.equals(productEntity.getSellerId()))
		{
			productFromDb.setSellerId(productEntity.getSellerId());
		}
		if(Objects.nonNull(productEntity.getProductExpiryDate())&&!falseDate.equals(productEntity.getProductExpiryDate()))
		{
			productFromDb.setProductExpiryDate(productEntity.getProductExpiryDate());
		}
		
		if(Objects.nonNull(productEntity.getProductExpiryDate())&&!falseDate.equals(productEntity.getProductExpiryDate()))
		{
			productFromDb.setProductExpiryDate(productEntity.getProductExpiryDate());
		}
		if(Objects.nonNull(productEntity.isAvailability())&&!falseAvail==(productEntity.isAvailability()))
		{
			productFromDb.setAvailability(productEntity.isAvailability());
		}
		if(Objects.nonNull(productEntity.getProductPicture())&&(falseProductPicture!=(productEntity.getProductPicture())))
		{
			productFromDb.setProductPicture(productEntity.getProductPicture());
		}
		if(Objects.nonNull(productEntity.getProfileURL())&&!falseProfileURL.equalsIgnoreCase(productEntity.getProfileURL()))
		{
			productFromDb.setProductPicture(productEntity.getProductPicture());
		}
		repo.save(productFromDb);
		logger.info("Returning success response-->updateProduct");
		return productConverter.convertEntityToDto(productFromDb);
	}
	
	public String deleteProductById(String productId) throws SearchServiceCustomException
	{
		Product productCheckBeforeDelete=repo.findProductByProductId(productId);
		if(productCheckBeforeDelete==null)
		{
			logger.error(DELETION_FAIL_MESSAGE);
			throw new SearchServiceCustomException(DELETION_FAIL_MESSAGE);
			
		}
		repo.deleteProductByProductId(productId);
		return "Deleted Product based on the productId";
	}
	
	public String deleteProductByProductIdFromUser(String productId) throws SearchServiceCustomException
	{
		Product productCheckBeforeDelete=repo.findProductByProductId(productId);
		if(productCheckBeforeDelete==null)
		{
			logger.error(DELETION_FAIL_MESSAGE);
			throw new SearchServiceCustomException(DELETION_FAIL_MESSAGE);
			
		}
		repo.deleteProductByProductId(productId);
		return "Deleted product based on the productId received";
	}

	public List<ProductDTO> getProductsByName(String productName) throws SearchServiceCustomException {
		List<Product> products=repo.findProductByProductName(productName);
		if(products.isEmpty())
		{
			logger.error("Throwing exception-->Search product by productName");
			throw new SearchServiceCustomException("The products was not found based on the given productName");
		}
		logger.info("Returning success response-->getProductByProductName");
		return Arrays.asList(mapper.map(products, ProductDTO[].class));
	}
	
	
}
