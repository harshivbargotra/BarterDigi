package com.stackroute.UserService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stackroute.UserService.dto.BuyerDTO;
import com.stackroute.UserService.dto.CartDTO;
import com.stackroute.UserService.dto.ProductDTO;
import com.stackroute.UserService.exception.BuyerException;
import com.stackroute.UserService.exception.CartException;
import com.stackroute.UserService.exception.ProductException;
import com.stackroute.UserService.repository.BuyerRepository;
import com.stackroute.UserService.repository.CartRepository;
import com.stackroute.UserService.repository.ProductRepository;
import com.stackroute.UserService.repository.UserRepository;

import springfox.documentation.spring.web.json.Json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;



    @Override
	public BuyerDTO addBuyer(BuyerDTO buyerDTO) throws BuyerException {
		try {
			return this.buyerRepository.save(buyerDTO);
		} catch (Exception e) {
			throw new BuyerException("Could not add buyer " +e);
		}
	}
    
    @Override
	public BuyerDTO completeProfile(BuyerDTO buyerDTO) throws BuyerException {
		try {
			Optional<BuyerDTO> existingBuyerOptional= this.buyerRepository.findById(buyerDTO.getBuyerId());
			if(existingBuyerOptional.isPresent())
			{
				BuyerDTO existingBuyer = existingBuyerOptional.get();
				existingBuyer.setAddress(buyerDTO.getAddress());
				existingBuyer.setUserBankDetails(buyerDTO.getUserBankDetails());
				existingBuyer.setCartDTO(new CartDTO(existingBuyer.getBuyerId().toString()));
				return this.buyerRepository.save(existingBuyer);		
			}
			else {
				throw new BuyerException("Could not complete buyer profile");
			}
		}catch(Exception e)
		{
			throw new BuyerException("Could not complete buyer profile ");
		}
	}

    @Override
    public List<CartDTO> getProductFromCart()  {
        List<BuyerDTO> buyers= this.buyerRepository.findAll();
        List<CartDTO> carts = new ArrayList<>();
        for(BuyerDTO u : buyers) {
        	carts.add(u.getCartDTO());
        }
        return carts;
    }

    public List<ProductDTO> getAllProduct(){
        List<ProductDTO> list = this.productRepository.findAll();
        return list;
    }


    @Override
    public CartDTO addToCart(Integer buyerId,String productId, Integer quantity) throws CartException {
        try {
        System.out.print("Details : "+buyerId+" "+productId+" "+quantity);
    	Optional<ProductDTO> productOpt = this.productRepository.findById(productId);
        Optional<BuyerDTO > buyerDTO = this.buyerRepository.findById(buyerId);
       
        if(buyerDTO.isPresent() && productOpt.isPresent())
        {
	        CartDTO existingCart = buyerDTO.get().getCartDTO();
	        existingCart.setCartId(String.valueOf(buyerId));
	        existingCart.getProducts().put(productId,quantity);
	        existingCart.setTotalQuantity(existingCart.getTotalQuantity()+quantity);
	        existingCart.setTotalAmount((quantity*productOpt.get().getProductPrice())+ existingCart.getTotalAmount());
	        buyerDTO.get().setCartDTO(existingCart);
	        BuyerDTO updatedBuyer = this.buyerRepository.save(buyerDTO.get());
	        return updatedBuyer.getCartDTO();
        }
        else {
        	throw new CartException("Product could not be added");
        }}
        catch(Exception e)
        {
        	throw new CartException("Product could not be added "+e);
        }
    }

    @Override
    public BuyerDTO removefromCart(Integer buyerId,String productId,Integer quantity) throws CartException, ProductException {
        Optional<BuyerDTO> removeCartOpt = this.buyerRepository.findById(buyerId);
        if(removeCartOpt.isEmpty())
            throw new CartException(buyerId + "does not exist !! Try Again.");
        BuyerDTO buyerDTO = removeCartOpt.get();
        CartDTO existingCart = buyerDTO.getCartDTO();
        Map<String, Integer> existingProducts = existingCart.getProducts();
        if(existingProducts.containsKey(productId)){
            if(existingProducts.get(productId)-quantity < 0){
                existingCart.setTotalAmount(existingCart.getTotalAmount()-(this.productRepository.findById(productId).get().getProductPrice()*(existingProducts.get(productId))));
                existingCart.setTotalQuantity(existingCart.getTotalQuantity()-existingProducts.get(productId));
                existingProducts.remove(productId);

            }else{
                existingCart.setTotalAmount(existingCart.getTotalAmount()-(this.productRepository.findById(productId).get().getProductPrice()*quantity));
                existingCart.setTotalQuantity(existingCart.getTotalQuantity()-quantity);
                existingProducts.put(productId,existingProducts.get(productId)-quantity);
            }
        }
        existingCart.setProducts(existingProducts);
        buyerDTO.setCartDTO(existingCart);

        return this.buyerRepository.save(buyerDTO);
    }

    @Override
    public BuyerDTO resetCart(Integer buyerId)  {
        CartDTO cart = new CartDTO();
        cart.setCartId(String.valueOf(buyerId));
        BuyerDTO buyerDTO = this.buyerRepository.findById(buyerId).get();
        buyerDTO.setCartDTO(cart);
        return this.buyerRepository.save(buyerDTO);
    }

    @Override
    public void deleteAll() throws ProductException {
        this.productRepository.deleteAll();
    }
    
    @Override
    public Object getProductByName(String productName) throws ProductException, JsonMappingException, JsonProcessingException {
    	Gson obj=new Gson();
    	HttpHeaders header = new HttpHeaders();
    	header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	HttpEntity<String> entity = new HttpEntity<String>(header);
        Object product = restTemplate.exchange("http://localhost:9988/searchService/product/byProductName?productName= " + productName, HttpMethod.GET,entity,Object.class).getBody();
        String responseString=obj.toJson(product);
		System.out.println(responseString);
		
		ObjectMapper mapper=new ObjectMapper();
		if(product != null) {		
			JSONObject prodJson= new JSONObject(responseString);
			String prod= prodJson.toString();
			ProductDTO productToSave=mapper.readValue(prod, ProductDTO.class);
			this.productRepository.save(productToSave);		
		}else {
			throw new ProductException("Product does not available!!");
		}
		return product;

    }

    @Override
    public Object getProductById(String productId) throws ProductException, JsonMappingException, JsonProcessingException {
    	Gson obj=new Gson();
    	HttpHeaders header = new HttpHeaders();
    	header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	HttpEntity<String> entity = new HttpEntity<String>(header);
        Object product = restTemplate.exchange("http://localhost:9988/searchService/product/" + productId, HttpMethod.GET,entity,Object.class).getBody();
        String responseString=obj.toJson(product);
		System.out.println(responseString);
		
		ObjectMapper mapper=new ObjectMapper();
		if(product != null) {
			JSONObject prodJson=new JSONObject(responseString);
			String prod= prodJson.toString();
			ProductDTO productToSave=mapper.readValue(prod, ProductDTO.class);
			this.productRepository.save(productToSave);	
		}else {
			throw new ProductException("Product does not available!!");
		}
		return product;

    }

    @Override
    public Object getProductBySellerId(Integer sellerId) throws ProductException, JsonMappingException, JsonProcessingException {
    	Gson obj = new Gson();
    	HttpHeaders header = new HttpHeaders();
    	header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	HttpEntity<String> entity = new HttpEntity<String>(header);
        Object response = restTemplate.exchange("http://localhost:9988/searchService/product-sellerId/" + sellerId, HttpMethod.GET,entity,Object.class).getBody();
        String responseString=obj.toJson(response);
		System.out.println(responseString);
		
		ObjectMapper mapper=new ObjectMapper();
		
		JSONArray array=new JSONArray(responseString);
		for(int i=0;i<array.length();i++)
			
		{
			JSONObject prodJson=array.getJSONObject(i);
			String prod= prodJson.toString();
			ProductDTO productToSave=mapper.readValue(prod, ProductDTO.class);
			this.productRepository.save(productToSave);
		}
		
		System.out.println(array.get(0));
		return response;
    }

    @Override
    public Object getProductBySellerIdAndAvailability(Integer sellerId,Boolean availability) throws ProductException, JsonMappingException, JsonProcessingException {
    	Gson obj = new Gson();
    	HttpHeaders header = new HttpHeaders();
    	header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	HttpEntity<String> entity = new HttpEntity<String>(header);
    	Object response = restTemplate.exchange("http://localhost:9988/searchService/product-sellerId-availability?sellerId=" + sellerId + "&availability=" + availability, HttpMethod.GET,entity,Object.class).getBody();
    	String responseString=obj.toJson(response);
		System.out.println(responseString);
		
		ObjectMapper mapper=new ObjectMapper();
		
		JSONArray array=new JSONArray(responseString);
		System.out.println(array);
		for(int i=0;i<array.length();i++)
			
		{
			JSONObject prodJson=array.getJSONObject(i);
			String prod= prodJson.toString();
			ProductDTO productToSave=mapper.readValue(prod, ProductDTO.class);
			this.productRepository.save(productToSave);
		}
		
		System.out.println(array.get(0));
		return response;
    }

   

    @Override
    public Object getProductWithinPriceRange(Double from, Double to) throws  JsonMappingException, JsonProcessingException,ProductException {
    	Gson obj = new Gson();
    	HttpHeaders header = new HttpHeaders();
    	header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	HttpEntity<String> entity = new HttpEntity<String>(header);
    	Object response = restTemplate.exchange("http://localhost:9988/searchService/products-within-price-range?from=" + from +"&to="+to, HttpMethod.GET,entity,Object.class).getBody();
    	String responseString=obj.toJson(response);
		System.out.println(responseString);
		
		ObjectMapper mapper=new ObjectMapper();
		
		JSONArray array=new JSONArray(responseString);
		System.out.println(array);
		for(int i=0;i<array.length();i++)
			
		{
			JSONObject prodJson=array.getJSONObject(i);
			String prod= prodJson.toString();
			ProductDTO productToSave=mapper.readValue(prod, ProductDTO.class);
			this.productRepository.save(productToSave);
		}
		
		System.out.println(array.get(0));
		return response;
    }



}
